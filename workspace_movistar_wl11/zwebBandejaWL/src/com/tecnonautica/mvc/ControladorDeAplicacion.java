/*
 * Created on 12-may-2003
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package com.tecnonautica.mvc;

/**
 * @author jguridi
 *
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class ControladorDeAplicacion {
	
	private Vista ultimaVista;
	private Evento evento;
	private String nombreUltimaAccion;
	private Accion ultimaAccion;
	
	
	/**
	 * Solo para uso interno de mvc
	 * 
	 * @param accion
	 */
	void setUltimaAccion(Accion accion){
		ultimaAccion=accion;
	}
	
	
	/**
	 * Solo para uso interno de mvc
	 */
	Accion getUltimaAccion(){
		return ultimaAccion;
	}
	
	
	void setUltimaVista(Vista ultimaVista){
		this.ultimaVista = ultimaVista;
	}
			
	public Vista getUltimaVista(){
		return null;
	}
	
	public Evento getUltimoEvento(){
		return evento;
	}
	
	public String getNombreUltimaAccion(){
		return nombreUltimaAccion;
	}
	
	void limpiarEvento(){
		evento=null;
	}	
}
