package com.telefonica_chile.bandeja.bintegrada.session;

import com.tecnonautica.utiles.excepciones.NestedException;

public class UsuarioNoAutenticadoException extends NestedException {
	public UsuarioNoAutenticadoException(String s) {
		super(s);
	}

	public UsuarioNoAutenticadoException(String s, Exception e) {
		super(s, e);
	}
}
