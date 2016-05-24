//- Copyright Notice
//-----------------------------------------------------------------------
// (C) Copyright 2007 Telefonica Colombia, http://www.telefonica.com.co
// Bogota, Colombia All Rights Reserved
// THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF Telefonica Colombia
// The copyright notice above does not evidence any actual or intended
// publication of such source code.
//
// $Id: TR005S.java,v 1.1 2011/03/30 18:23:29 lfmartinez Exp $
//-----------------------------------------------------------------------

package co.com.telefonica.atiempo.interfaces.atiempo;

import java.util.Date;
import co.com.telefonica.atiempo.util.EqualityUtilities;

/**
 * @author gminos
 * @version $Revision: 1.1 $
 */
public class TR005S extends ResponseHeader {

	private long breakdownNumber;
	private long clientCode;
	private long contractedProductServiceCode;
	private String comercialProductIdentification;
	private String comercialProductIdentificationNumber;
	private String comercialProductIdentificationNumberTV;
	private String breakdownOpenCode;
	private String breakdownCloseCode;
	private String breakdownSymptomCode;
	private String breakdownTestResponseCode;	
	private long managementAreaCode;
	private char breakdownStatusCode;	
	private long requestNumber;
	private long massiveBreakdownCode;
	private long breakdownOriginalCode;
	private String climantDocumentType;
	private String climantDocumentNumber;
	private String climantDocumentVerification;
	private String climantName;
	private String climantFirstLastname;
	private String climantSecondLastname;
	private String clientRelationshipCode;
	private String climantRelationshipOtherDescription;
	private char notificationWayCode;
	private String contactName;
	private String contactPhone;
	private String contactName2;
	private String contactPhone2;
	private char communicationMediaCode;
	private String categoryCode;
	private char breakdownPriorityCode;
	private long maxResolutionTerm;
	private long daysToAlarm;	
	private char answerIndicator;
	private char collectIndicator;
	private char delayIndicator;
	private char breakdownReiterateIndicator;
	private char extremeDefectIndicator;
	private char lackOfServiceIndicator;
	private char materialAmountIndicator;
	private char completedServiceIndicator;
	private char updatedEquipmentIndicator;
	private char makeAppoitmentIndicator;
	private org.exolab.castor.types.Date appoitmentDate;
	private Date breakdownCommitmentDate;
	private Date breakdownOpenDate;
	private Date breakdownCloseDate;
	private String secondLineNumber;
	private char breakdownSource;
	private String breakdownCancelationCode;
	private String breakdownTestDescription;
	private String emittingUser;
	private String lastModificationUser;	
	private Date emmitingTime;
	private Date lastModificationTime;
	private int informationElementLength;
	private String appoitmentObservation;
	private int informationElementLengthSynonym;
	private String breakdownObservation;
	private String breakdownTypeCode;
	private String massiveBreakdownSyntomCode;
	private long productServiceCode;
	private String massiveBreakdownStatusCode;
	private String massiveBreakdownCommitmentDate;
	private String massiveBreakdownOpenDate;
	private String massiveBreakdownCloseDate;
	private String massiveBreakdownExternalCode;
	private int informationComponentLengthSynonym;
	private String massiveBreakdownObservation;
	private String massiveBreakdownMotive;
	private String streetType;
	private String streetName;
	private String streetNumber;
	private String complementsDescription1;
	private String complementsDescription2;
	private String departmentCode;
	private String cityCode;
	private long accountSegmentCode; 
	private long accountSubsegmentCode;
	private String coordenate_x;
	private String coordenate_y;
//	private int graniteCode;
	
	
	
