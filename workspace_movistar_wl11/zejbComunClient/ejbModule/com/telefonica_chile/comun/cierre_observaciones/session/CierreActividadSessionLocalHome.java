package com.telefonica_chile.comun.cierre_observaciones.session;
/**
 * Local Home interface for Enterprise Bean: CierreActividadSession
 */
public interface CierreActividadSessionLocalHome
	extends javax.ejb.EJBLocalHome {
	public static final String JNDI_NAME = "ejb/com/telefonica_chile/comun/cierre_observaciones/session/CierreActividadSessionLocalHome";
		
	/**
	 * Creates a default instance of Session Bean: CierreActividadSession
	 */
	public com
		.telefonica_chile
		.comun
		.cierre_observaciones
		.session
		.CierreActividadSessionLocal create()
		throws javax.ejb.CreateException;
}
