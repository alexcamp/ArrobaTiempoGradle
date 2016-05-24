/*
 * Created on Feb 28, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.telefonica_chile.comun.causa.dto;

import java.io.Serializable;

/**
 * @author defuenz
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class CausaDTO implements Serializable{

	/**
	 * 
	 */
	public CausaDTO() {
		super();
	}
	
	Long causaId;
	String causaCodigo = "";
	String causaNombre = "";
	String codigoIvr ="";
	

	/**
	 * @return
	 */
	public String getCausaCodigo() {
		return causaCodigo;
	}

	/**
	 * @return
	 */
	public Long getCausaId() {
		return causaId;
	}

	/**
	 * @return
	 */
	public String getCausaNombre() {
		return causaNombre;
	}

	/**
	 * @param string
	 */
	public void setCausaCodigo(String string) {
		causaCodigo = string;
	}

	/**
	 * @param long1
	 */
	public void setCausaId(Long long1) {
		causaId = long1;
	}

	/**
	 * @param string
	 */
	public void setCausaNombre(String string) {
		causaNombre = string;
	}

	/**
	 * @return
	 */
	public String getCodigoIvr() {
		return codigoIvr;
	}

	/**
	 * @param string
	 */
	public void setCodigoIvr(String string) {
		codigoIvr = string;
	}

}