	/**
	 * @return Returns the accountSegmentCode.
	 */
	public long getAccountSegmentCode() {
		return accountSegmentCode;
	}
	/**
	 * @param accountSegmentCode The accountSegmentCode to set.
	 */
	public void setAccountSegmentCode(long accountSegmentCode) {
		this.accountSegmentCode = accountSegmentCode;
	}
	/**
	 * @return Returns the accountSubsegmentCode.
	 */
	public long getAccountSubsegmentCode() {
		return accountSubsegmentCode;
	}
	/**
	 * @param accountSubsegmentCode The accountSubsegmentCode to set.
	 */
	public void setAccountSubsegmentCode(long accountSubsegmentCode) {
		this.accountSubsegmentCode = accountSubsegmentCode;
	}		
	
/*
	public int getGraniteCode() {
		return graniteCode;
	}
	public void setGraniteCode(int graniteCode) {
		this.graniteCode = graniteCode;
	}
	public int hashCode() {
		return -1;
	}
*/	
	public boolean equals(Object arg0) {
		if (arg0 instanceof TR005S) {
			TR005S other = (TR005S) arg0;
			return super.equals(arg0) &&
			this.answerIndicator == other.answerIndicator
			&& EqualityUtilities.equals(this.appoitmentDate,other.appoitmentDate)
			&& EqualityUtilities.equals(this.appoitmentObservation,other.appoitmentObservation)
			&& EqualityUtilities.equals(this.breakdownCancelationCode,other.breakdownCancelationCode)
			&& EqualityUtilities.equals(this.breakdownCloseCode,other.breakdownCloseCode)
			&& EqualityUtilities.equals(this.breakdownCloseDate,other.breakdownCloseDate)
			&& EqualityUtilities.equals(this.breakdownCommitmentDate,other.breakdownCommitmentDate)
			&& this.breakdownNumber == other.breakdownNumber
			&& EqualityUtilities.equals(this.breakdownObservation,other.breakdownObservation)
			&& EqualityUtilities.equals(this.breakdownOpenCode,other.breakdownOpenCode)
			&& EqualityUtilities.equals(this.breakdownOpenDate,other.breakdownOpenDate)
			&& this.breakdownPriorityCode == other.breakdownPriorityCode
			&& this.breakdownReiterateIndicator == other.breakdownReiterateIndicator
			&& this.breakdownSource == other.breakdownSource
			&& this.accountSegmentCode == other.accountSegmentCode
			&& this.accountSubsegmentCode == other.accountSubsegmentCode
			&& this.breakdownStatusCode == other.breakdownStatusCode
			&& EqualityUtilities.equals(this.breakdownSymptomCode,other.breakdownSymptomCode)
			&& EqualityUtilities.equals(this.breakdownTestDescription,other.breakdownTestDescription)
			&& EqualityUtilities.equals(this.breakdownTestResponseCode,other.breakdownTestResponseCode)
			&& EqualityUtilities.equals(this.breakdownTypeCode,other.breakdownTypeCode)
			&& this.breakdownOriginalCode == other.breakdownOriginalCode
			&& EqualityUtilities.equals(this.categoryCode,other.categoryCode)
			&& EqualityUtilities.equals(this.clientRelationshipCode,other.clientRelationshipCode)
			&& EqualityUtilities.equals(this.climantDocumentNumber,other.climantDocumentNumber)
			&& EqualityUtilities.equals(this.climantDocumentType,other.climantDocumentType)
			&& EqualityUtilities.equals(this.climantDocumentVerification,other.climantDocumentVerification)
			&& EqualityUtilities.equals(this.climantFirstLastname,other.climantFirstLastname)
			&& EqualityUtilities.equals(this.climantName,other.climantName)
			&& EqualityUtilities.equals(this.climantRelationshipOtherDescription,other.climantRelationshipOtherDescription)
			&& EqualityUtilities.equals(this.climantSecondLastname,other.climantSecondLastname)
			&& this.collectIndicator == other.collectIndicator
			&& EqualityUtilities.equals(this.comercialProductIdentification,other.comercialProductIdentification)
			&& EqualityUtilities.equals(this.comercialProductIdentificationNumber,other.comercialProductIdentificationNumber)
			&& this.communicationMediaCode == other.communicationMediaCode
			&& EqualityUtilities.equals(this.complementsDescription1,other.complementsDescription1)
			&& EqualityUtilities.equals(this.complementsDescription2,other.complementsDescription2)
			&& this.completedServiceIndicator == other.completedServiceIndicator
			&& EqualityUtilities.equals(this.contactName,other.contactName)
			&& EqualityUtilities.equals(this.contactName2,other.contactName2)
			&& EqualityUtilities.equals(this.contactPhone,other.contactPhone)
			&& EqualityUtilities.equals(this.contactPhone2,other.contactPhone2)
			&& contractedProductServiceCode == other.contractedProductServiceCode
			&& this.collectIndicator == other.collectIndicator
			&& this.delayIndicator == other.delayIndicator
			&& this.daysToAlarm == other.daysToAlarm
			&& EqualityUtilities.equals(this.emittingUser,other.emittingUser)
			&& EqualityUtilities.equals(this.emmitingTime,other.emmitingTime)
			&& this.extremeDefectIndicator == other.extremeDefectIndicator
			&& this.informationComponentLengthSynonym == other.informationComponentLengthSynonym
			&& this.informationElementLength == other.informationElementLength
			&& this.informationElementLengthSynonym == other.informationElementLengthSynonym
			&& this.lackOfServiceIndicator == other.lackOfServiceIndicator
			&& EqualityUtilities.equals(this.lastModificationTime,other.lastModificationTime)
			&& EqualityUtilities.equals(this.lastModificationUser,other.lastModificationUser)
			&& this.makeAppoitmentIndicator == other.makeAppoitmentIndicator 
			&& this.managementAreaCode == other.managementAreaCode 
			&& EqualityUtilities.equals(this.massiveBreakdownCloseDate,other.massiveBreakdownCloseDate)
			&& this.massiveBreakdownCode == other.massiveBreakdownCode 
			&& EqualityUtilities.equals(this.massiveBreakdownCommitmentDate,other.massiveBreakdownCommitmentDate)
			&& EqualityUtilities.equals(this.massiveBreakdownExternalCode,other.massiveBreakdownExternalCode)
			&& EqualityUtilities.equals(this.massiveBreakdownMotive,other.massiveBreakdownMotive)
			&& EqualityUtilities.equals(this.massiveBreakdownObservation,other.massiveBreakdownObservation)
			&& EqualityUtilities.equals(this.massiveBreakdownOpenDate,other.massiveBreakdownOpenDate)
			&& EqualityUtilities.equals(this.massiveBreakdownStatusCode,other.massiveBreakdownStatusCode)
			&& EqualityUtilities.equals(this.massiveBreakdownSyntomCode,other.massiveBreakdownSyntomCode)
			&& this.materialAmountIndicator == other.materialAmountIndicator
			&& this.managementAreaCode == other.managementAreaCode
			&& this.maxResolutionTerm == other.maxResolutionTerm 
			&& this.notificationWayCode == other.notificationWayCode
			&& this.productServiceCode == other.productServiceCode
			&& this.requestNumber == other.requestNumber
			&& EqualityUtilities.equals(this.secondLineNumber,other.secondLineNumber)
			&& EqualityUtilities.equals(this.streetName,other.streetName)
			&& EqualityUtilities.equals(this.streetNumber,other.streetNumber)
			&& EqualityUtilities.equals(this.streetType,other.streetType)
			&& this.updatedEquipmentIndicator == other.updatedEquipmentIndicator
			&& EqualityUtilities.equals(this.departmentCode,other.departmentCode)
			&& EqualityUtilities.equals(this.cityCode,other.cityCode)
//			&& (graniteCode == other.graniteCode)
			;
		}
		return false;
	}

