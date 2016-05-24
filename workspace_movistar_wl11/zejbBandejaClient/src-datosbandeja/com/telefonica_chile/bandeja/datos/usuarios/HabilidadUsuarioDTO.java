/*
 * Created on Oct 21, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.telefonica_chile.bandeja.datos.usuarios;

import java.io.Serializable;

/**
 * @author gavalen
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class HabilidadUsuarioDTO implements Serializable{
	
	public Long   idHabiUsua;
	public Long   idHabilidad;
	public Long   idUsuario;
	public String Valor;
	/**
	 * @return
	 */
	public Long getIdHabilidad() {
		return idHabilidad;
	}

	/**
	 * @return
	 */
	public Long getIdHabiUsua() {
		return idHabiUsua;
	}

	/**
	 * @return
	 */
	public Long getIdUsuario() {
		return idUsuario;
	}

	/**
	 * @return
	 */
	public String getValor() {
		return Valor;
	}

	/**
	 * @param long1
	 */
	public void setIdHabilidad(Long long1) {
		idHabilidad = long1;
	}

	/**
	 * @param long1
	 */
	public void setIdHabiUsua(Long long1) {
		idHabiUsua = long1;
	}

	/**
	 * @param long1
	 */
	public void setIdUsuario(Long long1) {
		idUsuario = long1;
	}

	/**
	 * @param string
	 */
	public void setValor(String string) {
		Valor = string;
	}

}
