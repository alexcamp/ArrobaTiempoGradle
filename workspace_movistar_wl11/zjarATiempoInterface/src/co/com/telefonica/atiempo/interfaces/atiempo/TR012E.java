//- Copyright Notice
//-----------------------------------------------------------------------
// (C) Copyright 2007 Telefonica Colombia, http://www.telefonica.com.co
// Bogota, Colombia All Rights Reserved
// THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF Telefonica Colombia
// The copyright notice above does not evidence any actual or intended
// publication of such source code.
//
// $Id: TR012E.java,v 1.1 2011/03/30 18:24:04 lfmartinez Exp $
//-----------------------------------------------------------------------

package co.com.telefonica.atiempo.interfaces.atiempo;

/**
 * @author fmaceachen
 * @version $Revision: 1.1 $
 */
public class TR012E extends RequestHeader{
	private int department;
	private int city;
	private int phoneNumber;
	
	
	public int getCity() {
		return city;
	}

	public int getDepartment() {
		return department;
	}

	public int getPhoneNumber() {
		return phoneNumber;
	}

	public void setCity(int string) {
		city = string;
	}

	public void setDepartment(int string) {
		department = string;
	}

	public void setPhoneNumber(int string) {
		phoneNumber = string;
	}
	
	public int hashCode(){
		return department;
	}
	
	public boolean equals(Object arg0){
		if (arg0 instanceof TR012E) {
			TR012E other = (TR012E) arg0;
			return super.equals(arg0)
				&& city == other.city
				&& department == other.department
				&& phoneNumber == other.phoneNumber;
		}
		return false;
	}
}
