package com.telefonica_chile.bandeja.bintegrada.session;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import javax.ejb.EJBException;
import javax.ejb.FinderException;
import javax.naming.NamingException;

import org.apache.log4j.Logger;

import co.com.telefonica.atiempo.ejb.eb.Campo_variableKey;
import co.com.telefonica.atiempo.ejb.eb.Campo_variableLocal;
import co.com.telefonica.atiempo.ejb.eb.Campo_variableLocalHome;

import com.tecnonautica.utiles.db.DBManager;
import com.tecnonautica.utiles.tablas.Tabla;
import com.tecnonautica.utiles.tablas.TablaException;
import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.atiempo.utiles.LoggerFactory;
import com.telefonica_chile.bandeja.dto.PeticionDTO;
import com.telefonica_chile.bandeja.semaforos.Alertas;
import com.telefonica_chile.bandeja.semaforos.AlertasException;

public class TablaPeticion extends Tabla {

	/**
	 * Obtencion de logger.
	 * @author amartoq
	 */
	protected transient Logger log = LoggerFactory.getLogger(getClass());

	private static SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public TablaPeticion(int paginaActual) {
		super(paginaActual);		
	}
	
	public TablaPeticion() {
		super();
	}
	
	private javax.ejb.SessionContext mySessionCtx;
	/**
	 * getSessionContext
	 */
	public javax.ejb.SessionContext getSessionContext() {
		return mySessionCtx;
	}
	/**
	 * setSessionContext
	 */
	public void setSessionContext(javax.ejb.SessionContext ctx) {
		mySessionCtx = ctx;
	}


	public long getNumeroTotalElementos(HashMap filtro) {
		
		int tot = getCantidadElementos(filtro);
		long aux = (long) tot;
		//Set set = getIdsElementos(filtro);
		//return set.size();
		
		return aux;
	}


	public HashMap getWhereVariable(HashMap filtro) {
		HashMap filtrosVariables = getFiltrosVariables(filtro);
		HashMap mapAux = new HashMap();
		mapAux.put("T_VAR", "");
		mapAux.put("W_VAR", "");

		if ( filtrosVariables==null || filtrosVariables.size() <= 0)
			return mapAux;

		String sqlWhereVariable = "";
		String sqlTabVar = "";
		HashMap auxCV = getAllCampoVariable();
		String key = "", valor = "";;
		String aux = "";
		String and = "";
		String and1 = "";
		String coma = "";  
		String sqlWhereVar = "";
		String sqlWhereVar1 = "";
		int  cont = 1;
		for (Iterator it = filtrosVariables.keySet().iterator(); it.hasNext(); ) {
			key = (String) it.next();
			valor = (String) filtrosVariables.get(key);
			aux = (String) auxCV.get(key);
			if (aux != null && valor!=null && !"".equals(valor) ) {
				sqlWhereVar += and + " (V" + cont + ".cv_id=" + aux + " and " + "V" + cont + ".valor='" + valor + "') ";

				sqlTabVar += coma + " VALOR_VARIABLE V" + cont + " ";

				if ( cont > 1) {
					sqlWhereVar1 += and1 + " V" + (cont-1) + ".bi_id=V" + cont + ".bi_id ";
					and1 = "and";

				}
				cont++;
				and = "and";
				coma = ",";
			}
		}
		
		if (!"".equals( sqlWhereVar ) ) {
			sqlWhereVariable =  " and B.bi_id=V1.bi_id " + and1 + " " + sqlWhereVar1 + " " + and + " " + sqlWhereVar + " ";
			sqlTabVar =  ", " + sqlTabVar;
		} else {
			sqlWhereVariable = "";
			sqlTabVar = "";
		}

		mapAux.put("T_VAR", sqlTabVar);
		mapAux.put("W_VAR", sqlWhereVariable);
		
		return mapAux;
	}

	public HashMap getAllCampoVariable() {
		HashMap map = new HashMap(); 

		try {
			Campo_variableLocalHome cvHome = (Campo_variableLocalHome) HomeFactory.getHome(Campo_variableLocalHome.JNDI_NAME);
			Campo_variableLocal cvLocal = null;
			Collection c = cvHome.findAll();
			for ( Iterator it = c.iterator(); it.hasNext(); ) {
				cvLocal = (Campo_variableLocal) it.next();
				Campo_variableKey cvKey = (Campo_variableKey) cvLocal.getPrimaryKey();
				map.put(cvLocal.getCv_nombre_int(), ""+cvKey.cv_id);
			}
		} catch (EJBException e) {
		} catch (NamingException e) {
		} catch (FinderException e) {
		} catch (Exception e) {
		}
		
		return map;
	
	}


