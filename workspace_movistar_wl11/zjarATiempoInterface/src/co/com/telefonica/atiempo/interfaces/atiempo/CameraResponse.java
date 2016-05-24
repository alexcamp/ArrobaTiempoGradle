/*
 * Creado el 14/12/2012
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package co.com.telefonica.atiempo.interfaces.atiempo;

import java.io.Serializable;

/**
 * @author cacano
 * 
 * TODO Para cambiar la plantilla de este comentario generado, vaya a Ventana -
 * Preferencias - Java - Estilo de código - Plantillas de código
 */
public class CameraResponse implements Serializable {
	private String cameraCode;

	private String cameraSerial;

	private String cameraDescription;

	private Integer cameraState;

	/**
	 * @param cameraCode
	 *            El cameraCode a establecer.
	 */
	public void setCameraCode(String cameraCode) {
		this.cameraCode = cameraCode;
	}

	/**
	 * @return Devuelve cameraCode.
	 */
	public String getCameraCode() {
		return cameraCode;
	}

	/**
	 * @param cameraSerial
	 *            El cameraSerial a establecer.
	 */
	public void setCameraSerial(String cameraSerial) {
		this.cameraSerial = cameraSerial;
	}

	/**
	 * @return Devuelve cameraSerial.
	 */
	public String getCameraSerial() {
		return cameraSerial;
	}

	/**
	 * @param cameraState
	 *            El cameraState a establecer.
	 */
	public void setCameraState(Integer cameraState) {
		this.cameraState = cameraState;
	}

	/**
	 * @return Devuelve cameraState.
	 */
	public Integer getCameraState() {
		return cameraState;
	}

	/**
	 * @param cameraDescription El cameraDescription a establecer.
	 */
	public void setCameraDescription(String cameraDescription) {
		this.cameraDescription = cameraDescription;
	}

	/**
	 * @return Devuelve cameraDescription.
	 */
	public String getCameraDescription() {
		return cameraDescription;
	}
}