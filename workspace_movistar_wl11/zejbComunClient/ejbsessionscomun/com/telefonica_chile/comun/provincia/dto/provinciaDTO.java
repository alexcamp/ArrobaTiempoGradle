/*
 * Created on Nov 29, 2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.telefonica_chile.comun.provincia.dto;

import java.io.Serializable;

/**
 * @author cegatic
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class provinciaDTO implements Serializable{
	private Integer idProvincia = null;
	private Integer idRegion = null;
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
	public Integer getIdProvincia() {
		return idProvincia;
	}

	/**
	 * @return
	 */
	public Integer getIdRegion() {
		return idRegion;
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
	public void setIdProvincia(Integer integer) {
		idProvincia = integer;
	}

	/**
	 * @param integer
	 */
	public void setIdRegion(Integer integer) {
		idRegion = integer;
	}

}
