//- Copyright Notice
//-----------------------------------------------------------------------
// (C) Copyright 2007 Telefonica Colombia, http://www.telefonica.com.co
// Bogota, Colombia All Rights Reserved
// THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF Telefonica Colombia
// The copyright notice above does not evidence any actual or intended
// publication of such source code.
//
// $Id: TR014E.java,v 1.1 2011/03/30 18:24:04 lfmartinez Exp $
//-----------------------------------------------------------------------

package co.com.telefonica.atiempo.interfaces.atiempo;

/**
 * @author ylapchik
 * @version $Revision: 1.1 $
 */
public class TR014E extends RequestHeader{
	private long atisRequestNumber;
	private int phoneNumber;

	public long getAtisRequestNumber() {
		return atisRequestNumber;
	}

	public int getPhoneNumber() {
		return phoneNumber;
	}

	public void setAtisRequestNumber(long l) {
		atisRequestNumber = l;
	}

	public void setPhoneNumber(int i) {
		phoneNumber = i;
	}
	public int hashCode(){
		return phoneNumber;
	}
	
	public boolean equals(Object arg0){
		if (arg0 instanceof TR014E) {
			TR014E other = (TR014E) arg0;
			return super.equals(arg0)
				&& atisRequestNumber == other.atisRequestNumber
				&& phoneNumber== other.phoneNumber;
		}
		return false;
	}

}
