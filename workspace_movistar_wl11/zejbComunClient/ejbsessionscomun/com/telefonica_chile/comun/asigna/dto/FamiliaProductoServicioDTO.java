/*
 * Created on Mar 10, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.telefonica_chile.comun.asigna.dto;

import java.io.Serializable;


public class FamiliaProductoServicioDTO implements Serializable{
	
	Long fapsId;
	String fapsNombre;
	String fapsCodigo;
	
		
	public FamiliaProductoServicioDTO( Long fapsId) {
		this.fapsId = fapsId;					
	}
	

	/**
	 * @return
	 */
	public String getFapsCodigo() {
		return fapsCodigo;
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
	public String getFapsNombre() {
		return fapsNombre;
	}

	/**
	 * @param string
	 */
	public void setFapsCodigo(String string) {
		fapsCodigo = string;
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
	public void setFapsNombre(String string) {
		fapsNombre = string;
	}

}
