/*
 * Created on Feb 21, 2005
 *
 * Clase que recibe el parametro del filtro (id de agencia y el id de actividad)
 * y forma una tabla con los datos de BIntegrada asociado a esos 2 parametros
 */
package com.telefonica_chile.bandeja.torreControl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

import com.tecnonautica.utiles.db.DBManager;
import com.tecnonautica.utiles.tablas.TablaException;
import com.telefonica_chile.atiempo.trace.api.TraceMainConfiguration;
import com.telefonica_chile.atiempo.trace.api.TraceManager;
import com.telefonica_chile.atiempo.trace.api.TraceOperation;
import com.telefonica_chile.atiempo.trace.atiempo.TraceAdapter;
import com.telefonica_chile.atiempo.utiles.LoggerFactory;
import com.telefonica_chile.bandeja.bintegrada.session.TablaBandeja;
import com.telefonica_chile.bandeja.dto.PeticionDTO;
import com.telefonica_chile.bandeja.semaforos.Alertas;
import com.telefonica_chile.bandeja.semaforos.AlertasException;

/**
 * @author Lai Chun-Hau
 * 
 */
public class TablaTorreFiltro extends TablaBandeja {

	/**
	 * Obtencion de logger.
	 * @author amartoq
	 */
	protected transient Logger log = LoggerFactory.getLogger(getClass());

	/**
	 * @param paginaActual
	 */
	public TablaTorreFiltro() {
		super(0);

	}

