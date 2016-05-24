//- Copyright Notice
//-----------------------------------------------------------------------
// (C) Copyright 2007 Telefonica Colombia, http://www.telefonica.com.co
// Bogota, Colombia All Rights Reserved
// THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF Telefonica Colombia
// The copyright notice above does not evidence any actual or intended
// publication of such source code.
//
// $Id: TR017E.java,v 1.1 2011/03/30 18:24:04 lfmartinez Exp $
//-----------------------------------------------------------------------

package co.com.telefonica.atiempo.interfaces.atiempo;

import java.util.Collection;

import co.com.telefonica.atiempo.util.EqualityUtilities;

/**
 * @author ylapchik
 * @version $Revision: 1.1 $
 */
public class TR017E extends RequestHeader{
	
	private long atisRequestNumber;
	private String pcId;
	private long clientDocumentNumber;
	private String clientDocumentType;
	private String clientLastName;
	private String clientSecondLastName;
	private String clientName;
	private String city;
	private char clientType;
	private long pcProductServiceCode;
	private long pcCommercialOperation;
	private Collection equipments;
	private Collection packages;
	
	public long getAtisRequestNumber() {
		return atisRequestNumber;
	}

	public String getCity() {
		return city;
	}

	public long getClientDocumentNumber() {
		return clientDocumentNumber;
	}

	public String getClientDocumentType() {
		return clientDocumentType;
	}

	public String getClientLastName() {
		return clientLastName;
	}

	public String getClientName() {
		return clientName;
	}

	public String getClientSecondLastName() {
		return clientSecondLastName;
	}

	public char getClientType() {
		return clientType;
	}

	public Collection getEquipments() {
		return equipments;
	}

	public String getPcId() {
		return pcId;
	}

	public void setAtisRequestNumber(long l) {
		atisRequestNumber = l;
	}

	public void setCity(String string) {
		city = string;
	}

	public void setClientDocumentNumber(long l) {
		clientDocumentNumber = l;
	}

	public void setClientDocumentType(String string) {
		clientDocumentType = string;
	}

	public void setClientLastName(String string) {
		clientLastName = string;
	}

	public void setClientName(String string) {
		clientName = string;
	}

	public void setClientSecondLastName(String string) {
		clientSecondLastName = string;
	}

	public void setClientType(char c) {
		clientType = c;
	}

	public void setEquipments(Collection collection) {
		equipments = collection;
	}

	public void setPcId(String string) {
		pcId = string;
	}
	
	public Collection getPackages() {
		return packages;
	}

	public void setPackages(Collection collection) {
		packages = collection;
	}

	public long getPcCommercialOperation() {
		return pcCommercialOperation;
	}

	public long getPcProductServiceCode() {
		return pcProductServiceCode;
	}

	public void setPcCommercialOperation(long l) {
		pcCommercialOperation = l;
	}

	public void setPcProductServiceCode(long l) {
		pcProductServiceCode = l;
	}

	public int getHashCode(){
		return pcId.hashCode();
	}
	
	public boolean equals(Object arg0){
		if (arg0 instanceof TR017E) {
			TR017E other = (TR017E) arg0;
			return super.equals(arg0) &&
				atisRequestNumber == other.atisRequestNumber &&
				EqualityUtilities.equals(pcId, other.pcId) &&
				clientDocumentNumber == other.clientDocumentNumber &&
				EqualityUtilities.equals(clientDocumentType, other.clientDocumentType) &&
				EqualityUtilities.equals(clientLastName, other.clientLastName) &&
				EqualityUtilities.equals(clientSecondLastName, other.clientSecondLastName) &&
				EqualityUtilities.equals(clientName, other.clientName) &&
				EqualityUtilities.equals(city, other.city) &&
				clientType== other.clientType &&
				pcProductServiceCode== other.pcProductServiceCode &&
				pcCommercialOperation == other.pcCommercialOperation &&
				EqualityUtilities.equals(equipments, other.equipments) &&
				EqualityUtilities.equals(packages, other.packages);
		}
		return false;
	}

}
