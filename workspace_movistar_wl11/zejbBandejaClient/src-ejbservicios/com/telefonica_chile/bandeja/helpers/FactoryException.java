package com.telefonica_chile.bandeja.helpers;

public class FactoryException extends Exception {

	public FactoryException() {
		super();
	}

	public FactoryException(String s) {
		super(s);
	}

	public FactoryException(String s, Exception e) {
		super(s + ": " + e);
	}
}

