/*
 * Created on Nov 22, 2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.telefonica_chile.comun.segmento.dto;

import java.io.Serializable;

/**
 * @author cegatic
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class segmentoDTO implements Serializable{
	
	private Long idSegmento = null;
	private Long idFamiliaSegmento = null;
	private String descripcion = null;
	private String codigo = null;
	

	/**
	 * @return
	 */
	public String getCodigo() {
		return codigo;
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
	public Long getIdFamiliaSegmento() {
		return idFamiliaSegmento;
	}

	/**
	 * @return
	 */
	public Long getIdSegmento() {
		return idSegmento;
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
	public void setDescripcion(String string) {
		descripcion = string;
	}

	/**
	 * @param long1
	 */
	public void setIdFamiliaSegmento(Long long1) {
		idFamiliaSegmento = long1;
	}

	/**
	 * @param long1
	 */
	public void setIdSegmento(Long long1) {
		idSegmento = long1;
	}

}
