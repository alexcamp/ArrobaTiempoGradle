package com.telefonica_chile.bandeja.mantenedores.usuarios;

import com.telefonica_chile.bandeja.dto.DTO;
import com.telefonica_chile.bandeja.dto.RolDTO;
import com.telefonica_chile.bandeja.dto.UsuarioDTO;

public class ParUsuarioRol implements DTO {
	private UsuarioDTO usuario;
	private RolDTO rol;

	public ParUsuarioRol(UsuarioDTO usuario, RolDTO rol) {
		this.usuario = usuario;
		this.rol = rol;
	}

	public RolDTO getRol() {
		return rol;
	}

	public UsuarioDTO getUsuario() {
		return usuario;
	}

}
