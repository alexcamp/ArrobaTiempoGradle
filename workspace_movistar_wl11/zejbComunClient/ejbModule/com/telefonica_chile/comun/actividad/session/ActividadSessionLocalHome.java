package com.telefonica_chile.comun.actividad.session;
/**
 * Local Home interface for Enterprise Bean: ActividadSession
 */
public interface ActividadSessionLocalHome extends javax.ejb.EJBLocalHome {
	public static final String JNDI_NAME = "ejb/com/telefonica_chile/comun/actividad/session/ActividadSessionLocalHome";
		
	/**
	 * Creates a default instance of Session Bean: ActividadSession
	 */
	public com
		.telefonica_chile
		.comun
		.actividad
		.session
		.ActividadSessionLocal create()
		throws javax.ejb.CreateException;
}
