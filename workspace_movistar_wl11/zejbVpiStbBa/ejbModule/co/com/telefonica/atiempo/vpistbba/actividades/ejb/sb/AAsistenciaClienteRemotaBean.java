package co.com.telefonica.atiempo.vpistbba.actividades.ejb.sb;

import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;

import com.telefonica_chile.atiempo.actividades.TnProcesoExcepcion;

/**
 * Bean implementation class for Enterprise Bean: AAsistenciaClienteRemota
 */
public class AAsistenciaClienteRemotaBean
	extends
		co.com.telefonica.atiempo.vpistbba.actividades.ActividadManualEJB
	implements
		javax.ejb.SessionBean {
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
	}

	protected void onInicioActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {
			
		System.out.println("ESTAMOS EN AASISTENCIA CLIENTE REMOTA!");
		
	}

	protected void onTerminoActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {
	}
}
