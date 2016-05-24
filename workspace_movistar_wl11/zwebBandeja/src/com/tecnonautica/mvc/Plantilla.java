package com.tecnonautica.mvc;

import java.util.Hashtable;

/**
 * @author jguridi
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class Plantilla {
	public String nombre;
	private Hashtable areas;

	private Plantilla(){
	
	}
	
	public Plantilla(Hashtable areas){
		this();
		this.areas = areas;		
	}
	
	
	public void addUrl(String nombre,String url){
		areas.put(nombre,url);
	}

	public  String getUrlArea(String area){
		return (String)areas.get(area);
	}
}
