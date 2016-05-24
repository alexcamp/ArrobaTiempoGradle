package com.telefonica_chile.bandeja.web.utiles.msgbroker;

public class MBInput {
	private MBCampo[] campos;
	
	public MBInput(MBCampo[] campos) {
		this.campos = campos;
	}
	
	public void setData(String nombre, String valor) {
		for (int i = 0; i < campos.length; i++) {
			if (campos[i].getNombre().equals(nombre)) {
				campos[i].setValue(valor);
				return;
			}
		}
	}
	
	public String toString() {
		String out = "";
		for (int i = 0; i < campos.length; i++)
			out = out + campos[i].filled();
		
		return out;
	}
}

