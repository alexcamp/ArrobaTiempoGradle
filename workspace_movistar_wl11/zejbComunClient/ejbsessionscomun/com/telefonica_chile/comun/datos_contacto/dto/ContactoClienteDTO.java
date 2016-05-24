/*
 * Created on Mar 1, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.telefonica_chile.comun.datos_contacto.dto;

import java.io.Serializable;

public class ContactoClienteDTO  implements Serializable {	

	private Long contId;
	private Long contCliente;
	private Long contNumPeticion;
	private String contRut;
	private String contNombre;
	private String contApellido;
	private String contArea;
	private String contFono;
	
	
	/**
	 * @return
	 */
	public String getContApellido() {
		return contApellido;
	}

	/**
	 * @return
	 */
	public String getContArea() {
		return contArea;
	}

	/**
	 * @return
	 */
	public Long getContCliente() {
		return contCliente;
	}

	/**
	 * @return
	 */
	public String getContFono() {
		return contFono;
	}

	/**
	 * @return
	 */
	public Long getContId() {
		return contId;
	}

	/**
	 * @return
	 */
	public String getContNombre() {
		return contNombre;
	}

	/**
	 * @return
	 */
	public Long getContNumPeticion() {
		return contNumPeticion;
	}

	/**
	 * @return
	 */
	public String getContRut() {
		return contRut;
	}

	/**
	 * @param string
	 */
	public void setContApellido(String string) {
		contApellido = string;
	}

	/**
	 * @param string
	 */
	public void setContArea(String string) {
		contArea = string;
	}

	/**
	 * @param long1
	 */
	public void setContCliente(Long long1) {
		contCliente = long1;
	}

	/**
	 * @param string
	 */
	public void setContFono(String string) {
		contFono = string;
	}

	/**
	 * @param long1
	 */
	public void setContId(Long long1) {
		contId = long1;
	}

	/**
	 * @param string
	 */
	public void setContNombre(String string) {
		contNombre = string;
	}

	/**
	 * @param long1
	 */
	public void setContNumPeticion(Long long1) {
		contNumPeticion = long1;
	}

	/**
	 * @param string
	 */
	public void setContRut(String string) {
		contRut = string;
	}

}