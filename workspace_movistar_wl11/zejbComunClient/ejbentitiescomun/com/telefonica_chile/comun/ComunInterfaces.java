package com.telefonica_chile.comun;

/**
 *
 * @author francois
 */
public interface ComunInterfaces
{
    //TODO : Inicio CR 12827
	public static final long gponOrigen = 1016;
	public static final long gponDestino = 1024;
	public static final long psGpon = 1;
	public static final long actPlantaInterna = 1076;
	//  TODO : Fin CR 12827
	public static final int estadoOk = 1 ;
	public static final int estadoError = 2 ;
	public static final int estadoEspera= 3 ;
	public static final int estadoEsperaManual = 4 ;
	public static final int estadoReintentado = 5 ;
	
	// CR20948 - adocarmo - inicio
	// Indica que recibi ACK en la invocacion a servicio de Altamira
	//AT-2408 - guido - Corrección por concurrencia en recepecion ambas respuestas 601 - 16/Jul/2009
	//public static final int estadoACK = 6 ;
	// CR20948 - adocarmo - fin	
    
	public static final int codigoConectorDos = 2 ;
	public static final int codigoConectorCuatro = 4 ;
	public static final int codigoConectorSiete = 7 ;
	public static final int codigoConectorSeis = 6 ;
	public static final int codigoConectorTres = 3 ;
	public static final int codigoConectorOcho = 8 ;
	
	public static final int estadoPeticionEnCurso = 1 ;
	public static final int estadoPeticionTerminada = 2 ;
	public static final int estadoPeticionCancelada = 3 ;
	public static final int LOCALIDAD_TOA = 1;
    
	// TODO chequear valor estado cierre del Prpducto_servicio_peticion
	// ver Estado_ps_peticion
	public static final int estadoCierreOk = 0 ;
	public static final int estadoCierreNovedad = 1 ;
	public static final int estadoCierreError = 3 ;
    
	// familias de productos y servicios
	public static final int familiaLinea = 1 ;
	public static final int familiaBandaAncha = 2 ;
	public static final int familiaTV=3;
	public static final int familiaIC=4;
	public static final int familiaPS_GVP = 5;//Movistar Play/ Se agrega nuevo tipo de agrupacion para nueva tipo de PC
	public static final int familiaPC_GVP = 317;//Movistar Play/ Se agrega nuevo tipo de agrupacion para nueva tipo de PC
	public static final int familiaPcBANaked = 318 ;//BA Naked PC Banda Ancha 
	public static final int familiaPcPsBANaked = 7 ;//BA Naked PS Banda Ancha
	public static final int familiaBandaAnchaNaked = 6 ;//BA Naked // ps de voz
	public static final String ACT_DESINSTALAR_CRE = "Director de Flujos.Desintalacion_CRE.Desinstalar_CRE";
	public static final String ACT_DESINSTALAR_MTZ = "Director de Flujos.Motorizado.Motorizado";
	public static final String ACT_CONTROL_DESINSTALAR_CRE = "Director de Flujos.Desintalacion_CRE.Control_Desinstalacion_CRE";
	public static final String ACT_CONTROL__DESINSTALAR_MTZ = "Director de Flujos.Motorizado.Control_Motorizado";
	public static final String ACT_CONFIGURAR_ACS = "Director de Flujos.CONF_ACS.CONF_ACS";
	public static final String ACT_DESCONFIGURAR_MODEM = "Director de Flujos.Desconfigurar_Modem.Desconfigurar_Modem";
	
	public static final int familiaPcLinea = 300;
	// Nueva familia IP
	public static final int familiaIP = 313;
	public static final int familiaPcBA = 301 ;
	//esta familia se implemento pero no se usa en atiempo si no unicamente para que entre el PS y atiempo lo almacene
	//Familia presencia digital
	public static final int familiaPresenciaDigital = 315;
	public static final String A_ACS_RESULT_CODE  = "A_ACS_RESULT_CODE";
	public static final String A_ACS_RESULT_DESCRIPTION  = "A_ACS_RESULT_DESCRIPTION";
	public static final String DELIVERED = "TOA_DELIVERED";
	public static final String A_HC_RESULT_CODE = "A_HC_RESULT_CODE";
	public static final String A_HC_RESULT_DESCRIPTION = "A_HC_RESULT_DESCRIPTION";
	public static final String FAILED = "TOA_FAILED";
	public static final String FAILED_OUT = "TOA_FAILED_OUT";
	public static final String DELIVERED_OUT = "TOA_DELIVERED_OUT";
	public static final String A_REFRESH_TV_RESULT_CODE = "A_REFRESH_TV_RESULT_CODE ";
	public static final String A_REFRESH_TV_RESULT_DESCRIPTION = "A_REFRESH_TV_RESULT_DESCRIPTION";
	public static final String A_REFRESH_BA_RESULT_CODE = "A_REFRESH_BA_RESULT_CODE ";
	public static final String A_REFRESH_BA_RESULT_DESCRIPTION = "A_REFRESH_BA_RESULT_DESCRIPTION";
	public static final String A_REFRESH_LB_RESULT_CODE = "A_REFRESH_LB_RESULT_CODE ";
	public static final String A_REFRESH_LB_RESULT_DESCRIPTION = "A_REFRESH_LB_RESULT_DESCRIPTION";
	
	//RQ napster Familia Napster dcardena 27/02/2014 
	//fin SVAs
	//TODO -- Codigo donde vienen los nuevos tels de contacto -- Pablo L -- CR-10120
	public static final int codTelCot = 999 ;
	public static final int familiaPcTV = 302 ;
	public static final int familiaDecoTV = 303 ;
	public static final int familiaTematicoTV = 304 ;
	public static final int familiaPaqueteTV = 305 ;

	//TODO PVR se creo la familia deco PVR 306
	public static final int familiaDecoPVRTV = 306;
	
	//TODO: Se crea nuevo familia para deco HD TV
	public static final int familiaDecoHDTV = 312 ;
	public static final String desHCDecoHDTV ="HD";
	
	public static final String desDecoSTD ="Deco TV";
	public static final String desDecoPVR ="DecoPVR TV";
	public static final String desHCDecoSTD ="STD";
	public static final String desHCDecoPVR ="PVR";
	
    
	// tipo de agrupacion: sirve para clasificar la Agrupacion ATIS 
	public static final Integer tipoAgrupacionNoDeterminado = new Integer(0) ;
	public static final Integer tipoAgrupacionLinea = new Integer(1) ;	// el ATIS mando (o espera de vuelta) un telefono
	public static final Integer tipoSubAgrupacionAltaSTBExistente = new Integer(107) ;
	public static final Integer tipoSubAgrupacionAltaPVAExistente = new Integer(106) ;
	
