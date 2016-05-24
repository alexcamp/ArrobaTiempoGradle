/*
 * Created on Oct 19, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.telefonica_chile.comun.vistaagencia.dto;

import java.io.Serializable;

/**
 * @author gavalen
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class vistaAgenciaDTO implements Serializable{
	private Long   usuaId		;
	private String usuaNombre	;
	private String agenciaId	;
	private Long   rolId		;
	private Long   habiId		;

	/**
	 * @return
	 */
	public String getAgenciaId() {
		return agenciaId;
	}

	/**
	 * @return
	 */
	public Long getHabiId() {
		return habiId;
	}

	/**
	 * @return
	 */
	public Long getRolId() {
		return rolId;
	}

	/**
	 * @return
	 */
	public Long getUsuaId() {
		return usuaId;
	}

	/**
	 * @return
	 */
	public String getUsuaNombre() {
		return usuaNombre;
	}

	/**
	 * @param string
	 */
	public void setAgenciaId(String string) {
		agenciaId = string;
	}

	/**
	 * @param long1
	 */
	public void setHabiId(Long long1) {
		habiId = long1;
	}

	/**
	 * @param long1
	 */
	public void setRolId(Long long1) {
		rolId = long1;
	}

	/**
	 * @param long1
	 */
	public void setUsuaId(Long long1) {
		usuaId = long1;
	}

	/**
	 * @param string
	 */
	public void setUsuaNombre(String string) {
		usuaNombre = string;
	}

}
