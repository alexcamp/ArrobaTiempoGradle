/*
 * Created on Feb 23, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.telefonica_chile.comun.peticion_tipo_trabajo.dto;

import java.io.Serializable;

/**
 * @author ptoliva
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class PeticionTipoTrabajoDTO implements Serializable {
	
	public PeticionTipoTrabajoDTO() {
			super();
	}
	
	private String idTrabajo ="";
	private String codigo ="";
	private String nombre ="";



	/**
	 * @return
	 */
	public String getCodigo() {
		return codigo;
	}

	
	/**
	 * @return
	 */
	public String getIdTrabajo() {
		return idTrabajo;
	}

	/**
	 * @return
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param string
	 */
	public void setCodigo(String string) {
		codigo = string;
	}

	/**
	 * @param string
	 */
	public void setIdTrabajo(String string) {
		idTrabajo = string;
	}

	/**
	 * @param string
	 */
	public void setNombre(String string) {
		nombre = string;
	}

}