	public static final long CODIGO_CARACTERISTICA_EQUIPO_QW = 51;//Codigo de la caracteristica de quick wins
	public static final long CODIGO_CARACTERISTICA_CUOTAS_QW = 997; //Codigo de la caracteristica de quick wins
	
	public static final Integer tipoAgrupacionBA = new Integer(2);		//Producto de Banda Ancha
	public static final Integer tipoAgrupacionTV = new Integer(3) ;		// el ATIS mando un numero de TV
	public static final Integer tipoAgrupacionIC = new Integer(4);
	public static final Integer tipoAgrupacionIT = new Integer(4);
	public static final Integer tipoAgrupacionMNT = new Integer(5); //Tipo Mantenimiento, req 28274.
	public static final Integer tipoAgrupacionPDG = new Integer(9);
	public static final Integer tipoAgrupacionPS_SVA = new Integer(10); //RQ Napster // RQ SVAs se modifica napster
	public static final Integer tipoAgrupacionPS_GVP = new Integer(11);//Movistar Play/ Se agrega nuevo tipo de agrupacion para nueva tipo de PC
    
	public static final String NoDeter="NO";
	public static final String IC="IC";
	public static final String TV="TV";
	public static final String TVIC="TVIC";
	public static final String BA="BA";
	public static final String BAIC="BAIC";
	public static final String BATV="BATV";
	public static final String BATVIC="BATVIC";
	public static final String LB="LB";
	public static final String LBST="L";
	public static final String LBIC="LBIC";
	public static final String LBTV="LBTV";
	public static final String LBTVIC="LBTVIC";
	public static final String LBBA="LBBA";
	public static final String LBBAIC="LBBAIC";
	public static final String LBBATV="LBBATV";
	public static final String LBBATVIC="LBBATVIC";
	
	//TODO RETA 29/09/2009 - Internet Equipado
	public static final String IT="IT";
	public static final String LBIT="LBIT";
	public static final String LBITTV="LBITTV";
	public static final String ITIC="ITIC";
	public static final String ITTV="ITTV";
	public static final String ITTVIC="ITTVIC";
	public static final String LBITTVIC="LBITTVIC";
	public static final String LBITIC="LBITIC";
	public static final String LBITTVBA="LBITTVBA";
	public static final String LBITBA="LBITBA";
	public static final String ITICBA="ITICBA";
	public static final String ITTVBA="ITTVBA";
	public static final String ITTVICBA="ITTVICBA";
	public static final String LBITICBA="LBITICBA";
	public static final String LBITTVICBA="LBITTVICBA";
	public static final String PS_PDTI_PROPERTIES = "PS_PDTI";
	
	//familia ip
	public static final String IP="IP";
	public static final String IPBA="IPBA";
	public static final String IPBATV="IPBATV";
	public static final String IPTV="IPTV";
	public static final String IPIC="IPIC";
	public static final String IPTVIC="IPTVIC";
	public static final String IPBAIC="IPBAIC";
	public static final String IPBATVIC="IPBATVIC";
	public static final String IPIT="IPIT";
	public static final String IPITTV="IPITTV";
	
	
	//End TODO
	
	// CR25865 CRE - adocarmo - inicio
	public static final String BAPDTI="BAPDTI";
	public static final String LBBAPDTI="LBBAPDTI";
	public static final String TVPDTI="TVPDTI";
	// CR25865 CRE - adocarmo - fin
	
	// CR25996 UMTS - agonz - 24/06/2009
	public static final String LBPDTI="LBPDTI";
	public static final String LBTVPDTI="LBTVPDTI";
	public static final String BATVPDTI="BATVPDTI";
	public static final String LBBATVPDTI="LBBATVPDTI";
	public static final String PDTI="PDTI";
	
	public static final long ID_GRUPO_UMTS = 3;
	
	public static final String opCoTipoAlta = "A";
	public static final String opCoTipoBaja = "B";
	public static final String opCoTipoPosVenta = "P";
	//public static final String opCoTipoCambioNro = "ACN";
	public static final String opCoTipoAltaCambioProd = "ACP";
	public static final String opCoTipoBajaCambioProd = "BCP";
	public static final String opCoTipoAltaTraslado = "ATraslado";
	public static final String WF_VALUE_INSTALACION_EQUIPOS_QW = "instalacion_equipos";
	public static final String opCoTipoBajaTraslado = "BTraslado";
	public static final String opCoTipoTrasnfTecnica = "ATransferencia";
	public static final int opCoSuspencioAP = 17;
	public static final int opCoReconexionAP = 14;
	
	public static final int opCoCambioTitular = 106;
	
	public static final String opCoTras_Traslado = "T";
	public static final String opCoTras_Transferencia= "R";
	// fin - CR25996 UMTS - agonz - 24/06/2009

	//REQ 30793 - FAVG - ENE/20/2010 Inicio
	public static final int tipoLB=1;	
	//REQ 30793 - FAVG - ENE/20/2010 Fin
	public static final int tipoPBX=5;
	public static final int tipoIC=3;
	public static final int subTipoPbxPilotoNR=27;
	public static final int subTipoPbxPilotoAutoConsumo=28;
	public static final int subTipoPbxTroncalNR=29;
	public static final int subTipoPbxTroncalAutoconsumo=30;
	
	public static final String altaRecursoLineaBasica="a";
	public static final String bajaRecursoLineaBasica="b";
	
	//Acciones para modems
	public static final int accionModemNoAction=0;
	public static final int accionModemLiberar=1;
	public static final int accionModemOcupar=2;
	public static final int accionModemNoRecuperado=4;
	public static final String ASISTENCIA_OK = "ASISTENCIA_OK";
	public static final int accionModemCambioNoRec=5;
	public static final int accionModemCambioAveriado=7;
	public static final int accionModemWiFi=6;
	public static final int accionModemConfigurado = 8;
	public static final int accionModemNoConfigurado=10;//Accion de Mòdem no configurado
	public static final int accionModemConfiguradoSOA = 11;
	public static final int accionModemConfiguradoTOA = 12;
	   
	//@idrincon - req 1716 12/10/2010
	int accionModemAltaMigracion = 6;
	//fin req 1716
   
   //Acciones y Estados para Decos Tarjetas
   
	public static final int estadoParOk = 1 ;
	public static final int estadoParNoOk = 2 ;
	
	//Para inventario decos
	public static final int accionParNula=0;
	public static final int accionParActivar = 1; // ocupar
	public static final int accionParEliminar = 2; //liberar
	public static final int accionParDanhado=3;
	public static final int accionParNoRecuperado=4;
	public static final int accionParActivoCas = 5;
	
	//Operacion para configurar decos
	public static final int operacionParActivar = 1;
	public static final int operacionParDesactivar = 6;
	//	TODO: CR 7304 Inicio - ana  
	public static final int operacionParDesactivarLiberar = 41; 
	//	TODO: ana CR 7304 - Fin
	public static final int operacionParRefresh=0;
	
