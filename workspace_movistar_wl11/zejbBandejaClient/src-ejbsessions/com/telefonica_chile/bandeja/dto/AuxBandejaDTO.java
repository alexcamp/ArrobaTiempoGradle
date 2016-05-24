package com.telefonica_chile.bandeja.dto;

public class AuxBandejaDTO
{
	private String template;
	private Long folio;
	private String actividad;
	private String instanciaActividad;
	private String familiaPs;
	private Integer estadoPet;
	/**
	 * @return
	 */
	
	public AuxBandejaDTO(Long folio)
	{
		this.folio=folio;
	}
	public String getActividad() {
		return actividad;
	}

	/**
	 * @return
	 */
	public Integer getEstadoPet() {
		return estadoPet;
	}

	/**
	 * @return
	 */
	public String getFamiliaPs() {
		return familiaPs;
	}

	/**
	 * @return
	 */
	public Long getFolio() {
		return folio;
	}

	/**
	 * @return
	 */
	public String getInstanciaActividad() {
		return instanciaActividad;
	}

	/**
	 * @return
	 */
	public String getTemplate() {
		return template;
	}

	/**
	 * @param string
	 */
	public void setActividad(String string) {
		actividad = string;
	}

	/**
	 * @param integer
	 */
	public void setEstadoPet(Integer integer) {
		estadoPet = integer;
	}

	/**
	 * @param string
	 */
	public void setFamiliaPs(String string) {
		familiaPs = string;
	}

	/**
	 * @param long1
	 */
	public void setFolio(Long long1) {
		folio = long1;
	}

	/**
	 * @param string
	 */
	public void setInstanciaActividad(String string) {
		instanciaActividad = string;
	}

	/**
	 * @param string
	 */
	public void setTemplate(String string) {
		template = string;
	}

}
