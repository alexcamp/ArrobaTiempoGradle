/*
 * Created on Feb 28, 2005
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
public class PlantaComercialDTO implements Serializable {	
	
	public PlantaComercialDTO() {
		   super();
	}
		
	private Long    plcoiD;
	private Long    terrId;
	private String  plcoCodigo;
	private String  plcoNombre;

	/**
	 * @return
	 */
	public String getPlcoCodigo() {
		return plcoCodigo;
	}

	/**
	 * @return
	 */
	

	/**
	 * @return
	 */
	public String getPlcoNombre() {
		return plcoNombre;
	}

	/**
	 * @return
	 */
	public Long getTerrId() {
		return terrId;
	}

	/**
	 * @param string
	 */
	public void setPlcoCodigo(String string) {
		plcoCodigo = string;
	}

	

	/**
	 * @param string
	 */
	public void setPlcoNombre(String string) {
		plcoNombre = string;
	}

	/**
	 * @param long1
	 */
	public void setTerrId(Long long1) {
		terrId = long1;
	}

	/**
	 * @return
	 */
	public Long getPlcoiD() {
		return plcoiD;
	}

	/**
	 * @param long1
	 */
	public void setPlcoiD(Long long1) {
		plcoiD = long1;
	}

}
