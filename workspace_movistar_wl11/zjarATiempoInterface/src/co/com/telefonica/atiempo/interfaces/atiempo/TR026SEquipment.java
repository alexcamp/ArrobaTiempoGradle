//- Copyright Notice
//-----------------------------------------------------------------------
// (C) Copyright 2007 Telefonica Colombia, http://www.telefonica.com.co
// Bogota, Colombia All Rights Reserved
// THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF Telefonica Colombia
// The copyright notice above does not evidence any actual or intended
// publication of such source code.
//
// $Id: TR026SEquipment.java,v 1.2 2011/05/23 13:42:00 lfmartinez Exp $
//-----------------------------------------------------------------------

package co.com.telefonica.atiempo.interfaces.atiempo;

import java.io.Serializable;

import co.com.telefonica.atiempo.util.EqualityUtilities;

/**
 * @author ylapchik
 * @version $Revision: 1.2 $
 */
public class TR026SEquipment implements Serializable {

	private String elementSerial;
	private String elementType;
	private String brand;
	private String model;
	private String equipmentType;
	
	private String cellphone;
	private String materialSAP;
	private String plantSAP;
	private String storageSAP;
	private String numPurchasingDocSAP;
	private int numPositionPurchDocSAP;

	
	public String getElementType() {
		return elementType;
	}
	public void setElementType(String elementType) {
		this.elementType = elementType;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getElementSerial() {
		return elementSerial;
	}
	public void setElementSerial(String elementSerial) {
		this.elementSerial = elementSerial;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getEquipmentType() {
		return equipmentType;
	}
	public void setEquipmentType(String equipmentType) {
		this.equipmentType = equipmentType;
	}	
	/**
	 * @return Returns the materialSAP.
	 */
	public String getMaterialSAP() {
		return materialSAP;
	}
	/**
	 * @param materialSAP The materialSAP to set.
	 */
	public void setMaterialSAP(String materialSAP) {
		this.materialSAP = materialSAP;
	}
	/**
	 * @return Returns the numPositionPurchDocSAP.
	 */
	public int getNumPositionPurchDocSAP() {
		return numPositionPurchDocSAP;
	}
	/**
	 * @param numPositionPurchDocSAP The numPositionPurchDocSAP to set.
	 */
	public void setNumPositionPurchDocSAP(int numPositionPurchDocSAP) {
		this.numPositionPurchDocSAP = numPositionPurchDocSAP;
	}
	/**
	 * @return Returns the numPurchasingDocSAP.
	 */
	public String getNumPurchasingDocSAP() {
		return numPurchasingDocSAP;
	}
	/**
	 * @param numPurchasingDocSAP The numPurchasingDocSAP to set.
	 */
	public void setNumPurchasingDocSAP(String numPurchasingDocSAP) {
		this.numPurchasingDocSAP = numPurchasingDocSAP;
	}
	/**
	 * @return Returns the plantSAP.
	 */
	public String getPlantSAP() {
		return plantSAP;
	}
	/**
	 * @param plantSAP The plantSAP to set.
	 */
	public void setPlantSAP(String plantSAP) {
		this.plantSAP = plantSAP;
	}
	/**
	 * @return Returns the storageSAP.
	 */
	public String getStorageSAP() {
		return storageSAP;
	}
	/**
	 * @param storageSAP The storageSAP to set.
	 */
	public void setStorageSAP(String storageSAP) {
		this.storageSAP = storageSAP;
	}	
	/**
	 * @return Returns the cellphone.
	 */
	public String getCellphone() {
		return cellphone;
	}
	/**
	 * @param cellphone The cellphone to set.
	 */
	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}
	public boolean equals(Object arg0) {
		if (arg0 instanceof TR026SEquipment) {
			TR026SEquipment other = (TR026SEquipment) arg0;
			return EqualityUtilities.equals(elementSerial, other.elementSerial) &&
					EqualityUtilities.equals(brand, other.brand) &&
					EqualityUtilities.equals(equipmentType, other.equipmentType) &&
					EqualityUtilities.equals(model, other.model) &&
					EqualityUtilities.equals(elementType, other.elementType) &&
					EqualityUtilities.equals(cellphone, other.cellphone) &&
					EqualityUtilities.equals(materialSAP, other.materialSAP) &&
					EqualityUtilities.equals(plantSAP, other.plantSAP) &&
					EqualityUtilities.equals(storageSAP, other.storageSAP) &&
					EqualityUtilities.equals(numPurchasingDocSAP, other.numPurchasingDocSAP) &&
					numPositionPurchDocSAP == other.numPositionPurchDocSAP
					;
		}
		return false;
	}

}
