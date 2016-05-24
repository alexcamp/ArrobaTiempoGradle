/*
 * Created on Feb 28, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.telefonica_chile.comun.datos_agencia.dto;

import java.io.Serializable;

/**
 * @author ptoliva
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class AgenciaDTO implements Serializable {	
	
	public Long agenId;
	public String agenCodigo;
	public String agenDescripcion;
	public String agenPasaporMdf;
	public Long zonaID;

	/**
	 * @return
	 */
	public String getAgenCodigo() {
		return agenCodigo;
	}

	/**
	 * @return
	 */
	public String getAgenDescripcion() {
		return agenDescripcion;
	}

	/**
	 * @return
	 */
	public Long getAgenId() {
		return agenId;
	}

	/**
	 * @return
	 */
	public String getAgenPasaporMdf() {
		return agenPasaporMdf;
	}

	/**
	 * @return
	 */
	public Long getZonaID() {
		return zonaID;
	}

	/**
	 * @param string
	 */
	public void setAgenCodigo(String string) {
		agenCodigo = string;
	}

	/**
	 * @param string
	 */
	public void setAgenDescripcion(String string) {
		agenDescripcion = string;
	}

	/**
	 * @param long1
	 */
	public void setAgenId(Long long1) {
		agenId = long1;
	}

	/**
	 * @param string
	 */
	public void setAgenPasaporMdf(String string) {
		agenPasaporMdf = string;
	}

	/**
	 * @param long1
	 */
	public void setZonaID(Long long1) {
		zonaID = long1;
	}

}