	public TablaTorreFiltro(int paginaActual) {
		super(paginaActual);

	}

//	 CR17800 - guido - inicio - se agregan parametros
	public int getCantidadElementos(HashMap filtro, ArrayList tipoParam,ArrayList valorParam) {
		int cantElementos = 0;
		filtro.put("USUA_ID", ""); // Elimino el UsuaID, torre de Control no lo Necesita.

		String sqlWhereFijo = getWhereFijo(filtro, tipoParam, valorParam);

		// No tengo ROL en la TC
		String condActividadRol = "";
			
		String sql = "" +
			"select count(b.act_id) as TOTAL " +
			"from ATIEMPO.BINTEGRADA B, ATIEMPO.DETALLE_ACTIVIDAD_TC D " +
			" where B.act_id=D.act_id " + condActividadRol + " and B.bi_visible=1 " +
			sqlWhereFijo 
			;
			
		DBManager db = new DBManager();
		db.setDataSource(DBManager.JDBC_BANDEJA);
		//Gustavo - CR 16440 - Cambio la llamada al metodo de ejecución de selectCommitPrepared a selectReadUncommited para setear readOnly y nivel de aislamiento
		Hashtable salida =  db.selectReadUncommitted( sql, tipoParam, valorParam);
//		 CR17800 - guido - fin - se agregan parametros
		
		String[] c = (String[]) salida.get("TOTAL");
		if (c == null || c.length==0)
			return 0;
		else {
			try {
				cantElementos = Integer.parseInt( c[0] );
			} catch (Exception e) {
				cantElementos = 0;
			}
		}
		return cantElementos;
	}
	
//	 CR17800 - guido - inicio - se agregan parametros
	public List getElementosPagina(HashMap filtro, ArrayList tipoParam, ArrayList valorParam) throws TablaException {
		filtro.put("USUA_ID", "");
		String sqlWhereFijo = getWhereFijo(filtro, tipoParam, valorParam);
		String condActividadRol = "";
		//Obtengo el rol para saber si la aplicacion es ATST o no
		//  EN TC NO TENGO ROL
		//Long idRol = (Long)filtro.get("idRol");
		//if( idRol!=null && !esATST(idRol)) //Solo nos interesa modificar el caso cuando viene el idRol
		//   condActividadRol = "and A.rol_id=" + idRol;	

		String sql = 
			"select * from ( " +
				"select B.bi_id, B.bi_cliente_nombre, B.bi_cliente_apellidos, B.bi_cliente_area, " +
				"B.bi_cliente_servicio, B.bi_url_detalle, B.bi_fecha_inicio, B.bi_grupo_segmento, " +
				"B.bi_fecha_apertura, B.bi_fecha_asignacion, B.bi_fecha_compromiso, B.ap_id, " +
				"B.bi_nro_peticion, B.usua_id, B.act_id, " +
				//"A.rol_id, A.act_codigo, A.act_descripcion, A.act_nombre_reversa, " +
				"B.bi_estado_peticion, B.bi_tipo_trabajo, " +
				"B.bi_id_tipo_agendamiento, B.bi_id_rango, B.bi_hora_desde, B.bi_hora_hasta, " +
				"row_number() over(order by B.bi_grupo_segmento, B.bi_fecha_compromiso, B.bi_tipo_trabajo) as row  " +
				"from ATIEMPO.BINTEGRADA B, ATIEMPO.DETALLE_ACTIVIDAD_TC D " +
				"where B.bi_visible=1 and D.act_id=B.act_id " +
				sqlWhereFijo +
			" ) subtabla where row >= " + getIdxInicial() + " and row <= " + getIdxFinal() + "";


		DBManager db = new DBManager();
		db.setDataSource("jdbc/bandeja");
		//Gustavo - CR 16440 - Cambio la llamada al metodo de ejecución de selectCommitPrepared a selectReadUncommited para setear readOnly y nivel de aislamiento
		Hashtable salida = db.selectReadUncommitted(sql, tipoParam, valorParam);
//		 CR17800 - guido - fin - se agregan parametros
		
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
		String[] ACT_ID = (String[]) salida.get("ACT_ID");

		/*
		String[] ROL_ID = (String[]) salida.get("ROL_ID");
		String[] ACT_CODIGO = (String[]) salida.get("ACT_CODIGO");
		String[] ACT_DESCRIPCION = (String[]) salida.get("ACT_DESCRIPCION");
		String[] ACT_NOMBRE_REVERSA = (String[]) salida.get("ACT_NOMBRE_REVERSA");
		*/
		String[] BI_ESTADO_PETICION = (String[]) salida.get("BI_ESTADO_PETICION");
		String[] BI_TIPO_TRABAJO = (String[]) salida.get("BI_TIPO_TRABAJO");
		String[] BI_GSEGMENTO = (String[]) salida.get("BI_GRUPO_SEGMENTO");
		
		// Para el Agendamiento
		String[] BI_ID_AGENDAMIENTO = (String[]) salida.get("BI_ID_TIPO_AGENDAMIENTO");
		String[] BI_IDRANGO= (String[]) salida.get("BI_ID_RANGO");
		String[] BI_HDESDE = (String[]) salida.get("BI_HORA_DESDE");
		String[] BI_HHASTA = (String[]) salida.get("BI_HORA_HASTA");

		/*
		Alertas alerta;
		try {
			alerta = new Alertas();
		} catch (AlertasException e) {
			log.error("Problemas recuperando alertas", e);
			throw new TablaException("Hubo problemas recuperando las alertas", e);
		}
		*/

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
			p.setActividadId(ACT_ID[i]);
		/*
			p.setRolId(ROL_ID[i]);
			p.setActividadCodigo(ACT_CODIGO[i]);
			p.setActividadDescripcion(ACT_DESCRIPCION[i]);
			p.setActividadNombreReversa(ACT_NOMBRE_REVERSA[i]);
		*/	
			p.setIdBloqueSegmento( (Long) cast(BI_GSEGMENTO[i], new Long(0), "LONG") );
			p.setEstadoPeticion( (Integer) cast(BI_ESTADO_PETICION[i], new Integer(1), "INTEGER") );
			p.setTipoTrabajo( (Integer) cast(BI_TIPO_TRABAJO[i], new Integer(1), "INTEGER") );
			
			//alerta.setRol(p.getRolCodigo());
		/*	alerta.setRol( p.getRolId() );
			alerta.setActividadSemaforo(p.getActividadCodigo());
			alerta.setApId(p.getApId().toString());
		*/
			if (BI_FECHA_COMPROMISO != null && BI_FECHA_COMPROMISO[i].trim().length() > 0){
				Date fechaCompromiso = parseFecha(BI_FECHA_COMPROMISO[i]);
				p.setBiFechaCompromiso(fechaCompromiso);
		//		alerta.setFechaFinal(p.getBiFechaCompromiso());
		//		p.setBiSemaforoCompromiso(alerta.getSemaforoCompromiso());
			}
			
			if (BI_FECHA_ASIGNACION != null && BI_FECHA_ASIGNACION[i].trim().length() > 0){
				Date fechaAsignacion = parseFecha(BI_FECHA_ASIGNACION[i]);
				p.setBiFechaAsignacion(fechaAsignacion);
		//		alerta.setFechaFinal(p.getBiFechaAsignacion());
		//		p.setBiSemaforoActividad(alerta.getSemaforoActividad());
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


	/*
	 * 
	 * @author gavalen
	 *
	 * Consulta creada para la creacion del archivo de la torre de control
	 */	
	public Set getArchivoTC(String peticiones) throws TablaException {
		    String[] ids = null;
			//Inicio @Trace
			TraceMainConfiguration traceConf = TraceMainConfiguration.getInstance();		
			TraceManager traceManager = traceConf.getManager();			
			TraceOperation traceOp = traceManager.newOperation(TraceAdapter.getAccion(TablaTorreFiltro.class));
			try{
				traceOp.init(log);
				traceManager.traceOpStart(traceOp); 			
		
		StringBuffer sql = new StringBuffer()
			.append(" select bin.bi_id," )
			.append(" peti.peti_numero," )
			.append(" peti.peti_fecha_ingreso," )
			.append(" peti.peti_fecha_compromiso," )
			.append(" peti.peti_usuario_emisor," )
			.append(" peti.tica_id, ")	
			.append(" peti.peti_ooss,	" )
			.append(" bin.bi_fecha_inicio," )
			.append(" act.act_descripcion," )
			.append(" clie.clie_nombre," )
			.append(" clie.clie_ape_paterno, ")	
			.append(" clie.clie_ape_materno," )
			.append(" clie.segm_id," )
			.append(" segm.segm_descripcion," )
			.append(" usua.usua_nombre," )
			.append(" linea.line_numero, ")
			.append(" central.ceco_nombre," )
			.append(" agen.agen_descripcion," )
			.append(" est_peti.espe_nombre," )
			.append(" pun_vta.puve_codigo," )
			.append(" peti.peti_rut_vendedor ")
			.append(" from  vpistbba.peticion peti ")
			.append(" , Bintegrada bin ")
			.append(" , actividad act ")
			.append(" , cliente clie  ")
			.append(" , segmento segm ")
			.append(" , usuario usua ")
			.append(" , linea linea  ")
			.append(" , central_conmutacion central ") 
			.append(" , agencia agen ")
			.append(" , vpistbba.estado_peticion est_peti ")
            .append(" , punto_venta pun_vta ")
			.append(" where peti.peti_numero  in(" + peticiones + ")")
			.append(" and bin.act_id        = act.act_id ")
			.append(" and clie.cod_cliente  = peti.cod_cliente ")
			.append(" and segm.segm_id      = clie.segm_id ")
			.append(" and usua.usua_id	    = bin.usua_id ")
			.append(" and central.ceco_id   = linea.ceco_id ")
			.append(" and peti.line_id      = linea.line_id")
			.append(" and peti.agen_id 	    = agen.agen_id ")
			.append(" and est_peti.espe_id  = peti.espe_id ")
			.append(" and peti.peti_numero  = bin.bi_nro_peticion ")
			.append(" and peti.puve_id 	    = pun_vta.puve_id");	
		
		DBManager db = new DBManager();
		db.setDataSource("jdbc/bandeja");
		//Gustavo - CR 16440 - Cambio la llamada al metodo de ejecución de selectCommitPrepared a selectReadUncommited para setear readOnly y nivel de aislamiento
		Hashtable salida = db.selectReadUncommitted(sql.toString());
		traceOp.setColumn(TraceManager.COL_SQL,sql.toString());//@Trace
		traceOp.setColumn(TraceManager.COL_CANT_REGISTROS,String.valueOf(salida.size()));//@Trace
		
		String[] BI_ID = (String[]) salida.get("BI_ID");
		
		
		ids = (String[]) salida.get("BI_ID");
		}finally{	
			traceManager.traceOpEnd(traceOp); //@Trace
		}
		
		Set s = new HashSet();
		for (int i = 0; ids != null && i < ids.length; i++) {
			s.add(ids[i]);
		}
		
		//log.debug("TAMAÑO LISTA PETICIONES => " + salida.size());
		
		return s;
	}

	public ArrayList getElementosTC(String agencia, String actividad) throws TablaException {
		
		/*
		//actividad es el id de ACTIVIDAD_TORRE_CONTROL
		String condActividadRol=" 	act.rol_id = r.rol_id and ";
		//Averiguo si la aplicacion es ATST o no
		if(actividad!=null && esATST_TC(actividad)) 
		   condActividadRol="";	
		*/
								
		StringBuffer sql = new StringBuffer()
			.append(" select")
			.append(" 	bin.bi_id,")				
			.append(" 	bin.bi_cliente_nombre,")
			.append(" 	bin.bi_cliente_apellidos,")
			.append(" 	bin.bi_cliente_area,")
			.append(" 	bin.bi_cliente_servicio,")
			.append(" 	bin.bi_url_detalle,")
			.append("	grs.grse_nombre,")
			.append(" 	bin.bi_fecha_inicio,")
			.append(" 	bin.bi_fecha_apertura,")
			.append(" 	bin.bi_fecha_asignacion,")				
			.append(" 	bin.bi_fecha_compromiso,")
			.append(" 	bin.ap_id,")
			.append(" 	bin.bi_nro_peticion ,")
			.append(" 	us.usua_id,")
			.append(" 	us.usua_login,")
			.append(" 	us.usua_nombre,")
			.append(" 	r.rol_id,")
			.append(" 	r.rol_codigo,")
			.append(" 	r.rol_nombre,")
			.append(" 	act.act_id,")
			.append(" 	act.act_codigo,")
			.append(" 	act.act_descripcion,")
			.append(" 	act.act_nombre_reversa,")
			.append(" 	apl.ap_nombre,")
			.append("	bin.bi_estado_peticion,")
			.append("	bin.bi_tipo_trabajo,")										
			.append("	row_number() over() as row")
			.append(" from")
			.append(" 	ATIEMPO.BINTEGRADA bin , ATIEMPO.USUARIO us , ATIEMPO.ACTIVIDAD act , ATIEMPO.ROL r , ATIEMPO.APLICACION apl, ATIEMPO.actividad_torre_control tc, ATIEMPO.detalle_actividad_tc da , agenda.grupo_segmento grs")
			.append(" where")
			.append(" 	tc.actc_id = da.actc_id and")		
			.append("   grs.grse_id = bin.bi_grupo_segmento and ")	
			.append(" 	da.act_id  = act.act_id and")

			.append(" 	tc.actc_id = "+actividad+" and ")
			.append(" 	bin.agen_id = "+agencia+" and ")
			.append(" 	bin.act_id = act.act_id and ")
			.append(" 	act.rol_id = r.rol_id and ") // Esto no debe hacerse para ATST (Si se quiere solucionar puede
													 // realizarse un "((act.rol_id = r.rol_id and) or esATST) and"
													 // o se puede tratar de averiguar si es atst en base a la actividad
													 // pero ojo que es actividad de la torre control y no de atiempo.actividad
			.append(" 	bin.ap_id = apl.ap_id and ")
			.append(" 	bin.usua_id = us.usua_id ");		
			
			
		DBManager db = new DBManager();
		db.setDataSource(DBManager.JDBC_BANDEJA);
		//Gustavo - CR 16440 - Cambio la llamada al metodo de ejecución de selectCommitPrepared a selectReadUncommited para setear readOnly y nivel de aislamiento
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

		Alertas alerta;
		try {
			alerta = new Alertas();
		} catch (AlertasException e) {
			
			log.error("Problemas recuperando alertas", e);
			throw new TablaException("Hubo problemas recuperando las alertas", e);
		}

		ArrayList lista = new ArrayList();					
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
			p.setEstadoPeticion(new Integer(BI_ESTADO_PETICION[i]));
			p.setTipoTrabajo(new Integer(BI_TIPO_TRABAJO[i]));
			p.setNombreBloqueSegmento(GRSE_NOMBRE[i]);
			
			if (ACT_NOMBRE_REVERSA[i]!=null)
				p.setActividadNombreReversa(ACT_NOMBRE_REVERSA[i]);
			else
				ACT_NOMBRE_REVERSA[i]="";
			
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
							
			lista.add(p);
		}
		
		return lista;
	}
	
	/*
	// Funcion que determina si el rol pertenece o no a la aplicacion ATST
	protected boolean esATST_TC(String actividadTC){

		boolean esDeATST=false;

		Long idActividadTC=new Long(actividadTC);
		//Procedemos a invocar el Session de BIntegrada
		try {
			BIntegradaSessionLocalHome home = (BIntegradaSessionLocalHome)
					HomeFactory.getHome(BIntegradaSessionLocalHome.JNDI_NAME);
			BIntegradaSessionLocal local = home.create();

			String listaActATST = local.listaActividadesATST(idRol);

			if ((listaActATST!=null)&&(listaActATST.trim()!="")&&(listaActATST.length()>0))
		   		esDeATST=true;

		} catch (Exception e) {
			e.printStackTrace();
		} 
		return esDeATST;
	}
	*/
		
}