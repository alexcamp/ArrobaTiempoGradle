package com.telefonica_chile.comun.asigna.session;
/**
 * Local Home interface for Enterprise Bean: AsignaSession
 */
public interface AsignaSessionLocalHome extends javax.ejb.EJBLocalHome {
	public static final String JNDI_NAME = "ejb/com/telefonica_chile/comun/asigna/session/AsignaSessionLocalHome";

	/**
	 * Creates a default instance of Session Bean: AsignaSession
	 */
	public com
		.telefonica_chile
		.comun
		.asigna
		.session
		.AsignaSessionLocal create()
		throws javax.ejb.CreateException;
}
