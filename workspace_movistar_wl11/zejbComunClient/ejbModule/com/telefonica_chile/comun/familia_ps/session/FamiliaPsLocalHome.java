package com.telefonica_chile.comun.familia_ps.session;
/**
 * Local Home interface for Enterprise Bean: FamiliaPs
 */
public interface FamiliaPsLocalHome extends javax.ejb.EJBLocalHome {
	public static final String JNDI_NAME = "ejb/com/telefonica_chile/comun/familia_ps/session/FamiliaPsLocalHome";
	
	/**
	 * Creates a default instance of Session Bean: FamiliaPs
	 */
	public com
		.telefonica_chile
		.comun
		.familia_ps
		.session
		.FamiliaPsLocal create()
		throws javax.ejb.CreateException;
}