	/**
	 * @return
	 */
	public char getAnswerIndicator() {
		return answerIndicator;
	}

	/**
	 * @return
	 */
	public org.exolab.castor.types.Date getAppoitmentDate() {
		return appoitmentDate;
	}

	/**
	 * @return
	 */
	public String getAppoitmentObservation() {
		return appoitmentObservation;
	}

	/**
	 * @return
	 */
	public String getBreakdownCancelationCode() {
		return breakdownCancelationCode;
	}

	/**
	 * @return
	 */
	public String getBreakdownCloseCode() {
		return breakdownCloseCode;
	}

	/**
	 * @return
	 */
	public Date getBreakdownCloseDate() {
		return breakdownCloseDate;
	}

	/**
	 * @return
	 */
	public Date getBreakdownCommitmentDate() {
		return breakdownCommitmentDate;
	}

	/**
	 * @return
	 */
	public long getBreakdownNumber() {
		return breakdownNumber;
	}

	/**
	 * @return
	 */
	public String getBreakdownObservation() {
		return breakdownObservation;
	}

	/**
	 * @return
	 */
	public String getBreakdownOpenCode() {
		return breakdownOpenCode;
	}

	/**
	 * @return
	 */
	public Date getBreakdownOpenDate() {
		return breakdownOpenDate;
	}

