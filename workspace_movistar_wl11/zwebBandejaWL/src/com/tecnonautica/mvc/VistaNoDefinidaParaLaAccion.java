package com.tecnonautica.mvc;

/**
 * @author jguridi
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class VistaNoDefinidaParaLaAccion extends RuntimeException {
	public VistaNoDefinidaParaLaAccion(String nombreAccion){
		super("No se ha especificado una vista para la accion: '"+nombreAccion+"'");
	}
}
