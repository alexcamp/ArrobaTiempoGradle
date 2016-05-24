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
public abstract class ActividadAutomaticaSTEJB extends ActividadST_EJB {

	protected abstract void incializaActividadST(ActividadEJBDTO act) throws TnProcesoExcepcion;
	protected abstract void onInicioActividadST(ActividadEJBDTO act) throws TnProcesoExcepcion;
	protected abstract void onTerminoActividadST(ActividadEJBDTO act) throws TnProcesoExcepcion;
	
	protected final void inicializaActividad(ActividadEJBDTO act) throws TnProcesoExcepcion{
		this.incializaActividadST(act);
		if (!act.isRealizarTerminoInmediato()){
//			Se graba en WF instancia actividad si es que no se termina la actividad y necesita grabarse en  esta tabla. Por ej: Las actividades manuales(que se publican), no necesitan grabar en esta tabla, pues guardan el IdInstancia del WF en la url de la tabla BIntegrada.
//			Las automáticas que no terminan, necesitan guardarse en esta tabla para obtener el idinstancia del WF y poder cerrar la actividad
			act.setGrabarWfInstancia(true);
		}
	}
		 
	protected final void onInicioActividad(ActividadEJBDTO act) throws TnProcesoExcepcion {
		this.onInicioActividadST(act);		
	}
	
	protected final void onTerminoActividad(ActividadEJBDTO act) throws TnProcesoExcepcion{	
		this.onTerminoActividadST(act);
	}


}
