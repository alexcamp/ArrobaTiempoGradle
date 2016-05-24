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
public class TR056S extends ResponseHeaderAgendaSC {
	private String atiempoRequestNumber;

	private String atisRequestNumber;

	private String customerCode;

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
	 * @param customerCode
	 *            El customerCode a establecer.
	 */
	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}

	/**
	 * @return Devuelve customerCode.
	 */
	public String getCustomerCode() {
		return customerCode;
	}

}