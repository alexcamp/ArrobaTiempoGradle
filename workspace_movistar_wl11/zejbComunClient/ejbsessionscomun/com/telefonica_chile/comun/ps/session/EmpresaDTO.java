/*
 * Created on Mar 12, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.telefonica_chile.comun.ps.session;

/**
 * @author defuenz
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class EmpresaDTO {

	/**
	 * 
	 */
	public EmpresaDTO() {
		super();
	}

	private Long id;
	private String cod;
	private String razonSocial;
	private String rut;
	private String nombreContacto;
	private String fonoContacto;
	private String faxContacto;
	
	/**
	 * @return
	 */
	public String getCod() {
		return cod;
	}

	/**
	 * @return
	 */
	public String getFaxContacto() {
		return faxContacto;
	}

	/**
	 * @return
	 */
	public String getFonoContacto() {
		return fonoContacto;
	}

	/**
	 * @return
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @return
	 */
	public String getNombreContacto() {
		return nombreContacto;
	}

	/**
	 * @return
	 */
	public String getRazonSocial() {
		return razonSocial;
	}

	/**
	 * @return
	 */
	public String getRut() {
		return rut;
	}

	/**
	 * @param string
	 */
	public void setCod(String string) {
		cod = string;
	}

	/**
	 * @param string
	 */
	public void setFaxContacto(String string) {
		faxContacto = string;
	}

	/**
	 * @param string
	 */
	public void setFonoContacto(String string) {
		fonoContacto = string;
	}

	/**
	 * @param long1
	 */
	public void setId(Long long1) {
		id = long1;
	}

	/**
	 * @param string
	 */
	public void setNombreContacto(String string) {
		nombreContacto = string;
	}

	/**
	 * @param string
	 */
	public void setRazonSocial(String string) {
		razonSocial = string;
	}

	/**
	 * @param string
	 */
	public void setRut(String string) {
		rut = string;
	}

}
