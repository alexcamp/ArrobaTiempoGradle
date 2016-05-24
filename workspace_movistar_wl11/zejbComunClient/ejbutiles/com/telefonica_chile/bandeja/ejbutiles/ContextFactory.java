package com.telefonica_chile.bandeja.ejbutiles;

/**
 * @author Pa-T!
 *
 */

import javax.naming.InitialContext;
import javax.naming.NamingException;

public class ContextFactory {

	static private ContextFactory jmsfactory = new ContextFactory();
	private InitialContext initialContext = null;

	protected ContextFactory() {
		super();
		try {
			initialContext = new InitialContext();
		} catch (NamingException namingException) {
			namingException.printStackTrace();
		}
	}

	static private ContextFactory singleton() {
		return jmsfactory;
	}

	public static Object lookup(String jmsRef) throws NamingException {
		return ContextFactory.singleton().gJMS(jmsRef);
	}

	private Object gJMS(String jmsRef) throws NamingException {
		if (initialContext != null) {
			Object nsObject =
				initialContext.lookup(jmsRef);
			return nsObject;
		} else {
			throw new NamingException("HomeFactory: no hay InitialContext");
		}
	}
}
