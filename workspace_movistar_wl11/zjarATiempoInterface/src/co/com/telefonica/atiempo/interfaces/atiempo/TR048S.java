/*
 * Creado el 19/11/2010
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package co.com.telefonica.atiempo.interfaces.atiempo;

import co.com.telefonica.atiempo.util.EqualityUtilities;

/**
 * @author damartinezv
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class TR048S extends ResponseHeaderAgendaSC{
	private String atisRequestNumber;
	private String errorCode;
	
	public int hashCode(){
		return new Long(atisRequestNumber).intValue();
	}
	
	public boolean equals(Object arg0) {
		if (arg0 instanceof TR048S) {
			TR048S other = (TR048S) arg0;
			return super.equals(arg0)
			&& EqualityUtilities.equals(atisRequestNumber, other.atisRequestNumber)
		 	&& EqualityUtilities.equals(errorCode, other.errorCode);
		}
		return false;
	}
	


	/**
	 * @return Devuelve atisRequestNumber.
	 */
	public String getAtisRequestNumber() {
		return atisRequestNumber;
	}
	/**
	 * @param atisRequestNumber El atisRequestNumber a establecer.
	 */
	public void setAtisRequestNumber(String atisRequestNumber) {
		this.atisRequestNumber = atisRequestNumber;
	}
	/**
	 * @return Devuelve errorCode.
	 */
	public String getErrorCode() {
		return errorCode;
	}
	/**
	 * @param errorCode El errorCode a establecer.
	 */
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
}
