/*
 * Created on 03-05-2005
 *
 */
package com.telefonica_chile.utiles.tablesNames;

import com.telefonica_chile.bandeja.ejbutiles.Boletero;

/**
 * @author M.Alarcón "Donovan"
 *
 */
public class TablesNames {

	/**
	 * Usen esta clase para obtener los nombres de las tablas, cuando llamen al boletero.
	 * Si no quieren llamar directamente al Boletero, usen el método getIdAutoincremental de esta misma clase
	 * PD: traten de dejar bien ordenados los nombres (ordénenlos alfabeticamente, separados por Aliases)
	 */
	
	// Tablas del Alias CATALOGO_TECNICO
	public static final String CATALAGO_TECNICO_ACCESO_FISICO = "CATALAGO_TECNICO.ACCESO_FISICO";
	public static final String CATALAGO_TECNICO_AGREGACION = 	"CATALAGO_TECNICO.AGREGACION";
	public static final String CATALAGO_TECNICO_CATEGORIA = 	"CATALAGO_TECNICO.CATEGORIA";
	
	public static final String CATALAGO_TECNICO_FAMILIA = 		"CATALAGO_TECNICO.FAMILIA";
	public static final String CATALAGO_TECNICO_PRODUCTO_SERVICIO = "CATALAGO_TECNICO.PRODUCTO_SERVICIO";

	public static final String CATALAGO_TECNICO_TIPO_CX = 		"CATALAGO_TECNICO.TIPO_CX";
	
