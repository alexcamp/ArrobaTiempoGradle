package com.telefonica_chile.bandeja.mantenedores;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

import com.tecnonautica.utiles.db.DBManager;
import com.tecnonautica.utiles.tablas.Tabla;

public class TablaUsuariosSupervisados extends Tabla implements Serializable {

	public TablaUsuariosSupervisados(int paginaActual) {
		super(paginaActual);
	}

	protected long getNumeroTotalElementos(HashMap filtro) {
		HashMap fAux = new HashMap();
		fAux.put("idUsuario", (String) filtro.get("idUsuario"));
		fAux.put("idRol", (String) filtro.get("idRol"));
		String where = getWhereFijo( fAux );
		
		
		String sql = "select count(*) as TOTAL " +
			"from ATIEMPO.JER_USUARIO_ROL J, ATIEMPO.USUARIO U " +
			"where J.jer_sub_uid=U.usua_id " + where; 


		DBManager db = new DBManager();
		db.setDataSource(DBManager.JDBC_BANDEJA);
		//Gustavo - CR 16440 - Cambio la llamada al metodo de ejecución de selectCommit a selectReadUncommited para setear readOnly y nivel de aislamiento
		Hashtable salida =  db.selectReadUncommitted(sql.toString());
		String[] cantidad = (String[]) salida.get("TOTAL");

		if ( cantidad != null && cantidad.length>0)
			return Long.parseLong( cantidad[0] );
		
		return ( 0 );
	}

	private HashMap getEstadosUsuario() {
		//Saco todos los Usuarios.
		HashMap map = new HashMap();

		String sql = "select esus_id, esus_nombre from ATIEMPO.ESTADO_USUARIO";

		DBManager db = new DBManager();
		db.setDataSource("jdbc/bandeja");
		try {
			//Gustavo - CR 16440 - Cambio la llamada al metodo de ejecución de selectCommit a selectReadUncommited para setear readOnly y nivel de aislamiento
			Hashtable salida = db.selectReadUncommitted( sql.toString() );
			
			String[] id = (String[]) salida.get("ESUS_ID");
			String[] nombre = (String[]) salida.get("ESUS_NOMBRE");
			for (int i=0; id!=null && i<id.length; i++) {
				map.put( id[i], nombre[i]);
			}
		} catch (Exception e) {
		}

		return map;
	}

	private HashMap getPeticosAsignadas(HashMap filtro) {
		//String listaAct = (String) filtro.get("actividades");
		String where = getWhereFijo( filtro );

		// ATST no se mete aqui
		where += utilWhere("B.rol_id", (String) filtro.get("idRol"), "N");

		HashMap map = new HashMap();
		String sql = "select B.usua_id, count(B.usua_id) as TOTAL " +
			"from ATIEMPO.BINTEGRADA B, ATIEMPO.JER_USUARIO_ROL J " +
			"where B.usua_id = J.jer_sub_uid and B.bi_visible=1 " +
			where + " " + 
			"group by usua_id";
		DBManager db = new DBManager();
		db.setDataSource("jdbc/bandeja");
		try {
			//Gustavo - CR 16440 - Cambio la llamada al metodo de ejecución de selectCommit a selectReadUncommited para setear readOnly y nivel de aislamiento
			Hashtable salida = db.selectReadUncommitted( sql.toString() );
			
			String[] id = (String[]) salida.get("USUA_ID");
			String[] total = (String[]) salida.get("TOTAL");
			for (int i=0; id!=null && i<id.length; i++) {
				map.put( id[i], total[i]);
			}
		} catch (Exception e) {
		}

		return map;
	}

