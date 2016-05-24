/*
 * Creado el Feb 3, 2005
 *
 */
package com.telefonica_chile.atiempo.actividades;

import java.io.Serializable;
import java.util.Map;

/**
 * @author Pa-T! (Richard Andreu)
 *
 */
public interface IProceso extends Serializable {
	public void iniciar() throws TnProcesoExcepcion;
	public String getIdInstancia();
	/**
	 * @return El id del TEMPLATE WF correspondiente a este proceso
	 */
	public String getIdProceso();
	public Map getDatos();
	public abstract void preInicio() throws TnProcesoExcepcion;
	public abstract void onInicio() throws TnProcesoExcepcion;

	public String getNombreEstructuraDatos();	
	
	/**
	 * @param datos
	 */
	public void setDatos(Map datos);
}
