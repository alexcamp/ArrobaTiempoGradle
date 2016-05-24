/*
 * Created on Feb 23, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.telefonica_chile.comun.ps.dto;

/**
 * @author ptoliva
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class DatosCabeceraDTO {
	
	public DatosCabeceraDTO() {
		super();
	}
	
	private String nombreTecnico = "";
	private String puntoVenta = "";
	private String segmento = "";
	private String tipoTrabajo = "";

	/**
	 * @return
	 */
	public String getNombreTecnico() {
		return nombreTecnico;
	}

	/**
	 * @return
	 */
	public String getPuntoVenta() {
		return puntoVenta;
	}

	/**
	 * @return
	 */
	public String getSegmento() {
		return segmento;
	}

	/**
	 * @return
	 */
	public String getTipoTrabajo() {
		return tipoTrabajo;
	}

	/**
	 * @param string
	 */
	public void setNombreTecnico(String string) {
		nombreTecnico = string;
	}

	/**
	 * @param string
	 */
	public void setPuntoVenta(String string) {
		puntoVenta = string;
	}

	/**
	 * @param string
	 */
	public void setSegmento(String string) {
		segmento = string;
	}

	/**
	 * @param string
	 */
	public void setTipoTrabajo(String string) {
		tipoTrabajo = string;
	}

}
