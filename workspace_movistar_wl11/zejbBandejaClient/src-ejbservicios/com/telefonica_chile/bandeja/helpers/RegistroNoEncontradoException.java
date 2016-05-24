package com.telefonica_chile.bandeja.helpers;

/**
 * Alguna busqueda no encontro una peticion que deberia existir segun el usuario.
 */
public class RegistroNoEncontradoException extends Exception {
	public RegistroNoEncontradoException(String s) {
		super(s);
	}
}
