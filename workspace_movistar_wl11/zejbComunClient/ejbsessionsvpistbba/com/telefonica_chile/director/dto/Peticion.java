/*
 * Created on Dec 6, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.telefonica_chile.director.dto;

import java.io.Serializable;

/**
 * @author dfiguer
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class Peticion implements Serializable{
	private Integer idPeticion;
	private Integer idAgencia;
	private String mutacion;

	
	/**
	 * @return
	 */
	public Integer getIdAgencia() {
		return idAgencia;
	}

	/**
	 * @return
	 */
	public Integer getIdPeticion() {
		return idPeticion;
	}

	/**
	 * @return
	 */
	public String getMutacion() {
		return mutacion;
	}

	/**
	 * @param integer
	 */
	public void setIdAgencia(Integer integer) {
		idAgencia = integer;
	}

	/**
	 * @param integer
	 */
	public void setIdPeticion(Integer integer) {
		idPeticion = integer;
	}

	/**
	 * @param integer
	 */
	public void setMutacion(String integer) {
		mutacion = integer;
	}

}
