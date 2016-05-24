package com.tecnonautica.mvc;

/**
 * @author jguridi
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class AccionNoEncontradaException extends RuntimeException {
	
	public	AccionNoEncontradaException(String texto) {
		super(texto);	
	}
	
}
