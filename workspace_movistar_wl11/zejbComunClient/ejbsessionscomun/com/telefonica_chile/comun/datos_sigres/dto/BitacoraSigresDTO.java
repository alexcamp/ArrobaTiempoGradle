/*
 * Created on 07-nov-06
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.telefonica_chile.comun.datos_sigres.dto;

import java.io.Serializable;

/**
 * @author Admin
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class BitacoraSigresDTO implements Serializable {
	
	private String id;
	private String nomAct;
	private String estadoAct;
	private String fechaIniAct;
	private String fechaAperAct;
	private String fechaTerAct;
	private String nomRol;
	private String operNom;
	private String operEmail;
	private String operFono;
	private String status;
	private String descripcion;
	private String observaciones;
	
	public BitacoraSigresDTO(){
		this.id="";
		this.nomAct="";
		this.nomAct="";
		this.estadoAct="";
		this.fechaIniAct="";
		this.fechaAperAct="";
		this.fechaTerAct="";
		this.nomRol="";
		this.operNom="";
		this.operEmail="";
		this.operFono="";
		this.status="";
		this.descripcion="";
		this.observaciones="";				
	}

	/**
	 * @return
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @return
	 */
	public String getEstadoAct() {
		return estadoAct;
	}

	/**
	 * @return
	 */
	public String getFechaIniAct() {
		return fechaIniAct;
	}

	/**
	 * @return
	 */
	public String getFechaTerAct() {
		return fechaTerAct;
	}

	/**
	 * @return
	 */
	public String getNomAct() {
		return nomAct;
	}

	/**
	 * @return
	 */
	public String getNomRol() {
		return nomRol;
	}

	/**
	 * @return
	 */
	public String getObservaciones() {
		return observaciones;
	}

	/**
	 * @return
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param string
	 */
	public void setDescripcion(String string) {
		descripcion = string;
	}

	/**
	 * @param string
	 */
	public void setEstadoAct(String string) {
		estadoAct = string;
	}

	/**
	 * @param string
	 */
	public void setFechaIniAct(String string) {
		fechaIniAct = string;
	}

	/**
	 * @param string
	 */
	public void setFechaTerAct(String string) {
		fechaTerAct = string;
	}

	/**
	 * @param string
	 */
	public void setNomAct(String string) {
		nomAct = string;
	}

	/**
	 * @param string
	 */
	public void setNomRol(String string) {
		nomRol = string;
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
	public void setStatus(String string) {
		status = string;
	}

	/**
	 * @return
	 */
	public String getFechaAperAct() {
		return fechaAperAct;
	}

	/**
	 * @return
	 */
	public String getOperEmail() {
		return operEmail;
	}

	/**
	 * @return
	 */
	public String getOperFono() {
		return operFono;
	}

	/**
	 * @return
	 */
	public String getOperNom() {
		return operNom;
	}

	/**
	 * @param string
	 */
	public void setFechaAperAct(String string) {
		fechaAperAct = string;
	}

	/**
	 * @param string
	 */
	public void setOperEmail(String string) {
		operEmail = string;
	}

	/**
	 * @param string
	 */
	public void setOperFono(String string) {
		operFono = string;
	}

	/**
	 * @param string
	 */
	public void setOperNom(String string) {
		operNom = string;
	}

	/**
	 * @return
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param string
	 */
	public void setId(String string) {
		id = string;
	}

}
