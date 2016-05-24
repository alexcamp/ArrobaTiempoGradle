package com.telefonica_chile.bandeja;

import com.tecnonautica.utiles.excepciones.NestedException;

public class BandejaException extends NestedException {
	public BandejaException(String msg) {
		super(msg);
	}
	
	public BandejaException(String msg, Exception exc) {
		super(msg, exc);
	}
	
	public BandejaException(Exception e) {
		super(e);
	}
}
