package com.telefonica_chile.bandeja.datos.bandeja;
/**
 * Home interface for Enterprise Bean: BandejaSession
 */
public interface BandejaSessionHome extends javax.ejb.EJBHome {
	public static final String JNDI_NAME = "ejb/com/telefonica_chile/bandeja/datos/bandeja/BandejaSessionHome";
	/**
	 * Creates a default instance of Session Bean: BandejaSession
	 */
	public com.telefonica_chile.bandeja.datos.bandeja.BandejaSession create()
		throws javax.ejb.CreateException, java.rmi.RemoteException;
}
