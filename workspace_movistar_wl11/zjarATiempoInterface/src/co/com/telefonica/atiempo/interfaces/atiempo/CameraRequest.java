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
public class CameraRequest implements Serializable {
	private String cameraCode;

	private String cameraBrand;

	private String cameraType;

	private String cameraSerial;

	private String cameraModel;

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
	 * @param cameraBrand
	 *            El cameraBrand a establecer.
	 */
	public void setCameraBrand(String cameraBrand) {
		this.cameraBrand = cameraBrand;
	}

	/**
	 * @return Devuelve cameraBrand.
	 */
	public String getCameraBrand() {
		return cameraBrand;
	}

	/**
	 * @param cameraType
	 *            El cameraType a establecer.
	 */
	public void setCameraType(String cameraType) {
		this.cameraType = cameraType;
	}

	/**
	 * @return Devuelve cameraType.
	 */
	public String getCameraType() {
		return cameraType;
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
	 * @param cameraModel
	 *            El cameraModel a establecer.
	 */
	public void setCameraModel(String cameraModel) {
		this.cameraModel = cameraModel;
	}

	/**
	 * @return Devuelve cameraModel.
	 */
	public String getCameraModel() {
		return cameraModel;
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
}