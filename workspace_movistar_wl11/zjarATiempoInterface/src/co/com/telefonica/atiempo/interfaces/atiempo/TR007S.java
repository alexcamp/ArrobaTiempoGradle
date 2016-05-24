 //- Copyright Notice
//-----------------------------------------------------------------------
// (C) Copyright 2007 Telefonica Colombia, http://www.telefonica.com.co
// Bogota, Colombia All Rights Reserved
// THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF Telefonica Colombia
// The copyright notice above does not evidence any actual or intended
// publication of such source code.
//
// $Id: TR007S.java,v 1.1 2011/03/30 18:23:28 lfmartinez Exp $
//-----------------------------------------------------------------------

package co.com.telefonica.atiempo.interfaces.atiempo;

import co.com.telefonica.atiempo.util.EqualityUtilities;

/**
 * @author fmaceachen
 * @version $Revision: 1.1 $
 */
public class TR007S extends ResponseHeader {
	private long atisRequestNumber;
	private long errorCode;
	private String errorMessageInv;
	
	
	public int hashCode() {
		return 0;
	}

	public boolean equlas(Object arg0) {
		if (arg0 instanceof TR007S) {
			TR007S other = (TR007S) arg0;
			return super.equals(arg0)
				&& atisRequestNumber == other.atisRequestNumber
				&& errorCode == other.errorCode
				&& EqualityUtilities.equals(errorMessageInv,other.errorMessageInv);
		}
		return false;
	}

	public long getAtisRequestNumber() {
		return atisRequestNumber;
	}

	public long getErrorCode() {
		return errorCode;
	}

	public void setAtisRequestNumber(long l) {
		atisRequestNumber = l;
	}

	public void setErrorCode(long l) {
		errorCode = l;
	}

	public String getErrorMessageInv() {
		return errorMessageInv;
	}

	public void setErrorMessageInv(String string) {
		errorMessageInv = string;
	}


}
