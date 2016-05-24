/*
 * Creado el 17/12/2012
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
public class TR709E extends ResponseHeaderAgendaSC {
	private String idSourceSystem;

	private String idSchedule;

	private String response;

	private Collection cameras;//Collection type: Camera response

	/**
	 * @param idSourceSystem
	 *            El idSourceSystem a establecer.
	 */
	public void setIdSourceSystem(String idSourceSystem) {
		this.idSourceSystem = idSourceSystem;
	}

	/**
	 * @return Devuelve idSourceSystem.
	 */
	public String getIdSourceSystem() {
		return idSourceSystem;
	}

	/**
	 * @param idSchedule
	 *            El idSchedule a establecer.
	 */
	public void setIdSchedule(String idSchedule) {
		this.idSchedule = idSchedule;
	}

	/**
	 * @return Devuelve idSchedule.
	 */
	public String getIdSchedule() {
		return idSchedule;
	}

	/**
	 * @param response
	 *            El response a establecer.
	 */
	public void setResponse(String response) {
		this.response = response;
	}

	/**
	 * @return Devuelve response.
	 */
	public String getResponse() {
		return response;
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