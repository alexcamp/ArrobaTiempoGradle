package co.com.telefonica.atiempo.vpistbba.actividades.df.instalacion.ejb.sb;

import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.vpistbba.servicioba.RecursosBADelegate;
import co.com.telefonica.atiempo.vpistbba.servicioba.RecursosBAInterfaces;

import com.telefonica_chile.atiempo.actividades.TnProcesoExcepcion;

/**
 * Bean implementation class for Enterprise Bean: AValidarActuacionASC
 */
public class AValidarActuacionASCBean
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
		log.debug("--Pasa por metodo onInicioActividadVPI de AValidarActuacionASCBean, en la actividad "+ act.getCodigoActividad()+", para la petición: "+act.getNumeroPeticion());
		
		try {
			RecursosBAInterfaces delegateRecBA = new RecursosBADelegate();			
			delegateRecBA.enviarConsultaActuacionASC(act, act.getNumeroPeticion(),act.getCodigoActividad());			
		} catch (ATiempoAppEx e) {
			log.error("AValidarActuacionASCBean: onInicioActividadVPI: Error intentando enviar la solicitud de consulta de actuación. "+e);
		}
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB#onTerminoActividadVPI(co.com.telefonica.atiempo.actividades.ActividadEJBDTO)
	 */
	protected void onTerminoActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {
		// TODO Auto-generated method stub
		
	}
	
}