	//Ides de Actividades para Almacenar la Fecha de Cierre por Ps
	//para su posterior envio a ATIS
	public static long idActividadControlInstalacion=1023;
	public static long idActividadControlDesInstalacion=1028;
	
	
	//Ps de Control Parental
	public static long psReseteoCP=308;
	
	//Id Actividad Obtener Recursos DTH
	public static int idActividadObtenerConfiguracionDTH=24;
	
	//Central TRucha
	public static long centralGraldeTV=999999;
	public static long cambioNumero=8;
	public static long altaTraslado = 50;
	public static long bajaTraslado = 51;
	public static long bajaSTB = 6;
	//para baja de deco
	public static String desconfiguracionEspera="0";
	public static String desconfiguracionCorrecta="1";
	public static String desconfiguracionInCorrecta="2";

	//Enrutamiento ST
	public static final String codigoEstadoAveriaCerrada="X";
	public static final String codigoEstadoAveriaCancelada="C";
	public static final String codigoEstadoAveriaTerminada="T";
	public static final String codigoEstadoAveriaAbierta="A";
	
	public static final long codigoReglaBloqueo=1;
	public static final long codigoReglaDiagnostico=2;
	public static final long codigoReglaPlantaInterna=3;
	public static final long codigoReglaPlantaExterna=4;
	public static final long codigoReglaGestionAbonados=5;
	//TCS-gquevedo May 27, 2009 CR24382 INICIO
	public static final long codigoReglaAprovisionamientoRI=6;
	//TCS-gquevedo May 27, 2009 CR24382 FIN
	
	public static final int reiteracionFallida=0;
	public static final int reiteracionOK=1;
	public static final int reiteracionGrabaNuevoIncidente=2;	
	// TODO: AT-1035 - PVR - Inicio - guido - Operacion Comercial Alta Cambio de Producto
	public static final String operacionACP = "ACP";
	public static final String operacionBCP = "BCP";
	public static final String operacionTraslado = "T";
	public static final String FLUJO_VENTA_EQUIPO_CONTADO = "ALTA DE CONTADO";
	// TODO: guido AT-1035 - Fin

	//	TODO: AT-1138  - PVR - Inicio - juan - Operacion Comercial Alta
	public static final String operacionA = "A";
	
	//CR4860 - Sigres - guido - 2/May
	public static final String SIGRES_OP_EXITOSA = "0000";
	
	//CR4860 - Sigres - Bandeja Espera - gustavo 30/Mayo
	public static final int SUB_ESTADO_ESPERA_SIN_MENSAJE = 10;
	public static final int SUB_ESTADO_ESPERA_CON_MENSAJE = 20;
	//agonz 6 abril 2009 AT-2028 CR-24573
	public static final String COD_ACTIVIDAD_OBT_CONF_BA_SI="Director de Flujos.Obtener_Configuracion_BA_SI.Obtener_Configuracion_BA_SI";
	
	// CR20948 - Altamira - guido - 22/Abr/2009 - Nombres de alto nivel o atiempoServiceName de los sericios de Altamira
	public static final String ALT_SERVICIO_ALTA_ABONADO 			= "ALTA_ABONADO";
	public static final String ALT_SERVICIO_ALTA_PERIODIFICACION	= "ALTA_PERIODIFICACION";
	public static final String ALT_SERVICIO_BAJA_ABONADO			= "BAJA_ABONADO";
	public static final String ALT_SERVICIO_BAJA_PERIODIFICACION	= "BAJA_PERIODIFICACION";
	public static final String ALT_SERVICIO_CAMBIO_NUMERO			= "CAMBIO_NUMERO";
	public static final String ALT_SERVICIO_CAMBIO_TIPO_PREPAGO		= "CAMBIO_PREPAGO";
	public static final String BAJ_SERVICIO_DESACTIVACION_CLAVE     = "DESACTIVACION_CLAVE";
	public static final String ALT_SERVICIO							= "ALTA";
	public static final String BAJ_SERVICIO							= "BAJA";
	public static final Object ACT_MOVISTAR_PLAY 					= "CONF_MOVISTAR_PLAY";
	public static final Object ACT_CONF_ACS		 					= "CONF_ACS";
	public static final String ACT_INSTALAR_FLUJO					= "Instalacion";
	public static final String ACT_INSTALAR_TOA_FLUJO				= "Instalar TOA";
	public static final String ACT_SXRX_IMS							= "CONF_SXRX_IMS";//ACTVIDAID SUSPENCION RECONXION NAKED IMS APSC
	public static final String ACT_SOL_TECNICA_AVERIAS				= "Solucion_Tecnica_Averias";
	// CR20948 - Altamira - adocarmo - 13/May/2009 - Valor de variable de WF para que derive a la actividad Aprovisionamiento Red Inteligente
	public static final String WF_VALUE_ALTAMIRA_APROVISIONAMIENTO_RI	= "AprovisionamientoRIS";
	
	//CR24918 - TV Masivo - PCawen
	public static final int NRO_DECOS_MASIVO = 10;
	
	//CR27638 - GermanP - 24/Aug/2009
	public static final String COD_ALTA_CLAVE_SECRETA="Director de Flujos.Alta_Clave_RI.Alta_Clave_RI";
	public static final String COD_DESACT_CLAVE_SECRETA="Director de Flujos.Desactivacion_Clave_RI.Desactivacion_Clave_RI";
	public static final String ALTA_CLAVE_SECRETA = "ALTA_CLAVE_SECRETA";
	public static final String DESACT_CLAVE_SECRETA = "DESACT_CLAVE_SECRETA";
	public static final String CODIGO_SECRETO_DEFAULT = "0000";
	
	
	//CR-23444 - PCawen - Informacion del ciclo de facturacion 
	public static final int codInfCicloFac = 998;
	
	//CR25865 CRE - adocarmo - inicio
	public static final int ID_GRUPO_PDTI = 7;
	public static final int ID_GRUPO_VentaEquipos = 8;
	//CR25865 CRE - adocarmo - fin
	
	// CR26362 - adocarmo - inicio
	public static int ID_OP_BAJA_TRASLADO_SOLO_BA = 68;
	public static int ID_OP_BAJA_TRASLADO = 51;
	public static int ID_OP_ALTA_TRASLADO = 50;
	public static int BAJA_POR_CAMBIO_PDTO_TRASL = 49; 
	public static int ALTA_POR_CAMBIO_PDTO_TRASL = 48;
	public static int ID_OP_ALTA_VENTA_CONTADO = 111;
	// CR26362 - adocarmo - fin
	
	public static long BajaDuoUmts = 89;
	public static long AltaDuoUmts = 88;
	public static final String MUESTRA_PESTANA_TV = "1";
	public static long TrasladoUmts = 90;

