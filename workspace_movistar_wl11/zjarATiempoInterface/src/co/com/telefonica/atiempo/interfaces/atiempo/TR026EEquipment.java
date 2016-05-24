//- Copyright Notice
//-----------------------------------------------------------------------
// (C) Copyright 2007 Telefonica Colombia, http://www.telefonica.com.co
// Bogota, Colombia All Rights Reserved
// THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF Telefonica Colombia
// The copyright notice above does not evidence any actual or intended
// publication of such source code.
//
// $Id: TR026EEquipment.java,v 1.1 2011/03/30 18:24:44 lfmartinez Exp $
//-----------------------------------------------------------------------

package co.com.telefonica.atiempo.interfaces.atiempo;

import java.io.Serializable;

import co.com.telefonica.atiempo.util.EqualityUtilities;

/**
 * @author ylapchik
 * @version $Revision: 1.1 $
 */
public class TR026EEquipment implements Serializable {

	private String elementSerial;
	private String elementType;
	private String equipmentType;
	
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
		if (arg0 instanceof TR026EEquipment) {
			TR026EEquipment other = (TR026EEquipment) arg0;
			return EqualityUtilities.equals(elementSerial, other.elementSerial) &&
					EqualityUtilities.equals(elementType, other.elementType) &&
					EqualityUtilities.equals(equipmentType, other.equipmentType);
		}
		return false;
	}

}
