package com.tecnonautica.mvc;

/**
 * @author jguridi
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class PlantillaNoDefinida extends RuntimeException {
	PlantillaNoDefinida(String nombreVista){
		super("No se definio una plantilla para la vista: '"+nombreVista+"'");
	}
}
