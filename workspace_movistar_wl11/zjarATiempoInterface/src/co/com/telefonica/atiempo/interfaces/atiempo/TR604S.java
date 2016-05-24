//- Copyright Notice
//-----------------------------------------------------------------------
//(C) Copyright 2007 Telefonica Colombia, http://www.telefonica.com.co
//Bogota, Colombia All Rights Reserved
//THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF Telefonica Colombia
//The copyright notice above does not evidence any actual or intended
//publication of such source code.
//
//$Id: TR0604S.java Creado el 19/02/2014 dcardena Exp $
//-----------------------------------------------------------------------

package co.com.telefonica.atiempo.interfaces.atiempo;

import co.com.telefonica.atiempo.util.EqualityUtilities;

/**
 * @author dcardena
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class TR604S extends ResponseHeader{
	private String atiempoRequestNumber;
	private String errorCode;
	private String errorCodeMessage;
	
	public boolean equals(Object arg0) {
		if (arg0 instanceof TR604S) {
			TR604S other = (TR604S) arg0;
			return super.equals(arg0)
				&& EqualityUtilities.equals (atiempoRequestNumber, other.atiempoRequestNumber)
				&& EqualityUtilities.equals (errorCode, other.errorCode)
				&& EqualityUtilities.equals (errorCodeMessage, other.errorCodeMessage);
			}
		return false;
	}
	
	
	/**
	 * @return Devuelve atiempoRequestNumber.
	 */
	public String getAtiempoRequestNumber() {
		return atiempoRequestNumber;
	}
	/**
	 * @param atiempoRequestNumber El atiempoRequestNumber a establecer.
	 */
	public void setAtiempoRequestNumber(String atiempoRequestNumber) {
		this.atiempoRequestNumber = atiempoRequestNumber;
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
	/**
	 * @return Devuelve errorCodeMessage.
	 */
	public String getErrorCodeMessage() {
		return errorCodeMessage;
	}
	/**
	 * @param errorCodeMessage El errorCodeMessage a establecer.
	 */
	public void setErrorCodeMessage(String errorCodeMessage) {
		this.errorCodeMessage = errorCodeMessage;
	}
}
