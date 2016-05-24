//- Copyright Notice
//-----------------------------------------------------------------------
// (C) Copyright 2007 Telefonica Colombia, http://www.telefonica.com.co
// Bogota, Colombia All Rights Reserved
// THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF Telefonica Colombia
// The copyright notice above does not evidence any actual or intended
// publication of such source code.
//
// $Id: TR021S.java,v 1.1 2011/03/30 18:24:43 lfmartinez Exp $
//-----------------------------------------------------------------------

package co.com.telefonica.atiempo.interfaces.atiempo;

/**
 * @author gminos
 * @version $Revision: 1.1 $
 */
public class TR021S extends ResponseHeader{
	private boolean response;
	private long atisRequestNumber;
	
	public long getAtisRequestNumber() {
		return atisRequestNumber;
	}

	public void setAtisRequestNumber(long l) {
		atisRequestNumber = l;
	}

	public int hashCode(){
		return (int)atisRequestNumber;
	}
	
	public boolean equals(Object arg0) {
		if (arg0 instanceof TR021S) {
			TR021S other = (TR021S) arg0;
			return super.equals(arg0)
				&& response == other.response
				&& atisRequestNumber == other.atisRequestNumber;
		}
		return false;
	}

	/**
	 * @return
	 */
	public boolean isResponse() {
		return response;
	}

	/**
	 * @param b
	 */
	public void setResponse(boolean b) {
		response = b;
	}

}
