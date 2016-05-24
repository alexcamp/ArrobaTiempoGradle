package com.telefonica_chile.bandeja.ejbutiles;

import javax.mail.Session;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class MailSessionFactory {
	public static final String JNDI = "java:comp/env/mail/AtiempoSession";

	public static Session getMailSession() throws NamingException {
		return (Session) new InitialContext().lookup(JNDI);
	}
}