	protected List getElementosPagina(HashMap filtro) {
		//String where = getWhereFijo( filtro );
		
		HashMap fAux = new HashMap();
		fAux.put("idUsuario", (String) filtro.get("idUsuario"));
		fAux.put("idRol", (String) filtro.get("idRol"));
		String where = getWhereFijo( fAux );
		
		
		// Tengo los nombres de los Estado
		HashMap mapEstado = getEstadosUsuario();

		// Tengo las Cantidades en Bandeja Integrada
		HashMap mapTotalesUsuario = getPeticosAsignadas( filtro );

		String sql = "select * from ( " +
			"select U.usua_id, U.usua_login, U.usua_nombre, " +
			"U.usua_telefono, U.usua_habilitado, U.esus_ultima_actualizacion, " +
			"U.esus_id,  " +
			"row_number() over(order by U.usua_nombre) as row " +
			"from ATIEMPO.USUARIO U, ATIEMPO.JER_USUARIO_ROL J " +
			"where J.jer_sub_uid=U.usua_id " +
			where + " " +
		" ) subtabla where row >= " + getIdxInicial() + " and row <= " + getIdxFinal() + "";
	
		DBManager db = new DBManager();
		db.setDataSource("jdbc/bandeja");
		//Gustavo - CR 16440 - Cambio la llamada al metodo de ejecución de ejecutaConsultaHash a selectReadUncommited para setear readOnly y nivel de aislamiento
		Hashtable salida = db.selectReadUncommitted(sql.toString());
		String[] id = (String[]) salida.get("USUA_ID");
		String[] login = (String[]) salida.get("USUA_LOGIN");
		String[] nombre = (String[]) salida.get("USUA_NOMBRE");
		String[] telefono = (String[]) salida.get("USUA_TELEFONO");
		String[] habilitado = (String[]) salida.get("USUA_HABILITADO");
		String[] fecha_estado = (String[]) salida.get("ESUS_ULTIMA_ACTUALIZACION");
		String[] estado_id = (String[]) salida.get("ESUS_ID");
		
//		String[] estado_usuario = (String[]) salida.get("ESUS_NOMBRE");
//		String[] c = (String[]) salida.get("C");
		
		List lista = new ArrayList();
		for (int  i = 0; id != null && i < id.length; i++) {
			UsuarioBandejaSupervisorDTO u = new UsuarioBandejaSupervisorDTO();
			u.setId(new Long(id[i]));
			u.setUsername(login[i]);
			u.setNombre(nombre[i]);
			u.setFono(telefono[i]);
			u.setHabilitado(habilitado[i]);

			Integer tot = (Integer) cast( (String) mapTotalesUsuario.get( id[i] ), new Integer(0), "INTEGER");
			u.setCantidadPeticiones( tot.intValue() );
			u.setEstado( (String) cast( (String) mapEstado.get( estado_id[i] ), "", "STRING" ) );
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
			if (fecha_estado[i] != null && !fecha_estado[i].equals("")) {
				try {
					Date fecha = sdf.parse(fecha_estado[i]);
					Timestamp tp = new Timestamp(fecha.getTime());
					u.setUltimoCambioEstado(tp);
				} catch (ParseException e) {
					u.setUltimoCambioEstado(null);
				}
			}
						
			/* 
			 * Consulta si el estado es diferente de DESCONECTADO. En tal caso, pregunta si lleva mas de 15 minutos 
			 * sin actualizar el estado. Si eso ocurre, se asume que el usuario esta desconectado
			 */
			if (u.getUltimoCambioEstado() != null && u.getEstado() != null && !u.getEstado().equals("DESCONECTADO")) {
				Timestamp ultimaActualizacion = u.getUltimoCambioEstado();
				long tiempoCambio = ultimaActualizacion.getTime();
				long tiempoActual = System.currentTimeMillis();
				
				/* Revisa desconexion cada 15 minutos */				
				if (tiempoActual > tiempoCambio + 900000) {
					u.setEstado("Desconectado");
				}
			}			
			
			lista.add(u);
		}
		return lista;
	}

/*
	protected List getElementosPaginaOld(HashMap filtro) {
		String       act = (String)filtro.get("actividades");
		StringBuffer sql = new StringBuffer()
		
		
			.append("select * from (")
			.append(" select")
			.append("     u.usua_id,")
			.append("     u.usua_login,")
			.append("     u.usua_nombre,")
			.append("     u.usua_telefono,")
			.append("     u.usua_habilitado,")
			.append("     u.esus_ultima_actualizacion,")
			.append("      e.esus_nombre,")
			.append("     count(b.bi_id) c,")
			.append("	row_number() over() as row")
			.append(" from")
			.append("     jer_usuario_rol j,")
			.append("     usuario u left outer join")
			.append("     bintegrada b")
			.append(" on")
			.append(" 	(u.usua_id = b.usua_id  and b.act_id in ( " + act + "))")
			.append("     left outer join estado_usuario e")
			.append(" on")
			.append(" 	(u.esus_id = e.esus_id)")
			.append(" where")
			.append("     j.jer_sub_uid = u.usua_id");
		
		
		
		StringBuffer where = getWhere(filtro);
		if (where.length() > 0)
			sql.append(where);
			
			
		sql	.append(" group by")
			.append("     u.usua_id,")
			.append("     u.usua_login,")
			.append("     u.usua_nombre,")
			.append("     u.usua_telefono,")
			.append("     u.usua_habilitado,")
			.append("     u.esus_ultima_actualizacion,")
			.append("     e.esus_nombre")
			.append("     order by u.usua_nombre");
			
		sql.append(") subtabla where ")
			.append(" row >= ").append(getIdxInicial()).append(" and row <= ").append(getIdxFinal());
			
		DBManager db = new DBManager();
		db.setDataSource("jdbc/bandeja");
		Hashtable salida = db.ejecutaConsultaHash(sql.toString());
		String[] id = (String[]) salida.get("USUA_ID");
		String[] login = (String[]) salida.get("USUA_LOGIN");
		String[] nombre = (String[]) salida.get("USUA_NOMBRE");
		String[] telefono = (String[]) salida.get("USUA_TELEFONO");
		String[] habilitado = (String[]) salida.get("USUA_HABILITADO");
		String[] fecha_estado = (String[]) salida.get("ESUS_ULTIMA_ACTUALIZACION");
		String[] estado_usuario = (String[]) salida.get("ESUS_NOMBRE");
		String[] c = (String[]) salida.get("C");
		
		List lista = new ArrayList();
		for (int  i = 0; id != null && i < id.length; i++) {
			UsuarioBandejaSupervisorDTO u = new UsuarioBandejaSupervisorDTO();
			u.setId(new Long(id[i]));
			u.setUsername(login[i]);
			u.setNombre(nombre[i]);
			u.setFono(telefono[i]);
			u.setHabilitado(habilitado[i]);
			u.setCantidadPeticiones(Integer.parseInt(c[i]));
			u.setEstado(estado_usuario[i]);
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
			if (fecha_estado[i] != null && !fecha_estado[i].equals("")) {
				try {
					Date fecha = sdf.parse(fecha_estado[i]);
					Timestamp tp = new Timestamp(fecha.getTime());
					u.setUltimoCambioEstado(tp);
				} catch (ParseException e) {
					u.setUltimoCambioEstado(null);
				}
			}
						
//			/ * 
//			 * Consulta si el estado es diferente de DESCONECTADO. En tal caso, pregunta si lleva mas de 15 minutos 
//			 * sin actualizar el estado. Si eso ocurre, se asume que el usuario esta desconectado
//			 * /

			if (u.getUltimoCambioEstado() != null && u.getEstado() != null && !u.getEstado().equals("DESCONECTADO")) {
				Timestamp ultimaActualizacion = u.getUltimoCambioEstado();
				long tiempoCambio = ultimaActualizacion.getTime();
				long tiempoActual = System.currentTimeMillis();
				
//				// Revisa desconexion cada 15 minutos				
				if (tiempoActual > tiempoCambio + 900000) {
					u.setEstado("Desconectado");
				}
			}			
			
			lista.add(u);
		}
		return lista;
	}
*/
	public String getWhereFijo(HashMap filtro) {
		String whereFijo = "";
		whereFijo += utilWhere("J.jer_sup_uid", (String) filtro.get("idUsuario"), "N");
		whereFijo += utilWhere("J.jer_rol", (String) filtro.get("idRol"), "N");
		
		whereFijo += utilWhere("B.bi_tipo_trabajo", (String) filtro.get("tipo_trabajo"), "N");
		whereFijo += utilWhere("B.bi_familia_ps", (String) filtro.get("familia"), "N");
		whereFijo += utilWhere("days(B.bi_fecha_compromiso)-days(current timestamp)", (String) filtro.get("fecha_compromiso"), "FC");
		whereFijo += utilWhere("B.bi_estado_peticion", (String) filtro.get("estado_peticion"), "IN");

		return whereFijo;
	}

	public String utilWhere(String campo, String valor, String tipo) {
			
		if ( valor == null || "".equals(valor) )
			return "";
			
		if ( "N".equals(tipo) )
			return ( " and " + campo + "=" + valor );
			  
		if ( "S".equals(tipo) )
			return ( " and " + campo + "='" + valor + "'");
	
		if ( "LS".equals(tipo) )
			return ( " and " + campo + " like (" + valor + ")");
			
		if ( "FC".equals(tipo) )
			return ( " and " + campo + " " + valor + " ");
	
		if ( "IN".equals(tipo) )
			return ( " and " + campo + " in (" + valor + ") ");
						
		return "";
	}
	
	protected Object cast( String str, Object def, String obj ) {
		if ( str == null )
			return def;

		try {
			if ( "LONG".equals( obj ) )
				return ( new Long( str ) );
			if ( "INTEGER".equals( obj ) )
				return ( new Integer( str ) );
			if ( "STRING".equals( obj ) )
				return ( str );
		} catch (Exception e) {
			// No se pudo Trasnformar
		}

		return def;

	}

}
