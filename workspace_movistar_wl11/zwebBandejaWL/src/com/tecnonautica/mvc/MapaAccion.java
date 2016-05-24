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
public class MapaAccion {
	private String url;
	private String clase;
	private String vista;
	private String flujo="";
	private Accion accion=null;
	private HashMap mapas=new HashMap();
	  
	
	public MapaAccion(String url,String clase, String vista){
	
		this.url=url;
		this.clase=clase;
		this.vista=vista;
		this.flujo=null;
		
	}
	
	public MapaAccion(String url,String clase, String vista, String flujo){
	
		this.url=url;
		this.clase=clase;
		this.vista=vista;
		this.flujo=flujo;		
	}
	
	/**
	 * Returns the accion.
	 * @return String
	 */
	public String getClase() {
		return clase;
	}
	
	
	public String getFlujo(){
		return flujo;
	}
	
	public boolean tieneFlujo(){
		
		if (!flujo.equals("")){
			return true;
		}
		else{
			return false;
		}
		
	}
	

	/**
	 * Returns the url.
	 * @return String
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * Returns the vista.
	 * @return String
	 */
	public String getVista() {
		return vista;
	}

	/**
	 * @return
	 */
	public HashMap getMapas() {
		return mapas;
	}

	/**
	 * @param map
	 */
	public void setMapas(HashMap map) {
		mapas = map;
	}
	
	String getDestino(String resultado){
		try{
			Mapa mapa=(Mapa)mapas.get(resultado);
			return mapa.getDestino();
		}
		catch(NullPointerException e){
			return null;
		}
	}	
	
	//devuelve si el destino es una accion o una vista (valores ACCION=1 o VISTA=2)
	int getTipoDestino(String resultado){
		Mapa mapa=(Mapa)mapas.get(resultado);
		try{
			return mapa.getTipoDestino();
		}
		catch(NullPointerException e){
			return 0;
		}
	}
	
	public Accion getAccion(){
		
		if (accion !=null){
			return accion;
		}
		
		synchronized(MapaAccion.class){
		
			try {
				accion = (Accion)getClass().getClassLoader().loadClass(clase).newInstance();
			} 
			catch (InstantiationException e) {
				throw(new AccionNoEncontradaException("La clase '"+clase+"' no puede instanciarse"));	
			} 
			catch (IllegalAccessException e) {
				throw(new AccionNoEncontradaException("Acceso ilegal a la clase '"+clase));		
			} 
			catch (ClassNotFoundException e) {
				throw(new AccionNoEncontradaException("La clase '"+clase+"' no se encontró"));	
			}
		
		}
		
		return null;
	}
	
		
}
