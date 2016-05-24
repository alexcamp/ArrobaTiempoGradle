//- Copyright Notice
//-----------------------------------------------------------------------
// (C) Copyright 2007 Telefonica Colombia, http://www.telefonica.com.co
// Bogota, Colombia All Rights Reserved
// THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF Telefonica Colombia
// The copyright notice above does not evidence any actual or intended
// publication of such source code.
//
// $Id: TR018S.java,v 1.1 2011/03/30 18:24:44 lfmartinez Exp $
//-----------------------------------------------------------------------

package co.com.telefonica.atiempo.interfaces.atiempo;

import co.com.telefonica.atiempo.util.EqualityUtilities;

/**
 * @author ylapchik
 * @version $Revision: 1.1 $
 */
public class TR018S extends ResponseHeader{
	private long atisRequestNumber;
	private long errorCode;
	private String errorCodeMessage;
	

	public long getAtisRequestNumber() {
		return atisRequestNumber;
	}

	public long getErrorCode() {
		return errorCode;
	}

	public String getErrorCodeMessage() {
		return errorCodeMessage;
	}

	public void setAtisRequestNumber(long l) {
		atisRequestNumber = l;
	}

	public void setErrorCode(long l) {
		errorCode = l;
	}

	public void setErrorCodeMessage(String string) {
		errorCodeMessage = string;
	}
	public int hashCode(){
		return errorCodeMessage.hashCode();
	}
	public boolean equals(Object arg0) {
		if (arg0 instanceof TR018S) {
			TR018S other = (TR018S) arg0;
			return super.equals(arg0)
				&& atisRequestNumber == other.atisRequestNumber
				&& errorCode==other.errorCode
				&& EqualityUtilities.equals(errorCodeMessage, other.errorCodeMessage);
		}
		return false;
	}

}
