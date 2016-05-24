//- Copyright Notice
//-----------------------------------------------------------------------
// (C) Copyright 2007 Telefonica Colombia, http://www.telefonica.com.co
// Bogota, Colombia All Rights Reserved
// THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF Telefonica Colombia
// The copyright notice above does not evidence any actual or intended
// publication of such source code.
//
// $Id: TR028SEquipment.java,v 1.1 2011/03/30 18:25:34 lfmartinez Exp $
//-----------------------------------------------------------------------

package co.com.telefonica.atiempo.interfaces.atiempo;

import java.io.Serializable;

import co.com.telefonica.atiempo.util.EqualityUtilities;

/**
 * @author ylapchik
 * @version $Revision: 1.1 $
 */
public class TR028SEquipment implements Serializable {

	private String elementSerial;
	private String elementType;
	private String equipmentType;
	private String inventoryType;
	private Long errorCode;
	private String errorCodeMessage;
	

	public String getInventoryType() {
		return inventoryType;
	}
	public void setInventoryType(String inventoryType) {
		this.inventoryType = inventoryType;
	}
	public String getElementSerial() {
		return elementSerial;
	}
	public void setElementSerial(String elementSerial) {
		this.elementSerial = elementSerial;
	}
	public String getElementType() {
		return elementType;
	}
	public void setElementType(String elementType) {
		this.elementType = elementType;
	}
	public String getEquipmentType() {
		return equipmentType;
	}
	public void setEquipmentType(String equipmentType) {
		this.equipmentType = equipmentType;
	}
	
	public boolean equals(Object arg0) {
		if (arg0 instanceof TR028SEquipment) {
			TR028SEquipment other = (TR028SEquipment) arg0;
			return EqualityUtilities.equals(elementSerial, other.elementSerial) &&
					EqualityUtilities.equals(elementType, other.elementType) &&
					EqualityUtilities.equals(errorCodeMessage, other.errorCodeMessage) &&
					EqualityUtilities.equals(errorCodeMessage, other.errorCodeMessage) &&
					EqualityUtilities.equals(errorCode, other.errorCode) &&					
					EqualityUtilities.equals(equipmentType, other.equipmentType);
		}
		return false;
	}

	public Long getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(Long errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorCodeMessage() {
		return errorCodeMessage;
	}
	public void setErrorCodeMessage(String errorCodeMessage) {
		this.errorCodeMessage = errorCodeMessage;
	}
}
