//- Copyright Notice
//-----------------------------------------------------------------------
// (C) Copyright 2007 Telefonica Colombia, http://www.telefonica.com.co
// Bogota, Colombia All Rights Reserved
// THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF Telefonica Colombia
// The copyright notice above does not evidence any actual or intended
// publication of such source code.
//
// $Id: TR011S.java,v 1.1 2011/03/30 18:24:04 lfmartinez Exp $
//-----------------------------------------------------------------------

package co.com.telefonica.atiempo.interfaces.atiempo;

/**
 * @author ylapchik
 * @version $Revision: 1.1 $
 */
public class TR011S extends ResponseHeader {
	private boolean response;
	private long atisRequestNumber;
	private long typeError;
	
	
	public int hashCode() {
		return response?0:1;
	}
	public boolean equals(Object arg0) {
		if (arg0 instanceof TR011S) {
			TR011S other = (TR011S) arg0;
			return super.equals(arg0)
				&& typeError == other.typeError
				&& response == other.response
				&& atisRequestNumber == other.atisRequestNumber
				;
		}
		return false;
	}

	public boolean isResponse() {
		return response;
	}

	public void setResponse(boolean b) {
		response = b;
	}

	public long getAtisRequestNumber() {
		return atisRequestNumber;
	}

	public void setAtisRequestNumber(long l) {
		atisRequestNumber = l;
	}

	
	public long getTypeError() {
		return typeError;
	}

	
	public void setTypeError(long i) {
		typeError = i;
	}

}
