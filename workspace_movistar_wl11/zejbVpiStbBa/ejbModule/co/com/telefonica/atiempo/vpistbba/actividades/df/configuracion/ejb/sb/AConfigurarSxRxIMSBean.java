package co.com.telefonica.atiempo.vpistbba.actividades.df.configuracion.ejb.sb;

import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.vpistbba.serviciosba.ejb.ConfigurarSxRxIMSBean;

import com.telefonica_chile.atiempo.actividades.TnProcesoExcepcion;

/**
 * Bean implementation class for Enterprise Bean: AconfigurarSxRxIMS
 * @dcardena 25/11/2014
 * REQUERIMIENTO BA Naked
 */

public class AConfigurarSxRxIMSBean	extends co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB
implements javax.ejb.SessionBean {

	/* (sin Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB#incializaActividadVPI(co.com.telefonica.atiempo.actividades.ActividadEJBDTO)
	 */
	protected void incializaActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {
		// TODO Apéndice de método generado automáticamente
	
	}

	/* (sin Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB#onInicioActividadVPI(co.com.telefonica.atiempo.actividades.ActividadEJBDTO)
	 */
	protected void onInicioActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {

		try {
			log.debug("Entrando actividad Configurar suspencion y reconexion ims");
			ConfigurarSxRxIMSBean sxRx = new ConfigurarSxRxIMSBean();
			sxRx.configurarSxRxIMS(act);
		} catch (ATiempoAppEx e) {
			// TODO Bloque catch generado automáticamente
			log.debug("Error producido al instanciar el proceso: " + e);
		}
	}

	/* (sin Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB#onTerminoActividadVPI(co.com.telefonica.atiempo.actividades.ActividadEJBDTO)
	 */
	protected void onTerminoActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {
		// TODO Apéndice de método generado automáticamente
		
	}
	
}