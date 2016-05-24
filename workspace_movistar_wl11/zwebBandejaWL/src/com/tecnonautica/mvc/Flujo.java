package com.tecnonautica.mvc;



import javax.servlet.http.HttpServletRequest;

/**
 * @author jguridi
 *
 * Esta clase est� encargada de definir que vista corresponde mostrar despues de una 
 * acci�n determinada, en caso que no est� determinada directamente en la configuraci�n. 
 * 
 * Se espera que un flujo interactue con la l�gica de negocio para definir en terminos 
 * del estado de la aplicaci�n que vista corresponde mostrar.
 * 
 * 
 */
public abstract class Flujo {
	private ControladorDeAplicacion controladorApp;
	/**
	 * Este m�todo debe implementarse en las clases heredadas de Flujo y es llamado por el 
	 * controlador de entrada para definir la proxima vista a mostrar.
	 * 
	 * @param request
	 * 
	 */
	abstract public String getProximaVista(HttpServletRequest request);
	
	
	
	void setControladorApp(ControladorDeAplicacion controladprApp){
			this.controladorApp=controladprApp;
	}
	
	protected ControladorDeAplicacion getControladorDeAplicacion(){
		return controladorApp; 
	}
}
