package com.telefonica_chile.bandeja.datos.bandeja;

import java.io.Serializable;

public class ActividadDTO implements Serializable{
	private Long id;
	private Long idRol;
	private String codigo;
	private String descripcion;
	private AplicacionDTO aplicacion;
	private RolDTO rol;
	private int ordenTorreControl;
	private String nombreReversa;
	private boolean manual;
	private double ola;
	private String nombreFlujo;
	
	public String getCodigo() {
		return codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public Long getId() {
		return id;
	}

	public void setCodigo(String string) {
		codigo = string;
	}

	public void setDescripcion(String string) {
		descripcion = string;
	}

	public void setId(Long long1) {
		id = long1;
	}
	/**
	 * @return
	 */
	public AplicacionDTO getAplicacion() {
		return aplicacion;
	}

	/**
	 * @return
	 */
	public boolean isManual() {
		return manual;
	}

	/**
	 * @return
	 */
	public String getNombreFlujo() {
		return nombreFlujo;
	}

	/**
	 * @return
	 */
	public String getNombreReversa() {
		return nombreReversa;
	}

	/**
	 * @return
	 */
	public double getOla() {
		return ola;
	}

	/**
	 * @return
	 */
	public int getOrdenTorreControl() {
		return ordenTorreControl;
	}

	/**
	 * @return
	 */
	public RolDTO getRol() {
		return rol;
	}

	/**
	 * @param aplicacionDTO
	 */
	public void setAplicacion(AplicacionDTO aplicacionDTO) {
		aplicacion = aplicacionDTO;
	}

	/**
	 * @param b
	 */
	public void setManual(boolean b) {
		manual = b;
	}

	/**
	 * @param string
	 */
	public void setNombreFlujo(String string) {
		nombreFlujo = string;
	}

	/**
	 * @param string
	 */
	public void setNombreReversa(String string) {
		nombreReversa = string;
	}

	/**
	 * @param d
	 */
	public void setOla(double d) {
		ola = d;
	}

	/**
	 * @param i
	 */
	public void setOrdenTorreControl(int i) {
		ordenTorreControl = i;
	}

	/**
	 * @param rolDTO
	 */
	public void setRol(RolDTO rolDTO) {
		rol = rolDTO;
	}

	/**
	 * @return
	 */
	public Long getIdRol() {
		return idRol;
	}

	/**
	 * @param long1
	 */
	public void setIdRol(Long long1) {
		idRol = long1;
	}

}
