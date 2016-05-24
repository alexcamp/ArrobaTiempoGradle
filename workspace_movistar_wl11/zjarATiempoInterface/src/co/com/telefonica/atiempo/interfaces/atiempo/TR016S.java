//- Copyright Notice
//-----------------------------------------------------------------------
// (C) Copyright 2007 Telefonica Colombia, http://www.telefonica.com.co
// Bogota, Colombia All Rights Reserved
// THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF Telefonica Colombia
// The copyright notice above does not evidence any actual or intended
// publication of such source code.
//
// $Id: TR016S.java,v 1.1 2011/03/30 18:24:03 lfmartinez Exp $
//-----------------------------------------------------------------------

package co.com.telefonica.atiempo.interfaces.atiempo;

import java.util.Collection;

import co.com.telefonica.atiempo.util.EqualityUtilities;

/**
 * @author ylapchik
 * @version $Revision: 1.1 $
 */
public class TR016S extends ResponseHeader {
	
	private long atisRequestNumber;
	private Collection decos;
	private Collection cardSerial;
	private long errorCode;
	private String errorCodeMessage;

	public long getAtisRequestNumber() {
		return atisRequestNumber;
	}
	public Collection getCardSerial() {
		return cardSerial;
	}
	public Collection getDecos() {
		return decos;
	}
	public void setAtisRequestNumber(long l) {
		atisRequestNumber = l;
	}
	public void setCardSerial(Collection collection) {
		cardSerial = collection;
	}
	public void setDecos(Collection collection) {
		decos = collection;
	}
	public long getErrorCode() {
		return errorCode;
	}
	public String getErrorCodeMessage() {
		return errorCodeMessage;
	}
	public void setErrorCode(long l) {
		errorCode = l;
	}
	public void setErrorCodeMessage(String string) {
		errorCodeMessage = string;
	}
	
	public int hashCode(){
		return (int)atisRequestNumber;
	}
	
	public boolean equals(Object arg0){
		if (arg0 instanceof TR016S) {
			TR016S other = (TR016S) arg0;
			return super.equals(arg0) &&
				atisRequestNumber == other.atisRequestNumber &&
				EqualityUtilities.equals(decos, other.decos) &&
				EqualityUtilities.equals(cardSerial, other.cardSerial) &&
				errorCode== other.errorCode &&
				EqualityUtilities.equals(errorCodeMessage, other.errorCodeMessage);
		}
		return false;
	}

}
