package com.telefonica_chile.comun.mail.session;
/**
 * Local Home interface for Enterprise Bean: MailSession
 */
public interface MailSessionLocalHome extends javax.ejb.EJBLocalHome {
	
	public final static String JNDI_NAME ="ejb/com/telefonica_chile/comun/mail/session/MailSessionHome";
	/**
	 * Creates a default instance of Session Bean: MailSession
	 */
	public com.telefonica_chile.comun.mail.session.MailSessionLocal create()
		throws javax.ejb.CreateException;
}
