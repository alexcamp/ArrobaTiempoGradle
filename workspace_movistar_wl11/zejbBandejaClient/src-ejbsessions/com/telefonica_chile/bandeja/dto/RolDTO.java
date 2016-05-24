package com.telefonica_chile.bandeja.dto;


public class RolDTO implements DTO {
	private Long id;
	private String codigo;
	private String nombre;

	public Long getId() {
		return id;
	}

	public void setId(Long long1) {
		id = long1;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String string) {
		codigo = string;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getNombre() {
		return nombre;
	}
}
