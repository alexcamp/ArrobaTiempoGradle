/*
 * Created on Mar 12, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.telefonica_chile.vpistbba.bajadaarchivostbba.dto;

import java.util.ArrayList;
import java.util.Date;

/**
 * @author ptoliva
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class PeticionArchivoDTO {
	
	private Long   idPeticion;
	private Long   numOOSS;

	private Long   idLineaPrincipal;
	private Long   idLineaAntigua;

	private Date   fechaEmision;
	private String reversa;
	private String idTica;
	
	private Long idSegmento;
	private String nombreSegmento;
	private String nombreGrupoSegmento;

	private String  observacion;
	private String  nombrePuntoVenta;
	private String  usuarioEmisor;

	private Long idActividad;
	private String  codActividad;
	private String  nombreActividad;
	private String rol;

	private LineaArchivoDTO lineaPrincipal;
	private LineaArchivoDTO lineaAntigua;

	private ArrayList listaPI;
	private ArrayList listaPS;

	private String claveCandado;
	private String numeroTransferencia;
	private String cantidadLineas;
	private String numeroNuevo;

	public PeticionArchivoDTO() {
	}

	/**
	 * @return
	 */
	public String getClaveCandado() {
		return claveCandado;
	}

	/**
	 * @return
	 */
	public String getCodActividad() {
		return codActividad;
	}

	/**
	 * @return
	 */
	public Date getFechaEmision() {
		return fechaEmision;
	}

	/**
	 * @return
	 */
	public Long getIdPeticion() {
		return idPeticion;
	}

	/**
	 * @return
	 */
	public String getIdTica() {
		return idTica;
	}

	/**
	 * @return
	 */
	public ArrayList getListaPI() {
		return listaPI;
	}

	/**
	 * @return
	 */
	public ArrayList getListaPS() {
		return listaPS;
	}

	/**
	 * @return
	 */
	public String getNombreActividad() {
		return nombreActividad;
	}

	/**
	 * @return
	 */
	public String getNombreGrupoSegmento() {
		return nombreGrupoSegmento;
	}

	/**
	 * @return
	 */
	public String getNombrePuntoVenta() {
		return nombrePuntoVenta;
	}

	/**
	 * @return
	 */
	public String getNombreSegmento() {
		return nombreSegmento;
	}

	/**
	 * @return
	 */
	public Long getNumOOSS() {
		return numOOSS;
	}

	/**
	 * @return
	 */
	public String getObservacion() {
		return observacion;
	}

	/**
	 * @return
	 */
	public String getReversa() {
		return reversa;
	}

	/**
	 * @return
	 */
	public String getRol() {
		return rol;
	}

	/**
	 * @return
	 */
	public String getUsuarioEmisor() {
		return usuarioEmisor;
	}

	/**
	 * @param string
	 */
	public void setClaveCandado(String string) {
		claveCandado = string;
	}

	/**
	 * @param string
	 */
	public void setCodActividad(String string) {
		codActividad = string;
	}

	/**
	 * @param date
	 */
	public void setFechaEmision(Date date) {
		fechaEmision = date;
	}

	/**
	 * @param long1
	 */
	public void setIdPeticion(Long long1) {
		idPeticion = long1;
	}

	/**
	 * @param string
	 */
	public void setIdTica(String string) {
		idTica = string;
	}

	/**
	 * @param list
	 */
	public void setListaPI(ArrayList list) {
		listaPI = list;
	}

	/**
	 * @param list
	 */
	public void setListaPS(ArrayList list) {
		listaPS = list;
	}

	/**
	 * @param string
	 */
	public void setNombreActividad(String string) {
		nombreActividad = string;
	}

	/**
	 * @param string
	 */
	public void setNombreGrupoSegmento(String string) {
		nombreGrupoSegmento = string;
	}

	/**
	 * @param string
	 */
	public void setNombrePuntoVenta(String string) {
		nombrePuntoVenta = string;
	}

	/**
	 * @param string
	 */
	public void setNombreSegmento(String string) {
		nombreSegmento = string;
	}

	/**
	 * @param long1
	 */
	public void setNumOOSS(Long long1) {
		numOOSS = long1;
	}

	/**
	 * @param string
	 */
	public void setObservacion(String string) {
		observacion = string;
	}

	/**
	 * @param string
	 */
	public void setReversa(String string) {
		reversa = string;
	}

	/**
	 * @param string
	 */
	public void setRol(String string) {
		rol = string;
	}

	/**
	 * @param string
	 */
	public void setUsuarioEmisor(String string) {
		usuarioEmisor = string;
	}

	/**
	 * @return
	 */
	public LineaArchivoDTO getLineaAntigua() {
		return lineaAntigua;
	}

	/**
	 * @return
	 */
	public LineaArchivoDTO getLineaPrincipal() {
		return lineaPrincipal;
	}

	/**
	 * @param archivoDTO
	 */
	public void setLineaAntigua(LineaArchivoDTO archivoDTO) {
		lineaAntigua = archivoDTO;
	}

	/**
	 * @param archivoDTO
	 */
	public void setLineaPrincipal(LineaArchivoDTO archivoDTO) {
		lineaPrincipal = archivoDTO;
	}

	/**
	 * @return
	 */
	public Long getIdLineaAntigua() {
		return idLineaAntigua;
	}

	/**
	 * @return
	 */
	public Long getIdLineaPrincipal() {
		return idLineaPrincipal;
	}

	/**
	 * @param long1
	 */
	public void setIdLineaAntigua(Long long1) {
		idLineaAntigua = long1;
	}

	/**
	 * @param long1
	 */
	public void setIdLineaPrincipal(Long long1) {
		idLineaPrincipal = long1;
	}

	/**
	 * @return
	 */
	public Long getIdActividad() {
		return idActividad;
	}

	/**
	 * @param long1
	 */
	public void setIdActividad(Long long1) {
		idActividad = long1;
	}

	/**
	 * @return
	 */
	public Long getIdSegmento() {
		return idSegmento;
	}

	/**
	 * @param long1
	 */
	public void setIdSegmento(Long long1) {
		idSegmento = long1;
	}

	/**
	 * @return
	 */
	public String getCantidadLineas() {
		return cantidadLineas;
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
	public void setCantidadLineas(String string) {
		cantidadLineas = string;
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
	public String getNumeroNuevo() {
		return numeroNuevo;
	}

	/**
	 * @param string
	 */
	public void setNumeroNuevo(String string) {
		numeroNuevo = string;
	}

}
