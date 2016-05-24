package com.telefonica_chile.bandeja.dto;

import java.io.Serializable;
import java.util.TimeZone;

public class BIntegradaDTO implements Serializable{
	
	private Long biId = new Long(0);
	private Long apId = new Long(0);
	private Long usuaId = new Long(0);
	private String usuaLogin = "";
	private String usuaNombre = "";
	private Long biNroPeticion = new Long(0);
	private String biCodAgencia = "";
	private String biFechaCompromiso = "";	
	private String biClienteNombre = "";
	private String biClienteApellidos = "";
	private String biClienteServicio = "";
	private String biClienteArea = "";
	private String biClienteRut = "";
	private String biClienteRutDv = "";
	private String biSegmentoCliente = "";
	private String biFamiliaPs = "";
	private String biUrlDetalle = "";
	private String biUrlPuntaje = "";
	private String biFechaInicio;
	private String biFechaApertura;
	private String biFechaAsignacion = "";
	private String biPrioridad = "";	
	private String biSemaforoActividad = "";
	private String biSemaforoCompromiso = "";		
	private String rolId = "";
	private String rolCodigo = "";
	private String rolNombre = "";
	private String actividadId = "";
	private String actividadCodigo = "";
	private String actividadDescripcion = "";
	private String aplicacionNombre = "";
	private String aplicacionCodigo = "";
	
	private TimeZone tz = TimeZone.getTimeZone("America/Santiago");
	
	
	/**
	 * @return
	 */
	public String getActividadCodigo() {
		return actividadCodigo;
	}

	/**
	 * @return
	 */
	public String getActividadDescripcion() {
		return actividadDescripcion;
	}

	/**
	 * @return
	 */
	public String getActividadId() {
		return actividadId;
	}

	/**
	 * @return
	 */
	public Long getApId() {
		return apId;
	}

	/**
	 * @return
	 */
	public String getAplicacionCodigo() {
		return aplicacionCodigo;
	}

	/**
	 * @return
	 */
	public String getAplicacionNombre() {
		return aplicacionNombre;
	}

	/**
	 * @return
	 */
	public String getBiClienteApellidos() {
		return biClienteApellidos;
	}

	/**
	 * @return
	 */
	public String getBiClienteArea() {
		return biClienteArea;
	}

	/**
	 * @return
	 */
	public String getBiClienteNombre() {
		return biClienteNombre;
	}

	/**
	 * @return
	 */
	public String getBiClienteRut() {
		return biClienteRut;
	}

	/**
	 * @return
	 */
	public String getBiClienteRutDv() {
		return biClienteRutDv;
	}

	/**
	 * @return
	 */
	public String getBiClienteServicio() {
		return biClienteServicio;
	}

	/**
	 * @return
	 */
	public String getBiCodAgencia() {
		return biCodAgencia;
	}

	/**
	 * @return
	 */
	public String getBiFamiliaPs() {
		return biFamiliaPs;
	}

	/**
	 * @return
	 */
	public String getBiFechaApertura() {
		return biFechaApertura;
	}

	/**
	 * @return
	 */
	public String getBiFechaAsignacion() {
		return biFechaAsignacion;
	}

	/**
	 * @return
	 */
	public String getBiFechaCompromiso() {
		return biFechaCompromiso;
	}

	/**
	 * @return
	 */
	public String getBiFechaInicio() {
		return biFechaInicio;
	}

	/**
	 * @return
	 */
	public Long getBiId() {
		return biId;
	}

	/**
	 * @return
	 */
	public Long getBiNroPeticion() {
		return biNroPeticion;
	}

	/**
	 * @return
	 */
	public String getBiPrioridad() {
		return biPrioridad;
	}

	/**
	 * @return
	 */
	public String getBiSegmentoCliente() {
		return biSegmentoCliente;
	}

	/**
	 * @return
	 */
	public String getBiSemaforoActividad() {
		return biSemaforoActividad;
	}

	/**
	 * @return
	 */
	public String getBiSemaforoCompromiso() {
		return biSemaforoCompromiso;
	}

	/**
	 * @return
	 */
	public String getBiUrlDetalle() {
		return biUrlDetalle;
	}

	/**
	 * @return
	 */
	public String getBiUrlPuntaje() {
		return biUrlPuntaje;
	}

