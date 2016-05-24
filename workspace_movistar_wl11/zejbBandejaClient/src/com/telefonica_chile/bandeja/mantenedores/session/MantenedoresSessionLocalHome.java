package com.telefonica_chile.bandeja.mantenedores.session;
/**
 * Local Home interface for Enterprise Bean: MantenedoresSession
 */
public interface MantenedoresSessionLocalHome extends javax.ejb.EJBLocalHome {
	public static final String JNDI_NAME = "ejb/com/telefonica_chile/bandeja/mantenedores/session/MantenedoresSessionLocalHome";

	/**
	 * Creates a default instance of Session Bean: MantenedoresSession
	 */
	public com
		.telefonica_chile
		.bandeja
		.mantenedores
		.session
		.MantenedoresSessionLocal create()
		throws javax.ejb.CreateException;
}
