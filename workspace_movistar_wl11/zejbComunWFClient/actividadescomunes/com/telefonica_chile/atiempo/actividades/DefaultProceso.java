/*
 * Creado el Feb 4, 2005
 *
 */
package com.telefonica_chile.atiempo.actividades;

import java.util.Date;
import java.util.Map;


/**
 * @author Pa-T! (Richard Andreu)
 *
 */
public class DefaultProceso extends Proceso {

	/**
	 * @param idTemplateWf
	 * @param idInstancia
	 * @param datos
	 */
	public DefaultProceso(String idTemplateWf, Map datos) {
		super(idTemplateWf, genIdInstancia(), datos);
	}
	
	public DefaultProceso(String template, String numPeticion, Map datos){
		super(template, numPeticion, datos);
	}
	
	protected static String genIdInstancia() {
		return Long.toString((new Date()).getTime()); 
	}

	/* (non-Javadoc)
	 * @see com.telefonica_chile.atiempo.actividades.IProceso#onInicio()
	 */
	public void onInicio() throws TnProcesoExcepcion {}

}
