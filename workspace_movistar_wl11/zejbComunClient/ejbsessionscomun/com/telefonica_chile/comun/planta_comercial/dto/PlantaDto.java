/*
 * Created on Mar 1, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.telefonica_chile.comun.planta_comercial.dto;

import java.io.Serializable;

/**
 * @author ptoliva
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class PlantaDto implements Serializable {	
	
	public PlantaDto() {
		   super();
	}
		
	private String  directorComercial;
	private String  telefonoDirector;
	private String  ejecutivoComercial;
	private String  telefonoEjecutivo;	
	

	/**
	 * @return
	 */
	public String getDirectorComercial() {
		return directorComercial;
	}

	/**
	 * @return
	 */
	public String getEjecutivoComercial() {
		return ejecutivoComercial;
	}

	/**
	 * @return
	 */
	public String getTelefonoDirector() {
		return telefonoDirector;
	}

	/**
	 * @return
	 */
	public String getTelefonoEjecutivo() {
		return telefonoEjecutivo;
	}

	/**
	 * @param string
	 */
	public void setDirectorComercial(String string) {
		directorComercial = string;
	}

	/**
	 * @param string
	 */
	public void setEjecutivoComercial(String string) {
		ejecutivoComercial = string;
	}

	/**
	 * @param string
	 */
	public void setTelefonoDirector(String string) {
		telefonoDirector = string;
	}

	/**
	 * @param string
	 */
	public void setTelefonoEjecutivo(String string) {
		telefonoEjecutivo = string;
	}

}
