//- Copyright Notice
//-----------------------------------------------------------------------
// (C) Copyright 2007 Telefonica Colombia, http://www.telefonica.com.co
// Bogota, Colombia All Rights Reserved
// THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF Telefonica Colombia
// The copyright notice above does not evidence any actual or intended
// publication of such source code.
//
// $Id: TR025SEquipment.java,v 1.2 2011/06/19 mfendez Exp $
//-----------------------------------------------------------------------

package co.com.telefonica.atiempo.interfaces.atiempo;

import java.io.Serializable;

import co.com.telefonica.atiempo.util.EqualityUtilities;

/**
 * 
 * @author mfmendez
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TR025SEquipment implements Serializable {

	private long elementId;
	private String elementSerial;
	private String elementType;
	private String equipmentType;
	private String inventoryType;
	private String inventoryBrand;
	private String inventoryModel;
	private String inventoryWork;
	private long productServiceCode;
	private String inventoryContractor;
	private long productCode;
	private String invPostingDateSAP;
	private String invMoveTypeSAP;
	private String invMaterialCodeSAP;
	private String invMaterialSAP;
	private String invPlantSAP;
	private String invStorageSAP;
	private String invBatchCodeSAP;	
	private String invMeasurementUnitSAP;
	private String invCostCenterSAP;
	private String invFuncAreaLongSAP;
	private String invPepElementSAP;
	

	public int hashCode() {
		return elementSerial.hashCode();
	}
	
	public boolean equals(Object arg0) {
		if (arg0 instanceof TR025SEquipment) {
			TR025SEquipment other = (TR025SEquipment) arg0;
			return elementId==other.getElementId() 
				&& EqualityUtilities.equals(elementSerial, other.getElementSerial())
				&& EqualityUtilities.equals(elementType, other.getElementType())
				&& EqualityUtilities.equals(equipmentType, other.getEquipmentType())
				&& EqualityUtilities.equals(inventoryType, other.getInventoryType())
				&& EqualityUtilities.equals(inventoryBrand, other.getInventoryBrand())
				&& EqualityUtilities.equals(inventoryModel, other.getInventoryModel())
				&& EqualityUtilities.equals(inventoryWork, other.getInventoryWork())
				&& productServiceCode == other.getProductServiceCode()
				&& EqualityUtilities.equals(inventoryContractor, other.getInventoryContractor())
				&& productCode == other.getProductCode()
				&& EqualityUtilities.equals(invPostingDateSAP, other.getInvPostingDateSAP())
				&& EqualityUtilities.equals(invMoveTypeSAP, other.getInvMoveTypeSAP())
				&& EqualityUtilities.equals(invMaterialCodeSAP, other.getInvMaterialCodeSAP())
				&& EqualityUtilities.equals(invMaterialSAP, other.getInvMaterialSAP())
				&& EqualityUtilities.equals(invPlantSAP, other.getInvPlantSAP())
				&& EqualityUtilities.equals(invStorageSAP, other.getInvStorageSAP())
				&& EqualityUtilities.equals(invBatchCodeSAP, other.getInvBatchCodeSAP())
				&& EqualityUtilities.equals(invMeasurementUnitSAP, other.getInvMeasurementUnitSAP())
				&& EqualityUtilities.equals(invCostCenterSAP, other.getInvCostCenterSAP())
				&& EqualityUtilities.equals(invFuncAreaLongSAP, other.getInvFuncAreaLongSAP())
				&& EqualityUtilities.equals(invPepElementSAP, other.getInvPepElementSAP());
		}
		return false;
	}

	
	/**
	 * @return Returns the elementId.
	 */
	public long getElementId() {
		return elementId;
	}
	/**
	 * @param elementId The elementId to set.
	 */
	public void setElementId(long elementId) {
		this.elementId = elementId;
	}
	/**
	 * @return Returns the elementSerial.
	 */
	public String getElementSerial() {
		return elementSerial;
	}
	/**
	 * @param elementSerial The elementSerial to set.
	 */
	public void setElementSerial(String elementSerial) {
		this.elementSerial = elementSerial;
	}
	/**
	 * @return Returns the elementType.
	 */
	public String getElementType() {
		return elementType;
	}
	/**
	 * @param elementType The elementType to set.
	 */
	public void setElementType(String elementType) {
		this.elementType = elementType;
	}
	/**
	 * @return Returns the equipmentType.
	 */
	public String getEquipmentType() {
		return equipmentType;
	}
	/**
	 * @param equipmentType The equipmentType to set.
	 */
	public void setEquipmentType(String equipmentType) {
		this.equipmentType = equipmentType;
	}
	/**
	 * @return Returns the invBatchCodeSAP.
	 */
	public String getInvBatchCodeSAP() {
		return invBatchCodeSAP;
	}
	/**
	 * @param invBatchCodeSAP The invBatchCodeSAP to set.
	 */
	public void setInvBatchCodeSAP(String invBatchCodeSAP) {
		this.invBatchCodeSAP = invBatchCodeSAP;
	}
	/**
	 * @return Returns the invCostCenterSAP.
	 */
	public String getInvCostCenterSAP() {
		return invCostCenterSAP;
	}
	/**
	 * @param invCostCenterSAP The invCostCenterSAP to set.
	 */
	public void setInvCostCenterSAP(String invCostCenterSAP) {
		this.invCostCenterSAP = invCostCenterSAP;
	}
	/**
	 * @return Returns the inventoryBrand.
	 */
	public String getInventoryBrand() {
		return inventoryBrand;
	}
	/**
	 * @param inventoryBrand The inventoryBrand to set.
	 */
	public void setInventoryBrand(String inventoryBrand) {
		this.inventoryBrand = inventoryBrand;
	}
	/**
	 * @return Returns the inventoryContractor.
	 */
	public String getInventoryContractor() {
		return inventoryContractor;
	}
	/**
	 * @param inventoryContractor The inventoryContractor to set.
	 */
	public void setInventoryContractor(String inventoryContractor) {
		this.inventoryContractor = inventoryContractor;
	}
	/**
	 * @return Returns the inventoryModel.
	 */
	public String getInventoryModel() {
		return inventoryModel;
	}
	/**
	 * @param inventoryModel The inventoryModel to set.
	 */
	public void setInventoryModel(String inventoryModel) {
		this.inventoryModel = inventoryModel;
	}
	/**
	 * @return Returns the inventoryType.
	 */
	public String getInventoryType() {
		return inventoryType;
	}
	/**
	 * @param inventoryType The inventoryType to set.
	 */
	public void setInventoryType(String inventoryType) {
		this.inventoryType = inventoryType;
	}
	/**
	 * @return Returns the inventoryWork.
	 */
	public String getInventoryWork() {
		return inventoryWork;
	}
	/**
	 * @param inventoryWork The inventoryWork to set.
	 */
	public void setInventoryWork(String inventoryWork) {
		this.inventoryWork = inventoryWork;
	}
	/**
	 * @return Returns the invFuncAreaLongSAP.
	 */
	public String getInvFuncAreaLongSAP() {
		return invFuncAreaLongSAP;
	}
	/**
	 * @param invFuncAreaLongSAP The invFuncAreaLongSAP to set.
	 */
	public void setInvFuncAreaLongSAP(String invFuncAreaLongSAP) {
		this.invFuncAreaLongSAP = invFuncAreaLongSAP;
	}
	/**
	 * @return Returns the invMaterialCodeSAP.
	 */
	public String getInvMaterialCodeSAP() {
		return invMaterialCodeSAP;
	}
	/**
	 * @param invMaterialCodeSAP The invMaterialCodeSAP to set.
	 */
	public void setInvMaterialCodeSAP(String invMaterialCodeSAP) {
		this.invMaterialCodeSAP = invMaterialCodeSAP;
	}
	/**
	 * @return Returns the invMaterialSAP.
	 */
	public String getInvMaterialSAP() {
		return invMaterialSAP;
	}
	/**
	 * @param invMaterialSAP The invMaterialSAP to set.
	 */
	public void setInvMaterialSAP(String invMaterialSAP) {
		this.invMaterialSAP = invMaterialSAP;
	}
	/**
	 * @return Returns the invMeasurementUnitSAP.
	 */
	public String getInvMeasurementUnitSAP() {
		return invMeasurementUnitSAP;
	}
	/**
	 * @param invMeasurementUnitSAP The invMeasurementUnitSAP to set.
	 */
	public void setInvMeasurementUnitSAP(String invMeasurementUnitSAP) {
		this.invMeasurementUnitSAP = invMeasurementUnitSAP;
	}
	/**
	 * @return Returns the invMoveTypeSAP.
	 */
	public String getInvMoveTypeSAP() {
		return invMoveTypeSAP;
	}
	/**
	 * @param invMoveTypeSAP The invMoveTypeSAP to set.
	 */
	public void setInvMoveTypeSAP(String invMoveTypeSAP) {
		this.invMoveTypeSAP = invMoveTypeSAP;
	}
	/**
	 * @return Returns the invPepElementSAP.
	 */
	public String getInvPepElementSAP() {
		return invPepElementSAP;
	}
	/**
	 * @param invPepElementSAP The invPepElementSAP to set.
	 */
	public void setInvPepElementSAP(String invPepElementSAP) {
		this.invPepElementSAP = invPepElementSAP;
	}
	/**
	 * @return Returns the invPlantSAP.
	 */
	public String getInvPlantSAP() {
		return invPlantSAP;
	}
	/**
	 * @param invPlantSAP The invPlantSAP to set.
	 */
	public void setInvPlantSAP(String invPlantSAP) {
		this.invPlantSAP = invPlantSAP;
	}
	/**
	 * @return Returns the invStorageSAP.
	 */
	public String getInvStorageSAP() {
		return invStorageSAP;
	}
	/**
	 * @param invStorageSAP The invStorageSAP to set.
	 */
	public void setInvStorageSAP(String invStorageSAP) {
		this.invStorageSAP = invStorageSAP;
	}
	/**
	 * @return Returns the productCode.
	 */
	public long getProductCode() {
		return productCode;
	}
	/**
	 * @param productCode The productCode to set.
	 */
	public void setProductCode(long productCode) {
		this.productCode = productCode;
	}
	/**
	 * @return Returns the productServiceCode.
	 */
	public long getProductServiceCode() {
		return productServiceCode;
	}
	/**
	 * @param productServiceCode The productServiceCode to set.
	 */
	public void setProductServiceCode(long productServiceCode) {
		this.productServiceCode = productServiceCode;
	}
	/**
	 * @return Returns the invPostingDateSAP.
	 */
	public String getInvPostingDateSAP() {
		return invPostingDateSAP;
	}
	/**
	 * @param invPostingDateSAP The invPostingDateSAP to set.
	 */
	public void setInvPostingDateSAP(String invPostingDateSAP) {
		this.invPostingDateSAP = invPostingDateSAP;
	}
}