	public static final String CATALAGO_TECNICO_UNIDAD_COBRO = 	"CATALAGO_TECNICO.UNIDAD_COBRO";
	public static final String CATALAGO_TECNICO_VELOCIDAD = 	"CATALAGO_TECNICO.VELOCIDAD";
	// Fin tablas del Alias CATALOGO_TECNICO
	
	
	// Tablas del Alias VPISSDD
	public static final String VPISSDD_ACCESO = 				"VPISSDD.ACCESO";
	public static final String VPISSDD_ACCESO_TERRENO = 		"VPISSDD.ACCESO_TERRENO";
	public static final String VPISSDD_ATIS =			 		"VPISSDD.ATIS";	
	public static final String VPISSDD_APLICACION = 			"VPISSDD.APLICACION";
	public static final String VPISSDD_BASTIDOR = 				"VPISSDD.BASTIDOR";
	public static final String VPISSDD_BITACORA = 				"VPISSDD.BITACORA";
	public static final String VPISSDD_BITACORA_REVERSA_FLUJOS= "VPISSDD.BITACORA_REVERSA_FLUJOS";
	public static final String VPISSDD_CABLE = 					"VPISSDD.CABLE";
	public static final String VPISSDD_CABLE_ENLACE = 			"VPISSDD.CABLE_ENLACE";
	public static final String VPISSDD_CENTRO_PRIMARIO = 		"VPISSDD.CENTRO_PRIMARIO";
	public static final String VPISSDD_CLIENTE = 				"VPISSDD.CLIENTE";
	public static final String VPISSDD_COBERTURA_COBRE = 		"VPISSDD.COBERTURA_COBRE";
	public static final String VPISSDD_COBERTURA_FIBRA = 		"VPISSDD.COBERTURA_FIBRA";
	public static final String VPISSDD_CONFIG_LOCAL_CTC =		"VPISSDD.CONFIG_LOCAL_CTC";
	public static final String VPISSDD_CONFIGURACION_ACCESO_SIMETRICO = "VPISSDD.CONFIGURACION_ACCESO_SIMETRICO";
	public static final String VPISSDD_CONFIGURACION_FIBRAOPTICA = "VPISSDD.CONFIGURACION_FIBRAOPTICA";
	public static final String VPISSDD_CONFIGURACION_HDSL =		"VPISSDD.CONFIGURACION_HDSL";
	public static final String VPISSDD_CONFIGURACION_NEWBRIDGE ="VPISSDD.CONFIGURACION_NEWBRIDGE";
	public static final String VPISSDD_CUBICACION = 			"VPISSDD.CUBICACION";
	public static final String VPISSDD_DATOS_CONFIGURACION = 	"VPISSDD.DATOS_CONFIGURACION";
	public static final String VPISSDD_DATOS_FORM_NUM_INTERCX =	"VPISSDD.DATOS_FORM_NUM_INTERCX";	
	public static final String VPISSDD_DF = 					"VPISSDD.DF";
	public static final String VPISSDD_ENCUESTA_SATISFACCION = 	"VPISSDD.ENCUESTA_SATISFACCION";
	public static final String VPISSDD_EQU_RETIRADOS_ACCESO_SIMETRICO = "VPISSDD.EQU_RETIRADOS_ACCESO_SIMETRICO";
	public static final String VPISSDD_EQUIPO = 				"VPISSDD.EQUIPO";
	public static final String VPISSDD_EQUIPO_ADSL = 			"VPISSDD.EQUIPO_ADSL";
	public static final String VPISSDD_EQUIPO_CLIENTE = 		"VPISSDD.EQUIPO_CLIENTE";
	public static final String VPISSDD_ES_TRAFICO_ENT = 		"VPISSDD.ES_TRAFICO_ENT";
	public static final String VPISSDD_ESP_FIS_INTERCX = 		"VPISSDD.ESP_FIS_INTERCX";
	public static final String VPISSDD_FIBRA_OPTICA = 			"VPISSDD.FIBRA_OPTICA";
	public static final String VPISSDD_FICHA_CONF_RUTA =		"VPISSDD.FICHA_CONF_RUTA";
	public static final String VPISSDD_FO_ACIO = 				"VPISSDD.FO_ACIO";
	public static final String VPISSDD_FO_ASIGNACION_EQUIPO =	"VPISSDD.FO_ASIGNACION_EQUIPO";
	public static final String VPISSDD_FO_FIBRAS_EXISTENTES =	"VPISSDD.FO_FIBRAS_EXISTENTES";
	public static final String VPISSDD_FO_INGENIERO = 			"VPISSDD.FO_INGENIERO";
	public static final String VPISSDD_FO_INGENIERIA_CONSTRUCCION = "VPISSDD.FO_INGENIERIA_CONSTRUCCION";
	public static final String VPISSDD_FO_MATERIAL_ENTREGADO =	"VPISSDD_FO_MATERIAL_ENTREGADO";
	public static final String VPISSDD_FO_ORDEN_DE_EJECUCION = 	"VPISSDD.FO_ORDEN_DE_EJECUCION";
	public static final String VPISSDD_FRAME_RELAY =			"VPISSDD.FRAME_RELAY";
	public static final String VPISSDD_INFORMACION_MEDICION = 	"VPISSDD.INFORMACION_MEDICION";
	public static final String VPISSDD_INSTALACION_EQUIPOS = 	"VPISSDD.INSTALACION_EQUIPOS";
	public static final String VPISSDD_INTERCENTRALES_FIBRA =	"VPISSDD.INTERCENTRALES_FIBRA";
	public static final String VPISSDD_INTERLOCUTOR =			"VPISSDD.INTERLOCUTOR";
	public static final String VPISSDD_NUMERACION_INTERCONEXIONES =	"VPISSDD.NUMERACION_INTERCONEXIONES";
	public static final String VPISSDD_OBSERVACION_FIBRA = 		"VPISSDD.OBSERVACION_FIBRA";
	public static final String VPISSDD_PERSONA = 				"VPISSDD.PERSONA";
	public static final String VPISSDD_PROYECTO = 				"VPISSDD.PROYECTO";
	public static final String VPISSDD_PRUE_PTO_PTO_TX =		"VPISSDD.PRUE_PTO_PTO_TX";
	public static final String VPISSDD_PRUEBA_TRAMA_NUM_INTERCX = "VPISSDD.PRUEBA_TRAMA_NUM_INTERCX";
	public static final String VPISSDD_PRUEBA_TRAMA_NUM_LOCAL =	"VPISSDD.PRUEBA_TRAMA_NUM_LOCAL";
	public static final String VPISSDD_PUERTO = 				"VPISSDD.PUERTO";
	public static final String VPISSDD_QUIEBRE =				"VPISSDD.QUIEBRE";
	public static final String VPISSDD_RECURSO_DE_RED = 		"VPISSDD.RECURSO_DE_RED";
	public static final String VPISSDD_RECURSOS_CX =			"VPISSDD.RECURSOS_CX";
	public static final String VPISSDD_REGISTRO_NUM_INTERCX = 	"VPISSDD.REGISTRO_NUM_INTERCX";
	public static final String VPISSDD_RENTAS = 				"VPISSDD.RENTAS";
	public static final String VPISSDD_RESPUESTA_CLIENTE =		"VPISSDD.RESPUESTA_CLIENTE";
	public static final String VPISSDD_RESPUESTARECHAZO = 		"VPISSDD.RESPUESTARECHAZO";
	public static final String VPISSDD_RESPUESTASINCOBERTURA = 	"VPISSDD.RESPUESTASINCOBERTURA";
	//public static final String VPISSDD_SERVICIO = 				"VPISSDD.SERVICIO";
	public static final String VPISSDD_SERVICIO_COMPLEMENTARIO = "VPISSDD.SERVICIO_COMPLEMENTARIO";
	public static final String VPISSDD_SERVICIO_OPERADORA =		"VPISSDD.SERVICIO_OPERADORA";
	public static final String VPISSDD_SERVICIO_SIMETRICOS = 	"VPISSDD.SERVICIO_SIMETRICOS";
	public static final String VPISSDD_SOL_EVA_TEC = 			"VPISSDD.SOL_EVA_TEC";
	public static final String VPISSDD_SOLI_EVA_TECNICA =		"VPISSDD.SOLI_EVA_TECNICA";
	public static final String VPISSDD_SOLICITUD = 				"VPISSDD.SOLICITUD";
	public static final String VPISSDD_SOLICITUD_EQUIPO =		"VPISSDD.SOLICITUD_EQUIPO";
	public static final String VPISSDD_TABLA_OTEE =				"VPISSDD.TABLA_OTEE";
	public static final String VPISSDD_TARJETA = 				"VPISSDD.TARJETA";
	public static final String VPISSDD_TARJETA_ASIGNADA = 		"VPISSDD.TARJETA_ASIGNADA";
	public static final String VPISSDD_TECNOLOGIA = 			"VPISSDD.TECNOLOGIA";
	public static final String VPISSDD_TGEN =					"VPISSDD.TGEN";
	public static final String VPISSDD_TIPO_INTER =				"VPISSDD.TIPO_INTER";
	public static final String VPISSDD_TIPO_INTERLOCUTOR = 		"VPISSDD.TIPO_INTERLOCUTOR";
	public static final String VPISSDD_TIPO_SERV_COMPLEMENTARIO = "VPISSDD.TIPO_SERV_COMPLEMENTARIO";
	public static final String VPISSDD_TRAMO_TRANSPORTE  =      "VPISSDD.TRAMO_TRANSPORTE";   
	public static final String VPISSDD_TRANSITOS_TRAFICO =		"VPISSDD.TRANSITOS_TRAFICO";
	public static final String VPISSDD_TRANSPORTE =				"VPISSDD.TRANSPORTE";
	public static final String VPISSDD_UNIDAD_MEDIDA_VELOC = 	"VPISSDD.UNIDAD_MEDIDA_VELOC";
	public static final String VPISSDD_VARIABLE_CANCELACION =	"VPISSDD.VARIABLE_CANCELACION";
	public static final String VPISSDD_ZONA_PRIMARIA =			"VPISSDD.ZONA_PRIMARIA";
	public static final String VPISSDD_PLATAFORMA_DATOS  =		"VPISSDD.PLATAFORMA_DATOS";
	public static final String VPISSDD_DATOS_IP = 				"VPISSDD.DATOS_IP";
	public static final String VPISSDD_NUM_INTERCX = 			"VPISSDD.NUM_INTERCX";

