package com.telefonica_chile.comun.segmento.dto;


public class SubSegmentoDto
{
	private Long subSegmentoId;
	private Long segmentoId;
	private String descripcion;

	/**
	 * @return
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @return
	 */
	public Long getSegmentoId() {
		return segmentoId;
	}

	/**
	 * @return
	 */
	public Long getSubSegmentoId() {
		return subSegmentoId;
	}

	/**
	 * @param string
	 */
	public void setDescripcion(String string) {
		descripcion = string;
	}

	/**
	 * @param long1
	 */
	public void setSegmentoId(Long long1) {
		segmentoId = long1;
	}

	/**
	 * @param long1
	 */
	public void setSubSegmentoId(Long long1) {
		subSegmentoId = long1;
	}

}
