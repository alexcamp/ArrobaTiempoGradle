package com.telefonica_chile.vpistbba.parametro.session;
/**
 * Home interface for Enterprise Bean: ParametroSession
 */
public interface ParametroSessionHome extends javax.ejb.EJBHome {
	public static final String JNDI_NAME = "ejb/com/telefonica_chile/vpistbba/parametro/session/ParametroSessionHome";

	/**
	 * Creates a default instance of Session Bean: ParametroSession
	 */
	public com
		.telefonica_chile
		.vpistbba
		.parametro
		.session
		.ParametroSession create()
		throws javax.ejb.CreateException, java.rmi.RemoteException;
}
