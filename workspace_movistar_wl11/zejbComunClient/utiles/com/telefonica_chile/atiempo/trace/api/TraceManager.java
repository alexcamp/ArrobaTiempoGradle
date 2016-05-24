/*
 * Created on Jul 30, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.telefonica_chile.atiempo.trace.api;


/**
 * @author 805538
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface TraceManager {
	// Operaciones que se loguean
	public static final Short OP_VER_BANDEJA_PRINCIPAL = new Short((short)100);
	public static final Short OP_VER_CONTROL_TECNICO = new Short((short)101);
	public static final Short OP_VER_REPORTE_TERRENO = new Short((short)102);
	public static final Short OP_VER_FRAME_REPORTE_TERRENO = new Short((short)103);
	public static final Short OP_VER_BANDEJA_BACK_OC = new Short((short)104);
	public static final Short OP_VER_BANDEJA_ESPERA_BA = new Short((short)105);
	public static final Short OP_VER_BANDEJA_SUP_CO = new Short((short)106);
	public static final Short OP_PETICIONES_SUPERVISADO = new Short((short)107);
	public static final Short OP_VER_BANDEJA_ESPERA_TERRA = new Short((short)108);
	public static final Short OP_VER_NUEVA_BANDEJA_TORRE_CONTROL = new Short((short)109);
	public static final Short OP_VER_NUEVA_TORRE_CONTROL = new Short((short)110);
	public static final Short OP_MARCO_TORRE_CONTROL = new Short((short)111);
	public static final Short OP_VER_GESTION_INBOUND = new Short((short)112);
	public static final Short OP_VER_GESTION_OUTBOUND = new Short((short)113);
	public static final Short OP_VER_BUSCADOR_GES_OS = new Short((short)114);
	public static final Short OP_VER_PTVPI = new Short((short)115);
	public static final Short OP_LOG_OFF = new Short((short)116);
	public static final Short OP_RECUPERAR_CLAVE = new Short((short)117);
	public static final Short OP_CAMBIAR_CLAVE = new Short((short)118);
	public static final Short OP_LISTADO_MASIVO = new Short((short)119);
	public static final Short OP_VER_UPLOAD_FILE = new Short((short)120);
	public static final Short OP_UPLOAD_FILE = new Short((short)121);	
	public static final Short OP_VOLVER_BANDEJA = new Short((short)122);
	public static final Short OP_VOLVER_BANDEJA_SUPERVISADO = new Short((short)123);
	public static final Short OP_DESPLIEGA_PANTALLA_CO = new Short((short)124);
	public static final Short OP_IMPRESION_ODT_CO = new Short((short)125);
	public static final Short OP_IMPRESION_ODT_CDS = new Short((short)126);
	public static final Short OP_REFRESCA_DATOS_TECNICOS = new Short((short)127);
	public static final Short OP_REFRESCA_DATOS_BA = new Short((short)128);
	public static final Short OP_REFRESH_PAR_DECO = new Short((short)129);
	public static final Short OP_BUSCADOR_PETICION_CO = new Short((short)130);
	public static final Short OP_DESPLIEGA_BUSCADOR_HISTORICO_VPI = new Short((short)131);
	public static final Short OP_CAMBIO_TECNICO = new Short((short)132);
	public static final Short OP_LISTADO_TECNICOS = new Short((short)133);
	public static final Short OP_CAMBIO_AGENDA = new Short((short)134);
	public static final Short OP_BUSCAR_DECO_TARJETA = new Short((short)135);
	public static final Short OP_BUSCAR_DECO_TARJETA_CI = new Short((short)136);
	public static final Short OP_CONSULTA_MODEMS = new Short((short)137);
	public static final Short OP_ACTIVAR_DECO_TARJETA = new Short((short)138);
	public static final Short OP_ACTIVAR_DECO_TARJETA_CI = new Short((short)139);
	public static final Short OP_RESULTADO_ACTIVACION_DECOS = new Short((short)140);
	public static final Short OP_GRABA_PANTALLA_CO = new Short((short)141);
	public static final Short OP_FRAME_DECO_TAR_MIGRACION_CI = new Short((short)142);
	public static final Short OP_FRAME_DECO_TAR_CI = new Short((short)143);
	public static final Short OP_ACTUALIZAR_INVENTARIO_DTHCI = new Short((short)144);
	public static final Short OP_VERIFICA_FECHAS = new Short((short)145);
	
	
	public static final Short OP_UPLOAD_FILE_CLASS = new Short((short)146);
	public static final Short OP_SQL_GOUTBOUND = new Short((short)147);
	public static final Short OP_SQL_TORRE_FILTRO = new Short((short)148);
	public static final Short OP_SQL_TORRE_CONTROL = new Short((short)149);
	public static final Short OP_SQL_ESPERA_BA = new Short((short)160);
	public static final Short OP_SQL_ESPERA_TERRA = new Short((short)161);
	public static final Short OP_SQL_GINBOUND = new Short((short)162);
	public static final Short OP_USUARIO_WEB = new Short((short)163);
	public static final Short OP_DBMANAGER_CONSULTA_HASH = new Short((short)164);
	public static final Short OP_DBMANAGER_SELECT_COMMIT = new Short((short)165);
	public static final Short OP_DBMANAGER_SELECT_COMMIT_PREPARED = new Short((short)166);
	public static final Short OP_DBMANAGER_SELECT_READ_UNCOMMIT = new Short((short)167);
	public static final Short OP_DBMANAGER_UPDATE = new Short((short)168);
	public static final Short OP_DBMANAGER_EJECUTA = new Short((short)169);
	public static final Short OP_SQL_FILTRO_COUNT = new Short((short)170);
	public static final Short OP_SQL_ESPERA_BA_COUNT = new Short((short)171);
	public static final Short OP_SQL_ESPERA_TERRA_COUNT = new Short((short)172);
		
	public static final Short OP_FILTRO = new Short((short)150);
	public static final Short OP_AUTORIZACION_VPI = new Short((short)151);
	public static final Short OP_AUTORIZACION_BANDEJA = new Short((short)152);
	
	
	//CR21911 -- Optimizacion asingancion de usuario
	public static final Short OP_ASIGNA_USUARIO = new Short((short)153);
	public static final Short OP_ASIGNA_USUARIO_SUP = new Short((short)154);
	
	public static final Short OP_VER_INSTALAR_EQUIPOS = new Short((short)155);
	
	public static final Short OP_DEFECTO = new Short((short)999);// CR15338 - ana santos - 04/08 - Inicio
	public static final Short OP_TR001S = new Short((short)201);
	public static final Short OP_TR002S = new Short((short)202);
	public static final Short OP_TR003S = new Short((short)203);
	public static final Short OP_TR004S = new Short((short)204);
	public static final Short OP_TR005S = new Short((short)205);	
	public static final Short OP_TR007S = new Short((short)207);
	public static final Short OP_TR010S = new Short((short)210);
	public static final Short OP_TR011S = new Short((short)211);
	public static final Short OP_TR012S = new Short((short)212);	
	public static final Short OP_TR013S = new Short((short)213);
	public static final Short OP_TR014S = new Short((short)214);
	public static final Short OP_TR015S = new Short((short)215);
	public static final Short OP_TR016S = new Short((short)216);
	public static final Short OP_TR017S = new Short((short)217);
	public static final Short OP_TR018S = new Short((short)218);
	public static final Short OP_TR019S = new Short((short)219);	
	public static final Short OP_TR021S = new Short((short)221);
	public static final Short OP_TR022S = new Short((short)222);
	public static final Short OP_TR023S = new Short((short)223);	
	public static final Short OP_TR031S = new Short((short)231);
	public static final Short OP_TR032S = new Short((short)232);	
	public static final Short OP_TR034S = new Short((short)234);
	public static final Short OP_TR035S = new Short((short)235);
	public static final Short OP_TR037S = new Short((short)237);
	public static final Short OP_TR039S = new Short((short)239);
	public static final Short OP_TR040S = new Short((short)240);
	public static final Short OP_TR041S = new Short((short)241);	
	public static final Short OP_TR042S = new Short((short)242);
	public static final Short OP_TR043S = new Short((short)243);
	// Se agrega OP_TR028S con valor 244 - German P.
	public static final Short OP_TR028S = new Short((short)244);
	//Cr20948 - Altamira - Operaciones que faltaban de altamira y demas PdTI, etc - guido - 22/May/2009
	public static final Short OP_TR601S = new Short((short)245);
	public static final Short OP_VERIF_PETICION = new Short((short)300);
	public static final Short OP_RECHAZO_PETICION = new Short((short)301);
	public static final Short OP_GEN_FLUJO_PET = new Short((short)302);
	public static final Short OP_REVISAR_FLUJO = new Short((short)303);
	public static final Short OP_ANULACION_ATIS_RECEPCION_PET = new Short((short)304);
	public static final Short OP_PROBLEMA_ANULACION_ECEPCION_PET = new Short((short)305);
	public static final Short OP_ESPERO_DESBLOQUEO = new Short((short)306);
	public static final Short OP_ANULACION_ATIS = new Short((short)307);
	public static final Short OP_PROBLEMA_ANULACION = new Short((short)308);
	public static final Short OP_CALC_SIG_TAREA = new Short((short)309);
	public static final Short OP_TERMINARON_PARALELAS = new Short((short)310);
	public static final Short OP_CIERRE_PETICION = new Short((short)311);
	public static final Short OP_ERROR_DIRECTOR = new Short((short)312);
	public static final Short OP_ASIG_REC_STB = new Short((short)313);
	public static final Short OP_ASIG_MANUAL_STB = new Short((short)314);
	public static final Short OP_GESTION_RECURSOS = new Short((short)315);
	public static final Short OP_CONFIG_STB = new Short((short)316);
	public static final Short OP_CONFIG_RI = new Short((short)317);
	public static final Short OP_OBT_CONFIG_BA = new Short((short)318);
	public static final Short OP_OBT_PTOS_RED_STB = new Short((short)319);
	public static final Short OP_CONFIG_INTERNET = new Short((short)320);
	public static final Short OP_CONFIG_DTH = new Short((short)321);
	public static final Short OP_INSTALAR = new Short((short)322);
	public static final Short OP_CTRL_INSTALACION = new Short((short)323);
	public static final Short OP_DESCONFIG_STB = new Short((short)324);
	public static final Short OP_DESCONFIG_RI = new Short((short)325);
	public static final Short OP_DESCONFIG_INTERNET = new Short((short)326);
	public static final Short OP_DESINSTALAR = new Short((short)327);
	public static final Short OP_CTRL_DESINSTALACION = new Short((short)328);
	public static final Short OP_DESINSTALAR_DTH = new Short((short)329);
	public static final Short OP_CTRL_DESINST_DTH = new Short((short)330);
	public static final Short OP_ACTUALIZAR_INV_STB = new Short((short)331);
	public static final Short OP_ACTUALIZAR_INV_SS = new Short((short)332);
	//public static final Short OP_ACTUALIZAR_INV_BA = new Short((short)333);// es igual a la 1067 ?????
	public static final Short OP_ACTUALIZAR_INV_DTH = new Short((short)334);
	public static final Short OP_SOL_SOPORTE_TECNICA = new Short((short)335);
	public static final Short OP_PEND_SOL_TEC_STB = new Short((short)336);
	public static final Short OP_PEND_SOL_TEC_BA = new Short((short)337);
	public static final Short OP_PEND_SOL_TEC_DTH = new Short((short)338);
	public static final Short OP_PEND_SOL_COMERCIAL = new Short((short)339);
	public static final Short OP_VERIF_PETICION_ST = new Short((short)340);
	public static final Short OP_BLOQUEO_PET_ST = new Short((short)341);
	public static final Short OP_GEN_FLUJO_PET_ST = new Short((short)342);
	public static final Short OP_REVISAR_FLUJO_ST = new Short((short)343);
	public static final Short OP_ANULACION_ATIS_ST = new Short((short)344);
	public static final Short OP_PROBLEMA_ANULACION_ST = new Short((short)345);
	public static final Short OP_CALCULAR_SIG_TAREA_ST = new Short((short)346);
	public static final Short OP_TERMINARON_PARALELAS_ST = new Short((short)347);
	public static final Short OP_CIERRE_MANUAL = new Short((short)348);
	public static final Short OP_ENVIO_CIERRE_PET = new Short((short)349);
	public static final Short OP_ERROR_DIR_ST = new Short((short)350);
	
	public static final Short OP_RECUPERAR_INFO_STB = new Short((short)351);
	public static final Short OP_RECUPERAR_INFO_BA = new Short((short)352);
	public static final Short OP_RECUPERAR_INFO_TV = new Short((short)353);
	public static final Short OP_DIAGNOSTICAR_STB = new Short((short)354);
	public static final Short OP_PLANTA_INT_STB = new Short((short)355);
	public static final Short OP_PLANTA_EXT_STB = new Short((short)356);
	public static final Short OP_RED_INTELIGENTE_STB = new Short((short)357);
	public static final Short OP_CTRL_REP_STB = new Short((short)358);
	public static final Short OP_DIAGNOSTICO_BA = new Short((short)359);
	public static final Short OP_PRIMER_NIVLE_BA = new Short((short)360);
	public static final Short OP_PLANTA_EXT_BA = new Short((short)361);
	public static final Short OP_CTRL_REAPARCION_BA = new Short((short)362);
	public static final Short OP_DIAGNOSTICO_TV = new Short((short)363);
	public static final Short OP_PLATAFORMA_TV = new Short((short)364);
	public static final Short OP_CTRL_REPARACION_TV = new Short((short)365);
	
	//public static final Short OP_ACTUALIZAR_INV_STB = new Short((short)366);
	
	public static final Short OP_ACTUALIZAR_INV_BA = new Short((short)367); // es igual a la 1033 ?????
	public static final Short OP_ACTUALIZAR_INV_TV = new Short((short)368);
	public static final Short OP_CONFIG_INPREL = new Short((short)369);
	public static final Short OP_CAMBIAR_NRO_BA = new Short((short)370);
	public static final Short OP_ANALIZAR_REC = new Short((short)371);
	public static final Short OP_OBTENER_PTOS_RED_BA = new Short((short)372);
	public static final Short OP_MATTO_ENERGIA = new Short((short)373);
	public static final Short OP_MATTO_CX = new Short((short)374);
	public static final Short OP_MATTO_TX = new Short((short)375);
	public static final Short OP_SEGUNDO_NIVEL_BA = new Short((short)376);
	public static final Short OP_MARCAR_AVERIA_STB = new Short((short)377);
	public static final Short OP_CTRL_ACT_INV_STB = new Short((short)378);
	public static final Short OP_CTRL_ACT_INV_BA = new Short((short)379);
	public static final Short OP_OBT_CONFIG_DTH = new Short((short)380);
	public static final Short OP_CTRL_ACT_INV_TV = new Short((short)381);
	public static final Short OP_CONFIG_TERRA = new Short((short)382);
	public static final Short OP_CTRL_CONFIG_TERRA = new Short((short)383);
	public static final Short OP_OBT_CONFIG_BA_TERRA = new Short((short)384);
	public static final Short OP_ESPERO_FEC_EJECUCION = new Short((short)385);
	public static final Short OP_VERIF_FEC_EJECUCION = new Short((short)386);	
	
	public static final Short OP_INSTALAR_VISTA = new Short((short)387);
	public static final Short OP_CTRL_INST_VISITA = new Short((short)388);
		
	public static final Short OP_CIERRE_PRIMARIO = new Short((short)390); // 1500
	public static final Short OP_CIERRE_PRIMARIO_TV = new Short((short)391); // 1501
	public static final Short OP_CONFIG_AULA = new Short((short)392); // 1502
	public static final Short OP_CTRL_CONFIG_AULA = new Short((short)393); // 1503
	
	// ana santos - 21 Nov - Granite - inicio
	//Operaciones para acciones masivas
	public static final Short OP_ENVIO_MASIVA_POR_USUARIO = new Short((short)394);
	public static final Short OP_ENVIO_MASIVA_POR_ARCHIVO = new Short((short)395);
	public static final Short OP_EJECUCION_MASIVA_UNITARIA = new Short((short)396);
	
	//Cr20948 - Altamira - Operaciones que faltaban de altamira y demas - guido - 22/May/2009 - INICIO
	public static final Short OP_ACT_CONF_TELEFONAUTAS 				= new Short((short)398);
	public static final Short OP_ACT_OBTENER_INV_EQUIPO 			= new Short((short)399);
	public static final Short OP_ACT_ACTUALIZAR_INVENTARIO_EQUIPO 	= new Short((short)400);
	public static final Short OP_ACT_RECUPERAR_INFO_EQUIPO 			= new Short((short)401);
	public static final Short OP_ACT_ACTUALIZAR_INV_EQUIPO 			= new Short((short)402);
	public static final Short OP_ACT_CONTROL_ACT_INV_EQUIPO 		= new Short((short)403);
	//Operaciones para actividades ALTAMIRA 
	public static final Short OP_ACT_RI_ALTA_ABONADO 				= new Short((short)404);
	public static final Short OP_ACT_RI_APROV_ALTA_ABONADO 			= new Short((short)405);
	public static final Short OP_ACT_RI_ALTA_PERIODIFICACION 		= new Short((short)406);
	public static final Short OP_ACT_RI_APROV_ALTA_PER 				= new Short((short)407);
	public static final Short OP_ACT_RI_BAJA_ABONADO				= new Short((short)408);
	public static final Short OP_ACT_RI_CAMBIO_NUMERO				= new Short((short)409);
	public static final Short OP_ACT_RI_APROV_CAMBIO_NUMERO			= new Short((short)410);
	public static final Short OP_ACT_RI_APROV_BAJA_ABONADO 			= new Short((short)411);
	public static final Short OP_ACT_RI_APROV_CAMBIO_PREPAGO 		= new Short((short)412);
	public static final Short OP_ACT_RI_CAMBIO_PREPAGO 				= new Short((short)413);
	public static final Short OP_ACT_RI_APROV_BAJA_PER 				= new Short((short)414);
	public static final Short OP_ACT_RI_BAJA_PER 					= new Short((short)415);
	//Cr20948 - Altamira - Operaciones que faltaban de altamira y demas - guido - 22/May/2009 - FIN
	
	public static final Short OP_ASIG_REC_LB = new Short((short)510);
	public static final Short OP_CONFIG_AUTOM_SERV_CENTRALES = new Short((short)511);
	public static final Short OP_ACTUALIZAR_INVENTARIO_STB = new Short((short)512);
	public static final Short OP_LIBERACION_RECURSOS = new Short((short)513);
	public static final Short OP_CONSULTA_PUNTOS_RED = new Short((short)514);
	public static final Short OP_PLAN_BA_ASOCIADO_LB = new Short((short)515);
	public static final Short OP_ACTUALIZACION_ESTADO_LB = new Short((short)516);
	public static final Short OP_ASIGNA_SESSION = new Short((short)397);
	
	// ana santos - 21 Nov - Granite - fin
	
	/*
	 * JOSE BAEZ - PRESENCIA DIGITAL
	 */
	public static final Short OP_TR054S = new Short((short)517);
	
	// CR15338 - ana santos - 04/08  - Fin
		
	//Columnas, campos a mostrar en trace y excel
	public static final Short COL_ID_TRANSACTION = new Short((short)0);
	public static final Short COL_ID_OPERATION = new Short((short)1);
	public static final Short COL_OP_NAME = new Short((short)2);
	public static final Short COL_OP_EVENT = new Short((short)3); // start, end, etc
	public static final Short COL_OP_TIME = new Short((short)4); // start, end, etc
	public static final Short COL_USER_LOGIN = new Short((short)5);
	public static final Short COL_USER_IP = new Short((short)6);
	public static final Short COL_NUM_PET_ATIEMPO = new Short((short)7); // Nro peticion generada por @Tiempo (NO Nro ATIS)
	public static final Short COL_TIPO_OPERACION = new Short((short)8);
	public static final Short COL_SQL = new Short((short)9);
	public static final Short COL_OP_PRINCIPAL = new Short((short)10);
	public static final Short COL_TR_NAME = new Short((short)11);
	public static final Short COL_NUM_PET_ATIS = new Short((short)12);
	public static final Short COL_ID_MSG = new Short((short)13);
	public static final Short COL_ERROR = new Short((short)14);
	public static final Short COL_AUTORIZA = new Short((short)15);
	public static final Short COL_CANT_REGISTROS = new Short((short)16);
	public static final Short COL_TRANS_PRINCIPAL = new Short((short)17);
	public static final Short COL_ES_OP_PRINCIPAL = new Short((short)18);
	public static final Short COL_NOM_ACTIVIDAD = new Short((short)19);
	public static final Short COL_ID_ACTIVIDAD = new Short((short)20);
	public static final Short COL_ROL_ACTIVIDAD = new Short((short)21);
	public static final Short COL_SERVER_APPLICATION = new Short((short)22);
	
	public static final Short OP_TR135S = new Short((short)135); 
	
	
	
	/**
	 * Este metodo crea la operacion (si esta deshabilitada devuelve DisabledTraceOperation), y le setea el CurrentExecution
	 * obtenido del current thread
	 */
	public TraceOperation newOperation(Short operationId,CurrentExecution currentExecution);
	public TraceOperation newOperation(Short columnId);
	public CurrentExecution newCurrentExecution();
	public boolean isTraceDisabled();
	
	public void traceOpStart(TraceOperation operation);
	public void traceOpEnd(TraceOperation operation);
	public void closeCurrentExecution(CurrentExecution cExecution);
}
