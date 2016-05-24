package com.telefonica_chile.bandeja.bintegrada.session;
/**
 * Home interface for Enterprise Bean: BIntegradaSession
 */
public interface BIntegradaSessionHome extends javax.ejb.EJBHome {
	/**
	 * Creates a default instance of Session Bean: BIntegradaSession
	 */
	
	public static final String JNDI_NAME = "ejb/com/telefonica_chile/bandeja/bintegrada/session/BIntegradaSessionHome";

	public com
		.telefonica_chile
		.bandeja
		.bintegrada
		.session
		.BIntegradaSession create()
		throws javax.ejb.CreateException, java.rmi.RemoteException;
}
