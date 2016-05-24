package com.telefonica_chile.bandeja.servicios.publicador;
/**
 * Home interface for Enterprise Bean: PublicadorBandeja
 */
public interface PublicadorBandejaHome extends javax.ejb.EJBHome {
	public static final String JNDI_NAME = "ejb/com/telefonica_chile/bandeja/servicios/publicador/PublicadorBandejaHome";
	/**
	 * Creates a default instance of Session Bean: PublicadorBandeja
	 */
	public com
		.telefonica_chile
		.bandeja
		.servicios
		.publicador
		.PublicadorBandeja create()
		throws javax.ejb.CreateException, java.rmi.RemoteException;
}
