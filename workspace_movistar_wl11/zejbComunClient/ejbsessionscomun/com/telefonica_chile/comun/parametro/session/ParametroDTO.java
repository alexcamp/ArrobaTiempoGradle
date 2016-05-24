/*
 * Created on Apr 15, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.telefonica_chile.comun.parametro.session;

import java.io.Serializable;

/**
 * @author luolave
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ParametroDTO implements Serializable{

	/**
	 * 
	 */
	public ParametroDTO() {
		super();
	}
	
	private Long idParametro;
	private String nombreParametro;
	private String valorParametro;
	

	/**
	 * @return
	 */
	public Long getIdParametro() {
		return idParametro;
	}

	/**
	 * @return
	 */
	public String getNombreParametro() {
		return nombreParametro;
	}

	/**
	 * @return
	 */
	public String getValorParametro() {
		return valorParametro;
	}

	/**
	 * @param long1
	 */
	public void setIdParametro(Long long1) {
		idParametro = long1;
	}

	/**
	 * @param string
	 */
	public void setNombreParametro(String string) {
		nombreParametro = string;
	}

	/**
	 * @param string
	 */
	public void setValorParametro(String string) {
		valorParametro = string;
	}

}
