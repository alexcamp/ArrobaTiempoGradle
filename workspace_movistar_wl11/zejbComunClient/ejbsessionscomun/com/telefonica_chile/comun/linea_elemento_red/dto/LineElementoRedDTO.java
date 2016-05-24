/*
 * Created on Mar 3, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.telefonica_chile.comun.linea_elemento_red.dto;

/**
 * @author randreu
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class LineElementoRedDTO {
	

	private Long id=null;
	private Long idTELR=null;
	private String valor=null;
	private java.util.Date fechaInicio=null;
	private java.util.Date fechaFin=null;

public LineElementoRedDTO(){
	
		
}




	/**
	 * @return
	 */
	public java.util.Date getFechaFin() {
		return fechaFin;
	}

	/**
	 * @return
	 */
	public java.util.Date getFechaInicio() {
		return fechaInicio;
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
	public Long getIdTELR() {
		return idTELR;
	}

	/**
	 * @return
	 */
	public String getValor() {
		return valor;
	}

	/**
	 * @param date
	 */
	public void setFechaFin(java.util.Date date) {
		fechaFin = date;
	}

	/**
	 * @param date
	 */
	public void setFechaInicio(java.util.Date date) {
		fechaInicio = date;
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
	public void setIdTELR(Long long1) {
		idTELR = long1;
	}

	/**
	 * @param string
	 */
	public void setValor(String string) {
		valor = string;
	}

}
