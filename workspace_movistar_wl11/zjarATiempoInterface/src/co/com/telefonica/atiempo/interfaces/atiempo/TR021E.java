//- Copyright Notice
//-----------------------------------------------------------------------
// (C) Copyright 2007 Telefonica Colombia, http://www.telefonica.com.co
// Bogota, Colombia All Rights Reserved
// THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF Telefonica Colombia
// The copyright notice above does not evidence any actual or intended
// publication of such source code.
//
// $Id: TR021E.java,v 1.1 2011/03/30 18:24:44 lfmartinez Exp $
//-----------------------------------------------------------------------

package co.com.telefonica.atiempo.interfaces.atiempo;

/**
 * @author ylapchik
 * @version $Revision: 1.1 $
 */
public class TR021E extends RequestHeader{
	private int phoneNumber;
	private int status;
	private long atisRequestNumber;
	 
	public long getAtisRequestNumber() {
		return atisRequestNumber;
	}

	public void setAtisRequestNumber(long l) {
		atisRequestNumber = l;
	}

	public boolean equals(Object arg0){
		if (arg0 instanceof TR021E) {
			TR021E other = (TR021E) arg0;
			return super.equals(arg0)
				&& phoneNumber == other.phoneNumber
				&& status == other.status
				&& atisRequestNumber == other.atisRequestNumber;
		}
		return false;
	}

	/**
	 * @return
	 */
	public int getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @return
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * @param i
	 */
	public void setPhoneNumber(int i) {
		phoneNumber = i;
	}

	/**
	 * @param i
	 */
	public void setStatus(int i) {
		status = i;
	}

}
