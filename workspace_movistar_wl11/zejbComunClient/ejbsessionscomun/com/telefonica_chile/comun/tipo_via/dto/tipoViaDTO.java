/*
 * Created on Nov 29, 2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.telefonica_chile.comun.tipo_via.dto;

import java.io.Serializable;

/**
 * @author cegatic
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class tipoViaDTO implements Serializable{
	private Integer idTipoVia = null;
	private String descripcion = null;
	

	/**
	 * @return
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @return
	 */
	public Integer getIdTipoVia() {
		return idTipoVia;
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
	public void setIdTipoVia(Integer integer) {
		idTipoVia = integer;
	}

}
