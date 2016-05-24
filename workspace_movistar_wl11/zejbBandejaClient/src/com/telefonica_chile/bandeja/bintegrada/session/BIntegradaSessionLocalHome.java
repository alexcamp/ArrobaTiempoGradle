package com.telefonica_chile.bandeja.bintegrada.session;
/**
 * Local Home interface for Enterprise Bean: BIntegradaSession
 */
public interface BIntegradaSessionLocalHome extends javax.ejb.EJBLocalHome {
	
	public static final String JNDI_NAME = "ejb/com/telefonica_chile/bandeja/bintegrada/session/BIntegradaSessionHome";		

	/**
	 * Creates a default instance of Session Bean: BIntegradaSession
	 */
	public com
		.telefonica_chile
		.bandeja
		.bintegrada
		.session
		.BIntegradaSessionLocal create()
		throws javax.ejb.CreateException;
}
