/*
 * Created on Sep 26, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.tecnonautica.batch;

/**
 * @author Marco Alarcón "Dono"
 * Clase de Excepción para el thread de eliminación de registros en batch
 */
public class ThreadEliminacionRegistrosException extends Exception {

	/**
	 * Constructor para la excepción 
	 * @param texto
	 */
	public ThreadEliminacionRegistrosException(String texto) {
		super(texto);
	}

}
