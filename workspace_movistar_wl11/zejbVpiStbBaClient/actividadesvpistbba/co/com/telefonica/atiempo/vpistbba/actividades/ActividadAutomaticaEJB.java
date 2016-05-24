/*
 * Created on 08-03-2007
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package co.com.telefonica.atiempo.vpistbba.actividades;

import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;

import com.telefonica_chile.atiempo.actividades.TnProcesoExcepcion;

/**
 * @author VictorMan
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public abstract class ActividadAutomaticaEJB extends ActividadVPI_EJB{
	

//	protected abstract void incializaActividadVPI() throws TnProcesoExcepcion;
//	protected abstract void onInicioActividadVPI() throws TnProcesoExcepcion;
//	protected abstract void onTerminoActividadVPI() throws TnProcesoExcepcion;
//
//	protected final void inicializaActividad() throws TnProcesoExcepcion{
//		this.incializaActividadVPI();
//		if (!this.realizarTerminoInmediato){
////			Se graba en WF instancia actividad si es que no se termina la actividad y necesita grabarse en  esta tabla. Por ej: Las actividades manuales(que se publican), no necesitan grabar en esta tabla, pues guardan el IdInstancia del WF en la url de la tabla BIntegrada.
////			Las automáticas que no terminan, necesitan guardarse en esta tabla para obtener el idinstancia del WF y poder cerrar la actividad
//			this.esGrabarWfInstancia=true;
//		}
//	}
//		 
//	protected final void onInicioActividad() throws TnProcesoExcepcion {
//		this.onInicioActividadVPI();		
//	}
//	
//	protected final void onTerminoActividad() throws TnProcesoExcepcion{	
//		this.onTerminoActividadVPI();
//	}

	protected abstract void incializaActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion;
	protected abstract void onInicioActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion;
	protected abstract void onTerminoActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion;

	protected final void inicializaActividad(ActividadEJBDTO act) throws TnProcesoExcepcion{
		this.incializaActividadVPI(act);
		if (!act.isRealizarTerminoInmediato()){
//			Se graba en WF instancia actividad si es que no se termina la actividad y necesita grabarse en  esta tabla. Por ej: Las actividades manuales(que se publican), no necesitan grabar en esta tabla, pues guardan el IdInstancia del WF en la url de la tabla BIntegrada.
//			Las automáticas que no terminan, necesitan guardarse en esta tabla para obtener el idinstancia del WF y poder cerrar la actividad
			act.setGrabarWfInstancia(true);
		}
	}
		 
	protected final void onInicioActividad(ActividadEJBDTO act) throws TnProcesoExcepcion {
		this.onInicioActividadVPI(act);		
	}
	
	protected final void onTerminoActividad(ActividadEJBDTO act) throws TnProcesoExcepcion{	
		this.onTerminoActividadVPI(act);
	}


}
