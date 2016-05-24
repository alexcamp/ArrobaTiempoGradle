package co.com.telefonica.atiempo.interfaces.atiempo;

import co.com.telefonica.atiempo.util.EqualityUtilities;

/**
 * @author joeroa
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TR613E extends RequestHeaderAgendaSC {
	private Long atiempoRequestNumber;
	private Long atisRequestNumber;
	private String operacion;
	private String numeroTelefonoFijo;
	private String numeroTelefonoMovil;
	private String codigoPlan;
	private String nuevoTelefonoFijo;
	
	public int hashCode() {
		return atiempoRequestNumber.hashCode();
	}
	
	public boolean equals(Object arg0) {
		if (arg0 instanceof TR613E) {
			TR613E other = (TR613E) arg0;
			return super.equals(arg0)
				&& EqualityUtilities.equals(atiempoRequestNumber, other.atiempoRequestNumber)
				&& EqualityUtilities.equals(atisRequestNumber, other.atisRequestNumber);
			}
		return false;
	}
	
	public Long getAtiempoRequestNumber() {
		return atiempoRequestNumber;
	}
	public void setAtiempoRequestNumber(Long atiempoRequestNumber) {
		this.atiempoRequestNumber = atiempoRequestNumber;
	}
	public Long getAtisRequestNumber() {
		return atisRequestNumber;
	}
	public void setAtisRequestNumber(Long atisRequestNumber) {
		this.atisRequestNumber = atisRequestNumber;
	}
	/**
	 * @return Devuelve codigoPlan.
	 */
	public String getCodigoPlan() {
		return codigoPlan;
	}
	/**
	 * @param codigoPlan El codigoPlan a establecer.
	 */
	public void setCodigoPlan(String codigoPlan) {
		this.codigoPlan = codigoPlan;
	}
	/**
	 * @return Devuelve nuevoNumeroTelefonoFijo.
	 */
	public String getNuevoTelefonoFijo() {
		return nuevoTelefonoFijo;
	}
	/**
	 * @param nuevoNumeroTelefonoFijo El nuevoNumeroTelefonoFijo a establecer.
	 */
	public void setNuevoTelefonoFijo(String nuevoTelefonoFijo) {
		this.nuevoTelefonoFijo = nuevoTelefonoFijo;
	}
	/**
	 * @return Devuelve numeroTelefonoFijo.
	 */
	public String getNumeroTelefonoFijo() {
		return numeroTelefonoFijo;
	}
	/**
	 * @param numeroTelefonoFijo El numeroTelefonoFijo a establecer.
	 */
	public void setNumeroTelefonoFijo(String numeroTelefonoFijo) {
		this.numeroTelefonoFijo = numeroTelefonoFijo;
	}
	/**
	 * @return Devuelve numeroTelefonoMovil.
	 */
	public String getNumeroTelefonoMovil() {
		return numeroTelefonoMovil;
	}
	/**
	 * @param numeroTelefonoMovil El numeroTelefonoMovil a establecer.
	 */
	public void setNumeroTelefonoMovil(String numeroTelefonoMovil) {
		this.numeroTelefonoMovil = numeroTelefonoMovil;
	}
	/**
	 * @return Devuelve operacion.
	 */
	public String getOperacion() {
		return operacion;
	}
	/**
	 * @param operacion El operacion a establecer.
	 */
	public void setOperacion(String operacion) {
		this.operacion = operacion;
	}
}
