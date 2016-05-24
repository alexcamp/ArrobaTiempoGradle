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



public class FiltroDia implements Serializable {
	
	private Date fechaMinima;
	private Date fechaMaxima;
	int duracionActividad;
	
	
	/**
	 * 
	 */
	public FiltroDia() {		
	}

	/**
	 * 
	 */
	
	public FiltroDia(Integer diasMinimos,Integer diasMaximos,int duracionActividad) {
		this.fechaMinima = agregarDiasFechaHoy(diasMinimos);
		this.fechaMaxima = agregarDiasFechaHoy(diasMaximos);
		this.duracionActividad = duracionActividad;
	}

	/**
	 * @param dias
	 * @return Date + Dias
	 */
	private Date agregarDiasFechaHoy(Integer dias) {
		GregorianCalendar gc = (GregorianCalendar) GregorianCalendar.getInstance();			
		gc.setTime(new Date());		
		gc.add(GregorianCalendar.DAY_OF_YEAR, dias.intValue());
		return gc.getTime();
	}

	/**
	 * @return
	 */
	public int getDuracionActividad() {
		return duracionActividad;
	}

	/**
	 * @return
	 */
	public Date getFechaMaxima() {
		return fechaMaxima;
	}

	/**
	 * @return
	 */
	public Date getFechaMinima() {
		return fechaMinima;
	}

	/**
	 * @param i
	 */
	public void setDuracionActividad(int i) {
		duracionActividad = i;
	}

	/**
	 * @param date
	 */
	public void setFechaMaxima(Date date) {
		fechaMaxima = date;
	}

	/**
	 * @param date
	 */
	public void setFechaMinima(Date date) {
		fechaMinima = date;
	}
	
	public static FiltroDia recuperarRangoDias(String codigoAgencia, String tica, String puntoVenta, String segmento) {
		FiltroDia objFiltroDiaDTO = null;
//		FiltroAgendaLocal filtroAgendaEjb = null;
//		
//		filtroAgendaEjb = getFiltroAgenda(codigoAgencia,tica,puntoVenta,segmento);		
//		
//		if(filtroAgendaEjb!=null){
//			objFiltroDiaDTO = new FiltroDia(filtroAgendaEjb.getDiasMinimos(), filtroAgendaEjb.getDiasMaximos(),
//															  				  filtroAgendaEjb.getDuracion().intValue());		
//		}else{
//			// TODO Verificar que pasa cuando no se encuentra filtro 
//			objFiltroDiaDTO = new FiltroDia(new Integer(1),new Integer(2),2);
//		}		
		return objFiltroDiaDTO;
	}
	
	
	
//	private static FiltroAgendaLocal getFiltroAgenda(String codigoAgencia, String tica, String puntoVenta, String segmento){
//		
//			try {
//				FiltroAgendaLocalHome home = (FiltroAgendaLocalHome) HomeFactory.getHome(FiltroAgendaLocalHome.JNDI_NAME);
//				return home.findCombinacion(codigoAgencia,new Long(segmento),puntoVenta,tica);
//			} catch (Exception e) {
//								
//			}
//			return null;
//	}
	
	

}
