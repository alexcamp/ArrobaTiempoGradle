package com.telefonica_chile.bandeja.semaforos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Enumeration;
import java.util.Hashtable;

import org.apache.log4j.Logger;

import com.tecnonautica.utiles.db.DBManager;
import com.telefonica_chile.atiempo.utiles.LoggerFactory;

/**
 * @author jorginho
 */
public class Horario {

	/**
	 * Obtencion de logger.
	 * @author amartoq
	 */
	protected transient Logger log = LoggerFactory.getLogger(getClass());

	public static final int DT_HORARIO = 1;
	public static final int DT_LIMITE = 2;

	private Hashtable horario = new Hashtable();
	private String rol;
	private String actSemaforo;
	private int tipoSemaforo;
	private String apId;

	public void setRol(String param) {
		rol = param;
	}

	public void setActividad(String param) {
		actSemaforo = param;
	}

	int getData(int tipo, String id) {
		if (horario == null)
			return 0;

		String key = "", keyDef = "";
		if (tipo == DT_HORARIO) {
			key = apId + "-" + rol + "-" + id;
			keyDef = "2-ROL_DEFAULT-" + id;
		} else if (tipo == DT_LIMITE) {
			key = apId + "-" + actSemaforo + "-" + tipoSemaforo + "-" + id;
			keyDef = "2-DEFAULT-" + tipoSemaforo + "-" + id;
		}

		Integer data = (Integer) horario.get(key);

		if (data == null) {
			//log.warn("hash horario no contenia key " + key + " ==> por defecto uso " + keyDef);
			data = (Integer) horario.get(keyDef);
		}
		if (data == null) {
			//log.warn("hash horario no contenia key " + key + " ==> por defecto uso 0");
			return 0;
		}

		return data.intValue();
	}

	void setHorario() throws AlertasException {
		setLimites();
		setHorarioByRol();
	}

