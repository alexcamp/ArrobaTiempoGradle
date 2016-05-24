package co.com.telefonica.atiempo.vpistbba.cambio_numero;

import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.vpistbba.estructura_flujo.DATOS_ATVPISTBBA;
import co.com.telefonica.atiempo.vpistbba.servicioba.RecursosBADelegate;

import com.telefonica_chile.atiempo.actividades.TnProcesoExcepcion;

/**
 * Bean implementation class for Enterprise Bean: ACambioNumeroBA
 */
public class ACambioNumeroBABean
	extends co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB{

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB#incializaActividadVPI()
	 */
	protected void incializaActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB#onInicioActividadVPI()
	 */
	protected void onInicioActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {
		RecursosBADelegate recursosBADelegate;
		try
		{
			if(!act.getDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_reversa).equals("S")){
				log.debug("Inicio Actividad Cambio Numero BA [" + act.getNumeroPeticion() + "]");
				recursosBADelegate = new RecursosBADelegate();
				if(recursosBADelegate.envioCambioNro(act)){
					recursosBADelegate.enviarCambioNumeroBA(act.getNumeroPeticion().toString(),act.getCodigoActividad().toString());
					act.setObservacion("Se envia el mensaje de Cambio de numero");	
				}else{
					act.setObservacion("El numero nuevo y el numero actual son iguales o los DSLAMs son distintos. No se envia el mensaje de Cambio de numero");
					act.setRealizarTerminoInmediato(true);
				}
		  }else{
			  log.debug("Inicio Actividad Reversa Cambio Numero BA [" + act.getNumeroPeticion() + "]");
			  act.setObservacion("No esta definido que hacer en la reversa. Se cierra la actividad.");
			  act.setRealizarTerminoInmediato(true);	 
		  }
		} catch (ATiempoAppEx e){
			log.warn("Error en Actividad Cambio Numero BA",e);
			throw new TnProcesoExcepcion("Error en Actualizar Inventario BA", e);
		} 
		log.debug("Fin Actividad Cambio Numero BA [" + act.getNumeroPeticion() + "]");
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB#onTerminoActividadVPI()
	 */
	protected void onTerminoActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {
		
	}

}
