/*
 * Created on Jul 30, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.telefonica_chile.atiempo.trace.core;

import java.util.HashMap;

import com.telefonica_chile.atiempo.trace.api.TraceManager;

/**
 * @author 805538
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public final class TraceOperatiosDef {
	/**
	 * Retorna el orden de las columnas como deben aparecer en el trace
	 * @return
	 */
	
	private static final Short[] criterio = {		
		TraceManager.COL_ID_TRANSACTION, 
		TraceManager.COL_ID_OPERATION, 
		TraceManager.COL_OP_NAME,
		TraceManager.COL_OP_EVENT,
		TraceManager.COL_OP_TIME , 
		TraceManager.COL_USER_LOGIN , 
		TraceManager.COL_USER_IP ,	
		TraceManager.COL_NUM_PET_ATIEMPO,
		TraceManager.COL_TIPO_OPERACION,		
		TraceManager.COL_SQL,	
		TraceManager.COL_CANT_REGISTROS,
		TraceManager.COL_TR_NAME,
		TraceManager.COL_NUM_PET_ATIS,		
		TraceManager.COL_ERROR,
		TraceManager.COL_ID_MSG,		
		TraceManager.COL_OP_PRINCIPAL,
		TraceManager.COL_TRANS_PRINCIPAL,
		TraceManager.COL_ES_OP_PRINCIPAL,
		TraceManager.COL_AUTORIZA, 
		TraceManager.COL_NOM_ACTIVIDAD,
		TraceManager.COL_ID_ACTIVIDAD, 
		TraceManager.COL_ROL_ACTIVIDAD,
		TraceManager.COL_SERVER_APPLICATION
		 };
	private static final HashMap col_titles = new HashMap();
	private static final HashMap op_names = new HashMap();
	
	public static void start(){
		col_titles.put(TraceManager.COL_ID_OPERATION, "ID OPERACION");
		col_titles.put(TraceManager.COL_ID_TRANSACTION, "ID TRANSACCION");
		col_titles.put(TraceManager.COL_OP_NAME, "NOMBRE DE LA OPERACION");
		col_titles.put(TraceManager.COL_OP_TIME, "TIEMPO (ms)");
		col_titles.put(TraceManager.COL_NUM_PET_ATIEMPO, "NUM PET ATIEMPO");
		col_titles.put(TraceManager.COL_TIPO_OPERACION, "TIPO OPERACION");
		col_titles.put(TraceManager.COL_SQL, "SQL");
		col_titles.put(TraceManager.COL_USER_IP, "IP USUARIO");
		col_titles.put(TraceManager.COL_USER_LOGIN, "USUARIO");
		col_titles.put(TraceManager.COL_OP_EVENT, "EVENTO");
		col_titles.put(TraceManager.COL_ID_OPERATION, "ID OPERACION");
		col_titles.put(TraceManager.COL_OP_PRINCIPAL, "OP. PRINCIPAL");
		col_titles.put(TraceManager.COL_AUTORIZA, "AUTORIZA RECURSO");
		col_titles.put(TraceManager.COL_CANT_REGISTROS, "CANTIDAD DE REGISTROS");
		col_titles.put(TraceManager.COL_TRANS_PRINCIPAL, "TRANSACCION PRINCIPAL");
		col_titles.put(TraceManager.COL_ES_OP_PRINCIPAL, "ES OP PRINCIPAL?");
	   	col_titles.put(TraceManager.COL_TR_NAME, "NOMBRE TR");
	   	col_titles.put(TraceManager.COL_NUM_PET_ATIS, "NUM PET ATIS");
	   	col_titles.put(TraceManager.COL_ERROR, "ERROR");
	   	col_titles.put(TraceManager.COL_ID_MSG,"ID MENSAJE");
	   	col_titles.put(TraceManager.COL_SERVER_APPLICATION,"SERVER APPLICATION");
	   	
		col_titles.put(TraceManager.COL_NOM_ACTIVIDAD,"NOMBRE ACTIVIDAD");
		col_titles.put(TraceManager.COL_ID_ACTIVIDAD,"ID ACTIVIDAD");
		col_titles.put(TraceManager.COL_ROL_ACTIVIDAD,"ROL ACTIVIDAD");
	   	
	   	
		op_names.put(TraceManager.OP_VER_BANDEJA_PRINCIPAL , "Ver Bandeja Principal");
		op_names.put(TraceManager.OP_ACTIVAR_DECO_TARJETA , "Activar deco-tarjeta");
		op_names.put(TraceManager.OP_ACTIVAR_DECO_TARJETA_CI , "Activar deco-tarjeta CI");
		op_names.put(TraceManager.OP_ACTUALIZAR_INVENTARIO_DTHCI , "Actualizar inventario DTHCI");
	   	op_names.put(TraceManager.OP_BUSCADOR_PETICION_CO , "Buscador de peticiones");
	   	op_names.put(TraceManager.OP_BUSCAR_DECO_TARJETA , "Buscador deco-tarjeta");
	   	op_names.put(TraceManager.OP_BUSCAR_DECO_TARJETA_CI , "Buscador deco-tarjeta CI");
	   	op_names.put(TraceManager.OP_CAMBIAR_CLAVE, "Cambiar clave");
	   	op_names.put(TraceManager.OP_CAMBIO_AGENDA, "Cambio agenda");
	   	op_names.put(TraceManager.OP_CAMBIO_TECNICO, "Cambio Tecnico");
	   	op_names.put(TraceManager.OP_CONSULTA_MODEMS, "Consulta modems");
	   	op_names.put(TraceManager.OP_DESPLIEGA_BUSCADOR_HISTORICO_VPI, "Buscador historico Vpi");
	   	op_names.put(TraceManager.OP_DESPLIEGA_PANTALLA_CO, "Despliega Peticion");
	   	op_names.put(TraceManager.OP_FRAME_DECO_TAR_CI, "Deco-tarjeta CI");
	   	op_names.put(TraceManager.OP_FRAME_DECO_TAR_MIGRACION_CI, "Deco-tarjeta migracion CI");
	   	op_names.put(TraceManager.OP_GRABA_PANTALLA_CO, "Graba Pantalla");
	   	op_names.put(TraceManager.OP_IMPRESION_ODT_CDS, "Impresion ODT CDS");
	   	op_names.put(TraceManager.OP_IMPRESION_ODT_CO, "Impresion ODT CO");
	   	op_names.put(TraceManager.OP_LISTADO_MASIVO, "Listado Masivo");
	   	op_names.put(TraceManager.OP_LISTADO_TECNICOS, "Listado tecnicos");
	   	op_names.put(TraceManager.OP_LOG_OFF, "Log off");
	   	op_names.put(TraceManager.OP_MARCO_TORRE_CONTROL, "Marco torre de control");
	    op_names.put(TraceManager.OP_PETICIONES_SUPERVISADO, "Peticiones supervis");
	    op_names.put(TraceManager.OP_RECUPERAR_CLAVE, "Recuperar Clave");
	    op_names.put(TraceManager.OP_REFRESCA_DATOS_BA, "Refresca datos BA");
	    op_names.put(TraceManager.OP_REFRESCA_DATOS_TECNICOS, "Refresca datos tecnicos");
	    op_names.put(TraceManager.OP_REFRESH_PAR_DECO, "Refresca par deco-tarjeta");
	    op_names.put(TraceManager.OP_RESULTADO_ACTIVACION_DECOS, "Resultado Activacion decos");
	    op_names.put(TraceManager.OP_UPLOAD_FILE, "Upload file");
	    op_names.put(TraceManager.OP_VER_BANDEJA_BACK_OC, "Ver Bandeja Back OC");
	    op_names.put(TraceManager.OP_VER_BANDEJA_ESPERA_BA, "Ver Bandeja Espera BA");
	    op_names.put(TraceManager.OP_VER_BANDEJA_ESPERA_TERRA, "Ver Bandeja Espera Terra");
	    op_names.put(TraceManager.OP_VER_BANDEJA_PRINCIPAL, "Ver Bandeja Principal");
	    op_names.put(TraceManager.OP_VER_BANDEJA_SUP_CO, "Ver Bandeja Sup Co");
	    op_names.put(TraceManager.OP_VER_BUSCADOR_GES_OS, "Ver Buscador Ges Os");
	    op_names.put(TraceManager.OP_VER_CONTROL_TECNICO, "Ver Control Tecnico");
	    op_names.put(TraceManager.OP_VER_FRAME_REPORTE_TERRENO, "Ver Reporte Terreno");
	    op_names.put(TraceManager.OP_VER_GESTION_INBOUND, "Ver Gestion Inbound");
	    op_names.put(TraceManager.OP_VER_GESTION_OUTBOUND, "Ver Gestion Outbound");
	    op_names.put(TraceManager.OP_VER_INSTALAR_EQUIPOS, "Ver Instalar Equipos");
	    op_names.put(TraceManager.OP_VER_NUEVA_BANDEJA_TORRE_CONTROL, "Ver Nueva bandeja torre de control");
	    op_names.put(TraceManager.OP_VER_NUEVA_TORRE_CONTROL, "Ver Nueva torre de control");
	    op_names.put(TraceManager.OP_VER_PTVPI, "Ver PTVPI");
	    op_names.put(TraceManager.OP_VER_REPORTE_TERRENO, "Ver Reporte Terreno");
	    op_names.put(TraceManager.OP_VER_UPLOAD_FILE, "Ver Upload File");
	    op_names.put(TraceManager.OP_VERIFICA_FECHAS, "Verifica fechas");
	    op_names.put(TraceManager.OP_VOLVER_BANDEJA, "Volver Bandeja");
	    op_names.put(TraceManager.OP_VOLVER_BANDEJA_SUPERVISADO, "Volver bandeja supervisado");
	    op_names.put(TraceManager.OP_AUTORIZACION_BANDEJA , "Autorizacion bandeja");  
	    op_names.put(TraceManager.OP_AUTORIZACION_VPI,"Autorizacion vpi");
	    op_names.put(TraceManager.OP_USUARIO_WEB,"Usuario web");
        op_names.put(TraceManager.OP_FILTRO , "SQL Bandeja");
	    op_names.put(TraceManager.OP_SQL_FILTRO_COUNT , "SQL Bandeja (count)");
	    op_names.put(TraceManager.OP_SQL_TORRE_CONTROL, "SQL torre de control");
	    op_names.put(TraceManager.OP_SQL_ESPERA_BA, "SQL espera ba");
	    op_names.put(TraceManager.OP_SQL_ESPERA_BA_COUNT, "SQL espera ba (count)");
	    op_names.put(TraceManager.OP_SQL_ESPERA_TERRA, "SQL espera terra");
	    op_names.put(TraceManager.OP_SQL_ESPERA_TERRA_COUNT, "SQL espera terra (count)");
	   
	    op_names.put(TraceManager.OP_DBMANAGER_CONSULTA_HASH , "DBManager consulta hash");
	    op_names.put(TraceManager.OP_DBMANAGER_EJECUTA , "DBManager ejecuta");
	    op_names.put(TraceManager.OP_DBMANAGER_SELECT_COMMIT , "DBManager select commit");
	    op_names.put(TraceManager.OP_DBMANAGER_SELECT_COMMIT_PREPARED , "DBManager select commit prepared");
	    op_names.put(TraceManager.OP_DBMANAGER_SELECT_READ_UNCOMMIT , "DBManager select read uncommit");
	    op_names.put(TraceManager.OP_DBMANAGER_UPDATE , "DBManager update");
	   
	   	   
	    op_names.put(TraceManager.OP_UPLOAD_FILE_CLASS, "Actividad upload file");
	    op_names.put(TraceManager.OP_SQL_GOUTBOUND, "SQL gestion outbound");
	    op_names.put(TraceManager.OP_SQL_GINBOUND, "SQL gestion inbound");
	    op_names.put(TraceManager.OP_SQL_TORRE_FILTRO, "SQL torre filtro");
	   
	    // operaciones para las TRs
	    op_names.put(TraceManager.OP_TR001S, "Ingreso de petición");	  	
	    op_names.put(TraceManager.OP_TR002S, "Liberación de recursos STB");
	    op_names.put(TraceManager.OP_TR003S, "Asignación Manual");
	    op_names.put(TraceManager.OP_TR004S, "Actualización inventario STB");
	    op_names.put(TraceManager.OP_TR005S, "Armar incidente @Tiempo");		
	    op_names.put(TraceManager.OP_TR007S, "Actualización inventario BA");		
	    op_names.put(TraceManager.OP_TR010S, "Asignación STB");
	    op_names.put(TraceManager.OP_TR011S, "Actualización SSs STB");
	    op_names.put(TraceManager.OP_TR012S, "Puntos de red STB");
	    op_names.put(TraceManager.OP_TR013S, "Configuración Internet");
	    op_names.put(TraceManager.OP_TR014S, "Configuración actual BA");
	    op_names.put(TraceManager.OP_TR015S, "Cambio de numero");
	    op_names.put(TraceManager.OP_TR016S, "Alistar equipos DTH - Gestionar DECOS");
	    op_names.put(TraceManager.OP_TR017S, "Configuración DTH");
	    op_names.put(TraceManager.OP_TR018S, "Actualización inventario DTH");
	    op_names.put(TraceManager.OP_TR019S, "Configuración actual DTH");		
	    op_names.put(TraceManager.OP_TR021S, "Actualización de estado técnico STB");
	    op_names.put(TraceManager.OP_TR022S, "Listar Modems");
	    op_names.put(TraceManager.OP_TR023S, "Aprovisionamiento de productos Terra");		
	    op_names.put(TraceManager.OP_TR031S, "Respuesta reserva recursos BA");
	    op_names.put(TraceManager.OP_TR032S, "Respuesta activación recursos BA");		
	    op_names.put(TraceManager.OP_TR034S, "Resultado final activación recursos BA");
	    op_names.put(TraceManager.OP_TR035S, "Solicitar configuracion actual BA");
	    op_names.put(TraceManager.OP_TR037S, "Respuesta confirmación baja BA");
	    op_names.put(TraceManager.OP_TR039S, "Respuesta reserva modificación BA");
	    op_names.put(TraceManager.OP_TR040S, "Respuesta activación modificación BA");	
	    op_names.put(TraceManager.OP_TR041S, "Respuesta modificación servicio BA");			
	    op_names.put(TraceManager.OP_TR042S, "Suspensión reconexión BA");
	    op_names.put(TraceManager.OP_TR043S, "Solicitar cuenta correo BA");	
		op_names.put(TraceManager.OP_TR601S, "RI - Respuesta Altamira");
	    
	    // Se agrega nombre para TR028S - German P. - 31/07/09
	    op_names.put(TraceManager.OP_TR028S, "Recepción de actualización de inventario de equipos" );
	    
		op_names.put(TraceManager.OP_VERIF_PETICION, "Verificar petición"); 
		op_names.put(TraceManager.OP_RECHAZO_PETICION, "Rechazo petición");
		op_names.put(TraceManager.OP_GEN_FLUJO_PET, "Generar flujo petición");
		op_names.put(TraceManager.OP_REVISAR_FLUJO, "Revisar flujo");
	
		op_names.put(TraceManager.OP_ESPERO_DESBLOQUEO, "Espero desbloqueo");
	
	
		op_names.put(TraceManager.OP_CALC_SIG_TAREA, "Calcular siguiente tarea");
		op_names.put(TraceManager.OP_TERMINARON_PARALELAS, "Terminaron paralelas");
		op_names.put(TraceManager.OP_CIERRE_PETICION, "Cierre petición");
		op_names.put(TraceManager.OP_ERROR_DIRECTOR, "Error director");
		op_names.put(TraceManager.OP_ASIG_REC_STB, "Asignacion recursos STB");
		op_names.put(TraceManager.OP_ASIG_MANUAL_STB, "Asignacion manual STB");
		op_names.put(TraceManager.OP_GESTION_RECURSOS, "Gestion recursos");
		op_names.put(TraceManager.OP_CONFIG_STB, "Configurar STB"); 
	
	
		op_names.put(TraceManager.OP_CONFIG_RI, "Configurar RI");  
	
		op_names.put(TraceManager.OP_OBT_CONFIG_BA, "Configurar BA"); 
		op_names.put(TraceManager.OP_OBT_PTOS_RED_STB, "Obtener puntos de red STB");  
		op_names.put(TraceManager.OP_CONFIG_INTERNET, "Configurar internet"); 
		op_names.put(TraceManager.OP_CONFIG_DTH, "Configurar DTH");  
		op_names.put(TraceManager.OP_INSTALAR, "Instalar");  
		op_names.put(TraceManager.OP_CTRL_INSTALACION, "Control instalación");  
		op_names.put(TraceManager.OP_DESCONFIG_STB, "Desconfigurar STB");  
		op_names.put(TraceManager.OP_DESCONFIG_RI, "Desconfigurar RI");  
		op_names.put(TraceManager.OP_DESCONFIG_INTERNET, "Desconfigurar internet"); 
		op_names.put(TraceManager.OP_DESINSTALAR, "Desinstalar"); 
		op_names.put(TraceManager.OP_CTRL_DESINSTALACION, "Control desinstalacion"); 
		op_names.put(TraceManager.OP_DESINSTALAR_DTH, "Desinstalar DTH");  
		op_names.put(TraceManager.OP_CTRL_DESINST_DTH, "Control desinstalación DTH"); 
		op_names.put(TraceManager.OP_ACTUALIZAR_INV_STB, "Actualizar inventario STB"); 
		op_names.put(TraceManager.OP_ACTUALIZAR_INV_SS, "Actualizar inventario SS");  
	
	
		op_names.put(TraceManager.OP_ACTUALIZAR_INV_DTH, "Actualizar inventario DTH"); 
		op_names.put(TraceManager.OP_SOL_SOPORTE_TECNICA, "Solucion y soporte tecnica"); 
		op_names.put(TraceManager.OP_PEND_SOL_TEC_STB, "Pendiente solución técnica STB"); 
		op_names.put(TraceManager.OP_PEND_SOL_TEC_BA, "Pendiente solución técnica BA"); 
		op_names.put(TraceManager.OP_PEND_SOL_TEC_DTH, "Pendiente solución tecnica DTH"); 
		op_names.put(TraceManager.OP_PEND_SOL_COMERCIAL, "Pendiente solución comercial"); 
	
		op_names.put(TraceManager.OP_CIERRE_MANUAL, "Cierre manual"); 
		op_names.put(TraceManager.OP_ENVIO_CIERRE_PET, "Envio cierre petición"); 
	
	
		op_names.put(TraceManager.OP_RECUPERAR_INFO_STB, "Recuperar información STB"); 
		op_names.put(TraceManager.OP_RECUPERAR_INFO_BA, "Recuperar información BA"); 
		op_names.put(TraceManager.OP_RECUPERAR_INFO_TV, "Recuperar información TV"); 
		op_names.put(TraceManager.OP_DIAGNOSTICAR_STB, "Diagnosticar STB"); 
		op_names.put(TraceManager.OP_PLANTA_INT_STB, "Planta interna STB"); 
		op_names.put(TraceManager.OP_PLANTA_EXT_STB, "Planta externa STB"); 
		op_names.put(TraceManager.OP_RED_INTELIGENTE_STB, "Red inteligente STB"); 
		op_names.put(TraceManager.OP_CTRL_REP_STB , "Control reaparación STB");
		op_names.put(TraceManager.OP_DIAGNOSTICO_BA, "Diagnóstico BA"); 
		op_names.put(TraceManager.OP_PRIMER_NIVLE_BA, "Primer nivel BA");
		op_names.put(TraceManager.OP_PLANTA_EXT_BA, "Planta externa BA"); 
		op_names.put(TraceManager.OP_CTRL_REAPARCION_BA, "Control reparación BA"); 
		op_names.put(TraceManager.OP_DIAGNOSTICO_TV, "Diagnóstico TV"); 
		op_names.put(TraceManager.OP_PLATAFORMA_TV, "Plataforma TV"); 
		op_names.put(TraceManager.OP_CTRL_REPARACION_TV, "Control reparación TV"); 
	
	
		op_names.put(TraceManager.OP_ACTUALIZAR_INV_BA, "Actualizar inventario BA");  
		op_names.put(TraceManager.OP_ACTUALIZAR_INV_TV, "Actualizar inventario TV"); 
		op_names.put(TraceManager.OP_CONFIG_INPREL, "Configurar INPREL"); 
		op_names.put(TraceManager.OP_CAMBIAR_NRO_BA, "Cambiar número BA");
		op_names.put(TraceManager.OP_ANALIZAR_REC, "Analizar recursos"); 
		op_names.put(TraceManager.OP_OBTENER_PTOS_RED_BA, "Obtener puntos de red BA"); 
		op_names.put(TraceManager.OP_MATTO_ENERGIA, "Matto energía"); 
		op_names.put(TraceManager.OP_MATTO_CX, "Matto CX"); 
		op_names.put(TraceManager.OP_MATTO_TX, "Matto TX"); 
		op_names.put(TraceManager.OP_SEGUNDO_NIVEL_BA, "Segundo nivel BA"); 
		op_names.put(TraceManager.OP_MARCAR_AVERIA_STB, "Marcar avería BA"); 
		op_names.put(TraceManager.OP_CTRL_ACT_INV_STB, "Control actividad "); 
		op_names.put(TraceManager.OP_CTRL_ACT_INV_BA, "Control actualizar inventario BA"); 
		op_names.put(TraceManager.OP_OBT_CONFIG_DTH, "Obtener configuración DTH");
		op_names.put(TraceManager.OP_CTRL_ACT_INV_TV, "Control actualizar inventario TV"); 
	
		op_names.put(TraceManager.OP_ESPERO_FEC_EJECUCION, "Espero fecha ejecución"); 
		op_names.put(TraceManager.OP_VERIF_FEC_EJECUCION, "Verificar fecha ejecución"); 
	
		op_names.put(TraceManager.OP_OBT_CONFIG_BA_TERRA, "Obtener configuración BA Terra"); 
	
		op_names.put(TraceManager.OP_CTRL_INST_VISITA , "Control instalación visita");
	
		op_names.put(TraceManager.OP_INSTALAR_VISTA, "Instalar visita"); 
		op_names.put(TraceManager.OP_CONFIG_TERRA, "Configuración Terra"); 
		op_names.put(TraceManager.OP_CTRL_CONFIG_TERRA, "Control configuración Terra"); 
	
		op_names.put(TraceManager.OP_CONFIG_AULA, "Configuración Aula"); 
		op_names.put(TraceManager.OP_CTRL_CONFIG_AULA, "Control configuración Aula");
		
		// ana santos - 21 Nov - Granite - inicio
		
		op_names.put(TraceManager.OP_ASIG_REC_LB, "Asignación recursos LB");
		op_names.put(TraceManager.OP_CONFIG_AUTOM_SERV_CENTRALES, "Configuración automática servicios centrales");
		op_names.put(TraceManager.OP_ACTUALIZAR_INVENTARIO_STB, "Actualizar inventario STB");
		op_names.put(TraceManager.OP_LIBERACION_RECURSOS, "Liberación de recursos");
		op_names.put(TraceManager.OP_CONSULTA_PUNTOS_RED, "Consulta puntos de red");
		op_names.put(TraceManager.OP_PLAN_BA_ASOCIADO_LB, "Plan BA asociado a LB");
		op_names.put(TraceManager.OP_ACTUALIZACION_ESTADO_LB, "Actualización estado LB");
		
		// ana santos - 21 Nov - Granite - fin
		
		op_names.put(TraceManager.OP_ENVIO_MASIVA_POR_USUARIO, "Envio masiva por usuario");  
		op_names.put(TraceManager.OP_ENVIO_MASIVA_POR_ARCHIVO, "Envio masiva por archivo");  
		op_names.put(TraceManager.OP_EJECUCION_MASIVA_UNITARIA, "Ejecución masiva unitaria");  
		
		op_names.put(TraceManager.OP_ASIGNA_SESSION, "Algoritmo de asignación");  
		op_names.put(TraceManager.OP_ASIGNA_USUARIO, "Asignación usuario Normal"); 
		op_names.put(TraceManager.OP_ASIGNA_USUARIO_SUP, "Asignación usuario Supervisor"); 

		op_names.put(TraceManager.OP_DEFECTO, "Default Operation");
		
		//Cr20948 - Altamira - Operaciones que faltaban de altamira y demas PdTI, etc - guido - 22/May/2009 - INICIO
		op_names.put(TraceManager.OP_ACT_CONF_TELEFONAUTAS 				, "Configurar Telefonautas");
		op_names.put(TraceManager.OP_ACT_OBTENER_INV_EQUIPO 			, "Obtener Inventario Equipo");
		op_names.put(TraceManager.OP_ACT_ACTUALIZAR_INVENTARIO_EQUIPO 	, "Actualizar Inventario Equipo");
		op_names.put(TraceManager.OP_ACT_RECUPERAR_INFO_EQUIPO 			, "Recuperar Informacion Equipo");
		op_names.put(TraceManager.OP_ACT_ACTUALIZAR_INV_EQUIPO 			, "Actualizar Inv Equipo");
		op_names.put(TraceManager.OP_ACT_CONTROL_ACT_INV_EQUIPO 		, "Control Actualizar Inventario Equipo");
		//Operaciones para actividades ALTAMIRA
		op_names.put(TraceManager.OP_ACT_RI_ALTA_ABONADO 				, "RI - Alta Abonado");
		op_names.put(TraceManager.OP_ACT_RI_APROV_ALTA_ABONADO 			, "RI - Aprov. Alta Abonado");
		op_names.put(TraceManager.OP_ACT_RI_ALTA_PERIODIFICACION 		, "RI - Alta Periodificación");
		op_names.put(TraceManager.OP_ACT_RI_APROV_ALTA_PER 				, "RI - Aprov. Alta Periodificación");
		op_names.put(TraceManager.OP_ACT_RI_BAJA_ABONADO				, "RI - Baja Abonado");
		op_names.put(TraceManager.OP_ACT_RI_CAMBIO_NUMERO				, "RI - Cambio de Número");
		op_names.put(TraceManager.OP_ACT_RI_APROV_CAMBIO_NUMERO			, "RI - Aprov. Cambio Número");
		op_names.put(TraceManager.OP_ACT_RI_APROV_BAJA_ABONADO 			, "RI - Aprov. Baja Abonado");
		op_names.put(TraceManager.OP_ACT_RI_APROV_CAMBIO_PREPAGO 		, "RI - Aprov. Cambio Prepago");
		op_names.put(TraceManager.OP_ACT_RI_CAMBIO_PREPAGO 				, "RI - Cambio Prepago");
		op_names.put(TraceManager.OP_ACT_RI_APROV_BAJA_PER 				, "RI - Aprov. Baja Periodificación");
		op_names.put(TraceManager.OP_ACT_RI_BAJA_PER 					, "RI - Baja Periodificación");
		//Cr20948 - Altamira - Operaciones que faltaban de altamira y demas - guido - 22/May/2009 - FIN 
		
	 }
	 
	
	public static final Short[] getColumnsOrder() {		
		return criterio;
	}
	public static final String getOperationName(Short operationId) {
		Object opName = op_names.get(operationId);
		if(opName == null){
			return "OpNameGenerated_" + operationId;
		}
		return opName.toString();
	}
	public static final String getColumnName(Short columnId) {
		Object titCol = col_titles.get(columnId);
		if(titCol == null)
			return "";
		return titCol.toString();
	}

}
