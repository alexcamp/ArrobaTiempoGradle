package com.tecnonautica.mvc;



import javax.servlet.http.HttpServletRequest;

/**
 * @author jguridi
 *
 * Esta clase está encargada de definir que vista corresponde mostrar despues de una 
 * acción determinada, en caso que no esté determinada directamente en la configuración. 
 * 
 * Se espera que un flujo interactue con la lógica de negocio para definir en terminos 
 * del estado de la aplicación que vista corresponde mostrar.
 * 
 * 
 */
public abstract class Flujo {
	private ControladorDeAplicacion controladorApp;
	/**
	 * Este método debe implementarse en las clases heredadas de Flujo y es llamado por el 
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
