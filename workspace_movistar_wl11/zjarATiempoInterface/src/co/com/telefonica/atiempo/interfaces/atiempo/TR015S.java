//- Copyright Notice
//-----------------------------------------------------------------------
// (C) Copyright 2007 Telefonica Colombia, http://www.telefonica.com.co
// Bogota, Colombia All Rights Reserved
// THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF Telefonica Colombia
// The copyright notice above does not evidence any actual or intended
// publication of such source code.
//
// $Id: TR015S.java,v 1.1 2011/03/30 18:24:04 lfmartinez Exp $
//-----------------------------------------------------------------------

package co.com.telefonica.atiempo.interfaces.atiempo;

import co.com.telefonica.atiempo.util.EqualityUtilities;

/**
 * @author ylapchik
 * @version $Revision: 1.1 $
 */
public class TR015S extends ResponseHeader{
	private long atisRequestNumber;
	private String errorCode;
	private String errorCodeMessage;

	public long getAtisRequestNumber() {
		return atisRequestNumber;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public String getErrorCodeMessage() {
		return errorCodeMessage;
	}

	public void setAtisRequestNumber(long l) {
		atisRequestNumber = l;
	}

	public void setErrorCode(String string) {
		errorCode = string;
	}

	public void setErrorCodeMessage(String string) {
		errorCodeMessage = string;
	}
	public int hashCode(){
		return errorCode.hashCode();
	}
	public boolean equals(Object arg0){
		if (arg0 instanceof TR015S) {
			TR015S other = (TR015S) arg0;
			return super.equals(arg0)
				&& atisRequestNumber == other.atisRequestNumber
				&& EqualityUtilities.equals(errorCode, other.errorCode)
				&& EqualityUtilities.equals(errorCodeMessage, other.errorCodeMessage);
		}
		return false;
	}

}
