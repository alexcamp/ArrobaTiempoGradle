package co.com.telefonica.atiempo.interfaces.atiempo;

/**
 * @author cacano
 * 
 * Representa la tr-612-e para Configuración Mediación Móvil
 */
public class TR612E extends RequestHeaderAgendaSC {
	private long atiempoRequestNumber;

	private long atisRequestNumber;

	private String fatherPhoneNumber;

	private String celuFijoPhoneNumber;

	private String psCode;

	private String ocCode;

	/**
	 * @param fatherPhoneNumber
	 *            El fatherPhoneNumber a establecer.
	 */
	public void setFatherPhoneNumber(String fatherPhoneNumber) {
		this.fatherPhoneNumber = fatherPhoneNumber;
	}

	/**
	 * @return Devuelve fatherPhoneNumber.
	 */
	public String getFatherPhoneNumber() {
		return fatherPhoneNumber;
	}

	/**
	 * @param atisRequestNumber
	 *            El atisRequestNumber a establecer.
	 */
	public void setAtisRequestNumber(long atisRequestNumber) {
		this.atisRequestNumber = atisRequestNumber;
	}

	/**
	 * @return Devuelve atisRequestNumber.
	 */
	public long getAtisRequestNumber() {
		return atisRequestNumber;
	}

	/**
	 * @param atiempoRequestNumber
	 *            El atiempoRequestNumber a establecer.
	 */
	public void setAtiempoRequestNumber(long atiempoRequestNumber) {
		this.atiempoRequestNumber = atiempoRequestNumber;
	}

	/**
	 * @return Devuelve atiempoRequestNumber.
	 */
	public long getAtiempoRequestNumber() {
		return atiempoRequestNumber;
	}

	/**
	 * @param celufjioPhoneNumber
	 *            El celufjioPhoneNumber a establecer.
	 */
	public void setCeluFijoPhoneNumber(String celufjioPhoneNumber) {
		this.celuFijoPhoneNumber = celufjioPhoneNumber;
	}

	/**
	 * @return Devuelve celufjioPhoneNumber.
	 */
	public String getCeluFijoPhoneNumber() {
		return celuFijoPhoneNumber;
	}

	/**
	 * @param psCode
	 *            El psCode a establecer.
	 */
	public void setPsCode(String psCode) {
		this.psCode = psCode;
	}

	/**
	 * @return Devuelve psCode.
	 */
	public String getPsCode() {
		return psCode;
	}

	/**
	 * @param ocCode
	 *            El ocCode a establecer.
	 */
	public void setOcCode(String ocCode) {
		this.ocCode = ocCode;
	}

	/**
	 * @return Devuelve ocCode.
	 */
	public String getOcCode() {
		return ocCode;
	}
}