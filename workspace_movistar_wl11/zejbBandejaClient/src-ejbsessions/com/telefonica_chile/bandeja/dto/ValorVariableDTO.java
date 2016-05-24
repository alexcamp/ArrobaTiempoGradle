package com.telefonica_chile.bandeja.dto;

import java.io.Serializable;

public class ValorVariableDTO implements Serializable {

	private int cvId = 0;
	private Long biId = new Long(0);
	private String valor = "";
	
	public Long getBiId() {
		return biId;
	}

	public int getCvId() {
		return cvId;
	}

	public String getValor() {
		return valor;
	}

	public void setBiId(Long long1) {
		biId = long1;
	}

	public void setCvId(int i) {
		cvId = i;
	}

	public void setValor(String string) {
		valor = string;
	}
}
