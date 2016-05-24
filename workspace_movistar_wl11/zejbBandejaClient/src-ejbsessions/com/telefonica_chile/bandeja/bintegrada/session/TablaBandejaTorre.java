package com.telefonica_chile.bandeja.bintegrada.session;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.tecnonautica.utiles.db.DBManager;
import com.tecnonautica.utiles.tablas.Tabla;
import com.tecnonautica.utiles.tablas.TablaException;
import com.telefonica_chile.atiempo.utiles.Fecha;
import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.atiempo.utiles.LoggerFactory;
import com.telefonica_chile.bandeja.dto.PeticionDTO;
import com.telefonica_chile.bandeja.semaforos.Alertas;
import com.telefonica_chile.bandeja.semaforos.AlertasException;

public class TablaBandejaTorre extends Tabla {


	public TablaBandejaTorre(int paginaActual, int orden)
	{
		super(paginaActual);
		super.orden=orden;
	}

	protected transient Logger log = LoggerFactory.getLogger(getClass());

	private static SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	

	public TablaBandejaTorre(int paginaActual) {
		super(paginaActual);
	}

	public TablaBandejaTorre()
	{
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

		return aux;
	}


	/*
	 * Retorna la Cantidad Total de Elementos
	 * que habria en la Bandeja para los Filtros dados.
	 */
	public int getCantidadElementos(HashMap filtro)
	{
		int cantElementos = 0;

		String sqlWhereFijo = getWhereFijo(filtro);

		String sql = "" + "select count( b.act_id ) AS TOTAL " +
	"from ATIEMPO.BINTEGRADA B, ATIEMPO.ACTIVIDAD A where B.act_id=A.act_id and B.bi_visible=1 " +
			// Hay que agregar los Filtros.
	sqlWhereFijo ;

		//log.debug(sql);

		DBManager db = new DBManager();
		db.setDataSource(DBManager.JDBC_BANDEJA);
		Hashtable salida = db.selectReadUncommitted(sql);
		//		Hashtable salida =  db.selectReadUncommitted( sql );

		String[] Total = (String[]) salida.get("TOTAL");
		if (Total == null || Total.length == 0)
			return 0;
		try {
			cantElementos = Integer.parseInt(Total[0]);
		} catch (Exception e) {
			cantElementos = 0;
		}

		return cantElementos;
	}

	public String utilWhere(String campo, String valor, String tipo) {

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

		return "";
	}

	public String getWhereFijo(HashMap filtro) {
		String whereFijo = "";

		whereFijo += utilWhere("B.bi_tipo_trabajo", (String) filtro.get("BI_TIPO_TRABAJO"), "N");
		whereFijo += utilWhere("B.SEGM_ID", (String) filtro.get("SEGM_ID"), "N");
		whereFijo += utilWhere("B.agen_id", (String) filtro.get("AGEN_ID"), "N");
		whereFijo += utilWhere("B.ap_id", (String) filtro.get("AP_ID"), "N");
		whereFijo += utilWhere("B.bi_nro_peticion", (String) filtro.get("BI_NRO_PETICION"), "N");
		whereFijo += utilWhere("B.act_id", (String) filtro.get("ACTIVIDAD_ID"), "N");
		whereFijo += utilWhere("B.BI_NRO_PETICION_ATIS", (String) filtro.get("BI_NRO_PETICION_ATIS"), "N");
		whereFijo += utilWhere("B.COD_LOC", (String) filtro.get("COD_LOC"), "S");
		whereFijo += utilWhere("B.COD_DPT", (String) filtro.get("COD_DPT"), "S");
		whereFijo += utilWhere("B.TICA_ID",(String)filtro.get("opcocat"),"S");
		String codActividaTorre=(String)filtro.get("fTC_Actividad");
		whereFijo+=" and  B.ACT_ID in ( select dtc.ACT_ID from atiempo.DETALLE_ACTIVIDAD_TC" +
		" dtc where dtc.ACTC_ID= "+codActividaTorre+" )  ";

		whereFijo += utilWhere("B.rol_id", (String) filtro.get("ROL_ID"), "N");

		whereFijo += utilWhere("B.BI_FAMILIA_PS", (String) filtro.get("BI_FAMILIA_PS"), "S");
		whereFijo += utilWhere("B.bi_cliente_rut", (String) filtro.get("BI_CLIENTE_RUT"), "S");
		whereFijo += utilWhere("B.bi_cliente_rutdv", (String) filtro.get("BI_CLIENTE_RUTDV"), "S");
		whereFijo += utilWhere("B.bi_cliente_area", (String) filtro.get("BI_CLIENTE_AREA"), "S");
		whereFijo += utilWhere("B.bi_cliente_servicio", (String) filtro.get("BI_CLIENTE_SERVICIO"), "S");

		whereFijo += utilWhere("days(B.bi_fecha_compromiso)-days(current timestamp)", (String) filtro.get("BI_FECHA_COMPROMISO"), "FC");
		
		whereFijo += utilWhere("B.bi_estado_peticion", (String) filtro.get("BI_ESTADO_PETICION"), "IN");
		whereFijo += utilWhere("B.bi_id", (String) filtro.get("LISTA_BI_ID"), "IN");

		whereFijo += utilWhere("B.COD_CENTRAL", (String) filtro.get("CCN"), "N");
		whereFijo += utilWhere("B.bi_pcom", (String) filtro.get("PCOM"), "S");

		whereFijo += utilWhere("D.actc_id", (String) filtro.get("ACT_TC_ID"), "N");

		return whereFijo;
	}


