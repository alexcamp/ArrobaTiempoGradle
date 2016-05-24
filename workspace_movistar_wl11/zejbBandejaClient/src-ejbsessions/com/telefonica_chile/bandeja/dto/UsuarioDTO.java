package com.telefonica_chile.bandeja.dto;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.telefonica_chile.bandeja.mantenedores.usuarios.ParUsuarioRol;


public class UsuarioDTO implements DTO, Serializable {
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
	private String estado;
	private Timestamp ultimoCambioEstado;
	
	private Long rolPorAgregar;
	private Long perfilPorAgregar;

	private Long rolPorEliminar;
	private Long perfilPorEliminar;

	private List roles = new ArrayList();
	private List perfiles = new ArrayList();

	private List paresSupervisor = new ArrayList();

	private ParUsuarioRol parSupervisorPorAgregar;
	private ParUsuarioRol parSupervisorPorEliminar;

	private List camposVariables = new ArrayList();
	private short campoPorAgregar;
	private short campoPorEliminar;

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

	public Long getPerfilPorAgregar() {
		return perfilPorAgregar;
	}

	public Long getPerfilPorEliminar() {
		return perfilPorEliminar;
	}

	public Long getRolPorAgregar() {
		return rolPorAgregar;
	}

	public Long getRolPorEliminar() {
		return rolPorEliminar;
	}

	public void setPerfilPorAgregar(Long long1) {
		perfilPorAgregar = long1;
	}

	public void setPerfilPorEliminar(Long long1) {
		perfilPorEliminar = long1;
	}

	public void setRolPorAgregar(Long long1) {
		rolPorAgregar = long1;
	}

	public void setRolPorEliminar(Long long1) {
		rolPorEliminar = long1;
	}

	public void addParSupervisor(UsuarioDTO u, RolDTO r) {
		ParUsuarioRol par = new ParUsuarioRol(u, r);
		paresSupervisor.add(par);
	}

	public List getParesSupervisor() {
		return paresSupervisor;
	}
	
	public void setParSupervisorPorAgregar(Long idUsuario, Long idRol) {
		UsuarioDTO u = new UsuarioDTO();
		u.setId(idUsuario);

		RolDTO r = new RolDTO();
		r.setId(idRol);
 
		this.parSupervisorPorAgregar = new ParUsuarioRol(u, r); 
	}
	
	public ParUsuarioRol getParSupervisorPorAgregar() {
		return parSupervisorPorAgregar;
	}

	public void setParSupervisorPorEliminar(Long idUsuario, Long idRol) {
		UsuarioDTO u = new UsuarioDTO();
		u.setId(idUsuario);

		RolDTO r = new RolDTO();
		r.setId(idRol);
 
		this.parSupervisorPorEliminar = new ParUsuarioRol(u, r); 
	}
	
	public ParUsuarioRol getParSupervisorPorEliminar() {
		return parSupervisorPorEliminar;
	}

	public List getCamposVariables() {
		return camposVariables;
	}
	
	public void addCampoVariable(CampoDTO campo) {
		camposVariables.add(campo);
	}

	public short getCampoPorAgregar() {
		return campoPorAgregar;
	}

	public short getCampoPorEliminar() {
		return campoPorEliminar;
	}

	public void setCampoPorAgregar(short idcampo) {
		campoPorAgregar = idcampo;
	}

	public void setCampoPorEliminar(short idcampo) {
		campoPorEliminar = idcampo;
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
	
	public String getEstado() {
		return estado;
	}
	
	public void setEstado(String string) {
		estado = string;
	}
	
	public Timestamp getUltimoCambioEstado() {
		return ultimoCambioEstado;
	}
	
	public void setUltimoCambioEstado(Timestamp t) {
		ultimoCambioEstado = t;
	}

}
