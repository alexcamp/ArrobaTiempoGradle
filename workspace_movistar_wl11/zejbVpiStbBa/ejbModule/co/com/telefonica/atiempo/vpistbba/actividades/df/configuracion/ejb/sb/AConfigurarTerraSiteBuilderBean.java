package co.com.telefonica.atiempo.vpistbba.actividades.df.configuracion.ejb.sb;

import java.util.Collection;
import java.util.Iterator;

import javax.ejb.FinderException;
import javax.naming.NamingException;

import co.com.atiempo.dto.PsVsOcVO;
import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;
import co.com.telefonica.atiempo.ejb.eb.Mensaje_estado_baLocal;
import co.com.telefonica.atiempo.ejb.eb.Mensaje_estado_baLocalHome;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.vpistbba.estructura_flujo.DATOS_ATVPISTBBA;
import co.com.telefonica.atiempo.vpistbba.servicioba.RecursosBADelegate;
import co.com.telefonica.atiempo.vpistbba.servicioba.RecursosBAInterfaces;

import com.telefonica_chile.atiempo.actividades.TnProcesoExcepcion;
import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.atiempo.utiles.VpistbbaConfig;
import com.telefonica_chile.comun.ComunInterfaces;

/**
 * Bean implementation class for Enterprise Bean: AConfigurarTerraSiteBuilder
 */
