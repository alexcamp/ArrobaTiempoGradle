package com.tecnonautica.utiles.ldap;

import directory.util.LdapException;

public class SimpleLdapException extends RuntimeException {
	LdapException ex;

	public SimpleLdapException() {
		super();
	}

	public SimpleLdapException(String msg) {
		super(msg);
	}

	public SimpleLdapException(String msg, Exception ex) {
		super(msg);
		if (ex instanceof LdapException)
			this.ex = (LdapException) ex;
		else
			msg += ": " + ex;
	}

	public LdapException getLdapException() {
		return ex;
	}
}
