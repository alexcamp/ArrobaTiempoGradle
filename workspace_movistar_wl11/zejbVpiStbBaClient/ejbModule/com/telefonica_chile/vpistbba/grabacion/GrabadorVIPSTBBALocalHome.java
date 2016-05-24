package com.telefonica_chile.vpistbba.grabacion;
/**
 * Local Home interface for Enterprise Bean: GrabadorVIPSTBBA
 */
public interface GrabadorVIPSTBBALocalHome extends javax.ejb.EJBLocalHome {
	
	public static final String JNDI_NAME = "ejb/com/telefonica_chile/vpistbba/grabacion/GrabadorVIPSTBBALocalHome";

	/**
	 * Creates a default instance of Session Bean: GrabadorVIPSTBBA
	 */
	public com.telefonica_chile.vpistbba.grabacion.GrabadorVIPSTBBALocal create() throws javax.ejb.CreateException;
}
