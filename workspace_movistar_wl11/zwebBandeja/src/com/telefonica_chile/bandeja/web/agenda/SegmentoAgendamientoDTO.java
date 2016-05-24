/*
 * Created on Mar 3, 2005
 * 
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.telefonica_chile.bandeja.web.agenda;



public class SegmentoAgendamientoDTO {
	
	Long idSegmento = null;
	String nombreSegmento = null;

	Integer idGrupoSegmento = null;
	String nombreGrupoSegmento = null;
	
	Integer porcentaje = null;
	Integer porcentajeMinimo = null;
	
	boolean segmentoMayor = false;

	public SegmentoAgendamientoDTO() {
	}


	/**
	 * @return
	 */
	public Integer getIdGrupoSegmento() {
		return idGrupoSegmento;
	}

	/**
	 * @return
	 */
	public Long getIdSegmento() {
		return idSegmento;
	}

	/**
	 * @return
	 */
	public String getNombreGrupoSegmento() {
		return nombreGrupoSegmento;
	}

	/**
	 * @return
	 */
	public String getNombreSegmento() {
		return nombreSegmento;
	}

	/**
	 * @return
	 */
	public Integer getPorcentaje() {
		return porcentaje;
	}

	/**
	 * @param integer
	 */
	public void setIdGrupoSegmento(Integer integer1) {
		idGrupoSegmento = integer1;
	}

	/**
	 * @param integer
	 */
	public void setIdSegmento(Long long1) {
		idSegmento = long1;
	}

	/**
	 * @param string
	 */
	public void setNombreGrupoSegmento(String string) {
		nombreGrupoSegmento = string;
	}

	/**
	 * @param string
	 */
	public void setNombreSegmento(String string) {
		nombreSegmento = string;
	}

	/**
	 * @param integer
	 */
	public void setPorcentaje(Integer integer) {
		porcentaje = integer;
	}

	/**
	 * @return
	 */
	public Integer getPorcentajeMinimo() {
		return porcentajeMinimo;
	}

	/**
	 * @param integer
	 */
	public void setPorcentajeMinimo(Integer integer) {
		porcentajeMinimo = integer;
	}

	/**
	 * @return
	 */
	public boolean isSegmentoMayor() {
		return segmentoMayor;
	}

	/**
	 * @param b
	 */
	public void setSegmentoMayor(boolean b) {
		segmentoMayor = b;
	}

}
