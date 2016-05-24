//- Copyright Notice
//-----------------------------------------------------------------------
// (C) Copyright 2007 Telefonica Colombia, http://www.telefonica.com.co
// Bogota, Colombia All Rights Reserved
// THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF Telefonica Colombia
// The copyright notice above does not evidence any actual or intended
// publication of such source code.
//
// $Id: TR004E.java,v 1.1 2011/03/30 18:23:28 lfmartinez Exp $
//-----------------------------------------------------------------------

package co.com.telefonica.atiempo.interfaces.atiempo;

import co.com.telefonica.atiempo.util.EqualityUtilities;

/**
 * @author ylapchik
 * @version $Revision: 1.1 $
 */
public class TR004E extends RequestHeader {
	private int odsNumber;
	private int department;
	private int city;
	private long atisRequestNumber;
	private String password_fttc;
	public int getDepartment() {
		return department;
	}


	public int getOdsNumber() {
		return odsNumber;
	}

	public void setDepartment(int i) {
		department = i;
	}

	public void setOdsNumber(int i) {
		odsNumber = i;
	}
	public int hashCode() {
		return odsNumber;
	}
	public boolean equals(Object arg0) {
		if (arg0 instanceof TR004E) {
			TR004E other = (TR004E) arg0;
			return super.equals(arg0)
				&& department == other.department
				&& odsNumber == other.odsNumber
				&& city == other.city
				&& atisRequestNumber== other.atisRequestNumber
				&& EqualityUtilities.equals(password_fttc,other.password_fttc)
				;
		}
		return false;
	}

	public long getAtisRequestNumber() {
		return atisRequestNumber;
	}

	
	public void setAtisRequestNumber(long l) {
		atisRequestNumber = l;
	}


	public int getCity() {
		return city;
	}

	public void setCity(int i) {
		city = i;
	}

	/**
	 * @return Devuelve password_fttc.
	 */
	public String getPassword_fttc() {
		return password_fttc;
	}
	/**
	 * @param password_fttc El password_fttc a establecer.
	 */
	public void setPassword_fttc(String pasword_fttc) {
		this.password_fttc = pasword_fttc;
	}
}
