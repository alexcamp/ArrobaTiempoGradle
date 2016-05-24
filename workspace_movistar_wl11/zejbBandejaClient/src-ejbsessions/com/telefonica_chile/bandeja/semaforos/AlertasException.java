package com.telefonica_chile.bandeja.semaforos;

import com.tecnonautica.utiles.excepciones.NestedException;

public class AlertasException extends NestedException{

	public AlertasException(String msg) {
		super(msg);
	}

	public AlertasException(String msg, Exception exc) {
		super(msg, exc);
	}

	public AlertasException(Exception e) {
		super(e);
	}
}
