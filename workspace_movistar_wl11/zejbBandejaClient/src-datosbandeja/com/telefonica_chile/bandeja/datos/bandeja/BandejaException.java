package com.telefonica_chile.bandeja.datos.bandeja;

import com.tecnonautica.utiles.excepciones.NestedException;

public class BandejaException extends NestedException {

	public BandejaException() {
		super();
	}

	public BandejaException(String msg) {
		super(msg);
	}

	public BandejaException(Exception e) {
		super(e);
	}

	public BandejaException(String msg, Exception e) {
		super(msg, e);
	}
}
