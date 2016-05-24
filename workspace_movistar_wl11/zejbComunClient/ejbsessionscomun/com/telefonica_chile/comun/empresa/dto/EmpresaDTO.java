package com.telefonica_chile.comun.empresa.dto;

import java.io.Serializable;

/**
 * @author ptoliva
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class EmpresaDTO implements Serializable{
	
	private Long emprID;
	private String emprCodigo;
	private String emprRazonSocial;
	private String emprRut;
	private String emprNombreContacto;
	private String emprFonoContacto;
	private String emprFaxContacto;

	/**
	 * @return
	 */
	public String getEmprCodigo() {
		return emprCodigo;
	}

	/**
	 * @return
	 */
	public String getEmprFaxContacto() {
		return emprFaxContacto;
	}

	/**
	 * @return
	 */
	public String getEmprFonoContacto() {
		return emprFonoContacto;
	}

	/**
	 * @return
	 */
	public Long getEmprID() {
		return emprID;
	}

	/**
	 * @return
	 */
	public String getEmprNombreContacto() {
		return emprNombreContacto;
	}

	/**
	 * @return
	 */
	public String getEmprRazonSocial() {
		return emprRazonSocial;
	}

	/**
	 * @return
	 */
	public String getEmprRut() {
		return emprRut;
	}

	/**
	 * @param string
	 */
	public void setEmprCodigo(String string) {
		emprCodigo = string;
	}

	/**
	 * @param string
	 */
	public void setEmprFaxContacto(String string) {
		emprFaxContacto = string;
	}

	/**
	 * @param string
	 */
	public void setEmprFonoContacto(String string) {
		emprFonoContacto = string;
	}

	/**
	 * @param long1
	 */
	public void setEmprID(Long long1) {
		emprID = long1;
	}

	/**
	 * @param string
	 */
	public void setEmprNombreContacto(String string) {
		emprNombreContacto = string;
	}

	/**
	 * @param string
	 */
	public void setEmprRazonSocial(String string) {
		emprRazonSocial = string;
	}

	/**
	 * @param string
	 */
	public void setEmprRut(String string) {
		emprRut = string;
	}

}
