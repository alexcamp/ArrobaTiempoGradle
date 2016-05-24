//- Copyright Notice
//-----------------------------------------------------------------------
//(C) Copyright 2007 Telefonica Colombia, http://www.telefonica.com.co
//Bogota, Colombia All Rights Reserved
//THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF Telefonica Colombia
//The copyright notice above does not evidence any actual or intended
//publication of such source code.
//
//$Id: TR518S.java 2013/09/23 14:46:28 dcardena Exp $
//-----------------------------------------------------------------------

package co.com.telefonica.atiempo.interfaces.atiempo;

import co.com.telefonica.atiempo.util.EqualityUtilities;

/**
* @author dcardena
*
*/
public class TR603S extends ResponseHeader{
	private long atiempoRequestNumber;
	private long errorCode;
	private String errorCodeMessage;
	
	public boolean equals(Object arg0) {
		if (arg0 instanceof TR603S) {
			TR603S other = (TR603S) arg0;
			return super.equals(arg0)
				&& (atiempoRequestNumber == other.atiempoRequestNumber)
				&& (errorCode == other.errorCode)
				&& EqualityUtilities.equals(errorCodeMessage, other.errorCodeMessage);
			}
		return false;
	}
	
	/**
	 * @return Devuelve x.
	 */
	public String getErrorCodeMessage() {
		return errorCodeMessage;
	}
	/**
	 * @param x El x a establecer.
	 */
	public void setErrorCodeMessage(String y) {
		this.errorCodeMessage = y;
	}
	/**
	 * @return Devuelve atiempoRequestNumber.
	 */
	public long getAtiempoRequestNumber() {
		return atiempoRequestNumber;
	}
	/**
	 * @param atiempoRequestNumber El atiempoRequestNumber a establecer.
	 */
	public void setAtiempoRequestNumber(long atiempoRequestNumber) {
		this.atiempoRequestNumber = atiempoRequestNumber;
	}
	/**
	 * @return Devuelve errorCode.
	 */
	public long getErrorCode() {
		return errorCode;
	}
	/**
	 * @param errorCode El errorCode a establecer.
	 */
	public void setErrorCode(long errorCode) {
		this.errorCode = errorCode;
	}
}
