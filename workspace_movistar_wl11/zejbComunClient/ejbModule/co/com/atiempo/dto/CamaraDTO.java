package co.com.atiempo.dto;

/**
 * @author cacano
 * 
 * Artefacto DTO para registro tabla atiempo.camara
 */
public class CamaraDTO {
	private Long petiNumero;

	private String cameraCode;

	private String cameraType;

	private String cameraSerial;

	private String cameraBrand;

	private String cameraDescription;

	private Integer cameraState;

	private String cameraModel;

	/**
	 * @param petiNumero
	 *            El petiNumero a establecer.
	 */
	public void setPetiNumero(Long petiNumero) {
		this.petiNumero = petiNumero;
	}

	/**
	 * @return Devuelve petiNumero.
	 */
	public Long getPetiNumero() {
		return petiNumero;
	}

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
	 * @param cameraDescription
	 *            El cameraDescription a establecer.
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
}