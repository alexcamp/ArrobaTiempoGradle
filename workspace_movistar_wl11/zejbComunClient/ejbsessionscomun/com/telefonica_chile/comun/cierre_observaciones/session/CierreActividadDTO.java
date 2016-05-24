/*
 * Created on Mar 11, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.telefonica_chile.comun.cierre_observaciones.session;

import java.io.Serializable;

/**
 * @author defuenz
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class CierreActividadDTO implements Serializable {

	/**
	 * 
	 */
	public CierreActividadDTO() {
		super();
	}
	private Long id;
	private Long idAct;
	private String nombre;
	private String codigo;
	private String valor;
	private Byte reversa;
	/**
	 * @return
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * @return
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @return
	 */
	public Long getIdAct() {
		return idAct;
	}

	/**
	 * @return
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @return
	 */
	public String getValor() {
		return valor;
	}

	/**
	 * @param string
	 */
	public void setCodigo(String string) {
		codigo = string;
	}

	/**
	 * @param long1
	 */
	public void setId(Long long1) {
		id = long1;
	}

	/**
	 * @param long1
	 */
	public void setIdAct(Long long1) {
		idAct = long1;
	}

	/**
	 * @param string
	 */
	public void setNombre(String string) {
		nombre = string;
	}



	/**
	 * @param string
	 */
	public void setValor(String string) {
		valor = string;
	}





	/**
	 * @return
	 */
	public Byte getReversa() {
		return reversa;
	}

	/**
	 * @param byte1
	 */
	public void setReversa(Byte byte1) {
		reversa = byte1;
	}

}