	public static final String VPISSDD_MO_ACCESO_TERRENO = 		"VPISSDD.MO_ACCESO_TERRENO";
	public static final String VPISSDD_MO_EQUIPO_CLIENTE = 		"VPISSDD.MO_EQUIPO_CLIENTE";
	public static final String VPISSDD_MO_ATIS 	= 		"VPISSDD.MO_ATIS";
	public static final String VPISSDD_MO_TGEN 			= 		"VPISSDD.MO_TGEN";
	public static final String VPISSDD_MO_ZONA_PRIMARIA	= 		"VPISSDD.MO_ZONA_PRIMARIA";
	public static final String VPISSDD_MO_TIPO_INTER	= 		"VPISSDD.MO_TIPO_INTER";
	public static final String VPISSDD_MO_REGISTRO_NUM_INTERCX	= 		"VPISSDD.MO_REGISTRO_NUM_INTERCX";
	public static final String VPISSDD_MO_NUM_INTERCX	= 		"VPISSDD.MO_NUM_INTERCX";	

	public static final String VPISSDD_MO_TRANSITOS_TRAFICO	= 		"VPISSDD.MO_TRANSITOS_TRAFICO";
	public static final String VPISSDD_MO_CENTRO_PRIMARIO	= 		"VPISSDD.MO_CENTRO_PRIMARIO";
	public static final String VPISSDD_MO_CONFIG_LOCAL_CTC 	= 		"VPISSDD.MO_CONFIG_LOCAL_CTC";
	public static final String VPISSDD_MO_DATOS_FORM_NUM_INTERCX	= 		"VPISSDD.MO_DATOS_FORM_NUM_INTERCX";
	public static final String VPISSDD_MO_ES_TRAFICO 			= 		"VPISSDD.ES_TRAFICO";
	public static final String VPISSDD_MO_SERVICIO_COMPLEMENTARIO	=	"VPISSDD.MO_SERVICIO_COMPLEMENTARIO";
	public static final String VPISSDD_MO_RECURSOS_CX	=	"VPISSDD.MO_RECURSOS_CX";

