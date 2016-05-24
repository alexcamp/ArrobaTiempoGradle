package com.telefonica_chile.bandeja.bintegrada.session;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

import org.apache.log4j.Logger;

import com.tecnonautica.utiles.db.DBManager;
import com.tecnonautica.utiles.tablas.Tabla;
import com.tecnonautica.utiles.tablas.TablaException;
import com.telefonica_chile.atiempo.trace.api.TraceMainConfiguration;
import com.telefonica_chile.atiempo.trace.api.TraceManager;
import com.telefonica_chile.atiempo.trace.api.TraceOperation;
import com.telefonica_chile.atiempo.trace.atiempo.TraceAdapter;
import com.telefonica_chile.atiempo.utiles.Fecha;
import com.telefonica_chile.atiempo.utiles.LoggerFactory;
import com.telefonica_chile.bandeja.dto.PeticionDTO;
import com.telefonica_chile.bandeja.semaforos.Alertas;
import com.telefonica_chile.bandeja.semaforos.AlertasException;

public class TablaBandeja extends Tabla {

	
	public TablaBandeja(int paginaActual, int orden)
	{
		super(paginaActual);
		super.orden=orden;
		//log.debug("Creando  TablaBandeja TTTTTTTTTTTT 15:13 ################# AAA");
		
	}

	/**
	 * Obtencion de logger.
	 * @author amartoq
	 */
	protected transient Logger log = LoggerFactory.getLogger(getClass());

	private static SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	

	public TablaBandeja(int paginaActual) {
		super(paginaActual);
		//log.debug("Creando  TablaBandeja TTTTTTTTTTTT 15:13 ################# BBB");
	}

