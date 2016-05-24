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
public class TR709S extends RequestHeaderAgendaSC {
	private String idSourceSystem;

	private String idSchedule;

	private long clientCode;

	private String clientNumber;

	private Collection cameras;//Collection type: cameraRequest

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
	 * @param clientCode
	 *            El clientCode a establecer.
	 */
	public void setClientCode(long clientCode) {
		this.clientCode = clientCode;
	}

	/**
	 * @return Devuelve clientCode.
	 */
	public long getClientCode() {
		return clientCode;
	}

	/**
	 * @param clientNumber
	 *            El clientNumber a establecer.
	 */
	public void setClientNumber(String clientNumber) {
		this.clientNumber = clientNumber;
	}

	/**
	 * @return Devuelve clientNumber.
	 */
	public String getClientNumber() {
		return clientNumber;
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