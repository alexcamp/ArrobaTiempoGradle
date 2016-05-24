/*
 * Created on Aug 1, 2008
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.telefonica_chile.atiempo.trace.atiempo;

import java.util.HashMap;

import org.apache.log4j.Logger;

import com.telefonica_chile.atiempo.trace.api.TraceManager;

/**
 * @author 807793
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class TraceAdapter {
    private static final HashMap map_adapter = new HashMap();
	private static HashMap operationsHash = new HashMap();
	private static Logger logAdapter = Logger.getLogger(TraceAdapter.class);
	public static final String COL_TIPO_OP_ACCION_WEB="Acción Web";
	public static final String COL_TIPO_OP_MENSAJE_TR="Mensaje TR";
	public static final String COL_TIPO_OP_ACTIVIDAD_WF="Actividad WF";
	public static final String COL_TIPO_OP_INTERNA="Interna";
    
    public static final void  startAdapter(){
    	
    	/* acciones.xml -- Bandeja */
		map_adapter.put("verBandeja",TraceManager.OP_VER_BANDEJA_PRINCIPAL);		
		map_adapter.put("verControlTecnico",TraceManager.OP_VER_CONTROL_TECNICO);
		map_adapter.put("verReporteTerreno",TraceManager.OP_VER_REPORTE_TERRENO);
		map_adapter.put("iframeReporteTerreno",TraceManager.OP_VER_FRAME_REPORTE_TERRENO);
		map_adapter.put("verBandejaBackOC",TraceManager.OP_VER_BANDEJA_BACK_OC);
		map_adapter.put("verBandejaEsperaBA",TraceManager.OP_VER_BANDEJA_ESPERA_BA);
		map_adapter.put("verBandejaSupCo",TraceManager.OP_VER_BANDEJA_SUP_CO);
		map_adapter.put("peticionesSupervisado",TraceManager.OP_PETICIONES_SUPERVISADO);
		map_adapter.put("verBandejaEsperaTerra",TraceManager.OP_VER_BANDEJA_ESPERA_TERRA);
		map_adapter.put("verNuevaBandejaTorreControl",TraceManager.OP_VER_NUEVA_BANDEJA_TORRE_CONTROL);
		map_adapter.put("verNuevaTorreControl",TraceManager.OP_VER_NUEVA_TORRE_CONTROL);
		map_adapter.put("verMarcoTorreControl",TraceManager.OP_MARCO_TORRE_CONTROL);
		map_adapter.put("verGestionInbound",TraceManager.OP_VER_GESTION_INBOUND);
		map_adapter.put("verGestionOutbound",TraceManager.OP_VER_GESTION_OUTBOUND);
		map_adapter.put("verInstalarEquipos",TraceManager.OP_VER_INSTALAR_EQUIPOS);
		map_adapter.put("verBuscadorGesOS",TraceManager.OP_VER_BUSCADOR_GES_OS);
		map_adapter.put("verPTVPI",TraceManager.OP_VER_PTVPI);
		map_adapter.put("logoff",TraceManager.OP_LOG_OFF);
		map_adapter.put("recuperarClave",TraceManager.OP_RECUPERAR_CLAVE);
		map_adapter.put("cambiarClave",TraceManager.OP_CAMBIAR_CLAVE);
		map_adapter.put("ListadoMasivo",TraceManager.OP_LISTADO_MASIVO);
		map_adapter.put("VerUploadFile",TraceManager.OP_VER_UPLOAD_FILE);
		map_adapter.put("UploadFile",TraceManager.OP_UPLOAD_FILE);
		
		/* acciones.xml -- Vpistbba */
		map_adapter.put("VolverBandeja",TraceManager.OP_VOLVER_BANDEJA);		
		map_adapter.put("VolverBandejaSupervisado",TraceManager.OP_VOLVER_BANDEJA_SUPERVISADO);
		map_adapter.put("DespliegaPantallaCo",TraceManager.OP_DESPLIEGA_PANTALLA_CO);
		map_adapter.put("ImpresionOdtCo",TraceManager.OP_IMPRESION_ODT_CO);
		map_adapter.put("ImpresionOdtCds",TraceManager.OP_IMPRESION_ODT_CDS);
		map_adapter.put("RefrescaDatosTecnicos",TraceManager.OP_REFRESCA_DATOS_TECNICOS);
		map_adapter.put("RefrescaDatosBA",TraceManager.OP_REFRESCA_DATOS_BA);
		map_adapter.put("RefreshParDeco",TraceManager.OP_REFRESH_PAR_DECO);
		map_adapter.put("BuscadorPeticionesCo",TraceManager.OP_BUSCADOR_PETICION_CO);
		map_adapter.put("DespliegaBuscadorHistoricoVPI",TraceManager.OP_DESPLIEGA_BUSCADOR_HISTORICO_VPI);
		map_adapter.put("CambioTecnico",TraceManager.OP_CAMBIO_TECNICO);
		map_adapter.put("ListadoTecnicos",TraceManager.OP_LISTADO_TECNICOS);
		map_adapter.put("CambioAgenda",TraceManager.OP_CAMBIO_AGENDA);
		map_adapter.put("BuscarDecoTarjeta",TraceManager.OP_BUSCAR_DECO_TARJETA);
		map_adapter.put("BuscarDecoTarjetaCI",TraceManager.OP_BUSCAR_DECO_TARJETA_CI);
		map_adapter.put("ConsultaModems",TraceManager.OP_CONSULTA_MODEMS);
		map_adapter.put("ActivarDecoTarjeta",TraceManager.OP_ACTIVAR_DECO_TARJETA);
		map_adapter.put("ActivarDecoTarjetaCI",TraceManager.OP_ACTIVAR_DECO_TARJETA_CI);
		map_adapter.put("ResultadoActivacionDecos",TraceManager.OP_RESULTADO_ACTIVACION_DECOS);
		map_adapter.put("GrabaPantallaCo",TraceManager.OP_GRABA_PANTALLA_CO);
		map_adapter.put("iframeDecoTarMigracionCI",TraceManager.OP_FRAME_DECO_TAR_MIGRACION_CI);
		map_adapter.put("iframeDecoTarCI",TraceManager.OP_FRAME_DECO_TAR_CI);
		map_adapter.put("ActualizarInventarioDTHCI",TraceManager.OP_ACTUALIZAR_INVENTARIO_DTHCI);
		map_adapter.put("verificaFechas",TraceManager.OP_VERIFICA_FECHAS);
			
		map_adapter.put("Operacion por defecto",TraceManager.OP_DEFECTO);	
		
		map_adapter.put("UploadFileAcc",TraceManager.OP_UPLOAD_FILE_CLASS);
		map_adapter.put("TablaGestionOutbound",TraceManager.OP_SQL_GOUTBOUND);
		map_adapter.put("TablaGestionInbound",TraceManager.OP_SQL_GINBOUND);
		map_adapter.put("TablaNuevaTorreControl",TraceManager.OP_SQL_TORRE_CONTROL);
		map_adapter.put("TablaEsperaBA",TraceManager.OP_SQL_ESPERA_BA);
		map_adapter.put("TablaEsperaBA_count",TraceManager.OP_SQL_ESPERA_BA);
		map_adapter.put("TablaEsperaTerra",TraceManager.OP_SQL_ESPERA_TERRA);
		map_adapter.put("TablaEsperaTerra_count",TraceManager.OP_SQL_ESPERA_TERRA);
				
		map_adapter.put("TablaTorreFiltro",TraceManager.OP_SQL_TORRE_FILTRO);
		map_adapter.put("ControladorDeSeguridadPorDefecto",TraceManager.OP_USUARIO_WEB);
		map_adapter.put("ControladorDeSeguridadPorDefecto_vpi",TraceManager.OP_AUTORIZACION_VPI);
		map_adapter.put("ControladorDeSeguridadPorDefecto_bandeja",TraceManager.OP_AUTORIZACION_BANDEJA);
		map_adapter.put("TablaBandeja",TraceManager.OP_FILTRO);
		map_adapter.put("TablaBandeja_count",TraceManager.OP_SQL_FILTRO_COUNT);
    }
    
    public static Short getAccion(String nombre){
    	if(map_adapter.get(nombre) == null){
    		logAdapter.debug("No se encuentra ninguna actividad en la tabla con el nombre "+nombre+ ", se crea una operacion por defecto");
    		return TraceManager.OP_DEFECTO;
    	}
    	return (Short) map_adapter.get(nombre);
    }
    
	public static Short getAccion(Class c){
		String clase = c.getName();
		String operacion = clase.substring(clase.lastIndexOf(".")+1);		
			if(map_adapter.get(operacion) == null){
				logAdapter.debug("No se encuentra ninguna actividad en la tabla con el nombre "+operacion+ ", se crea una operacion por defecto");
				return TraceManager.OP_DEFECTO;
			}
			return (Short) map_adapter.get(operacion);
	}
	
	public static Short getAccion(Class c , String tipo){
		String clase = c.getName();
		String operacion = clase.substring(clase.lastIndexOf(".")+1);		
		if(map_adapter.get(operacion + "_" + tipo) == null){
			logAdapter.debug("No se encuentra ninguna actividad en la tabla con el nombre "+operacion+ "_" + tipo +", se crea una operacion por defecto");
			return TraceManager.OP_DEFECTO;
		}
		return (Short) map_adapter.get(operacion + "_" +tipo);
	}
	
	public static Short getIdOp(Long idAct){
		if (operationsHash.isEmpty()) {
			initOperationsHash();
		}
		Short idOpAct = (Short) operationsHash.get(idAct);
		if (idOpAct == null) {
			logAdapter.debug("Para la actividad " + idAct + " , se utiliza la operación " + idOpAct);
			idOpAct = TraceManager.OP_DEFECTO;
		}
		return idOpAct;
	}
	
	
	private static void initOperationsHash() {
		operationsHash.put(new Long(1000),TraceManager.OP_VERIF_PETICION);
		operationsHash.put(new Long(1001),TraceManager.OP_RECHAZO_PETICION);
		operationsHash.put(new Long(1002),TraceManager.OP_GEN_FLUJO_PET);
		operationsHash.put(new Long(1003),TraceManager.OP_REVISAR_FLUJO);
		operationsHash.put(new Long(1004),TraceManager.OP_ANULACION_ATIS_RECEPCION_PET);
		operationsHash.put(new Long(1005),TraceManager.OP_PROBLEMA_ANULACION_ECEPCION_PET);
		operationsHash.put(new Long(1006),TraceManager.OP_ESPERO_DESBLOQUEO);
		operationsHash.put(new Long(1007),TraceManager.OP_ANULACION_ATIS);
		operationsHash.put(new Long(1008),TraceManager.OP_PROBLEMA_ANULACION);
		operationsHash.put(new Long(1009),TraceManager.OP_CALC_SIG_TAREA);		
		operationsHash.put(new Long(1010),TraceManager.OP_TERMINARON_PARALELAS);
		operationsHash.put(new Long(1011),TraceManager.OP_CIERRE_PETICION);
		operationsHash.put(new Long(1012),TraceManager.OP_ERROR_DIRECTOR);
		operationsHash.put(new Long(1013),TraceManager.OP_ASIG_REC_STB);
		operationsHash.put(new Long(1014),TraceManager.OP_ASIG_MANUAL_STB);
		operationsHash.put(new Long(1015),TraceManager.OP_GESTION_RECURSOS);
		operationsHash.put(new Long(1016),TraceManager.OP_CONFIG_STB);		
		operationsHash.put(new Long(1017),TraceManager.OP_CONFIG_RI);
		operationsHash.put(new Long(1018),TraceManager.OP_OBT_CONFIG_BA);
		operationsHash.put(new Long(1019),TraceManager.OP_OBT_PTOS_RED_STB);
		operationsHash.put(new Long(1020),TraceManager.OP_CONFIG_INTERNET);
		operationsHash.put(new Long(1021),TraceManager.OP_CONFIG_DTH);
		operationsHash.put(new Long(1022),TraceManager.OP_INSTALAR);
		operationsHash.put(new Long(1023),TraceManager.OP_CTRL_INSTALACION);
		operationsHash.put(new Long(1024),TraceManager.OP_DESCONFIG_STB);
		operationsHash.put(new Long(1025),TraceManager.OP_DESCONFIG_RI);
		operationsHash.put(new Long(1026),TraceManager.OP_DESCONFIG_INTERNET);
		operationsHash.put(new Long(1027),TraceManager.OP_DESINSTALAR);
		operationsHash.put(new Long(1028),TraceManager.OP_CTRL_DESINSTALACION);
		operationsHash.put(new Long(1029),TraceManager.OP_DESINSTALAR_DTH);
		operationsHash.put(new Long(1030),TraceManager.OP_CTRL_DESINST_DTH);
		operationsHash.put(new Long(1031),TraceManager.OP_ACTUALIZAR_INV_STB);
		operationsHash.put(new Long(1032),TraceManager.OP_ACTUALIZAR_INV_SS);
		operationsHash.put(new Long(1033),TraceManager.OP_ACTUALIZAR_INV_BA);
		operationsHash.put(new Long(1034),TraceManager.OP_ACTUALIZAR_INV_DTH);
		operationsHash.put(new Long(1035),TraceManager.OP_SOL_SOPORTE_TECNICA);
		operationsHash.put(new Long(1036),TraceManager.OP_PEND_SOL_TEC_STB);
		operationsHash.put(new Long(1037),TraceManager.OP_PEND_SOL_TEC_BA);
		operationsHash.put(new Long(1038),TraceManager.OP_PEND_SOL_TEC_DTH);
		operationsHash.put(new Long(1039),TraceManager.OP_PEND_SOL_COMERCIAL);
		
		operationsHash.put(new Long(1040),TraceManager.OP_VERIF_PETICION_ST);
		operationsHash.put(new Long(1041),TraceManager.OP_BLOQUEO_PET_ST);
		operationsHash.put(new Long(1042),TraceManager.OP_GEN_FLUJO_PET_ST);
		operationsHash.put(new Long(1043),TraceManager.OP_REVISAR_FLUJO_ST);
		operationsHash.put(new Long(1044),TraceManager.OP_ANULACION_ATIS_ST);
		operationsHash.put(new Long(1045),TraceManager.OP_PROBLEMA_ANULACION_ST);
		operationsHash.put(new Long(1046),TraceManager.OP_CALCULAR_SIG_TAREA_ST);
		operationsHash.put(new Long(1047),TraceManager.OP_TERMINARON_PARALELAS_ST);

		operationsHash.put(new Long(1048),TraceManager.OP_CIERRE_MANUAL);
		operationsHash.put(new Long(1049),TraceManager.OP_ENVIO_CIERRE_PET);		
		operationsHash.put(new Long(1050),TraceManager.OP_ERROR_DIR_ST);
		
		operationsHash.put(new Long(1051),TraceManager.OP_RECUPERAR_INFO_STB);
		operationsHash.put(new Long(1052),TraceManager.OP_RECUPERAR_INFO_BA);
		operationsHash.put(new Long(1053),TraceManager.OP_RECUPERAR_INFO_TV);
		operationsHash.put(new Long(1054),TraceManager.OP_DIAGNOSTICAR_STB);
		operationsHash.put(new Long(1055),TraceManager.OP_PLANTA_INT_STB);
		operationsHash.put(new Long(1056),TraceManager.OP_PLANTA_EXT_STB);
		operationsHash.put(new Long(1057),TraceManager.OP_RED_INTELIGENTE_STB);
		operationsHash.put(new Long(1058),TraceManager.OP_CTRL_REP_STB);
		operationsHash.put(new Long(1059),TraceManager.OP_DIAGNOSTICO_BA);
		operationsHash.put(new Long(1060),TraceManager.OP_PRIMER_NIVLE_BA);
		operationsHash.put(new Long(1061),TraceManager.OP_PLANTA_EXT_BA);
		operationsHash.put(new Long(1062),TraceManager.OP_CTRL_REAPARCION_BA);
		operationsHash.put(new Long(1063),TraceManager.OP_DIAGNOSTICO_TV);
		operationsHash.put(new Long(1064),TraceManager.OP_PLATAFORMA_TV);
		operationsHash.put(new Long(1065),TraceManager.OP_CTRL_REPARACION_TV);
		operationsHash.put(new Long(1066),TraceManager.OP_ACTUALIZAR_INV_STB);
		operationsHash.put(new Long(1067),TraceManager.OP_ACTUALIZAR_INV_BA);
		operationsHash.put(new Long(1068),TraceManager.OP_ACTUALIZAR_INV_TV);
		operationsHash.put(new Long(1069),TraceManager.OP_CONFIG_INPREL);
		operationsHash.put(new Long(1070),TraceManager.OP_CAMBIAR_NRO_BA);
		operationsHash.put(new Long(1071),TraceManager.OP_ANALIZAR_REC);
		operationsHash.put(new Long(1072),TraceManager.OP_OBTENER_PTOS_RED_BA);
		operationsHash.put(new Long(1073),TraceManager.OP_MATTO_ENERGIA);
		operationsHash.put(new Long(1074),TraceManager.OP_MATTO_CX);
		operationsHash.put(new Long(1075),TraceManager.OP_MATTO_TX);
		operationsHash.put(new Long(1076),TraceManager.OP_SEGUNDO_NIVEL_BA);
		operationsHash.put(new Long(1077),TraceManager.OP_MARCAR_AVERIA_STB);
		operationsHash.put(new Long(1078),TraceManager.OP_CTRL_ACT_INV_STB);
		operationsHash.put(new Long(1079),TraceManager.OP_CTRL_ACT_INV_BA);
		operationsHash.put(new Long(1080),TraceManager.OP_OBT_CONFIG_DTH);
		operationsHash.put(new Long(1081),TraceManager.OP_CTRL_ACT_INV_TV);
		operationsHash.put(new Long(1082),TraceManager.OP_CONFIG_TERRA);
		operationsHash.put(new Long(1083),TraceManager.OP_CTRL_CONFIG_TERRA);
		operationsHash.put(new Long(1084),TraceManager.OP_OBT_CONFIG_BA_TERRA);
		operationsHash.put(new Long(1085),TraceManager.OP_ESPERO_FEC_EJECUCION);
		operationsHash.put(new Long(1086),TraceManager.OP_VERIF_FEC_EJECUCION);		
		operationsHash.put(new Long(1087),TraceManager.OP_INSTALAR_VISTA);
		operationsHash.put(new Long(1088),TraceManager.OP_CTRL_INST_VISITA);	
		
		
		operationsHash.put(new Long(1500),TraceManager.OP_CIERRE_PRIMARIO);
		operationsHash.put(new Long(1501),TraceManager.OP_CIERRE_PRIMARIO_TV);
		operationsHash.put(new Long(1502),TraceManager.OP_CONFIG_AULA);
		operationsHash.put(new Long(1503),TraceManager.OP_CTRL_CONFIG_AULA);

		// ana santos - 21 Nov - Granite - inicio
		operationsHash.put(new Long(1510),TraceManager.OP_ASIG_REC_LB);
		operationsHash.put(new Long(1511),TraceManager.OP_CONFIG_AUTOM_SERV_CENTRALES);
		operationsHash.put(new Long(1512),TraceManager.OP_ACTUALIZAR_INVENTARIO_STB);
		operationsHash.put(new Long(1513),TraceManager.OP_LIBERACION_RECURSOS);
		operationsHash.put(new Long(1514),TraceManager.OP_CONSULTA_PUNTOS_RED);
		operationsHash.put(new Long(1515),TraceManager.OP_PLAN_BA_ASOCIADO_LB);
		operationsHash.put(new Long(1516),TraceManager.OP_ACTUALIZACION_ESTADO_LB);
		// ana santos - 21 Nov - Granite - fin
		
		//Cr20948 - Altamira - Operaciones que faltaban de altamira y demas PdTI, etc - guido - 22/May/2009 - INICIO
		operationsHash.put(new Long(1504), TraceManager.OP_ACT_CONF_TELEFONAUTAS);
		operationsHash.put(new Long(1700), TraceManager.OP_ACT_OBTENER_INV_EQUIPO);
		operationsHash.put(new Long(1701), TraceManager.OP_ACT_ACTUALIZAR_INVENTARIO_EQUIPO);
		operationsHash.put(new Long(1702), TraceManager.OP_ACT_RECUPERAR_INFO_EQUIPO);
		operationsHash.put(new Long(1703), TraceManager.OP_ACT_ACTUALIZAR_INV_EQUIPO);
		operationsHash.put(new Long(1704), TraceManager.OP_ACT_CONTROL_ACT_INV_EQUIPO);
		operationsHash.put(new Long(1705), TraceManager.OP_ACT_RI_ALTA_ABONADO );
		operationsHash.put(new Long(1706), TraceManager.OP_ACT_RI_APROV_ALTA_ABONADO);
		operationsHash.put(new Long(1707), TraceManager.OP_ACT_RI_ALTA_PERIODIFICACION);
		operationsHash.put(new Long(1708), TraceManager.OP_ACT_RI_APROV_ALTA_PER);
		operationsHash.put(new Long(1709), TraceManager.OP_ACT_RI_BAJA_ABONADO);
		operationsHash.put(new Long(1710), TraceManager.OP_ACT_RI_CAMBIO_NUMERO);
		operationsHash.put(new Long(1711), TraceManager.OP_ACT_RI_APROV_CAMBIO_NUMERO);
		operationsHash.put(new Long(1712), TraceManager.OP_ACT_RI_APROV_BAJA_ABONADO);
		operationsHash.put(new Long(1713), TraceManager.OP_ACT_RI_APROV_CAMBIO_PREPAGO);
		operationsHash.put(new Long(1714), TraceManager.OP_ACT_RI_CAMBIO_PREPAGO);
		operationsHash.put(new Long(1715), TraceManager.OP_ACT_RI_APROV_BAJA_PER);
		operationsHash.put(new Long(1716), TraceManager.OP_ACT_RI_BAJA_PER );
		//Cr20948 - Altamira - Operaciones que faltaban de altamira y demas PdTI, etc - guido - 22/May/2009 - FIN
	}


	
}