	/**
	 * @return
	 */
	public long getBreakdownOriginalCode() {
		return breakdownOriginalCode;
	}

	/**
	 * @return
	 */
	public char getBreakdownPriorityCode() {
		return breakdownPriorityCode;
	}

	/**
	 * @return
	 */
	public char getBreakdownReiterateIndicator() {
		return breakdownReiterateIndicator;
	}

	/**
	 * @return
	 */
	public char getBreakdownSource() {
		return breakdownSource;
	}

	/**
	 * @return
	 */
	public char getBreakdownStatusCode() {
		return breakdownStatusCode;
	}

	/**
	 * @return
	 */
	public String getBreakdownSymptomCode() {
		return breakdownSymptomCode;
	}

	/**
	 * @return
	 */
	public String getBreakdownTestDescription() {
		return breakdownTestDescription;
	}

	/**
	 * @return
	 */
	public String getBreakdownTestResponseCode() {
		return breakdownTestResponseCode;
	}

	/**
	 * @return
	 */
	public String getBreakdownTypeCode() {
		return breakdownTypeCode;
	}

	/**
	 * @return
	 */
	public String getCategoryCode() {
		return categoryCode;
	}

	/**
	 * @return
	 */
	public long getClientCode() {
		return clientCode;
	}

	/**
	 * @return
	 */
	public String getClientRelationshipCode() {
		return clientRelationshipCode;
	}

	/**
	 * @return
	 */
	public String getClimantDocumentNumber() {
		return climantDocumentNumber;
	}

	/**
	 * @return
	 */
	public String getClimantDocumentType() {
		return climantDocumentType;
	}

	/**
	 * @return
	 */
	public String getClimantDocumentVerification() {
		return climantDocumentVerification;
	}

	/**
	 * @return
	 */
	public String getClimantFirstLastname() {
		return climantFirstLastname;
	}

	/**
	 * @return
	 */
	public String getClimantName() {
		return climantName;
	}

	/**
	 * @return
	 */
	public String getClimantRelationshipOtherDescription() {
		return climantRelationshipOtherDescription;
	}

	/**
	 * @return
	 */
	public String getClimantSecondLastname() {
		return climantSecondLastname;
	}

	/**
	 * @return
	 */
	public char getCollectIndicator() {
		return collectIndicator;
	}

	/**
	 * @return
	 */
	public String getComercialProductIdentification() {
		return comercialProductIdentification;
	}

	/**
	 * @return
	 */
	public String getComercialProductIdentificationNumber() {
		return comercialProductIdentificationNumber;
	}

	/**
	 * @return
	 */
	public char getCommunicationMediaCode() {
		return communicationMediaCode;
	}

	/**
	 * @return
	 */
	public String getComplementsDescription1() {
		return complementsDescription1;
	}

