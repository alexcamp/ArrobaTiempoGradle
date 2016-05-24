package com.telefonica_chile.comun.operacion_comercial.session;
/**
 * Local Home interface for Enterprise Bean: OperacionComercialSession
 */
public interface OperacionComercialSessionLocalHome
	extends javax.ejb.EJBLocalHome {

	public static final String JNDI_NAME = "ejb/com/telefonica_chile/comun/operacion_comercial/session/OperacionComercialSessionLocalHome";		

	/**
	 * Creates a default instance of Session Bean: OperacionComercialSession
	 */
	public com
		.telefonica_chile
		.comun
		.operacion_comercial
		.session
		.OperacionComercialSessionLocal create()
		throws javax.ejb.CreateException;
}
