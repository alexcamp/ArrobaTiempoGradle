package com.telefonica_chile.bandeja.dto;

import com.telefonica_chile.atiempo.utiles.Fecha;

public class UserCoDto implements Comparable
{
	private Long usuaId;
	private String usuaLogin;
	private String usuaNombre;
	private String usuaDireccion;
	private String usuaEmail;
	private String usuaTelefono;
	private String usuaRut;
	private String usuaCargo;
	private String usuaHabilitado;
	private String emprId;
	private String usuaPassword;
	private String usuaIdca;
	private Fecha usuaFechaAlta;
	private Long esusId;
	private Fecha esusUltimaActualizacion;
	private String usuaNombres;
	private String usuaApePaterno;
	private String usuaApeMaterno;
	private String usuaNumRut;
	private String usuaDvRut;
	private Integer nroPeticiones;

	/**
	 * @return
	 */
	public String getEmprId() {
		return emprId;
	}

	/**
	 * @return
	 */
	public Long getEsusId() {
		return esusId;
	}

	/**
	 * @return
	 */
	public Fecha getEsusUltimaActualizacion() {
		return esusUltimaActualizacion;
	}

	/**
	 * @return
	 */
	public String getUsuaApeMaterno() {
		return usuaApeMaterno;
	}

	/**
	 * @return
	 */
	public String getUsuaApePaterno() {
		return usuaApePaterno;
	}

	/**
	 * @return
	 */
	public String getUsuaCargo() {
		return usuaCargo;
	}

	/**
	 * @return
	 */
	public String getUsuaDireccion() {
		return usuaDireccion;
	}

	/**
	 * @return
	 */
	public String getUsuaDvRut() {
		return usuaDvRut;
	}

	/**
	 * @return
	 */
	public String getUsuaEmail() {
		return usuaEmail;
	}

	/**
	 * @return
	 */
	public Fecha getUsuaFechaAlta() {
		return usuaFechaAlta;
	}

	/**
	 * @return
	 */
	public String getUsuaHabilitado() {
		return usuaHabilitado;
	}

	/**
	 * @return
	 */
	public Long getUsuaId() {
		return usuaId;
	}

	/**
	 * @return
	 */
	public String getUsuaIdca() {
		return usuaIdca;
	}

	/**
	 * @return
	 */
	public String getUsuaLogin() {
		return usuaLogin;
	}

	/**
	 * @return
	 */
	public String getUsuaNombre() {
		return usuaNombre;
	}

	/**
	 * @return
	 */
	public String getUsuaNombres() {
		return usuaNombres;
	}

	/**
	 * @return
	 */
	public String getUsuaNumRut() {
		return usuaNumRut;
	}

	/**
	 * @return
	 */
	public String getUsuaPassword() {
		return usuaPassword;
	}

	/**
	 * @return
	 */
	public String getUsuaRut() {
		return usuaRut;
	}

	/**
	 * @return
	 */
	public String getUsuaTelefono() {
		return usuaTelefono;
	}

	/**
	 * @param string
	 */
	public void setEmprId(String string) {
		emprId = string;
	}

	/**
	 * @param long1
	 */
	public void setEsusId(Long long1) {
		esusId = long1;
	}

	/**
	 * @param fecha
	 */
	public void setEsusUltimaActualizacion(Fecha fecha) {
		esusUltimaActualizacion = fecha;
	}

	/**
	 * @param string
	 */
	public void setUsuaApeMaterno(String string) {
		usuaApeMaterno = string;
	}

	/**
	 * @param string
	 */
	public void setUsuaApePaterno(String string) {
		usuaApePaterno = string;
	}

	/**
	 * @param string
	 */
	public void setUsuaCargo(String string) {
		usuaCargo = string;
	}

	/**
	 * @param string
	 */
	public void setUsuaDireccion(String string) {
		usuaDireccion = string;
	}

	/**
	 * @param string
	 */
	public void setUsuaDvRut(String string) {
		usuaDvRut = string;
	}

	/**
	 * @param string
	 */
	public void setUsuaEmail(String string) {
		usuaEmail = string;
	}

	/**
	 * @param fecha
	 */
	public void setUsuaFechaAlta(Fecha fecha) {
		usuaFechaAlta = fecha;
	}

	/**
	 * @param string
	 */
	public void setUsuaHabilitado(String string) {
		usuaHabilitado = string;
	}

	/**
	 * @param long1
	 */
	public void setUsuaId(Long long1) {
		usuaId = long1;
	}

	/**
	 * @param string
	 */
	public void setUsuaIdca(String string) {
		usuaIdca = string;
	}

	/**
	 * @param string
	 */
	public void setUsuaLogin(String string) {
		usuaLogin = string;
	}

	/**
	 * @param string
	 */
	public void setUsuaNombre(String string) {
		usuaNombre = string;
	}

	/**
	 * @param string
	 */
	public void setUsuaNombres(String string) {
		usuaNombres = string;
	}

	/**
	 * @param string
	 */
	public void setUsuaNumRut(String string) {
		usuaNumRut = string;
	}

	/**
	 * @param string
	 */
	public void setUsuaPassword(String string) {
		usuaPassword = string;
	}

	/**
	 * @param string
	 */
	public void setUsuaRut(String string) {
		usuaRut = string;
	}

	/**
	 * @param string
	 */
	public void setUsuaTelefono(String string) {
		usuaTelefono = string;
	}

	/**
	 * @return
	 */
	public Integer getNroPeticiones() {
		return nroPeticiones;
	}

	/**
	 * @param integer
	 */
	public void setNroPeticiones(Integer integer) {
		nroPeticiones = integer;
	}

	public int compareTo(Object o)
	{
		UserCoDto otro=(UserCoDto) o;
		if(this.usuaLogin!=null && otro.getUsuaLogin()!=null)
			return usuaLogin.compareTo(otro.getUsuaLogin());
		return 0;
	}

}