	public int getCantidadElementos(HashMap filtro) {
		int cantElementos = 0;
		String sqlWhereFijo = getWhereFijo(filtro);

		HashMap auxMap = getWhereVariable(filtro);

		String condActividadRol="and A.rol_id = R.rol_id";
		//Obtengo el rol para saber si la aplicacion es ATST o no
		Long idRol = (Long) filtro.get("idRol");
		if (idRol!=null && esATST(idRol)) //Solo nos interesa modificar el caso cuando viene el idRol
		   condActividadRol="";

		String sql = "" +
			"select distinct b.bi_id,  b.bi_nro_peticion, b.ap_id, b.act_id, a.act_descripcion " +
			"from ATIEMPO.BINTEGRADA B, ATIEMPO.USUARIO U, ATIEMPO.ACTIVIDAD A, ATIEMPO.ROL R, ATIEMPO.APLICACION AP " +
			( (String) auxMap.get("T_VAR") ) +
			" where B.act_id = A.act_id " + condActividadRol + " and B.ap_id = AP.ap_id " +
			"and B.usua_id = U.usua_id and (B.bi_visible = 1 or B.bi_visible is null) " +
			// Hay que agregar los Filtros.
			sqlWhereFijo +
			( (String) auxMap.get("W_VAR") );
		
		DBManager db = new DBManager();
		db.setDataSource(DBManager.JDBC_BANDEJA);
		//Gustavo - CR 16440 - Cambio la llamada al metodo de ejecución de selectCommit a selectReadUncommited para setear readOnly y nivel de aislamiento
		Hashtable salida =  db.selectReadUncommitted( sql.toString() );
		
		String[] BI_ID = (String[]) salida.get("BI_ID");
		String[] PETI = (String[]) salida.get("BI_NRO_PETICION");
		String[] AP_ID = (String[]) salida.get("AP_ID");
		String[] AC_ID = (String[]) salida.get("ACT_ID");
		String[] AC_DESC = (String[]) salida.get("ACT_DESCRIPCION");
		if (BI_ID == null || BI_ID.length==0)
			return 0;
		else {
			try {
				for (int i=0; i<BI_ID.length; i++) {
					PeticionDTO p = new PeticionDTO();
					p.setBiId(new Long(BI_ID[i]));
					p.setBiNroPeticion(new Long(PETI[i]));
					p.setApId(new Long(AP_ID[i]));
					p.setActividadId(AC_ID[i]);
					p.setActividadDescripcion(AC_DESC[i]);
					this.listaIdElements.add( p );
				}
				
				return BI_ID.length;

			} catch (Exception e) {
				cantElementos = 0;
			}
		}
		
		return cantElementos;
	}

	protected String utilWhere(String campo, String valor, String tipo) {
		
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
		
		return "";
	}
	
	public String getWhereFijo(HashMap filtro) {
		String whereFijo = "";
		Long idRol = (Long) filtro.get("idRol");
		String rolId;
		rolId = (idRol == null) ? "" : ( ""+idRol);
		
		whereFijo += utilWhere("B.usua_id", (String) filtro.get("USUA_ID"), "N");
		whereFijo += utilWhere("B.bi_tipo_trabajo", (String) filtro.get("BI_TIPO_TRABAJO"), "N");
		whereFijo += utilWhere("B.bi_grupo_segmento", (String) filtro.get("SEGM_ID"), "N");
		whereFijo += utilWhere("B.agen_id", (String) filtro.get("AGEN_ID"), "N");
		whereFijo += utilWhere("B.ap_id", (String) filtro.get("AP_ID"), "N");
		whereFijo += utilWhere("B.bi_nro_peticion", (String) filtro.get("BI_NRO_PETICION"), "N");
		whereFijo += utilWhere("B.act_id", (String) filtro.get("ACTIVIDAD_ID"), "N");
		whereFijo += utilWhere("R.rol_id", rolId, "N");
		whereFijo += utilWhere("R.rol_id", (String) filtro.get("ROL_ID"), "N");
		whereFijo += utilWhere("R.isp_id", (String) filtro.get("ISP_ID"), "N");
		whereFijo += utilWhere("B.bi_familia_ps", (String) filtro.get("BI_FAMILIA_PS"), "LS");
		whereFijo += utilWhere("B.bi_cliente_rut", (String) filtro.get("BI_CLIENTE_RUT"), "S");
		whereFijo += utilWhere("B.bi_cliente_rutdv", (String) filtro.get("BI_CLIENTE_RUTDV"), "S");
		whereFijo += utilWhere("B.bi_cliente_area", (String) filtro.get("BI_CLIENTE_AREA"), "S");
		whereFijo += utilWhere("B.bi_cliente_servicio", (String) filtro.get("BI_CLIENTE_SERVICIO"), "S");
		whereFijo += utilWhere("TC.actc_id", (String) filtro.get("ACT_TC_ID"), "N");
		whereFijo += utilWhere("days(bi_fecha_compromiso)-days(current timestamp)", (String) filtro.get("BI_FECHA_COMPROMISO"), "FC");
		whereFijo += utilWhere("B.TICA_ID",(String)filtro.get("opcocat"),"S");
		//ACA SE IMGRESO EL OTROS TIPOS
		whereFijo += utilWhere("B.bi_estado_peticion", (String) filtro.get("BI_ESTADO_PETICION"), "FC");
		return whereFijo;
	}


