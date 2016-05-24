/*
 * Created on Mar 9, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.telefonica_chile.comun.opearacion_comercial.dto;

import java.io.Serializable;

/**
 * @author defuenz
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class OperacionComercialDTO implements Serializable {

	/**
	 * 
	 */
	public OperacionComercialDTO() {
		super();
		this.nombreOpco = "";
	}

	private String idOpco;
	private String nombreOpco;
	private String tipoOC;
	private Integer idTipoTrabajo;
	
	


	/**
	 * @return
	 */
	public String getNombreOpco() {
		return nombreOpco;
	}

	/**
	 * @param string
	 */
	public void setNombreOpco(String string) {
		nombreOpco = string;
	}

	/**
	 * @return
	 */
	
	/**
	 * @return
	 */
	public String getIdOpco() {
		return idOpco;
	}

	/**
	 * @param string
	 */
	public void setIdOpco(String string) {
		idOpco = string;
	}

	/**
	 * @return
	 */
	public String getTipoOC() {
		return tipoOC;
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
	public Integer getIdTipoTrabajo() {
		return idTipoTrabajo;
	}

	/**
	 * @param integer
	 */
	public void setIdTipoTrabajo(Integer integer) {
		idTipoTrabajo = integer;
	}

}
