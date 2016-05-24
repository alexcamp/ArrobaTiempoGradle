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
public class LineaArchivoDTO {
	
	private Double area;
	private Double telefono;

	private String codigoCentral;
	private String codigoCentralMadre;
	private String nombreCentral;
	private String nombreCentralMadre;
	private String tecnologia;
	private String territorio;

	private String numeroEquipo;
	
	private Integer nodo;
	private String  preselector;

	private String fonoLogico1;
	private String fonoLogico2;
	

	public LineaArchivoDTO() {
	}
	/**
	 * @return
	 */
	public Double getArea() {
		return area;
	}

	/**
	 * @return
	 */
	public String getFonoLogico1() {
		return fonoLogico1;
	}

	/**
	 * @return
	 */
	public String getFonoLogico2() {
		return fonoLogico2;
	}

	/**
	 * @return
	 */
	public Integer getNodo() {
		return nodo;
	}

	/**
	 * @return
	 */
	public String getNombreCentral() {
		return nombreCentral;
	}

	/**
	 * @return
	 */
	public String getNombreCentralMadre() {
		return nombreCentralMadre;
	}

	/**
	 * @return
	 */
	public String getPreselector() {
		return preselector;
	}

	/**
	 * @return
	 */
	public String getTecnologia() {
		return tecnologia;
	}

	/**
	 * @return
	 */
	public Double getTelefono() {
		return telefono;
	}

	/**
	 * @return
	 */
	public String getTerritorio() {
		return territorio;
	}

	/**
	 * @param double1
	 */
	public void setArea(Double double1) {
		area = double1;
	}

	/**
	 * @param string
	 */
	public void setFonoLogico1(String string) {
		fonoLogico1 = string;
	}

	/**
	 * @param string
	 */
	public void setFonoLogico2(String string) {
		fonoLogico2 = string;
	}

	/**
	 * @param integer
	 */
	public void setNodo(Integer integer) {
		nodo = integer;
	}

	/**
	 * @param string
	 */
	public void setNombreCentral(String string) {
		nombreCentral = string;
	}

	/**
	 * @param string
	 */
	public void setNombreCentralMadre(String string) {
		nombreCentralMadre = string;
	}

	/**
	 * @param string
	 */
	public void setPreselector(String string) {
		preselector = string;
	}

	/**
	 * @param string
	 */
	public void setTecnologia(String string) {
		tecnologia = string;
	}

	/**
	 * @param double1
	 */
	public void setTelefono(Double double1) {
		telefono = double1;
	}

	/**
	 * @param string
	 */
	public void setTerritorio(String string) {
		territorio = string;
	}

	/**
	 * @return
	 */
	public String getCodigoCentral() {
		return codigoCentral;
	}

	/**
	 * @return
	 */
	public String getCodigoCentralMadre() {
		return codigoCentralMadre;
	}

	/**
	 * @param string
	 */
	public void setCodigoCentral(String string) {
		codigoCentral = string;
	}

	/**
	 * @param string
	 */
	public void setCodigoCentralMadre(String string) {
		codigoCentralMadre = string;
	}

	/**
	 * @return
	 */
	public String getNumeroEquipo() {
		return numeroEquipo;
	}

	/**
	 * @param string
	 */
	public void setNumeroEquipo(String string) {
		numeroEquipo = string;
	}

}
