package com.telefonica_chile.atiempo.javaWf;
/**
 * Local Home interface for Enterprise Bean: WFSession
 */
public interface WFSessionLocalHome extends javax.ejb.EJBLocalHome {
	public static final String JNDI_NAME = "ejb/com/telefonica_chile/atiempo/javaWf/WFSessionLocalHome";
	
	/**
	 * Creates a default instance of Session Bean: WFSession
	 */
	public com.telefonica_chile.atiempo.javaWf.WFSessionLocal create()
		throws javax.ejb.CreateException;
}
