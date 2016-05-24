/*
 * Created on Feb 24, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.telefonica_chile.comun.usuario.dto;

import java.io.Serializable;
import java.util.Date;


/**
 * @author ptoliva
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class UsuarioDTO implements Serializable {	
	
	public UsuarioDTO() {
			super();
	}
	private Long idUsuario;
	private Long idTrabajo ;
	private String  usuario="";
	private String  login="";
	private String  nombre="";
	private String  direccion="";
	private String  email="";
	private String  telefono="";
	private String  rut="";
	private String  cargo="";
	private String  habilitado="";
	private Long    idEmpresa;
	private String  password="";
	private String  idca="";
	private Date    fechaAlta;

	/**
	 * @return
	 */
	public String getCargo() {
		return cargo;
	}

	/**
	 * @return
	 */
	public String getDireccion() {
		return direccion;
	}

	/**
	 * @return
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @return
	 */
	public Date getFechaAlta() {
		return fechaAlta;
	}

	/**
	 * @return
	 */
	public String getHabilitado() {
		return habilitado;
	}

	/**
	 * @return
	 */
	public String getIdca() {
		return idca;
	}

	/**
	 * @return
	 */
	public Long getIdEmpresa() {
		return idEmpresa;
	}

	/**
	 * @return
	 */
	public Long getIdTrabajo() {
		return idTrabajo;
	}

	/**
	 * @return
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * @return
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @return
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @return
	 */
	public String getRut() {
		return rut;
	}

	/**
	 * @return
	 */
	public String getTelefono() {
		return telefono;
	}

	/**
	 * @return
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * @param string
	 */
	public void setCargo(String string) {
		cargo = string;
	}

	/**
	 * @param string
	 */
	public void setDireccion(String string) {
		direccion = string;
	}

	/**
	 * @param string
	 */
	public void setEmail(String string) {
		email = string;
	}

	/**
	 * @param date
	 */
	public void setFechaAlta(Date date) {
		fechaAlta = date;
	}

	/**
	 * @param string
	 */
	public void setHabilitado(String string) {
		habilitado = string;
	}

	/**
	 * @param string
	 */
	public void setIdca(String string) {
		idca = string;
	}

	/**
	 * @param long1
	 */
	public void setIdEmpresa(Long long1) {
		idEmpresa = long1;
	}

	/**
	 * @param long1
	 */
	public void setIdTrabajo(Long long1) {
		idTrabajo = long1;
	}

	/**
	 * @param string
	 */
	public void setLogin(String string) {
		login = string;
	}

	/**
	 * @param string
	 */
	public void setNombre(String string) {
		nombre = string;
	}

	/**
	 * @param string
	 */
	public void setPassword(String string) {
		password = string;
	}

	/**
	 * @param string
	 */
	public void setRut(String string) {
		rut = string;
	}

	/**
	 * @param string
	 */
	public void setTelefono(String string) {
		telefono = string;
	}

	/**
	 * @param string
	 */
	public void setUsuario(String string) {
		usuario = string;
	}

	/**
	 * @return
	 */
	public Long getIdUsuario() {
		return idUsuario;
	}

	/**
	 * @param long1
	 */
	public void setIdUsuario(Long long1) {
		idUsuario = long1;
	}

}