	//CR14657 - Granite - 14/Oct/2008
	public static final long sinGranite=0;
	public static final long conGranite=1;
	public static final long estadoRelaretencionActiva=1;
	public static final long estadoRelaretencionInactiva=2;
	//CR4860 - Sigres - Bandeja Espera - gustavo 30/Mayo
	public static final int SS_granite = 99;
	public static final int CP_granite = 97;
	public static final int reversa_Granite = 98;
	
	// Raúl Triviño - bandera para ubicar lineas prepago
	public static String BanderaPrepago = "prepag";
	public static String BanderaControl = "control";
	
	//	TODO RETA 29/09/2009 - Internet Equipado
	public static final int familiaIntEquipado = 307;
	public static final String ACT_INSTALAR_IE= "Director de Flujos.Instalacion_Equipo.Instalar_Equipo";
	public static final String ACT_CONTROL_INSTALACION_IE= "Director de Flujos.Instalacion_Equipo.Control_Instalacion_IE";
	//End TODO
	
	//DAVID: Req 30480 Migración TV sin fronteras
	public static final int psTVSinFronteras = 302 ;
	public static final int bajaMigracionPS = 70 ;
	public static final int altaMigracionPS = 69 ;
	//DAVID: Fin
	
	//@idrincon - req 1617 12/10/2010
	int ALTAMIGRACIONPS2 = 71;
	int ALTAMIGRACIONPS3 = 73;
	//fin 12/10/2010
	
	//DAVID: Req 27950 Deco adicional
	public static final int psDecoAdicional = 350 ;
	//DAVID: Fin
	
	//TODO: Raúl Triviño - 13/10/2009 - ajuste del requerimiento 00029981_AltaTematicosEquipos
	public static final String ACTI_ID_Configuracion_DTH = "8";
	public static final String PEFL_ESTADO_OK = "OK";
	public static final String PRSE_ID_Configuracion_DTH_1 = "290";
	public static final String PRSE_ID_Configuracion_DTH_2 = "907";
	
	/**
     * DAVID: se adiciona el siguiente código para RQ 28274, cobro incidencias.
     */
    public static final int familiaMantenimiento = 308;
    public static final String MNT="MNT";
    public static final int rolGetorODSLB=21000;
	public static final int rolGetorODSBA=20000;
	public static final int rolGetorODSTV=22000;
	public static final int rolGestorPGC=15000;
	public static final long psMNTTV=1353;
	public static final long psMNTBA=1352;
	public static final long psTrasladoInternoTV=309;
	public static final Long rolGestorAsistenciaRemota= new Long(40006);
	public static final Long rolGestorBandejaBaja=new Long(40008);//TOA FASE III
	//TODO: Raúl Triviño - 17/02/2010 - Requerimiento Peticion en vuelo
    public static final String BA_EN_VUELO = "BA_EN_VUELO";
    public static final String BA_EN_VUELO_OLD = "BA_EN_VUELO_OLD";
    public static final String TV_BASICO_EN_VUELO = "TV_BASICO_EN_VUELO";
    public static final String TV_ADICIONAL_EN_VUELO = "TV_ADICIONAL_EN_VUELO";
    public static final String TV_BASICO_EN_VUELO_OLD = "TV_BASICO_EN_VUELO_OLD";
    public static final String TV_ADICIONAL_EN_VUELO_OLD = "TV_ADICIONAL_EN_VUELO_OLD";
    public static final String TV_BASICO_EN_VUELO_S = "TV_BASICO_EN_VUELO_S";
    public static final String TV_ADICIONAL_EN_VUELO_S = "TV_ADICIONAL_EN_VUELO_S";
    
    public static int CENTRO_SEGURIDAD = 1222;
    
	/**
     * DAVID: se adiciona el siguiente código para RQ 33936, mejoras proceso CRE, Marzo 26 2010.
     */
	public static long actDesInstDTHCRE=1900;
	public static long actDesInstCRE=1902;
	/**
     * FIN DAVID: se adiciona el siguiente código para RQ 33936, mejoras proceso CRE, Marzo 26 2010.
     */
     
    public static int identificadorWiFi = 2;
    public static int identificadorConvencional = 1;
	public static short accionWiFiUnico = 6;
	
	
    public static final int familiaAsistencia = 309;
    public static final int familiaVisitaAsist = 310;
    public static final int familiaAsistRemota = 311;
    public static final int familiaAplicateca = 314;
	
	
	public static long caracteristicaMailAsistencia=34;
	public static long caracteristicaPC1=47;
	public static long caracteristicaPC2=48;
	public static long caracteristicaPC3=49;
	
	public static final Integer tipoAgrupacionAsist = new Integer(6); //req 33626.
	public static final Integer tipoAgrupacionVisAsist = new Integer(7); //req 33626.
	public static final Integer tipoAgrupacionAsistRem = new Integer(8); //req 33626.
	public static final Integer tipoAgrupacionPCNaked = new Integer(318); //req 33626.
	public static final Integer tipoAgrupacionPSNaked = new Integer(7); //req 33626.
	
	public static final String ACT_ASISTENCIA= "Director de Flujos.AsistenciaCliente.AsistenciaCliente";

	public static final String ACT_ASISTENCIA_RMT= "Director de Flujos.AsistenciaClienteRemota.AsistenciaClienteRemota";
	
	public static final String ACT_INSTALAR= "Director de Flujos.Instalacion.Instalar";
	public static final String ACT_DES_INSTALAR= "Director de Flujos.Desinstalacion.Desinstalar";
	
	//req 3709
	public static final String ACT_AUTO_INST = "Director de Flujos.AutoInstalacion.AutoInstalacion";
	public static final String ACT_ALISTAR_KIT = "Director de Flujos.Alistar_Kit_Auto_Inst.Alistar_Kit_Auto_Inst";
	
	public static final String ACT_CTRL_INSTALACION= "Director de Flujos.Instalacion.Control_Instalacion";
	public static final String ACT_CTRL_DESINSTALACION= "Director de Flujos.Desinstalacion.Control_Desinstalacion";
	public static final String ACT_INSTALAR_TOA= "Director de Flujos.Instalar_TOA.Instalar_TOA";
	public static final String ACT_CTRL_INSTALACION_TOA= "Director de Flujos.Instalar_TOA.Control_Instalacion_TOA";
	public static final String ACT_PGI_AVERIAS= "Director de Flujos.PGI.Solucion_y_Soporte_Tecnico_Averias";
	public static final String ACT_BANDEJA_PROCESO_BAJA= "Director de Flujos.PGI.Proceso_De_Baja";
	public static final String Asistencia="ASIST";
	
	public static final String visAsist="VIS_ASIST";
	
	public static final String asistRemo="ASIST_REM";
	
	public static long idActividadPGC=1039;
	public static long idActividadPGI=1035;
	public static long idActividadPGF=1914;
	public static long idAct_Solucion_Tecnica_BA=1037;
	public static long idAct_Solucion_Tecnica_DTH=1038;
	public static long idAct_Solucion_Tecnica_STB=1036;
	public static long idActividadInstalar=1022;
	
