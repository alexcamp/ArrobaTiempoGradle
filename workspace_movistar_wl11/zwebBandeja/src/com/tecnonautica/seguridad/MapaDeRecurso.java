package com.tecnonautica.seguridad;

/**
 * @author jguridi
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class MapaDeRecurso {
	
	private String nombre;
	private String controlador; 
	
	public MapaDeRecurso(String nombre, String controlador){
		this.nombre=nombre;
		this.controlador=controlador;
	}
	
	public String getControlador(){
		return controlador;
	}
	
	public String getNombre(){
		return nombre;
	}
}
