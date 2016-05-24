package com.telefonica_chile.comun.parametro.session;
/**
 * Local Home interface for Enterprise Bean: ParametroComunSession
 */
public interface ParametroComunSessionLocalHome
	extends javax.ejb.EJBLocalHome {
	public static final String JNDI_NAME = "ejb/com/telefonica_chile/comun/parametro/session/ParametroComunSessionLocalHome";
	
	/**
	 * Creates a default instance of Session Bean: ParametroComunSession
	 */
	public com
		.telefonica_chile
		.comun
		.parametro
		.session
		.ParametroComunSessionLocal create()
		throws javax.ejb.CreateException;
}
