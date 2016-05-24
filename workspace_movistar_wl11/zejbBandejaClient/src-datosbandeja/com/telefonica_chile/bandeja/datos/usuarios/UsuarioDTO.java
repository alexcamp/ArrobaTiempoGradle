package com.telefonica_chile.bandeja.datos.usuarios;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class UsuarioDTO implements Serializable {
	
	private Long id;
	private String username;
	private String nombre;
	private String fono;
	private String rut;
	private String email;
	private String password;
	private String habilitado;
	private String direccion;
	private String cargo;
	private boolean tokenValido;
	
	private List roles = new ArrayList();
	private List perfiles = new ArrayList();

	private List paresSupervisor = new ArrayList();
	//// para GP
	private Long codUnidadComercial;
	private String unidadComercial;
	
	public UsuarioDTO() {
		tokenValido=false;
	}

	public UsuarioDTO(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public String getHabilitado() {
		return habilitado;
	}

	public String getPassword() {
		return password;
	}

	public String getRut() {
		return rut;
	}
	
	public String[] getPartesRut() {
		if (rut == null)
			return null;
		int idx = rut.indexOf('-');
		if (idx < 0)
			return null;
		String n = rut.substring(0, idx);
		String dv = rut.substring(idx + 1, rut.length());
		return new String[] {
			n, dv
		};
	}

	public void setEmail(String string) {
		email = string;
	}

	public void setHabilitado(String string) {
		habilitado = string;
	}

	public void setPassword(String string) {
		password = string;
	}

	public void setRut(String string) {
		rut = string;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String string) {
		direccion = string;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String string) {
		cargo = string;
	}

	public List getPerfiles() {
		return perfiles;
	}
	
	public List getRoles() {
		return roles;
	}
	
	public void addPerfil(PerfilDTO perfil) {
		perfiles.add(perfil);
	}

	public void addRol(RolDTO rol) {
		roles.add(rol);
	}

	public List getParesSupervisor() {
		return paresSupervisor;
	}

	public String getFono() {
		return fono;
	}

	public Long getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public String getUsername() {
		return username;
	}

	public void setFono(String string) {
		fono = string;
	}

	public void setId(Long long1) {
		id = long1;
	}

	public void setNombre(String string) {
		nombre = string;
	}

	public void setUsername(String string) {
		username = string;
	}

	public String toString() {
		return new StringBuffer("[U:")
		.append("(id=" + id)
		.append(")(username=" + username)
		.append(")(nombre=" + nombre)
		.append(")(fono=" + fono)
		.append(")(rut=" + rut)
		.append(")(email=" + email)
		.append(")]")
		.toString();	
	}
	/**
	 * @return
	 */
	public Long getCodUnidadComercial() {
		return codUnidadComercial;
	}

	/**
	 * @return
	 */
	public String getUnidadComercial() {
		return unidadComercial;
	}

	/**
	 * @param long1
	 */
	public void setCodUnidadComercial(Long long1) {
		codUnidadComercial = long1;
	}

	/**
	 * @param string
	 */
	public void setUnidadComercial(String string) {
		unidadComercial = string;
	}

	/**
	 * @return
	 */
	public boolean isTokenValido() {
		return tokenValido;
	}

	/**
	 * @param list
	 */
	public void setParesSupervisor(List list) {
		paresSupervisor = list;
	}

	/**
	 * @param list
	 */
	public void setPerfiles(List list) {
		perfiles = list;
	}

	/**
	 * @param list
	 */
	public void setRoles(List list) {
		roles = list;
	}

	/**
	 * @param b
	 */
	public void setTokenValido(boolean b) {
		tokenValido = b;
	}

}
