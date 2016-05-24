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
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class TR811SMaterials implements Serializable {
	private String codeMaterial;

	private Integer materialAmount;

	private String materialUnitMeasure;

	private String typeEquipment;

	private String brandEquipment;

	private String modelEquipment;

	private String equipmentSerialNumber;

	private String cassId;

	private String actionType;

	private String material;

	private String costCenter;

	private String codeDescription;

	public int hashCode() {
		return equipmentSerialNumber.hashCode();
	}

	public boolean equals(Object arg0) {
		if (arg0 instanceof TR811SMaterials) {
			TR811SMaterials other = (TR811SMaterials) arg0;
			return super.equals(arg0)
					&& EqualityUtilities.equals(codeMaterial,
							other.codeMaterial)
					&& EqualityUtilities.equals(materialAmount,
							other.materialAmount)
					&& EqualityUtilities.equals(materialUnitMeasure,
							other.materialUnitMeasure)
					&& EqualityUtilities.equals(typeEquipment,
							other.typeEquipment)
					&& EqualityUtilities.equals(brandEquipment,
							other.brandEquipment)
					&& EqualityUtilities.equals(modelEquipment,
							other.modelEquipment)
					&& EqualityUtilities.equals(equipmentSerialNumber,
							other.equipmentSerialNumber)
					&& EqualityUtilities.equals(cassId, other.cassId)
					&& EqualityUtilities.equals(actionType, other.actionType)
					&& EqualityUtilities.equals(material, other.material)
					&& EqualityUtilities.equals(costCenter, other.costCenter)
					&& EqualityUtilities.equals(codeDescription,
							other.codeDescription);
		}
		return false;
	}

	/**
	 * @return Devuelve actionType.
	 */
	public String getActionType() {
		return actionType;
	}

	/**
	 * @param actionType
	 *            El actionType a establecer.
	 */
	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

	/**
	 * @return Devuelve brandEquipment.
	 */
	public String getBrandEquipment() {
		return brandEquipment;
	}

	/**
	 * @param brandEquipment
	 *            El brandEquipment a establecer.
	 */
	public void setBrandEquipment(String brandEquipment) {
		this.brandEquipment = brandEquipment;
	}

	/**
	 * @return Devuelve cassId.
	 */
	public String getCassId() {
		return cassId;
	}

	/**
	 * @param cassId
	 *            El cassId a establecer.
	 */
	public void setCassId(String cassId) {
		this.cassId = cassId;
	}

	/**
	 * @return Devuelve codeDescription.
	 */
	public String getCodeDescription() {
		return codeDescription;
	}

	/**
	 * @param codeDescription
	 *            El codeDescription a establecer.
	 */
	public void setCodeDescription(String codeDescription) {
		this.codeDescription = codeDescription;
	}

	

	/**
	 * @return Devuelve codeMaterial.
	 */
	public String getCodeMaterial() {
		return codeMaterial;
	}
	/**
	 * @param codeMaterial El codeMaterial a establecer.
	 */
	public void setCodeMaterial(String codeMaterial) {
		this.codeMaterial = codeMaterial;
	}
	/**
	 * @return Devuelve costCenter.
	 */
	public String getCostCenter() {
		return costCenter;
	}

	/**
	 * @param costCenter
	 *            El costCenter a establecer.
	 */
	public void setCostCenter(String costCenter) {
		this.costCenter = costCenter;
	}

	/**
	 * @return Devuelve equipmentSerialNumber.
	 */
	public String getEquipmentSerialNumber() {
		return equipmentSerialNumber;
	}

	/**
	 * @param equipmentSerialNumber
	 *            El equipmentSerialNumber a establecer.
	 */
	public void setEquipmentSerialNumber(String equipmentSerialNumber) {
		this.equipmentSerialNumber = equipmentSerialNumber;
	}

	/**
	 * @return Devuelve material.
	 */
	public String getMaterial() {
		return material;
	}

	/**
	 * @param material
	 *            El material a establecer.
	 */
	public void setMaterial(String material) {
		this.material = material;
	}

	/**
	 * @return Devuelve materialAmount.
	 */
	public Integer getMaterialAmount() {
		return materialAmount;
	}

	/**
	 * @param materialAmount
	 *            El materialAmount a establecer.
	 */
	public void setMaterialAmount(Integer materialAmount) {
		this.materialAmount = materialAmount;
	}

	/**
	 * @return Devuelve materialUnitMeasure.
	 */
	public String getMaterialUnitMeasure() {
		return materialUnitMeasure;
	}

	/**
	 * @param materialUnitMeasure
	 *            El materialUnitMeasure a establecer.
	 */
	public void setMaterialUnitMeasure(String materialUnitMeasure) {
		this.materialUnitMeasure = materialUnitMeasure;
	}

	/**
	 * @return Devuelve modelEquipment.
	 */
	public String getModelEquipment() {
		return modelEquipment;
	}

	/**
	 * @param modelEquipment
	 *            El modelEquipment a establecer.
	 */
	public void setModelEquipment(String modelEquipment) {
		this.modelEquipment = modelEquipment;
	}

	/**
	 * @return Devuelve typeEquipment.
	 */
	public String getTypeEquipment() {
		return typeEquipment;
	}

	/**
	 * @param typeEquipment
	 *            El typeEquipment a establecer.
	 */
	public void setTypeEquipment(String typeEquipment) {
		this.typeEquipment = typeEquipment;
	}
}