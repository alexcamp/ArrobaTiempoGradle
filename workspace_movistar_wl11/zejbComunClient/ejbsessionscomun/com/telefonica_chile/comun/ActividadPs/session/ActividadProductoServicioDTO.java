/*
 * Created on Mar 14, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.telefonica_chile.comun.ActividadPs.session;

import java.io.Serializable;

/**
 * @author defuenz
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ActividadProductoServicioDTO implements Serializable{

	/**
	 * 
	 */
	public ActividadProductoServicioDTO() {
		super();
	}
	
	private Long idPs;
	private Long idActividad;
	private Long idKey;
	private String nombreActividad;
	private String nombreCierreActividad;
	private String tipoOC;
	private Long idEmpresa;

	/**
	 * @return
	 */
	public Long getIdActividad() {
		return idActividad;
	}

	/**
	 * @return
	 */
	public Long getIdKey() {
		return idKey;
	}

	/**
	 * @return
	 */
	public Long getIdPs() {
		return idPs;
	}

	/**
	 * @param long1
	 */
	public void setIdActividad(Long long1) {
		idActividad = long1;
	}

	/**
	 * @param long1
	 */
	public void setIdKey(Long long1) {
		idKey = long1;
	}

	/**
	 * @param long1
	 */
	public void setIdPs(Long long1) {
		idPs = long1;
	}

	/**
	 * @return
	 */
	public String getNombreActividad() {
		return nombreActividad;
	}

	/**
	 * @param string
	 */
	public void setNombreActividad(String string) {
		nombreActividad = string;
	}

	/**
	 * @return
	 */
	public String getNombreCierreActividad() {
		return nombreCierreActividad;
	}

	/**
	 * @param string
	 */
	public void setNombreCierreActividad(String string) {
		nombreCierreActividad = string;
	}

	/**
	 * @return
	 */
	public String getTipoOC() {
		return tipoOC;
	}

	/**
	 * @param string
	 */
	public void setTipoOC(String string) {
		tipoOC = string;
	}

	/**
	 * @return
	 */
	public Long getIdEmpresa() {
		return idEmpresa;
	}

	/**
	 * @param long1
	 */
	public void setIdEmpresa(Long long1) {
		idEmpresa = long1;
	}

}