	public List getElementosPagina(HashMap filtro) throws TablaException {

		String condActividadRol="and A.rol_id = R.rol_id";
		//Obtengo el rol para saber si la aplicacion es ATST o no
		Long idRol = (Long)filtro.get("idRol");
		if(idRol!=null && esATST(idRol)) //Solo nos interesa modificar el caso cuando viene el idRol
		   condActividadRol="";
				

		String sqlWhereFijo = getWhereFijo(filtro);
		HashMap auxMap = getWhereVariable(filtro);

		String sql = 
			"select * from ( " +
				"select B.bi_id, B.bi_cliente_nombre, B.bi_cliente_apellidos, B.bi_cliente_area, " +
				"B.bi_cliente_servicio, B.bi_url_detalle, GS.grse_nombre, B.bi_fecha_inicio, " +
				"B.bi_fecha_apertura, B.bi_fecha_asignacion, B.bi_fecha_compromiso, B.ap_id, " +
				"B.bi_nro_peticion , U.usua_id, U.usua_login, U.usua_nombre, R.rol_id, R.rol_codigo, " +
				"R.rol_nombre, A.act_id, A.act_codigo, A.act_descripcion, A.act_nombre_reversa, " +
				"AP.ap_nombre, B.bi_estado_peticion, B.bi_tipo_trabajo, " +
				"B.bi_id_tipo_agendamiento, B.bi_id_rango, B.bi_hora_desde, B.bi_hora_hasta, " +
				"row_number() over(order by B.bi_grupo_segmento, B.bi_fecha_compromiso, T.titr_orden ) as row  " +
				"from ATIEMPO.BINTEGRADA B, ATIEMPO.USUARIO U, ATIEMPO.ACTIVIDAD A, ATIEMPO.ROL R, ATIEMPO.APLICACION AP, " +
				"AGENDA.GRUPO_SEGMENTO GS, ATIEMPO.TIPO_TRABAJO T " +
				( (String) auxMap.get("T_VAR") ) +
				"where B.act_id = A.act_id " + condActividadRol + " and B.ap_id = AP.ap_id and  " +
				"GS.grse_id=B.bi_grupo_segmento and B.usua_id = U.usua_id and B.bi_tipo_trabajo=T.titr_id " +
				"and (B.bi_visible = 1 or B.bi_visible is null) " +
				sqlWhereFijo +
				( (String) auxMap.get("W_VAR") ) +
			" ) subtabla where row >= " + getIdxInicial() + " and row <= " + getIdxFinal() + "";


		DBManager db = new DBManager();
		db.setDataSource("jdbc/bandeja");
		//Gustavo - CR 16440 - Cambio la llamada al metodo de ejecución de selectCommit a selectReadUncommited para setear readOnly y nivel de aislamiento
		Hashtable salida = db.selectReadUncommitted(sql.toString());
		
		String[] BI_ID = (String[]) salida.get("BI_ID");
		String[] BI_CLIENTE_NOMBRE = (String[]) salida.get("BI_CLIENTE_NOMBRE");
		String[] BI_CLIENTE_APELLIDOS = (String[]) salida.get("BI_CLIENTE_APELLIDOS");
		String[] BI_CLIENTE_AREA = (String[]) salida.get("BI_CLIENTE_AREA");
		String[] BI_CLIENTE_SERVICIO = (String[]) salida.get("BI_CLIENTE_SERVICIO");
		String[] BI_URL_DETALLE = (String[]) salida.get("BI_URL_DETALLE");			
		String[] GRSE_NOMBRE = (String[]) salida.get("GRSE_NOMBRE");		
		String[] BI_FECHA_INICIO = (String[]) salida.get("BI_FECHA_INICIO");
		String[] BI_FECHA_APERTURA = (String[]) salida.get("BI_FECHA_APERTURA");
		String[] BI_FECHA_ASIGNACION = (String[]) salida.get("BI_FECHA_ASIGNACION");
		String[] BI_FECHA_COMPROMISO = (String[]) salida.get("BI_FECHA_COMPROMISO");			 
		String[] AP_ID = (String[]) salida.get("AP_ID");
		String[] BI_NRO_PETICION = (String[]) salida.get("BI_NRO_PETICION");
		String[] USUA_ID = (String[]) salida.get("USUA_ID");
		String[] USUA_LOGIN = (String[]) salida.get("USUA_LOGIN");
		String[] USUA_NOMBRE = (String[]) salida.get("USUA_NOMBRE");
		String[] ROL_ID = (String[]) salida.get("ROL_ID");
		String[] ROL_CODIGO = (String[]) salida.get("ROL_CODIGO");
		String[] ROL_NOMBRE = (String[]) salida.get("ROL_NOMBRE");
		String[] ACT_ID = (String[]) salida.get("ACT_ID");
		String[] ACT_CODIGO = (String[]) salida.get("ACT_CODIGO");
		String[] ACT_DESCRIPCION = (String[]) salida.get("ACT_DESCRIPCION");
		String[] AP_NOMBRE = (String[]) salida.get("AP_NOMBRE");
		String[] BI_ESTADO_PETICION = (String[]) salida.get("BI_ESTADO_PETICION");
		String[] BI_TIPO_TRABAJO = (String[]) salida.get("BI_TIPO_TRABAJO");
		String[] ACT_NOMBRE_REVERSA = (String[]) salida.get("ACT_NOMBRE_REVERSA");
		
		// Para el Agendamiento
		String[] BI_ID_AGENDAMIENTO = (String[]) salida.get("BI_ID_TIPO_AGENDAMIENTO");
		String[] BI_IDRANGO= (String[]) salida.get("BI_ID_RANGO");
		String[] BI_HDESDE = (String[]) salida.get("BI_HORA_DESDE");
		String[] BI_HHASTA = (String[]) salida.get("BI_HORA_HASTA");

		Alertas alerta;
		try {
			alerta = new Alertas();
		} catch (AlertasException e) {
			log.error("Problemas recuperando alertas", e);
			throw new TablaException("Hubo problemas recuperando las alertas", e);
		}

		List lista = new ArrayList();					
		for (int i = 0; BI_ID != null && i < BI_ID.length; i++) {
			PeticionDTO p = new PeticionDTO();
			p.setBiId(new Long(BI_ID[i]));
			p.setBiClienteNombre(BI_CLIENTE_NOMBRE[i]);
			p.setBiClienteApellidos(BI_CLIENTE_APELLIDOS[i]);
			p.setBiClienteArea(BI_CLIENTE_AREA[i]);
			p.setBiClienteServicio(BI_CLIENTE_SERVICIO[i]);
			p.setBiUrlDetalle(BI_URL_DETALLE[i]);
			p.setApId(new Long(AP_ID[i]));
			p.setBiNroPeticion(new Long(BI_NRO_PETICION[i]));
			p.setUsuaId(new Long(USUA_ID[i]));
			p.setUsuaLogin(USUA_LOGIN[i]);
			p.setUsuaNombre(USUA_NOMBRE[i]);
			p.setRolId(ROL_ID[i]);
			p.setRolCodigo(ROL_NOMBRE[i]);
			p.setRolNombre(ROL_NOMBRE[i]);
			p.setRolCodigo(ROL_CODIGO[i]);
			p.setActividadId(ACT_ID[i]);
			p.setActividadCodigo(ACT_CODIGO[i]);
			p.setActividadDescripcion(ACT_DESCRIPCION[i]);
			p.setAplicacionNombre(AP_NOMBRE[i]);
			p.setActividadId(ACT_ID[i]);
			p.setActividadCodigo(ACT_CODIGO[i]);
			p.setActividadDescripcion(ACT_DESCRIPCION[i]);
			p.setNombreBloqueSegmento(GRSE_NOMBRE[i]);
			try{
				p.setEstadoPeticion(new Integer(BI_ESTADO_PETICION[i]));
				p.setTipoTrabajo(new Integer(BI_TIPO_TRABAJO[i]));
			}catch(Exception e)
			{
				log.info("El campo en Estado Peticion , y Tipo Trabajo viene NULO... MENSAJE==>"+e.toString());
				p.setEstadoPeticion(new Integer(1));
				p.setTipoTrabajo(new Integer(1));
			}
			p.setActividadNombreReversa(ACT_NOMBRE_REVERSA[i]);
			
			/**Se setean valores necesarios al Objeto Alerta**/				
			alerta.setRol(p.getRolCodigo());
			alerta.setActividadSemaforo(p.getActividadCodigo());
			alerta.setApId(p.getApId().toString());
								
			if (BI_FECHA_COMPROMISO != null && BI_FECHA_COMPROMISO[i].trim().length() > 0){
				Date fechaCompromiso = parseFecha(BI_FECHA_COMPROMISO[i]);
				p.setBiFechaCompromiso(fechaCompromiso);
				/**Alerta**/
				alerta.setFechaFinal(p.getBiFechaCompromiso());
				p.setBiSemaforoCompromiso(alerta.getSemaforoCompromiso());
			}
			
			if (BI_FECHA_ASIGNACION != null && BI_FECHA_ASIGNACION[i].trim().length() > 0){
				Date fechaAsignacion = parseFecha(BI_FECHA_ASIGNACION[i]);
				p.setBiFechaAsignacion(fechaAsignacion);
				/**Alerta**/
				alerta.setFechaFinal(p.getBiFechaAsignacion());
				p.setBiSemaforoActividad(alerta.getSemaforoActividad());
			}
			
			if (BI_FECHA_INICIO != null && BI_FECHA_INICIO[i].trim().length() > 0){
				p.setBiFechaInicio(parseFecha(BI_FECHA_INICIO[i]));						
			}					
			
			if (BI_FECHA_APERTURA != null && BI_FECHA_APERTURA[i].trim().length() > 0){
				p.setBiFechaApertura(parseFecha(BI_FECHA_APERTURA[i]));						
			}

			// Seteamos Valores Adicionales para elñ Agendamiento.
			if (BI_ID_AGENDAMIENTO!= null && BI_ID_AGENDAMIENTO[i].trim().length()>0)
				p.setIdTipoAgendamiento(new Long(BI_ID_AGENDAMIENTO[i]));
			if (BI_IDRANGO!= null && BI_IDRANGO[i].trim().length() > 0)
				p.setIdRango(new Integer(BI_IDRANGO[i]));
			if (BI_HDESDE != null && BI_HDESDE[i].trim().length() > 0)
				p.setHoraDesde(BI_HDESDE[i]);
			if (BI_HHASTA!= null && BI_HHASTA[i].trim().length() > 0)
				p.setHoraHasta(BI_HHASTA[i]);

			lista.add(p);
		}
			
		return lista;		
	}