	/**
	 * @return
	 */
	public String getComplementsDescription2() {
		return complementsDescription2;
	}

	/**
	 * @return
	 */
	public char getCompletedServiceIndicator() {
		return completedServiceIndicator;
	}

	/**
	 * @return
	 */
	public String getContactName() {
		return contactName;
	}

	/**
	 * @return
	 */
	public String getContactName2() {
		return contactName2;
	}

	/**
	 * @return
	 */
	public String getContactPhone() {
		return contactPhone;
	}

	/**
	 * @return
	 */
	public String getContactPhone2() {
		return contactPhone2;
	}

	/**
	 * @return
	 */
	public long getContractedProductServiceCode() {
		return contractedProductServiceCode;
	}

	/**
	 * @return
	 */
	public long getDaysToAlarm() {
		return daysToAlarm;
	}

	/**
	 * @return
	 */
	public char getDelayIndicator() {
		return delayIndicator;
	}

	/**
	 * @return
	 */
	public String getEmittingUser() {
		return emittingUser;
	}

	/**
	 * @return
	 */
	public Date getEmmitingTime() {
		return emmitingTime;
	}

	
	/**
	 * @return
	 */
	public char getExtremeDefectIndicator() {
		return extremeDefectIndicator;
	}

	/**
	 * @return
	 */
	public int getInformationComponentLengthSynonym() {
		return informationComponentLengthSynonym;
	}

	/**
	 * @return
	 */
	public int getInformationElementLength() {
		return informationElementLength;
	}

	/**
	 * @return
	 */
	public int getInformationElementLengthSynonym() {
		return informationElementLengthSynonym;
	}

	/**
	 * @return
	 */
	public char getLackOfServiceIndicator() {
		return lackOfServiceIndicator;
	}

	/**
	 * @return
	 */
	public Date getLastModificationTime() {
		return lastModificationTime;
	}

	/**
	 * @return
	 */
	public String getLastModificationUser() {
		return lastModificationUser;
	}

	/**
	 * @return
	 */
	public char getMakeAppoitmentIndicator() {
		return makeAppoitmentIndicator;
	}

	/**
	 * @return
	 */
	public long getManagementAreaCode() {
		return managementAreaCode;
	}

	/**
	 * @return
	 */
	public String getMassiveBreakdownCloseDate() {
		return massiveBreakdownCloseDate;
	}

	/**
	 * @return
	 */
	public long getMassiveBreakdownCode() {
		return massiveBreakdownCode;
	}

	/**
	 * @return
	 */
	public String getMassiveBreakdownCommitmentDate() {
		return massiveBreakdownCommitmentDate;
	}

	/**
	 * @return
	 */
	public String getMassiveBreakdownExternalCode() {
		return massiveBreakdownExternalCode;
	}

	/**
	 * @return
	 */
	public String getMassiveBreakdownMotive() {
		return massiveBreakdownMotive;
	}

	/**
	 * @return
	 */
	public String getMassiveBreakdownObservation() {
		return massiveBreakdownObservation;
	}

	/**
	 * @return
	 */
	public String getMassiveBreakdownOpenDate() {
		return massiveBreakdownOpenDate;
	}

	/**
	 * @return
	 */
	public String getMassiveBreakdownStatusCode() {
		return massiveBreakdownStatusCode;
	}

	/**
	 * @return
	 */
	public String getMassiveBreakdownSyntomCode() {
		return massiveBreakdownSyntomCode;
	}

	/**
	 * @return
	 */
	public char getMaterialAmountIndicator() {
		return materialAmountIndicator;
	}

	/**
	 * @return
	 */
	public long getMaxResolutionTerm() {
		return maxResolutionTerm;
	}

	/**
	 * @return
	 */
	public char getNotificationWayCode() {
		return notificationWayCode;
	}

	/**
	 * @return
	 */
	public long getProductServiceCode() {
		return productServiceCode;
	}

