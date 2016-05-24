package com.telefonica_chile.comun.mail.session;
import javax.mail.Session;
import javax.naming.NamingException;
/**
 * Local interface for Enterprise Bean: MailSession
 */
public interface MailSessionLocal extends javax.ejb.EJBLocalObject {
	public Session getMailSession() throws NamingException;
}
