/*
 * Created on Mar 12, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.telefonica_chile.vpistbba.bajadaarchivostbba.dto;

import java.util.Date;
import java.util.List;

/**
 * @author ptoliva
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ArchivoDTO {
	
	public Long   peticionNumero;
	public Long   peticionOOSS;
	public Double peticionAreaLinea;
	public Double peticionNroLinea;
	public String peticionCentral;
	public String peticionCentralMadre;
	public String peticionTecnologia;
	public String peticionTerritorio;
	public Date   peticionFechaEmision ;
	public String peticionReverse;
	public String peticionTica;
	public String peticionSegmento;
	public String peticionNroEquipo;
	
	public List   peticionPS;
	public List   peticionOtrosPS;
	
	public Integer peticionNodo;
	public String  peticionObservacion;
	public String  peticionClaveCandado;
	public String  peticionPtoVenta;
	public String  peticionRolVenta;
	public String  opComercial;
	public String  preselector;
	public String  codigoActividad;
	public String  obsActividad;
	public String  numeroTransferencia;
	public String  nuevoNumero;
	public String  cantidadLineas;
	public List    listaNumeros;
	public String  obsMac;
	public Long    peticionNroAsociados;
	public String fonoLogico1;
	public String fonoLogico2;
	public String actividad;
	public String rol;
	public String nombreSegmento;
	public Double numeroNuevo;
	public String preselectorNuevo;
	public String peticionCentralNuevo;
	public String peticionTecnologiaNuevo;
	

	/**
	 * @return
	 */
	public Double getPeticionAreaLinea() {
		return peticionAreaLinea;
	}

	/**
	 * @return
	 */
	public String getPeticionCentral() {
		return peticionCentral;
	}

	/**
	 * @return
	 */
	public String getPeticionClaveCandado() {
		return peticionClaveCandado;
	}

	/**
	 * @return
	 */
	public Date getPeticionFechaEmision() {
		return peticionFechaEmision;
	}

	
