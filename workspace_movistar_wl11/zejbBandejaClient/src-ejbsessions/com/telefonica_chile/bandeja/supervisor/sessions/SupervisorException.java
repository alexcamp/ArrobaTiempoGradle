package com.telefonica_chile.bandeja.supervisor.sessions;

import com.tecnonautica.utiles.excepciones.NestedException;

public class SupervisorException extends NestedException {
	public SupervisorException(String msg) {
		super(msg);
	}

	public SupervisorException(String msg, Exception exc) {
		super(msg, exc);
	}

	public SupervisorException(Exception e) {
		super(e);
	}
}
