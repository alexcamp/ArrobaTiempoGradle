package co.com.telefonica.atiempo.soltec.actividades.df.ejb.sb;

import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;
import co.com.telefonica.atiempo.soltec.estructura_flujo.DATOS_ATSTSTBBA;
import co.com.telefonica.atiempo.soltec.incidentes.IncidentesDelegate;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;

import com.telefonica_chile.atiempo.actividades.TnProcesoExcepcion;

/**
 * Bean implementation class for Enterprise Bean: ASTEnvioCierrePeticion
 */
public class ASTEnvioCierrePeticionBean
	extends co.com.telefonica.atiempo.soltec.actividades.ActividadAutomaticaSTEJB{

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.soltec.actividades.ActividadAutomaticaSTEJB#incializaActividadST()
	 */
	protected void incializaActividadST(ActividadEJBDTO act) throws TnProcesoExcepcion {
		//Envia el mensaje de cierre y no espera respuesta.
		act.setRealizarTerminoInmediato(true);
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.soltec.actividades.ActividadAutomaticaSTEJB#onInicioActividadST()
	 */
	protected void onInicioActividadST(ActividadEJBDTO act) throws TnProcesoExcepcion {
		log.debug("Inicio Cierre Incidente [" + act.getNumeroPeticion() + "]");

//TODO: LOGICA , lca
		
		try {
			IncidentesDelegate delegate=new IncidentesDelegate();
			delegate.cerrarIncidente(act.getNumeroPeticion());
		} catch (ATiempoAppEx e) {
			log.warn("Error al cerrar incidente",e);
			throw new TnProcesoExcepcion("Error al cerrar incidente", e);
		}
		

		log.debug("Fin Cierre incidente [" + act.getNumeroPeticion() + "]");
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.soltec.actividades.ActividadAutomaticaSTEJB#onTerminoActividadST()
	 */
	protected void onTerminoActividadST(ActividadEJBDTO act) throws TnProcesoExcepcion {
		act.setDato(DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.fluj_cierre,"S");
		
	}
}
