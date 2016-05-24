//- Copyright Notice
//-----------------------------------------------------------------------
// (C) Copyright 2007 Telefonica Colombia, http://www.telefonica.com.co
// Bogota, Colombia All Rights Reserved
// THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF Telefonica Colombia
// The copyright notice above does not evidence any actual or intended
// publication of such source code.
//
// $Id: SubRequest.java,v 1.1 2011/03/30 18:21:06 lfmartinez Exp $
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
public class SubRequest implements Serializable {
	
	private long productServiceCode;
	private String productServiceName;
	private long comercialOperation;
	private long productServiceType;
	private String quantityOfProductServices;
	private String serviceReferenceId;
	private Date serviceInitialDate;
	private Date serviceEndDate;
	private String observations;
	private int code;
	private long contractNumber;
	private Collection characteristic;
	
	public Collection getCharacteristic() {
		return characteristic;
	}

	public int getCode() {
		return code;
	}

	public long getComercialOperation() {
		return comercialOperation;
	}

	public String getObservations() {
		return observations;
	}

	public long getProductServiceCode() {
		return productServiceCode;
	}

	public String getProductServiceName() {
		return productServiceName;
	}

	public long getProductServiceType() {
		return productServiceType;
	}

	public String getQuantityOfProductServices() {
		return quantityOfProductServices;
	}

	public Date getServiceEndDate() {
		return serviceEndDate;
	}

	public Date getServiceInitialDate() {
		return serviceInitialDate;
	}

	public void setCharacteristic(Collection collection) {
		characteristic = collection;
	}

	public void setCode(int l) {
		code = l;
	}

	public void setComercialOperation(long string) {
		comercialOperation = string;
	}

	public void setObservations(String string) {
		observations = string;
	}

	public void setProductServiceCode(long string) {
		productServiceCode = string;
	}

	public void setProductServiceName(String string) {
		productServiceName = string;
	}

	public void setProductServiceType(long l) {
		productServiceType = l;
	}

	public void setQuantityOfProductServices(String string) {
		quantityOfProductServices = string;
	}

	public void setServiceEndDate(Date date) {
		serviceEndDate = date;
	}

	public void setServiceInitialDate(Date date) {
		serviceInitialDate = date;
	}
	
	public long getContractNumber() {
		return contractNumber;
	}

	public void setContractNumber(long l) {
		contractNumber = l;
	}

	public int hashCode() {
			return observations.hashCode();
	}
	
	public boolean equals(Object arg0) {
		if (arg0 instanceof SubRequest) {
			SubRequest other = (SubRequest) arg0;
			return productServiceCode == other.productServiceCode
				&& EqualityUtilities.equals(productServiceName, other.productServiceName)
				&& comercialOperation == other.comercialOperation
				&& productServiceType == other.productServiceType
				&& EqualityUtilities.equals(quantityOfProductServices, other.quantityOfProductServices)
				&& EqualityUtilities.equals(serviceReferenceId, other.serviceReferenceId)
				&& EqualityUtilities.equals(serviceInitialDate, other.serviceInitialDate)
				&& EqualityUtilities.equals(serviceEndDate, other.serviceEndDate)
				&& EqualityUtilities.equals(observations, other.observations)
				&& code == other.code
				&& contractNumber == other.contractNumber
				&& EqualityUtilities.equals(characteristic, other.characteristic);
		}
		return false;
	}

	public String getServiceReferenceId() {
		return serviceReferenceId;
	}

	public void setServiceReferenceId(String string) {
		serviceReferenceId = string;
	}

}	