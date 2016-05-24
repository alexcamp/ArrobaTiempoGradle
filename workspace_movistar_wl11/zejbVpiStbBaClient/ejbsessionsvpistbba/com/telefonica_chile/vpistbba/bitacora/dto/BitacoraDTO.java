/*
 * Created on Feb 28, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.telefonica_chile.vpistbba.bitacora.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * @author defuenz
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class BitacoraDTO implements Serializable{

	/**
	 * 
	 */
	
	public BitacoraDTO(int i){
		switch (i) {
			
		case 1 :
		this.actividad = "Actividad en Terreno";
		this.fechaInicio = new Date();
		this.fechaFin =  new Date();
		this.usuario = "";
		this.causa = "causa";
		this.actividad = "actividad";
		this.observaciones = "Cuadrante 18";
		this.rol = "Privado";				
		break;
		
		case 2 :		
		this.actividad = "Tendido Eléctrico";
		this.fechaInicio = new Date();
		this.fechaFin =  new Date();
		this.usuario = "";
		this.causa = "causa";
		this.actividad = "actividad";
		this.observaciones = "--";
		this.rol = "Privado";				
		break;
		
		case 3 :		
		this.actividad = "Cableado Subterraneo";
		this.fechaInicio = new Date();
		this.fechaFin =  new Date();
		this.usuario = "";
		this.causa = "causa";
		this.actividad = "actividad";
		this.observaciones = "Dirección 2 entre la 1° y la 3°";
		this.rol = "General";				
		break;
		
		case 4 :		
		this.actividad = "Mapeado y Sincronización de Terminal de Resistencia";
		this.fechaInicio = new Date();
		this.fechaFin =  new Date();
		this.usuario = "";
		this.causa = "causa";
		this.actividad = "actividad";
		this.observaciones = "Control de T° , O2 , CO2";
		this.rol = "Contratista";				
		break;
		
		default :
		break;
		}		
	}
	
	public BitacoraDTO() {
		super();
		
		this.usuario = "";
		this.causa = "";
		this.actividad = "";
		this.codigoActividad = "";
		this.rol = "";
		this.observaciones = "";
	}
	
	private Date fechaInicio;
	private Date fechaFin;
	private String usuario;
	private String causa;
	private String actividad = "";
	private String codigoActividad = "";
	private String rol = "";
	private String observaciones = "";  
	private String reversa = "";
	private Long idActividad = null;

	/**
	 * @return
	 */
	public String getActividad() {
		return actividad;
	}

	/**
	 * @return
	 */
	//Correccion defecto "No muestra las observaciones"  - 02 junio 2009 - agonz
	public String getObservaciones() {
		String result= "";
		if(observaciones!=null)
			result = observaciones.replaceAll("\"","'").trim();
		
		return result;
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
	public void setActividad(String string) {
		actividad = string;
	}


	/**
	 * @param string
	 */
	public void setObservaciones(String string) {
		observaciones = string;
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
	public Date getFechaFin() {
		return fechaFin;
	}

	/**
	 * @return
	 */
	public Date getFechaInicio() {
		return fechaInicio;
	}

	/**
	 * @param date
	 */
	public void setFechaFin(Date date) {
		fechaFin = date;
	}

	/**
	 * @param date
	 */
	public void setFechaInicio(Date date) {
		fechaInicio = date;
	}

	/**
	 * @return
	 */
	public String getCausa() {
		return causa;
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
	public void setCausa(String string) {
		causa = string;
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
	public String getReversa() {
		return reversa;
	}

	/**
	 * @param string
	 */
	public void setReversa(String string) {
		reversa = string;
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

}
