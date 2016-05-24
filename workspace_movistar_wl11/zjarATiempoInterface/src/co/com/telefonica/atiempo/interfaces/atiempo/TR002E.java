//- Copyright Notice
//-----------------------------------------------------------------------
// (C) Copyright 2007 Telefonica Colombia, http://www.telefonica.com.co
// Bogota, Colombia All Rights Reserved
// THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF Telefonica Colombia
// The copyright notice above does not evidence any actual or intended
// publication of such source code.
//
// $Id: TR002E.java,v 1.1 2011/03/30 18:23:28 lfmartinez Exp $
//-----------------------------------------------------------------------

package co.com.telefonica.atiempo.interfaces.atiempo;

/**
 * @author ylapchik
 * @version $Revision: 1.1 $
 */
public class TR002E extends RequestHeader{
	private int odsNumber;
	private long atisRequestNumber;
	private int department;
	private int city;
	private String cni;
	

	public long getAtisRequestNumber() {
		return atisRequestNumber;
	}

	public int getCity() {
		return city;
	}

	public String getCni() {
		return cni;
	}

	public int getDepartment() {
		return department;
	}

	public int getOdsNumber() {
		return odsNumber;
	}

	public void setAtisRequestNumber(long l) {
		atisRequestNumber = l;
	}

	public void setCity(int i) {
		city = i;
	}

	public void setCni(String string) {
		cni = string;
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
		if (arg0 instanceof TR002E) {
			TR002E other = (TR002E) arg0;
			return super.equals(arg0)
				&& atisRequestNumber == other.atisRequestNumber
				&& city == other.city
				&& department == other.department
				&& odsNumber == other.odsNumber
				;
		}
		return false;
	}

}
