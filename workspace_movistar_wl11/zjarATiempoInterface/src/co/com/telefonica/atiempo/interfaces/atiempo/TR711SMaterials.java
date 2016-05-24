/*
 * Created on Aug 26, 2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package co.com.telefonica.atiempo.interfaces.atiempo;

import java.io.Serializable;

import co.com.telefonica.atiempo.util.EqualityUtilities;

/**
 * @author damartinezv
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TR711SMaterials implements Serializable {
	private String codeMaterial;
	private Integer materialAmount;
	private String materialUnitMeasure;
	private String typeEquipment;
	private String brandEquipment;
	private String modelEquipment;
	private String equipmentSerialNumber;
	private String cassId;
	private String externalCodeEquipment;
	private String actionType;
	/*mfmendez - Datos SAP - 8595*/
	private String postingDateSAP;
	private String moveTypeSAP;
	private String materialCodeSAP;
	private String materialSAP;
	private String plantSAP;
	private String storageSAP;
	private String batchCodeSAP;	
	private String measurementUnitSAP;
	private String costCenterSAP;
	private String funcAreaLongSAP;
	private String pepElementSAP;
	private String flagMatSAP;
	
	public int hashCode() {
		return equipmentSerialNumber.hashCode();
	}
	
	public boolean equals(Object arg0) {
		if (arg0 instanceof TR711SMaterials) {
			TR711SMaterials other = (TR711SMaterials) arg0;
			return super.equals(arg0)
				&& EqualityUtilities.equals(codeMaterial, other.codeMaterial)
				&& EqualityUtilities.equals(materialAmount, other.materialAmount)
				&& EqualityUtilities.equals(materialUnitMeasure, other.materialUnitMeasure)
				&& EqualityUtilities.equals(typeEquipment, other.typeEquipment)
				&& EqualityUtilities.equals(brandEquipment, other.brandEquipment)
				&& EqualityUtilities.equals(modelEquipment, other.modelEquipment)
				&& EqualityUtilities.equals(equipmentSerialNumber, other.equipmentSerialNumber)
				&& EqualityUtilities.equals(cassId, other.cassId)
				&& EqualityUtilities.equals(externalCodeEquipment, other.externalCodeEquipment)
				&& EqualityUtilities.equals(actionType, other.actionType)
				&& EqualityUtilities.equals(postingDateSAP, other.getPostingDateSAP())
				&& EqualityUtilities.equals(moveTypeSAP, other.getMoveTypeSAP())
				&& EqualityUtilities.equals(materialCodeSAP, other.getMaterialCodeSAP())
				&& EqualityUtilities.equals(materialSAP, other.getMaterialSAP())
				&& EqualityUtilities.equals(plantSAP, other.getPlantSAP())
				&& EqualityUtilities.equals(storageSAP, other.getStorageSAP())
				&& EqualityUtilities.equals(batchCodeSAP, other.getBatchCodeSAP())
				&& EqualityUtilities.equals(measurementUnitSAP, other.getMeasurementUnitSAP())
				&& EqualityUtilities.equals(costCenterSAP, other.getCostCenterSAP())
				&& EqualityUtilities.equals(funcAreaLongSAP, other.getFuncAreaLongSAP())
				&& EqualityUtilities.equals(pepElementSAP, other.getPepElementSAP())
				&& EqualityUtilities.equals(flagMatSAP, other.getFlagMatSAP());
			}
		return false;
	}
	
	public String getActionType() {
		return actionType;
	}
	public void setActionType(String actionType) {
		this.actionType = actionType;
	}
	public String getBrandEquipment() {
		return brandEquipment;
	}
	public void setBrandEquipment(String brandEquipment) {
		this.brandEquipment = brandEquipment;
	}
	public String getCodeMaterial() {
		return codeMaterial;
	}
	public void setCodeMaterial(String codeMaterial) {
		this.codeMaterial = codeMaterial;
	}
	public String getEquipmentSerialNumber() {
		return equipmentSerialNumber;
	}
	public void setEquipmentSerialNumber(String equipmentSerialNumber) {
		this.equipmentSerialNumber = equipmentSerialNumber;
	}
	public String getExternalCodeEquipment() {
		return externalCodeEquipment;
	}
	public void setExternalCodeEquipment(String externalCodeEquipment) {
		this.externalCodeEquipment = externalCodeEquipment;
	}
	public Integer getMaterialAmount() {
		return materialAmount;
	}
	public void setMaterialAmount(Integer materialAmount) {
		this.materialAmount = materialAmount;
	}
	public String getMaterialUnitMeasure() {
		return materialUnitMeasure;
	}
	public void setMaterialUnitMeasure(String materialUnitMeasure) {
		this.materialUnitMeasure = materialUnitMeasure;
	}
	public String getModelEquipment() {
		return modelEquipment;
	}
	public void setModelEquipment(String modelEquipment) {
		this.modelEquipment = modelEquipment;
	}
	public String getTypeEquipment() {
		return typeEquipment;
	}
	public void setTypeEquipment(String typeEquipment) {
		this.typeEquipment = typeEquipment;
	}
	public String getCassId() {
		return cassId;
	}
	public void setCassId(String cassId) {
		this.cassId = cassId;
	}
	/**
	 * @return Returns the batchCodeSAP.
	 */
	public String getBatchCodeSAP() {
		return batchCodeSAP;
	}
	/**
	 * @param batchCodeSAP The batchCodeSAP to set.
	 */
	public void setBatchCodeSAP(String batchCodeSAP) {
		this.batchCodeSAP = batchCodeSAP;
	}
	/**
	 * @return Returns the costCenterSAP.
	 */
	public String getCostCenterSAP() {
		return costCenterSAP;
	}
	/**
	 * @param costCenterSAP The costCenterSAP to set.
	 */
	public void setCostCenterSAP(String costCenterSAP) {
		this.costCenterSAP = costCenterSAP;
	}
	/**
	 * @return Returns the funcAreaLongSAP.
	 */
	public String getFuncAreaLongSAP() {
		return funcAreaLongSAP;
	}
	/**
	 * @param funcAreaLongSAP The funcAreaLongSAP to set.
	 */
	public void setFuncAreaLongSAP(String funcAreaLongSAP) {
		this.funcAreaLongSAP = funcAreaLongSAP;
	}
	/**
	 * @return Returns the materialCodeSAP.
	 */
	public String getMaterialCodeSAP() {
		return materialCodeSAP;
	}
	/**
	 * @param materialCodeSAP The materialCodeSAP to set.
	 */
	public void setMaterialCodeSAP(String materialCodeSAP) {
		this.materialCodeSAP = materialCodeSAP;
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
	 * @return Returns the measurementUnitSAP.
	 */
	public String getMeasurementUnitSAP() {
		return measurementUnitSAP;
	}
	/**
	 * @param measurementUnitSAP The measurementUnitSAP to set.
	 */
	public void setMeasurementUnitSAP(String measurementUnitSAP) {
		this.measurementUnitSAP = measurementUnitSAP;
	}
	/**
	 * @return Returns the moveTypeSAP.
	 */
	public String getMoveTypeSAP() {
		return moveTypeSAP;
	}
	/**
	 * @param moveTypeSAP The moveTypeSAP to set.
	 */
	public void setMoveTypeSAP(String moveTypeSAP) {
		this.moveTypeSAP = moveTypeSAP;
	}
	/**
	 * @return Returns the pepElementSAP.
	 */
	public String getPepElementSAP() {
		return pepElementSAP;
	}
	/**
	 * @param pepElementSAP The pepElementSAP to set.
	 */
	public void setPepElementSAP(String pepElementSAP) {
		this.pepElementSAP = pepElementSAP;
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
	 * @return Returns the postingDateSAP.
	 */
	public String getPostingDateSAP() {
		return postingDateSAP;
	}
	/**
	 * @param postingDateSAP The postingDateSAP to set.
	 */
	public void setPostingDateSAP(String postingDateSAP) {
		this.postingDateSAP = postingDateSAP;
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
	 * @return Returns the flagMatSAP.
	 */
	public String getFlagMatSAP() {
		return flagMatSAP;
	}
	/**
	 * @param flagMatSAP The flagMatSAP to set.
	 */
	public void setFlagMatSAP(String flagMatSAP) {
		this.flagMatSAP = flagMatSAP;
	}
}
