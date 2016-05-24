/*
 * Created on Mar 3, 2005
 * 
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.telefonica_chile.bandeja.web.agenda;

import java.util.Date;



public class FechaCompromisoAgendamientoDTO {
	
	Long idPeticion = null;
	Date fechaCompromiso = null;
	String horaDesde = null;
	String horaHasta = null;
	Integer idRango = null;
	String conTecnico = null;

	String nombreTecnico = null;
	Long idTecnico = null;
	
	Long idTipoAgenda = null;
	String userMac = null;
	Byte estado = null;
	Integer idCausaCierre = null;
	Long idUsuario = null; 
	Date fechaAtencion = null;
	
	Long idGaudi = null;

	public FechaCompromisoAgendamientoDTO() {
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
	public Integer getIdRango() {
		return idRango;
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
	 * @param integer
	 */
	public void setIdRango(Integer integer) {
		idRango = integer;
	}

	/**
	 * @return
	 */
	public Long getIdPeticion() {
		return idPeticion;
	}

	/**
	 * @param long1
	 */
	public void setIdPeticion(Long long1) {
		idPeticion = long1;
	}

	/**
	 * @return
	 */
	public Long getIdTipoAgenda() {
		return idTipoAgenda;
	}

	/**
	 * @param long1
	 */
	public void setIdTipoAgenda(Long long1) {
		idTipoAgenda = long1;
	}

	/**
	 * @return
	 */
	public String getUserMac() {
		return userMac;
	}

	/**
	 * @param string
	 */
	public void setUserMac(String string) {
		userMac = string;
	}

	/**
	 * @return
	 */
	public String getConTecnico() {
		return conTecnico;
	}

	/**
	 * @param string
	 */
	public void setConTecnico(String string) {
		conTecnico = string;
	}

	/**
	 * @return
	 */
	public String getNombreTecnico() {
		return nombreTecnico;
	}

	/**
	 * @param string
	 */
	public void setNombreTecnico(String string) {
		nombreTecnico = string;
	}

	/**
	 * @return
	 */
	public Long getIdTecnico() {
		return idTecnico;
	}

	/**
	 * @param long1
	 */
	public void setIdTecnico(Long long1) {
		idTecnico = long1;
	}

	/**
	 * @return
	 */
	public Byte getEstado() {
		return estado;
	}

	/**
	 * @param byte1
	 */
	public void setEstado(Byte byte1) {
		estado = byte1;
	}

	/**
	 * @return
	 */
	public Integer getIdCausaCierre() {
		return idCausaCierre;
	}

	/**
	 * @param integer
	 */
	public void setIdCausaCierre(Integer integer) {
		idCausaCierre = integer;
	}

	/**
	 * @return
	 */
	public Long getIdUsuario() {
		return idUsuario;
	}

	/**
	 * @param long1
	 */
	public void setIdUsuario(Long long1) {
		idUsuario = long1;
	}

	/**
	 * @return
	 */
	public Date getFechaAtencion() {
		return fechaAtencion;
	}

	/**
	 * @param date
	 */
	public void setFechaAtencion(Date date) {
		fechaAtencion = date;
	}

	/**
	 * @return
	 */
	public Long getIdGaudi() {
		return idGaudi;
	}

	/**
	 * @param long1
	 */
	public void setIdGaudi(Long long1) {
		idGaudi = long1;
	}

}
