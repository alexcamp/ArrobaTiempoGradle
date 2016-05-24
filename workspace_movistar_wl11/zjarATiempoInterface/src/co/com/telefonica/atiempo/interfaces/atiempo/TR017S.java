//- Copyright Notice
//-----------------------------------------------------------------------
// (C) Copyright 2007 Telefonica Colombia, http://www.telefonica.com.co
// Bogota, Colombia All Rights Reserved
// THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF Telefonica Colombia
// The copyright notice above does not evidence any actual or intended
// publication of such source code.
//
// $Id: TR017S.java,v 1.1 2011/03/30 18:24:46 lfmartinez Exp $
//-----------------------------------------------------------------------

package co.com.telefonica.atiempo.interfaces.atiempo;

import java.util.Collection;

import co.com.telefonica.atiempo.util.EqualityUtilities;

/**
 * @author ylapchik
 * @version $Revision: 1.1 $
 */
public class TR017S extends ResponseHeader{
	private long atisRequestNumber;
	private Collection errors;
	

	public long getAtisRequestNumber() {
		return atisRequestNumber;
	}

	public Collection getErrors() {
		return errors;
	}

	public void setAtisRequestNumber(long l) {
		atisRequestNumber = l;
	}

	public void setErrors(Collection collection) {
		errors = collection;
	}
	
	public int getHashCode(){
		return (int)atisRequestNumber;
	}
	public boolean equals(Object arg0) {
		if (arg0 instanceof TR017S) {
			TR017S other = (TR017S) arg0;
			return super.equals(arg0)
				&& atisRequestNumber == other.atisRequestNumber
				&& EqualityUtilities.equals(errors, other.errors);
		}
		return false;
	}


}
