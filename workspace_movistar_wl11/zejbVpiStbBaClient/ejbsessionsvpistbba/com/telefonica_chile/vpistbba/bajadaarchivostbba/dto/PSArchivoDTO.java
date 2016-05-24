/*
 * Created on Mar 12, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.telefonica_chile.vpistbba.bajadaarchivostbba.dto;


/**
 * @author ptoliva
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class PSArchivoDTO {
	
	private Long   codigoPS;
	private Long   idTipoOC;
	private String tipoOC;

	/**
	 * @return
	 */
	public PSArchivoDTO() {
	}

	/**
	 * @return
	 */
	public Long getCodigoPS() {
		return codigoPS;
	}

	/**
	 * @return
	 */
	public String getTipoOC() {
		return tipoOC;
	}

	/**
	 * @param long1
	 */
	public void setCodigoPS(Long long1) {
		codigoPS = long1;
	}

	/**
	 * @param string
	 */
	public void setTipoOC(String string) {
		tipoOC = string;
	}

	/**
	 * @return
	 */
	public Long getIdTipoOC() {
		return idTipoOC;
	}

	/**
	 * @param long1
	 */
	public void setIdTipoOC(Long long1) {
		idTipoOC = long1;
	}

}
