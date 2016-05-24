/*
 * Created on Feb 26, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.telefonica_chile.comun.rol.dto;

import java.io.Serializable;

/**
 * @author defuenz
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class RolDTO implements Serializable {
	
	Long rolId =null;
	int tipoRelacionId;
	Long ispId;
	String nombreRol;
	String sabana;
	String rolCod;
	/**
	 * 
	 */
	public RolDTO() {
		this.rolId =null;
		this.tipoRelacionId = 0;
		this.ispId = null;
		this.nombreRol = "";
		this.sabana = "";
		this.rolCod = "";
	}

	/**
	 * @return
	 */
	public Long getIspId() {
		return ispId;
	}

	/**
	 * @return
	 */
	public String getNombreRol() {
		return nombreRol;
	}

	/**
	 * @return
	 */
	public String getRolCod() {
		return rolCod;
	}

	/**
	 * @return
	 */
	public Long getRolId() {
		return rolId;
	}

	/**
	 * @return
	 */
	public String getSabana() {
		return sabana;
	}

	/**
	 * @return
	 */
	public int getTipoRelacionId() {
		return tipoRelacionId;
	}

	/**
	 * @param long1
	 */
	public void setIspId(Long long1) {
		ispId = long1;
	}

	/**
	 * @param string
	 */
	public void setNombreRol(String string) {
		nombreRol = string;
	}

	/**
	 * @param string
	 */
	public void setRolCod(String string) {
		rolCod = string;
	}

	/**
	 * @param long1
	 */
	public void setRolId(Long long1) {
		rolId = long1;
	}

	/**
	 * @param string
	 */
	public void setSabana(String string) {
		sabana = string;
	}

	/**
	 * @param i
	 */
	public void setTipoRelacionId(int i) {
		tipoRelacionId = i;
	}

}
