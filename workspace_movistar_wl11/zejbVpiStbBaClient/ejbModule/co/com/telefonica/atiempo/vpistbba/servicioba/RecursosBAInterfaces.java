/*
 * Created on 30-03-2007
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package co.com.telefonica.atiempo.vpistbba.servicioba;

import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.CreateException;
import javax.ejb.FinderException;
import javax.naming.NamingException;

import co.com.atiempo.dto.DecoTarDTO;
import co.com.atiempo.dto.ModemVpiDTO;
import co.com.atiempo.dto.PsVsOcVO;
import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;
import co.com.telefonica.atiempo.actividades.IActividadEJB;
import co.com.telefonica.atiempo.ejb.eb.Agenda_scLocal;
import co.com.telefonica.atiempo.ejb.eb.CamaraLocal;
import co.com.telefonica.atiempo.ejb.eb.Deco_Tarjeta_Info_SapLocal;
import co.com.telefonica.atiempo.ejb.eb.Deco_Tarjeta_Info_SapLocalHome;
import co.com.telefonica.atiempo.ejb.eb.ErrorLegadoLocal;
import co.com.telefonica.atiempo.ejb.eb.PeticionKey;
import co.com.telefonica.atiempo.ejb.eb.PeticionLocal;
import co.com.telefonica.atiempo.ejb.eb.Producto_servicio_en_vueloLocal;
import co.com.telefonica.atiempo.ejb.eb.Producto_servicio_peticionLocal;
import co.com.telefonica.atiempo.ejb.eb.Subpeticion_atisLocal;
import co.com.telefonica.atiempo.interfaces.atiempo.TR007S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR013S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR014S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR015S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR022S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR023S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR024S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR031S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR032S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR034S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR035S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR037S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR039S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR040S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR041S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR042S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR043S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR044S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR048S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR049EWideBand;
import co.com.telefonica.atiempo.interfaces.atiempo.TR051S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR135S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR137S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR515S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR610S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR701EEquipment;
import co.com.telefonica.atiempo.interfaces.atiempo.TR701S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR702S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR703S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR705S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR708S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR711S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR717S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR718S;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.vpistbba.actividades.parser.accion_masiva.AccionesMasivasByFileMSGDTO;
import co.com.telefonica.atiempo.vpistbba.dto.InformacionAdslDTO;

import com.telefonica_chile.atiempo.actividades.TnProcesoExcepcion;
import com.telefonica_chile.atiempo.utiles.Fecha;
import com.telefonica_chile.comun.ComunInterfaces;

import ejb.co.com.telefonica.atiempo.ejb.eb.caracteristicaPS.CaracteristicaPSLocal;
//CR 4860 - SIGRES - GUSTAVOG

/**
 * @author Karlo
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public interface RecursosBAInterfaces extends ComunInterfaces {

	public void solicitudConfiguracionBA(String peticion, String cod_actividad, PsVsOcVO psprim,boolean reversa, Integer actividadFlujo) throws ATiempoAppEx;
	public void recepcionConfiguracionBA(TR013S tr013s) throws ATiempoAppEx;
	public void enviaActualizaInventarioBA(ActividadEJBDTO act, String peticion, String id_actividad, boolean esAgendaSC) throws ATiempoAppEx;
	public void recepcionActualizaInventarioBA(TR007S tr007s, boolean esAgendaSC) throws ATiempoAppEx;
	public boolean enviarCambioNumeroBA(String peticion, String id_actividad) throws ATiempoAppEx;
	public void recepcionCambioNumeroBA(TR015S tr015s) throws ATiempoAppEx;
	public void enviarConfiguracionActualBA(String telefonoConsulta,String peticion, String id_actividad) throws ATiempoAppEx;
	public void enviarConfiguracionActualBA(String peticion, String id_actividad) throws ATiempoAppEx;
	public void recepcionConfiguracionActualBA(TR014S tr014s) throws ATiempoAppEx;
	public InformacionAdslDTO obtenerDatosAdsl(Long idPeticion) throws ATiempoAppEx;
	public InformacionAdslDTO obtenerDatosActualAdsl(Long idPeticion) throws ATiempoAppEx;
	public ArrayList recuperaModemsDePet(Long nroPeticion) throws ATiempoAppEx;
	public void grabaModemsBaVpi(Long nroPeticion,Long telAsignado,ArrayList modems);
	public ArrayList recuperaModemsBaVPi(Long nroPeticion);
	public ArrayList recuperaModemsAnyWay(Long nroPeticion);
	public boolean esNumerosDistintosBA(Long idPeticion) throws ATiempoAppEx;
	public void actualizaModemPorUtilizar(TR022S tr022s) throws ATiempoAppEx;
	public Long enviaModemPorUtilizar(long idPeticion, String ult4Digitos, long idContratista) throws ATiempoAppEx;
	public TR022S buscarRespuestaMensajeModem(Long idPeticion, Long idMensaje) throws ATiempoAppEx;
	public void enviaRefrescoBA(String nroPeticion)throws ATiempoAppEx;
	public void enviaRefrescoBA(String nroPeticion, String codigo, String codActividad)throws ATiempoAppEx;
	public boolean enviaConfiguracionTerra (Long idPeticion, ActividadEJBDTO act, PsVsOcVO psprim,boolean reversa) throws ATiempoAppEx;
	
	//	adocarmo - CR9664 - inicio
	//public boolean enviaConfiguracionAula365 (Long idPeticion, ActividadEJBDTO act, PsVsOcVO psprim, boolean reversa) throws ATiempoAppEx;
	public boolean enviaConfiguracionAula365 (Long idPeticion, ActividadEJBDTO act, PsVsOcVO psprim, Long licenseType, boolean reversa) throws ATiempoAppEx;	
	//	adocarmo - CR9664 - inicio
	
	public void recepcionConfiguracionTerra(TR023S tr023s) throws ATiempoAppEx;
	
	//	adocarmo - CR9664 - inicio
	public void recepcionConfiguracionAula365(TR024S tr024s) throws ATiempoAppEx;
	//	adocarmo - CR9664 - fin
	
	public void marcarNovedadAutomaticaCT(Long numPet) throws ATiempoAppEx;	
	public void obtenerCuentaCorreoSigres(Long idPeticion, String idServicio, String idActividad,PsVsOcVO psprim) throws ATiempoAppEx; //CR4860 SIGRES - GUSTAVO G
	public void obtenerCuentaCorreoSigres(Long idPeticion, String idActividad,PsVsOcVO psprim) throws ATiempoAppEx; //CR4860 SIGRES - GUSTAVO G
	public void recepcionObtenerCuentaCorreoSigres(TR043S tr043s)throws ATiempoAppEx; //CR4860 SIGRES - GUSTAVO G
	public void informarResultadoInstalacionSigres(Long idPeticion, String idActividad,ActividadEJBDTO actDto) throws ATiempoAppEx; //CR4860 SIGRES - GUSTAVO G
	public void recepcionInformarResultadoInstalacionSigres(TR034S tr034s) throws ATiempoAppEx; //CR4860 SIGRES - GUSTAVO G
//	CR9664 - adocarmo - inicio
	public void marcarNovedadAutomaticaCTAula(Long numPet) throws ATiempoAppEx;
//	CR9664 - adocarmo - fin	
//	CR10394 - Pablo Cawen - Inicio	
	public AccionesMasivasByFileMSGDTO getDatosPetAMxFile(long nroPetAtiempo) throws ATiempoAppEx;
//	CR10394 -Fin

//	cr16551 - p.cawen
	public boolean envioCambioNro(ActividadEJBDTO act)throws TnProcesoExcepcion;

	// CR4860 - Sigres - guido - 28/Abr
	public void solicitudConfiguracionSigresBA(String peticion, String cod_actividad, PsVsOcVO psprim,long psIPFijaPeticion, boolean reversa, Integer actividadFlujo, ActividadEJBDTO act) throws ATiempoAppEx;
	public void recepcionConfiguracionSigresBAAck(TR031S tr031s) throws ATiempoAppEx;
	public void recepcionConfiguracionSigresBAOk(TR032S tr032s) throws ATiempoAppEx;
	public void recepcionConfiguracionSigresBANovedad(TR034S tr034s) throws ATiempoAppEx;
	
//	CR4860 - Sigres -inicio- agonzalez- 14-05-2008
	public void solicitudConfiguracionSigresCambioPlan(String peticion, String cod_actividad, PsVsOcVO psprim,long psIPFijaPeticion, boolean reversa, Integer actividadFlujo, boolean noIncluirPSPrim, ActividadEJBDTO act) throws ATiempoAppEx;
	public void recepcionConfiguracionSigresCambioPlanBAAck(TR039S tr039s) throws ATiempoAppEx;
	public void recepcionConfiguracionSigresCambioPlanBAOk(TR040S tr040s) throws ATiempoAppEx;
	public boolean seDebeEntrarAInstalar (Long nroPeticion,Integer idActividadFlujo) throws ATiempoAppEx;
	public boolean huboCambiosPuertoIp (Long nroPeticion) throws ATiempoAppEx;
	
	//CR4860 Sigres - Gustavo - 22-05-2008
	public void enviarTR030E(String peticion,PsVsOcVO psprim,long psIPFijaPeticion, boolean reversa,PeticionLocal peticionLocal,Long IdCorrelativo, ActividadEJBDTO act) throws NamingException, FinderException, ATiempoAppEx;
	public void enviarTR043E(PeticionLocal peticionLocal,Long IdCorrelativo,String phoneNumber) throws ATiempoAppEx;
	public void enviarTR033E(Long idPeticion,PeticionLocal peticionLocal,Long IdCorrelativo,boolean reversa) throws ATiempoAppEx, FinderException, NamingException; 
	public void enviarTR038E(String peticion,PsVsOcVO psprim,long psIPFijaPeticion, boolean reversa,PeticionLocal peticionLocal,Long IdCorrelativo, boolean noIncluirPSPrim, ActividadEJBDTO act) throws NamingException, FinderException, ATiempoAppEx;
	public void enviarTR042E(Long peticion,PsVsOcVO psprim,PeticionLocal peticionLocal,Long IdCorrelativo) throws NamingException, FinderException, ATiempoAppEx;
	public void enviarTR036E(Long peticion,PsVsOcVO psprim,PeticionLocal peticionLocal,Long IdCorrelativo) throws NamingException, FinderException, ATiempoAppEx;
	public void enviarTR041E(Long peticion,PsVsOcVO psprim,PeticionLocal peticionLocal,Long IdCorrelativo) throws NamingException, FinderException, ATiempoAppEx;

	/**
	 * recepcionSuspensionReconexionBASigres
	 * 
	 * Procesa la suspension y reconexion de SIGRES.
	 * 
	 * @author Gonzalo Arreche - CR4860. 
	 * @param tr042s
	 * @throws ATiempoAppEx
	 */
	public void recepcionSuspensionReconexionBASigres(TR042S tr042s) throws ATiempoAppEx;
	/**
	 * enviaSuspensionReconexionBASigres
	 * 
	 * Envía la suspension y reconexion de SIGRES, TR042E.
	 * 
	 * @author Gonzalo Arreche CR-4860
	 */
	public void enviaSuspensionReconexionBASigres(Long peticion, String id_actividad,PsVsOcVO psprim) throws ATiempoAppEx;
	
	
