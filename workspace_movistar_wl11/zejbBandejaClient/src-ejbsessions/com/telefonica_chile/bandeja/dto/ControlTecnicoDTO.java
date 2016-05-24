package com.telefonica_chile.bandeja.dto;

import java.io.Serializable;

public class ControlTecnicoDTO implements Serializable
{
	private String codTecnico;
	private String nombre;
	private String apellido;
	private Long emprId;	

	/**
	 * @return
	 */
	public String getApellido() {
		return apellido;
	}

	/**
	 * @return
	 */
	public String getCodTecnico() {
		return codTecnico;
	}

	/**
	 * @return
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @return
	 */
	public Long getEmprId() {
		return emprId;
	}

	/**
	 * @param string
	 */
	public void setApellido(String string) {
		apellido = string;
	}

	/**
	 * @param string
	 */
	public void setCodTecnico(String string) {
		codTecnico = string;
	}

	/**
	 * @param string
	 */
	public void setNombre(String string) {
		nombre = string;
	}

	/**
	 * @param long1
	 */
	public void setEmprId(Long long1) {
		emprId = long1;
	}

}
