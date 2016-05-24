package com.telefonica_chile.atiempo.javaWf;
/**
 * Home interface for Enterprise Bean: WFSession
 */
public interface WFSessionHome extends javax.ejb.EJBHome {
	
	public static final String JNDI_NAME = "ejb/com/telefonica_chile/atiempo/javaWf/WFSessionLocalHome";

	/**
	 * Creates a default instance of Session Bean: WFSession
	 */
	public com.telefonica_chile.atiempo.javaWf.WFSession create()
		throws javax.ejb.CreateException, java.rmi.RemoteException;
}
