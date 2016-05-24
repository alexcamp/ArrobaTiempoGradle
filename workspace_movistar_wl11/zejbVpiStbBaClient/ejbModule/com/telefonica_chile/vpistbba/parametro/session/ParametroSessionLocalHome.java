package com.telefonica_chile.vpistbba.parametro.session;
/**
  * Local Home interface for Enterprise Bean: ParametroSession
  */

public interface ParametroSessionLocalHome extends javax.ejb.EJBLocalHome {
	
	public static final String JNDI_NAME = "ejb/com/telefonica_chile/vpistbba/parametro/session/ParametroSessionHome";

	/**
	  * Creates a default instance of Session Bean: ParametroSession
	  */

	public com
		.telefonica_chile
		.vpistbba
		.parametro
		.session
		.ParametroSessionLocal create()
		throws javax.ejb.CreateException;
}