	public TablaBandeja()
	{
		super();
		//log.debug("Creando  TablaBandeja TTTTTTTTTTTT 15:13 ################# CCC");
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

	// CR17800 - inicio
	public long getNumeroTotalElementos(HashMap filtro,ArrayList tipoParam,ArrayList valorParam) {

		int tot = getCantidadElementos(filtro,tipoParam,valorParam);
		long aux = (long) tot;

		return aux;
	}

	public int getCantidadElementos(HashMap filtro,ArrayList tipoParam,ArrayList valorParam)
	{
	//Inicio @Trace
		TraceMainConfiguration traceConf = TraceMainConfiguration.getInstance();
		TraceManager traceManager = traceConf.getManager();
		TraceOperation traceOp = traceManager.newOperation(TraceAdapter.getAccion(TablaBandeja.class,"count"));
		int cantElementos = 0;
		try{
		
		traceOp.init(log);		
		traceManager.traceOpStart(traceOp);
	   // Fin @Trace
		String sqlWhereFijo = getWhereFijo(filtro, tipoParam, valorParam);
		//CR11267
		String sql = "" +" select count( b.BI_ID ) AS TOTAL"
								// CR17800 - guido - 17-Dic-2008 - se quita "select *" para el count, quedan los campos utilizados en filtros con el alias "B."
							   //+" from (select * "
							   +" from (select BI_NRO_PETICION, AP_ID, BI_ID, BI_FECHA_COMPROMISO "
							   +"from ATIEMPO.BINTEGRADA "
							   +"where bi_visible=1 "
									 + getAplicacionID(filtro)
									 + sqlWhereFijo 			  
							  +") B "
						+ getAgendamiento(filtro)
						+ getTipoIncidencia(filtro,tipoParam,valorParam)
						+ getPrioridadCategoria(filtro,tipoParam,valorParam)
						+ getAgendamientoWhere(filtro)
					//CR-11458
					  + getGruposEspecialesWhere(filtro,tipoParam,valorParam)
					  //fin
						//+" where B.bi_visible=1 " +
						//sqlWhereFijo ;
//						CR16847 pcawen
					  	+ getCanalWhere(filtro, tipoParam, valorParam)
						//CR23110 - PCawen - Filtro por regla
						+ getReglaWhere(filtro, tipoParam, valorParam);//TODO

		//DEBUG ver SQL en nuevo trace - log.debug("SQL: cuenta cantidad->"+sql);
		//fin
		DBManager db = new DBManager();
		db.setDataSource(DBManager.JDBC_BANDEJA);
		Hashtable salida = db.selectReadUncommitted(sql,tipoParam,valorParam); 
		String[] Total = (String[]) salida.get("TOTAL");
		if (Total == null || Total.length == 0)
			return 0;
		try
		{
			cantElementos = Integer.parseInt(Total[0]);
		}
		catch (Exception e)
		{
			cantElementos = 0;
		}
		traceOp.setColumn(TraceManager.COL_SQL, sql );
		traceOp.setColumn(TraceManager.COL_CANT_REGISTROS,String.valueOf(cantElementos) );
		}finally{
			traceManager.traceOpEnd(traceOp); //@Trace
		}

		return cantElementos;
	}
	// CR17800 - fin
	
	
	// CR17800 - inicio
	public String utilWhere(String campo, String valor, String tipo,ArrayList tipoParam, ArrayList valorParam)
	{
		if (valor == null || "".equals(valor))
			return "";

		if ("N".equals(tipo)) {
        	tipoParam.add("N"); // codigo para indicar que es un int
        	valorParam.add(valor);
        	return (" and " + campo + "= ?" );
		}

		if ("S".equals(tipo)) {
    		tipoParam.add("S"); // codigo para indicar que es un STRING
    		valorParam.add(valor);
    		return (" and " + campo + "= ?" );
		}	

		if ("LS".equals(tipo)) {
    		tipoParam.add("S"); // codigo para indicar que es un string
    		valorParam.add(valor);			
    		return (" and " + campo + " like (?)");    		
		}
		
		if ("FC".equals(tipo)) {
        	tipoParam.add("N"); // codigo para indicar que es un int
        	valorParam.add(valor);
			return (" and " + campo + "= ?" );			
		}	

		if ("IN".equals(tipo)) {
			//CR17800 - guido - 17-Dic-2008 - agregado de parametros para el IN
    		if (valor.indexOf(",") != -1) {
    			String[] valores = valor.split(",");
    			StringBuffer interogs = new StringBuffer(); 
    			for (int i = 0; i < valores.length; i++) {
    	       		tipoParam.add("S"); // codigo para indicar que es un string
    	       		interogs.append("?");
    	       		if (i != (valores.length-1)) {
        	       		interogs.append(", ");
    	       		}
    	    		valorParam.add(valores[i].trim());
				}
	    		return (" and " + campo + " in (" + interogs + ") ");
    			
    		} else {
	       		tipoParam.add("S"); // codigo para indicar que es un string
	    		valorParam.add(valor);
	    		return (" and " + campo + " in (?) ");
    		}
		}	

		return "";
	}
	// CR17800 - inicio	
	public String getWhereFijo(HashMap filtro,ArrayList tipoParam,ArrayList valorParam) {
		String whereFijo = "";
		Long idRol = (Long) filtro.get("idRol");
		String rolId;
		rolId = (idRol == null) ? "" : ("" + idRol);

		whereFijo += utilWhere("usua_id", (String) filtro.get("USUA_ID"), "N",tipoParam,valorParam);
		whereFijo += utilWhere("bi_tipo_trabajo", (String) filtro.get("BI_TIPO_TRABAJO"), "N",tipoParam,valorParam);
		whereFijo += utilWhere("SEGM_ID", (String) filtro.get("SEGM_ID"), "N",tipoParam,valorParam);
		whereFijo += utilWhere("agen_id", (String) filtro.get("AGEN_ID"), "N",tipoParam,valorParam);
		whereFijo += utilWhere("ap_id", (String) filtro.get("AP_ID"), "N",tipoParam,valorParam);
		
		whereFijo += utilWhere("bi_nro_peticion", (String) filtro.get("BI_NRO_PETICION"), "N",tipoParam,valorParam);
		whereFijo += utilWhere("act_id", (String) filtro.get("ACTIVIDAD_ID"), "N",tipoParam,valorParam);
		whereFijo += utilWhere("BI_NRO_PETICION_ATIS", (String) filtro.get("BI_NRO_PETICION_ATIS"), "N",tipoParam,valorParam);
		whereFijo += utilWhere("COD_LOC", (String) filtro.get("COD_LOC"), "S",tipoParam,valorParam);
		whereFijo += utilWhere("COD_DPT", (String) filtro.get("COD_DPT"), "S",tipoParam,valorParam);
		whereFijo += utilWhere("TICA_ID",(String)filtro.get("opcocat"),"S",tipoParam,valorParam);

		//whereFijo += utilWhere("A.rol_id", rolId, "N");
		//whereFijo += utilWhere("A.rol_id", (String) filtro.get("ROL_ID"), "N");
		whereFijo += utilWhere("rol_id", rolId, "N",tipoParam,valorParam);
		whereFijo += utilWhere("rol_id", (String) filtro.get("ROL_ID"), "N",tipoParam,valorParam);

		whereFijo += utilWhere("BI_FAMILIA_PS", (String) filtro.get("BI_FAMILIA_PS"), "S",tipoParam,valorParam);
		whereFijo += utilWhere("bi_cliente_rut", (String) filtro.get("BI_CLIENTE_RUT"), "S",tipoParam,valorParam);
		whereFijo += utilWhere("bi_cliente_rutdv", (String) filtro.get("BI_CLIENTE_RUTDV"), "S",tipoParam,valorParam);
		whereFijo += utilWhere("bi_cliente_area", (String) filtro.get("BI_CLIENTE_AREA"), "S",tipoParam,valorParam);
		whereFijo += utilWhere("bi_cliente_servicio", (String) filtro.get("BI_CLIENTE_SERVICIO"), "S",tipoParam,valorParam);

		whereFijo += utilWhere("days(B.bi_fecha_compromiso)-days(current timestamp)", (String) filtro.get("BI_FECHA_COMPROMISO"), "FC",tipoParam,valorParam);
		//log.debug("BI_ESTADO_PETICION 1--> " + filtro.get("BI_ESTADO_PETICION"));
		whereFijo += utilWhere("bi_estado_peticion", (String) filtro.get("BI_ESTADO_PETICION"), "IN",tipoParam,valorParam);
		whereFijo += utilWhere("bi_id", (String) filtro.get("LISTA_BI_ID"), "IN",tipoParam,valorParam);

		whereFijo += utilWhere("COD_CENTRAL", (String) filtro.get("CCN"), "N",tipoParam,valorParam);
		whereFijo += utilWhere("bi_pcom", (String) filtro.get("PCOM"), "S",tipoParam,valorParam);

		whereFijo += utilWhere("D.actc_id", (String) filtro.get("ACT_TC_ID"), "N",tipoParam,valorParam);

		return whereFijo;
	}
	// CR17800 - inicio
	public List getElementosPagina(HashMap filtro, ArrayList tipoParam, ArrayList valorParam) throws TablaException {
		//		Inicio @Trace
		TraceMainConfiguration traceConf = TraceMainConfiguration.getInstance();
		TraceManager traceManager = traceConf.getManager();
		TraceOperation traceOp = traceManager.newOperation(TraceAdapter.getAccion(TablaBandeja.class));
		traceOp.init(log);		
		traceManager.traceOpStart(traceOp);
			  // Fin @Trace
		List retorno = null;
		try {
			retorno = getElementosPaginaInterno(filtro,traceOp,tipoParam,valorParam);
		} finally {
			traceManager.traceOpEnd(traceOp);
		}
		return retorno;
	}
		
	// CR17800 - inicio		
	private List getElementosPaginaInterno(HashMap filtro, TraceOperation op, ArrayList tipoParam, ArrayList valorParam) throws TablaException {

		String sqlWhereFijo = getWhereFijo(filtro,tipoParam,valorParam);
		String sqlOrden		= getOrden(super.orden);
		//cr11267
		String sql =
			"select * from ( "
				+ "select B.bi_id, B.bi_cliente_nombre, B.bi_cliente_apellidos, B.bi_cliente_area, "
				+ "B.bi_cliente_servicio, B.bi_url_detalle, B.bi_fecha_inicio, bi_grupo_segmento, "
				+ "B.bi_fecha_apertura, B.bi_fecha_asignacion, B.bi_fecha_compromiso, B.bi_fecha_compromiso_sec, B.ap_id, "//CR17031 se agragó campo B.bi_fecha_compromiso_sec
				+ "B.bi_nro_peticion , B.usua_id,B.COD_LOC,B.BI_NRO_PETICION_ATIS,B.BI_AGRUPACIONES,B.DESC_LOCALIDAD,"
				+ "B.BI_FAMILIA_PS,B.DESC_CENTRAL,B.SEGM_DESCRIPCION,B.SUBSEGM_DESCRIPCION,B.BI_CLIENTE_RUT,B.BI_CLIENTE_RUTDV, "
				+ " A.rol_id, A.act_id, A.act_codigo, "
				+ "A.act_descripcion, A.act_nombre_reversa, "
				+ "B.bi_estado_peticion, B.bi_tipo_trabajo, "
				+ "B.bi_id_tipo_agendamiento, B.bi_id_rango, B.bi_hora_desde, B.bi_hora_hasta, "
//				CR17031 pcawen
				+ "B.BI_COD_CAT,"
				+ "row_number() over( order by " + sqlOrden + " ) as row  "
				//CR17800 - guido - 17-Dic-2008 - no vale la pena quitar el "select *" porque actualmente se proyectan 33 campos y la tabla tiene 40
				+ " from (select * "
						+"from ATIEMPO.BINTEGRADA "
						+"where bi_visible=1 "
							  + getAplicacionID(filtro)
				+ sqlWhereFijo 
							  +") B" 
				+ " inner join ATIEMPO.ACTIVIDAD A on B.act_id=A.act_id "
				+ getAgendamiento(filtro)
				+ getTipoIncidencia(filtro,tipoParam,valorParam)	
				+ getPrioridadCategoria(filtro,tipoParam,valorParam)
				//CR 11458
		        + getGruposEspecialesWhere(filtro,tipoParam,valorParam)
				//CR16847 pcawen				
				+ getCanalWhere(filtro, tipoParam, valorParam)
   				+ getAgendamientoWhere(filtro)			
				//CR23110 - PCawen - Filtro por regla
				+ getReglaWhere(filtro, tipoParam, valorParam)//TODO
				+ " ) subtabla where row >= ? "
				+ " and row <= ? ";

		tipoParam.add("N"); // codigo para indicar que es un number
		valorParam.add(Integer.toString(getIdxInicial()));			
		
		tipoParam.add("N"); // codigo para indicar que es un number
		valorParam.add(Integer.toString(getIdxFinal()));					
				
		op.setColumn(TraceManager.COL_SQL,sql); //@Trace
		//fin
		DBManager db = new DBManager();
		db.setDataSource("jdbc/bandeja");
		Hashtable salida = db.selectReadUncommitted(sql,tipoParam,valorParam);
		//		Hashtable salida = db.selectReadUncommitted(sql.toString());

		String[] BI_ID = (String[]) salida.get("BI_ID");
		String[] BI_CLIENTE_NOMBRE = (String[]) salida.get("BI_CLIENTE_NOMBRE");
		String[] BI_CLIENTE_APELLIDOS = (String[]) salida.get("BI_CLIENTE_APELLIDOS");
		String[] BI_CLIENTE_AREA = (String[]) salida.get("BI_CLIENTE_AREA");
		String[] BI_CLIENTE_SERVICIO = (String[]) salida.get("BI_CLIENTE_SERVICIO");
		String[] BI_URL_DETALLE = (String[]) salida.get("BI_URL_DETALLE");
		String[] BI_FECHA_INICIO = (String[]) salida.get("BI_FECHA_INICIO");
		String[] BI_FECHA_APERTURA = (String[]) salida.get("BI_FECHA_APERTURA");
		String[] BI_FECHA_ASIGNACION = (String[]) salida.get("BI_FECHA_ASIGNACION");
		String[] BI_FECHA_COMPROMISO = (String[]) salida.get("BI_FECHA_COMPROMISO");
//		CR17031 pcawen
		String[] BI_FECHA_COMPROMISO_SEC = (String[]) salida.get("BI_FECHA_COMPROMISO_SEC");
		String[] BI_COD_CAT = (String[]) salida.get("BI_COD_CAT");
//		CR17031 - Fin
		String[] AP_ID = (String[]) salida.get("AP_ID");
		String[] BI_NRO_PETICION = (String[]) salida.get("BI_NRO_PETICION");
		String[] USUA_ID = (String[]) salida.get("USUA_ID");
		String[] ROL_ID = (String[]) salida.get("ROL_ID");
		String[] ACT_ID = (String[]) salida.get("ACT_ID");
		String[] ACT_CODIGO = (String[]) salida.get("ACT_CODIGO");
		String[] ACT_DESCRIPCION = (String[]) salida.get("ACT_DESCRIPCION");
		String[] ACT_NOMBRE_REVERSA = (String[]) salida.get("ACT_NOMBRE_REVERSA");
		String[] BI_ESTADO_PETICION = (String[]) salida.get("BI_ESTADO_PETICION");
		String[] BI_TIPO_TRABAJO = (String[]) salida.get("BI_TIPO_TRABAJO");
		String[] BI_GSEGMENTO = (String[]) salida.get("BI_GRUPO_SEGMENTO");

		// Para el Agendamiento
		String[] BI_ID_AGENDAMIENTO = (String[]) salida.get("BI_ID_TIPO_AGENDAMIENTO");
		String[] BI_IDRANGO = (String[]) salida.get("BI_ID_RANGO");
		String[] BI_HDESDE = (String[]) salida.get("BI_HORA_DESDE");
		String[] BI_HHASTA = (String[]) salida.get("BI_HORA_HASTA");
		
		String[] COD_LOC = (String[])salida.get("COD_LOC");
		String[] BI_NRO_PETICION_ATIS = (String[])salida.get("BI_NRO_PETICION_ATIS");
		String[] BI_AGRUPACIONES = (String[])salida.get("BI_AGRUPACIONES");
		String[] DESC_LOCALIDAD = (String[])salida.get("DESC_LOCALIDAD");
		String[] BI_FAMILIA_PS = (String[])salida.get("BI_FAMILIA_PS");
		String[] DESC_CENTRAL = (String[])salida.get("DESC_CENTRAL");
		String[] SEGM_DESCRIPCION= (String[])salida.get("SEGM_DESCRIPCION");
		String[] SUBSEGM_DESCRIPCION = (String[])salida.get("SUBSEGM_DESCRIPCION");
		String[] BI_CLIENTE_RUT=(String[])salida.get("BI_CLIENTE_RUT");
		String[] BI_CLIENTE_RUTDV=(String[])salida.get("BI_CLIENTE_RUTDV");  

		Alertas alerta;
		try
		{
			alerta = new Alertas();
		}
		catch (AlertasException e)
		{
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
			p.setRolId(ROL_ID[i]);

			p.setActividadId(ACT_ID[i]);
			p.setActividadCodigo(ACT_CODIGO[i]);
			p.setActividadDescripcion(ACT_DESCRIPCION[i]);
			p.setActividadNombreReversa(ACT_NOMBRE_REVERSA[i]);

			p.setIdBloqueSegmento((Long) cast(BI_GSEGMENTO[i], new Long(0), "LONG"));
			p.setEstadoPeticion((Integer) cast(BI_ESTADO_PETICION[i], new Integer(1), "INTEGER"));
			p.setTipoTrabajo((Integer) cast(BI_TIPO_TRABAJO[i], new Integer(1), "INTEGER"));

			//alerta.setRol(p.getRolCodigo());
			alerta.setRol(p.getRolId());
			alerta.setActividadSemaforo(p.getActividadCodigo());
			alerta.setApId(p.getApId().toString());

//			CR17031 pcawen
			if (BI_FECHA_COMPROMISO != null && BI_FECHA_COMPROMISO[i].trim().length() > 0)
			{
				Date fechaCompromiso = parseFecha(BI_FECHA_COMPROMISO[i]);
				Fecha fecha=new Fecha(fechaCompromiso);
				p.setBiFechaCompromiso(fechaCompromiso);
				alerta.setFechaFinal(p.getBiFechaCompromiso());
			}

			if (BI_FECHA_COMPROMISO_SEC != null && BI_FECHA_COMPROMISO_SEC[i].trim().length() > 0){
				//Obtengo Categoria
				if (BI_COD_CAT != null && BI_COD_CAT[i].trim().length() > 0)
					alerta.setCodCat(BI_COD_CAT[i]);
				else
					alerta.setCodCat("");
				
				Date fechaInicio = parseFecha(BI_FECHA_INICIO[i]);
				Date fechacompromisoSec = parseFecha(BI_FECHA_COMPROMISO_SEC[i]);
				alerta.setFechaInicio(fechaInicio);
				alerta.setFechaFinalSec(fechacompromisoSec);
			}
			
			p.setBiSemaforoCompromiso(alerta.getSemaforoCompromiso());
//			CR17031 - FIN

			if (BI_FECHA_ASIGNACION != null && BI_FECHA_ASIGNACION[i].trim().length() > 0) {
				Date fechaAsignacion = parseFecha(BI_FECHA_ASIGNACION[i]);
				p.setBiFechaAsignacion(fechaAsignacion);
				alerta.setFechaFinal(p.getBiFechaAsignacion());
				p.setBiSemaforoActividad(alerta.getSemaforoActividad());
			}

			if (BI_FECHA_INICIO != null && BI_FECHA_INICIO[i].trim().length() > 0) {
				p.setBiFechaInicio(parseFecha(BI_FECHA_INICIO[i]));
			}

			if (BI_FECHA_APERTURA != null && BI_FECHA_APERTURA[i].trim().length() > 0) {
				p.setBiFechaApertura(parseFecha(BI_FECHA_APERTURA[i]));
			}

			// Seteamos Valores Adicionales para elñ Agendamiento.
			if (BI_ID_AGENDAMIENTO != null && BI_ID_AGENDAMIENTO[i].trim().length() > 0)
				p.setIdTipoAgendamiento(new Long(BI_ID_AGENDAMIENTO[i]));
			if (BI_IDRANGO != null && BI_IDRANGO[i].trim().length() > 0)
				p.setIdRango(new Integer(BI_IDRANGO[i]));
			if (BI_HDESDE != null && BI_HDESDE[i].trim().length() > 0)
				p.setHoraDesde(BI_HDESDE[i]);
			if (BI_HHASTA != null && BI_HHASTA[i].trim().length() > 0)
				p.setHoraHasta(BI_HHASTA[i]);
			
			if(BI_NRO_PETICION_ATIS!=null && BI_NRO_PETICION_ATIS[i].trim().length() > 0)
				p.setCod_pet_cd(new Long(BI_NRO_PETICION_ATIS[i]));
	
			if(BI_AGRUPACIONES!=null && BI_AGRUPACIONES[i].trim().length() > 0)
				p.setAgrupaciones(BI_AGRUPACIONES[i]);
	
			if(DESC_LOCALIDAD!=null && DESC_LOCALIDAD[i].trim().length() > 0)
				p.setLocalidad(DESC_LOCALIDAD[i]);
			
			if(BI_FAMILIA_PS!=null && BI_FAMILIA_PS[i].trim().length() > 0)
				p.setBiFamiliaPs(BI_FAMILIA_PS[i]);
				
			if(DESC_CENTRAL!=null && DESC_CENTRAL[i].trim().length() > 0)
				p.setCentral(DESC_CENTRAL[i]);
				
			if(SEGM_DESCRIPCION!=null && SEGM_DESCRIPCION[i].trim().length()>0)
				p.setSegmentoDescripcion(SEGM_DESCRIPCION[i]);	
			
			if(SUBSEGM_DESCRIPCION!=null && SUBSEGM_DESCRIPCION[i].trim().length()>0)
				p.setSubSegmentoDescripcion(SUBSEGM_DESCRIPCION[i]);
				
			if(BI_CLIENTE_RUT!=null && BI_CLIENTE_RUT[i].trim().length()>0)
				p.setBiClienteRut(BI_CLIENTE_RUT[i]);
			
			if(BI_CLIENTE_RUTDV!=null && BI_CLIENTE_RUTDV[i].trim().length()>0)
				p.setBiClienteRutDv(BI_CLIENTE_RUTDV[i]);
			lista.add(p);
		}
		op.setColumn(TraceManager.COL_CANT_REGISTROS, String.valueOf(lista.size())); //@Trace		
		return lista;
	}
	
	// CR17800 - fin
	

	protected Object cast(String str, Object def, String obj) {
		if (str == null)
			return def;

		try {
			if ("LONG".equals(obj))
				return (new Long(str));
			if ("INTEGER".equals(obj))
				return (new Integer(str));
			if ("STRING".equals(obj))
				return (str);
		} catch (Exception e) {
			// No se pudo Trasnformar
		}

		return def;

	}

	protected Date parseFecha(String fecha) {
		try {
			int sinMs = fecha.lastIndexOf(".");
			return formatoFecha.parse(fecha.substring(0, sinMs));
		} catch (Exception e) {
			log.debug("Problemas parseando fecha " + fecha + ". Default: Fecha Actual", e);
			return new Date();
		}
	}
	
	//CR11267 inicio
	
	// agendamiento
	private String getAgendamiento(HashMap filtro){
		String sqlAgendamiento = "";	
		try{
			String filtroAgendamiento = (String)filtro.get("estadoAgendamiento");
			// Defecto 14358 - NullPointer desde bandeja supervisor, agrego protección - guido - 14-Jun - Inicio
			if (filtroAgendamiento == null) {
				filtroAgendamiento = "";
			}
			// FIN Defecto 14358
			String condicionNull = "";
			if (!"".equals(filtroAgendamiento)){
				sqlAgendamiento = " left join AGENDA.TECNICO_PETICION tp on (1,B.ap_id,B.BI_nro_peticion)=(tp.estado,tp.ap_id,tp.peti_numero) ";
			}
		}catch (Exception e) {
			log.debug("CR-11267 Problemas por estado de agendamiento --> en el JOIN", e);
			sqlAgendamiento = "";
		}
		return (sqlAgendamiento);
	}
	
	// en el where afuera de la consulta, agrego tambien el AP_ID que viene del filtro
	private String getAgendamientoWhere(HashMap filtro){
		String sqlAgendamientoWhere = "";
		String sqlAplicacionIDWhere = "";	
		try{
			String filtroAgendamiento = (String)filtro.get("estadoAgendamiento");
			// Defecto 14358 - NullPointer desde bandeja supervisor, agrego protección - guido - 14-Jun - Inicio
			if (filtroAgendamiento == null) {
				filtroAgendamiento = "";
			}
			// FIN Defecto 14358
			
			String filtroAplicacionID = (String)filtro.get("AP_ID");
			
			if (!"".equals(filtroAgendamiento)){
				if (filtroAgendamiento.equals("agendado")) {sqlAgendamientoWhere = " where tepe_id is not null ";
				} else if (filtroAgendamiento.equals("noAgendado")) {sqlAgendamientoWhere = " where tepe_id is null ";
				} else throw new Exception();
			}		
		}catch (Exception e) {
			log.debug("CR-11267 Problemas por estado de agendamiento --> en el where", e);
			sqlAgendamientoWhere = "";
		}
		return (sqlAgendamientoWhere);
	}
	// CR17800 - inicio
	private String getGruposEspecialesWhere(HashMap filtro,ArrayList tipoParam,ArrayList valorParam){
		String sqlGruposEspecialesWhere = "";			
		try{
		String filtroGrupo = (String) filtro.get("GRUPOE_ID");		
		if (filtroGrupo == null) {
			filtroGrupo = "";
		}
		String ap_id = (String) filtro.get("AP_ID");		
		if (!"".equals(filtroGrupo) && ap_id.equals("3")){
			sqlGruposEspecialesWhere = " inner join VPISTBBA.PRODUCTO_SERVICIO_PETICION psp on (B.BI_nro_peticion)=(psp.peti_numero) inner join ATIEMPO.GRPE_PS grp  on grp.PS_ID = psp.ps_id AND grp.GRPE_ID = ? ";
			tipoParam.add("N"); // codigo para indicar que es un number
    		valorParam.add(filtroGrupo);										
		}else if(!"".equals(filtroGrupo) && ap_id.equals("2")){
			sqlGruposEspecialesWhere = " inner join SOLTEC.PETICION_ST pst on (B.BI_nro_peticion)=(pst.cod_ave_cd) inner join ATIEMPO.GRPE_PS grp  on grp.PS_ID = pst.COD_PRO_SER_CD AND grp.GRPE_ID = ? ";
			tipoParam.add("N"); // codigo para indicar que es un STRING
    		valorParam.add(filtroGrupo);											
		}else if(!"".equals(filtroGrupo)){ 
			sqlGruposEspecialesWhere = " left join SOLTEC.PETICION_ST pst on (B.BI_nro_peticion)=(pst.cod_ave_cd) and b.ap_id=2 left join VPISTBBA.PRODUCTO_SERVICIO_PETICION pspVpi on (B.BI_nro_peticion)=(pspVpi.peti_numero) and b.ap_id=3 inner join ATIEMPO.GRPE_PS grp  on (grp.PS_ID=pst.COD_PRO_SER_CD or grp.PS_ID=pspVpi.ps_id) AND grp.GRPE_ID = ? ";
			tipoParam.add("N"); // codigo para indicar que es un STRING
    		valorParam.add(filtroGrupo);											
		}
	}catch (Exception e) {
		log.debug("CR-11458 Problemas por estado de grupo --> en el JOIN", e);
		sqlGruposEspecialesWhere = "";
	}
	return (sqlGruposEspecialesWhere);
	}	
	// CR17800 - fin
	
	

	//	CR16847 pcawen
//	 CR17800 - inicio
	private String getCanalWhere(HashMap filtro, ArrayList tipoParam, ArrayList valorParam){
		String sqlCanalWhere = "";
		String filtroCanal = (String)filtro.get("canal_id");
		if(filtroCanal == null)
			filtroCanal = "";
		if(!"".equals(filtroCanal)) {
			sqlCanalWhere = " inner join VPISTBBA.PETICION p on( p.cod_cnl_ven_cd  = ? and B.bi_nro_peticion = p.peti_numero) ";
			tipoParam.add("S"); // codigo para indicar que es un STRING
			valorParam.add(filtroCanal);							
		}			
		return sqlCanalWhere;
	}
//	 CR17800 - fin
//	CR16847 - FIN
	
//	CR23110 - PCawen - Filtro por regla
	private String getReglaWhere(HashMap filtro, ArrayList tipoParam, ArrayList valorParam){//TODO
		String sqlReglaWhere = "";
		String filtroRegla = (String)filtro.get("regla_id");
		if(filtroRegla == null)
			filtroRegla = "";
		String ap_id = (String) filtro.get("AP_ID");
		if(!"".equals(filtroRegla) && ap_id.equals("3")) {
			sqlReglaWhere = " inner join VPISTBBA.PETICION p on (p.regla_id = ? and p.peti_numero = B.bi_nro_peticion) ";
			tipoParam.add("S"); // codigo para indicar que es un STRING
			valorParam.add(filtroRegla);							
		}else if(!"".equals(filtroRegla) && ap_id.equals("2")){
			sqlReglaWhere = " inner join SOLTEC.PETICION_ST pst on (pst.regla_id = ? and pst.cod_ave_cd = B.bi_nro_peticion) ";
			tipoParam.add("S"); // codigo para indicar que es un STRING
			valorParam.add(filtroRegla);
		}else if(!"".equals(filtroRegla)){
			sqlReglaWhere = " left join VPISTBBA.PETICION p on p.peti_numero = B.bi_nro_peticion left join SOLTEC.PETICION_ST pst on pst.cod_ave_cd = B.bi_nro_peticion inner join ATIEMPO.REGLA_RETENCIONES R on ((R.regla_id = p.regla_id or R.regla_id = pst.regla_id) and R.regla_id = ?) ";
			tipoParam.add("S"); // codigo para indicar que es un STRING
			valorParam.add(filtroRegla);
		}
		return sqlReglaWhere;
	}
	
	// CR17800 - inicio
	private String getTipoIncidencia(HashMap filtro,ArrayList tipoParam,ArrayList valorParam){
		String filtroTipoIncidencia = (String)filtro.get("tipoIncidencia");
		// Defecto 14358 - NullPointer desde bandeja supervisor, agrego protección - guido - 14-Jun - Inicio
		if (filtroTipoIncidencia == null) {
			filtroTipoIncidencia = "";
		}
		// FIN Defecto 14358
		String sqlTipoIncidencia  = "";
		try{
			if (!"".equals(filtroTipoIncidencia)){
				//AT-1469 - agonz - se modifico la consulta "like" por "=" 
				//CR17800 - guido - inicio - eliminacion de sub-queries a prueba_linea, por distinct
//				sqlTipoIncidencia = " inner join (SELECT pl.cod_ave_cd " 
//											   +"FROM soltec.catalogo_prueba_linea cpl, soltec.prueba_linea pl "
//											   +"WHERE cpl.tipo_incidencia = ? "
//											   +" and cpl.faps_id = pl.faps_id and cpl.cod_prueba = pl.cod_prueba and pl.id_prueba in (select max(id_prueba) " 
//											   																						  +"from soltec.prueba_linea pl " 
//											   																						  +"group by cod_ave_cd)) tipo_inci"
//								    + " on tipo_inci.cod_ave_cd = B.bi_nro_peticion ";
				sqlTipoIncidencia = " inner join (SELECT distinct pl.cod_ave_cd " 
					   +" FROM soltec.catalogo_prueba_linea cpl, soltec.prueba_linea pl "
					   +" WHERE cpl.tipo_incidencia = ? "
					   +" and cpl.faps_id = pl.faps_id " +
					   	" and cpl.cod_prueba = pl.cod_prueba" +
		    " ) tipo_inci on tipo_inci.cod_ave_cd = B.bi_nro_peticion ";
	    		tipoParam.add("S"); // codigo para indicar que es un STRING
	    		valorParam.add(filtroTipoIncidencia);				
//	    		CR17800 - guido - Fin				
			};
			//String a ="AP_ID = 2 and BI_NRO_PETICION in ";
		}catch(Exception e){
			log.debug("CR-11267 Problemas con el filtrado por tipo de incidencia", e);
		}
		return sqlTipoIncidencia;
	}	

	
	// CR17800 - inicio	
	private String getPrioridadCategoria(HashMap filtro,ArrayList tipoParam,ArrayList valorParam){
		String sqlPrioridadCategoria  = "";
		try{
			String filtroPrioridadAveria= (String)filtro.get("prioridadAveria");
			String filtroCategoriaAveria = (String)filtro.get("categoriaAveria");
			// Defecto 14358 - NullPointer desde bandeja supervisor, agrego protección - guido - 14-Jun - Inicio
			if (filtroPrioridadAveria == null) {
				filtroPrioridadAveria = "";
			}
			if (filtroCategoriaAveria == null) {
				filtroCategoriaAveria = "";
			}
			// FIN Defecto 14358
			
			if(!"".equals(filtroPrioridadAveria) || !"".equals(filtroCategoriaAveria)){
				sqlPrioridadCategoria = " inner join soltec.peticion_st pst1 on pst1.cod_ave_cd = b.bi_nro_peticion ";
				if (!"".equals(filtroPrioridadAveria)) { 
					sqlPrioridadCategoria = sqlPrioridadCategoria +  " and pst1.COD_PRA_AVE_CD=? ";
					tipoParam.add("S"); // codigo para indicar que es un STRING
		    		valorParam.add(filtroPrioridadAveria.trim());									
				}	
				if (!"".equals(filtroCategoriaAveria)) { 
					sqlPrioridadCategoria = sqlPrioridadCategoria +  " and pst1.COD_CTZ_CD=? ";
					tipoParam.add("S"); // codigo para indicar que es un STRING
		    		valorParam.add(filtroCategoriaAveria.trim());									
				}	
				
			}
		}catch (Exception e) {
			log.debug("CR-11267 Problemas con el filtrado Categoria  o Prioridad de Averia", e);
			sqlPrioridadCategoria  = "";
		}		
		return sqlPrioridadCategoria;
	}
	// CR17800 - fin
	
	private String getAplicacionID(HashMap filtro){
		String filtroTipoIncidencia = (String)filtro.get("tipoIncidencia");
		// Defecto 14358 - NullPointer desde bandeja supervisor, agrego protección - guido - 14-Jun - Inicio
		if (filtroTipoIncidencia == null) {
			filtroTipoIncidencia = "";
		}
		String filtroPrioridadAveria= (String)filtro.get("prioridadAveria");
		if (filtroPrioridadAveria == null) {
			filtroPrioridadAveria = "";
		}
		String filtroCategoriaAveria = (String)filtro.get("categoriaAveria");
		if (filtroCategoriaAveria == null) {
			filtroCategoriaAveria = "";
		}
		// FIN Defecto 14358
		String ap_id = "";
		try{
			if ( !"".equals(filtroTipoIncidencia) || !"".equals(filtroPrioridadAveria) || !"".equals(filtroCategoriaAveria)) ap_id = " and ap_id = 2";
		}catch(Exception e){
			log.debug("CR-11267 Problemas al agregar el AP_ID", e);	
		}
		return ap_id;
		
	}
		
	// CR17800 - adocarmo/guido - inicio - se sobre-escribe retrieve para poner parametros en el prepared statement
	public void retrieve(HashMap filtro) throws TablaException {
		ArrayList tipoParams = new ArrayList(8);
		ArrayList valorParams = new ArrayList(8);
		long nroElementos = getNumeroTotalElementos(filtro, tipoParams, valorParams);


		idxInicial = 1 + (paginaActual - 1) * largoPagina;
		idxFinal = paginaActual * largoPagina;
		this.totalPaginas = (int)Math.ceil(nroElementos / (float) largoPagina);

		if (nroElementos > 0) {
			tipoParams.clear();
			valorParams.clear();
			this.elementos = getElementosPagina(filtro,tipoParams, valorParams);
		}

		nroTotalElementos = (int) nroElementos;
	}
	// CR17800 - adocarmo - fin	

	protected long getNumeroTotalElementos(HashMap filtro) throws TablaException {
		//No soportado luego de CR17800 - guido - 16-Dic-2008
		throw new UnsupportedOperationException("No soportado luego de CR17800 de pasaje de parametros");
	}

	protected List getElementosPagina(HashMap filtro) throws TablaException {
		//No soportado luego de CR17800 - guido - 16-Dic-2008
		throw new UnsupportedOperationException("No soportado luego de CR17800 de pasaje de parametros");
	}

	
}