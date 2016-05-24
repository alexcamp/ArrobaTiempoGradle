package com.telefonica_chile.bandeja.torreControl;
/**
 * Local Home interface for Enterprise Bean: ParametrosTCSession
 */
public interface ParametrosTCSessionLocalHome extends javax.ejb.EJBLocalHome {
	public static final String JNDI_NAME = "ejb/com/telefonica_chile/bandeja/torreControl/ParametrosTCSessionLocalHome";

	public com
		.telefonica_chile
		.bandeja
		.torreControl
		.ParametrosTCSessionLocal create()
		throws javax.ejb.CreateException;
}
