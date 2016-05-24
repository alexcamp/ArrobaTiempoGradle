/*
 * Created on 18-04-2007
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package co.com.telefonica.atiempo.soltec.actividades;

import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;

import com.telefonica_chile.atiempo.actividades.TnProcesoExcepcion;

/**
 * @author Admin
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public abstract class ActividadManualSTEJB extends ActividadST_EJB {


//	protected abstract void incializaActividadST() throws TnProcesoExcepcion;
//	protected abstract void onInicioActividadST() throws TnProcesoExcepcion;
//	protected abstract void onTerminoActividadST() throws TnProcesoExcepcion;
//	/* (non-Javadoc)
//	 * @see co.com.telefonica.atiempo.soltec.actividades.ActividadST_EJB#inicializaActividad()
//	 */
//	protected final void inicializaActividad() throws TnProcesoExcepcion{
//		this.incializaActividadST();
//	}	
//			 
//	protected final void onInicioActividad() throws TnProcesoExcepcion {
//		this.onInicioActividadST();
//		if(!this.realizarTerminoInmediato){		
//			this.publicar();
//		}
//	}
//	
//	protected final void onTerminoActividad() throws TnProcesoExcepcion{
//		this.onTerminoActividadST();
//		if(!this.realizarTerminoInmediato){		
//			this.despublicar();
//		}
//	}

	protected abstract void incializaActividadST(ActividadEJBDTO act) throws TnProcesoExcepcion;
	protected abstract void onInicioActividadST(ActividadEJBDTO act) throws TnProcesoExcepcion;
	protected abstract void onTerminoActividadST(ActividadEJBDTO act) throws TnProcesoExcepcion;
	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.soltec.actividades.ActividadST_EJB#inicializaActividad()
	 */
	protected final void inicializaActividad(ActividadEJBDTO act) throws TnProcesoExcepcion{
		this.incializaActividadST(act);
//		if(!act.isRealizarTerminoInmediato()){
//			act.setGrabarWfInstancia(true);
//		}		
	}	
			 
	protected final void onInicioActividad(ActividadEJBDTO act) throws TnProcesoExcepcion {
		this.onInicioActividadST(act);
		if(!act.isRealizarTerminoInmediato()){
			this.publicar(act);
		}
	}
	
	protected final void onTerminoActividad(ActividadEJBDTO act) throws TnProcesoExcepcion{
		this.onTerminoActividadST(act);
		if(act.isNoTerminar()){ // solo hace la logica de termino, pero no cierra la actividad.
			return;
		}
		if(!act.isRealizarTerminoInmediato()){
			
			this.despublicar(act);
		}
	}
}