	//	David, req 1235 Asistencia ST
	public static final String AS="ASISTENCIA";	
	
	//	David, req 1060 kiosko y zona wifi ST
	public static final String KIOSKO="KIOSKO";	
	public static final String ACT_ASISTENCIA_ST= "Director de Flujos.Asistencia_Cliente_ST.Asistencia_Cliente_ST";
	public static final String ACT_PE_KIOSKO_ZWF= "Director de Flujos.Solucion_BA.Planta_Externa_KZWF";
	public static final String ACT_PE_VE="Director de Flujos.Planta_Externa_VE.Planta_Externa_VE";
	
	//Agenda SC
	public static final String sistemaAtiempo = "ATIEMPO";
	public static final String sistemaAtiempoSt = "ATIEMPO_ST";
	public static final String sistemaAgendaSC = "ESB";
	public static final int ACTUACION_ABIERTA = 1;
	public static final int ACTUACION_CERRADA = 2;
	public static final int ACTUACION_CANCELADA = 3;
	public static final int ACTUACION_REAGENDADA = 4;
	public static final int ID_ACTIVIDAD_INSTALACION = 9;
	public static final int ID_ACTIVIDAD_INSTALACION_TOA = 1980;
	public static final int ID_ACTIVIDAD_DESINSTALACION = 13;
	public static final int ESTADO_AGENDA_SC_SIN_MARCA = 0;
	public static final int ESTADO_AGENDA_SC_CON_MARCA = 1;
	public static final int ESTADO_AGENDA_SC_CON_MARCA_PGC = 2;
	public static final int ERROR_OK_FALLIDA_OK = 7;
	public static final String NOMBRE_SOLICITANTE = "Nombre solicitante:";
	public static final String TEL_SOLICITANTE = "Telefono solicitante:";
	public static final String SEG_TEL_SOLICITANTE = "Segundo telefono del solicitante:";
	public static final String DATOS_AGENDAMIENTO = "Datos para el agendamiento:";
	public static final String NOMBRE_CLIENTE = "Nombre cliente:";
	public static final String TEL_CLIENTE = "Primer telefono cliente:";
	public static final String SEG_TEL_CLIENTE = "Segundo telefono cliente";
	public static final String TEL_CONTACTO_INTERLOCUTOR = "Teléfono Contacto Interlocutor:";
	public static final String SEG_TELEFONO_CONTACTO = "Segundo Telefono de contacto:";
	public static final String TER_TELEFONO_CONTACTO = "Tercer teléfono de contacto:";
	public static final String ESTRATO = "ESTRATO: ";
	public static final String TIPOINCIDENCIA = "TIPO INCIDENCIA: ";
	public static final String CARACINCIDENCIA = "45";
	
	public static final String OBSERVACION_BITACORA = "Observación bitácora:";
	public static final int TIMEOUT_ERROR = 1003;
	public static final String OBSERVACIONES = "Observaciones:";
	
	//Agenda SC - ST
	public static final String PLANTA_EXTERNA_TV= "Director de Flujos.Solucion_TV.Plataforma_TV";
	public static final String PLANTA_EXTERNA_BA= "Director de Flujos.Solucion_BA.Planta_Externa_BA";
	public static final String PLANTA_EXTERNA_STB= "Director de Flujos.Solucion_STB.Planta_Externa_STB";
	public static final String PLANTA_EXTERNA_TOA= "Director de Flujos.Solucion_TOA.Planta_Externa_TOA";//REQ TOA FASE I @DCARDENA
	public static long idActividadPETV=1064;
	public static long idActividadPEBA=1061;
	public static long idActividadPESTB=1056;
	public static long idActividadPETOA=2004;//REQ TOA FASE I SE DEBE AJUSTAR EN EL MERGE
	public static final String CONTACT_MEDIA_SMS = "4";
	public static final String CONTACT_MEDIA_TELEFONO = "3";
	public static final String CONTACT_MEDIA_CELULAR = "4";
	public static final int CIERRE_POR_ERROR = 1;
	public static final String ACCION_ALTA_DELTA_ALTA_EQUIPOS = "1";
	public static final String ACCION_BAJA_DELTA_ALTA_EQUIPOS = "2";
	public static final String RESULTADO_IT_SL = "SL";
	public static final String RESULTADO_IT_TR = "TR";
	public static final String RESULTADO_IT_AM = "AM";
	public static final String RESULTADO_AP_MM = "MM";
	
	public static long idActividadDiagnostivoBA=1059;
	public static long idActividadDiagnostivoSTB=1054;
	public static final String DIAGNOSTICO_STB = "Director de Flujos.Solucion_STB.Diagnostico_STB.Diagnosticar_STB";
	public static final String DIAGNOSTICO_BA = "Director de Flujos.Solucion_BA.Diagnostico_BA";
	
	//@idrincon - req 2596 12/10/2010
	public static final String PCTV= "PCTV";
	//fin req 2596
	
	public static final int psTVACCESOBASE = 281 ;
	
	//Tutor Web
	public static final String SISTEMA_TUTOR = "OPC_TUTOR";
	public static final String OPERACION_ALTA = "A";
	public static final String OPERACION_BAJA = "B";
	public static final String OPERACION_MODIFICAR = "M";
	public static final String OPERACION_TRANSACCION = "T";
	public static final String OPERACION_MODIFICACION_TRANSACCION = "MT";
	public static final Integer ID_ACTIVIDAD_ASISTENCIA = new Integer(97);
	
	public static final String ID_DECO_TARJETA = "1";
	public static final String ID_MODEM = "2";
	public static final String ID_OTROS = "3";
	public static final String DECODTHSTD = "DECODTHSTD";
	public static final String DECODTHPVR = "DECODTHPVR";
	public static final String DECOHD = "DECOHD";
	public static final String MODEM_STD_2 = "MODEMSTD4P";
	public static final String MODEM_STD = "MODEMSTD2P";
	public static final String MODEM_STD4P = "MODEMSTD4P";
	public static final String MODEM_STD1P = "MODEMSTD1P";
	public static final String MODEM_WIFI = "MODEMWIFI";
	
	//@idrincon - req 3274
	public static final String TRONCAL_SIP="Director de Flujos.Conf_Troncal_Sip.Conf_Troncal_Sip";
	//fin
	
	public static final String TELEFONO_DUMMY_TV = "55555555";
		
