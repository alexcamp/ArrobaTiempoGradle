//- Copyright Notice
//-----------------------------------------------------------------------
// (C) Copyright 2007 Telefonica Colombia, http://www.telefonica.com.co
// Bogota, Colombia All Rights Reserved
// THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF Telefonica Colombia
// The copyright notice above does not evidence any actual or intended
// publication of such source code.
//
// $Id: TR023S.java,v 1.1 2011/03/30 18:24:44 lfmartinez Exp $
//-----------------------------------------------------------------------

package co.com.telefonica.atiempo.interfaces.atiempo;

import co.com.telefonica.atiempo.util.EqualityUtilities;

/**
 * @author gmarcone
 * @version $Revision: 1.1 $
 */
public class TR023S extends ResponseHeader{

	private long atisRequestNumber;
	private String cna;
	private String statusMessage;

	public int hashCode(){
		return (int)atisRequestNumber;
	}
	
	public boolean equals(Object arg0) {
		if (arg0 instanceof TR023S) {
			TR023S other = (TR023S) arg0;
			return super.equals(arg0)
				&& EqualityUtilities.equals(cna,other.cna)
				&& EqualityUtilities.equals(statusMessage,other.statusMessage)
				&& atisRequestNumber == other.atisRequestNumber;
		}
		return false;
	}

	public long getAtisRequestNumber() {
		return atisRequestNumber;
	}



	public void setAtisRequestNumber(long l) {
		atisRequestNumber = l;
	}

	public String getCna() {
		return cna;
	}

	public String getStatusMessage() {
		return statusMessage;
	}

	public void setCna(String string) {
		cna = string;
	}

	public void setStatusMessage(String string) {
		statusMessage = string;
	}

}
