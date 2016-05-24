package com.telefonica_chile.bandeja.helpers;

import com.tecnonautica.utiles.excepciones.NestedException;

/**
 * Parametros insuficientes o invalidos.
 */
public class ParametrosIncorrectosException extends NestedException {
	public ParametrosIncorrectosException(String s) {
		super(s);
	}
	public ParametrosIncorrectosException(String s, Exception e) {
		super(s + ": " + e);
	}
}
