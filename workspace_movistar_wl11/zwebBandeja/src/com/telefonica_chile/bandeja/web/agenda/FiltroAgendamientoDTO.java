/*
 * Created on Mar 3, 2005
 * 
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.telefonica_chile.bandeja.web.agenda;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;


public class FiltroAgendamientoDTO {
	
	Long idSegmento = null;
	Integer idGrupoSegmento = null;
	String codigoAgencia = null;
	String plantaComercial = null;
	String armario = "";
	String ptoVenta = "";
	String tica = "";

	Integer tiempoMinimo = null;
	Integer tiempoMaximo = null;
	
	Date fechaMinimaExcepcion = null;
	Date fechaMinimaLlamada = null;
	Date fechaMinimaCita = null;
	Date fechaMaxima = null;
	
	String tipoTrabajo = null;
	
	boolean hayTerreno = false;

	boolean soloTecnico = false;
	
	// Lista Familia PS
	ArrayList listaPS = new ArrayList();

	public FiltroAgendamientoDTO() {
	}
	/**
	 * @return
	 */
	public String getArmario() {
		return armario;
	}

	/**
	 * @return
	 */
	public String getCodigoAgencia() {
		return codigoAgencia;
	}

	/**
	 * @return
	 */
	public Integer getIdGrupoSegmento() {
		return idGrupoSegmento;
	}

	/**
	 * @return
	 */
	public Long getIdSegmento() {
		return idSegmento;
	}

	/**
	 * @return
	 */
	public ArrayList getListaPS() {
		return listaPS;
	}

	/**
	 * @return
	 */
	public String getPlantaComercial() {
		return plantaComercial;
	}

	/**
	 * @return
	 */
	public String getPtoVenta() {
		return ptoVenta;
	}

	/**
	 * @return
	 */
	public String getTica() {
		return tica;
	}

	/**
	 * @return
	 */
	public Integer getTiempoMaximo() {
		return tiempoMaximo;
	}

	/**
	 * @return
	 */
	public Integer getTiempoMinimo() {
		return tiempoMinimo;
	}

	/**
	 * @param string
	 */
	public void setArmario(String string) {
		armario = string;
	}

	/**
	 * @param string
	 */
	public void setCodigoAgencia(String string) {
		codigoAgencia = string;
	}

	/**
	 * @param integer
	 */
	public void setIdGrupoSegmento(Integer integer1) {
		idGrupoSegmento = integer1;
	}

	/**
	 * @param integer
	 */
	public void setIdSegmento(Long long1) {
		idSegmento = long1;
	}

	/**
	 * @param list
	 */
	public void setListaPS(ArrayList list) {
		listaPS = list;
	}

	/**
	 * @param string
	 */
	public void setPlantaComercial(String string) {
		plantaComercial = string;
	}

	/**
	 * @param string
	 */
	public void setPtoVenta(String string) {
		ptoVenta = string;
	}

	/**
	 * @param string
	 */
	public void setTica(String string) {
		tica = string;
	}

	/**
	 * @param integer
	 */
	public void setTiempoMaximo(Integer integer) {
		tiempoMaximo = integer;
	}

	/**
	 * @param integer
	 */
	public void setTiempoMinimo(Integer integer) {
		tiempoMinimo = integer;
	}

	public void addPS( PSAgendamientoDTO ps ) {
		if (ps == null)
			return;

		listaPS.add( ps );
	}

	public boolean esTerreno() {
		if ( hayTerreno )
			return true;

		for (int i=0; listaPS!=null && i<listaPS.size(); i++) {
			PSAgendamientoDTO ps = (PSAgendamientoDTO) listaPS.get(i);
			if ( ps == null || !ps.isPsTerreno() )
				continue;
			return true;
		}
		return false;
	}

	public ArrayList getListaFamiliaPS() {
		ArrayList aux = new ArrayList(); 
		HashMap x = new HashMap();
		for (int i=0; listaPS!=null && i<listaPS.size(); i++) {
			PSAgendamientoDTO ps = (PSAgendamientoDTO) listaPS.get(i);
			if ( ps == null || !ps.isPsTerreno() )
				continue; 

			if ( ps.getIdFamiliaPS() == null || ps.getIdFamiliaPS().intValue() == 0)
				continue;
			if ( x.get( ps.getIdFamiliaPS() ) == null )
				aux.add( ps.getIdFamiliaPS() );
			x.put( ps.getIdFamiliaPS(), "1" );
		}
		return aux;
	}

	public ArrayList getListaOpComercial() {
		ArrayList aux = new ArrayList(); 
		HashMap x = new HashMap();
		for (int i=0; listaPS!=null && i<listaPS.size(); i++) {
			PSAgendamientoDTO ps = (PSAgendamientoDTO) listaPS.get(i);
			if ( ps == null || !ps.isPsTerreno() )
				continue;

			if ( x.get( ps.getOpComercial() ) == null )
				aux.add( ps.getOpComercial() );
			x.put( ps.getOpComercial(), "1" );
		}
		
		return aux;
	}

	/**
	 * @return
	 */
	public boolean isSoloTecnico() {
		return soloTecnico;
	}

	/**
	 * @param b
	 */
	public void setSoloTecnico(boolean b) {
		soloTecnico = b;
	}

	/**
	 * @return
	 */
	public Date getFechaMinimaCita() {
		return fechaMinimaCita;
	}

	/**
	 * @return
	 */
	public Date getFechaMinimaExcepcion() {
		return fechaMinimaExcepcion;
	}

	/**
	 * @return
	 */
	public Date getFechaMinimaLlamada() {
		return fechaMinimaLlamada;
	}

	/**
	 * @param date
	 */
	public void setFechaMinimaCita(Date date) {
		fechaMinimaCita = date;
	}

	/**
	 * @param date
	 */
	public void setFechaMinimaExcepcion(Date date) {
		fechaMinimaExcepcion = date;
	}

	/**
	 * @param date
	 */
	public void setFechaMinimaLlamada(Date date) {
		fechaMinimaLlamada = date;
	}

	/**
	 * @return
	 */
	public Date getFechaMaxima() {
		return fechaMaxima;
	}

	/**
	 * @param date
	 */
	public void setFechaMaxima(Date date) {
		fechaMaxima = date;
	}

	/**
	 * @return
	 */
	public String getTipoTrabajo() {
		return tipoTrabajo;
	}

	/**
	 * @param string
	 */
	public void setTipoTrabajo(String string) {
		tipoTrabajo = string;
	}

	/**
	 * @return
	 */
	public boolean isHayTerreno() {
		return hayTerreno;
	}

	/**
	 * @param b
	 */
	public void setHayTerreno(boolean b) {
		hayTerreno = b;
	}

}
