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
public class TR701EEquipment implements Serializable {
	private String serialNumber;
	private String brand;
	private String model;
	private String type;
	private String cassId;
	private String cardSerialNumber;
	private String inventoryType;
	private String partType;
	private String id;
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
	
	/*Datos de la Tarjeta*/
	private String cardPostingDateSAP;
	private String cardMoveTypeSAP;
	private String cardMaterialCodeSAP;
	private String cardMaterialSAP;
	private String cardPlantSAP;
	private String cardStorageSAP;
	private String cardBatchCodeSAP;	
	private String cardMeasurementUnitSAP;
	private String cardCostCenterSAP;
	private String cardFuncAreaLongSAP;
	private String cardPepElementSAP;
	private String cardFlagMatSAP;
	
	public int hashCode() {
		return cassId.hashCode() + cardSerialNumber.hashCode();
	}
	
	public boolean equals(Object arg0) {
		if (arg0 instanceof TR701EEquipment) {
			TR701EEquipment other = (TR701EEquipment) arg0;
			return super.equals(arg0)
				&& EqualityUtilities.equals(serialNumber, other.serialNumber)
				&& EqualityUtilities.equals(brand, other.brand)
				&& EqualityUtilities.equals(model, other.model)
				&& EqualityUtilities.equals(type, other.type)
				&& EqualityUtilities.equals(cassId, other.cassId)
				&& EqualityUtilities.equals(cardSerialNumber, other.cardSerialNumber)
				&& EqualityUtilities.equals(inventoryType, other.inventoryType)
				&& EqualityUtilities.equals(partType, other.partType)
				&& EqualityUtilities.equals(id, other.id)
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
				&& EqualityUtilities.equals(flagMatSAP, other.getFlagMatSAP())
				&& EqualityUtilities.equals(cardPostingDateSAP, other.getCardPostingDateSAP())
				&& EqualityUtilities.equals(cardMoveTypeSAP, other.getCardMoveTypeSAP())
				&& EqualityUtilities.equals(cardMaterialCodeSAP, other.getCardMaterialCodeSAP())
				&& EqualityUtilities.equals(cardMaterialSAP, other.getCardMaterialSAP())
				&& EqualityUtilities.equals(cardPlantSAP, other.getCardPlantSAP())
				&& EqualityUtilities.equals(cardStorageSAP, other.getCardStorageSAP())
				&& EqualityUtilities.equals(cardBatchCodeSAP, other.getCardBatchCodeSAP())
				&& EqualityUtilities.equals(cardMeasurementUnitSAP, other.getCardMeasurementUnitSAP())
				&& EqualityUtilities.equals(cardCostCenterSAP, other.getCardCostCenterSAP())
				&& EqualityUtilities.equals(cardFuncAreaLongSAP, other.getCardFuncAreaLongSAP())
				&& EqualityUtilities.equals(cardPepElementSAP, other.getCardPepElementSAP())
				&& EqualityUtilities.equals(cardFlagMatSAP, other.getCardFlagMatSAP());
			}
		return false;
	}
	
	
	/**
	 * @return Devuelve brand.
	 */
	public String getBrand() {
		return brand;
	}
	/**
	 * @param brand El brand a establecer.
	 */
	public void setBrand(String brand) {
		this.brand = brand;
	}
	/**
	 * @return Devuelve cardSerialNumber.
	 */
	public String getCardSerialNumber() {
		return cardSerialNumber;
	}
	/**
	 * @param cardSerialNumber El cardSerialNumber a establecer.
	 */
	public void setCardSerialNumber(String cardSerialNumber) {
		this.cardSerialNumber = cardSerialNumber;
	}
	/**
	 * @return Devuelve cassId.
	 */
	public String getCassId() {
		return cassId;
	}
	/**
	 * @param cassId El cassId a establecer.
	 */
	public void setCassId(String cassId) {
		this.cassId = cassId;
	}
	/**
	 * @return Devuelve id.
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id El id a establecer.
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return Devuelve inventoryType.
	 */
	public String getInventoryType() {
		return inventoryType;
	}
	/**
	 * @param inventoryType El inventoryType a establecer.
	 */
	public void setInventoryType(String inventoryType) {
		this.inventoryType = inventoryType;
	}
	/**
	 * @return Devuelve model.
	 */
	public String getModel() {
		return model;
	}
	/**
	 * @param model El model a establecer.
	 */
	public void setModel(String model) {
		this.model = model;
	}
	/**
	 * @return Devuelve partType.
	 */
	public String getPartType() {
		return partType;
	}
	/**
	 * @param partType El partType a establecer.
	 */
	public void setPartType(String partType) {
		this.partType = partType;
	}
	/**
	 * @return Devuelve serialNumber.
	 */
	public String getSerialNumber() {
		return serialNumber;
	}
	/**
	 * @param serialNumber El serialNumber a establecer.
	 */
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	/**
	 * @return Devuelve type.
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type El type a establecer.
	 */
	public void setType(String type) {
		this.type = type;
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
	 * @return Returns the cardBatchCodeSAP.
	 */
	public String getCardBatchCodeSAP() {
		return cardBatchCodeSAP;
	}
	/**
	 * @param cardBatchCodeSAP The cardBatchCodeSAP to set.
	 */
	public void setCardBatchCodeSAP(String cardBatchCodeSAP) {
		this.cardBatchCodeSAP = cardBatchCodeSAP;
	}
	/**
	 * @return Returns the cardCostCenterSAP.
	 */
	public String getCardCostCenterSAP() {
		return cardCostCenterSAP;
	}
	/**
	 * @param cardCostCenterSAP The cardCostCenterSAP to set.
	 */
	public void setCardCostCenterSAP(String cardCostCenterSAP) {
		this.cardCostCenterSAP = cardCostCenterSAP;
	}
	/**
	 * @return Returns the cardFuncAreaLongSAP.
	 */
	public String getCardFuncAreaLongSAP() {
		return cardFuncAreaLongSAP;
	}
	/**
	 * @param cardFuncAreaLongSAP The cardFuncAreaLongSAP to set.
	 */
	public void setCardFuncAreaLongSAP(String cardFuncAreaLongSAP) {
		this.cardFuncAreaLongSAP = cardFuncAreaLongSAP;
	}
	/**
	 * @return Returns the cardMaterialCodeSAP.
	 */
	public String getCardMaterialCodeSAP() {
		return cardMaterialCodeSAP;
	}
	/**
	 * @param cardMaterialCodeSAP The cardMaterialCodeSAP to set.
	 */
	public void setCardMaterialCodeSAP(String cardMaterialCodeSAP) {
		this.cardMaterialCodeSAP = cardMaterialCodeSAP;
	}
	/**
	 * @return Returns the cardMaterialSAP.
	 */
	public String getCardMaterialSAP() {
		return cardMaterialSAP;
	}
	/**
	 * @param cardMaterialSAP The cardMaterialSAP to set.
	 */
	public void setCardMaterialSAP(String cardMaterialSAP) {
		this.cardMaterialSAP = cardMaterialSAP;
	}
	/**
	 * @return Returns the cardMeasurementUnitSAP.
	 */
	public String getCardMeasurementUnitSAP() {
		return cardMeasurementUnitSAP;
	}
	/**
	 * @param cardMeasurementUnitSAP The cardMeasurementUnitSAP to set.
	 */
	public void setCardMeasurementUnitSAP(String cardMeasurementUnitSAP) {
		this.cardMeasurementUnitSAP = cardMeasurementUnitSAP;
	}
	/**
	 * @return Returns the cardMoveTypeSAP.
	 */
	public String getCardMoveTypeSAP() {
		return cardMoveTypeSAP;
	}
	/**
	 * @param cardMoveTypeSAP The cardMoveTypeSAP to set.
	 */
	public void setCardMoveTypeSAP(String cardMoveTypeSAP) {
		this.cardMoveTypeSAP = cardMoveTypeSAP;
	}
	/**
	 * @return Returns the cardPepElementSAP.
	 */
	public String getCardPepElementSAP() {
		return cardPepElementSAP;
	}
	/**
	 * @param cardPepElementSAP The cardPepElementSAP to set.
	 */
	public void setCardPepElementSAP(String cardPepElementSAP) {
		this.cardPepElementSAP = cardPepElementSAP;
	}
	/**
	 * @return Returns the cardPlantSAP.
	 */
	public String getCardPlantSAP() {
		return cardPlantSAP;
	}
	/**
	 * @param cardPlantSAP The cardPlantSAP to set.
	 */
	public void setCardPlantSAP(String cardPlantSAP) {
		this.cardPlantSAP = cardPlantSAP;
	}
	/**
	 * @return Returns the cardPostingDateSAP.
	 */
	public String getCardPostingDateSAP() {
		return cardPostingDateSAP;
	}
	/**
	 * @param cardPostingDateSAP The cardPostingDateSAP to set.
	 */
	public void setCardPostingDateSAP(String cardPostingDateSAP) {
		this.cardPostingDateSAP = cardPostingDateSAP;
	}
	/**
	 * @return Returns the cardStorageSAP.
	 */
	public String getCardStorageSAP() {
		return cardStorageSAP;
	}
	/**
	 * @param cardStorageSAP The cardStorageSAP to set.
	 */
	public void setCardStorageSAP(String cardStorageSAP) {
		this.cardStorageSAP = cardStorageSAP;
	}
	/**
	 * @return Returns the cardFlagMatSAP.
	 */
	public String getCardFlagMatSAP() {
		return cardFlagMatSAP;
	}
	/**
	 * @param cardFlagMatSAP The cardFlagMatSAP to set.
	 */
	public void setCardFlagMatSAP(String cardFlagMatSAP) {
		this.cardFlagMatSAP = cardFlagMatSAP;
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
