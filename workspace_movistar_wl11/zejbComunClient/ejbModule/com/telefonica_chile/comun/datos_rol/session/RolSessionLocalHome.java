package com.telefonica_chile.comun.datos_rol.session;
/**
 * Local Home interface for Enterprise Bean: RolSession
 */
public interface RolSessionLocalHome extends javax.ejb.EJBLocalHome {
	public static final String JNDI_NAME = "ejb/com/telefonica_chile/comun/datos_rol/session/RolSessionLocalHome";
	
	/**
	 * Creates a default instance of Session Bean: RolSession
	 */
	public com
		.telefonica_chile
		.comun
		.datos_rol
		.session
		.RolSessionLocal create()
		throws javax.ejb.CreateException;
}
