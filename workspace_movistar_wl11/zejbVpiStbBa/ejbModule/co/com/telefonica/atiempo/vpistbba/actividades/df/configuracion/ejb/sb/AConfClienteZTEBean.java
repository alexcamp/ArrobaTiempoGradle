package co.com.telefonica.atiempo.vpistbba.actividades.df.configuracion.ejb.sb;

import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.vpistbba.estructura_flujo.DATOS_ATVPISTBBA;
import co.com.telefonica.atiempo.vpistbba.recursos.RecursosDelegate;

import com.telefonica_chile.atiempo.actividades.TnProcesoExcepcion;

/**
 * Bean implementation class for Enterprise Bean: AConfClienteZTE
 */
public class AConfClienteZTEBean extends
		co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB {

	/*
	 * (sin Javadoc)
	 * 
	 * @see co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB#incializaActividadVPI(co.com.telefonica.atiempo.actividades.ActividadEJBDTO)
	 */
	protected void incializaActividadVPI(ActividadEJBDTO act)
			throws TnProcesoExcepcion {
		// TODO Apéndice de método generado automáticamente

	}

	/*
	 * (sin Javadoc)
	 * 
	 * @see co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB#onInicioActividadVPI(co.com.telefonica.atiempo.actividades.ActividadEJBDTO)
	 */
	protected void onInicioActividadVPI(ActividadEJBDTO act)
			throws TnProcesoExcepcion {
		log.debug("Ejecutando AConfClienteZTEBean: nro peticion: [ "
				+ act.getNumeroPeticion() + "]");

		try {
			RecursosDelegate recursos = new RecursosDelegate();
			if (!act.getDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_reversa)
					.equals("S")) {
				recursos.configurarClienteZTE(act);
			} else {
				act.setObservacion("Reversa Baja");
				act.setRealizarTerminoInmediato(true);
			}
		} catch (ATiempoAppEx appEx) {
			appEx.printStackTrace();
			throw new TnProcesoExcepcion(
					"Error en Actividad AConfClienteZTEBean ", appEx);
		}
	}

	/*
	 * (sin Javadoc)
	 * 
	 * @see co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB#onTerminoActividadVPI(co.com.telefonica.atiempo.actividades.ActividadEJBDTO)
	 */
	protected void onTerminoActividadVPI(ActividadEJBDTO act)
			throws TnProcesoExcepcion {
		// TODO Apéndice de método generado automáticamente

	}

}