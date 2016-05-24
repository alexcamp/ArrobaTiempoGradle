package com.telefonica_chile.comun.mail.session;
/**
 * Home interface for Enterprise Bean: MailSession
 */
public interface MailSessionHome extends javax.ejb.EJBHome {
	/**
	 * Creates a default instance of Session Bean: MailSession
	 */
	public com.telefonica_chile.comun.mail.session.MailSession create()
		throws javax.ejb.CreateException, java.rmi.RemoteException;
}
