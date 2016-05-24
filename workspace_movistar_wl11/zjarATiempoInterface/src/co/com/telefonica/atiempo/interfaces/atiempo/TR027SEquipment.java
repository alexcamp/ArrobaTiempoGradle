//- Copyright Notice
//-----------------------------------------------------------------------
// (C) Copyright 2007 Telefonica Colombia, http://www.telefonica.com.co
// Bogota, Colombia All Rights Reserved
// THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF Telefonica Colombia
// The copyright notice above does not evidence any actual or intended
// publication of such source code.
//
// $Id: TR027SEquipment.java,v 1.1 2011/03/30 18:24:46 lfmartinez Exp $
//-----------------------------------------------------------------------

package co.com.telefonica.atiempo.interfaces.atiempo;

import java.io.Serializable;

import co.com.telefonica.atiempo.util.EqualityUtilities;

/**
 * @author ylapchik
 * @version $Revision: 1.1 $
 */
public class TR027SEquipment implements Serializable {

	private String elementSerial;
	private String elementType;
	private String equipmentType;
	private String inventoryType;
	private long productServiceCode;
	/*mfmendez - Campos SAP - 8595*/
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
	private String invFlagMatSAP;
	private Long caracteristica;
	
	/**
	 * @return Devuelve caracteristica.
	 */
	public Long getCaracteristica() {
		return caracteristica;
	}
	/**
	 * @param caracteristica El caracteristica a establecer.
	 */
	public void setCaracteristica(Long caracteristica) {
		this.caracteristica = caracteristica;
	}
	public boolean equals(Object arg0) {
		if (arg0 instanceof TR027SEquipment) {
			TR027SEquipment other = (TR027SEquipment) arg0;
			return EqualityUtilities.equals(elementSerial, other.elementSerial) &&
					EqualityUtilities.equals(elementType, other.elementType) &&
					EqualityUtilities.equals(equipmentType, other.equipmentType) &&
					productServiceCode==other.productServiceCode &&
					EqualityUtilities.equals(inventoryType, other.inventoryType)
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
					&& EqualityUtilities.equals(invPepElementSAP, other.getInvPepElementSAP())
					&& EqualityUtilities.equals(invFlagMatSAP, other.getInvFlagMatSAP())					
					&& EqualityUtilities.equals(caracteristica, other.getCaracteristica());
		}
		return false;
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

	public long getProductServiceCode() {
		return productServiceCode;
	}
	public void setProductServiceCode(long productServiceCode) {
		this.productServiceCode = productServiceCode;
	}
	public String getInventoryType() {
		return inventoryType;
	}
	public void setInventoryType(String inventoryType) {
		this.inventoryType = inventoryType;
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
	 * @return Returns the invFlagMatSAP.
	 */
	public String getInvFlagMatSAP() {
		return invFlagMatSAP;
	}
	/**
	 * @param invFlagMatSAP The invFlagMatSAP to set.
	 */
	public void setInvFlagMatSAP(String invFlagMatSAP) {
		this.invFlagMatSAP = invFlagMatSAP;
	}
}
