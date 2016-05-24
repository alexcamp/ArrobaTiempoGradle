package com.telefonica_chile.bandeja.helpers;

/**
 * Se intenta agregar una peticion que viola criterio de unicidad.
 */
public class RegistroDuplicadoException extends Exception {
	public RegistroDuplicadoException(String s) {
		super(s);
	}
	
	public RegistroDuplicadoException(String s, Exception e) {
		super(s + ": " + e);
	}
}