	private static final String sqlSelectHorarios="SELECT T.AP_ID, T.HR_DIA, T.HR_TIPO_SEMAFORO, T.ROL_ID, " +
		" T.HR_HORA_INI, T.HR_MINU_INI, T.HR_HORA_FIN, T.HR_MINU_FIN, " +
		" T.HR_HH_TRABAJO FROM ATIEMPO.HORARIO T";
	private void setHorarioByRol() throws AlertasException
	{
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try
		{
			conn=DBManager.getConnection(DBManager.JDBC_BANDEJA);
			conn.setReadOnly(true);
			conn.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
			conn.setAutoCommit(true);
			pstmt=conn.prepareStatement(sqlSelectHorarios);
			rs=pstmt.executeQuery();
			while(rs.next())
			{
				Long idAplicacion=new Long(rs.getString("AP_ID"));
				String HR_DIA=rs.getString("HR_DIA");
				Integer HR_TIPO_SEMAFORO=new Integer(rs.getInt("HR_TIPO_SEMAFORO"));
				Long ROL_ID=new Long(rs.getLong("ROL_ID"));
				Integer HR_HORA_INI=new Integer(rs.getInt("HR_HORA_INI"));
				Integer HR_MINU_INI=new Integer(rs.getInt("HR_MINU_INI"));
				Integer HR_HORA_FIN=new Integer(rs.getInt("HR_HORA_FIN"));
				Integer HR_MINU_FIN=new Integer(rs.getInt("HR_MINU_FIN"));
				Integer HR_HH_TRABAJO=new Integer(rs.getInt("HR_HH_TRABAJO"));
				String codigoRol = "DEFAULT";
				if ( ROL_ID != null )
				{
					codigoRol = ROL_ID.toString();
				}

				horario.put(idAplicacion + "-" + codigoRol + "-HH-INICIO-" + HR_DIA,	HR_HORA_INI);
				horario.put(idAplicacion + "-" + codigoRol + "-MM-INICIO-" + HR_DIA,	HR_MINU_INI);
				horario.put(idAplicacion + "-" + codigoRol + "-HH-TERMINO-" + HR_DIA,	HR_HORA_FIN);
				horario.put(idAplicacion + "-" + codigoRol + "-MM-TERMINO-" + HR_DIA,	HR_MINU_FIN);
				horario.put(idAplicacion + "-" + codigoRol + "-NUM-HH-" + HR_DIA,		HR_HH_TRABAJO);
			}
		}
		catch(Exception e)
		{
			
		}
		finally
		{
			try
			{
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			}
			catch(Exception e)
			{
				throw new AlertasException(e);
			}
		}
	}
	
	
	private static final String sqlSelectLimites="SELECT T.LI_TIPO_SEMAFORO, T.ACT_ID, T.LI_LIMITE_ROJO," +
		" T.LI_LIMITE_AMARILLO, T.LI_LIMITE_NEGRO,AA.AP_ID,AA.ACT_CODIGO FROM ATIEMPO.LIMITE T,ATIEMPO.ACTIVIDAD AA " +
		" WHERE T.ACT_ID=AA.ACT_ID";
	private void setLimites() throws AlertasException
	{		
		//Collection listLimites = getLimites();
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try
		{
			conn=DBManager.getConnection(DBManager.JDBC_BANDEJA);
			conn.setReadOnly(true);
			conn.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
			conn.setAutoCommit(true);
			pstmt=conn.prepareStatement(sqlSelectLimites);
			rs=pstmt.executeQuery();
			while(rs.next())
			{
				String LI_TIPO_SEMAFORO=rs.getString("LI_TIPO_SEMAFORO");
				String ACT_ID=rs.getString("ACT_ID");
				Integer LI_LIMITE_ROJO=new Integer(rs.getInt("LI_LIMITE_ROJO"));
				Integer LI_LIMITE_AMARILLO=new Integer(rs.getInt("LI_LIMITE_AMARILLO"));
				Integer LI_LIMITE_NEGRO=new Integer(rs.getInt("LI_LIMITE_NEGRO"));
				String codigoActividad=rs.getString("ACT_CODIGO");
				Long  idAplicacion=new Long(rs.getLong("AP_ID"));
				
				String prefijo = idAplicacion + "-" + codigoActividad + "-" + LI_TIPO_SEMAFORO + "-LIM-";

				horario.put( prefijo + "AMARILLO", LI_LIMITE_AMARILLO);
				horario.put( prefijo + "ROJO", LI_LIMITE_ROJO);
				horario.put( prefijo + "NEGRO", LI_LIMITE_NEGRO);
			}
		}
		catch(Exception exception)
		{
			throw new AlertasException(exception);
		}
		finally
		{
			try
			{
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			}
			catch(Exception e)
			{
				throw new AlertasException(e);
			}
		}
	}

	public String toString() {
		String str = "\nRol: '" + rol + "' -- ActSem: '" + actSemaforo + "'";
		Enumeration e = horario.keys();
		while (e.hasMoreElements()) {
			String key = (String) e.nextElement();
			str += "\n'" + key + "'='" + horario.get(key) + "'";
		}

		return (str);
	}

	void setTipoSemaforo(int param) {
		tipoSemaforo = param;
	}
	
	public void setApId(String string) {
		apId = string;
	}


//	private Collection getHorarios() throws AlertasException
//	{
//		try
//		{
//			HorarioLocalHome horarioHome
//					= (HorarioLocalHome)HomeFactory.getHome(HorarioLocalHome.JNDI_NAME);
//			return (Collection)horarioHome.findAll();
//		} catch (Exception e) {
//			log.warn("Problemas recuperando horarios!", e);
//			throw new AlertasException("Problemas recuperando horarios!", e);
//		}
//	}
	
//	private Collection getLimites() throws AlertasException
//	{
//		try
//		{
//			log.debug("Inicio get Limites:"+new Fecha().getFechaconFormato());
//			LimiteLocalHome limiteHome
//						= (LimiteLocalHome) HomeFactory.getHome(LimiteLocalHome.JNDI_NAME);
//			Collection retorno=limiteHome.findAll();
//			log.debug("Fin get Limites:"+new Fecha().getFechaconFormato());
//			return retorno;
//			
//		} catch (Exception e) {
//			log.warn("Problemas recuperando limites!", e);
//			throw new AlertasException("Problemas recuperando limites!", e);
//		}
//	}
}
