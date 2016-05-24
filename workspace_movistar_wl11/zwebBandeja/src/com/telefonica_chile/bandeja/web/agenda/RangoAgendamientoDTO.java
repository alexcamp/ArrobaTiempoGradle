/*
 * Created on Mar 3, 2005
 * 
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.telefonica_chile.bandeja.web.agenda;

import java.util.ArrayList;
import java.util.HashMap;



public class RangoAgendamientoDTO {
	
	Integer idRango = null;
	String descripcionRango = null;

	Integer porcentaje = null;
	Integer cantidadOcupada = null;
	
	Integer cantidadOcupadaSegmento = null;
	
	Integer idPadre = null;
	Integer porcentajePadre = null;
	
	ArrayList listaHijos = null;
	ArrayList listaHermanos = null;

	Byte habilitado = null;
	
	String horaDesde = null;
	String horaHasta = null;
	String codigoRango = null;
	
	HashMap ocupadoSegmento = null;

	public RangoAgendamientoDTO() {
	}

	/**
	 * @return
	 */
	public Integer getCantidadOcupada() {
		return cantidadOcupada;
	}

	/**
	 * @return
	 */
	public String getDescripcionRango() {
		return descripcionRango;
	}

	/**
	 * @return
	 */
	public Integer getIdRango() {
		return idRango;
	}

	/**
	 * @return
	 */
	public Integer getPorcentaje() {
		return porcentaje;
	}

	/**
	 * @param integer
	 */
	public void setCantidadOcupada(Integer integer) {
		cantidadOcupada = integer;
	}

	/**
	 * @param string
	 */
	public void setDescripcionRango(String string) {
		descripcionRango = string;
	}

	/**
	 * @param integer
	 */
	public void setIdRango(Integer integer) {
		idRango = integer;
	}

	/**
	 * @param integer
	 */
	public void setPorcentaje(Integer integer) {
		porcentaje = integer;
	}

	/**
	 * @return
	 */
	public Byte getHabilitado() {
		return habilitado;
	}

	/**
	 * @param byte1
	 */
	public void setHabilitado(Byte byte1) {
		habilitado = byte1;
	}

	/**
	 * @return
	 */
	public String getCodigoRango() {
		return codigoRango;
	}

	/**
	 * @return
	 */
	public String getHoraDesde() {
		return horaDesde;
	}

	/**
	 * @return
	 */
	public String getHoraHasta() {
		return horaHasta;
	}

	/**
	 * @param string
	 */
	public void setCodigoRango(String string) {
		codigoRango = string;
	}

	/**
	 * @param string
	 */
	public void setHoraDesde(String string) {
		horaDesde = string;
	}

	/**
	 * @param string
	 */
	public void setHoraHasta(String string) {
		horaHasta = string;
	}

	/**
	 * @return
	 */
	public Integer getCantidadOcupadaSegmento() {
		return cantidadOcupadaSegmento;
	}

	/**
	 * @param integer
	 */
	public void setCantidadOcupadaSegmento(Integer integer) {
		cantidadOcupadaSegmento = integer;
	}

	/**
	 * @return
	 */
	public HashMap getOcupadoSegmento() {
		return ocupadoSegmento;
	}

	/**
	 * @param map
	 */
	public void setOcupadoSegmento(HashMap map) {
		ocupadoSegmento = map;
	}

	/**
	 * @return
	 */
	public Integer getIdPadre() {
		return idPadre;
	}

	/**
	 * @param integer
	 */
	public void setIdPadre(Integer integer) {
		idPadre = integer;
	}

	/**
	 * @return
	 */
	public ArrayList getListaHijos() {
		return listaHijos;
	}

	/**
	 * @param list
	 */
	public void setListaHijos(ArrayList list) {
		listaHijos = list;
	}

	/**
	 * @return
	 */
	public ArrayList getListaHermanos() {
		return listaHermanos;
	}

	/**
	 * @param list
	 */
	public void setListaHermanos(ArrayList list) {
		listaHermanos = list;
	}

	/**
	 * @return
	 */
	public Integer getPorcentajePadre() {
		return porcentajePadre;
	}

	/**
	 * @param integer
	 */
	public void setPorcentajePadre(Integer integer) {
		porcentajePadre = integer;
	}

}