	/**
	 * @return
	 */
	public long getRequestNumber() {
		return requestNumber;
	}

	/**
	 * @return
	 */
	public String getSecondLineNumber() {
		return secondLineNumber;
	}

	/**
	 * @return
	 */
	public String getStreetName() {
		return streetName;
	}

	/**
	 * @return
	 */
	public String getStreetNumber() {
		return streetNumber;
	}

	/**
	 * @return
	 */
	public String getStreetType() {
		return streetType;
	}

	/**
	 * @return
	 */
	public char getUpdatedEquipmentIndicator() {
		return updatedEquipmentIndicator;
	}

	/**
	 * @param string
	 */
	public void setAnswerIndicator(char string) {
		answerIndicator = string;
	}

	/**
	 * @param string
	 */
	public void setAppoitmentDate(org.exolab.castor.types.Date date) {
		appoitmentDate = date;
	}

	/**
	 * @param string
	 */
	public void setAppoitmentObservation(String string) {
		appoitmentObservation = string;
	}

	/**
	 * @param string
	 */
	public void setBreakdownCancelationCode(String string) {
		breakdownCancelationCode = string;
	}

	/**
	 * @param string
	 */
	public void setBreakdownCloseCode(String string) {
		breakdownCloseCode = string;
	}

	/**
	 * @param date
	 */
	public void setBreakdownCloseDate(Date date) {
		breakdownCloseDate = date;
	}

	/**
	 * @param date
	 */
	public void setBreakdownCommitmentDate(Date date) {
		breakdownCommitmentDate = date;
	}

	/**
	 * @param l
	 */
	public void setBreakdownNumber(long l) {
		breakdownNumber = l;
	}

	/**
	 * @param string
	 */
	public void setBreakdownObservation(String string) {
		breakdownObservation = string;
	}

	/**
	 * @param string
	 */
	public void setBreakdownOpenCode(String string) {
		breakdownOpenCode = string;
	}

	/**
	 * @param date
	 */
	public void setBreakdownOpenDate(Date date) {
		breakdownOpenDate = date;
	}

	/**
	 * @param l
	 */
	public void setBreakdownOriginalCode(long l) {
		breakdownOriginalCode = l;
	}

	/**
	 * @param string
	 */
	public void setBreakdownPriorityCode(char string) {
		breakdownPriorityCode = string;
	}

	/**
	 * @param string
	 */
	public void setBreakdownReiterateIndicator(char string) {
		breakdownReiterateIndicator = string;
	}

	/**
	 * @param string
	 */
	public void setBreakdownSource(char string) {
		breakdownSource = string;
	}

	/**
	 * @param string
	 */
	public void setBreakdownStatusCode(char string) {
		breakdownStatusCode = string;
	}

	/**
	 * @param string
	 */
	public void setBreakdownSymptomCode(String string) {
		breakdownSymptomCode = string;
	}

	/**
	 * @param string
	 */
	public void setBreakdownTestDescription(String string) {
		breakdownTestDescription = string;
	}

	/**
	 * @param string
	 */
	public void setBreakdownTestResponseCode(String string) {
		breakdownTestResponseCode = string;
	}

	/**
	 * @param string
	 */
	public void setBreakdownTypeCode(String string) {
		breakdownTypeCode = string;
	}

	/**
	 * @param string
	 */
	public void setCategoryCode(String string) {
		categoryCode = string;
	}

	/**
	 * @param l
	 */
	public void setClientCode(long l) {
		clientCode = l;
	}

	/**
	 * @param string
	 */
	public void setClientRelationshipCode(String string) {
		clientRelationshipCode = string;
	}

	/**
	 * @param string
	 */
	public void setClimantDocumentNumber(String string) {
		climantDocumentNumber = string;
	}

	/**
	 * @param string
	 */
	public void setClimantDocumentType(String string) {
		climantDocumentType = string;
	}

