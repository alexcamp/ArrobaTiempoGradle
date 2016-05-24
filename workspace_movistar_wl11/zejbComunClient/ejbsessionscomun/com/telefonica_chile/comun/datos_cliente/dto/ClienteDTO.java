/*
 * Created on Feb 25, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.telefonica_chile.comun.datos_cliente.dto;


import java.io.Serializable;

/**
 * @author ptoliva
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ClienteDTO  implements Serializable {	
	
	public ClienteDTO() {
		   super();
	}
		
	private Long    segmID;
	private Long    segmFaseId;
	private String  segmDescripcion;
	private String  segmCodigo;
	private Long    cliCodCliente;
	private String  cliRut;
	private String  cliDigVer;
	private String  clieNombre;
	private String  clieApePaterno;
	private String  clieApeMaterno;
	private String  clieCicloFacturacion;
	private String  actividadEconomica;
	private String nomBloqueSegmento="";
	/**
	 * @return
	 */
	public Long getCliCodCliente() {
		return cliCodCliente;
	}

	
	/**
	 * @return
	 */
	public String getCliDigVer() {
		return cliDigVer;
	}

	/**
	 * @return
	 */
	public String getClieApeMaterno() {
		return clieApeMaterno;
	}

	/**
	 * @return
	 */
	public String getClieApePaterno() {
		return clieApePaterno;
	}

	/**
	 * @return
	 */
	public String getClieCicloFacturacion() {
		return clieCicloFacturacion;
	}

	/**
	 * @return
	 */
	public String getClieNombre() {
		return clieNombre;
	}

	/**
	 * @return
	 */
	public String getCliRut() {
		return cliRut;
	}

	/**
	 * @return
	 */
	public String getSegmCodigo() {
		return segmCodigo;
	}

	/**
	 * @return
	 */
	public String getSegmDescripcion() {
		return segmDescripcion;
	}

	/**
	 * @return
	 */
	public Long getSegmFaseId() {
		return segmFaseId;
	}

	/**
	 * @return
	 */
	public Long getSegmID() {
		return segmID;
	}

	/**
	 * @param long1
	 */
	public void setCliCodCliente(Long long1) {
		cliCodCliente = long1;
	}

	
	/**
	 * @param string
	 */
	public void setCliDigVer(String string) {
		cliDigVer = string;
	}

	/**
	 * @param string
	 */
	public void setClieApeMaterno(String string) {
		clieApeMaterno = string;
	}

	/**
	 * @param string
	 */
	public void setClieApePaterno(String string) {
		clieApePaterno = string;
	}

	/**
	 * @param string
	 */
	public void setClieCicloFacturacion(String string) {
		clieCicloFacturacion = string;
	}

	/**
	 * @param string
	 */
	public void setClieNombre(String string) {
		clieNombre = string;
	}

	/**
	 * @param string
	 */
	public void setCliRut(String string) {
		cliRut = string;
	}

	/**
	 * @param string
	 */
	public void setSegmCodigo(String string) {
		segmCodigo = string;
	}

	/**
	 * @param string
	 */
	public void setSegmDescripcion(String string) {
		segmDescripcion = string;
	}

	/**
	 * @param long1
	 */
	public void setSegmFaseId(Long long1) {
		segmFaseId = long1;
	}

	/**
	 * @param long1
	 */
	public void setSegmID(Long long1) {
		segmID = long1;
	}

	/**
	 * @return
	 */
	public String getActividadEconomica() {
		return actividadEconomica;
	}

	/**
	 * @param string
	 */
	public void setActividadEconomica(String string) {
		actividadEconomica = string;
	}

	/**
	 * @return
	 */
	public String getNomBloqueSegmento() {
		return nomBloqueSegmento;
	}

	/**
	 * @param string
	 */
	public void setNomBloqueSegmento(String string) {
		nomBloqueSegmento = string;
	}

}
