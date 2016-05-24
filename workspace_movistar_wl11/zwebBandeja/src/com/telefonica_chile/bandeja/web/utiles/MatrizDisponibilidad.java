/*
 * Created on Mar 3, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.telefonica_chile.bandeja.web.utiles;


public class MatrizDisponibilidad {
	
	Integer	disponibilidadHoraria[][]= new Integer[12][5];
	 
	/**
	 * 
	 */
	public MatrizDisponibilidad() {	
	}

	/**
	 * @return
	 */
	public Integer[][] getDisponibilidadHoraria() {
		return disponibilidadHoraria;
	}

	/**
	 * @param integers
	 */
	public void setDisponibilidadHoraria(Integer[][] integers) {
		disponibilidadHoraria = integers;
	}

}
