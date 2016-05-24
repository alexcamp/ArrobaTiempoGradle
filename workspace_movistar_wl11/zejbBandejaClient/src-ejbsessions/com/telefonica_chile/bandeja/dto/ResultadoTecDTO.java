package com.telefonica_chile.bandeja.dto;

public class ResultadoTecDTO {

	private String codTecnico;
	private Integer idRango;
	private int cantidad;	

	/**
	 * @return
	 */
	public int getCantidad()
	{
		return cantidad;
	}

	/**
	 * @return
	 */
	public String getCodTecnico() {
		return codTecnico;
	}

	/**
	 * @return
	 */
	public Integer getIdRango() {
		return idRango;
	}

	/**
	 * @param i
	 */
	public void setCantidad(int i) {
		cantidad = i;
	}

	/**
	 * @param string
	 */
	public void setCodTecnico(String string) {
		codTecnico = string;
	}

	/**
	 * @param integer
	 */
	public void setIdRango(Integer integer) {
		idRango = integer;
	}

}
