package co.com.telefonica.atiempo.vpistbba.actividades.recepcion.ejb.sb;

import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.vpistbba.actividades.ActividadManualEJB;
import co.com.telefonica.atiempo.vpistbba.estructura_flujo.DATOS_ATVPISTBBA;
import co.com.telefonica.atiempo.vpistbba.peticiones.PeticionesDelegate;

import com.telefonica_chile.atiempo.actividades.TnProcesoExcepcion;

/**
 * Bean implementation class for Enterprise Bean: AEsperoDesbloqueo
 */
public class AEsperoDesbloqueoBean
	extends ActividadManualEJB {

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.actividades.ActividadManualEJB#incializaActividadVPI()
	 */
	protected void incializaActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {
		act.setGrabarWfInstancia(true);	
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.actividades.ActividadManualEJB#onInicioActividadVPI()
	 */
	protected void onInicioActividadVPI(ActividadEJBDTO actDto) throws TnProcesoExcepcion {
		
		// CR26362 - adocarmo - inicio
		try {
			PeticionesDelegate delegate=new PeticionesDelegate();
			if (delegate.existeBajaConIgualNro(actDto.getNumeroPeticion())) {
				actDto.setRealizarTerminoInmediato(true);
				actDto.setObservacion("Se cancela la peticion ya que el nro de tel del alta y la baja son iguales");				
			}
		} catch (ATiempoAppEx e) {
			log.debug("Problemas al instanciar PeticionesDelegate");
		}
		// CR26362 - adocarmo - fin
	}
	
	protected void onTerminoActividadVPI(ActividadEJBDTO actDto) throws TnProcesoExcepcion {	
		
		// CR26362 - adocarmo - inicio
		try {
			PeticionesDelegate delegate=new PeticionesDelegate();
			if (delegate.existeBajaConIgualNro(actDto.getNumeroPeticion())) {
				log.debug("Cancelo alta ya que los nro de tel son iguales");
				actDto.setObservacion("Se cancela la peticion ya que el nro de tel del alta y la baja son iguales");
				actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_mult_cierre_peticion,"S");				
			}
		} catch (ATiempoAppEx e) {
			log.debug("Problemas al instanciar PeticionesDelegate");
		}
		// CR26362 - adocarmo - fin
	}	
			
}
