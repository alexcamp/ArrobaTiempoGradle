/*
 * Created on Apr 21, 2008
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */

//TODO CR11267 adecarlini
package com.telefonica_chile.bandeja.dto;

/**
 * @author 804233
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class CodigoStDTO {
	
	String codigo;
	int tipo;
	String descripcion;
	long correlativo;
	
	

	/**
	 * @return
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * @return
	 */
	public long getCorrelativo() {
		return correlativo;
	}

	/**
	 * @return
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @return
	 */
	public int getTipo() {
		return tipo;
	}

	/**
	 * @param string
	 */
	public void setCodigo(String string) {
		codigo = string;
	}

	/**
	 * @param long1
	 */
	public void setCorrelativo(long long1) {
		correlativo = long1;
	}

	/**
	 * @param string
	 */
	public void setDescripcion(String string) {
		descripcion = string;
	}

	/**
	 * @param integer
	 */
	public void setTipo(int integer) {
		tipo = integer;
	}

}

