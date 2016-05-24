package com.telefonica_chile.bandeja.dto;


public class PerfilDTO implements DTO {
	private Long id;
	private String descripcion;

	public String getDescripcion() {
		return descripcion;
	}

	public Long getId() {
		return id;
	}

	public void setDescripcion(String string) {
		descripcion = string;
	}

	public void setId(Long long1) {
		id = long1;
	}

}
