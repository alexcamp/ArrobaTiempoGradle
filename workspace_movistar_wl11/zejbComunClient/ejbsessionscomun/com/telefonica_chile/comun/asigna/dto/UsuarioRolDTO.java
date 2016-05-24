/*
 * Created on Sep 24, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.telefonica_chile.comun.asigna.dto;

import java.io.Serializable;
import java.util.HashMap;

/**
 * @author gavalen
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class UsuarioRolDTO implements Serializable{
	private Long usuaId;
	private Long rolId; 
	private Long usroHabilitado; 
	private Long usuaIdSup;
	
	private String login;
	private String nombre;
	private HashMap habilidades = null;

	/**
	 * @return
	 */
	public Long getRolId() {
		return rolId;
	}

	/**
	 * @return
	 */
	public Long getUsroHabilitado() {
		return usroHabilitado;
	}

	/**
	 * @return
	 */
	public Long getUsuaId() {
		return usuaId;
	}

	/**
	 * @return
	 */
	public Long getUsuaIdSup() {
		return usuaIdSup;
	}

	/**
	 * @param long1
	 */
	public void setRolId(Long long1) {
		rolId = long1;
	}

	/**
	 * @param long1
	 */
	public void setUsroHabilitado(Long long1) {
		usroHabilitado = long1;
	}

	/**
	 * @param long1
	 */
	public void setUsuaId(Long long1) {
		usuaId = long1;
	}

	/**
	 * @param long1
	 */
	public void setUsuaIdSup(Long long1) {
		usuaIdSup = long1;
	}

	/**
	 * @return
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * @return
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param string
	 */
	public void setLogin(String string) {
		login = string;
	}

	/**
	 * @param string
	 */
	public void setNombre(String string) {
		nombre = string;
	}

	/**
	 * @return
	 */
	public boolean tieneHabilidad(String key) {

		if ( habilidades == null )
			return false;
		if ( habilidades.containsKey(key) )
			return true;
		if ( habilidades.containsKey("*") && !habilidades.containsKey("-" + key) )
			return true;
		
		return false;
	}

	public HashMap getHabilidades() {
		return habilidades;
	}

	public void setHabilidades(HashMap map) {
		habilidades = map;
	}

}
