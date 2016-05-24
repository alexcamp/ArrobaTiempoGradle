package com.telefonica_chile.bandeja.torreControl;

import java.io.Serializable;
import java.util.ArrayList;

public class CuadroMandoDTO implements Serializable{
	
	private Long actividadTC = null;
	private String nombreActividad = null;
	private int cuadro = 0;
	private int posicion = 0;
	private int link = 0;
	private int subTotal = 0;
	private Integer appID = null;

	private ArrayList listaActividades = null;

	public void addActividad(Long idActividad) {
		if ( idActividad == null )
			return;

		if ( listaActividades == null )
			listaActividades = new ArrayList();
		
		listaActividades.add( idActividad );
	}

	public ArrayList getListaActividades() {
		return listaActividades;
	}
	
	
	/**
	 * @return
	 */
	public Long getActividadTC() {
		return actividadTC;
	}

	/**
	 * @return
	 */
	public Integer getAppID() {
		return appID;
	}

	/**
	 * @return
	 */
	public int getCuadro() {
		return cuadro;
	}

	/**
	 * @return
	 */
	public int getLink() {
		return link;
	}

	/**
	 * @return
	 */
	public String getNombreActividad() {
		return nombreActividad;
	}

	/**
	 * @return
	 */
	public int getPosicion() {
		return posicion;
	}

	/**
	 * @return
	 */
	public int getSubTotal() {
		return subTotal;
	}

	/**
	 * @param long1
	 */
	public void setActividadTC(Long long1) {
		actividadTC = long1;
	}

	/**
	 * @param integer
	 */
	public void setAppID(Integer integer) {
		appID = integer;
	}

	/**
	 * @param i
	 */
	public void setCuadro(int i) {
		cuadro = i;
	}

	/**
	 * @param i
	 */
	public void setLink(int i) {
		link = i;
	}

	/**
	 * @param string
	 */
	public void setNombreActividad(String string) {
		nombreActividad = string;
	}

	/**
	 * @param i
	 */
	public void setPosicion(int i) {
		posicion = i;
	}

	/**
	 * @param i
	 */
	public void setSubTotal(int i) {
		subTotal = i;
	}

}
