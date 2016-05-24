//- Copyright Notice
//-----------------------------------------------------------------------
// (C) Copyright 2007 Telefonica Colombia, http://www.telefonica.com.co
// Bogota, Colombia All Rights Reserved
// THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF Telefonica Colombia
// The copyright notice above does not evidence any actual or intended
// publication of such source code.
//
// $Id: TR024E.java,v 1.1 2011/03/30 18:24:44 lfmartinez Exp $
//-----------------------------------------------------------------------

package co.com.telefonica.atiempo.interfaces.atiempo;

import co.com.telefonica.atiempo.util.EqualityUtilities;

/**
 * @author gmarcone
 * @version $Revision: 1.1 $
 */
public class TR024E extends RequestHeader{

	private long atisRequestNumber;
	private String licenseType;
	private long comercialOperation;
	private String serviceReferenceId;
	private String clientDocument;
	private String clientName;
	private String clientFirstLastName;
	private String clientSecondLastName;
	private String contactName;
	private String contactFirstLastName;
	private String contactSecondLastName;	
	private String fatherEmail;
	private String secondEmail;
	private int phoneNumber;
	
	public int hashCode() {
		return (int)atisRequestNumber;
	}
	
	public boolean equals(Object arg0) {
		if (arg0 instanceof TR024E) {
			TR024E other = (TR024E) arg0;
			return super.equals(arg0)
				&& EqualityUtilities.equals(licenseType,other.licenseType)
				&& comercialOperation == other.comercialOperation
				&& atisRequestNumber == other.atisRequestNumber
				&& EqualityUtilities.equals(serviceReferenceId,other.serviceReferenceId)
				&& EqualityUtilities.equals(clientDocument,other.clientDocument)
				&& EqualityUtilities.equals(clientName,other.clientName)
				&& EqualityUtilities.equals(clientFirstLastName,other.clientFirstLastName)
				&& EqualityUtilities.equals(clientSecondLastName,other.clientSecondLastName)
				&& EqualityUtilities.equals(contactName,other.contactName)
				&& EqualityUtilities.equals(contactFirstLastName,other.contactFirstLastName)
				&& EqualityUtilities.equals(contactSecondLastName,other.contactSecondLastName)
				&& EqualityUtilities.equals(fatherEmail,other.fatherEmail)
				&& EqualityUtilities.equals(secondEmail,other.secondEmail);
		}
		return false;
	}



	public long getAtisRequestNumber() {
		return atisRequestNumber;
	}

	public String getClientDocument() {
		return clientDocument;
	}

	public String getClientFirstLastName() {
		return clientFirstLastName;
	}

	public String getClientName() {
		return clientName;
	}

	public String getClientSecondLastName() {
		return clientSecondLastName;
	}

	public long getComercialOperation() {
		return comercialOperation;
	}

	public String getContactFirstLastName() {
		return contactFirstLastName;
	}

	public String getContactName() {
		return contactName;
	}

	public String getContactSecondLastName() {
		return contactSecondLastName;
	}

	public String getFatherEmail() {
		return fatherEmail;
	}

	public String getSecondEmail() {
		return secondEmail;
	}

	public void setAtisRequestNumber(long l) {
		atisRequestNumber = l;
	}

	public void setClientDocument(String string) {
		clientDocument = string;
	}

	public void setClientFirstLastName(String string) {
		clientFirstLastName = string;
	}

	public void setClientName(String string) {
		clientName = string;
	}

	public void setClientSecondLastName(String string) {
		clientSecondLastName = string;
	}

	public void setComercialOperation(long l) {
		comercialOperation = l;
	}

	public void setContactFirstLastName(String string) {
		contactFirstLastName = string;
	}

	public void setContactName(String string) {
		contactName = string;
	}

	public void setContactSecondLastName(String string) {
		contactSecondLastName = string;
	}

	public void setFatherEmail(String string) {
		fatherEmail = string;
	}


	public void setSecondEmail(String string) {
		secondEmail = string;
	}

	public String getLicenseType() {
		return licenseType;
	}

	public void setLicenseType(String l) {
		licenseType = l;
	}

	public String getServiceReferenceId() {
		return serviceReferenceId;
	}

	public void setServiceReferenceId(String l) {
		serviceReferenceId = l;
	}
	public int getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(int l) {
		this.phoneNumber = l;
	}
}
