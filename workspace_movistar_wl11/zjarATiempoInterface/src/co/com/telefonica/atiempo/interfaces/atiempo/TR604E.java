//- Copyright Notice
//-----------------------------------------------------------------------
//(C) Copyright 2007 Telefonica Colombia, http://www.telefonica.com.co
//Bogota, Colombia All Rights Reserved
//THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF Telefonica Colombia
//The copyright notice above does not evidence any actual or intended
//publication of such source code.
//
//$Id: TR0604E.java Creado el 19/02/2014 dcardena Exp $
//-----------------------------------------------------------------------

package co.com.telefonica.atiempo.interfaces.atiempo;

import co.com.telefonica.atiempo.util.EqualityUtilities;

/**
 * @author dcardena
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class TR604E extends RequestHeader{

	private String atiempoRequestNumber;
	private String operacioncomercial;
	private String document;
	private String password;
	private String emailAddress;
	private String phoneNumber;
	private String firstName;
	private String lastName;
	private String originCode;
	private String partnerUserId;
	private String psId;
	
	
	public boolean equals(Object arg0) {
		if (arg0 instanceof TR604E) {
			TR604E other = (TR604E) arg0;
			return super.equals(arg0)
				&& EqualityUtilities.equals(atiempoRequestNumber, other.atiempoRequestNumber)
				&& EqualityUtilities.equals(operacioncomercial, other.operacioncomercial)
				&& EqualityUtilities.equals(password, other.password)
				&& EqualityUtilities.equals(emailAddress, other.emailAddress)
				&& EqualityUtilities.equals(phoneNumber, other.phoneNumber)
				&& EqualityUtilities.equals(firstName, other.firstName)
				&& EqualityUtilities.equals(lastName, other.lastName)
				&& EqualityUtilities.equals(document, other.document)
				&& EqualityUtilities.equals(originCode, other.originCode)
				&& EqualityUtilities.equals(partnerUserId, other.partnerUserId)
				&& EqualityUtilities.equals(psId, other.psId)
			;
			}
		return false;
	}
	
	/**
	 * @return Devuelve atiempoRequestNumber.
	 */
	public String getAtiempoRequestNumber() {
		return atiempoRequestNumber;
	}
	/**
	 * @param atiempoRequestNumber El atiempoRequestNumber a establecer.
	 */
	public void setAtiempoRequestNumber(String atiempoRequestNumber) {
		this.atiempoRequestNumber = atiempoRequestNumber;
	}

	/**
	 * @return Devuelve document.
	 */
	public String getDocument() {
		return document;
	}
	/**
	 * @param document El document a establecer.
	 */
	public void setDocument(String document) {
		this.document = document;
	}
	/**
	 * @return Devuelve emailAddress.
	 */
	public String getEmailAddress() {
		return emailAddress;
	}
	/**
	 * @param emailAddress El emailAddress a establecer.
	 */
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	/**
	 * @return Devuelve firstName.
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * @param firstName El firstName a establecer.
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * @return Devuelve lastName.
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * @param lastName El lastName a establecer.
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	/**
	 * @return Devuelve operacioncomercial.
	 */
	public String getOperacioncomercial() {
		return operacioncomercial;
	}
	/**
	 * @param operacioncomercial El operacioncomercial a establecer.
	 */
	public void setOperacioncomercial(String operacioncomercial) {
		this.operacioncomercial = operacioncomercial;
	}
	/**
	 * @return Devuelve originCode.
	 */
	public String getOriginCode() {
		return originCode;
	}
	/**
	 * @param originCode El originCode a establecer.
	 */
	public void setOriginCode(String originCode) {
		this.originCode = originCode;
	}
	/**
	 * @return Devuelve partnerUserId.
	 */
	public String getPartnerUserId() {
		return partnerUserId;
	}
	/**
	 * @param partnerUserId El partnerUserId a establecer.
	 */
	public void setPartnerUserId(String partnerUserId) {
		this.partnerUserId = partnerUserId;
	}
	/**
	 * @return Devuelve password.
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password El password a establecer.
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return Devuelve phoneNumber.
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}
	/**
	 * @param phoneNumber El phoneNumber a establecer.
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	/**
	 * @return Devuelve psId.
	 */
	public String getPsId() {
		return psId;
	}
	/**
	 * @param psId El psId a establecer.
	 */
	public void setPsId(String psId) {
		this.psId = psId;
	}
}