public class AConfigurarTerraSiteBuilderBean
	extends
		co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB
	implements
		javax.ejb.SessionBean {

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB#incializaActividadVPI(co.com.telefonica.atiempo.actividades.ActividadEJBDTO)
	 */
	protected void incializaActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB#onInicioActividadVPI(co.com.telefonica.atiempo.actividades.ActividadEJBDTO)
	 */
	protected void onInicioActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {
		log.debug("--Pasa por metodo onInicioActividadVPI de AConfigurarTerraSiteBuilderBean, en la actividad "+ act.getCodigoActividad()+", para la petición: "+act.getNumeroPeticion());
		
//		TODO: 23/10/2009 - Raúl Ernesto Triviño Alvarado - Ajuste del requerimeinto 00030351 - Se coloca for para mandar mensaje para cada ps
		boolean resp = true;
		Long correlativo = new Long(0);
		
		try{
			Mensaje_estado_baLocalHome mensajeEstadoBaLocalHome=(Mensaje_estado_baLocalHome) HomeFactory.getHome(Mensaje_estado_baLocalHome.JNDI_NAME);
			Collection listaMensajeLocal =  mensajeEstadoBaLocalHome.findByPetiNumeroErrorActividad(act.getNumeroPeticion(), "6", ComunInterfaces.COD_ACT_CONFIG_TERRA_SITEBUILDER);
			
//			for( Iterator iterTemp = act.getPsOk().iterator(); iterTemp.hasNext(); ){
			//Iterator iterTemp = act.getPsOk().iterator(); //Debe venir al menos un ps
			//PsVsOcVO psTemp= (PsVsOcVO) iterTemp.next();
			
			RecursosBAInterfaces rBAI= new RecursosBADelegate();			
	
			PsVsOcVO psTemp = rBAI.devuelvePSPrioridadAltaSVA(act);
			
			correlativo = psTemp.getCorrelativo();
	
			if(!act.getDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_reversa).equals("S")){
				log.debug("Inicio Actividad Configurar Terra [" + act.getNumeroPeticion() + "]");
	
				if (listaMensajeLocal != null && listaMensajeLocal.isEmpty() && listaMensajeLocal.size()==0){
					log.debug("No hay mensajes de error por lo tanto envio la petición sin validación");
					resp=rBAI.enviaConfiguracionTerra(act.getNumeroPeticion(),act,psTemp,false);
				}else{
					for( Iterator iterListaMensajeLocal = listaMensajeLocal.iterator(); iterListaMensajeLocal.hasNext(); ){
						log.debug("Hay mensajes de error por lo tanto envio la petición con validación para el PS "+psTemp.getPsId().toString());
						Mensaje_estado_baLocal mebaLocal=(Mensaje_estado_baLocal) iterListaMensajeLocal.next();
						
						if (psTemp.getPsId().toString().equals(mebaLocal.getDesc_error())){
							mebaLocal.setId_error("");
							mebaLocal.setDesc_error("");
							
							log.debug("Enviando mensaje de error para el ps "+psTemp.getPsId().toString());
							resp=rBAI.enviaConfiguracionTerra(act.getNumeroPeticion(),act,psTemp,false);
						}
					}
				}
				
				
				if (resp){
					act.setObservacion("Se envia el mensaje de Configuracion Terra.");	
				}else{
					act.setObservacion("No se envia el mensaje de Configuracion Terra. Faltan datos. Revise Service Reference ID(CDS), Direccion Electronica(CDS) y Usuario de Acceso Nuevo(ADSL)");					
					//Se deriva a Control Terra para que se haga la activacion en forma manual.
					
					//OJO, AHORA CONTROL TERRA DESAPARECE, ENTONCES SI OCURRE UN ERROR EN EL ENVIO, A QUE PLATAFORMA SE DERIVA?
					//act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.CONFIGURACION_TERRA.control_terra, "S");
					act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza, VpistbbaConfig.getVariable("IDPGI"));
					act.setRealizarTerminoInmediato(true);
				}
			}else{
				log.debug("Inicio Actividad Reversa de Configurar Internet [" + act.getNumeroPeticion() + "]");
	
				if (listaMensajeLocal != null && listaMensajeLocal.isEmpty() && listaMensajeLocal.size()==0){
					log.debug("No hay mensajes de error por lo tanto envio la petición sin validación");
					resp=rBAI.enviaConfiguracionTerra(act.getNumeroPeticion(),act,psTemp,true);
				}else{
					for( Iterator iterListaMensajeLocal = listaMensajeLocal.iterator(); iterListaMensajeLocal.hasNext(); ){
						log.debug("Hay mensajes de error por lo tanto envio la petición con validación para el PS "+psTemp.getPsId().toString());
						Mensaje_estado_baLocal mebaLocal=(Mensaje_estado_baLocal) iterListaMensajeLocal.next();
						
						if (psTemp.getPsId().toString().equals(mebaLocal.getDesc_error())){
							mebaLocal.setId_error("");
							mebaLocal.setDesc_error("");
							
							log.debug("Enviando mensaje de error para el ps "+psTemp.getPsId().toString());
							resp=rBAI.enviaConfiguracionTerra(act.getNumeroPeticion(),act,psTemp,true);
						}
					}
				}
				
				
				if (resp){
					act.setObservacion("Se envia el mensaje de Reversa Configuracion Terra.");
				}else{
					act.setObservacion("No hay OC para la reversa. No se envia mensaje de reversa.");
					//Se deriva a Control Terra para que se haga la desactivacion.
					
					//OJO, AHORA CONTROL TERRA DESAPARECE, ENTONCES SI OCURRE UN ERROR EN EL ENVIO, A QUE PLATAFORMA SE DERIVA?
					//act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.CONFIGURACION_TERRA.control_terra, "S");
					act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza, VpistbbaConfig.getVariable("IDPGI"));
					act.setRealizarTerminoInmediato(true);
				}
			}
		//}
		
		/*if (listaMensajeLocal.isEmpty()){
			this.limpiarErroresMensajeEstadoBA(act.getNumeroPeticion(), correlativo);
		}*/
		} catch (ATiempoAppEx e){
			log.warn("Error en Actividad Configurar Terra",e);
			throw new TnProcesoExcepcion("Error en Actividad Configurar Terra", e);
		} catch (FinderException e) {
			log.warn("Error en Actividad Configurar Internet",e);
			throw new TnProcesoExcepcion("Error en Actividad Configurar Internet", e);
		} catch (NamingException e) {
			log.warn("Error en Actividad Configurar Internet",e);
			throw new TnProcesoExcepcion("Error en Actividad Configurar Internet", e);
		}
		 //End TODO
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB#onTerminoActividadVPI(co.com.telefonica.atiempo.actividades.ActividadEJBDTO)
	 */
	protected void onTerminoActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {
		// TODO Auto-generated method stub
		
	}

	
}