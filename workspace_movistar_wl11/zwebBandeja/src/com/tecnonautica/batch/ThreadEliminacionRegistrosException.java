/*
 * Created on Sep 26, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.tecnonautica.batch;

/**
 * @author Marco Alarc�n "Dono"
 * Clase de Excepci�n para el thread de eliminaci�n de registros en batch
 */
public class ThreadEliminacionRegistrosException extends Exception {

	/**
	 * Constructor para la excepci�n 
	 * @param texto
	 */
	public ThreadEliminacionRegistrosException(String texto) {
		super(texto);
	}

}
