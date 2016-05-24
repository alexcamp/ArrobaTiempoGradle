//- Copyright Notice
//-----------------------------------------------------------------------
// (C) Copyright 2007 Telefonica Colombia, http://www.telefonica.com.co
// Bogota, Colombia All Rights Reserved
// THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF Telefonica Colombia
// The copyright notice above does not evidence any actual or intended
// publication of such source code.
//
// $Id: TR013E.java,v 1.1 2011/03/30 18:24:04 lfmartinez Exp $
//-----------------------------------------------------------------------

package co.com.telefonica.atiempo.interfaces.atiempo;

import java.util.Collection;

import co.com.telefonica.atiempo.util.EqualityUtilities;

/**
 * @author fmaceachen
 * @version $Revision: 1.1 $
 */
public class TR013E extends RequestHeader{
	private long atisRequestNumber;
	private String clientDocument;
	private String clientDocumentType;
	private String address;
	private char clientType;
	private String clientName;
	private String clientFirstLastName;
	private String clientSecondLastName;
	private String contactPhone;
	private String contactName;
	private String contactFirstLastName;
	private String contactSecondLastName;
	private int servicePhone;
	private int previousServicePhone;
	private long productServiceCode;
	private long comercialOperationType;
	private Collection dslams;
	private String cityCode;
	private String fatherEmail;
	private long secondComercialOperation;
	
	
	
	public int hashCode(){
		return servicePhone;
	}
	
	public boolean equals(Object arg0){
		if (arg0 instanceof TR013E) {
			TR013E other = (TR013E) arg0;
			return super.equals(arg0)
				&& atisRequestNumber == other.atisRequestNumber
				&& secondComercialOperation == other.secondComercialOperation
				&& EqualityUtilities.equals(clientDocument, other.clientDocument)
				&& EqualityUtilities.equals(clientDocumentType, other.clientDocumentType)
				&& EqualityUtilities.equals(address, other.address)
				&& clientType == other.clientType
				&& EqualityUtilities.equals(clientName, other.clientName)
				&& EqualityUtilities.equals(clientFirstLastName, other.clientFirstLastName)
				&& EqualityUtilities.equals(clientSecondLastName, other.clientSecondLastName)
				&& EqualityUtilities.equals(contactPhone, other.contactPhone)
				&& EqualityUtilities.equals(contactName, other.contactName)
				&& EqualityUtilities.equals(contactFirstLastName, other.contactFirstLastName)
				&& EqualityUtilities.equals(contactSecondLastName, other.contactSecondLastName)
				&& servicePhone == other.servicePhone
				&& previousServicePhone == other.previousServicePhone
				&& productServiceCode == other.productServiceCode
				&& comercialOperationType == other.comercialOperationType
				&& EqualityUtilities.equals(dslams, other.dslams) 
				&& EqualityUtilities.equals(cityCode, other.cityCode)
				&& EqualityUtilities.equals(fatherEmail, other.fatherEmail);
		}
		return false;
	}

	public String getAddress() {
		return address;
	}

	public long getAtisRequestNumber() {
		return atisRequestNumber;
	}

	public String getClientDocumentType() {
		return clientDocumentType;
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

	public char getClientType() {
		return clientType;
	}

	public long getSecondComercialOperation() {
		return secondComercialOperation;
	}
	public void setSecondComercialOperation(long secondComercialOperation) {
		this.secondComercialOperation = secondComercialOperation;
	}
	public long getComercialOperationType() {
		return comercialOperationType;
	}

	public String getContactFirstLastName() {
		return contactFirstLastName;
	}

	public String getContactName() {
		return contactName;
	}

	public String getContactPhone() {
		return contactPhone;
	}

	public String getContactSecondLastName() {
		return contactSecondLastName;
	}

	public Collection getDslams() {
		return dslams;
	}

	public String getClientDocument() {
		return clientDocument;
	}

	public int getPreviousServicePhone() {
		return previousServicePhone;
	}

	public long getProductServiceCode() {
		return productServiceCode;
	}

	public int getServicePhone() {
		return servicePhone;
	}

	public void setAddress(String string) {
		address = string;
	}

	public void setAtisRequestNumber(long l) {
		atisRequestNumber = l;
	}

	public void setClientDocumentType(String string) {
		clientDocumentType = string;
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

	public void setClientType(char c) {
		clientType = c;
	}

	public void setComercialOperationType(long l) {
		comercialOperationType = l;
	}

	public void setContactFirstLastName(String string) {
		contactFirstLastName = string;
	}

	public void setContactName(String string) {
		contactName = string;
	}

	public void setContactPhone(String string) {
		contactPhone = string;
	}

	public void setContactSecondLastName(String string) {
		contactSecondLastName = string;
	}

	public void setDslams(Collection collection) {
		dslams = collection;
	}

	public void setClientDocument(String string) {
		clientDocument = string;
	}

	public void setPreviousServicePhone(int i) {
		previousServicePhone = i;
	}

	public void setProductServiceCode(long l) {
		productServiceCode = l;
	}

	public void setServicePhone(int i) {
		servicePhone = i;
	}

	public String getCityCode() {
		return cityCode;
	}

	public String getFatherEmail() {
		return fatherEmail;
	}

	public void setCityCode(String string) {
		cityCode = string;
	}

	public void setFatherEmail(String string) {
		fatherEmail = string;
	}

}
