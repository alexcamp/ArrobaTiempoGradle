package co.com.telefonica.atiempo.vpistbba.actividades.df.configuracion.ejb.sb;

import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.vpistbba.servicioba.RecursosBADelegate;

import com.telefonica_chile.atiempo.actividades.TnProcesoExcepcion;

/**
 * Bean implementation class for Enterprise Bean: ALegalizarInternetMovil
 */
public class ALegalizarInternetMovilBean
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
		log.debug("--Pasa por metodo onInicioActividadVPI de ALegalizarInternetMovilBean, en la actividad "+ act.getCodigoActividad()+", para la petición: "+act.getNumeroPeticion());
		
		try {
			RecursosBADelegate delegateRecursosBA = new RecursosBADelegate();
			
			delegateRecursosBA.enviarConfiguracionInternetMovil(act, act.getNumeroPeticion());
			
		} catch (ATiempoAppEx e) {
			log.error("ALegalizarInternetMovilBean: onInicioActividadVPI: Error al intentando enviar la información de Internet Movil hacia el Broker. "+e.toString());
		}
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB#onTerminoActividadVPI(co.com.telefonica.atiempo.actividades.ActividadEJBDTO)
	 */
	protected void onTerminoActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {
		// TODO Auto-generated method stub
		
	}

	
}