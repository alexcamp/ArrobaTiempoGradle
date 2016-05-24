package com.telefonica_chile.vpistbba.causa.session;
/**
 * Local Home interface for Enterprise Bean: CausaComun
 */
public interface CausaComunLocalHome extends javax.ejb.EJBLocalHome {
	public static final String JNDI_NAME = "ejb/com/telefonica_chile/vpistbba/causa/session/CausaComunLocalHome";
	
	/**
	 * Creates a default instance of Session Bean: CausaComun
	 */
	public com.telefonica_chile.vpistbba.causa.session.CausaComunLocal create()
		throws javax.ejb.CreateException;
}
