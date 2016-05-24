//- Copyright Notice
//-----------------------------------------------------------------------
// (C) Copyright 2007 Telefonica Colombia, http://www.telefonica.com.co
// Bogota, Colombia All Rights Reserved
// THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF Telefonica Colombia
// The copyright notice above does not evidence any actual or intended
// publication of such source code.
//
// $Id: TR023E.java,v 1.1 2011/03/30 18:24:46 lfmartinez Exp $
//-----------------------------------------------------------------------

package co.com.telefonica.atiempo.interfaces.atiempo;

import co.com.telefonica.atiempo.util.EqualityUtilities;

/**
 * @author gmarcone
 * @version $Revision: 1.1 $
 */
public class TR023E extends RequestHeader{

	private long atisRequestNumber;
	private long productServiceCode;
	private long comercialOperation;
	private String serviceReferenceId;
	private String clientDocument;
	private String password;
	private String clientName;
	private String clientFirstLastName;
	private String clientSecondLastName;
	private String contactName;
	private String contactPhone;
	private String contactFirstLastName;
	private String contactSecondLastName;	
	private String fatherEmail;
	private String secondEmail;
	private Integer phoneNumber;	
	private String tipoSVA;
	private String dominio1;	
	private String dominio3;
	private String dominio2;
//	REQ McAfee multidevice @dcardena 08/10/2014
	private long productServiceCodeAnt;
	private String oldServiceReferenceId;
	//FIN McAfee
	
	public String getContactPhone() {
		return contactPhone;
	}
	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}
	public String getDominio1() {
		return dominio1;
	}
	public void setDominio1(String dominio1) {
		this.dominio1 = dominio1;
	}
	public String getDominio2() {
		return dominio2;
	}
	public void setDominio2(String dominio2) {
		this.dominio2 = dominio2;
	}
	public String getDominio3() {
		return dominio3;
	}
	public void setDominio3(String dominio3) {
		this.dominio3 = dominio3;
	}
	public String getTipoSVA() {
		return tipoSVA;
	}
	public void setTipoSVA(String tipoSVA) {
		this.tipoSVA = tipoSVA;
	}
	public int hashCode() {
		return (int)atisRequestNumber;
	}
	
	public boolean equals(Object arg0) {
		if (arg0 instanceof TR023E) {
			TR023E other = (TR023E) arg0;
			return super.equals(arg0)
				&& productServiceCode == other.productServiceCode
				&& comercialOperation == other.comercialOperation
				&& atisRequestNumber == other.atisRequestNumber
				&& EqualityUtilities.equals(phoneNumber,other.phoneNumber)
				&& EqualityUtilities.equals(serviceReferenceId,other.serviceReferenceId)
				&& EqualityUtilities.equals(clientDocument,other.clientDocument)
				&& EqualityUtilities.equals(password,other.password)
				&& EqualityUtilities.equals(clientName,other.clientName)
				&& EqualityUtilities.equals(clientFirstLastName,other.clientFirstLastName)
				&& EqualityUtilities.equals(clientSecondLastName,other.clientSecondLastName)
				&& EqualityUtilities.equals(contactName,other.contactName)
				&& EqualityUtilities.equals(contactFirstLastName,other.contactFirstLastName)
				&& EqualityUtilities.equals(contactSecondLastName,other.contactSecondLastName)
				&& EqualityUtilities.equals(fatherEmail,other.fatherEmail)
				&& EqualityUtilities.equals(secondEmail,other.secondEmail)				
				&& EqualityUtilities.equals(tipoSVA,other.tipoSVA)
				&& EqualityUtilities.equals(dominio1,other.dominio1)
				&& EqualityUtilities.equals(dominio3,other.dominio3)
				&& EqualityUtilities.equals(dominio2,other.dominio2)
				&& EqualityUtilities.equals(contactPhone,other.contactPhone)
				//REQ McAfee multidevice
				&& productServiceCodeAnt == other.productServiceCodeAnt
				&& EqualityUtilities.equals(oldServiceReferenceId,other.oldServiceReferenceId)
				//FIN REQ McAfee multidevice
				;
		}
		return false;
	}



	public Integer getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(Integer phoneNumber) {
		this.phoneNumber = phoneNumber;
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

	public String getPassword() {
		return password;
	}

	public long getProductServiceCode() {
		return productServiceCode;
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

	public void setPassword(String string) {
		password = string;
	}

	public void setProductServiceCode(long l) {
		productServiceCode = l;
	}

	public void setSecondEmail(String string) {
		secondEmail = string;
	}

	public String getServiceReferenceId() {
		return serviceReferenceId;
	}

	public void setServiceReferenceId(String l) {
		serviceReferenceId = l;
	}
//	 REQ McAfee multidevice
	/**
	 * @return Devuelve oldServiceReferenceId.
	 */
	public String getOldServiceReferenceId() {
		return oldServiceReferenceId;
	}
	/**
	 * @param oldServiceReferenceId El oldServiceReferenceId a establecer.
	 */
	public void setOldServiceReferenceId(String oldServiceReferenceId) {
		this.oldServiceReferenceId = oldServiceReferenceId;
	}
	/**
	 * @return Devuelve productServiceCodeAnt.
	 */
	public long getProductServiceCodeAnt() {
		return productServiceCodeAnt;
	}
	/**
	 * @param productServiceCodeAnt El productServiceCodeAnt a establecer.
	 */
	public void setProductServiceCodeAnt(long productServiceCodeAnt) {
		this.productServiceCodeAnt = productServiceCodeAnt;
	}
//	FIN REQ McAfee multideviceç
}