	/**
	 * @return
	 */
	public String getRolCodigo() {
		return rolCodigo;
	}

	/**
	 * @return
	 */
	public String getRolId() {
		return rolId;
	}

	/**
	 * @return
	 */
	public String getRolNombre() {
		return rolNombre;
	}

	/**
	 * @return
	 */
	public TimeZone getTz() {
		return tz;
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
	 * @param string
	 */
	public void setActividadCodigo(String string) {
		actividadCodigo = string;
	}

	/**
	 * @param string
	 */
	public void setActividadDescripcion(String string) {
		actividadDescripcion = string;
	}

	/**
	 * @param string
	 */
	public void setActividadId(String string) {
		actividadId = string;
	}

	/**
	 * @param long1
	 */
	public void setApId(Long long1) {
		apId = long1;
	}

	/**
	 * @param string
	 */
	public void setAplicacionCodigo(String string) {
		aplicacionCodigo = string;
	}

	/**
	 * @param string
	 */
	public void setAplicacionNombre(String string) {
		aplicacionNombre = string;
	}

	/**
	 * @param string
	 */
	public void setBiClienteApellidos(String string) {
		biClienteApellidos = string;
	}

	/**
	 * @param string
	 */
	public void setBiClienteArea(String string) {
		biClienteArea = string;
	}

	/**
	 * @param string
	 */
	public void setBiClienteNombre(String string) {
		biClienteNombre = string;
	}

	/**
	 * @param string
	 */
	public void setBiClienteRut(String string) {
		biClienteRut = string;
	}

	/**
	 * @param string
	 */
	public void setBiClienteRutDv(String string) {
		biClienteRutDv = string;
	}

	/**
	 * @param string
	 */
	public void setBiClienteServicio(String string) {
		biClienteServicio = string;
	}

	/**
	 * @param string
	 */
	public void setBiCodAgencia(String string) {
		biCodAgencia = string;
	}

	/**
	 * @param string
	 */
	public void setBiFamiliaPs(String string) {
		biFamiliaPs = string;
	}

	/**
	 * @param string
	 */
	public void setBiFechaApertura(String string) {
		biFechaApertura = string;
	}

	/**
	 * @param string
	 */
	public void setBiFechaAsignacion(String string) {
		biFechaAsignacion = string;
	}

	/**
	 * @param string
	 */
	public void setBiFechaCompromiso(String string) {
		biFechaCompromiso = string;
	}

	/**
	 * @param string
	 */
	public void setBiFechaInicio(String string) {
		biFechaInicio = string;
	}

	/**
	 * @param long1
	 */
	public void setBiId(Long long1) {
		biId = long1;
	}

	/**
	 * @param long1
	 */
	public void setBiNroPeticion(Long long1) {
		biNroPeticion = long1;
	}

	/**
	 * @param string
	 */
	public void setBiPrioridad(String string) {
		biPrioridad = string;
	}

	/**
	 * @param string
	 */
	public void setBiSegmentoCliente(String string) {
		biSegmentoCliente = string;
	}

	/**
	 * @param string
	 */
	public void setBiSemaforoActividad(String string) {
		biSemaforoActividad = string;
	}

	/**
	 * @param string
	 */
	public void setBiSemaforoCompromiso(String string) {
		biSemaforoCompromiso = string;
	}

	/**
	 * @param string
	 */
	public void setBiUrlDetalle(String string) {
		biUrlDetalle = string;
	}

	/**
	 * @param string
	 */
	public void setBiUrlPuntaje(String string) {
		biUrlPuntaje = string;
	}

	/**
	 * @param string
	 */
	public void setRolCodigo(String string) {
		rolCodigo = string;
	}

	/**
	 * @param string
	 */
	public void setRolId(String string) {
		rolId = string;
	}

	/**
	 * @param string
	 */
	public void setRolNombre(String string) {
		rolNombre = string;
	}

	/**
	 * @param zone
	 */
	public void setTz(TimeZone zone) {
		tz = zone;
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
	public void setUsuaLogin(String string) {
		usuaLogin = string;
	}

	/**
	 * @param string
	 */
	public void setUsuaNombre(String string) {
		usuaNombre = string;
	}

}
