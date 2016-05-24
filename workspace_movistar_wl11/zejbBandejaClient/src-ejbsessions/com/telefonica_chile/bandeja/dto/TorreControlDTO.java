/*
 * Created on Oct 27, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.telefonica_chile.bandeja.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * @author gavalen
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class TorreControlDTO implements Serializable{
	
	private Long petiNumero;
	private Date petiFechaIngreso    = new Date();	
	private Date petiFechaCompromiso = new Date();
	private String petiUsuarioEmisor;
	private String ticaId;	
	private Long petiOOSS;	
	private Date biFechaInicio = new Date();
	private String actDescripcion;
	private String clieNombre;
	private String clieApePaterno;
	private String clieApeMaterno;
	private Long clieSegmId;
	private String segmDescripcion;
	private String usuaNombre;
	private Double lineNumero;
	private String cecoNombre;
	private String agenDescripcion;
	private String  espeNombre;
	private String puveCodigo;
	private String petiRutVendedor;
	private String segmentoBandeja;

	// Para el Agendamiento.
	private Long idTipoAgendamiento;
	private String nombreTipoAgendamiento; 
	private Integer idRango;
	private String nombreRango;
	private String horaDesde;
	private String horaHasta;

	/**
	 * @return
	 */
	public String getActDescripcion() {
		return actDescripcion;
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
	public Date getBiFechaInicio() {
		return biFechaInicio;
	}

	/**
	 * @return
	 */
	public String getCecoNombre() {
		return cecoNombre;
	}

	/**
	 * @return
	 */
	public String getClieApeMaterno() {
		return clieApeMaterno;
	}

	/**
	 * @return
	 */
	public String getClieApePaterno() {
		return clieApePaterno;
	}

	/**
	 * @return
	 */
	public String getClieNombre() {
		return clieNombre;
	}

	/**
	 * @return
	 */
	public Long getClieSegmId() {
		return clieSegmId;
	}

	


	/**
	 * @return
	 */
	public Date getPetiFechaCompromiso() {
		return petiFechaCompromiso;
	}

	/**
	 * @return
	 */
	public Date getPetiFechaIngreso() {
		return petiFechaIngreso;
	}

	/**
	 * @return
	 */
	public Long getPetiNumero() {
		return petiNumero;
	}

	/**
	 * @return
	 */
	public Long getPetiOOSS() {
		return petiOOSS;
	}

	/**
	 * @return
	 */
	public String getPetiRutVendedor() {
		return petiRutVendedor;
	}

	/**
	 * @return
	 */
	public String getPetiUsuarioEmisor() {
		return petiUsuarioEmisor;
	}

	
	/**
	 * @return
	 */
	public String getSegmDescripcion() {
		return segmDescripcion;
	}

	/**
	 * @return
	 */
	public String getTicaId() {
		return ticaId;
	}

	/**
	 * @return
	 */
	public String getUsuaNombre() {
		return usuaNombre;
	}

	/**
	 * @param string
	 */
	public void setActDescripcion(String string) {
		actDescripcion = string;
	}

	/**
	 * @param string
	 */
	public void setAgenDescripcion(String string) {
		agenDescripcion = string;
	}

	/**
	 * @param date
	 */
	public void setBiFechaInicio(Date date) {
		biFechaInicio = date;
	}

	/**
	 * @param string
	 */
	public void setCecoNombre(String string) {
		cecoNombre = string;
	}

	/**
	 * @param string
	 */
	public void setClieApeMaterno(String string) {
		clieApeMaterno = string;
	}

	/**
	 * @param string
	 */
	public void setClieApePaterno(String string) {
		clieApePaterno = string;
	}

	/**
	 * @param string
	 */
	public void setClieNombre(String string) {
		clieNombre = string;
	}

	/**
	 * @param long1
	 */
	public void setClieSegmId(Long long1) {
		clieSegmId = long1;
	}

	


	/**
	 * @param date
	 */
	public void setPetiFechaCompromiso(Date date) {
		petiFechaCompromiso = date;
	}

	/**
	 * @param date
	 */
	public void setPetiFechaIngreso(Date date) {
		petiFechaIngreso = date;
	}

	/**
	 * @param long1
	 */
	public void setPetiNumero(Long long1) {
		petiNumero = long1;
	}

	/**
	 * @param long1
	 */
	public void setPetiOOSS(Long long1) {
		petiOOSS = long1;
	}

	/**
	 * @param string
	 */
	public void setPetiRutVendedor(String string) {
		petiRutVendedor = string;
	}

	/**
	 * @param string
	 */
	public void setPetiUsuarioEmisor(String string) {
		petiUsuarioEmisor = string;
	}

	
	/**
	 * @param string
	 */
	public void setSegmDescripcion(String string) {
		segmDescripcion = string;
	}

	/**
	 * @param string
	 */
	public void setTicaId(String string) {
		ticaId = string;
	}

	/**
	 * @param string
	 */
	public void setUsuaNombre(String string) {
		usuaNombre = string;
	}

	/**
	 * @return
	 */
	public String getEspeNombre() {
		return espeNombre;
	}

	/**
	 * @param string
	 */
	public void setEspeNombre(String string) {
		espeNombre = string;
	}

	/**
	 * @return
	 */
	public Double getLineNumero() {
		return lineNumero;
	}

	/**
	 * @param double1
	 */
	public void setLineNumero(Double double1) {
		lineNumero = double1;
	}

	/**
	 * @return
	 */
	public String getPuveCodigo() {
		return puveCodigo;
	}

	/**
	 * @param string
	 */
	public void setPuveCodigo(String string) {
		puveCodigo = string;
	}

	/**
	 * @return
	 */
	public String getSegmentoBandeja() {
		return segmentoBandeja;
	}

	/**
	 * @param string
	 */
	public void setSegmentoBandeja(String string) {
		segmentoBandeja = string;
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
	 * @return
	 */
	public Long getIdTipoAgendamiento() {
		return idTipoAgendamiento;
	}

	/**
	 * @return
	 */
	public String getNombreRango() {
		return nombreRango;
	}

	/**
	 * @return
	 */
	public String getNombreTipoAgendamiento() {
		return nombreTipoAgendamiento;
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
	 * @param long1
	 */
	public void setIdTipoAgendamiento(Long long1) {
		idTipoAgendamiento = long1;
	}

	/**
	 * @param string
	 */
	public void setNombreRango(String string) {
		nombreRango = string;
	}

	/**
	 * @param string
	 */
	public void setNombreTipoAgendamiento(String string) {
		nombreTipoAgendamiento = string;
	}

}
