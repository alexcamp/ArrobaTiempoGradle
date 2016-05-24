package com.telefonica_chile.bandeja.bintegrada.session;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

import org.apache.log4j.Logger;

import com.tecnonautica.utiles.db.DBManager;
import com.tecnonautica.utiles.tablas.Tabla;
import com.tecnonautica.utiles.tablas.TablaException;
import com.telefonica_chile.atiempo.utiles.Fecha;
import com.telefonica_chile.atiempo.utiles.FechaException;
import com.telefonica_chile.atiempo.utiles.LoggerFactory;
import com.telefonica_chile.bandeja.dto.UserCoDto;

public class TablaBandejaSupervisor extends Tabla
{
	private DBManager db = new DBManager();
	private static SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	protected transient Logger log = LoggerFactory.getLogger(getClass());
	
	public TablaBandejaSupervisor(int paginaActual)
	{
		super(paginaActual);
		db.setDataSource(DBManager.JDBC_BANDEJA);
	}

	protected long getNumeroTotalElementos(HashMap filtro) throws TablaException
	{
		int tot = getCantidadElementos(filtro);
		long aux = (long) tot;

		return aux;
	}

	protected List getElementosPagina(HashMap filtro) throws TablaException
	{
		String sqlWhereFijo = getWhereFijo(filtro);
		
		String sql =
			"select * from ( select UU.USUA_ID,uu.USUA_LOGIN,uu.USUA_NOMBRE," +
			" uu.USUA_APE_PATERNO,uu.USUA_APE_MATERNO," +
			" (select count(bi.BI_NRO_PETICION) from atiempo.bintegrada bi" +
			" where bi.BI_VISIBLE=1 and bi.USUA_ID=uu.USUA_ID)" +
			"  AS NROPETICIONES " +
			" ,   row_number() over(ORDER BY UU.USUA_LOGIN )" +
			" as row " +
			" from atiempo.JER_USUARIO_ROL ur,atiempo.usuario uu where ur.JER_SUP_UID!=0 " +
			" and uu.USUA_ID=ur.JER_SUB_UID " +
			sqlWhereFijo
			+" group by UU.USUA_ID,uu.USUA_LOGIN,"
			+" uu.USUA_NOMBRE,uu.USUA_APE_PATERNO,uu.USUA_APE_MATERNO ) subtabla where row >= "
				+ getIdxInicial()
				+ " and row <= "
				+ getIdxFinal()
				+ " "  ;
				
//		log.debug("SQL->"+sql);

		DBManager db = new DBManager();
		db.setDataSource(DBManager.JDBC_BANDEJA);
		Hashtable salida = db.selectReadUncommitted(sql.toString());
		String[] USUA_ID = (String[]) salida.get("USUA_ID");
		String[] USUA_LOGIN = (String[]) salida.get("USUA_LOGIN");
		String[] USUA_NOMBRE = (String[]) salida.get("USUA_NOMBRE");
		String[] USUA_APE_PATERNO = (String[]) salida.get("USUA_APE_PATERNO");
		String[] USUA_APE_MATERNO = (String[]) salida.get("USUA_APE_MATERNO");
		String[] NROPETICIONES = (String[]) salida.get("NROPETICIONES");
				
		List lista = new ArrayList();
		for (int i = 0; USUA_ID != null && i < USUA_ID.length; i++)
		{
			UserCoDto dto=new UserCoDto();
			dto.setUsuaId(new Long(USUA_ID[i]));
			dto.setUsuaLogin(USUA_LOGIN[i]);
			dto.setUsuaNombre(USUA_NOMBRE[i]);
			dto.setUsuaApePaterno(USUA_APE_PATERNO[i]);
			dto.setUsuaApeMaterno(USUA_APE_MATERNO[i]);
			dto.setNroPeticiones(new Integer(NROPETICIONES[i]));
			lista.add(dto);
		}
		return lista;
	}
	
	
	private int getCantidadElementos(HashMap filtro)
	{
		int cantElementos = 0;
		String sqlWhereFijo = getWhereFijo(filtro);
	
	
		String sql = " SELECT  count(DISTINCT uu.USUA_ID) AS TOTAL " +
			" from atiempo.JER_USUARIO_ROL ur,atiempo.usuario uu where ur.JER_SUP_UID!=0 " +
			" and uu.USUA_ID=ur.JER_SUB_UID " +
		sqlWhereFijo ;
	
		DBManager db = new DBManager();
		db.setDataSource(DBManager.JDBC_BANDEJA);

//		log.debug(sql);
	
	
		Hashtable salida = db.selectReadUncommitted(sql);

		String[] Total = (String[]) salida.get("TOTAL");
		if (Total == null || Total.length == 0)
		{
			log.debug("Estoy retornando Cero");
			return 0;
		}
		try
		{
			cantElementos = Integer.parseInt(Total[0]);
		} catch (Exception e)
		{
			e.printStackTrace();
			cantElementos = 0;
		}
		return cantElementos;
	}
	
	private String getWhereFijo(HashMap filtro)
	{
		String whereFijo="";
	
		whereFijo += utilWhere("UR.JER_SUP_UID", (String) filtro.get("usua_id"), "N");
		whereFijo += utilWhere("UR.JER_ROL",(String)filtro.get("rol"),"N");
		
	
		return whereFijo;
	}
	
	public String utilWhere(String campo, String valor, String tipo)
	{

		if (valor == null || "".equals(valor))
			return "";

		if ("N".equals(tipo))
			return (" and " + campo + "=" + valor);

		if ("S".equals(tipo))
			return (" and " + campo + "='" + valor + "'");

		if ("LS".equals(tipo))
			return (" and " + campo + " like (" + valor + ")");

		if ("FC".equals(tipo))
			return (" and " + campo + " " + valor + " ");

		if ("IN".equals(tipo))
			return (" and " + campo + " in (" + valor + ") ");

		if("F".equals(tipo))
		{
			try
			{
				Fecha fecha=new Fecha(valor,"dd/MM/yyyy");
				return (" and " + campo + " >= '" + fecha.getFechaconFormato(10)+"' and "+campo+" <= '"+fecha.getFechaconFormato(10)+"'");
			} catch (FechaException e)
			{
				e.printStackTrace();
			}
		}
		if("FI".equals(tipo))
		{
			try
			{
				Fecha fecha=new Fecha(valor,"dd/MM/yyyy");
				return (" and " + campo + " >= '" + fecha.getFechaconFormato(10)+"'");
			} catch (FechaException e)
			{
				e.printStackTrace();
			}
		}
		if("FH".equals(tipo))
		{
			try
			{
				Fecha fecha=new Fecha(valor,"dd/MM/yyyy");
				String fechaS=fecha.getYYYYMMDD();
				String horaminutoss="235959";
				Fecha nueva=new Fecha(fechaS+horaminutoss,"aaaammddhhmmss");
				return (" and " + campo + " <= '" + nueva.getFechaconFormato(10)+"'");
			} catch (FechaException e)
			{
				e.printStackTrace();
			}
		}
		return "";
	}
}
