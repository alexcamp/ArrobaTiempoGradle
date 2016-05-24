
package com.telefonica_chile.bandeja.datos.usuarios;

import com.tecnonautica.utiles.excepciones.NestedException;

public class UsuariosException extends NestedException {

	public UsuariosException() {
		super();
	}

	public UsuariosException(String msg) {
		super(msg);
	}

	public UsuariosException(Exception e) {
		super(e);
	}

	public UsuariosException(String msg, Exception e) {
		super(msg, e);
	}
}
