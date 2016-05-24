package com.telefonica_chile.bandeja.datos.usuarios;

import java.io.Serializable;


public class RolDTO implements Serializable,Comparable {
	private Long id;
	private String codigo;
	private String nombre;
	private String pushApp;

	public Long getId() {
		return id;
	}

	public void setId(Long long1) {
		id = long1;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String string) {
		codigo = string;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getNombre() {
		return nombre;
	}
	public void setPushApp(String pushApp) {
		this.pushApp = pushApp;
	}
	public String getPushApp() {
		return pushApp;
	}
	
	public String toString() {
		return "RolDTO" + " id=[" + id + "]" + " codigo=[" + codigo + "]" + " nombre=[" + nombre +"]" + " pushApp=[" + pushApp + "]";
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Object o) {
		RolDTO otro=(RolDTO) o;
		if(this.nombre!=null && otro.getNombre()!=null)
			return this.nombre.compareTo(otro.getNombre());
		return 0;
	}
}
