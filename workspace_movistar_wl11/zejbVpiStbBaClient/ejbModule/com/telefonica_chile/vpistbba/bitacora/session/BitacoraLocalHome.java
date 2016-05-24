package com.telefonica_chile.vpistbba.bitacora.session;
/**
 * Local Home interface for Enterprise Bean: Bitacora
 */
public interface BitacoraLocalHome extends javax.ejb.EJBLocalHome {
	
	public static final String JNDI_NAME = "ejb/com/telefonica_chile/vpistbba/bitacora/session/BitacoraLocalHome";	
	/**
	 * Creates a default instance of Session Bean: Bitacora
	 */
	public com
		.telefonica_chile
		.vpistbba
		.bitacora
		.session
		.BitacoraLocal create()
		throws javax.ejb.CreateException;
}