//	CR4860 - Sigres -inicio- agonzalez- 24-06-2008
	public void recepcionBajaBASigres(TR037S tr037s) throws ATiempoAppEx;
	public void enviaBajaBASigres(Long peticion, String id_actividad, PsVsOcVO psprim) throws ATiempoAppEx;
	
	
	public void enviaNotificacionCambioNumero(Long peticion, String id_actividad, PsVsOcVO psprim)throws ATiempoAppEx;


//  CR4860 - Sigres - inicio -agonzalez 03/07/2008 
	public void enviarConfiguracionActualBASigres(String telefonoConsulta,String peticion, String id_actividad, String codActividad) throws ATiempoAppEx;
	public void enviarConfiguracionActualBASigres(String peticion, String id_actividad, String codActividad) throws ATiempoAppEx;
	public void recepcionConfiguracionActualBASigres(TR035S tr035s) throws ATiempoAppEx;
//	 CR4860 - Sigres - inicio -agonzalez 10/07/2008 
	public void recepcionModificarIdOperadora(TR041S tr041s) throws ATiempoAppEx;
	public boolean enviarModificarIdOperadora(String peticion, String  id_actividad) throws ATiempoAppEx;
	
//	CR4860 - Sigres -inicio- agonzalez- 15-07-2008 AT-1480;
	public void solicitudConfiguracionSigresTraslado(String peticion, String cod_actividad, PsVsOcVO psprim,boolean reversa, Integer actividadFlujo, ActividadEJBDTO act) throws ATiempoAppEx;
	
