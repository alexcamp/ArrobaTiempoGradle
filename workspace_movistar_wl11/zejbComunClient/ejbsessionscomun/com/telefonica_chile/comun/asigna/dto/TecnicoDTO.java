/*
 * Created on Mar 3, 2005
 * 
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.telefonica_chile.comun.asigna.dto;

import java.io.Serializable;


public class TecnicoDTO implements Serializable,Comparable {
	
	private String nombre;
	private String apellido;
	private String  codigo;

	public TecnicoDTO() {
	}
	
	/**
	 * @return
	 */
	public String getApellido() {
		return apellido;
	}

	/**
	 * @return
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * @return
	 */
	public String getNombre() {
		return nombre;
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
	public void setCodigo(String string) {
		codigo = string;
	}

	/**
	 * @param string
	 */
	public void setNombre(String string) {
		nombre = string;
	}
	
	public int compareTo(Object o)
	{
		TecnicoDTO otro=(TecnicoDTO) o;
		if(this.nombre!=null && otro.getNombre()!=null)
			return this.nombre.compareTo(otro.getNombre());
		return 0;
	}

}