	public static final String VPISSDD_MO_ESP_FIS_INTERCX	=	"VPISSDD.MO_ESP_FIS_INTERCX";
	public static final String VPISSDD_MO_FICHA_CONF_RUTA	=	"VPISSDD.MO_FICHA_CONF_RUTA";
	public static final String VPISSDD_MO_ES_TRAFICO_ENT	=	"VPISSDD.MO_ES_TRAFICO_ENT";
	
	
	public static final String VPISSDD_CONFIGURACION_PARCUPLUS ="VPISSDD.CONFIGURACION_PARCUPLUS";	
	public static final String VPISSDD_RECURSOS_NURIA ="VPISSDD.RECURSOS_NURIA";
	// Fin tablas del Alias VPISSDD
	
	//Tablas del Alias ATIEMPO
	public static final String ATIEMPO_EMPRESA =                "ATIEMPO.EMPRESA";
	public static final String ATIEMPO_MONEDA =					"ATIEMPO.MONEDA";
	public static final String ATIEMPO_UBICACION_GEOGRAFICA = 	"ATIEMPO.UBICACION_GEOGRAFICA";
	public static final String ATIEMPO_USUARIO = 					"ATIEMPO.USUARIO";
	public static final String ATIEMPO_GRUPOS_PRODUCTOS_SERVICIOS = "ATIEMPO.GRUPOS_PRODUCTOS_SERVICIOS";
	public static final String VPISTBBA_FLUJO_PROD_SERV_OPER_COM  = "VPISTBBA.FLUJO_PROD_SERV_OPER_COM";
	public static final String AGENDA_CARGA_MAXIMA  = "AGENDA.CARGA_MAXIMA";
	public static final String AGENDA_TIPO_AGENDAMIENTO_SEGMENTO  = "AGENDA.TIPO_AGENDAMIENTO_SEGMENTO";
	public static final String AGENDA_DISTRIBUCION_CARGA_MAXIMA  = "AGENDA.DISTRIBUCION_CARGA_MAXIMA";
	public static final String AGENDA_USUARIO_MAC  = "AGENDA.USUARIO_MAC";
	public static final String AGENDA_RANGO  = "AGENDA.RANGO";
	public static final String AGENDA_EXCEPCION_TIPO_AGENDAMIENTO  = "AGENDA.EXCEPCION_TIPO_AGENDAMIENTO";
	public static final String ATIEMPO_FAMILIA_PRODUCTO_SERVICIO  = "ATIEMPO.FAMILIA_PRODUCTO_SERVICIO";
	public static final String AGENDA_EXCEPCION_RANGO  = "AGENDA.EXCEPCION_RANGO";
	public static final String AGENDA_EXCEPCION_DISTRIBUCION_CARGA_MAXIMA  = "AGENDA.EXCEPCION_DISTRIBUCION_CARGA_MAXIMA";
	public static final String AGENDA_EXCEPCION_CARGA_SEGMENTO  = "AGENDA.EXCEPCION_CARGA_SEGMENTO";
	public static final String AGENDA_EXCEPCION_CARGA_MAXIMA  = "AGENDA.EXCEPCION_CARGA_MAXIMA";
	public static final String AGENDA_DIAS_FESTIVOS  = "AGENDA.DIAS_FESTIVOS";
	public static final String AGENDA_GRUPO_SEGMENTO  = "AGENDA.GRUPO_SEGMENTO";
	public static final String VPISTBBA_FLUJO_DEFINICION  = "VPISTBBA.FLUJO_DEFINICION";
	public static final String ATIEMPO_CAUSA_CIERRE  = "ATIEMPO.CAUSA_CIERRE";
	public static final String ATIEMPO_CAUSA  = "ATIEMPO.CAUSA";
	public static final String VPISTBBA_FILTRO_PS_TICA = "VPISTBBA.FILTRO_PS_TICA";

