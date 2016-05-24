package com.telefonica_chile.bandeja.web;

import com.tecnonautica.mvc.ControladorDeAplicacion;
import com.telefonica_chile.bandeja.ejbutiles.UsuarioWeb;

public class AtiempoControladorDeAplicacion extends ControladorDeAplicacion {
	private UsuarioWeb usuario;
	public AtiempoControladorDeAplicacion() {
	}
	public UsuarioWeb getUsuario() {
		return usuario;
	}
	public void setUsuario(UsuarioWeb usuario) {
		this.usuario = usuario;
	}
}
