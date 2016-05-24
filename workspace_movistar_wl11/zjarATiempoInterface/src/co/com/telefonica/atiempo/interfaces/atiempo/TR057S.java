/*
 * Creado el 14/12/2012
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package co.com.telefonica.atiempo.interfaces.atiempo;

import java.util.Collection;

/**
 * @author cacano
 * 
 * TODO Para cambiar la plantilla de este comentario generado, vaya a Ventana -
 * Preferencias - Java - Estilo de código - Plantillas de código
 */
public class TR057S extends ResponseHeaderAgendaSC {
	private String atiempoRequestNumber;

	private String atisRequestNumber;

	private Collection cameras;

	/**
	 * @param atiempoRequestNumber
	 *            El atiempoRequestNumber a establecer.
	 */
	public void setAtiempoRequestNumber(String atiempoRequestNumber) {
		this.atiempoRequestNumber = atiempoRequestNumber;
	}

	/**
	 * @return Devuelve atiempoRequestNumber.
	 */
	public String getAtiempoRequestNumber() {
		return atiempoRequestNumber;
	}

	/**
	 * @param atisRequestNumber
	 *            El atisRequestNumber a establecer.
	 */
	public void setAtisRequestNumber(String atisRequestNumber) {
		this.atisRequestNumber = atisRequestNumber;
	}

	/**
	 * @return Devuelve atisRequestNumber.
	 */
	public String getAtisRequestNumber() {
		return atisRequestNumber;
	}

	/**
	 * @param cameras
	 *            El cameras a establecer.
	 */
	public void setCameras(Collection cameras) {
		this.cameras = cameras;
	}

	/**
	 * @return Devuelve cameras.
	 */
	public Collection getCameras() {
		return cameras;
	}
}