/**
	 * @return
	 */
	public Double getPeticionNroLinea() {
		return peticionNroLinea;
	}

	/**
	 * @return
	 */
	public Long getPeticionNumero() {
		return peticionNumero;
	}

	/**
	 * @return
	 */
	public String getPeticionObservacion() {
		return peticionObservacion;
	}

	/**
	 * @return
	 */
	public Long getPeticionOOSS() {
		return peticionOOSS;
	}

	

	/**
	 * @return
	 */
	public String getPeticionPtoVenta() {
		return peticionPtoVenta;
	}

	/**
	 * @return
	 */
	public String getPeticionReverse() {
		return peticionReverse;
	}

	/**
	 * @return
	 */
	public String getPeticionRolVenta() {
		return peticionRolVenta;
	}

	/**
	 * @return
	 */
	public String getPeticionTecnologia() {
		return peticionTecnologia;
	}

	/**
	 * @return
	 */
	public String getPeticionTerritorio() {
		return peticionTerritorio;
	}

	/**
	 * @return
	 */
	public String getPeticionTica() {
		return peticionTica;
	}

	/**
	 * @param double1
	 */
	public void setPeticionAreaLinea(Double double1) {
		peticionAreaLinea = double1;
	}

	/**
	 * @param string
	 */
	public void setPeticionCentral(String string) {
		peticionCentral = string;
	}

	/**
	 * @param string
	 */
	public void setPeticionClaveCandado(String string) {
		peticionClaveCandado = string;
	}

	/**
	 * @param date
	 */
	public void setPeticionFechaEmision(Date date) {
		peticionFechaEmision = date;
	}


	/**
	 * @param double1
	 */
	public void setPeticionNroLinea(Double double1) {
		peticionNroLinea = double1;
	}

	/**
	 * @param long1
	 */
	public void setPeticionNumero(Long long1) {
		peticionNumero = long1;
	}

	/**
	 * @param string
	 */
	public void setPeticionObservacion(String string) {
		peticionObservacion = string;
	}

	/**
	 * @param long1
	 */
	public void setPeticionOOSS(Long long1) {
		peticionOOSS = long1;
	}

	/**
	 * @param string
	 */
	public void setPeticionPtoVenta(String string) {
		peticionPtoVenta = string;
	}

	/**
	 * @param string
	 */
	public void setPeticionReverse(String string) {
		peticionReverse = string;
	}

	/**
	 * @param string
	 */
	public void setPeticionRolVenta(String string) {
		peticionRolVenta = string;
	}

	/**
	 * @param string
	 */
	public void setPeticionTecnologia(String string) {
		peticionTecnologia = string;
	}

	/**
	 * @param string
	 */
	public void setPeticionTerritorio(String string) {
		peticionTerritorio = string;
	}

	/**
	 * @param string
	 */
	public void setPeticionTica(String string) {
		peticionTica = string;
	}

	
	/**
	 * @return
	 */
	public List getPeticionOtrosPS() {
		return peticionOtrosPS;
	}

	/**
	 * @return
	 */
	public List getPeticionPS() {
		return peticionPS;
	}

	
	/**
	 * @param list
	 */
	public void setPeticionOtrosPS(List list) {
		peticionOtrosPS = list;
	}

	/**
	 * @param list
	 */
	public void setPeticionPS(List list) {
		peticionPS = list;
	}

	
	/**
	 * @return
	 */
	public String getPeticionSegmento() {
		return peticionSegmento;
	}

	/**
	 * @param string
	 */
	public void setPeticionSegmento(String string) {
		peticionSegmento = string;
	}

	

	/**
	 * @return
	 */
	public String getOpComercial() {
		return opComercial;
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
	public String getPeticionNroEquipo() {
		return peticionNroEquipo;
	}

	/**
	 * @param string
	 */
	public void setPeticionNroEquipo(String string) {
		peticionNroEquipo = string;
	}

	/**
	 * @return
	 */
	public String getPreselector() {
		return preselector;
	}

	/**
	 * @param string
	 */
	public void setPreselector(String string) {
		preselector = string;
	}

	

	/**
	 * @return
	 */
	public String getObsActividad() {
		return obsActividad;
	}

	/**
	 * @param string
	 */
	public void setObsActividad(String string) {
		obsActividad = string;
	}

	
	/**
	 * @return
	 */
	public String getCodigoActividad() {
		return codigoActividad;
	}

	/**
	 * @param string
	 */
	public void setCodigoActividad(String string) {
		codigoActividad = string;
	}

	
	

	/**
	 * @return
	 */
	public String getNuevoNumero() {
		return nuevoNumero;
	}

	/**
	 * @return
	 */
	public String getNumeroTransferencia() {
		return numeroTransferencia;
	}

	
	/**
	 * @param string
	 */
	public void setNuevoNumero(String string) {
		nuevoNumero = string;
	}

	/**
	 * @param string
	 */
	public void setNumeroTransferencia(String string) {
		numeroTransferencia = string;
	}

	
	/**
	 * @return
	 */
	public List getListaNumeros() {
		return listaNumeros;
	}

	/**
	 * @param list
	 */
	public void setListaNumeros(List list) {
		listaNumeros = list;
	}

	/**
	 * @return
	 */
	public String getCantidadLineas() {
		return cantidadLineas;
	}

	/**
	 * @param string
	 */
	public void setCantidadLineas(String string) {
		cantidadLineas = string;
	}

	/**
	 * @return
	 */
	public String getObsMac() {
		return obsMac;
	}

	/**
	 * @param string
	 */
	public void setObsMac(String string) {
		obsMac = string;
	}

	
	/**
	 * @return
	 */
	public Integer getPeticionNodo() {
		return peticionNodo;
	}

	/**
	 * @param integer
	 */
	public void setPeticionNodo(Integer integer) {
		peticionNodo = integer;
	}

	
	/**
	 * @return
	 */
	public String getFonoLogico1() {
		return fonoLogico1;
	}

	/**
	 * @return
	 */
	public String getFonoLogico2() {
		return fonoLogico2;
	}

	/**
	 * @return
	 */
	public Long getPeticionNroAsociados() {
		return peticionNroAsociados;
	}

	/**
	 * @param string
	 */
	public void setFonoLogico1(String string) {
		fonoLogico1 = string;
	}

	/**
	 * @param string
	 */
	public void setFonoLogico2(String string) {
		fonoLogico2 = string;
	}

	/**
	 * @param long1
	 */
	public void setPeticionNroAsociados(Long long1) {
		peticionNroAsociados = long1;
	}

	/**
	 * @return
	 */
	public String getPeticionCentralMadre() {
		return peticionCentralMadre;
	}

	/**
	 * @param string
	 */
	public void setPeticionCentralMadre(String string) {
		peticionCentralMadre = string;
	}

	/**
	 * @return
	 */
	public String getActividad() {
		return actividad;
	}

	/**
	 * @param string
	 */
	public void setActividad(String string) {
		actividad = string;
	}

	/**
	 * @return
	 */
	public String getRol() {
		return rol;
	}

	/**
	 * @param string
	 */
	public void setRol(String string) {
		rol = string;
	}

	/**
	 * @return
	 */
	public String getNombreSegmento() {
		return nombreSegmento;
	}

	/**
	 * @param string
	 */
	public void setNombreSegmento(String string) {
		nombreSegmento = string;
	}


	/**
	 * @return
	 */
	public Double getNumeroNuevo() {
		return numeroNuevo;
	}

	/**
	 * @return
	 */
	public String getPeticionCentralNuevo() {
		return peticionCentralNuevo;
	}

	/**
	 * @return
	 */
	public String getPeticionTecnologiaNuevo() {
		return peticionTecnologiaNuevo;
	}

	/**
	 * @return
	 */
	public String getPreselectorNuevo() {
		return preselectorNuevo;
	}

	/**
	 * @param double1
	 */
	public void setNumeroNuevo(Double double1) {
		numeroNuevo = double1;
	}

	/**
	 * @param string
	 */
	public void setPeticionCentralNuevo(String string) {
		peticionCentralNuevo = string;
	}

	/**
	 * @param string
	 */
	public void setPeticionTecnologiaNuevo(String string) {
		peticionTecnologiaNuevo = string;
	}

	/**
	 * @param string
	 */
	public void setPreselectorNuevo(String string) {
		preselectorNuevo = string;
	}

}