	// @mfmendez - req 4659
	public static final String ESTADO_CIERRE="Director de Flujos.Cierre_Peticion";
	public static final String LEGADO_SIRS="SIRS";	
	public static final String LEGADO_APSC="APSC";	
	// fin req 4659	
	public static final String OPCO_AUTOINSTALACION ="OPCO_AUTOINSTALACION";
	public static final String CRUZADA_BA = "Cruzada de BA";
	public static final String CTRL_CRUZADA_BA = "Control Cruzada de BA";
	public static final String CRUZADA_BA_TOA = "Cruzada de BA TOA";
	public static final String CTRL_CRUZADA_BA_TOA = "Control Cruzada de BA TOA";
	public static final String MENSAJE_SMS_ACS = "MENSAJE_SMS_AUTO_INST";
	public static final String MAX_MENSAJE_SMS_ACS = "MAX_MENSAJE_SMS_ACS";
	public static final String TMAXIMO_ENTRE_SMS_ACS_MILISEGS = "TMAXIMO_ENTRE_SMS_ACS_MILISEGS";
	public static final Long UN_DIA_EN_MILISEGUNDOS=new Long(86400000);
	public static final String MENSAJE_OK_ACS = "MENSAJE_OK_ACS";
	public static final String COD_ACT_AUTO_INSTALACION="Director de Flujos.AutoInstalacion.AutoInstalacion";
	public static final String URL_WEB_SERVICE_CONF_MODEM = "URL_WEB_SERVICE_CONF_MODEM";
	public static final String USER_WEB_SERVICE_CONF_MODEM = "USER_WEB_SERVICE_CONF_MODEM";
	public static final String PASSWD_WEB_SERVICE_CONF_MODEM = "PASSWD_WEB_SERVICE_CONF_MODEM";
	public static final String CERTIFICADE_WEB_SERVICE_CONF_MODEM = "CERTIFICADE_WEB_SERVICE_CONF_MODEM";
	public static final String PROTOCOL_CONF_MODEM = "PROTOCOL_CONF_MODEM";
	public static final String NAME_WIFI_CONF_MODEM = "NAME_WIFI_CONF_MODEM";
	public static final String SECURITY_MODEM_CONF_MODEM = "SECURITY_MODEM_CONF_MODEM";
	public static final String PASSWORD_MODEM_CONF_MODEM = "PASSWORD_MODEM_CONF_MODEM";
	public static final String COD_ACT_PGACS="Director de Flujos.PGACS.Pendiente_Gestion_ACS";
	public static final String ID_ACT_AUTO_INSTALACION = "ID_ACT_AUTO_INSTALACION";
	public static final String ID_ACT_PGACS = "ID_ACT_PGACS";
	public static final int ALTA_CERO_CNEX = 2;
	public static final String PS_ID_RETENCION ="PS_ID_RETENCION";
	public static final String OUI="OUI_";
	public static final String REV_CRUZADA_BA = "(Rev) Cruzada de BA";
	public static final String REV_CTRL_CRUZADA_BA = "(Rev) Control Cruzada de BA";
	public static final String REV_CRUZADA_BA_TOA = "(Rev) Cruzada de BA TOA";
	public static final String REV_CTRL_CRUZADA_BA_TOA = "(Rev) Control Cruzada de BA TOA";
	public static final int bajaModemACS = 6;
	public static final String OC_PGACS = "0";
	public static final String OC_REVERSA_AUT_INST = "300";
	public static final String MATERIALES_EQUIPOS_ST = "MATERIALES_EQUIPOS_ST";
	public static final String PLANTA_EXTERNA_VE ="Director de Flujos.Planta_Externa_VE.Planta_Externa_VE";
	public static long idActividadPEVE=1926;
	public static final long OPCO_CAMBIO_NUMERO =8;
	public static final String VALIDAR_MODEM_CONFIGURADO = "VALIDAR_MODEM_CONFIGURADO";
	public static final String INDICATIVO_PASSWORD_MODEM = "009";
	
	/*RQ5606 - Internet Total - mfmendez*/
	public static final int ID_ACT_ALIST_KIT_AUTO_INST = 106;
	public static final int ID_GRP_INTERNET_TOTAL = 12;
	public static final int ID_ACT_CONFIG_INTERNET_MOVIL = 114;
	public static final int ID_ACT_LEGALI_INTERNET_MOVIL = 115;
	public static final String COD_ACT_CONFIG_INTERNET_MOVIL = "Director de Flujos.Conf_Int_Movil.Conf_Int_Movil";
	public static final String COD_ACT_LEGALI_INTERNET_MOVIL = "Director de Flujos.Leg_Int_Movil.Leg_Int_Movil";
	public static final String LLAVE_PROPERTIES_PS_INTERNET_MOVIL = "PS_INTERNET_MOVIL";
	/*FIN RQ5606 - Internet Total - mfmendez*/

	public static final String ACT_GENERAR_RECIBO = "GENERAR_RECIBO";
	public static final String PS_TV_SOLA = "PS_TV_SOLA";
	public static final String COS_ACT_CONFIRMACION_PAGO_RECIBO = "Director de Flujos.Conf_Pago_Recibo.Conf_Pago_Recibo";
	public static final String COS_ACT_INSTALAR_EQUIPOS = "Director de Flujos.instalacion_equipos.Instalacion_Equipos";
	public static final String MENSAJE_ENV_FACTURA_AVANCE = "0";
	public static final String MENSAJE_ENV_FACTURA_REVERSA = "1";
	public static final String INDICADOR_PAGO_OK = "true";
	
	public static final String IDPGC="IDPGC";
	
	public static Integer idActividadFlujoActInventarioSTB = new Integer(14);

	/*RQ6142 - WS Aula - mfmendez*/
	public static final String COD_ACT_CONFIG_TERRA_CDS = "Director de Flujos.Configuracion_Terra.Configurar_Terra";
	public static final int ID_ACT_CONFIG_TERRA_CDS = 25;
	public static final String COD_ACT_CONFIG_TERRA_SONORA = "Director de Flujos.Config_Terra_Sonora.Config_Terra_Sonora";
	public static final int ID_ACT_CONFIG_TERRA_SONORA = 119;
	public static final String COD_ACT_CONFIG_TERRA_SITEBUILDER = "Director de Flujos.Config_Terra_SiteBuilder.Config_Terra_SiteBuilder";
	public static final int ID_ACT_CONFIG_TERRA_SITEBUILDER = 120;
	public static final String COD_ACT_CONFIG_TERRA_ANTIVIRUS = "Director de Flujos.Config_Terra_Antivirus.Config_Terra_Antivirus";
	public static final int ID_ACT_CONFIG_TERRA_ANTIVIRUS = 122;
	public static final String COD_ACT_CONFIG_AULA = "Director de Flujos.Configuracion_Aula.Configurar_Aula";
	public static final int ID_ACT_CONFIG_AULA = 52;
	public static final String COD_ACT_CONFIG_WEBTUTOR = "Director de Flujos.AsistenciaCliente.AsistenciaCliente";
	public static final int ID_ACT_CONFIG_WEBTUTOR = 97;
	public static final String COD_ACT_CONFIG_WEBFILTER_OPTENET = "Director de Flujos.Conf_Web_Filter.Conf_Web_Filter";
	public static final int ID_ACT_CONFIG_WEBFILTER_OPTENET = 102;
	public static final String COD_ACT_CONFIG_TERABOX = "Director de Flujos.Conf_Terabox.Conf_Terabox";
	public static final int ID_ACT_CONFIG_TERABOX = 105;
	public static final String COD_ACT_CONFIG_TERABOX_MAIL = "Director de Flujos.Conf_Terabox_Mail.Conf_Terabox_Mail";
	public static final int ID_ACT_CONFIG_TERABOX_MAIL = 129;
	
