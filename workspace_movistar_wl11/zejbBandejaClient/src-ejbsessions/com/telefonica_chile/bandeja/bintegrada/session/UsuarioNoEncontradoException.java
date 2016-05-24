package com.telefonica_chile.bandeja.bintegrada.session;

import com.tecnonautica.utiles.excepciones.NestedException;

public class UsuarioNoEncontradoException extends NestedException {
	public UsuarioNoEncontradoException(Long idUsuario) {
		super("No se encontro usuario " + idUsuario);
	}

	public UsuarioNoEncontradoException(String username, String email) {
		super(username + "(" + email + ")");
	}

	public UsuarioNoEncontradoException(String username, String email, Exception e) {
		super(username + "(" + email + ")", e);
	}
	
	public UsuarioNoEncontradoException(Long idUsuario, Exception e) {
		super("No se encontro usuario " + idUsuario, e);
	}
}
