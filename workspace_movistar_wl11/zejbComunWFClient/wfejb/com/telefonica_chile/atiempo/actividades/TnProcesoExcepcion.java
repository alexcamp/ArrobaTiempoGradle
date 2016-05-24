/*
 * Creado el Feb 3, 2005
 *
 */
package com.telefonica_chile.atiempo.actividades;

/**
 * @author Pa-T! (Richard Andreu)
 *
 */
public class TnProcesoExcepcion extends Exception {

	/**
	 * @param arg0
	 */
	public TnProcesoExcepcion(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}
	
	public TnProcesoExcepcion(String s, Exception e) {
		super(s + ": " + e);
	}

}
