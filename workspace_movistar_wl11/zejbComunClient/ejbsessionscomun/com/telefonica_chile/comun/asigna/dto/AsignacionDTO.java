/*
 * Created on Mar 3, 2005
 * 
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.telefonica_chile.comun.asigna.dto;

import java.io.Serializable;
import java.util.Date;


public class AsignacionDTO implements Serializable {
	
	private Long idRango;
	private Long idTipoAgenda;
	private Long idUsuario;
	private Long numeroPeticion;
	private Date fechaCompromiso;
	private String horaDesde = "";
	private String horaHasta = "";

	// Para el despliegue.
	private String nombreTecnico = "";
	private String nombreTipoCompromiso = "";
	private String nombreRangoCompromiso = "";


	public AsignacionDTO() {
	}


	/**
	 * @return
	 */
	public Date getFechaCompromiso() {
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
	public Long getIdRango() {
		return idRango;
	}

	/**
	 * @return
	 */
	public Long getIdTipoAgenda() {
		return idTipoAgenda;
	}

	/**
	 * @return
	 */
	public Long getIdUsuario() {
		return idUsuario;
	}

	/**
	 * @return
	 */
	public Long getNumeroPeticion() {
		return numeroPeticion;
	}

	/**
	 * @param date
	 */
	public void setFechaCompromiso(Date date) {
		fechaCompromiso = date;
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
	 * @param long1
	 */
	public void setIdRango(Long long1) {
		idRango = long1;
	}

	/**
	 * @param long1
	 */
	public void setIdTipoAgenda(Long long1) {
		idTipoAgenda = long1;
	}

	/**
	 * @param long1
	 */
	public void setIdUsuario(Long long1) {
		idUsuario = long1;
	}

	/**
	 * @param long1
	 */
	public void setNumeroPeticion(Long long1) {
		numeroPeticion = long1;
	}

	/**
	 * @return
	 */
	public String getNombreRangoCompromiso() {
		return nombreRangoCompromiso;
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
	public String getNombreTipoCompromiso() {
		return nombreTipoCompromiso;
	}

	/**
	 * @param string
	 */
	public void setNombreRangoCompromiso(String string) {
		nombreRangoCompromiso = string;
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
	public void setNombreTipoCompromiso(String string) {
		nombreTipoCompromiso = string;
	}

}
