/*
 * Created on Oct 18, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.telefonica_chile.bandeja.dto;

import java.io.Serializable;

/**
 * @author gavalen
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class AgenciaTCDTO implements Serializable{
	
	private Long usuaId = new Long(0);
	private String usuaNombre = "";
	private String agenId = "";
	private Long rolId = new Long(0);

	/**
	 * @return
	 */
	public String getAgenId() {
		return agenId;
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
	public void setAgenId(String string) {
		agenId = string;
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