	/*
	 * Retorna las Tuplas de la Bandeja Integrada.
	 */
	public List getElementosPagina(HashMap filtro) throws TablaException {

		String sqlWhereFijo = getWhereFijo(filtro);
		String sqlOrden		= getOrden(super.orden);
		
		String sql =
			"select * from ( "
				+ "select B.bi_id, B.bi_cliente_nombre, B.bi_cliente_apellidos, B.bi_cliente_area, "
				+ "B.bi_cliente_servicio, B.bi_url_detalle, B.bi_fecha_inicio, bi_grupo_segmento, "
				+ "B.bi_fecha_apertura, B.bi_fecha_asignacion, B.bi_fecha_compromiso, B.ap_id, "
				+ "B.bi_nro_peticion , B.usua_id,B.COD_LOC,B.BI_NRO_PETICION_ATIS,B.BI_AGRUPACIONES,B.DESC_LOCALIDAD,B.BI_FAMILIA_PS,B.DESC_CENTRAL,B.SEGM_DESCRIPCION,B.SUBSEGM_DESCRIPCION,B.BI_CLIENTE_RUT,B.BI_CLIENTE_RUTDV, "
				+ " A.rol_id, A.act_id, A.act_codigo, "
				+ "A.act_descripcion, A.act_nombre_reversa, "
				+ "B.bi_estado_peticion, B.bi_tipo_trabajo, "
				+ "B.bi_id_tipo_agendamiento, B.bi_id_rango, B.bi_hora_desde, B.bi_hora_hasta, "
				+ "row_number() over( order by " + sqlOrden + " ) as row  "
				+ "from ATIEMPO.BINTEGRADA B, ATIEMPO.ACTIVIDAD A "
				+ "where B.act_id=A.act_id and B.bi_visible=1 "
				+ sqlWhereFijo 
				+ " ) subtabla where row >= "
				+ getIdxInicial()
				+ " and row <= "
				+ getIdxFinal()
				+ " "  ;
				
				//log.debug("SQL->"+sql);

		DBManager db = new DBManager();
		db.setDataSource(DBManager.JDBC_BANDEJA);
		Hashtable salida = db.selectReadUncommitted(sql);

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

			if (BI_FECHA_COMPROMISO != null && BI_FECHA_COMPROMISO[i].trim().length() > 0)
			{
				Date fechaCompromiso = parseFecha(BI_FECHA_COMPROMISO[i]);
				Fecha fecha=new Fecha(fechaCompromiso);
				p.setBiFechaCompromiso(fechaCompromiso);
				alerta.setFechaFinal(p.getBiFechaCompromiso());
				p.setBiSemaforoCompromiso(alerta.getSemaforoCompromiso());
			}

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
		return lista;
	}

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
	// Funcion que determina si el rol pertenece o no a la aplicacion ATST
	protected boolean esATST(Long idRol) {

		boolean rolEsDeATST = false;

		//Procedemos a invocar el Session de BIntegrada
		try {
			BIntegradaSessionLocalHome home = (BIntegradaSessionLocalHome) HomeFactory.getHome(BIntegradaSessionLocalHome.JNDI_NAME);
			BIntegradaSessionLocal local = home.create();

			String listaActATST = local.listaActividadesATST(idRol);

			if ((listaActATST != null) && (listaActATST.trim() != "") && (listaActATST.length() > 0))
				rolEsDeATST = true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return rolEsDeATST;
	}

	protected HashMap getFiltrosVariables(HashMap filtros) {

		HashMap fv = new HashMap();
		HashMap salida = new HashMap();

		if (filtros.containsKey("FILTROS_VARIABLES")) {

			fv = (HashMap) filtros.get("FILTROS_VARIABLES");
			for (Iterator it = fv.keySet().iterator(); it.hasNext();) {
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
			return formatoFecha.parse(fecha.substring(0, sinMs));
		} catch (Exception e) {
			log.debug("Problemas parseando fecha " + fecha + ". Default: Fecha Actual", e);
			return new Date();
		}
	}

	/*
	 * Entrega todo el Listado de Bandeja Integrada para Generar el Excel.
	 */
	public ArrayList getListadoBIntegrada(HashMap filtro)
	{
		String sqlWhereFijo = getWhereFijo(filtro);

		String sql =
			"select B.bi_id, B.bi_cliente_nombre, B.bi_cliente_apellidos, B.bi_cliente_area, "
				+ " B.bi_cliente_servicio, B.bi_fecha_inicio, B.bi_grupo_segmento, "
				+ " B.bi_fecha_apertura, B.bi_fecha_compromiso, B.bi_nro_peticion, B.act_id, "
				+ " B.bi_id_tipo_agendamiento, B.bi_id_rango, B.bi_hora_desde, B.bi_hora_hasta,B.COD_LOC,B.BI_NRO_PETICION_ATIS,B.BI_AGRUPACIONES,B.DESC_LOCALIDAD,B.BI_FAMILIA_PS,B.DESC_CENTRAL,B.SEGM_DESCRIPCION,B.SUBSEGM_DESCRIPCION,B.BI_CLIENTE_RUT,B.BI_CLIENTE_RUTDV "
				+ " from ATIEMPO.BINTEGRADA B  "
				+ "where B.bi_visible=1 "
				+ sqlWhereFijo
				+ " order by B.bi_grupo_segmento, B.bi_fecha_compromiso, B.bi_tipo_trabajo";
		//log.debug(sql);
		DBManager db = new DBManager();
		db.setDataSource(DBManager.JDBC_BANDEJA);
		Hashtable salida = db.selectReadUncommitted(sql);

		String[] BI_ID = (String[]) salida.get("BI_ID");
		String[] BI_CLIENTE_NOMBRE = (String[]) salida.get("BI_CLIENTE_NOMBRE");
		String[] BI_CLIENTE_APELLIDOS = (String[]) salida.get("BI_CLIENTE_APELLIDOS");
		String[] BI_CLIENTE_AREA = (String[]) salida.get("BI_CLIENTE_AREA");
		String[] BI_CLIENTE_SERVICIO = (String[]) salida.get("BI_CLIENTE_SERVICIO");
		String[] BI_FECHA_INICIO = (String[]) salida.get("BI_FECHA_INICIO");
		String[] BI_FECHA_APERTURA = (String[]) salida.get("BI_FECHA_APERTURA");
		String[] BI_FECHA_COMPROMISO = (String[]) salida.get("BI_FECHA_COMPROMISO");
		String[] BI_NRO_PETICION = (String[]) salida.get("BI_NRO_PETICION");
		String[] ACT_ID = (String[]) salida.get("ACT_ID");
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
		String[] BI_FAMILIA_PS=(String[])salida.get("BI_FAMILIA_PS");
		String[] DESC_CENTRAL=(String[])salida.get("DESC_CENTRAL");
		String[] SEGM_DESCRIPCION=(String[])salida.get("SEGM_DESCRIPCION");
		String[] SUBSEGM_DESCRIPCION=(String[])salida.get("SUBSEGM_DESCRIPCION");
		String[] BI_CLIENTE_RUT=(String[])salida.get("BI_CLIENTE_RUT");
		String[] BI_CLIENTE_RUTDV=(String[])salida.get("BI_CLIENTE_RUTDV");
		
		ArrayList lista = new ArrayList();
		for (int i = 0; BI_ID != null && i < BI_ID.length; i++) {
			
			PeticionDTO p = new PeticionDTO();
			p.setBiId(new Long(BI_ID[i]));
			p.setBiClienteNombre(BI_CLIENTE_NOMBRE[i]);
			p.setBiClienteApellidos(BI_CLIENTE_APELLIDOS[i]);
			p.setBiClienteArea(BI_CLIENTE_AREA[i]);
			p.setBiClienteServicio(BI_CLIENTE_SERVICIO[i]);
			p.setBiNroPeticion(new Long(BI_NRO_PETICION[i]));
			
			p.setActividadId(ACT_ID[i]);

			p.setIdBloqueSegmento((Long) cast(BI_GSEGMENTO[i], new Long(0), "LONG"));

			if (BI_FECHA_COMPROMISO != null && BI_FECHA_COMPROMISO[i].trim().length() > 0) {
				Date fechaCompromiso = parseFecha(BI_FECHA_COMPROMISO[i]);
				p.setBiFechaCompromiso(fechaCompromiso);
			}

			if (BI_FECHA_INICIO != null && BI_FECHA_INICIO[i].trim().length() > 0) {
				p.setBiFechaInicio(parseFecha(BI_FECHA_INICIO[i]));
			}

			if (BI_FECHA_APERTURA != null && BI_FECHA_APERTURA[i].trim().length() > 0) {
				p.setBiFechaApertura(parseFecha(BI_FECHA_APERTURA[i]));
			}

			// Seteamos Valores Adicionales para el Agendamiento.
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

		return lista;

	}
}