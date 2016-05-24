/*
 * Created on Feb 28, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.telefonica_chile.comun.actividad.dto;

import java.io.Serializable;

/**
 * @author defuenz
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ActividadDTO implements Serializable{

	/**
	 * 
	 */
	public ActividadDTO() {
		esGestionInbound=false;
	}
	
	private Long idActividad;
	private Long idAp;
	private Long idRol;
	private String descRol;
	private String codActividad;
	private String descActividad;
	private Integer actOrdenTc;
	private String actNombreReversa;
	private Byte actManual;
	private String nombreFlujo;
	
	private String idUsuario;
	private String nombreUsuario;
	private String fonoUsuario;
	private String emailUsuario;
	
	private Integer idActFlujo;
	private boolean quiebre;
	private boolean esGestionInbound;

	/**
	 * @return
	 */
	public Byte getActManual() {
		return actManual;
	}

	/**
	 * @return
	 */
	public String getActNombreReversa() {
		return actNombreReversa;
	}

	/**
	 * @return
	 */
	public Integer getActOrdenTc() {
		return actOrdenTc;
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
	public String getDescActividad() {
		return descActividad;
	}

	/**
	 * @return
	 */
	public Long getIdActividad() {
		return idActividad;
	}

	/**
	 * @return
	 */
	public Long getIdAp() {
		return idAp;
	}

	/**
	 * @return
	 */
	public Long getIdRol() {
		return idRol;
	}

	/**
	 * @param b
	 */
	public void setActManual(Byte b) {
		actManual = b;
	}

	/**
	 * @param string
	 */
	public void setActNombreReversa(String string) {
		actNombreReversa = string;
	}

	/**
	 * @param i
	 */
	public void setActOrdenTc(Integer i) {
		actOrdenTc = i;
	}

	/**
	 * @param string
	 */
	public void setCodActividad(String string) {
		codActividad = string;
	}

	/**
	 * @param string
	 */
	public void setDescActividad(String string) {
		descActividad = string;
	}

	/**
	 * @param long1
	 */
	public void setIdActividad(Long long1) {
		idActividad = long1;
	}

	/**
	 * @param long1
	 */
	public void setIdAp(Long long1) {
		idAp = long1;
	}

	/**
	 * @param long1
	 */
	public void setIdRol(Long long1) {
		idRol = long1;
	}

	/**
	 * @return
	 */
	public String getNombreFlujo() {
		return nombreFlujo;
	}

	/**
	 * @param string
	 */
	public void setNombreFlujo(String string) {
		nombreFlujo = string;
	}

	/**
	 * @return
	 */
	public String getFonoUsuario() {
		return fonoUsuario;
	}

	/**
	 * @return
	 */
	public String getIdUsuario() {
		return idUsuario;
	}

	/**
	 * @return
	 */
	public String getNombreUsuario() {
		return nombreUsuario;
	}

	/**
	 * @param string
	 */
	public void setFonoUsuario(String string) {
		fonoUsuario = string;
	}

	/**
	 * @param string
	 */
	public void setIdUsuario(String string) {
		idUsuario = string;
	}

	/**
	 * @param string
	 */
	public void setNombreUsuario(String string) {
		nombreUsuario = string;
	}

	/**
	 * @return
	 */
	public String getEmailUsuario() {
		return emailUsuario;
	}

	/**
	 * @param string
	 */
	public void setEmailUsuario(String string) {
		emailUsuario = string;
	}

	/**
	 * @return
	 */
	public String getDescRol() {
		return descRol;
	}

	/**
	 * @param string
	 */
	public void setDescRol(String string) {
		descRol = string;
	}

	/**
	 * @return
	 */
	public Integer getIdActFlujo() {
		return idActFlujo;
	}

	/**
	 * @param integer
	 */
	public void setIdActFlujo(Integer integer) {
		idActFlujo = integer;
	}

	/**
	 * @return
	 */
	public boolean isQuiebre() {
		return quiebre;
	}

	/**
	 * @param b
	 */
	public void setQuiebre(boolean b) {
		quiebre = b;
	}

	/**
	 * @return
	 */
	public boolean isEsGestionInbound() {
		return esGestionInbound;
	}

	/**
	 * @param b
	 */
	public void setEsGestionInbound(boolean b) {
		esGestionInbound = b;
	}

}
