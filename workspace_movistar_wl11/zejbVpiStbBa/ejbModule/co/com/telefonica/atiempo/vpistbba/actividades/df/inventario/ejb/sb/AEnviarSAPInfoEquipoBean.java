package co.com.telefonica.atiempo.vpistbba.actividades.df.inventario.ejb.sb;

import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;
import co.com.telefonica.atiempo.ejb.eb.EquipoDelegate;
import co.com.telefonica.atiempo.ejb.eb.EquipoLocal;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.vpistbba.estructura_flujo.DATOS_ATVPISTBBA;

import com.telefonica_chile.atiempo.actividades.TnProcesoExcepcion;

/**
 * Bean implementation class for Enterprise Bean: AEnviarSAPInfoEquipo
 */
public class AEnviarSAPInfoEquipoBean extends co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB
	implements javax.ejb.SessionBean {

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB#incializaActividadVPI(co.com.telefonica.atiempo.actividades.ActividadEJBDTO)
	 */
	protected void incializaActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {		
	}
	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB#onInicioActividadVPI(co.com.telefonica.atiempo.actividades.ActividadEJBDTO)
	 */
	protected void onInicioActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {
		log.debug("--Pasa por metodo onInicioActividadVPI de AEnviarSAPInfoEquipoBean para la petición: "+act.getNumeroPeticion());
		
		try {			
			
			if(!act.getDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_reversa).equals("S")){
				log.debug("Ejecuta la actividad de envio de informacion de equipos proveedores SAP.");
				EquipoLocal delegateEquipos = new EquipoDelegate();				
				boolean exitoEnvio = delegateEquipos.enviarInformacionEquiposMMSAP(act, act.getNumeroPeticion(),act.getCodigoActividad());
			} else{
				/*SE INHIBE LA ACTIVIDAD POR IR EN REVERSA*/
				log.debug("Se inhibe la actividad de envio de informacion de equipos proveedores SAP.");
	        	act.setObservacion("Se inhibe la actividad porque la peticion va en reversa.", true);
				act.setRealizarTerminoInmediato(true);
			}
			
		} catch (ATiempoAppEx e) {
			log.error("AEnviarSAPInfoEquipoBean: onInicioActividadVPI: Error intentando enviar la información de los equipos de Proveedores hacia SAP. "+e.toString());
		}
		
	}
	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB#onTerminoActividadVPI(co.com.telefonica.atiempo.actividades.ActividadEJBDTO)
	 */
	protected void onTerminoActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {
		// TODO Auto-generated method stub
		
	}
}