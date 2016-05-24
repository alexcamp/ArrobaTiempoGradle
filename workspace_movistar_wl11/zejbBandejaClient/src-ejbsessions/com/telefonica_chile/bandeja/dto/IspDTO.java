package com.telefonica_chile.bandeja.dto;

import java.io.Serializable;

public class IspDTO implements Serializable{
		
	private Long 	ispId = new Long(0);
	private String 	ispCodigo = "";
	private String 	ispDescripcion = ""; 

	public Long getIspId() {
		return ispId;
	}

	public void setIspId(Long long1) {
		ispId = long1;
	}

	public String getIspCodigo() {
		return ispCodigo;
	}

	public void setIspCodigo(String string) {
		ispCodigo = string;
	}

	public String getIspDescripcion() {
		return ispDescripcion;
	}

	public void setIspDescripcion(String string) {
		ispDescripcion = string;
	}
}
