package com.tecnonautica.mvc;

import java.util.HashMap;

/**
 * @author jguridi
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class Vista {
	
	private String nombre;
	private String plantilla;
	private HashMap areas;
	
	public Vista(){}
	
	public Vista(String nombre, String plantilla, HashMap areas){
		this.nombre=nombre;
		this.plantilla=plantilla;
		this.areas=areas;
	}
	
	public String getUrlArea(String area){
		
		return (String)areas.get(area);
	}
		
	public String getPlantilla(){return plantilla;}
	
	public String getNombre(){
		return nombre;
	}
}
