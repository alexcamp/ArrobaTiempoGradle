package com.telefonica_chile.bandeja.ejbutiles;

import java.io.Serializable;
import java.util.ArrayList;

public class UsuarioWeb implements Serializable{
	private Long id;
	private String username;
	private String nombre;
	private String telefono;
	private boolean tokenValido;
	
	private ArrayList roles = new ArrayList();
	private ArrayList menues = new ArrayList();
	
	public UsuarioWeb(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public String getTelefono() {
		return telefono;
	}

	public String getUsername() {
		return username;
	}

	public void setNombre(String string) {
		nombre = string;
	}

	public void setTelefono(String string) {
		telefono = string;
	}

	public void setUsername(String string) {
		username = string;
	}

	public ArrayList getRoles() {
		return roles;
	}

	public void addRol(RolWeb rol) {
		this.roles.add(rol);
	}

	public ArrayList getMenues() {
		return menues;
	}

	public void setMenues(ArrayList list) {
		menues = list;
	}

	/**
	 * @return
	 */
	public boolean isTokenValido() {
		return tokenValido;
	}

	/**
	 * @param b
	 */
	public void setTokenValido(boolean b) {
		tokenValido = b;
	}

}
