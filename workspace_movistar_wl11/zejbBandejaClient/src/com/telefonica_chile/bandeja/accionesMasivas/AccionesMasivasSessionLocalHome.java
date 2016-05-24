package com.telefonica_chile.bandeja.accionesMasivas;
/**
 * Local Home interface for Enterprise Bean: AccionesMasivasSession
 */
public interface AccionesMasivasSessionLocalHome
	extends javax.ejb.EJBLocalHome {
		
	public static final String JNDI_NAME = "ejb/com/telefonica_chile/bandeja/accionesMasivas/AccionesMasivasSessionHome";
	
	
	/**
	 * Creates a default instance of Session Bean: AccionesMasivasSession
	 */
	public com
		.telefonica_chile
		.bandeja
		.accionesMasivas
		.AccionesMasivasSessionLocal create()
		throws javax.ejb.CreateException;
}