	public static final String OC_NO_APLICA_MODIFICACION_SVA = "-1";
	public static final String OC_MODIFICACION_SVA = "200";
	
	public static final String VALOR_ALTA_OPCO_SVA = "A";
	public static final String VALOR_BAJA_OPCO_SVA = "B";
	public static final String VALOR_MODIFICACION_OPCO_SVA = "M";
	/*FIN RQ6142 - WS Aula - mfmendez*/
	
	public static final String TR701 = "TR-701";
	public static final String TR703 = "TR-703";
	public static final String formatoFechaTR703S = "yyyyMMddHHmmss";
	public static final String TR703_VACIA="50028";
	public static final String TR703_VACIA2="50030";
		
	public static String codigoActividadPresnciaDigital_ST="Director de Flujos.Conf_Presencia_Digital_ST.Conf_Presencia_Digital_ST";
	public static String idActividadPresnciaDigital_ST="ACT_Presencia_Digital_ST";
	/*RQ.8595 - mfmendez*/
	public static final String FLAG_EQUIPO_PETICION="Y";
	public static final String FLAG_ENVIO_EQUIPO_SAP="A";
	public static final String FLAG_ES_MATERIAL_SAP="S";
	public static final String FLAG_NO_ES_MATERIAL_SAP="N";
	public static final String INTERFAZ_VPI_VISTA_GLOBAL = "VISTA_GLOBAL";
	public static final String INTERFAZ_VPI_NOTIFICACION_SAP = "VENTA_MINORISTAS_SAP";
	public static final String INTERFAZ_ST_VISTA_GLOBAL = "VISTA_GLOBAL_ST";
	public static final String INTERFAZ_ST_NOTIFICACION_SAP = "VENTA_MINORISTAS_SAP_ST";
	public static final String FECHA_CONTABILIZACION_INVALIDO = "INVALIDO";
	
	public static final String IDENTIFICADOR_ELEMENTO_PETICION_SAP = "ELEMENTO_PETICION";
	public static final String IDENTIFICADOR_MODEM_SAP = "MODEM";
	public static final String IDENTIFICADOR_DECO_SAP = "DECO";
	public static final String IDENTIFICADOR_TARJETA_SAP = "TARJETA";
	public static final String IDENTIFICADOR_ELEM_NO_SERIALIZADO_SAP = "ELEMENTO_NO_SERIALIZADO";
	
	public static final String INDICADOR_ATIEMPO_MENSAJE_A_SAP = "ATM";
	/*FIN - RQ.8595 - mfmendez*/
	/*Req Apoyo-Inhibir Actividad Actualizar Inventario BA (NO SERIAL y NO_SERIAL)*/
	public static final String VALOR_NO_SERIAL_ESPACIO = "NO SERIAL";
	public static final String VALOR_NO_SERIAL_HC = "NO_SERIAL";
	/*Req Apoyo-Inhibir Actividad Actualizar Inventario BA (NO SERIAL y NO_SERIAL)*/
	/*Req Extra - Actividad en Agendamiento en el Front*/
	public static final String INTERFAZ_CONSULTA_ACTUACION = "ACT_ESTADO";
	public static final String INTERFAZ_SOLICITUD_REAGENDAMIENTO = "OPCION_SELEC";
	/*FIN - Req Extra - Actividad en Agendamiento en el Front*/
	
	/*mfmendez - Cambio para agregar observaciones en creación de actuacion*/
	public static final String CODIGO_APERTURA_ST = "1. Código de apertura: ";
	public static final String OBSERVACION_APERTURA_ST = "2. Observaciones de Apertura: ";
	public static final String DIAGNOSTICO_PRIMER_NIVEL_ST = "3. Diagnóstico de primer nivel: ";
	public static final String DIAGNOSTICO_SEGUNDO_NIVEL_ST = "4. Diagnóstico de segundo nivel: ";	
	public static final String OBS_DIAGNOSTICO_SEG_NIVEL_ST = "5. Observaciones diagnóstico segundo nivel: ";
	public static final String CATEGORIA_AVERIA_ST = "6. Categoría de avería: ";
	public static final String CODIGO_PRIORIDAD_ST = "7. Código de prioridad: ";
	public static final String CODIGO_PS = "8. Codigo del PS: ";
	public static final String CODIGO_PAQUETES = "9. Código de paquetes: ";
	/*FIN - mfmendez - Cambio para agregar observaciones en creación de actuacion*/
	public static final int ID_ACTIVIDAD_INSTALAR_EQUIPO = 96;
	
	//Id de la actividad de instalar equipos de venta de contado de Quick Wins
	public static final int ID_ACTIVIDAD_INSTALAR_EQUIPOS = 124;
	
	
	public static final String CARACTERISTICAS_VISUALIZAR = "CaracterisiticasVisualizar";
	public static final String SEGMENTOS_CORPORATIVOS = "Segmentos_corporativos";
	
		
	/*RQ.10402 - mfmendez*/
	public static final String INTERFAZ_PRESENCIA_DIGITAL = "PRESENCIA_DIGITAL";
	public static final String INTERFAZ_CONF_MEDIACION_MOVIL = "Conf_Mediacion_Movil";
	public static final int VALOR_OC_SOLTEC_PRESENCIA_DIGITAL = 9999;
	public static final String FORMATO_FECHA = "dd/MM/yyyy HH:mm:ss";
	public static final Long CARACT_PDG_CATALOGO = new Long(57);
	public static final Long CARACT_PDG_CUENTA_CORREO = new Long(58);
	public static final Long CARACT_PDG_INDICATIVO_TELEFONICO = new Long(59);
	public static final Long CARACT_PDG_MAPA = new Long(60);
	public static final Long CARACT_PDG_MENSAJE_VOLANTE_UNO = new Long(61);
	public static final Long CARACT_PDG_MENSAJE_VOLANTE_DOS = new Long(62);
	public static final Long CARACT_PDG_MENSAJE_VOLANTE_TRES = new Long(63);
	public static final Long CARACT_PDG_NUMERO_TELEFONICO = new Long(64);
	public static final Long CARACT_PDG_PALABRA_CLAVE_UNO = new Long(65);
	public static final Long CARACT_PDG_PROMOCION_UNO = new Long(66);
	public static final Long CARACT_PDG_PAGINA_INTERNET = new Long(67);
	public static final Long CARACT_PDG_REGION_PAUTA = new Long(68);
	public static final Long URL = new Long(69);
	public static final String INTERFAZ_PDVA = "PDVA";
	/*FIN - RQ.10402 - mfmendez*/
	/**
	 * JOSE BAEZ
	 * INDRA - 2012
	 * REQ 2012 - 00012804 - Nuevas Caracteristicas de Planes de Presencia Digital
	 */
	public static final Long CARACT_PDG_PALABRA_CLAVE_DOS = new Long(74);
	public static final Long CARACT_PDG_PALABRA_CLAVE_TRES = new Long(75);
	public static final Long CARACT_PDG_PROMOCION_DOS = new Long(72);
	public static final Long CARACT_PDG_PROMOCION_TRES = new Long(73);
	public static final String PREFIJO_PDG = "PB";
	public static final int OPCO_PDG_ST = 99;	
	
