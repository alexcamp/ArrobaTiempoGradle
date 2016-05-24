package com.tecnonautica.seguridad;

/**
 * @author jguridi
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class NoExisteControladorDeSeguridadPorDefecto
	extends RuntimeException {
	NoExisteControladorDeSeguridadPorDefecto(String recurso){
		super("Al intentar controlar recurso: '"+recurso+"'");
	}
}
