package com.telefonica_chile.bandeja.dto;

public class MatrizTecnicaDTO
{

	private String codTecnico;
	private Integer idRango;

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
