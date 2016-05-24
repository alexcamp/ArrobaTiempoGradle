/*
 * Created on Feb 25, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.telefonica_chile.comun.datos_punto_venta.dto;

import java.io.Serializable;

/**
 * @author ptoliva
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class PuntoVentaDTO implements Serializable {	
	
	public PuntoVentaDTO() {
			super();
	}
	
	private Integer idPtoVenta;
	private String  nombre="";
	private String  codigo="";
	/**
	 * @return
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * @return
	 */
	public Integer getIdPtoVenta() {
		return idPtoVenta;
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
	public void setCodigo(String string) {
		codigo = string;
	}

	/**
	 * @param integer
	 */
	public void setIdPtoVenta(Integer integer) {
		idPtoVenta = integer;
	}

	/**
	 * @param string
	 */
	public void setNombre(String string) {
		nombre = string;
	}

}
