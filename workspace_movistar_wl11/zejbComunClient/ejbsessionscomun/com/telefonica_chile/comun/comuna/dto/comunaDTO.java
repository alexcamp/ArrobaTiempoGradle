/*
 * Created on Nov 29, 2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.telefonica_chile.comun.comuna.dto;

import java.io.Serializable;

/**
 * @author cegatic
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class comunaDTO implements Serializable{
	private Integer idComuna = null;
	private Integer idProvincia = null;
	private Long idCiudad = null;
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
	public Long getIdCiudad() {
		return idCiudad;
	}

	/**
	 * @return
	 */
	public Integer getIdComuna() {
		return idComuna;
	}

	/**
	 * @return
	 */
	public Integer getIdProvincia() {
		return idProvincia;
	}

	/**
	 * @param string
	 */
	public void setDescripcion(String string) {
		descripcion = string;
	}

	/**
	 * @param long1
	 */
	public void setIdCiudad(Long long1) {
		idCiudad = long1;
	}

	/**
	 * @param integer
	 */
	public void setIdComuna(Integer integer) {
		idComuna = integer;
	}

	/**
	 * @param integer
	 */
	public void setIdProvincia(Integer integer) {
		idProvincia = integer;
	}

}
