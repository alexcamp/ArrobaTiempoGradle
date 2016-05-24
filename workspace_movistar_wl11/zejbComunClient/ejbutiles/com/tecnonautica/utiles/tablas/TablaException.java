package com.tecnonautica.utiles.tablas;

public class TablaException extends Exception {
	public TablaException() {
		super();
	}

	public TablaException(String s) {
		super(s);
	}

	public TablaException(String s, Exception e) {
		super(s + ": " + e);
	}
}
