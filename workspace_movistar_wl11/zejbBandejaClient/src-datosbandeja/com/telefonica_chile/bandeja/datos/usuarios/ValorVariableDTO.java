package com.telefonica_chile.bandeja.datos.usuarios;

import java.util.HashMap;

public class ValorVariableDTO {
	private String nombre;
	private HashMap valorPorPeticion = new HashMap();
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String string) {
		nombre = string;
	}

	public String getValorPorPeticion(Long idPeticion) {
		String valor = (String)valorPorPeticion.get(idPeticion);
		if (valor == null)
			valor = "";
		return valor;
	}
	
	public void setValorPorPeticion(Long idPeticion, String valor) {
		valorPorPeticion.put(idPeticion, valor);
	}
}