	/**
	 * @param string
	 */
	public void setClimantDocumentVerification(String string) {
		climantDocumentVerification = string;
	}

	/**
	 * @param string
	 */
	public void setClimantFirstLastname(String string) {
		climantFirstLastname = string;
	}

	/**
	 * @param string
	 */
	public void setClimantName(String string) {
		climantName = string;
	}

	/**
	 * @param string
	 */
	public void setClimantRelationshipOtherDescription(String string) {
		climantRelationshipOtherDescription = string;
	}

	/**
	 * @param string
	 */
	public void setClimantSecondLastname(String string) {
		climantSecondLastname = string;
	}

	/**
	 * @param string
	 */
	public void setCollectIndicator(char string) {
		collectIndicator = string;
	}

	/**
	 * @param string
	 */
	public void setComercialProductIdentification(String string) {
		comercialProductIdentification = string;
	}

	/**
	 * @param string
	 */
	public void setComercialProductIdentificationNumber(String string) {
		comercialProductIdentificationNumber = string;
	}

	/**
	 * @param string
	 */
	public void setCommunicationMediaCode(char string) {
		communicationMediaCode = string;
	}

	/**
	 * @param string
	 */
	public void setComplementsDescription1(String string) {
		complementsDescription1 = string;
	}

	/**
	 * @param string
	 */
	public void setComplementsDescription2(String string) {
		complementsDescription2 = string;
	}

	/**
	 * @param string
	 */
	public void setCompletedServiceIndicator(char string) {
		completedServiceIndicator = string;
	}

	/**
	 * @param string
	 */
	public void setContactName(String string) {
		contactName = string;
	}

	/**
	 * @param string
	 */
	public void setContactName2(String string) {
		contactName2 = string;
	}

	/**
	 * @param string
	 */
	public void setContactPhone(String string) {
		contactPhone = string;
	}

	/**
	 * @param string
	 */
	public void setContactPhone2(String string) {
		contactPhone2 = string;
	}

	/**
	 * @param l
	 */
	public void setContractedProductServiceCode(long l) {
		contractedProductServiceCode = l;
	}

	/**
	 * @param l
	 */
	public void setDaysToAlarm(long l) {
		daysToAlarm = l;
	}

	/**
	 * @param string
	 */
	public void setDelayIndicator(char string) {
		delayIndicator = string;
	}

	/**
	 * @param string
	 */
	public void setEmittingUser(String string) {
		emittingUser = string;
	}

	/**
	 * @param date
	 */
	public void setEmmitingTime(Date date) {
		emmitingTime = date;
	}

	
	/**
	 * @param string
	 */
	public void setExtremeDefectIndicator(char string) {
		extremeDefectIndicator = string;
	}

	/**
	 * @param i
	 */
	public void setInformationComponentLengthSynonym(int i) {
		informationComponentLengthSynonym = i;
	}

	/**
	 * @param i
	 */
	public void setInformationElementLength(int i) {
		informationElementLength = i;
	}

	/**
	 * @param i
	 */
	public void setInformationElementLengthSynonym(int i) {
		informationElementLengthSynonym = i;
	}

	/**
	 * @param string
	 */
	public void setLackOfServiceIndicator(char string) {
		lackOfServiceIndicator = string;
	}

	/**
	 * @param date
	 */
	public void setLastModificationTime(Date date) {
		lastModificationTime = date;
	}

	/**
	 * @param string
	 */
	public void setLastModificationUser(String string) {
		lastModificationUser = string;
	}

	/**
	 * @param string
	 */
	public void setMakeAppoitmentIndicator(char string) {
		makeAppoitmentIndicator = string;
	}

	/**
	 * @param l
	 */
	public void setManagementAreaCode(long l) {
		managementAreaCode = l;
	}

	/**
	 * @param string
	 */
	public void setMassiveBreakdownCloseDate(String string) {
		massiveBreakdownCloseDate = string;
	}

