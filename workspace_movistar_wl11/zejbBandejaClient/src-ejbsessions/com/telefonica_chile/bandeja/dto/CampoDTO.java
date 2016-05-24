package com.telefonica_chile.bandeja.dto;

import java.io.Serializable;
import java.util.HashMap;

public class CampoDTO implements Serializable{
	private short id;
	private String nombreInterno;
	private String nombreExterno;
	private HashMap valorVariables = new HashMap();

	public short getId() {
		return id;
	}

	public String getNombreExterno() {
		return nombreExterno;
	}

	public String getNombreInterno() {
		return nombreInterno;
	}

	public void setId(short id) {
		this.id = id;
	}

	public void setNombreExterno(String string) {
		nombreExterno = string;
	}

	public void setNombreInterno(String string) {
		nombreInterno = string;
	}
	/**
	 * @return
	 */
	public HashMap getValorVariables() {
		return valorVariables;
	}

	/**
	 * @param map
	 */
	public void setValorVariables(HashMap map) {
		valorVariables = map;
	}

}
