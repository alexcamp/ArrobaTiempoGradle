package com.telefonica_chile.bandeja.mantenedores;

import java.io.Serializable;

import com.telefonica_chile.bandeja.dto.UsuarioDTO;

public class UsuarioBandejaSupervisorDTO extends UsuarioDTO implements Serializable {
	private int cantidadPeticiones;

	public int getCantidadPeticiones() {
		return cantidadPeticiones;
	}

	public void setCantidadPeticiones(int i) {
		cantidadPeticiones = i;
	}
}
