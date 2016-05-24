//- Copyright Notice
//-----------------------------------------------------------------------
// (C) Copyright 2007 Telefonica Colombia, http://www.telefonica.com.co
// Bogota, Colombia All Rights Reserved
// THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF Telefonica Colombia
// The copyright notice above does not evidence any actual or intended
// publication of such source code.
//
// $Id: TR019S.java,v 1.1 2011/03/30 18:24:44 lfmartinez Exp $
//-----------------------------------------------------------------------

package co.com.telefonica.atiempo.interfaces.atiempo;

import java.util.Collection;

import co.com.telefonica.atiempo.util.EqualityUtilities;

/**
 * @author ylapchik
 * @version $Revision: 1.1 $
 */
public class TR019S extends ResponseHeader{
	private long atisRequestNumber;
	private Collection equipments;
	private Collection packages;
	private long errorCode;
	private String errorCodeMessage;
	

	public long getAtisRequestNumber() {
		return atisRequestNumber;
	}

	public Collection getEquipments() {
		return equipments;
	}

	public long getErrorCode() {
		return errorCode;
	}

	public String getErrorCodeMessage() {
		return errorCodeMessage;
	}

	public Collection getPackages() {
		return packages;
	}

	public void setAtisRequestNumber(long l) {
		atisRequestNumber = l;
	}

	public void setEquipments(Collection collection) {
		equipments = collection;
	}

	public void setErrorCode(long l) {
		errorCode = l;
	}

	public void setErrorCodeMessage(String string) {
		errorCodeMessage = string;
	}

	public void setPackages(Collection collection) {
		packages = collection;
	}
	public int hashCode(){
		return (int)atisRequestNumber;
	}
	public boolean equals(Object arg0) {
		if (arg0 instanceof TR019S) {
			TR019S other = (TR019S) arg0;
			return super.equals(arg0)
				&& atisRequestNumber == other.atisRequestNumber
				&& errorCode==other.errorCode
				&& EqualityUtilities.equals(equipments, other.equipments)
				&& EqualityUtilities.equals(packages, other.packages)
				&& EqualityUtilities.equals(errorCodeMessage, other.errorCodeMessage);
		}
		return false;
	}
	
}