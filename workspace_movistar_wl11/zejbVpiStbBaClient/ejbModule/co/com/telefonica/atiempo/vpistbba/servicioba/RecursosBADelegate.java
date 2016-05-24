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
import co.com.telefonica.atiempo.vpistbba.serviciosba.ejb.RecursosBALocal;
import co.com.telefonica.atiempo.vpistbba.serviciosba.ejb.RecursosBALocalHome;

import com.telefonica_chile.atiempo.actividades.TnProcesoExcepcion;
import com.telefonica_chile.atiempo.utiles.Fecha;
import com.telefonica_chile.atiempo.utiles.HomeFactory;

import ejb.co.com.telefonica.atiempo.ejb.eb.caracteristicaPS.CaracteristicaPSLocal;
//CR4860 - SIGRES - GUSTAVOG

/**
 * @author Karlo
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class RecursosBADelegate implements  RecursosBAInterfaces{ 


	private RecursosBALocal servicios;

	public RecursosBADelegate() throws ATiempoAppEx {
		try {
			
			servicios = ((RecursosBALocalHome) HomeFactory.getHome(RecursosBALocalHome.JNDI_NAME)).create();
			
		} catch (NamingException nex) {
			throw new ATiempoAppEx(ATiempoAppEx.NAMING, nex);
		} catch (CreateException cex) {
			throw new ATiempoAppEx(ATiempoAppEx.CREATE, cex);
		}
	}


	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.servicioba.RecursosBAInterfaces#solicitudConfiguracionBA(java.lang.String, java.lang.String)
	 */
	public void solicitudConfiguracionBA(String peticion, String cod_actividad, PsVsOcVO psprim, boolean reversa, Integer actividadFlujo) throws ATiempoAppEx {
		
		
		servicios.solicitudConfiguracionBA(peticion,cod_actividad,psprim,reversa,actividadFlujo);
		
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.servicioba.RecursosBAInterfaces#recepcionConfiguracionBA(co.com.telefonica.atiempo.interfaces.atiempo.TR013S)
	 */
	public void recepcionConfiguracionBA(TR013S tr013s) throws ATiempoAppEx {
		
		
		servicios.recepcionConfiguracionBA(tr013s);
	}


	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.servicioba.RecursosBAInterfaces#enviaActualizaInventarioBA(java.lang.String, java.lang.String)
	 */
	public void enviaActualizaInventarioBA(ActividadEJBDTO act, String peticion, String id_actividad, boolean esAgendaSC) throws ATiempoAppEx {
		servicios.enviaActualizaInventarioBA(act, peticion, id_actividad, esAgendaSC);
	}


	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.servicioba.RecursosBAInterfaces#recepcionActualizaInventarioBA(co.com.telefonica.atiempo.interfaces.atiempo.TR007S)
	 */
	public void recepcionActualizaInventarioBA(TR007S tr007s, boolean esAgendaSC) throws ATiempoAppEx {
		servicios.recepcionActualizaInventarioBA(tr007s,esAgendaSC);
		
	}


	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.servicioba.RecursosBAInterfaces#enviarCambioNumeroBA(java.lang.String, java.lang.String)
	 */
	public boolean enviarCambioNumeroBA(String peticion, String id_actividad) throws ATiempoAppEx {
		
		
		return servicios.enviarCambioNumeroBA(peticion, id_actividad);
		
	}


	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.servicioba.RecursosBAInterfaces#recepcionCambioNumeroBA(co.com.telefonica.atiempo.interfaces.atiempo.TR015S)
	 */
	public void recepcionCambioNumeroBA(TR015S tr015s) throws ATiempoAppEx {
		
		
		servicios.recepcionCambioNumeroBA(tr015s);
		
	}


	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.servicioba.RecursosBAInterfaces#enviarConfiguracionActualBA(java.lang.String, java.lang.String)
	 */
//	SIGRES - refrescar datos - 03/07/2008 con la implementacion de SIGRES se deja de invocar este metodo
	public void enviarConfiguracionActualBA(String peticion, String id_actividad) throws ATiempoAppEx {
		
		
		servicios.enviarConfiguracionActualBA(peticion, id_actividad);
		
	}


	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.servicioba.RecursosBAInterfaces#recepcionConfiguracionActualBA(co.com.telefonica.atiempo.interfaces.atiempo.TR014S)
	 */
	public void recepcionConfiguracionActualBA(TR014S tr014s) throws ATiempoAppEx {
		
		
		servicios.recepcionConfiguracionActualBA(tr014s);
	}


	public InformacionAdslDTO obtenerDatosAdsl(Long idPeticion) throws ATiempoAppEx {
		return servicios.obtenerDatosAdsl( idPeticion );
	}
	public boolean noHayModemParaActualizarInventarioBa(Long long1) throws ATiempoAppEx {
		
		return servicios.noHayModemParaActualizarInventarioBa(long1);
	}


	public ArrayList recuperaModemsDePet(Long nroPeticion) throws ATiempoAppEx
	{
		return servicios.recuperaModemsDePet(nroPeticion);
	}
	
	public InformacionAdslDTO obtenerDatosActualAdsl(Long idPeticion) throws ATiempoAppEx
	{
		return servicios.obtenerDatosActualAdsl(idPeticion);
	}


	public void grabaModemsBaVpi(Long nroPeticion, Long telAsignado, ArrayList modems)
	{
		servicios.grabaModemsBaVpi(nroPeticion,telAsignado,modems);	
	}

	public ArrayList recuperaModemsBaVPi(Long nroPeticion)
	{
		return servicios.recuperaModemsBaVPi(nroPeticion);
	}


	public ArrayList recuperaModemsAnyWay(Long nroPeticion)
	{
		return servicios.recuperaModemsAnyWay(nroPeticion);
	}


	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.servicioba.RecursosBAInterfaces#numerosDistintosBA(java.lang.Long)
	 */
	public boolean esNumerosDistintosBA(Long idPeticion) throws ATiempoAppEx {
		return servicios.esNumerosDistintosBA(idPeticion);
	}


	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.servicioba.RecursosBAInterfaces#enviarConfiguracionActualBA(java.lang.String, java.lang.String, java.lang.String)
	 */
//	SIGRES - refrescar datos - 03/07/2008 con la implementacion de SIGRES se deja de invocar este metodo
	public void enviarConfiguracionActualBA(String telefonoConsulta, String peticion, String id_actividad) throws ATiempoAppEx {
		servicios.enviarConfiguracionActualBA(telefonoConsulta,peticion,id_actividad);
		
	}


	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.servicioba.RecursosBAInterfaces#actualizaModemPorUtilizar(co.com.telefonica.atiempo.interfaces.atiempo.TR022S)
	 */
	public void actualizaModemPorUtilizar(TR022S tr022s) throws ATiempoAppEx
	{
		servicios.actualizaModemPorUtilizar(tr022s);	
	}


	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.servicioba.RecursosBAInterfaces#enviaModemPorUtilizar(long, java.lang.String, long)
	 */
	public Long enviaModemPorUtilizar(long idPeticion, String ult4Digitos, long idContratista) throws ATiempoAppEx
	{
		return servicios.enviaModemPorUtilizar(idPeticion,ult4Digitos,idContratista);
	}


	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.servicioba.RecursosBAInterfaces#buscarRespuestaMensajeModem(java.lang.Long, java.lang.Long)
	 */
	public TR022S buscarRespuestaMensajeModem(Long idPeticion, Long idMensaje) throws ATiempoAppEx
	{
		return servicios.buscarRespuestaMensajeModem(idPeticion,idMensaje);
	}


	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.servicioba.RecursosBAInterfaces#enviaRefrescoBA(java.lang.String)
	 */
	public void enviaRefrescoBA(String nroPeticion) throws ATiempoAppEx {
		servicios.enviaRefrescoBA(nroPeticion);
	}


	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.servicioba.RecursosBAInterfaces#enviaConfiguracionTerra(java.lang.Long, java.lang.String, co.com.atiempo.dto.PsVsOcVO, boolean)
	 */
	public boolean enviaConfiguracionTerra(Long idPeticion, ActividadEJBDTO act, PsVsOcVO psprim, boolean reversa) throws ATiempoAppEx {
		return servicios.enviaConfiguracionTerra(idPeticion, act,psprim,reversa);
		
	}

	//	adocarmo - CR9664 - inicio
	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.servicioba.RecursosBAInterfaces#enviaConfiguracionTerra(java.lang.Long, java.lang.String, co.com.atiempo.dto.PsVsOcVO, boolean)
	 */
	/* 
	public boolean enviaConfiguracionAula365(Long idPeticion, ActividadEJBDTO act, PsVsOcVO psprim, boolean reversa) throws ATiempoAppEx {
		return servicios.enviaConfiguracionAula365(idPeticion, act,psprim,reversa);	
	}
	*/
	public boolean enviaConfiguracionAula365(Long idPeticion, ActividadEJBDTO act, PsVsOcVO psprim, Long licenseType,boolean reversa) throws ATiempoAppEx {
		return servicios.enviaConfiguracionAula365(idPeticion, act,psprim,licenseType,reversa);

	}	
	//	adocarmo - CR9664 - fin


	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.servicioba.RecursosBAInterfaces#recepcionConfiguracionTerra(co.com.telefonica.atiempo.interfaces.atiempo.TR023S)
	 */
	public void recepcionConfiguracionTerra(TR023S tr023s) throws ATiempoAppEx {
		servicios.recepcionConfiguracionTerra(tr023s);
	}

	// adocarmo - CR9664 - inicio
	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.servicioba.RecursosBAInterfaces#recepcionConfiguracionTerra(co.com.telefonica.atiempo.interfaces.atiempo.TR023S)
	 */
	public void recepcionConfiguracionAula365(TR024S tr024s) throws ATiempoAppEx {
		servicios.recepcionConfiguracionAula365(tr024s);
	}	
	// adocarmo - CR9664 - fin	

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.servicioba.RecursosBAInterfaces#marcarNovedadAutomaticaCT(java.lang.Long)
	 */
	public void marcarNovedadAutomaticaCT(Long numPet) throws ATiempoAppEx
	{
		servicios.marcarNovedadAutomaticaCT(numPet);	
	}

//	CR9664 - adocarmo - inicio
	public void marcarNovedadAutomaticaCTAula(Long numPet) throws ATiempoAppEx
	{
		servicios.marcarNovedadAutomaticaCTAula(numPet);	
	}
//	CR9664 - adocarmo - fin	

//	CR10394 - Pablo Cawen - Inicio
	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.servicioba.RecursosBAInterfaces#getDatosPetAMxFile(long)
	 */
	public AccionesMasivasByFileMSGDTO getDatosPetAMxFile(long nroPet) throws ATiempoAppEx {
		return servicios.getDatosPetAMxFile(nroPet);
	}
//	CR10394 - Fin

//	cr16551 p.cawen
	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.servicioba.RecursosBAInterfaces#envioCambioNro(co.com.telefonica.atiempo.actividades.ActividadEJBDTO)
	 */
	public boolean envioCambioNro(ActividadEJBDTO act) throws TnProcesoExcepcion {
		return servicios.envioCambioNro(act);
	}

	//	CR4860 - SIGRES - GUSTAVOG
	public void obtenerCuentaCorreoSigres(Long idPeticion, String idServicio, String idActividad,PsVsOcVO psprim) throws ATiempoAppEx {
		servicios.obtenerCuentaCorreoSigres(idPeticion,idServicio, idActividad,psprim);
	}

	public void obtenerCuentaCorreoSigres(Long idPeticion, String idActividad,PsVsOcVO psprim) throws ATiempoAppEx {
		servicios.obtenerCuentaCorreoSigres(idPeticion,idActividad,psprim);
	}

	public void recepcionObtenerCuentaCorreoSigres(TR043S tr023s) throws ATiempoAppEx{
		servicios.recepcionObtenerCuentaCorreoSigres(tr023s);
	}
	
	public void informarResultadoInstalacionSigres(Long idPeticion, String idActividad,ActividadEJBDTO actDto) throws ATiempoAppEx{
		servicios.informarResultadoInstalacionSigres(idPeticion,idActividad,actDto);
	}
	
	public void recepcionInformarResultadoInstalacionSigres(TR034S tr034s) throws ATiempoAppEx{
		servicios.recepcionInformarResultadoInstalacionSigres(tr034s);
	}
	  //HASTA AQUI CR4860 - SIGRES - GUSTAVOG

	
	// CR4860 - Sigres - guido - 28/Abr
	public void solicitudConfiguracionSigresBA(String peticion, String cod_actividad, PsVsOcVO psprim,long psIPFijaPeticion, boolean reversa, Integer actividadFlujo, ActividadEJBDTO act) throws ATiempoAppEx {
		servicios.solicitudConfiguracionSigresBA(peticion,cod_actividad,psprim,psIPFijaPeticion, reversa,actividadFlujo, act);
	}
	public void recepcionConfiguracionSigresBAAck(TR031S tr031s) throws ATiempoAppEx {
		servicios.recepcionConfiguracionSigresBAAck(tr031s);
	}
	public void recepcionConfiguracionSigresBAOk(TR032S tr032s) throws ATiempoAppEx 	{
		servicios.recepcionConfiguracionSigresBAOk(tr032s);
	}
	public void recepcionConfiguracionSigresBANovedad(TR034S tr034s) throws ATiempoAppEx {
		servicios.recepcionConfiguracionSigresBANovedad(tr034s);
	}
	
//	CR4860 - Sigres -inicio- agonzalez- 14-05-2008 
	public void solicitudConfiguracionSigresCambioPlan(String peticion, String cod_actividad, PsVsOcVO psprim,long psIPFijaPeticion, boolean reversa, Integer actividadFlujo,boolean noIncluirPSPrim, ActividadEJBDTO act) throws ATiempoAppEx {
		servicios.solicitudConfiguracionSigresCambioPlan(peticion,cod_actividad,psprim,psIPFijaPeticion, reversa,actividadFlujo, noIncluirPSPrim, act);
	}	
	public void recepcionConfiguracionSigresCambioPlanBAAck(TR039S tr039s) throws ATiempoAppEx {
			servicios.recepcionConfiguracionSigresCambioPlanBAAck(tr039s);
		}
	public void recepcionConfiguracionSigresCambioPlanBAOk(TR040S tr040s) throws ATiempoAppEx 	{
			servicios.recepcionConfiguracionSigresCambioPlanBAOk(tr040s);
	}
	public boolean seDebeEntrarAInstalar (Long nroPeticion,Integer idActividadFlujo) throws ATiempoAppEx{
			return servicios.seDebeEntrarAInstalar (nroPeticion,idActividadFlujo);
	}
	
	public boolean huboCambiosPuertoIp (Long nroPeticion) throws ATiempoAppEx{
				return servicios.huboCambiosPuertoIp (nroPeticion);
		}
		
	//CR4860 - Sigres - Gustavo - 22-05-2008
	
	public void enviarTR030E(String peticion,PsVsOcVO psprim,long psIPFijaPeticion, boolean reversa,PeticionLocal peticionLocal,Long IdCorrelativo, ActividadEJBDTO act) throws NamingException, FinderException, ATiempoAppEx {
		servicios.enviarTR030E(peticion,psprim,psIPFijaPeticion, reversa,peticionLocal,IdCorrelativo, act);
	}
	
	public void enviarTR043E(PeticionLocal peticionLocal,Long IdCorrelativo,String phoneNumber) throws ATiempoAppEx {
		servicios.enviarTR043E(peticionLocal,IdCorrelativo,phoneNumber);
	}
	
	public void enviarTR033E(Long idPeticion,PeticionLocal peticionLocal,Long IdCorrelativo,boolean reversa) throws ATiempoAppEx, FinderException, NamingException {
		servicios.enviarTR033E(idPeticion,peticionLocal,IdCorrelativo,reversa);
	}
	
	public void enviarTR038E(String peticion,PsVsOcVO psprim,long psIPFijaPeticion, boolean reversa,PeticionLocal peticionLocal,Long IdCorrelativo, boolean noIncluirPSPrim, ActividadEJBDTO act) throws NamingException, FinderException, ATiempoAppEx {
		servicios.enviarTR038E(peticion,psprim,psIPFijaPeticion,reversa,peticionLocal,IdCorrelativo, noIncluirPSPrim, act);
	}
	
	public void enviarTR042E(Long peticion,PsVsOcVO psprim,PeticionLocal peticionLocal,Long IdCorrelativo) throws NamingException, FinderException, ATiempoAppEx {
		servicios.enviarTR042E(peticion,psprim,peticionLocal,IdCorrelativo);
	}
	
	public void enviarTR036E(Long peticion,PsVsOcVO psprim,PeticionLocal peticionLocal,Long IdCorrelativo) throws NamingException, FinderException, ATiempoAppEx {
		servicios.enviarTR036E(peticion,psprim,peticionLocal,IdCorrelativo);
	}
	
	public void enviarTR041E(Long peticion,PsVsOcVO psprim,PeticionLocal peticionLocal,Long IdCorrelativo) throws NamingException, FinderException, ATiempoAppEx {
		servicios.enviarTR041E(peticion,psprim,peticionLocal,IdCorrelativo);
	}
	
	public void recepcionSuspensionReconexionBASigres(TR042S tr042s) throws ATiempoAppEx{
		servicios.recepcionSuspensionReconexionBASigres(tr042s);
	}

	public void enviaSuspensionReconexionBASigres(Long peticion, String id_actividad, PsVsOcVO psprim) throws ATiempoAppEx{
		servicios.enviaSuspensionReconexionBASigres(peticion, id_actividad, psprim);
	}
	
//	CR4860 - Sigres -inicio- agonzalez- 14-05-2008 
	public void recepcionBajaBASigres(TR037S tr037s) throws ATiempoAppEx{
		servicios.recepcionBajaBASigres(tr037s);
	}
	public void enviaBajaBASigres(Long peticion, String id_actividad, PsVsOcVO psprim) throws ATiempoAppEx{
		servicios.enviaBajaBASigres(peticion,id_actividad,psprim);
	}
	
	
	public void enviaNotificacionCambioNumero(Long peticion, String id_actividad, PsVsOcVO psprim) throws ATiempoAppEx {
		servicios.enviaNotificacionCambioNumero( peticion,  id_actividad,  psprim);
	}
	
	
//	CR4860 - Sigres -inicio- agonzalez- 03/07/2008 	
	public void enviarConfiguracionActualBASigres(String peticion, String id_actividad, String codActividad) throws ATiempoAppEx {
		servicios.enviarConfiguracionActualBASigres(peticion, id_actividad, codActividad);
	}
	
	public void enviarConfiguracionActualBASigres(String telefonoConsulta, String peticion, String id_actividad, String codActividad) throws ATiempoAppEx {
		servicios.enviarConfiguracionActualBASigres(telefonoConsulta,peticion,id_actividad, codActividad);
	}
	
	public void recepcionConfiguracionActualBASigres(TR035S tr035s) throws ATiempoAppEx {
	
		servicios.recepcionConfiguracionActualBASigres(tr035s);
	}


//	CR4860 - Sigres -inicio- agonzalez- 10/07/2008 	
	public void recepcionModificarIdOperadora(TR041S tr041s) throws ATiempoAppEx {
		servicios.recepcionModificarIdOperadora(tr041s);	
	}

	public boolean enviarModificarIdOperadora(String peticion, String id_actividad) throws ATiempoAppEx {
		servicios.enviarModificarIdOperadora(peticion,id_actividad);
		return false;
	}

	//	CR4860 - Sigres -inicio- agonzalez- 15-07-2008 AT-1480
	public void solicitudConfiguracionSigresTraslado(String peticion, String cod_actividad, PsVsOcVO psprim,boolean reversa, Integer actividadFlujo, ActividadEJBDTO act) throws ATiempoAppEx {
		servicios.solicitudConfiguracionSigresTraslado(peticion,cod_actividad,psprim,reversa,actividadFlujo, act);
	}

	//	CR14657 - Granite - agonz- 16-10-2008
	public void enviaActualizaInventarioBAGranite(String peticion, String codigoActividad, ActividadEJBDTO act)throws ATiempoAppEx {
		servicios.enviaActualizaInventarioBAGranite(peticion, codigoActividad,act);	
	}

	public void recepcionActualizaInventarioBAGranite(TR515S tr515s) throws ATiempoAppEx {
		servicios.recepcionActualizaInventarioBAGranite(tr515s);
	}
		// @author 808026 -Defecto 20476 - 24/11/2008 - 
	public boolean esTrasladoBa(Long idPeticion)throws ATiempoAppEx{
	    return servicios.esTrasladoBa(idPeticion);
	}
	public boolean envioCambioNroSigres(ActividadEJBDTO act) throws TnProcesoExcepcion {
		return servicios.envioCambioNroSigres(act);
	}

	
	// @author 808026 - CR 27922 - 28/07/2009 - 
	public boolean esBajaBA(ArrayList listadoPS)throws ATiempoAppEx{
	    return servicios.esBajaBA(listadoPS);
	}
	
	public void removeProductoPeticionEnVuelo(Long numeroPeticion, String tipoPeticion)throws ATiempoAppEx{
	    servicios.removeProductoPeticionEnVuelo(numeroPeticion, tipoPeticion);
	}

	//TODO: 08022010 - Raúl Ernesto Triviño Alvarado - Ajuste para el requerimiento Req_2009_00031777 - Peticion en Vuelo
	public void solicitudConfiguracionSigresCambioPlanEnVuelo(String peticion, String cod_actividad, PsVsOcVO psprim,boolean reversa, Integer actividadFlujo, PsVsOcVO psOld) throws ATiempoAppEx {
		servicios.solicitudConfiguracionSigresCambioPlanEnVuelo(peticion,cod_actividad,psprim,reversa,actividadFlujo,psOld);
	}

	public Producto_servicio_en_vueloLocal almacenaProductoPeticionEnVuelo(Integer numeroPeticion, Integer psNuevo, String tipoPeticion, String psViejo)throws ATiempoAppEx{
	    return servicios.almacenaProductoPeticionEnVuelo(numeroPeticion, psNuevo, tipoPeticion, psViejo);
	}


	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.servicioba.RecursosBAInterfaces#solicitarPrimeraFacturaInternetEquipado(java.lang.Long)
	 */
	public Long solicitarPrimeraFacturaInternetEquipado(Long numeroPeticion, Collection equipos, String codActividad, String tipoOperacion, Integer actiID) throws ATiempoAppEx {
		return servicios.solicitarPrimeraFacturaInternetEquipado(numeroPeticion, equipos, codActividad, tipoOperacion,actiID);
	}
	public TR044S buscarRespuestaFacturaInternetEquipado(Long idPeticion, Long idMensaje) throws ATiempoAppEx{
		return servicios.buscarRespuestaFacturaInternetEquipado(idPeticion, idMensaje);
	}
	
	public boolean validarEstadosMensajesInternetEquipado (Long idPeticion) throws ATiempoAppEx{
		return servicios.validarEstadosMensajesInternetEquipado (idPeticion);
	}
	public void enviarConfiguracionWebFilterOptenet(ActividadEJBDTO act, Long numeroPeticion, String codActividad, PsVsOcVO psprim) throws ATiempoAppEx{
		servicios.enviarConfiguracionWebFilterOptenet(act, numeroPeticion,codActividad,psprim);
	}
	public void recibirConfiguracionWebFilterOptenet(TR048S tr048s) throws ATiempoAppEx{
		servicios.recibirConfiguracionWebFilterOptenet(tr048s);
	}
	
	
	public void creacionActuacionAgendaSC(Long idPeticion, java.sql.Timestamp fechaReagendamiento, String tipoOC, String codActividad, ActividadEJBDTO act){
		servicios.creacionActuacionAgendaSC(idPeticion, fechaReagendamiento, tipoOC, codActividad, act);
	}
	
	public void recepcionCreacionActuacionAgendaSC(TR701S tr701s){
		servicios.recepcionCreacionActuacionAgendaSC(tr701s);
	}
	public void recepcionActivarDecosTarjetasAgendaSC(TR708S tr708s) throws ATiempoAppEx {
		servicios.recepcionActivarDecosTarjetasAgendaSC(tr708s);
	}
	
	public void enviaActivarDecosTarjetasAgendaSC(String idActuacion, String idPeticion) throws ATiempoAppEx{
		servicios.enviaActivarDecosTarjetasAgendaSC(idActuacion, idPeticion);
	}

	public boolean esAgendaSC(Long atisRequestNumber) throws ATiempoAppEx {
		return servicios.esAgendaSC(atisRequestNumber);
	}
	
	public void recepcionCierreActuacion(TR711S tr711s) throws ATiempoAppEx{
		servicios.recepcionCierreActuacion(tr711s);
	}
	
	public void reagendamientoAgendaSC(TR705S tr705s) {
		servicios.reagendamientoAgendaSC(tr705s);
	}
	
	/* (non-Javadoc)
	 * idrincon req 1038
	 * @see co.com.telefonica.atiempo.vpistbba.servicioba.RecursosBAInterfaces#esTrasladoTv(java.lang.Long)
	 */
	public boolean esTrasladoTv(Long idPeticion) throws ATiempoAppEx {
		return servicios.esTrasladoTv(idPeticion);
	}


	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.servicioba.RecursosBAInterfaces#enviarConfiguracionTeraBox()
	 */
	public void enviarConfiguracionTeraBox(ActividadEJBDTO act, Long numeroPeticion, String codActividad, PsVsOcVO psprim ) throws ATiempoAppEx {
		servicios.enviarConfiguracionTeraBox(act, numeroPeticion,codActividad, psprim);
	}
	public void enviarCorreoTeraBox (ActividadEJBDTO act, Long numeroPeticion, String codActividad, PsVsOcVO psprim ) throws ATiempoAppEx {
		servicios.enviarCorreoTeraBox(act, numeroPeticion,codActividad, psprim);
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.servicioba.RecursosBAInterfaces#recibirConfiguracionTeraBox(co.com.telefonica.atiempo.interfaces.atiempo.TR051S)
	 */
	public void recibirConfiguracionTeraBox(TR051S tr051s) throws ATiempoAppEx {
		servicios.recibirConfiguracionTeraBox(tr051s);
	}
	
	public String llamadoConfModemAutoInstalacion(ModemVpiDTO modem, String codActividad, String idMensajePadre, boolean esBaja, boolean esCierreActuacion) throws ATiempoAppEx{
		 return servicios.llamadoConfModemAutoInstalacion(modem, codActividad, idMensajePadre, esBaja, esCierreActuacion);
	}
	
	public String[] enviarConfiguracionModemAutoinstalacion(ModemVpiDTO modem, String codActividad, String idMensajePadre) throws ATiempoAppEx{
		return servicios.enviarConfiguracionModemAutoinstalacion(modem, codActividad, idMensajePadre);
	}
	public void recibirConfiguracionModemAutoinstalacion(TR135S tr135s, boolean esBaja, boolean esCierreActuacion) throws ATiempoAppEx {
		servicios.recibirConfiguracionModemAutoinstalacion(tr135s, esBaja, esCierreActuacion);
	}


	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.servicioba.RecursosBAInterfaces#buscarRespuestaConfiguracionModemAutoInstalacion(java.lang.Long, java.lang.Long)
	 */
	public TR049EWideBand getWideBand(PeticionLocal peticionLocal) {
		return servicios.getWideBand(peticionLocal);
	}

	public TR135S buscarRespuestaConfiguracionModemAutoInstalacion(Long idPeticion, Long idMensaje) throws ATiempoAppEx {
		return servicios.buscarRespuestaConfiguracionModemAutoInstalacion(idPeticion, idMensaje);
	}
	
		//req 3709
	public void grabarFechaEntregaKitAutoInst(Long petiNumero, Fecha fechaEntregaKit) throws ATiempoAppEx{
		servicios.grabarFechaEntregaKitAutoInst(petiNumero,fechaEntregaKit);
	}
	//req 3709
	public void recibirmensajeACS(TR137S tr137s) throws ATiempoAppEx{
		servicios.recibirmensajeACS(tr137s);
	}
	//req 3709
	public void enviarSMSAutoInstalacion(Long petiNumero,String usuario)throws ATiempoAppEx{
		servicios.enviarSMSAutoInstalacion(petiNumero,usuario);
	}
	//req 3709
	public void grabarGuiaKitAutoInst(Long petiNumero, String numGuia) throws ATiempoAppEx{
		servicios.grabarGuiaKitAutoInst(petiNumero,numGuia);
	}
	//req 3709
	public String obtenerGuiaKitAutoInst(Long petiNumero) throws ATiempoAppEx{
		return servicios.obtenerGuiaKitAutoInst(petiNumero);
	}
	//req 3709
	public void grabarFechaAutoInst(Long petiNumero, Fecha fechaAutoInst) throws ATiempoAppEx{
		servicios.grabarFechaAutoInst(petiNumero,fechaAutoInst);
	}
	//req 3709
	public void grabarFechaGuiaKitAutoInst(Long petiNumero, Fecha fechaGuiaKit) throws ATiempoAppEx {
		servicios.grabarFechaGuiaKitAutoInst(petiNumero,fechaGuiaKit);
	}
	public void recepcionActivarModemsAgendaSC(TR717S tr717s) throws ATiempoAppEx{
		servicios.recepcionActivarModemsAgendaSC(tr717s);
	}
	public void enviaActivarModemsAgendaSC(String idActuacion, String idMensajePeticion, String idMensajePeticionHijo) throws ATiempoAppEx {
		servicios.enviaActivarModemsAgendaSC(idActuacion, idMensajePeticion, idMensajePeticionHijo);
		}
	//req 3709
	public String recuperaNumeroGuiaAutoInst(Long petiNumero) throws ATiempoAppEx{
		return servicios.recuperaNumeroGuiaAutoInst(petiNumero);
	}
	//req 3709
	public void actualizarRecursosBA ( Long petiNumero ) throws ATiempoAppEx{
		servicios.actualizarRecursosBA( petiNumero );
	}
	//req 3709
	public boolean validarEnvioTrEnPGACS( String idActuacion ) throws ATiempoAppEx {
		return servicios.validarEnvioTrEnPGACS( idActuacion );
	}
	//req 3709
	public void recepcionCreacionActuacionAgendaSCEnPGACS(TR701S tr701s){
		servicios.recepcionCreacionActuacionAgendaSCEnPGACS( tr701s );
	}
	//req 3709
	public void recepcionCierreActuacionPGACS(TR711S tr711s) throws ATiempoAppEx {
		servicios.recepcionCierreActuacionPGACS(tr711s);
	}
	//req 3709
	public ArrayList recuperaModemsBaVPiReversaAutoInst(Long nroPeticion){
		return servicios.recuperaModemsBaVPiReversaAutoInst(nroPeticion);
	}


	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.servicioba.RecursosBAInterfaces#validarConfiguracionModemAutoinstalacion(java.lang.Long)
	 */
	public boolean validarConfiguracionModemAutoinstalacion(Long nroPeticion, String codActividad) {
		return servicios.validarConfiguracionModemAutoinstalacion(nroPeticion, codActividad);
	}
	
	public boolean esCambioDeLinea(Long petiNumero){
		return servicios.esCambioDeLinea(petiNumero);
	}
	
	/*RQ 5606 - Internet Movil*/
	public void enviarConfiguracionInternetMovil(ActividadEJBDTO act, Long nroPeticion) throws ATiempoAppEx{
		servicios.enviarConfiguracionInternetMovil(act, nroPeticion);
	}
	
	public void procesarRespuestaInternetMovil(TR610S tr610s) throws ATiempoAppEx{
		servicios.procesarRespuestaInternetMovil(tr610s);
	}
	/*FIN - RQ 5606 - Internet Movil*/	
	/*RQ 6142 - WS Aula*/
	public String validaOperacionComercialSVA(ActividadEJBDTO act, Long nroPeticion) throws ATiempoAppEx{
		return servicios.validaOperacionComercialSVA(act, nroPeticion);
	}
	
	public PsVsOcVO devuelvePSPrioridadAltaSVA(ActividadEJBDTO act) throws ATiempoAppEx{
		return servicios.devuelvePSPrioridadAltaSVA(act);
	}
	/*FIN - RQ 6142 - WS Aula*/
	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.servicioba.RecursosBAInterfaces#consultarDisponibilidadAgendaSC(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public Long consultarDisponibilidadAgendaSC(String petiNumero, String idSchedule, String fechaIni, String fechaFin, String actividad) throws ATiempoAppEx {
		return servicios.consultarDisponibilidadAgendaSC(petiNumero, idSchedule, fechaIni, fechaFin, actividad);
	}


	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.servicioba.RecursosBAInterfaces#respuestaDisponibilidadAgendaSC(co.com.telefonica.atiempo.interfaces.atiempo.TR703S)
	 */
	public void respuestaDisponibilidadAgendaSC(TR703S tr703s) throws ATiempoAppEx {
		servicios.respuestaDisponibilidadAgendaSC(tr703s);
		
	}
	
	/**
	 * Metodo para el envío de la consulta de actuación a Agenda SC
	 * @author mfmendez
	 */
	public void enviarConsultaActuacionASC(ActividadEJBDTO act, Long nroPeticion, String actGeneradora) throws ATiempoAppEx{
		servicios.enviarConsultaActuacionASC(act, nroPeticion, actGeneradora);
	}
	
	/**
	 * Metodo para procesar la respuesta de la consulta de actuación a Agenda SC
	 * @author mfmendez
	 */
	public void procesarRespuestaConsultaActuacionASC(TR702S tr702s) throws ATiempoAppEx{
		servicios.procesarRespuestaConsultaActuacionASC(tr702s);
	}
	
	/**
	 * Metodo para el envío de la solicitud de reagendamiento a Agenda SC
	 * @author mfmendez
	 */
	public Long enviarSolicitudReagendamientoASC(Long nroPeticion, String idActuacion,String opcionReagendamiento) throws ATiempoAppEx{
		return servicios.enviarSolicitudReagendamientoASC(nroPeticion, idActuacion,opcionReagendamiento);
	}
	
	/**
	 * Metodo para procesar la respuesta de la solicitud de reagendamiento a Agenda SC
	 * @author mfmendez
	 */
	public void procesarRespuestaSolicitudReagendamientoASC(TR705S tr705s) throws ATiempoAppEx{
		servicios.procesarRespuestaSolicitudReagendamientoASC(tr705s);
	}

	/**
	 * Metodo para validar que se pueda enviar los PS de la petición para un alta de actuacion
	 * @author mfmendez 
	 */
	public boolean validarPSEnvioAltaActuacionPGC(Long idPeticionAT){
		return servicios.validarPSEnvioAltaActuacionPGC(idPeticionAT);
	}


	/* (sin Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.servicioba.RecursosBAInterfaces#configuracionEnvioCorreoTeraBox(co.com.telefonica.atiempo.interfaces.atiempo.TR718S)
	 */
	public void configuracionEnvioCorreoTeraBox(TR718S tr718s) throws ATiempoAppEx {
		// TODO Apéndice de método generado automáticamente
		servicios.configuracionEnvioCorreoTeraBox(tr718s);
	}


	/* (sin Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.servicioba.RecursosBAInterfaces#enviarRefrecarDatos(java.lang.Long, java.lang.String, java.lang.String)
	 */
	public void enviarRefrecarDatos(Long idPeticion) throws ATiempoAppEx {
		// TODO Apéndice de método generado automáticamente
		servicios.enviarRefrecarDatos(idPeticion);
		
	}
	
	public void enviaRefrescoBA(String nroPeticion, String accion, String codActividad)throws ATiempoAppEx{
		servicios.enviaRefrescoBA(nroPeticion,accion, codActividad);
	}


	/**
	 * @param act
	 */
	public void ejecutarVelocidadAdicionalTMP(ActividadEJBDTO act) throws ATiempoAppEx{
		servicios.ejecutarVelocidadAdicionalTMP(act);
	}
	//-ToaservicioBean
	public String extraerVelocidadPlanYPSPrioridadAlta(Long petiNumero) throws ATiempoAppEx{
		return  servicios.extraerVelocidadPlanYPSPrioridadAlta(petiNumero);
	}
	public String obtenerCaracteristicaPeticion(Producto_servicio_peticionLocal producto_servicio_peticionLocal, Long caracteristica)throws ATiempoAppEx{
		return servicios.obtenerCaracteristicaPeticion(producto_servicio_peticionLocal,caracteristica);
	}
	
	public CaracteristicaPSLocal obtenerProductoServicio(Subpeticion_atisLocal subpeticion_atisLocal) throws NamingException, ATiempoAppEx {
		return servicios.obtenerProductoServicio(subpeticion_atisLocal);
	}
	//-ToaServicioBean
	
	//	REQ TOA FASE I @DCARDENA 15/05/2015
	/* (sin Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.servicioba.RecursosBAInterfaces#enviaInstalarTOA(co.com.telefonica.atiempo.actividades.ActividadEJBDTO)
	 */
	public void enviaInstalarTOA(ActividadEJBDTO act) throws ATiempoAppEx {
		// TODO Apéndice de método generado automáticamente
		servicios.enviaInstalarTOA(act);
	}
	/* (sin Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.servicioba.RecursosBAInterfaces#recepcionInstalarTOA(co.com.telefonica.atiempo.interfaces.atiempo.TR717S)
	 */
	public void recepcionInstalarTOA(TR717S tr717s) throws ATiempoAppEx {
		// TODO Apéndice de método generado automáticamente
		recepcionInstalarTOA(tr717s);
	}
	//FIN REQ TOA FASE I

	/**
	 * @param deco_tar_inf_sapLocalHome
	 * @param tr701eEquipment
	 * @param id_deco
	 * @param id_tarjeta
	 * @param idPeticion
	 * @return
	 */
	public TR701EEquipment setDatosSAPDecoTarjeta(Deco_Tarjeta_Info_SapLocalHome deco_tar_inf_sapLocalHome, TR701EEquipment tr701eEquipment, String id_deco, String id_tarjeta, Long idPeticion) {
		// TODO Apéndice de método generado automáticamente
		return servicios.setDatosSAPDecoTarjeta(deco_tar_inf_sapLocalHome, tr701eEquipment,id_deco, id_tarjeta,  idPeticion);
	}


	/**
	 * @param tr701eEquipment
	 * @param camara
	 */
	public void setValoresCamara(TR701EEquipment tr701eEquipment, CamaraLocal camara) throws Exception {
		// TODO Apéndice de método generado automáticamente
		servicios.setValoresCamara(tr701eEquipment, camara);
	}

	/**
	 * @param peticionLocal
	 * @return
	 */
	public boolean esPostventa(PeticionLocal peticionLocal) {
		// TODO Apéndice de método generado automáticamente
		return servicios.esPostventa(peticionLocal);
	}


	/**
	 * @param listaDecos
	 * @param peticionLocal
	 * @param tr711s
	 * @param actDto
	 * @param actividadEJB
	 * @param b
	 * @param codBandeja
	 */
	public void agendamientoDecosTR711(ArrayList listaDecos, PeticionLocal peticionLocal, TR711S tr711s, ActividadEJBDTO actDto, IActividadEJB actividadEJB, boolean b, String codBandeja) {
		// TODO Apéndice de método generado automáticamente
		servicios.agendamientoDecosTR711(listaDecos, peticionLocal, tr711s, actDto, actividadEJB, b, codBandeja);
	}


	/**
	 * @param peticion
	 * @param cod_actividad_generadora
	 * @param long1
	 * @param idActividadFlujo
	 * @throws ATiempoAppEx
	 * @throws NamingException
	 * @throws FinderException
	 * @throws CreateException
	 * @throws CreateException
	 */
	public void insertarCausalesCnaPeticion(PeticionLocal peticion, String cod_actividad_generadora, Long long1, Integer idActividadFlujo) throws ATiempoAppEx, NamingException, FinderException, CreateException {
		// TODO Apéndice de método generado automáticamente
		servicios.insertarCausalesCnaPeticion(peticion, cod_actividad_generadora,long1, idActividadFlujo );
	}


	/**
	 * @param plataforma
	 * @return
	 */
	public String getNombreBandeja(String plataforma) {
		// TODO Apéndice de método generado automáticamente
		return servicios.getNombreBandeja(plataforma);
	}


	/**
	 * @param errorCode
	 * @param string
	 * @return
	 */
	public ErrorLegadoLocal getErrorLegado(String errorCode, String string) {
		// TODO Apéndice de método generado automáticamente
		return servicios.getErrorLegado(errorCode, string);
	}


	/**
	 * @param breakPairs
	 * @param peti_numero
	 * @param productoSrvicioPeticionCollection
	 * @param codigoActividad
	 */
	public void setQuiebrePcAgendaSc(Collection breakPairs, Long peti_numero, Collection productoSrvicioPeticionCollection, String codigoActividad) {
		// TODO Apéndice de método generado automáticamente
		servicios.setQuiebrePcAgendaSc(breakPairs, peti_numero, productoSrvicioPeticionCollection, codigoActividad);
	}


	/**
	 * @param agendaSCLocal
	 * @return
	 * @throws ATiempoAppEx
	 * @throws FinderException
	 * @throws NamingException
	 */
	public boolean esAutoinstalacion(Agenda_scLocal agendaSCLocal) throws NamingException, FinderException, ATiempoAppEx {
		// TODO Apéndice de método generado automáticamente
		return servicios.esAutoinstalacion(agendaSCLocal);
	}


	/**
	 * @param producto_servicio_peticion
	 * @return
	 */
	public boolean validaBajAltaMigrDeco(Collection producto_servicio_peticion) {
		// TODO Apéndice de método generado automáticamente
		return servicios.validaBajAltaMigrDeco(producto_servicio_peticion);
	}


	/**
	 * @param operationComercial
	 * @param famOpLocal
	 * @return
	 * @throws NamingException
	 * @throws FinderException
	 */
	public String accionModemNaked(String operationComercial, int famOpLocal) throws FinderException, NamingException {
		// TODO Apéndice de método generado automáticamente
		return servicios.accionModemNaked(operationComercial, famOpLocal);
	}


	/**
	 * @param idPeticion
	 * @param familiaDecoPVRTV
	 * @param cantDecosDesinsPVR
	 * @return
	 * @throws FinderException
	 * @throws NamingException
	 */
	public int cantidadDecosHC(Long idPeticion, int familiaDecoPVRTV, int cantDecosDesinsPVR) throws NamingException, FinderException {
		// TODO Apéndice de método generado automáticamente
		return servicios.cantidadDecosHC(idPeticion, familiaDecoPVRTV, cantDecosDesinsPVR);
	}


	/**
	 * @param dec_tar_sapdecoLocal
	 * @param equipo
	 * @param peticionKey
	 * @param deco_tar_inf_sapLocalHome
	 */
	public void ingresoInformacionSAP(Deco_Tarjeta_Info_SapLocal dec_tar_sapdecoLocal, DecoTarDTO equipo, PeticionKey peticionKey, Deco_Tarjeta_Info_SapLocalHome deco_tar_inf_sapLocalHome) {
		// TODO Apéndice de método generado automáticamente
		servicios.ingresoInformacionSAP(dec_tar_sapdecoLocal,equipo, peticionKey,deco_tar_inf_sapLocalHome);
	}
	
}
