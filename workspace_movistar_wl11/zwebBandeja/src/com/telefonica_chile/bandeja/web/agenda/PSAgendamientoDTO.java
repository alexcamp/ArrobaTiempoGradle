/*
 * Created on Mar 3, 2005
 * 
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.telefonica_chile.bandeja.web.agenda;



public class PSAgendamientoDTO {
	
	Long idPs = null;
	String codigoPS = null;
	String nombrePS = null;

	String opComercial = null;
	
	Long idFamiliaPS = null; // Este corresponde al Padre (Para Agendamiento)
	Long idFamiliaOld = null; // Este corresponde al id de Familia. (Normal)
	String codFamiliaPS = null;
	String nombreFamiliaPS = null;
	
	boolean psTerreno = false;


	public PSAgendamientoDTO() {
	}

	/**
	 * @return
	 */
	public Long getIdFamiliaPS() {
		return idFamiliaPS;
	}

	/**
	 * @return
	 */
	public Long getIdPs() {
		return idPs;
	}

	/**
	 * @return
	 */
	public String getNombreFamiliaPS() {
		return nombreFamiliaPS;
	}

	/**
	 * @return
	 */
	public String getNombrePS() {
		return nombrePS;
	}

	/**
	 * @return
	 */
	public String getOpComercial() {
		return opComercial;
	}

	/**
	 * @param integer
	 */
	public void setIdFamiliaPS(Long l) {
		idFamiliaPS = l;
	}

	/**
	 * @param integer
	 */
	public void setIdPs(Long l) {
		idPs = l;
	}

	/**
	 * @param string
	 */
	public void setNombreFamiliaPS(String string) {
		nombreFamiliaPS = string;
	}

	/**
	 * @param string
	 */
	public void setNombrePS(String string) {
		nombrePS = string;
	}

	/**
	 * @param string
	 */
	public void setOpComercial(String string) {
		opComercial = string;
	}

	/**
	 * @return
	 */
	public String getCodigoPS() {
		return codigoPS;
	}

	/**
	 * @param string
	 */
	public void setCodigoPS(String string) {
		codigoPS = string;
	}

	/**
	 * @return
	 */
	public String getCodFamiliaPS() {
		return codFamiliaPS;
	}

	/**
	 * @param string
	 */
	public void setCodFamiliaPS(String string) {
		codFamiliaPS = string;
	}

	/**
	 * @return
	 */
	public boolean isPsTerreno() {
		return psTerreno;
	}

	/**
	 * @param b
	 */
	public void setPsTerreno(boolean b) {
		psTerreno = b;
	}

	/**
	 * @return
	 */
	public Long getIdFamiliaOld() {
		return idFamiliaOld;
	}

	/**
	 * @param long1
	 */
	public void setIdFamiliaOld(Long long1) {
		idFamiliaOld = long1;
	}

}
