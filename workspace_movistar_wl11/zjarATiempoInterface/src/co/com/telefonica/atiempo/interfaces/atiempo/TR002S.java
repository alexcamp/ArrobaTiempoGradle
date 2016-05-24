//- Copyright Notice
//-----------------------------------------------------------------------
// (C) Copyright 2007 Telefonica Colombia, http://www.telefonica.com.co
// Bogota, Colombia All Rights Reserved
// THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF Telefonica Colombia
// The copyright notice above does not evidence any actual or intended
// publication of such source code.
//
// $Id: TR002S.java,v 1.1 2011/03/30 18:23:29 lfmartinez Exp $
//-----------------------------------------------------------------------

package co.com.telefonica.atiempo.interfaces.atiempo;

/**
 * @author ylapchik
 * @version $Revision: 1.1 $
 */
public class TR002S extends ResponseHeader{
	private boolean response;
	private long atisRequestNumber;
	private long typeError;

	public long getAtisRequestNumber() {
		return atisRequestNumber;
	}

	public boolean isResponse() {
		return response;
	}

	public void setAtisRequestNumber(long l) {
		atisRequestNumber = l;
	}

	public void setResponse(boolean b) {
		response = b;
	}
	public int hashCode() {
		return (int)atisRequestNumber;
	}
	public boolean equals(Object arg0) {
		if (arg0 instanceof TR002S) {
			TR002S other = (TR002S) arg0;
			return super.equals(arg0) &&
			response == other.response &&
			typeError == other.typeError&& 
			atisRequestNumber == other.atisRequestNumber;
		}
		return false;
	}

	
	public long getTypeError() {
		return typeError;
	}


	public void setTypeError(long i) {
		typeError = i;
	}

}
