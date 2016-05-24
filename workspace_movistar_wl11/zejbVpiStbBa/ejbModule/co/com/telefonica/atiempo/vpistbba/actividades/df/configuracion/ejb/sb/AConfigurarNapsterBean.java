/*
 * Creado el 210/02/2014
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package co.com.telefonica.atiempo.vpistbba.actividades.df.configuracion.ejb.sb;

import com.telefonica_chile.atiempo.actividades.TnProcesoExcepcion;
import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.vpistbba.estructura_flujo.DATOS_ATVPISTBBA;
import co.com.telefonica.atiempo.vpistbba.recursos.RecursosDelegate;
/**
 * @author dcardena
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 * Bean implementation class for Enterprise Bean: AConfigurarNapster
 */
public class AConfigurarNapsterBean  extends co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB{
	protected void incializaActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {
		// TODO Apéndice de método generado automáticamente
		
	}
	protected void onInicioActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {
		
		try{
			
			log.debug("Inicio Actividad Configuracion Napster [" + act.getNumeroPeticion() + "]");
			//intanciamos la clase recursos delgate para implementar los metodos
			RecursosDelegate recursos = new RecursosDelegate();		
			//validamos que la actividad no se encuentre en reversa
			if (!act.getDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_reversa).equals("S")) {
				log.debug("Se envia mensaje Configuracion Napster");
					//llamamos a la funcion configurarNapster la cual tiene la logica de generar la TR604E
					recursos.configurarNapster(act);
				}else{		
					act.setObservacion(" ");
					act.setRealizarTerminoInmediato(true);		
				}
		} catch (ATiempoAppEx e){
			log.warn("Error en Actividad Configuracion Napster",e);
			throw new TnProcesoExcepcion("Error en Actividad Configuracion Napster", e);
		}
	}

	/* (sin Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB#onTerminoActividadVPI(co.com.telefonica.atiempo.actividades.ActividadEJBDTO)
	 */
	protected void onTerminoActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {
		// TODO Apéndice de método generado automáticamente
		
	}
}