	/**
	 * @param l
	 */
	public void setMassiveBreakdownCode(long l) {
		massiveBreakdownCode = l;
	}

	/**
	 * @param string
	 */
	public void setMassiveBreakdownCommitmentDate(String string) {
		massiveBreakdownCommitmentDate = string;
	}

	/**
	 * @param string
	 */
	public void setMassiveBreakdownExternalCode(String string) {
		massiveBreakdownExternalCode = string;
	}

	/**
	 * @param string
	 */
	public void setMassiveBreakdownMotive(String string) {
		massiveBreakdownMotive = string;
	}

	/**
	 * @param string
	 */
	public void setMassiveBreakdownObservation(String string) {
		massiveBreakdownObservation = string;
	}

	/**
	 * @param string
	 */
	public void setMassiveBreakdownOpenDate(String string) {
		massiveBreakdownOpenDate = string;
	}

	/**
	 * @param string
	 */
	public void setMassiveBreakdownStatusCode(String string) {
		massiveBreakdownStatusCode = string;
	}

	/**
	 * @param string
	 */
	public void setMassiveBreakdownSyntomCode(String string) {
		massiveBreakdownSyntomCode = string;
	}

	/**
	 * @param string
	 */
	public void setMaterialAmountIndicator(char string) {
		materialAmountIndicator = string;
	}

	/**
	 * @param l
	 */
	public void setMaxResolutionTerm(long l) {
		maxResolutionTerm = l;
	}

	/**
	 * @param string
	 */
	public void setNotificationWayCode(char string) {
		notificationWayCode = string;
	}

	/**
	 * @param l
	 */
	public void setProductServiceCode(long l) {
		productServiceCode = l;
	}

	/**
	 * @param l
	 */
	public void setRequestNumber(long l) {
		requestNumber = l;
	}

	/**
	 * @param string
	 */
	public void setSecondLineNumber(String string) {
		secondLineNumber = string;
	}

	/**
	 * @param string
	 */
	public void setStreetName(String string) {
		streetName = string;
	}

	/**
	 * @param string
	 */
	public void setStreetNumber(String string) {
		streetNumber = string;
	}

	/**
	 * @param string
	 */
	public void setStreetType(String string) {
		streetType = string;
	}

	/**
	 * @param string
	 */
	public void setUpdatedEquipmentIndicator(char string) {
		updatedEquipmentIndicator = string;
	}

	/**
	 * @return
	 */
	public String getCityCode() {
		return cityCode;
	}

	/**
	 * @return
	 */
	public String getDepartmentCode() {
		return departmentCode;
	}

	/**
	 * @param string
	 */
	public void setCityCode(String string) {
		cityCode = string;
	}

	/**
	 * @param string
	 */
	public void setDepartmentCode(String string) {
		departmentCode = string;
	}

	/**
	 * @return Returns the coordenate_x.
	 */
	public String getCoordenate_x() {
		return coordenate_x;
	}
	/**
	 * @param coordenate_x The coordenate_x to set.
	 */
	public void setCoordenate_x(String coordenate_x) {
		this.coordenate_x = coordenate_x;
	}
	/**
	 * @return Returns the coordenate_y.
	 */
	public String getCoordenate_y() {
		return coordenate_y;
	}
	/**
	 * @param coordenate_y The coordenate_y to set.
	 */
	public void setCoordenate_y(String coordenate_y) {
		this.coordenate_y = coordenate_y;
	}
	/**
	 * @return Devuelve comercialProductIdentificationNumberTV.
	 */
	public String getComercialProductIdentificationNumberTV() {
		return comercialProductIdentificationNumberTV;
	}
	/**
	 * @param comercialProductIdentificationNumberTV El comercialProductIdentificationNumberTV a establecer.
	 */
	public void setComercialProductIdentificationNumberTV(
			String comercialProductIdentificationNumberTV) {
		this.comercialProductIdentificationNumberTV = comercialProductIdentificationNumberTV;
	}
}
