package com.tecnonautica.mvc;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

/**
 * @author jguridi
 * Esta clase es un conector entre los requerimientos que vienen de las 
 * vistas y el modelo y l�gica internos de la aplicaci�n.
 * Esta clase no debiera tener l�gica de aplicaci�n. Basicamente su trabajo
 * es tomar los par�metros del requerimiento, traducirlos al modelo orientado 
 * a objetos, llamar a las clases internas de la aplicaci�n que hacen la 
 * l�gica de negocio y dejar en un lugar accesible (sesion o request) 
 * los datos necesarios para la pr�xima vista del cliente.
 *   
 */
public abstract class Accion {

	private ServletContext servletContext;
	private ServletConfig servletConfig;
	private ControladorDeAplicacion controladorApp;
	private String resultado;
	
  /**
   * @param request servlet request
   * 
   * Este es el m�todo que debe ser implementado por las clases que hereden de esta.
   * este m�todo es llamado por el controlador de aplicaci�n para ejecutar la acci�n.
   * 
   */
  protected abstract void ejecutar(HttpServletRequest 
    request) throws Evento;
	
	public ServletContext getServletContext(){
		return servletContext;
	}
		
	protected void setServletContext(ServletContext context){
		servletContext=context;
	}
	
	public ServletContext getServletConfig(){
		return servletContext;
	}
		
	protected void setServletConfig(ServletConfig config){
		servletConfig=config;
	}
	
	/**
	 * Funcion solo para uso interno de MVC
	*/
	void setControladorApp(ControladorDeAplicacion controladprApp){
		this.controladorApp=controladprApp;
	}
	
	/**
	 * Enrega el controlador de aplicacion relacionado a esta accion
	 * */
	
	public final ControladorDeAplicacion getControladorDeAplicacion(){
		return controladorApp; 
	}
	
		
	

	/**
	 * @return
	 */
	String getResultado() {
		return resultado;
	}

	/**
	 * @param string
	 */
	protected final void setResultado(String string) {
		resultado = string;
	}

}
