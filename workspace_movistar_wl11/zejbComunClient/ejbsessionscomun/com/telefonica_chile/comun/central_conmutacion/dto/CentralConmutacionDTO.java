/*
 * Created on Mar 11, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.telefonica_chile.comun.central_conmutacion.dto;

import java.io.Serializable;

/**
 * @author ptoliva
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class CentralConmutacionDTO implements Serializable{
	
	private Long   idCentralConmutacion;
	private String codigoCentralConmutacion;
	private String nombreCentralConmutacion;
	private String marcaCentralConmutacion;
	private String modeloCentralConmutacion;
	private Integer numeroNodo; 
	private String CodigoCcnMadre;

	/**
	 * @return
	 */
	public String getCodigoCentralConmutacion() {
		return codigoCentralConmutacion;
	}

	/**
	 * @return
	 */
	public Long getIdCentralConmutacion() {
		return idCentralConmutacion;
	}

	/**
	 * @return
	 */
	public String getMarcaCentralConmutacion() {
		return marcaCentralConmutacion;
	}

	/**
	 * @return
	 */
	public String getModeloCentralConmutacion() {
		return modeloCentralConmutacion;
	}

	/**
	 * @return
	 */
	public String getNombreCentralConmutacion() {
		return nombreCentralConmutacion;
	}

	/**
	 * @param string
	 */
	public void setCodigoCentralConmutacion(String string) {
		codigoCentralConmutacion = string;
	}

	/**
	 * @param long1
	 */
	public void setIdCentralConmutacion(Long long1) {
		idCentralConmutacion = long1;
	}

	/**
	 * @param string
	 */
	public void setMarcaCentralConmutacion(String string) {
		marcaCentralConmutacion = string;
	}

	/**
	 * @param string
	 */
	public void setModeloCentralConmutacion(String string) {
		modeloCentralConmutacion = string;
	}

	/**
	 * @param string
	 */
	public void setNombreCentralConmutacion(String string) {
		nombreCentralConmutacion = string;
	}

	/**
	 * @return
	 */
	public Integer getNumeroNodo() {
		return numeroNodo;
	}

	/**
	 * @param integer
	 */
	public void setNumeroNodo(Integer integer) {
		numeroNodo = integer;
	}

	/**
	 * @return
	 */
	public String getCodigoCcnMadre() {
		return CodigoCcnMadre;
	}

	/**
	 * @param string
	 */
	public void setCodigoCcnMadre(String string) {
		CodigoCcnMadre = string;
	}

}
