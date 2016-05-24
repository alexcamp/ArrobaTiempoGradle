package com.telefonica_chile.bandeja.datos;

import com.tecnonautica.utiles.excepciones.NestedRuntimeException;

public class DatosBandejaRuntimeException extends NestedRuntimeException {
	public DatosBandejaRuntimeException() {
		super();
	}

	public DatosBandejaRuntimeException(String msg) {
		super(msg);
	}

	public DatosBandejaRuntimeException(Exception e) {
		super(e);
	}

	public DatosBandejaRuntimeException(String msg, Exception e) {
		super(msg, e);
	}
}
