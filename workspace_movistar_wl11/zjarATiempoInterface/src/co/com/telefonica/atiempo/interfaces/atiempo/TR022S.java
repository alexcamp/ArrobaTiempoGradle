//- Copyright Notice
//-----------------------------------------------------------------------
// (C) Copyright 2007 Telefonica Colombia, http://www.telefonica.com.co
// Bogota, Colombia All Rights Reserved
// THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF Telefonica Colombia
// The copyright notice above does not evidence any actual or intended
// publication of such source code.
//
// $Id: TR022S.java,v 1.1 2011/03/30 18:24:44 lfmartinez Exp $
//-----------------------------------------------------------------------

package co.com.telefonica.atiempo.interfaces.atiempo;

import java.util.Collection;

import co.com.telefonica.atiempo.util.EqualityUtilities;

/**
 * @author gmarcone
 * @version $Revision: 1.1 $
 */
public class TR022S extends ResponseHeader{

	private String errorCodeMessage;
	private long errorCode;
	private long atisRequestNumber;
	private Collection modems;
	
	public int hashCode(){
		return (int)atisRequestNumber;
	}
	
	public boolean equals(Object arg0) {
		if (arg0 instanceof TR022S) {
			TR022S other = (TR022S) arg0;
			return super.equals(arg0)
				&& errorCode == other.errorCode
				&& EqualityUtilities.equals(errorCodeMessage, other.errorCodeMessage)
				&& EqualityUtilities.equals(modems, other.modems)
				&& atisRequestNumber == other.atisRequestNumber;
		}
		return false;
	}

	public long getAtisRequestNumber() {
		return atisRequestNumber;
	}

	public long getErrorCode() {
		return errorCode;
	}

	public String getErrorCodeMessage() {
		return errorCodeMessage;
	}

	public Collection getModems() {
		return modems;
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

	public void setModems(Collection collection) {
		modems = collection;
	}
}
