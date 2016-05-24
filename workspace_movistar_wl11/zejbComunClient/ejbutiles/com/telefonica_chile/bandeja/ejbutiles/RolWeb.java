package com.telefonica_chile.bandeja.ejbutiles;

public class RolWeb {
	private Long id;
	private String codigo;

	public RolWeb(Long id, String codigo) {
		this.id = id;
		this.codigo = codigo;
	}
	public Long getId() {
		return id;
	}

	public String getCodigo() {
		return codigo;
	}
}
