package com.telefonica_chile.bandeja.accionesMasivas;
/**
 * Home interface for Enterprise Bean: AccionesMasivasSession
 */
public interface AccionesMasivasSessionHome extends javax.ejb.EJBHome {
	public static final String JNDI_NAME = "ejb/com/telefonica_chile/bandeja/accionesMasivas/AccionesMasivasSessionHome";
	
	/**
	 * Creates a default instance of Session Bean: AccionesMasivasSession
	 */
	public com
		.telefonica_chile
		.bandeja
		.accionesMasivas
		.AccionesMasivasSession create()
		throws javax.ejb.CreateException, java.rmi.RemoteException;
}
