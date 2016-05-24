package co.com.telefonica.atiempo.vpistbba.actividades.df.configuracion.ejb.sb;

import java.util.Iterator;

import co.com.atiempo.dto.PsVsOcVO;
import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.vpistbba.estructura_flujo.DATOS_ATVPISTBBA;
import co.com.telefonica.atiempo.vpistbba.peticiones.PeticionesDelegate;
import co.com.telefonica.atiempo.vpistbba.peticiones.PeticionesInterfaces;
import co.com.telefonica.atiempo.vpistbba.servicioba.RecursosBADelegate;
import co.com.telefonica.atiempo.vpistbba.servicioba.RecursosBAInterfaces;

import com.telefonica_chile.atiempo.actividades.TnProcesoExcepcion;

/**
 * Bean implementation class for Enterprise Bean: AConfigurarInternetOptenet
 * DAVID: req 3013 Nov 24 2010, web filter fase II, esta actividad es una copia de AConfigurarInternet.
 */
public class AConfigurarInternetOptenetBean extends
		co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB
		implements javax.ejb.SessionBean {

	protected void onInicioActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {

		try{
			Long nroPet = act.getNumeroPeticion();		
			RecursosBAInterfaces delegate=new RecursosBADelegate();
			//Si se hace la actividad es porque psOk > 0
		
			if(act.getPsOk().size()==1){
				
				Iterator iterTemp = act.getPsOk().iterator();
				//Obtengo el primer PS
				PsVsOcVO psTemp= (PsVsOcVO) iterTemp.next();
				psTemp.setSacarFatherEmAlta(false);
				
				if(!act.getDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_reversa).equals("S")){
					log.debug("Inicio Actividad Configurar Internet Optenet[" + nroPet + "]");
					delegate.solicitudConfiguracionBA(nroPet.toString(),act.getCodigoActividad(),psTemp,false,act.getIdActividadFlujo());
//					act.setObservacion("Se envia el mensaje de Configuracion Internet Optenet.");
				}else{
					log.debug("Inicio Actividad Reversa de Configurar Internet Optenet[" + nroPet + "]");
					//Si tiene OC de reversa hago la reversa, si no tiene OC de Reversa asignada, no hay reversa y se termina la actividad.
					if (psTemp.getOpComRevId()!=null && psTemp.getOpComRevId().longValue()>-1){
						delegate.solicitudConfiguracionBA(nroPet.toString(),act.getCodigoActividad(),psTemp,true,act.getIdActividadFlujo());
						act.setObservacion("Se envia el mensaje de Reversa Configuracion Internet Optenet.");
					}
					else{
						act.setObservacion("No hay OC para la reversa. No se envia mensaje de reversa.");
						act.setRealizarTerminoInmediato(true);
					}
				}
			}else{
				act.setObservacion("Se inhibe la actividad, por tener mas de un PS de Optenet.");
				act.setRealizarTerminoInmediato(true);
			}
		}catch (ATiempoAppEx e){
			log.warn("Error en Actividad Configurar Internet Optenet",e);
			throw new TnProcesoExcepcion("Error en Actividad Configurar Internet Optenet", e);
		} 		

		log.debug("Fin Actividad Configurar Internet Optenet[" + act.getNumeroPeticion() + "]");
		
	}

	protected void onTerminoActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {
		if (act.containsKeyDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.hay_recursos_ba))
		{//La seteo solo en los flujos que conocen esa variable
			//Para que no haga el obtener configuracion BA Terra
			act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.hay_recursos_ba,"S");
		}
//		AT-1524 Reversa para Configurar Internet 08/12/2008 
		if (act.containsKeyDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza)&& !act.getDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza).equals("18")&& !act.getDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_reversa).equals("S")){		
			try {
				//llamar al metodo
				PeticionesInterfaces pI = new PeticionesDelegate();
				pI.actualizarEstadoPS(act.getNumeroPeticion(),act.getIdActividadFlujo(),act.getActividadBD().getIdActividad());
			}catch (ATiempoAppEx e) {
				log.warn("Error en Actividad Configurar Internet Optenet",e);
				throw new TnProcesoExcepcion("Error en Actividad Configurar Internet Optenet", e);
			}	
		}
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB#incializaActividadVPI()
	 */
	protected void incializaActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {
		
	}
}
