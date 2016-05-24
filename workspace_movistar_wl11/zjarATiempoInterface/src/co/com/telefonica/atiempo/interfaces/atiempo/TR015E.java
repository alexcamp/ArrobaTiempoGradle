//- Copyright Notice
//-----------------------------------------------------------------------
// (C) Copyright 2007 Telefonica Colombia, http://www.telefonica.com.co
// Bogota, Colombia All Rights Reserved
// THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF Telefonica Colombia
// The copyright notice above does not evidence any actual or intended
// publication of such source code.
//
// $Id: TR015E.java,v 1.1 2011/03/30 18:24:04 lfmartinez Exp $
//-----------------------------------------------------------------------

package co.com.telefonica.atiempo.interfaces.atiempo;

/**
 * @author ylapchik
 * @version $Revision: 1.1 $
 */
public class TR015E extends RequestHeader{
	private long atisRequestNumber;
	private int previousPhone;
	private int actualPhone;

	public int getActualPhone() {
		return actualPhone;
	}

	public long getAtisRequestNumber() {
		return atisRequestNumber;
	}

	public void setActualPhone(int i) {
		actualPhone = i;
	}

	public void setAtisRequestNumber(long l) {
		atisRequestNumber = l;
	}

	public int hashCode(){
		return previousPhone;
	}
	
	public boolean equals(Object arg0){
		if (arg0 instanceof TR015E) {
			TR015E other = (TR015E) arg0;
			return super.equals(arg0)
				&& atisRequestNumber == other.atisRequestNumber
				&& previousPhone== other.previousPhone
				&& actualPhone == other.actualPhone;
		}
		return false;
	}

	public int getPreviousPhone() {
		return previousPhone;
	}

	public void setPreviousPhone(int i) {
		previousPhone = i;
	}

}
