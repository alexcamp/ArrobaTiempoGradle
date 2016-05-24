package com.telefonica_chile.bandeja.datos.bandeja;
/**
 * Local Home interface for Enterprise Bean: BandejaSession
 */
public interface BandejaSessionLocalHome extends javax.ejb.EJBLocalHome {
	public static final String JNDI_NAME = "ejb/com/telefonica_chile/bandeja/datos/bandeja/BandejaSessionHome";
	
	
	/**
	 * Creates a default instance of Session Bean: BandejaSession
	 */
	public com
		.telefonica_chile
		.bandeja
		.datos
		.bandeja
		.BandejaSessionLocal create()
		throws javax.ejb.CreateException;
}
