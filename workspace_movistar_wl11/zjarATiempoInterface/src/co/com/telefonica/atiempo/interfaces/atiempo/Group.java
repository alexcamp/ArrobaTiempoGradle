//- Copyright Notice
//-----------------------------------------------------------------------
// (C) Copyright 2007 Telefonica Colombia, http://www.telefonica.com.co
// Bogota, Colombia All Rights Reserved
// THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF Telefonica Colombia
// The copyright notice above does not evidence any actual or intended
// publication of such source code.
//
// $Id: Group.java,v 1.1 2011/03/30 18:20:03 lfmartinez Exp $
//-----------------------------------------------------------------------

package co.com.telefonica.atiempo.interfaces.atiempo;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import co.com.telefonica.atiempo.util.EqualityUtilities;

/**
 * @author fmaceachen
 * @version $Revision: 1.1 $
 */
public class Group implements Serializable {
	
	private Date commitmentDate;
	private String observations;
	private int code;
	private int fatherGroupCode;
	private int childrenGroupCount;
	private String comercialProductIdentificationNumber;
	private String comercialProductIdentification;
	private long comercialOperationType;
	private long comercialProductCode;
	private long fatherComercialProductCode;
	private long comercialProductType;
	private long comercialProductSubtype;
	private long useTypeCode;
	private String useTypeName;
	private String fatherIdentificationLine;
	private Address address;
	private Collection subRequests;
	private Date estimatedExecutionDate;
	
	
		
	public int getCode() {
		return code;
	}
	public int getChildrenGroupCount() {
		return childrenGroupCount;
	}
	public void setChildrenGroupCount(int i) {
		childrenGroupCount = i;
	}
	public Date getCommitmentDate() {
		return commitmentDate;
	}
	public int getFatherGroupCode() {
		return fatherGroupCode;
	}
	public String getObservations() {
		return observations;
	}
	public Collection getSubRequests() {
		return subRequests;
	}
	public void setCode(int i) {
		code = i;
	}
	public void setCommitmentDate(Date date) {
		commitmentDate = date;
	}
	public void setFatherGroupCode(int i) {
		fatherGroupCode = i;
	}
	public void setObservations(String i) {
		observations = i;
	}
	public void setSubRequests(Collection collection) {
		subRequests = collection;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public long getComercialOperationType() {
		return comercialOperationType;
	}
	public long getComercialProductCode() {
		return comercialProductCode;
	}
	public String getComercialProductIdentification() {
		return comercialProductIdentification;
	}
	public String getComercialProductIdentificationNumber() {
		return comercialProductIdentificationNumber;
	}
	public long getComercialProductSubtype() {
		return comercialProductSubtype;
	}
	public long getComercialProductType() {
		return comercialProductType;
	}
	public long getFatherComercialProductCode() {
		return fatherComercialProductCode;
	}
	public long getUseTypeCode() {
		return useTypeCode;
	}
	public String getUseTypeName() {
		return useTypeName;
	}
	public void setComercialOperationType(long l) {
		comercialOperationType = l;
	}
	public void setComercialProductCode(long l) {
		comercialProductCode = l;
	}
	public void setComercialProductIdentification(String string) {
		comercialProductIdentification = string;
	}
	public void setComercialProductIdentificationNumber(String string) {
		comercialProductIdentificationNumber = string;
	}
	public void setComercialProductSubtype(long l) {
		comercialProductSubtype = l;
	}
	public void setComercialProductType(long l) {
		comercialProductType = l;
	}
	public void setFatherComercialProductCode(long l) {
		fatherComercialProductCode = l;
	}
	public void setUseTypeCode(long l) {
		useTypeCode = l;
	}
	public void setUseTypeName(String string) {
		useTypeName = string;
	}
	public String getFatherIdentificationLine() {
		return fatherIdentificationLine;
	}
	public void setFatherIdentificationLine(String string) {
		fatherIdentificationLine = string;
	}
	
	public Date getEstimatedExecutionDate() {
		return estimatedExecutionDate;
	}

	public void setEstimatedExecutionDate(Date date) {
		estimatedExecutionDate = date;
	}
	
	public int hashCode() {
			return code;
	}
	
	public boolean equals(Object arg0) {
		if (arg0 instanceof Group) {
			Group other = (Group) arg0;
			return EqualityUtilities.equals(commitmentDate, other.commitmentDate) &&
				EqualityUtilities.equals(observations, other.observations) &&
				code == other.code &&
				fatherGroupCode == other.fatherGroupCode &&
				childrenGroupCount == other.childrenGroupCount &&
				EqualityUtilities.equals(comercialProductIdentificationNumber, other.comercialProductIdentificationNumber) &&
				EqualityUtilities.equals(comercialProductIdentification, other.comercialProductIdentification) &&
				comercialOperationType == other.comercialOperationType &&
				comercialProductCode == other.comercialProductCode &&
				fatherComercialProductCode == other.fatherComercialProductCode &&
				comercialProductType == other.comercialProductType &&
				comercialProductSubtype == other.comercialProductSubtype &&
				useTypeCode == other.useTypeCode &&
				EqualityUtilities.equals(useTypeName, other.useTypeName) &&
				EqualityUtilities.equals(fatherIdentificationLine, other.fatherIdentificationLine) &&
				EqualityUtilities.equals(address, other.address) &&
				EqualityUtilities.equals(subRequests, other.subRequests) &&
				EqualityUtilities.equals(estimatedExecutionDate, other.estimatedExecutionDate);
		}
		
		return false;
	}

	
	

}