	// Funcion que determina si el rol pertenece o no a la aplicacion ATST
	protected boolean esATST(Long idRol){

		boolean rolEsDeATST=false;

		//Procedemos a invocar el Session de BIntegrada
		try {
			BIntegradaSessionLocalHome home = (BIntegradaSessionLocalHome)
					HomeFactory.getHome(BIntegradaSessionLocalHome.JNDI_NAME);
			BIntegradaSessionLocal local = home.create();

			String listaActATST = local.listaActividadesATST(idRol);

			if ((listaActATST!=null)&&(listaActATST.trim()!="")&&(listaActATST.length()>0))
			   rolEsDeATST=true;

		} catch (Exception e) {
			e.printStackTrace();
		} 
		return rolEsDeATST;
	}
	
	protected HashMap getFiltrosVariables(HashMap filtros) {
		
		HashMap fv = new HashMap();
		HashMap salida = new HashMap();
		
		if(filtros.containsKey("FILTROS_VARIABLES")){
			
			fv = (HashMap)filtros.get("FILTROS_VARIABLES");		
			for (Iterator it = fv.keySet().iterator(); it.hasNext(); ) {
				String k = (String) it.next();
				String v = (String) fv.get(k);
				if (v != null && v.trim().length() > 0)
					salida.put(k, v);
			}			
		}
		
		
		
		return salida;
	}

	protected Date parseFecha(String fecha) {
		try {
			int sinMs = fecha.lastIndexOf(".");
			return formatoFecha.parse(fecha.substring(0,sinMs));
		} catch (Exception e) {
			log.warn("Problemas parseando fecha " + fecha + ". Default: Fecha Actual", e);
			return new Date();
		}
	}
}
