/*
 * Creado el 27/11/2012
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package co.com.telefonica.atiempo.interfaces.atiempo;

/**
 * @author cacano
 * 
 * TODO Para cambiar la plantilla de este comentario generado, vaya a Ventana -
 * Preferencias - Java - Estilo de código - Plantillas de código
 */
public class TR056E extends RequestHeaderAgendaSC {
	private long atiempoRequestNumber;

	private long atisRequestNumber;

	private int commercialOperation;

	private String phoneNumber;

	private String clientName;

	private String city;

	private String fatherAccount;

	private String email;

	private String mobilePhone;

	private int psCode;

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
	 * @param commercialOperation
	 *            El commercialOperation a establecer.
	 */
	public void setCommercialOperation(int commercialOperation) {
		this.commercialOperation = commercialOperation;
	}

	/**
	 * @return Devuelve commercialOperation.
	 */
	public int getCommercialOperation() {
		return commercialOperation;
	}

	/**
	 * @param phoneNumber
	 *            El phoneNumber a establecer.
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * @return Devuelve phoneNumber.
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @param clientName
	 *            El clientName a establecer.
	 */
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	/**
	 * @return Devuelve clientName.
	 */
	public String getClientName() {
		return clientName;
	}

	/**
	 * @param city
	 *            El city a establecer.
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return Devuelve city.
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param fatherAccount
	 *            El fatherAccount a establecer.
	 */
	public void setFatherAccount(String fatherAccount) {
		this.fatherAccount = fatherAccount;
	}

	/**
	 * @return Devuelve fatherAccount.
	 */
	public String getFatherAccount() {
		return fatherAccount;
	}

	/**
	 * @param email
	 *            El email a establecer.
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return Devuelve email.
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param psCode
	 *            El psCode a establecer.
	 */
	public void setPsCode(int psCode) {
		this.psCode = psCode;
	}

	/**
	 * @return Devuelve psCode.
	 */
	public int getPsCode() {
		return psCode;
	}

	/**
	 * @param mobilePhone
	 *            El mobilePhone a establecer.
	 */
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	/**
	 * @return Devuelve mobilePhone.
	 */
	public String getMobilePhone() {
		return mobilePhone;
	}

}