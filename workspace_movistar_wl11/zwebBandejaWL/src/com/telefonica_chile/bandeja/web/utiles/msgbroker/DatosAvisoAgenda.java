/*
 * Created on Feb 24, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.telefonica_chile.bandeja.web.utiles.msgbroker;

import java.io.Serializable;

/**
 * @author jvelasc
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class DatosAvisoAgenda implements Serializable{
	private String nroPeticion = "";
	private String fechaCompromiso = "";
	private String horaDesde = "";
	private String horaHasta = "";
	private String tipoHora = "";
	private String nombreTecnico = "";
	private String error = "";
	private String descError = "";

	/**
	 * @return
	 */
	public String getDescError() {
		return descError;
	}

	/**
	 * @return
	 */
	public String getError() {
		return error;
	}

	/**
	 * @return
	 */
	public String getFechaCompromiso() {
		return fechaCompromiso;
	}

	/**
	 * @return
	 */
	public String getHoraDesde() {
		return horaDesde;
	}

	/**
	 * @return
	 */
	public String getHoraHasta() {
		return horaHasta;
	}

	/**
	 * @return
	 */
	public String getNombreTecnico() {
		return nombreTecnico;
	}

	/**
	 * @return
	 */
	public String getNroPeticion() {
		return nroPeticion;
	}

	/**
	 * @return
	 */
	public String getTipoHora() {
		return tipoHora;
	}

	/**
	 * @param string
	 */
	public void setDescError(String string) {
		descError = string;
	}

	/**
	 * @param string
	 */
	public void setError(String string) {
		error = string;
	}

	/**
	 * @param string
	 */
	public void setFechaCompromiso(String string) {
		fechaCompromiso = string;
	}

	/**
	 * @param string
	 */
	public void setHoraDesde(String string) {
		horaDesde = string;
	}

	/**
	 * @param string
	 */
	public void setHoraHasta(String string) {
		horaHasta = string;
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
	public void setNroPeticion(String string) {
		nroPeticion = string;
	}

	/**
	 * @param string
	 */
	public void setTipoHora(String string) {
		tipoHora = string;
	}

}
