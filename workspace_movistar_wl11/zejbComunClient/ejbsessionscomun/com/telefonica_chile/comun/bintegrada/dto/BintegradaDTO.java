/*
 * Created on Dec 23, 2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.telefonica_chile.comun.bintegrada.dto;

/**
 * @author mmoralr
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */

import java.io.Serializable;

public class BintegradaDTO implements Serializable{

	private String nombreUsuario;
	private String telefonoUsuario;
	private String emailUsuario;


	/**
	 * @return
	 */
	public String getEmailUsuario() {
		return emailUsuario;
	}

	/**
	 * @return
	 */
	public String getNombreUsuario() {
		return nombreUsuario;
	}

	/**
	 * @return
	 */
	public String getTelefonoUsuario() {
		return telefonoUsuario;
	}

	/**
	 * @param string
	 */
	public void setEmailUsuario(String string) {
		emailUsuario = string;
	}

	/**
	 * @param string
	 */
	public void setNombreUsuario(String string) {
		nombreUsuario = string;
	}

	/**
	 * @param string
	 */
	public void setTelefonoUsuario(String string) {
		telefonoUsuario = string;
	}

}

