package co.com.telefonica.atiempo.vpistbba.actividades.df.ejb.sb;

import com.telefonica_chile.atiempo.actividades.TnProcesoExcepcion;

import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.vpistbba.peticiones.PeticionesDelegate;

/**
 * Bean implementation class for Enterprise Bean: ACierrePrimarioTvPeticion
 */
public class ACierrePrimarioTvPeticionBean
	extends co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB {
	

	/* (non-Javadoc)
	* @see co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB#onInicioActividadVPI()
	*/
	protected void onInicioActividadVPI(ActividadEJBDTO act)throws TnProcesoExcepcion {

		log.debug("Cerrando Peticion [" + act.getNumeroPeticion().toString() + "]");

		try {

			PeticionesDelegate peticionesDelegate = new PeticionesDelegate();
			boolean esSVATempUnico= peticionesDelegate.esSVATemp(act.getNumeroPeticion());
			
			if(esSVATempUnico && act.getPsOk().size() == 1){
//				Requerimiento Try And Buy Cesar Remigio
				log.debug("Se inhibe por tener un unìco PS con OC que no se informa");
				act.setObservacion("Se inhibe por tener un unìco PS con OC que no se informa");
				act.setRealizarTerminoInmediato(true);
				
			}else{
				peticionesDelegate.cerrarPeticionPrimariaTv(act.getNumeroPeticion());
			}

		} catch (ATiempoAppEx e) {
			log.debug("Error en Cierre Primario TV.", e);
			throw new TnProcesoExcepcion("ATiempoAppEx", e);
		}
		log.debug(
			"Peticion Cerrada [" + act.getNumeroPeticion().toString() + "]");

	}

	/* (non-Javadoc)
		 * @see co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB#onTerminoActividadVPI()
		 */
	protected void onTerminoActividadVPI(ActividadEJBDTO act)
		throws TnProcesoExcepcion {
		//No debo terminar la actividad, dado que es un cierre previo. 
		//act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_cierre, "S");
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB#incializaActividadVPI()
	 */
	protected void incializaActividadVPI(ActividadEJBDTO act)
		throws TnProcesoExcepcion {
		//TODO: Envia el mensaje y no espera respuesta
		act.setRealizarTerminoInmediato(true);

	}
}
