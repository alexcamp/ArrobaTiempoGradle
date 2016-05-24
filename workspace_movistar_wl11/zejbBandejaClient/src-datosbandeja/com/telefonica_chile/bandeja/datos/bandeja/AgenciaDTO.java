package com.telefonica_chile.bandeja.datos.bandeja;

import java.io.Serializable;

public class AgenciaDTO  implements Serializable {
	private Long id;
	private String codigo;
	private String descripcion;
	private boolean pasaPorMDF;
	
	public String getCodigo() {
		return codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public Long getId() {
		return id;
	}

	public void setCodigo(String string) {
		codigo = string;
	}

	public void setDescripcion(String string) {
		descripcion = string;
	}

	public void setId(Long long1) {
		id = long1;
	}

	public boolean isPasaPorMDF() {
		return pasaPorMDF;
	}

	public void setPasaPorMDF(boolean b) {
		pasaPorMDF = b;
	}

}
