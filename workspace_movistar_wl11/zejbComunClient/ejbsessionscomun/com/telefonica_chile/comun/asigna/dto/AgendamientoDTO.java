/*
 * Created on Mar 3, 2005
 * 
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.telefonica_chile.comun.asigna.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import com.telefonica_chile.comun.ps.dto.ProductoDTO;


public class AgendamientoDTO implements Serializable {
	
	private Date fechaCompromiso;
	private Long idTipoAgenda;
	private Integer idRango;
	private String horaDesde;
	private String horaHasta;
	private String nombreTipoAgenda;
	private String nombreRango;


	private String idTica = "";
	private String codigoSegmento = "";
	private ArrayList listaPS = new ArrayList();

	private Integer plazoMaximo = new Integer(5);
	private Integer plazoMinimo = new Integer(2);
	private Long tipoAgenda = new Long(2);
	private String descripcionTipoAgenda = "";
	private String descripcionSegmento = "";
	private String descripcionFamiliaPS = "";
	private String nombreTica = "";
	
	private Long idGaudi = null;
	
	private HashMap camposVariables = new HashMap();

	public AgendamientoDTO() {
	}

	public String getCampoVariable(String key) {
		String aux = "";
		if (key == null)
			return aux;

		aux = (String) camposVariables.get(key);
		aux = (aux==null) ? "" : aux;
		
		return aux;
	}

	public void setCampoVariable(String key, String value) {
		if (key == null || value==null)
			return;
		camposVariables.put(key, value);
	}

	public void addPS(String codPS) {
		if ( codPS == null || "".equals(codPS) )
			return;
		ProductoDTO ps = new ProductoDTO();
		ps.setId( new Long(codPS) );
		listaPS.add(ps);
	}

	/**
	 * @return
	 */
	public String getCodigoSegmento() {
		return codigoSegmento;
	}

	/**
	 * @return
	 */
	public String getDescripcionFamiliaPS() {
		return descripcionFamiliaPS;
	}

	/**
	 * @return
	 */
	public String getDescripcionSegmento() {
		return descripcionSegmento;
	}

	/**
	 * @return
	 */
	public String getDescripcionTipoAgenda() {
		return descripcionTipoAgenda;
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
	public String getNombreTica() {
		return nombreTica;
	}

	/**
	 * @return
	 */
	public Integer getPlazoMaximo() {
		return plazoMaximo;
	}

	/**
	 * @return
	 */
	public Integer getPlazoMinimo() {
		return plazoMinimo;
	}

	/**
	 * @return
	 */
	public Long getTipoAgenda() {
		return tipoAgenda;
	}

	/**
	 * @param string
	 */
	public void setCodigoSegmento(String string) {
		codigoSegmento = string;
	}

	/**
	 * @param string
	 */
	public void setDescripcionFamiliaPS(String string) {
		descripcionFamiliaPS = string;
	}

	/**
	 * @param string
	 */
	public void setDescripcionSegmento(String string) {
		descripcionSegmento = string;
	}

	/**
	 * @param string
	 */
	public void setDescripcionTipoAgenda(String string) {
		descripcionTipoAgenda = string;
	}

	/**
	 * @param string
	 */
	public void setIdTica(String string) {
		idTica = string;
	}

	/**
	 * @param string
	 */
	public void setNombreTica(String string) {
		nombreTica = string;
	}

	/**
	 * @param integer
	 */
	public void setPlazoMaximo(Integer integer) {
		plazoMaximo = integer;
	}

	/**
	 * @param integer
	 */
	public void setPlazoMinimo(Integer integer) {
		plazoMinimo = integer;
	}

	/**
	 * @param long1
	 */
	public void setTipoAgenda(Long long1) {
		tipoAgenda = long1;
	}

	/**
	 * @return
	 */
	public ArrayList getListaPS() {
		return listaPS;
	}

	/**
	 * @param list
	 */
	public void setListaPS(ArrayList list) {
		listaPS = list;
	}

	public String toString() {
		 return ("idTica = '" + idTica + "'\n"
		+ "codigoSegmento = '" + codigoSegmento + "'\n"
		+ "plazoMaximo = '" + plazoMaximo + "'\n"
		+ "plazoMinimo = '" + plazoMinimo + "'\n"
		+ "tipoAgenda = '" + tipoAgenda + "'\n"
		+ "descripcionTipoAgenda = '" + descripcionTipoAgenda + "'\n"
		+ "descripcionSegmento = '" + descripcionSegmento + "'\n"
		+ "descripcionFamiliaPS = '" + descripcionFamiliaPS + "'\n"
		+ "nombreTica = '" + nombreTica + "'\n"
		 );
	}
	/**
	 * @return
	 */
	public Date getFechaCompromiso() {
		return fechaCompromiso;
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
	 * @return
	 */
	public Integer getIdRango() {
		return idRango;
	}

	/**
	 * @return
	 */
	public Long getIdTipoAgenda() {
		return idTipoAgenda;
	}

	/**
	 * @param date
	 */
	public void setFechaCompromiso(Date date) {
		fechaCompromiso = date;
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
	 * @param integer
	 */
	public void setIdRango(Integer integer) {
		idRango = integer;
	}

	/**
	 * @param long1
	 */
	public void setIdTipoAgenda(Long long1) {
		idTipoAgenda = long1;
	}

	/**
	 * @return
	 */
	public String getNombreRango() {
		return nombreRango;
	}

	/**
	 * @return
	 */
	public String getNombreTipoAgenda() {
		return nombreTipoAgenda;
	}

	/**
	 * @param string
	 */
	public void setNombreRango(String string) {
		nombreRango = string;
	}

	/**
	 * @param string
	 */
	public void setNombreTipoAgenda(String string) {
		nombreTipoAgenda = string;
	}

	/**
	 * @return
	 */
	public Long getIdGaudi() {
		return idGaudi;
	}

	/**
	 * @param string
	 */
	public void setIdGaudi(Long long1) {
		idGaudi = long1;
	}

}
