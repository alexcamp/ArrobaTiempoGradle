package com.telefonica_chile.bandeja.mantenedores;

public class ManagerException extends Exception {
	public ManagerException(String msg) {
		super(msg);
	}

	public ManagerException(String msg, Exception exc) {
		super(msg + ": " + exc);
	}

	public ManagerException(Exception exc) {
		super(exc.getMessage());
	}
}
