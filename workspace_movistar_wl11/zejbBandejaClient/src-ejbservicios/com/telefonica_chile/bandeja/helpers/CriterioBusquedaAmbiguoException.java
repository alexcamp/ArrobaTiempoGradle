package com.telefonica_chile.bandeja.helpers;

/**
 * Alguna busqueda retorna mas de una peticion cuando deberia retornar solo uno.
 */
public class CriterioBusquedaAmbiguoException extends Exception {

	public CriterioBusquedaAmbiguoException(String s) {
		super(s);
	}

}
