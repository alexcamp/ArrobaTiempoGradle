/*
 * Created on Mar 7, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.telefonica_chile.comun.familia_ps.dto;

import java.io.Serializable;

/**
 * @author defuenz
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class FamPsRemotoDTO implements Serializable{

	/**
	 * 
	 */
	public FamPsRemotoDTO() {
		super();
		this.fapsNom = "";
		this.fapsCod = "";
	}

	private Long fapsId;
	private String fapsNom;
	private String fapsCod;
	/**
	 * @return
	 */
	public String getFapsCod() {
		return fapsCod;
	}

	/**
	 * @return
	 */
	public Long getFapsId() {
		return fapsId;
	}

	/**
	 * @return
	 */
	public String getFapsNom() {
		return fapsNom;
	}

	/**
	 * @param string
	 */
	public void setFapsCod(String string) {
		fapsCod = string;
	}

	/**
	 * @param long1
	 */
	public void setFapsId(Long long1) {
		fapsId = long1;
	}

	/**
	 * @param string
	 */
	public void setFapsNom(String string) {
		fapsNom = string;
	}

}
