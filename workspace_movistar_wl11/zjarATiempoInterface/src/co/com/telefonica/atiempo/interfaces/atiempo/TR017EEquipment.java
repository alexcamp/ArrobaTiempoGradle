//- Copyright Notice
//-----------------------------------------------------------------------
// (C) Copyright 2007 Telefonica Colombia, http://www.telefonica.com.co
// Bogota, Colombia All Rights Reserved
// THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF Telefonica Colombia
// The copyright notice above does not evidence any actual or intended
// publication of such source code.
//
// $Id: TR017EEquipment.java,v 1.1 2011/03/30 18:24:04 lfmartinez Exp $
//-----------------------------------------------------------------------

package co.com.telefonica.atiempo.interfaces.atiempo;

import java.io.Serializable;

import co.com.telefonica.atiempo.util.EqualityUtilities;

/**
 * @author ylapchik
 * @version $Revision: 1.1 $
 */
public class TR017EEquipment implements Serializable {
	
	private String casId;
	private String cardSerial;
	private long productServiceCode;
	private long commercialOperation;
	private String decoSerial;
	private String decoType;
	private String decoBrand;

	public long getCommercialOperation() {
		return commercialOperation;
	}

	public long getProductServiceCode() {
		return productServiceCode;
	}

	public void setCommercialOperation(long l) {
		commercialOperation = l;
	}

	public void setProductServiceCode(long l) {
		productServiceCode = l;
	}

	public String getCardSerial() {
		return cardSerial;
	}

	public String getCasId() {
		return casId;
	}

	public void setCardSerial(String string) {
		cardSerial = string;
	}

	public void setCasId(String string) {
		casId = string;
	}
	
	public int hashCode() {
		return casId.hashCode();
	}

	public String getDecoBrand() {
		return decoBrand;
	}
	
	public void setDecoBrand(String decoBrand) {
		this.decoBrand = decoBrand;
	}
	
	public String getDecoSerial() {
		return decoSerial;
	}
	
	public void setDecoSerial(String decoSerial) {
		this.decoSerial = decoSerial;
	}
	
	public String getDecoType() {
		return decoType;
	}
	
	public void setDecoType(String decoType) {
		this.decoType = decoType;
	}
	
	public boolean equals(Object arg0) {
		if (arg0 instanceof TR017EEquipment) {
			TR017EEquipment other = (TR017EEquipment) arg0;
			return EqualityUtilities.equals(casId, other.casId) &&
				EqualityUtilities.equals(cardSerial, other.cardSerial) &&
				productServiceCode == other.productServiceCode &&
				commercialOperation == other.commercialOperation &&
				EqualityUtilities.equals(decoSerial, other.decoSerial) &&
				EqualityUtilities.equals(decoType, other.decoType) &&
				EqualityUtilities.equals(decoBrand, other.decoBrand);
		}
		return false;
	}

}