	//Tablas del FCONT
	public static final String FCONT_VALOR_BASE 			=	"FCONT.VALOR_BASE";
	public static final String FCONT_EXCEPCION_ACOMETIDA 	=	"FCONT.EXCEPCION_ACOMETIDA";
	public static final String FCONT_FILTRO 				=	"FCONT.FILTRO";
	
	public static final String ATIEMPO_CENTRAL_CONMUTACION = "ATIEMPO.CENTRAL_CONMUTACION";
	public static final String ATIEMPO_HABILIDAD_USUARIO  = "ATIEMPO.HABILIDAD_USUARIO";
	public static final String ATIEMPO_ROL = "ATIEMPO.ROL";
	public static final String ATIEMPO_ROL_HABILIDAD = "ATIEMPO.ROL_HABILIDAD";
	public static final String ATIEMPO_PUNTO_VENTA = "ATIEMPO.PUNTO_VENTA";
	public static final String ATIEMPO_PLANTA_COMERCIAL					=	"ATIEMPO.PLANTA_COMERCIAL";
	public static final String ATIEMPO_OPERACION_COMERCIAL				=	"ATIEMPO.OPERACION_COMERCIAL";
	public static final String ATIEMPO_SEGMENTO							=	"ATIEMPO.SEGMENTO";
	public static final String ATIEMPO_PERFIL							=	"ATIEMPO.PERFIL";	
	public static final String VPISTBBA_FAMILIA_EQUIPO  = "VPISTBBA.FAMILIA_EQUIPO";
	public static final String VPISTBBA_PRODUCTO_SERVICIO_GAA  = "VPISTBBA.PRODUCTO_SERVICIO_GAA";
	public static final String VPISTBBA_TIPO_RESPUESTA_PREGUNTA			=	"VPISTBBA.TIPO_RESPUESTA_PREGUNTA";
	public static final String VPISTBBA_TIPO_EQUIPO  = "VPISTBBA.TIPO_EQUIPO";
	public static final String VPISTBBA_TIPO_TRANSACCION_EQUIPO  = "VPISTBBA.TIPO_TRANSACCION_EQUIPO";
	public static final String ATIEMPO_AGENCIA							=	"ATIEMPO.AGENCIA";
	public static final String ATIEMPO_ACTIVIDAD_TORRE_CONTROL  = "ATIEMPO.ACTIVIDAD_TORRE_CONTROL";
	public static final String ATIEMPO_DETALLE_ACTIVIDAD_TC  = "ATIEMPO.DETALLE_ACTIVIDAD_TC";
	public static final String VPISTBBA_TIPO_SERVICIO_ADSL = "VPISTBBA.TIPO_SERVICIO_ADSL";
	public static final String VPISTBBA_IVR_VARIACION_COMERCIAL  		=	"VPISTBBA.IVR_VARIACION_COMERCIAL";
	public static final String VPISTBBA_PRODUCTO_SERVICIO_COMANDO_GAA	=	"VPISTBBA.PRODUCTO_SERVICIO_COMANDO_GAA";
	public static final String VPISTBBA_PREGUNTA_ENCUESTA				=	"VPISTBBA.PREGUNTA_ENCUESTA";
	public static final String VPISTBBA_PREGUNTA_AMBITO					=	"VPISTBBA.PREGUNTA_AMBITO";
	public static final String ATIEMPO_PRODUCTO_SERVICIO  = "ATIEMPO.PRODUCTO_SERVICIO";
	public static final String VPISTBBA_SERVICIO_FLEXIBLE  = "VPISTBBA.SERVICIO_FLEXIBLE";
	public static final String VPISTBBA_TICA  = "VPISTBBA.TICA";
	public static final String ATIEMPO_TERRITORIO  = "ATIEMPO.TERRITORIO";
	public static final String ATIEMPO_COMUNA  = "ATIEMPO.COMUNA";
	/** Método de clase que retorna un Id Autoincremental de tipo Long para una tabla dada
	 * @param tabla String que identifica a la tabla, se debe sacar de las constantes declaradas en esta misma clase
	 * @return un Long que se puede usar como identificador para la tabla seleccionada */
	public static Long getIdAutoincremental(String tabla) {
		return Boletero.getInstance().obtenerTicket(tabla);
	}
}
