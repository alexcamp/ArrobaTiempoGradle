package co.com.telefonica.atiempo.interfaces.atiempo;

/**
 * @author cacano
 * 
 * Representa la tr-614-e para Recarga fija Móvil
 */
public class TR614E extends RequestHeaderAgendaSC {
	private Long atiempoRequestNumber;
	private Long atisRequestNumber;
	private String operacion;
	private String fijoPhoneNumber;
	private String movilPhoneNumber;
	private String codigoPlan;
	private String valor;
	private String diaRecarga;

	/**
	 * @param fatherPhoneNumber
	 *            El fijoPhoneNumber a establecer.
	 */
	public void setFijoPhoneNumber(String fijoPhoneNumber) {
		this.fijoPhoneNumber = fijoPhoneNumber;
	}

	/**
	 * @return Devuelve fijoPhoneNumber.
	 */
	public String getFijoPhoneNumber() {
		return fijoPhoneNumber;
	}

	/**
	 * @param atisRequestNumber
	 *            El atisRequestNumber a establecer.
	 */
	public void setAtisRequestNumber(Long atisRequestNumber) {
		this.atisRequestNumber = atisRequestNumber;
	}

	/**
	 * @return Devuelve atisRequestNumber.
	 */
	public Long getAtisRequestNumber() {
		return atisRequestNumber;
	}

	/**
	 * @param atiempoRequestNumber
	 *            El atiempoRequestNumber a establecer.
	 */
	public void setAtiempoRequestNumber(Long atiempoRequestNumber) {
		this.atiempoRequestNumber = atiempoRequestNumber;
	}

	/**
	 * @return Devuelve atiempoRequestNumber.
	 */
	public Long getAtiempoRequestNumber() {
		return atiempoRequestNumber;
	}

	/**
	 * @param celufjioPhoneNumber
	 *            El movilPhoneNumber a establecer.
	 */
	public void setMovilPhoneNumber(String movilPhoneNumber) {
		this.movilPhoneNumber = movilPhoneNumber;
	}

	/**
	 * @return Devuelve movilPhoneNumber.
	 */
	public String getMovilPhoneNumber() {
		return movilPhoneNumber;
	}

	/**
	 * @param psCode
	 *            El psCode a establecer.
	 */
	public void setCodigoPlan(String codigoPlan) {
		this.codigoPlan = codigoPlan;
	}

	/**
	 * @return Devuelve psCode.
	 */
	public String getCodigoPlan() {
		return codigoPlan;
	}

	/**
	 * @param ocCode
	 *            El ocCode a establecer.
	 */
	public void setOperacion(String operacion) {
		this.operacion = operacion;
	}

	/**
	 * @return Devuelve ocCode.
	 */
	public String getOperacion() {
		return operacion;
	}
	
	/**
	 * @param ocCode
	 *            El ocCode a establecer.
	 */
	public void setValor(String valor) {
		this.valor = valor;
	}

	/**
	 * @return Devuelve ocCode.
	 */
	public String getValor() {
		return valor;
	}
	
	/**
	 * @param ocCode
	 *            El ocCode a establecer.
	 */
	public void setDiaRecarga(String diaRecarga) {
		this.diaRecarga = diaRecarga;
	}

	/**
	 * @return Devuelve ocCode.
	 */
	public String getDiaRecarga() {
		return diaRecarga;
	}
}