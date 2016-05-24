/*
 * Created on Feb 24, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.telefonica_chile.comun.asigna.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.GregorianCalendar;



public class FiltroDiaDTO implements Serializable {
	
	//	Limite para Agendar
	private Date fechaMinimaAgenda;
	//	Limite para Desplegar
	private Date fechaMinimaLimite;
	
	
	public FiltroDiaDTO(Date fechaMinima) {
		this.fechaMinimaAgenda = fechaMinima;
		this.fechaMinimaLimite = fechaMinima;
		//this.fechaMaximaAgenda = agregarDiasFechaInicio(fechaMinimaAgenda);
	}


	private Date agregarHorasFechaHoy(int horas) {
		GregorianCalendar gc = (GregorianCalendar) GregorianCalendar.getInstance();			
		gc.setTime(new Date());		
		gc.add(GregorianCalendar.HOUR_OF_DAY,horas);
		return gc.getTime();
	}

	private Date agregarDiasFechaInicio(Date fechaMinimaAgenda) {
		GregorianCalendar gc = (GregorianCalendar) GregorianCalendar.getInstance();			
		gc.setTime(fechaMinimaAgenda);		
		gc.add(GregorianCalendar.DAY_OF_YEAR,5);
		return gc.getTime();
	}
	
	
	
	

	

	/**
	 * @return
	 */
	public Date getFechaMinimaAgenda() {
		return fechaMinimaAgenda;
	}

	/**
	 * @param date
	 */
	public void setFechaMinimaAgenda(Date date) {
		fechaMinimaAgenda = date;
	}

	/**
	 * @return
	 */
	public Date getFechaMinimaLimite() {
		return fechaMinimaLimite;
	}

	/**
	 * @param date
	 */
	public void setFechaMinimaLimite(Date date) {
		fechaMinimaLimite = date;
	}

}
