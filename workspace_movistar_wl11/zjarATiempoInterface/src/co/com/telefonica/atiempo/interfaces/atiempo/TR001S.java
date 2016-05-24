//- Copyright Notice
//-----------------------------------------------------------------------
// (C) Copyright 2007 Telefonica Colombia, http://www.telefonica.com.co
// Bogota, Colombia All Rights Reserved
// THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF Telefonica Colombia
// The copyright notice above does not evidence any actual or intended
// publication of such source code.
//
// $Id: TR001S.java,v 1.1 2011/03/30 18:23:28 lfmartinez Exp $
//-----------------------------------------------------------------------

package co.com.telefonica.atiempo.interfaces.atiempo;

import java.util.Collection;
import java.util.Date;

import co.com.telefonica.atiempo.util.EqualityUtilities;

/**
 * @author fmaceachen
 * @version $Revision: 1.1 $
 */
public class TR001S extends RequestHeader {
	private long requestNumber;
	private String emittingUser;
	private Date emittingDate;
	private String observations;
	private long clientCode;
	private long accountCode;
	private String clientDocumentType;
	private String clientDocumentNumber;
	private String clientDocumentVerification;
	private String clientName;
	private String clientLastName;
	private String clientSecondLastName;
	private long clientSegmentCode;
	private long clientSubSegmentCode;
	private long accountSegmentCode;
	private long accountSubsegmentCode;
	private String clientType;
	private String submitterName;
	private String submitterLastName;
	private String submitterSecondLastName;
	private String contactPhone;
	private String saleChannel;
	private Collection groupings;
	private Date estimatedExecutionDate;
	private String jorney;
	private String coordinateX;
	private String coordinateY;

	public String getClientDocumentNumber() {
		return clientDocumentNumber;
	}
	public String getClientDocumentType() {
		return clientDocumentType;
	}
	public String getClientDocumentVerification() {
		return clientDocumentVerification;
	}
	public String getSubmitterName() {
		return submitterName;
	}
	public void setClientDocumentNumber(String string) {
		clientDocumentNumber = string;
	}
	public void setClientDocumentType(String string) {
		clientDocumentType = string;
	}
	public void setClientDocumentVerification(String string) {
		clientDocumentVerification = string;
	}
	public void setSubmitterName(String string) {
		submitterName = string;
	}
	public String getClientType() {
		return clientType;
	}
	public void setClientType(String string) {
		clientType = string;
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
	public long getClientSegmentCode() {
		return clientSegmentCode;
	}
	public long getClientSubSegmentCode() {
		return clientSubSegmentCode;
	}
	public String getContactPhone() {
		return contactPhone;
	}
	public Date getEmittingDate() {
		return emittingDate;
	}
	public String getEmittingUser() {
		return emittingUser;
	}
	public Collection getGroupings() {
		return groupings;
	}
	public String getObservations() {
		return observations;
	}
	public long getRequestNumber() {
		return requestNumber;
	}
	/**
	 * @return Devuelve clientCuenta.
	 */
	public long getAccountCode() {
		return accountCode;
	}
	/**
	 * @param clientCuenta El clientCuenta a establecer.
	 */
	public void setAccountCode(long clientCuenta) {
		this.accountCode = clientCuenta;
	}
	public String getSaleChannel() {
		return saleChannel;
	}
	public String getSubmitterLastName() {
		return submitterLastName;
	}
	public String getSubmitterSecondLastName() {
		return submitterSecondLastName;
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
	public void setClientSegmentCode(long l) {
		clientSegmentCode = l;
	}
	public void setClientSubSegmentCode(long l) {
		clientSubSegmentCode = l;
	}
	public void setContactPhone(String string) {
		contactPhone = string;
	}
	public void setEmittingDate(Date date) {
		emittingDate = date;
	}
	public void setEmittingUser(String string) {
		emittingUser = string;
	}
	public void setGroupings(Collection collection) {
		groupings = collection;
	}
	public void setObservations(String string) {
		observations = string;
	}
	public void setRequestNumber(long l) {
		requestNumber = l;
	}
	public void setSaleChannel(String string) {
		saleChannel = string;
	}
	public void setSubmitterLastName(String string) {
		submitterLastName = string;
	}
	public void setSubmitterSecondLastName(String string) {
		submitterSecondLastName = string;
	}
	
	public Date getEstimatedExecutionDate() {
		return estimatedExecutionDate;
	}

	public void setEstimatedExecutionDate(Date date) {
		estimatedExecutionDate = date;
	}

	public int hashCode() {
		return clientDocumentNumber.hashCode();
	}	
	
	public boolean equals(Object arg0) {
		if (arg0 instanceof TR001S) {
			TR001S other = (TR001S) arg0;
			return super.equals(arg0) &&
				requestNumber == other.requestNumber &&
				EqualityUtilities.equals(emittingUser, other.emittingUser) &&
				EqualityUtilities.equals(emittingDate, other.emittingDate) &&
				EqualityUtilities.equals(observations, other.observations) &&
				EqualityUtilities.equals(clientDocumentType, other.clientDocumentType) &&
				EqualityUtilities.equals(clientDocumentNumber, other.clientDocumentNumber) &&
				EqualityUtilities.equals(clientDocumentVerification,other.clientDocumentVerification) &&
				EqualityUtilities.equals(clientName, other.clientName) &&
				EqualityUtilities.equals(clientLastName, other.clientLastName) &&
				EqualityUtilities.equals(clientSecondLastName, other.clientSecondLastName) &&
				clientSegmentCode == other.clientSegmentCode &&
				clientCode == other.clientCode &&
				accountCode == other.accountCode &&
				clientSubSegmentCode == other.clientSubSegmentCode &&
				accountSegmentCode == other.accountSegmentCode &&
				accountSubsegmentCode == other.accountSubsegmentCode &&
				EqualityUtilities.equals(clientType, other.clientType) &&
				EqualityUtilities.equals(submitterName,other.submitterName) &&
				EqualityUtilities.equals(submitterLastName, other.submitterLastName) &&
				EqualityUtilities.equals(submitterSecondLastName, other.submitterSecondLastName) &&
				EqualityUtilities.equals(contactPhone, other.contactPhone) &&
				EqualityUtilities.equals(saleChannel, other.saleChannel) &&
				EqualityUtilities.equals(groupings, other.groupings) &&
				EqualityUtilities.equals(jorney, other.jorney) &&
				EqualityUtilities.equals(coordinateX, other.coordinateX) &&
				EqualityUtilities.equals(coordinateY, other.coordinateY);				
		}
		
		return false;
	}


	public long getAccountSegmentCode() {
		return accountSegmentCode;
	}

	public long getAccountSubsegmentCode() {
		return accountSubsegmentCode;
	}

	public void setAccountSegmentCode(long l) {
		accountSegmentCode = l;
	}

	public void setAccountSubsegmentCode(long l) {
		accountSubsegmentCode = l;
	}

	public long getClientCode() {
		return clientCode;
	}
	public void setClientCode(long clientCode) {
		this.clientCode = clientCode;
	}
	public String getCoordinateX() {
		return coordinateX;
	}
	public void setCoordinateX(String coordinateX) {
		this.coordinateX = coordinateX;
	}
	public String getCoordinateY() {
		return coordinateY;
	}
	public void setCoordinateY(String coordinateY) {
		this.coordinateY = coordinateY;
	}
	public String getJorney() {
		return jorney;
	}
	public void setJorney(String jorney) {
		this.jorney = jorney;
	}
}
