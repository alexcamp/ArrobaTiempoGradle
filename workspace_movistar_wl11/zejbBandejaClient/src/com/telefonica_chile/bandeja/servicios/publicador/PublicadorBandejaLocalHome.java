package com.telefonica_chile.bandeja.servicios.publicador;
/**
 * Local Home interface for Enterprise Bean: PublicadorBandeja
 */
public interface PublicadorBandejaLocalHome extends javax.ejb.EJBLocalHome {
	
	public static final String JNDI_NAME = "ejb/com/telefonica_chile/bandeja/servicios/publicador/PublicadorBandejaLocalHome";	

	/**
	 * Creates a default instance of Session Bean: PublicadorBandeja
	 */
	public com
		.telefonica_chile
		.bandeja
		.servicios
		.publicador
		.PublicadorBandejaLocal create()
		throws javax.ejb.CreateException;
}
