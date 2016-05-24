//- Copyright Notice
//-----------------------------------------------------------------------
// (C) Copyright 2007 Telefonica Colombia, http://www.telefonica.com.co
// Bogota, Colombia All Rights Reserved
// THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF Telefonica Colombia
// The copyright notice above does not evidence any actual or intended
// publication of such source code.
//
// $Id: Group1.java,v 1.1 2011/03/30 18:20:03 lfmartinez Exp $
//-----------------------------------------------------------------------

package co.com.telefonica.atiempo.interfaces.atiempo;

import java.io.Serializable;
import java.util.Collection;

import co.com.telefonica.atiempo.util.EqualityUtilities;

/**
 * @author ylapchik
 * @version $Revision: 1.1 $
 */
public class Group1 implements Serializable {
	
	private int atisGroupNumber;
	private String comercialProductIdNumber;
	private long pulseQuantityFrom;
	private long pulseQuantityTo;
	private Collection subRequests;
	
	public int getAtisGroupNumber() {
		return atisGroupNumber;
	}

	public String getComercialProductIdNumber() {
		return comercialProductIdNumber;
	}

	public long getPulseQuantityFrom() {
		return pulseQuantityFrom;
	}

	public long getPulseQuantityTo() {
		return pulseQuantityTo;
	}

	public Collection getSubRequests() {
		return subRequests;
	}

	public void setAtisGroupNumber(int i) {
		atisGroupNumber = i;
	}

	public void setComercialProductIdNumber(String string) {
		comercialProductIdNumber = string;
	}

	public void setPulseQuantityFrom(long l) {
		pulseQuantityFrom = l;
	}

	public void setPulseQuantityTo(long l) {
		pulseQuantityTo = l;
	}

	public void setSubRequests(Collection collection) {
		subRequests = collection;
	}
	public int hashCode() {
			return atisGroupNumber;
	}
	
	public boolean equals(Object arg0) {
		if (arg0 instanceof Group1) {
			Group1 other = (Group1) arg0;
			return 	atisGroupNumber == other.atisGroupNumber
			&& EqualityUtilities.equals(comercialProductIdNumber, other.comercialProductIdNumber)
			&& pulseQuantityFrom == other.pulseQuantityFrom 
			&& pulseQuantityTo == other.pulseQuantityTo
			&& EqualityUtilities.equals(subRequests, other.subRequests);
		}
		return false;
	}	

}
