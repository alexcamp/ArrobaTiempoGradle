/*
 * Created on Mar 11, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.telefonica_chile.comun.territorio.dto;

import java.io.Serializable;

/**
 * @author ptoliva
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class TerritorioDTO implements Serializable{
	
	private Long idTerr;
	private Long idAgen;
	private String codTerr;
	private String nombreTerr;

	/**
	 * @return
	 */
	public Long getIdAgen() {
		return idAgen;
	}

	/**
	 * @return
	 */
	public Long getIdTerr() {
		return idTerr;
	}

	/**
	 * @return
	 */
	public String getNombreTerr() {
		return nombreTerr;
	}

	
	/**
	 * @param long1
	 */
	public void setIdAgen(Long long1) {
		idAgen = long1;
	}

	/**
	 * @param long1
	 */
	public void setIdTerr(Long long1) {
		idTerr = long1;
	}

	/**
	 * @param string
	 */
	public void setNombreTerr(String string) {
		nombreTerr = string;
	}

	/**
	 * @return
	 */
	public String getCodTerr() {
		return codTerr;
	}

	/**
	 * @param string
	 */
	public void setCodTerr(String string) {
		codTerr = string;
	}

}
