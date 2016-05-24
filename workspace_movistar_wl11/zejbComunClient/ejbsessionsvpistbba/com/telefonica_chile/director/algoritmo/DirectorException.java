/*
 * Created on Jan 6, 2005
 *
 */
package com.telefonica_chile.director.algoritmo;

import com.tecnonautica.utiles.excepciones.NestedException;

/**
 * @author dfiguer
 *
 */
public class DirectorException extends NestedException {

	/**
	 * 
	 */
	public DirectorException() {
		super();
	}

	/**
	 * @param msg
	 */
	public DirectorException(String msg) {
		super(msg);
	}

	/**
	 * @param e
	 */
	public DirectorException(Exception e) {
		super(e);
	}

	/**
	 * @param msg
	 * @param e
	 */
	public DirectorException(String msg, Exception e) {
		super(msg, e);
	}

}