//	CR14657 - Granite - agonz- 16-10-2008
	public void enviaActualizaInventarioBAGranite(String peticion, String codigoActividad,ActividadEJBDTO act)throws ATiempoAppEx;
	public void recepcionActualizaInventarioBAGranite(TR515S tr515s) throws ATiempoAppEx;
	

	//	 @author 808026 -Defecto 20476 - 24/11/2008 -
	public boolean esTrasladoBa(Long idPeticion)throws ATiempoAppEx;
	public boolean envioCambioNroSigres(ActividadEJBDTO act) throws TnProcesoExcepcion;
	// @author 808026 - CR 27922 - 28/07/2009 - 
	public boolean esBajaBA(ArrayList listadoPS)throws ATiempoAppEx;
	
	public void solicitudConfiguracionSigresCambioPlanEnVuelo(String peticion, String cod_actividad, PsVsOcVO psprim,boolean reversa, Integer actividadFlujo, PsVsOcVO psprimOld) throws ATiempoAppEx;
	public void removeProductoPeticionEnVuelo(Long numeroPeticion, String tipoPeticion) throws ATiempoAppEx;
	public Producto_servicio_en_vueloLocal almacenaProductoPeticionEnVuelo(Integer numeroPeticion, Integer psNuevo, String tipoPeticion, String psViejo)throws ATiempoAppEx;
	
	public Long solicitarPrimeraFacturaInternetEquipado(Long numeroPeticion, Collection equipos, String codActividad, String tipoOperacion,Integer actiID)throws ATiempoAppEx;
	
	public TR044S buscarRespuestaFacturaInternetEquipado(Long idPeticion, Long idMensaje) throws ATiempoAppEx;
	public boolean validarEstadosMensajesInternetEquipado (Long idPeticion) throws ATiempoAppEx;
	
	public void enviarConfiguracionWebFilterOptenet(ActividadEJBDTO act, Long numeroPeticion, String codActividad, PsVsOcVO psprim) throws ATiempoAppEx;
	
	public void recibirConfiguracionWebFilterOptenet(TR048S tr048s) throws ATiempoAppEx;
	
	public void creacionActuacionAgendaSC(Long idPeticion, java.sql.Timestamp fechaReagendamiento, String tipoOC, String actividad, ActividadEJBDTO act);	
	public void recepcionCreacionActuacionAgendaSC(TR701S tr701s);
	
	public void recepcionActivarDecosTarjetasAgendaSC (TR708S tr708s) throws ATiempoAppEx;
	public void enviaActivarDecosTarjetasAgendaSC(String idActuacion, String idMensajePeticion) throws ATiempoAppEx;
	public boolean esAgendaSC(Long atisRequestNumber) throws ATiempoAppEx;
	
	public void recepcionCierreActuacion(TR711S tr711s) throws ATiempoAppEx;
	
	//@author idrincon reagendamiento agenda sc - 09/09/2010
	public void reagendamientoAgendaSC(TR705S tr705s);
	//fin reagendamiento 09/09/2010
	
	//@idrincon -req 1038 - 03/10/2010
	public boolean esTrasladoTv(Long idPeticion) throws ATiempoAppEx;
	// fin - req 1038
	
	//req 3183
	// moficacion - mfmendez - RQ. 6142 - envio de la actividad
	public void enviarConfiguracionTeraBox(ActividadEJBDTO act, Long numeroPeticion, String codActividad, PsVsOcVO psprim) throws ATiempoAppEx;
	
	public void enviarCorreoTeraBox(ActividadEJBDTO act, Long numeroPeticion, String codActividad, PsVsOcVO psprim) throws ATiempoAppEx;
	
	//req 3183
	public void recibirConfiguracionTeraBox(TR051S tr051s)throws ATiempoAppEx;
	
	public void configuracionEnvioCorreoTeraBox(TR718S tr718s)throws ATiempoAppEx;
	
	//req 4656 - 10/02/2011 - fmendez
	public TR049EWideBand getWideBand(PeticionLocal peticionLocal);

	//req 3709
	public String llamadoConfModemAutoInstalacion(ModemVpiDTO modem, String codActividad, String idMensajePadre, boolean esBaja, boolean esCierreActuacion) throws ATiempoAppEx;
	public String[] enviarConfiguracionModemAutoinstalacion(ModemVpiDTO modem, String codActividad, String idMensajePadre) throws ATiempoAppEx;
	public void recibirConfiguracionModemAutoinstalacion(TR135S tr135s, boolean esBaja, boolean esCierreActuacion) throws ATiempoAppEx;
	public TR135S buscarRespuestaConfiguracionModemAutoInstalacion(Long idPeticion, Long idMensaje) throws ATiempoAppEx;
	
		//req 3709
	public void grabarFechaEntregaKitAutoInst(Long petiNumero, Fecha fechaEntregaKit) throws ATiempoAppEx;
	public void grabarGuiaKitAutoInst(Long petiNumero, String numGuia) throws ATiempoAppEx;
	public void grabarFechaGuiaKitAutoInst(Long petiNumero, Fecha fechaGuiaKit) throws ATiempoAppEx;
	public String obtenerGuiaKitAutoInst(Long petiNumero) throws ATiempoAppEx;
	
	//req 3709
	public void recibirmensajeACS(TR137S tr137s) throws ATiempoAppEx;
	//req 3709
	public void enviarSMSAutoInstalacion(Long petiNumero, String usuario)throws ATiempoAppEx;
	public void recepcionActivarModemsAgendaSC(TR717S tr717s) throws ATiempoAppEx;
	public void enviaActivarModemsAgendaSC(String idActuacion, String idMensajePeticion, String idMensajePeticionHijo) throws ATiempoAppEx;
	//req 3709
	public void grabarFechaAutoInst(Long petiNumero, Fecha fechaAutoInst) throws ATiempoAppEx;
	//req 3709
	public String recuperaNumeroGuiaAutoInst(Long petiNumero) throws ATiempoAppEx;
	//req 3709 idrincon
	public void actualizarRecursosBA( Long petiNumero ) throws ATiempoAppEx;
	//req 3709 idrincon
	public boolean validarEnvioTrEnPGACS( String idActuacion ) throws ATiempoAppEx;
	//req 3709
	public void recepcionCreacionActuacionAgendaSCEnPGACS(TR701S tr701s);
	//req 3709
	public void recepcionCierreActuacionPGACS(TR711S tr711s) throws ATiempoAppEx ;
	//req 3709
	public ArrayList recuperaModemsBaVPiReversaAutoInst(Long nroPeticion);
	public boolean validarConfiguracionModemAutoinstalacion(Long nroPeticion, String codActividad);
	public boolean esCambioDeLinea(Long petiNumero);	
	/*RQ 5606 - Internet Movil*/
	public void enviarConfiguracionInternetMovil(ActividadEJBDTO act, Long nroPeticion) throws ATiempoAppEx;
	public void procesarRespuestaInternetMovil(TR610S tr610s) throws ATiempoAppEx;
	/*FIN - RQ 5606 - Internet Movil*/
	/*RQ 6142 - WS Aula*/
	public String validaOperacionComercialSVA(ActividadEJBDTO act, Long nroPeticion) throws ATiempoAppEx;
	public PsVsOcVO devuelvePSPrioridadAltaSVA(ActividadEJBDTO act) throws ATiempoAppEx;
	/*FIN - RQ 6142 - WS Aula*/
	public Long consultarDisponibilidadAgendaSC (String petiNumero, String idSchedule, String fechaIni, String fechaFin, String actividad) throws ATiempoAppEx;
	public void respuestaDisponibilidadAgendaSC(TR703S tr703s) throws ATiempoAppEx;
	
	public void enviarConsultaActuacionASC(ActividadEJBDTO act, Long nroPeticion, String actGeneradora) throws ATiempoAppEx;
	public void procesarRespuestaConsultaActuacionASC(TR702S tr702s) throws ATiempoAppEx;
	public Long enviarSolicitudReagendamientoASC(Long nroPeticion, String idActuacion,String opcionReagendamiento) throws ATiempoAppEx;
	public void procesarRespuestaSolicitudReagendamientoASC(TR705S tr705s) throws ATiempoAppEx;
	/*mfmendez*/	
	public boolean validarPSEnvioAltaActuacionPGC(Long idPeticionAT);
	/**
	 * @param idPeticion
	 * @param id_actuacion
	 * @param id
	 */
	public void enviarRefrecarDatos(Long idPeticion) throws ATiempoAppEx;
	//TOAserviceBean
	public String extraerVelocidadPlanYPSPrioridadAlta(Long petiNumero)throws ATiempoAppEx;
	public String obtenerCaracteristicaPeticion(Producto_servicio_peticionLocal producto_servicio_peticionLocal, Long caracteristica)throws ATiempoAppEx;
	public CaracteristicaPSLocal obtenerProductoServicio(Subpeticion_atisLocal subpeticion_atisLocal) throws NamingException, ATiempoAppEx ;
	//TOAServiceBean
	//	REQ TOA FASE I @DCARDENA 15/05/2015
	public void enviaInstalarTOA(ActividadEJBDTO act) throws ATiempoAppEx;	
	public void recepcionInstalarTOA(TR717S tr717s) throws ATiempoAppEx;
	// FIN REQ TOA FASE I
	
	
	public TR701EEquipment setDatosSAPDecoTarjeta(Deco_Tarjeta_Info_SapLocalHome deco_tar_inf_sapLocalHome, TR701EEquipment tr701eEquipment, String id_deco, String id_tarjeta, Long idPeticion);
	
	public void setValoresCamara(TR701EEquipment tr701eEquipment, CamaraLocal camara) throws Exception;

	public boolean esPostventa(PeticionLocal peticionLocal);
	
	public void agendamientoDecosTR711(ArrayList listaDecos, PeticionLocal peticionLocal, TR711S tr711s, ActividadEJBDTO actDto, IActividadEJB actividadEJB, boolean b, String codBandeja);

	public void insertarCausalesCnaPeticion(PeticionLocal peticion, String cod_actividad_generadora, Long long1, Integer idActividadFlujo) throws ATiempoAppEx, NamingException, FinderException, CreateException;
	
	public String getNombreBandeja(String plataforma);
	
	public ErrorLegadoLocal getErrorLegado(String errorCode, String string);
	
	public void setQuiebrePcAgendaSc(Collection breakPairs, Long peti_numero, Collection productoSrvicioPeticionCollection, String codigoActividad);
	
	public boolean esAutoinstalacion(Agenda_scLocal agendaSCLocal) throws NamingException, FinderException, ATiempoAppEx;
	
	public boolean validaBajAltaMigrDeco(Collection producto_servicio_peticion);
	
	public String accionModemNaked(String operationComercial, int famOpLocal) throws FinderException, NamingException;
	
	public int cantidadDecosHC(Long idPeticion, int familiaDecoPVRTV, int cantDecosDesinsPVR) throws NamingException, FinderException;

	public void ingresoInformacionSAP(Deco_Tarjeta_Info_SapLocal dec_tar_sapdecoLocal, DecoTarDTO equipo, PeticionKey peticionKey, Deco_Tarjeta_Info_SapLocalHome deco_tar_inf_sapLocalHome);
}
