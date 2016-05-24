//- Copyright Notice
//-----------------------------------------------------------------------
// (C) Copyright 2007 Telefonica Colombia, http://www.telefonica.com.co
// Bogota, Colombia All Rights Reserved
// THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF Telefonica Colombia
// The copyright notice above does not evidence any actual or intended
// publication of such source code.
//
// $Id: TR011E.java,v 1.1 2011/03/30 18:24:04 lfmartinez Exp $
//-----------------------------------------------------------------------

package co.com.telefonica.atiempo.interfaces.atiempo;

import java.util.Collection;

import co.com.telefonica.atiempo.util.EqualityUtilities;

/**
 * @author ylapchik
 * @version $Revision: 1.1 $
 */
public class TR011E extends RequestHeader {
	private int phoneNumber;
	private Collection services;
	private int action;
	private long atisRequestNumber;

	public int getAction() {
		return action;
	}

	public int getPhoneNumber() {
		return phoneNumber;
	}

	public Collection getServices() {
		return services;
	}

	public void setAction(int i) {
		action = i;
	}

	public void setPhoneNumber(int i) {
		phoneNumber = i;
	}

	public void setServices(Collection collection) {
		services = collection;
	}
	public int hashCode() {
		return action;
	}

	public boolean equals(Object arg0) {
		if (arg0 instanceof TR011E) {
			TR011E other = (TR011E) arg0;
			return super.equals(arg0)
				&& action == other.action
				&& phoneNumber == other.phoneNumber
				&& EqualityUtilities.equals(services, other.services)
				&& atisRequestNumber == other.atisRequestNumber
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

}
