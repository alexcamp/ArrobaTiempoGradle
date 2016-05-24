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
public abstract class ActividadManualEJB extends ActividadVPI_EJB{

//	protected abstract void incializaActividadVPI() throws TnProcesoExcepcion;
//	protected abstract void onInicioActividadVPI() throws TnProcesoExcepcion;
//	protected abstract void onTerminoActividadVPI() throws TnProcesoExcepcion;
//		 
//	protected final void inicializaActividad() throws TnProcesoExcepcion{
//		this.incializaActividadVPI();
//	}	
//			 
//	protected final void onInicioActividad() throws TnProcesoExcepcion {
//		this.onInicioActividadVPI();		
//		this.publicar();
//	}
//	
//	protected final void onTerminoActividad() throws TnProcesoExcepcion{
//		this.onTerminoActividadVPI();
//		if(this.noTerminar){ // solo hace la logica de termino, pero no cierra la actividad.
//			return;
//		}
//		this.despublicar();
//	}

	protected abstract void incializaActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion;
	protected abstract void onInicioActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion;
	protected abstract void onTerminoActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion;
		 
	protected final void inicializaActividad(ActividadEJBDTO act) throws TnProcesoExcepcion{
		this.incializaActividadVPI(act);
	}	
			 
	protected final void onInicioActividad(ActividadEJBDTO act) throws TnProcesoExcepcion {
		act.setRealizarTerminoInmediato(false);
		this.onInicioActividadVPI(act);		
		//TODO Alvaro Esta ha que super probarlo en PI 
		if(act.isRealizarTerminoInmediato()!= true){
			this.publicar(act);
		}
	}
	
	protected final void onTerminoActividad(ActividadEJBDTO act) throws TnProcesoExcepcion{
		//Despublico y si no hay nada que despublicar, no hago nada
//		int cantidadDespublicados = 0;
//		cantidadDespublicados=this.despublicar(act);
//		if(cantidadDespublicados==0){
//			//No hago logica, ni termino la actividad
//			act.setNoTerminar(true);
//			return;
//		}
//		this.onTerminoActividadVPI(act);
//		if(act.isNoTerminar()){ // solo hace la logica de termino, pero no cierra la actividad.
//			return;
//		}
//		//Despublico y si no hay nada que despublicar, genero rollback y no hago nada mas
//		int cantidadDespublicados = 0;
//		cantidadDespublicados=this.despublicar(act);
//		if(cantidadDespublicados==0){
//			//No hago logica, ni termino la actividad
//			throw new TnProcesoExcepcion 
//			return;
//		}	
		this.onTerminoActividadVPI(act);
		if(act.isNoTerminar()){ // solo hace la logica de termino, pero no cierra la actividad.
			return;
		}
		this.despublicar(act);
	}
	
}
