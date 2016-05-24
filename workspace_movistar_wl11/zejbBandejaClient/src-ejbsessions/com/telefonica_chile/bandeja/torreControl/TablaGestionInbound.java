package com.telefonica_chile.bandeja.torreControl;
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
import com.telefonica_chile.atiempo.utiles.LoggerFactory;
import com.telefonica_chile.atiempo.trace.api.TraceMainConfiguration;
import com.telefonica_chile.atiempo.trace.api.TraceManager;
import com.telefonica_chile.atiempo.trace.api.TraceOperation;
import com.telefonica_chile.atiempo.trace.atiempo.TraceAdapter;
import com.telefonica_chile.bandeja.dto.AgenciaDTO;
import com.telefonica_chile.bandeja.dto.PeticionDTO;

public class TablaGestionInbound extends Tabla
{	
	protected transient Logger log = LoggerFactory.getLogger(getClass());

	private static SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public TablaGestionInbound(int paginaActual)
	{
		super(paginaActual);		
	}

	public TablaGestionInbound() {
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

	public int getCantidadElementos(HashMap filtro)
	{
		return 1;
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

	public String getWhereFijo(HashMap filtro) {
		String whereFijo = "";
		Long idRol = (Long) filtro.get("idRol");
		String rolId;
		rolId = (idRol == null) ? "" : ( ""+idRol);

		whereFijo += utilWhere("B.usua_id", (String) filtro.get("USUA_ID"), "N");
		whereFijo += utilWhere("B.bi_tipo_trabajo", (String) filtro.get("BI_TIPO_TRABAJO"), "N");
		whereFijo += utilWhere("B.SEGM_ID", (String) filtro.get("SEGM_ID"), "N");
		whereFijo += utilWhere("B.bi_estado_peticion", (String) filtro.get("BI_ESTADO_PETICION"), "IN");
		whereFijo += utilWhere("B.agen_id", (String) filtro.get("AGEN_ID"), "N");
		whereFijo += utilWhere("B.ap_id", (String) filtro.get("AP_ID"), "N");
		whereFijo += utilWhere("B.bi_nro_peticion", (String) filtro.get("BI_NRO_PETICION"), "N");
		whereFijo += utilWhere("B.act_id", (String) filtro.get("ACTIVIDAD_ID"), "N");
		whereFijo += utilWhere("B.bi_familia_ps", (String) filtro.get("BI_FAMILIA_PS"), "S");
		whereFijo += utilWhere("B.bi_cliente_rut", (String) filtro.get("BI_CLIENTE_RUT"), "S");
		whereFijo += utilWhere("B.bi_cliente_rutdv", (String) filtro.get("BI_CLIENTE_RUTDV"), "S");
		whereFijo += utilWhere("B.bi_cliente_area", (String) filtro.get("BI_CLIENTE_AREA"), "S");
		whereFijo += utilWhere("B.bi_cliente_servicio", (String) filtro.get("BI_CLIENTE_SERVICIO"), "S");
		whereFijo += utilWhere("days(B.bi_fecha_compromiso)-days(current timestamp)", (String) filtro.get("BI_FECHA_COMPROMISO"), "FC");
		whereFijo += utilWhere("B.bi_estado_peticion", (String) filtro.get("BI_ESTADO_PETICION"), "IN");
		whereFijo += utilWhere("B.COD_CENTRAL", (String) filtro.get("CCN"), "N");
		whereFijo += utilWhere("B.TICA_ID",(String)filtro.get("opcocat"),"S");
		
		String codLoc=(String)filtro.get("COD_LOC");
		if(codLoc!=null && !codLoc.equals("00000000"))
			whereFijo += utilWhere("B.COD_LOC", (String) filtro.get("COD_LOC"), "S");
	
		String coddpt=(String) filtro.get("COD_DPT");
		if(coddpt!=null && !coddpt.equals("00"))
			whereFijo += utilWhere("B.COD_DPT", (String) filtro.get("COD_DPT"), "S");


		// Filtros de Torre de Control
		whereFijo += utilWhere("D.actc_id", (String) filtro.get("ACT_TC_ID"), "N");
		whereFijo += utilWhere("TC.actc_id", (String) filtro.get("ACTC_ID"), "N");
		whereFijo += utilWhere("TC.actc_cuadro", (String) filtro.get("ACTC_CUADRO"), "N");
		whereFijo += utilWhere("TC.ap_id", (String) filtro.get("ACTC_APID"), "N");

		return whereFijo;
	}

	/*
	 * Retorna los Datos para el Cuadro de Mando. 
	 */
	public List getElementosPagina(HashMap filtro) throws TablaException
	{
		//Inicio @Trace
		TraceMainConfiguration traceConf = TraceMainConfiguration.getInstance();		
		TraceManager traceManager = traceConf.getManager();		
		TraceOperation traceOp = traceManager.newOperation(TraceAdapter.getAccion(TablaGestionOutbound.class));
		List lista = null;
		try{
			  traceOp.init(log);
		      traceManager.traceOpStart(traceOp);
		      
		String apId = (String) filtro.get("AP_ID");
		filtro.remove("AP_ID");
		String sqlWhereFijo = getWhereFijo(filtro);
		
		String sql = 
			" select B.cod_dpt, B.act_id, B.ap_id, B.bi_visible, count(B.cod_dpt) AS TOTAL " +
			" from ATIEMPO.BINTEGRADA B " +
			" where b.act_id in ( select det.ACT_ID from atiempo.detalle_actividad_tc det where det.actc_id=4) " +
			sqlWhereFijo + " " +
			" group by B.cod_dpt, B.act_id, B.ap_id, B.bi_visible " ;

		//log.debug(sql);
		DBManager db = new DBManager();
		db.setDataSource(DBManager.JDBC_BANDEJA);
		Hashtable salida = db.selectReadUncommitted( sql );
		traceOp.setColumn(TraceManager.COL_SQL , sql );//@Trace
		String[] COD_DP = (String[]) salida.get("COD_DPT");
		String[] ACT_ID = (String[]) salida.get("ACT_ID");
		String[] VIS = (String[]) salida.get("BI_VISIBLE");
		String[] AP_ID = (String[]) salida.get("AP_ID");
		String[] TOTAL  = (String[]) salida.get("TOTAL");

		lista = new ArrayList();
		HashMap auxAG = new HashMap();
		AgenciaDTO agDto = null;
		boolean agExiste = true;
		traceOp.setColumn(TraceManager.COL_CANT_REGISTROS, String.valueOf(COD_DP.length)); //@Trace
		for (int i = 0; COD_DP != null && i < COD_DP.length; i++) {
			if ( !"1".equals(VIS[i]) || (apId!=null && !apId.equals(AP_ID[i])) )
				continue;

			agDto = (AgenciaDTO) auxAG.get( COD_DP[i] );
			agExiste = true;
			if (agDto == null) {
				agExiste = false;
				agDto = new AgenciaDTO();
			}

			agDto.setAgenId( COD_DP[i] );
			int cantidad = 0;
			try {
				cantidad = Integer.parseInt( TOTAL[i] );
			} catch (Exception e) {
				cantidad = 0;
			}
			agDto.addCantidad( ACT_ID[i], cantidad );
			auxAG.put( COD_DP[i], agDto);
			if ( !agExiste )
				lista.add( agDto );
		}
		}finally{			
			traceManager.traceOpEnd(traceOp); //@Trace
		}
		return lista;		
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

	protected Date parseFecha(String fecha) {
		try {
			int sinMs = fecha.lastIndexOf(".");
			return formatoFecha.parse(fecha.substring(0,sinMs));
		} catch (Exception e) {
			log.debug("Problemas parseando fecha " + fecha + ". Default: Fecha Actual", e);
			return new Date();
		}
	}

	/*
	 * Retorna los Datos para el Cuadro de Mando. 
	 */
	public List getDetalleActividadTC(HashMap filtro) throws TablaException {
	
		String sql = 
				"select D.actc_id, D.act_id " +
				"from ATIEMPO.DETALLE_ACTIVIDAD_TC D";

		DBManager db = new DBManager();
		db.setDataSource(DBManager.JDBC_BANDEJA);
		Hashtable salida = db.selectReadUncommitted(sql);		
		String[] ACTC_ID = (String[]) salida.get("ACTC_ID");
		String[] ACT_ID = (String[]) salida.get("ACT_ID");

		List lista = new ArrayList();
		CuadroMandoDTO cDto = null;
		HashMap TC = new HashMap();
		for (int i = 0; ACTC_ID!=null && i<ACTC_ID.length; i++) {
			cDto = (CuadroMandoDTO) TC.get( ACTC_ID[i] ); 

			if ( cDto == null ) {
				cDto = new CuadroMandoDTO();
				cDto.setActividadTC( (Long) cast(ACTC_ID[i], null, "LONG") );
				cDto.addActividad( (Long) cast(ACT_ID[i], null, "LONG")  );
				lista.add( cDto );
			} else {
				cDto.addActividad( (Long) cast(ACT_ID[i], null, "LONG")  );
			}
			TC.put( ACTC_ID[i], cDto );
		}
		return lista;		
	}

	/*
	 * Retorna los Datos para el Cuadro de Mando. 
	 */
	public List getTitulosCuadroMando(HashMap filtro) throws TablaException
	{
	
		String sqlWhereFijo = getWhereFijo(filtro);
		String sql = 
				"select TC.actc_id, TC.actc_nombre, TC.actc_cuadro, " +
				"TC.actc_link, TC.actc_subtotal, TC.ap_id, TC.actc_posicion " +
				"from ATIEMPO.ACTIVIDAD_TORRE_CONTROL TC " +
				"where 1=1 and  TC.actc_id=4 " + sqlWhereFijo + " " +
				"order by TC.actc_cuadro, Tc.actc_posicion, TC.actc_id";
			
		//log.debug(sql);

		DBManager db = new DBManager();
		db.setDataSource(DBManager.JDBC_BANDEJA);
		
		Hashtable salida = db.selectReadUncommitted(sql);
		
		String[] ACTC_ID = (String[]) salida.get("ACTC_ID");
		String[] ACTC_NAME = (String[]) salida.get("ACTC_NOMBRE");
		String[] ACTC_CUADRO = (String[]) salida.get("ACTC_CUADRO");
		String[] ACTC_LINK = (String[]) salida.get("ACTC_LINK");
		String[] ACTC_SUBTOTAL = (String[]) salida.get("ACTC_SUBTOTAL");
		String[] ACTC_POSICION = (String[]) salida.get("ACTC_POSICION");
		String[] AP_ID = (String[]) salida.get("AP_ID");

		List lista = new ArrayList();
		CuadroMandoDTO cDto = null;
		for (int i = 0; ACTC_ID!=null && i<ACTC_ID.length; i++) {
			cDto = new CuadroMandoDTO();
			cDto.setActividadTC( (Long) cast(ACTC_ID[i], null, "LONG") );
			cDto.setAppID( (Integer) cast(AP_ID[i], null, "INTEGER") );
			cDto.setNombreActividad( ( (String) cast(ACTC_NAME[i], new String(""), "STRING")) );
			cDto.setCuadro( ( (Integer) cast(ACTC_CUADRO[i], new Integer(0), "INTEGER")).intValue() );
			cDto.setLink( ( (Integer) cast(ACTC_LINK[i], new Integer(0), "INTEGER")).intValue() );
			cDto.setSubTotal( ( (Integer) cast(ACTC_SUBTOTAL[i], new Integer(0), "INTEGER")).intValue() );
			cDto.setPosicion( ( (Integer) cast(ACTC_POSICION[i], new Integer(0), "INTEGER")).intValue() );

			lista.add( cDto );
		}
		
		return lista;		
	}

	public List getListadoPeticionesTC(HashMap filtro) throws TablaException
	{
		String sqlWhereFijo = getWhereFijo(filtro);

		// No respeta los Filtros Originales ?

		String sql = 
				" select B.bi_id, B.bi_nro_peticion, B.usua_id, B.act_id " +
				" from ATIEMPO.BINTEGRADA B " +
				" where B.bi_visible=1 " + 
				sqlWhereFijo +
				" and b.act_id in ( select det.ACT_ID from atiempo.detalle_actividad_tc det where det.actc_id=4 ) ";
	
		//log.debug(sql);
	
		DBManager db = new DBManager();
		db.setDataSource(DBManager.JDBC_BANDEJA);
		Hashtable salida = db.selectReadUncommitted(sql);
//			Hashtable salida = db.selectReadUncommitted(sql.toString());
	
		String[] BI_ID = (String[]) salida.get("BI_ID");
		String[] BI_NRO_PETICION = (String[]) salida.get("BI_NRO_PETICION");
		String[] USUA_ID = (String[]) salida.get("USUA_ID");
		String[] ACT_ID = (String[]) salida.get("ACT_ID");
	
		List lista = new ArrayList();					
		for (int i = 0; BI_ID != null && i < BI_ID.length; i++) {
			PeticionDTO p = new PeticionDTO();
			p.setBiId(new Long(BI_ID[i]));
			p.setBiNroPeticion(new Long(BI_NRO_PETICION[i]));
			p.setUsuaId(new Long(USUA_ID[i]));
			p.setActividadId(ACT_ID[i]);
			lista.add(p);
		}
		
		return lista;		
	}
}