    //Req. 13420 - Jesus Carvajal - 10/04/2012
	public static final String ESTADO_LINEA_DEDICADO = "PAR DEDICADO";
	//Arreglo para hacer delta solo a las peticiones de alta
	public static final String DELTA_TICA_ID = "DELTA_TICA_ID";
	
	//Req. 15275 - Jorge Roa - 17/07/2012
	public static final String INTERFAZ_CONF_PAQUETE_MOVIL = "Conf_Paquete_Movil";
	public static final String CONF_PAQUETE_MOVIL = "CONF_PAQUETE_MOVIL";
	
	public static final String INTERFAZ_RECARGA_FIJA_MOVIL = "RECARGA_FIJA_MOVIL";
	public static final String INTERFAZ_CARACT_FIJA_MOVIL = "CARACT_FIJO_MOVIL";
	
	public static final String INTERFAZ_LB = "ACT_REFRESHSTB";
	public static final String INTERFAZ_BA = "ACT_REFRESHBA";
	
	public static final String CATEGORY_CODE = "COD_CTZ_CD";

	public static final String ACT_PGC = "Director de Flujos.PGC.Pendiente_Solucion_Comercial";

	public static final String INTERFAZ_CONF_CLIENTE_ZTE = "CONF_CLIENTE_ZTE";
	public static final String INTERFAZ_CONF_CAMARA_ZTE = "CONF_CAMARA_ZTE";
	public static final String INTERFAZ_CONF_CAMARA_ZTE_ST = "CONF_CAMARA_ZTE_ST";
	public static final String INTERFAZ_ACT_CAMARA_ZTE = "ACT_CAMARA_ZTE";
	public static final String INTERFAZ_ACT_CAMARA_ZTE_ST = "ACT_CAMARA_ZTE_ST";
	public static final String CAMERA_EQUIPMENT_TYPE = "CAMERA_EQUIPMENT_TYPE";
	
	public static final String CUASAL_SIRS = "715";
	public static final String CUASAL_AGENDA = "800";
	public static final String PS_PBX = "PS_PBX";
	
	public static final Integer CAMARA_ACTIVA = Integer.valueOf("1");
	public static final Integer CAMARA_INACTIVA = Integer.valueOf("2");
	public static final Integer CAMARA_PENDIENTE_ACTIVACION = Integer.valueOf("3");
	public static final Integer CAMARA_PENDIENTE_INACTIVACION = Integer.valueOf("4");
	public static final Integer CAMARA_ERROR = Integer.valueOf("5");
	
	public static final String ESTADO_SUSTENDIDO_DTH = "5";
	public static final Integer CAMARA_PENDIENTE_CAMBIO_PLAN = Integer.valueOf("7");
	public static final Integer CAMARA_CAMBIO_PLAN_ACTIVO = Integer.valueOf("6");
	
	public static final String MILISECONDS_TIMEOUT_NBIWS = "MILISECONDS_TIMEOUT_NBIWS";
	public static final String CAUSA_CLIENTE = "CAUSAL_QUIEBRE";
	public static final String STD = "DECO SD ADICIONAL";
	public static final String PVR = "DECO HD INCL CON PVR";
	public static final String HD = "DECO HD ADICIONAL";
	public static final String PS_TV_SD = "TELEVISION INDIVIDUAL";
	public static final String PS_TV_PVR = "DECO HD INCL CON PVR";
	public static final String PS_TV_HD = "DECO HD INCLUIDO";
	public static final Integer ACTIVIDAD_DESISTALAR_CRE = Integer.valueOf("91");
	public static final Integer ACTIVIDAD_DESISTALAR_MOTORIZADO = Integer.valueOf("136");
	 
	public static final String PS_HOMOLOGADO = "PS_HOMOLOGADO";
	
	public static final String OC_NO_INFORMADOS = "OC_NO_INFORMADOS";
	
	public static final String ACT_CONFIG_STB = "141";
	public static final String ACT_DESCONFIG_STB = "142";
	public static final int ACT_DESCONF_STB = 10;
	
	public static final String ACT_TMP_VELOCIDAD_ADICIONAL = "Director de Flujos.Vel_Adicional_TMP.Vel_Adicional_TMP"; 
	public static final String ACT_ASISTENCIA_REMOTA = "Director de Flujos.Asistencia_Remota.Asistencia_Remota";//RQ Cambio plan BA 25956 @dcardena 11/06/2014
	public static final int familiaPS_SVA = 316;
	public static final Long habilidadAsistenciaRemota = new Long(1018);//REQ ASISTENCITA REMOTA HABILIDAD @DCARDENA 08/05/2015
	public static final Long habilidadAsistenciaRemotaCambIP = new Long(1019);//REQ ASISTENCITA REMOTA HABILIDAD @DCARDENA 08/05/2015
	public static final int OcCambNumTransfTec = 5004;//REQ Cambio numero por transferencia tecnica @Dcardenas/ operacion comercial ALTA
	public static final int OcBajaCambNumTransfTec = 5006;//REQ Cambio numero por transferencia tecnica @Dcardenas/ operacion comercial BAJA
	
	public static final int ID_ACTIVIDAD_PGI_AVERIAS = 2007; // REQ TOA FASE III DCARDENA
	public static final int ID_ACTIVIDAD_PROCESO_BAJA = 2008; // REQ TOA FASE III DCARDENA
	public static final int ID_ACTIVIDAD_FLUJO_PE_TOA = 22; // REQ TOA FASE III DCARDENA
	public static final int ID_ACTIVIDAD_FLUJO_SOLUCION_STB = 4; // REQ TOA FASE III DCARDENA
	public static final int ID_ACTIVIDAD_FLUJO_SOLUCION_BA = 5; // REQ TOA FASE III DCARDENA
	public static final int ID_ACTIVIDAD_FLUJO_SOLUCION_TV = 6; // REQ TOA FASE III DCARDENA
	public static final String PS_VISITA_TECNICA = "PS_VISITA_TECNICA";
;}
