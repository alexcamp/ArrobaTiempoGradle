package co.com.telefonica.atiempo.vpistbba.actividades.df.configuracion.ejb.sb;

import java.util.Iterator;
import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.vpistbba.estructura_flujo.DATOS_ATVPISTBBA;
import co.com.telefonica.atiempo.vpistbba.recursos.RecursosDelegate;

import com.telefonica_chile.atiempo.actividades.TnProcesoExcepcion;

/**
 * Bean implementation class for Enterprise Bean: AConfiguracionFijaMovil
 */
public class ARecargaFijaMovilBean extends
	co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB
	implements
		javax.ejb.SessionBean  {
	private javax.ejb.SessionContext mySessionCtx;
	/**
	 * getSessionContext
	 */
	public javax.ejb.SessionContext getSessionContext() {
		return mySessionCtx;
	}
	/**
	 * setSessionContext
	 */
	public void setSessionContext(javax.ejb.SessionContext ctx) {
		mySessionCtx = ctx;
	}
	/**
	 * ejbCreate
	 */
	public void ejbCreate() throws javax.ejb.CreateException {
	}
	/**
	 * ejbActivate
	 */
	public void ejbActivate() {
	}
	/**
	 * ejbPassivate
	 */
	public void ejbPassivate() {
	}
	/**
	 * ejbRemove
	 */
	public void ejbRemove() {
	}
	
	protected void incializaActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {
		// TODO Auto-generated method stub
		
	}
	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB#onInicioActividadVPI(co.com.telefonica.atiempo.actividades.ActividadEJBDTO)
	 */
	protected void onInicioActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {
		log.debug("Ejecutando ARecargarFijaMovilBean: nro peticion: [ "+act.getNumeroPeticion()+"]");
		
		try{
			RecursosDelegate recursosDelegate = new RecursosDelegate();
			if(!act.getDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_reversa).equals("S")){
				Iterator it_ps = act.getPsOk().iterator();
				recursosDelegate.recargaFijaMovil(act);
			}else{
				act.setObservacion("Reversa Baja");
				act.setRealizarTerminoInmediato(true);
			}
		}catch(ATiempoAppEx appEx){
			appEx.printStackTrace();
			throw new TnProcesoExcepcion ("Error en Actividad ARecargarFijaMovil ", appEx);
		}
	}
	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB#onTerminoActividadVPI(co.com.telefonica.atiempo.actividades.ActividadEJBDTO)
	 */
	protected void onTerminoActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {
		// TODO Auto-generated method stub
		
	}
}