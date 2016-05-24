package com.telefonica_chile.bandeja.torreControl;
/**
 * Local Home interface for Enterprise Bean: TorreControlSession
 */
public interface TorreControlSessionLocalHome extends javax.ejb.EJBLocalHome {
	public static final String JNDI_NAME = "ejb/com/telefonica_chile/bandeja/torreControl/TorreControlSessionLocalHome";

	/**
	 * Creates a default instance of Session Bean: TorreControlSession
	 */
	public com
		.telefonica_chile
		.bandeja
		.torreControl
		.TorreControlSessionLocal create()
		throws javax.ejb.CreateException;
}
