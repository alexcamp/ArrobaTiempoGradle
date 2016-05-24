//- Copyright Notice
//-----------------------------------------------------------------------
// (C) Copyright 2007 Telefonica Colombia, http://www.telefonica.com.co
// Bogota, Colombia All Rights Reserved
// THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF Telefonica Colombia
// The copyright notice above does not evidence any actual or intended
// publication of such source code.
//
// $Id: Equipment.java,v 1.1 2011/03/30 18:20:03 lfmartinez Exp $
//-----------------------------------------------------------------------

package co.com.telefonica.atiempo.interfaces.atiempo;

import java.io.Serializable;

import co.com.telefonica.atiempo.util.EqualityUtilities;

/**
 * @author ylapchik
 * @version $Revision: 1.1 $
 */
public class Equipment implements Serializable {
	
	private String deco;
	private String decoReference;
	private String decoMarca;
	private String decoSerial;
	/*mfmendez - Datos SAP Deco*/
	private String decoPostingDateSAP;
	private String decoMoveTypeSAP;
	private String decoMaterialCodeSAP;
	private String decoMaterialSAP;
	private String decoPlantSAP;
	private String decoStorageSAP;
	private String decoBatchCodeSAP;	
	private String decoMeasurementUnitSAP;
	private String decoCostCenterSAP;
	private String decoFuncAreaLongSAP;
	private String decoPepElementSAP;
	private String decoFlagMatSAP;
	
	private String card;
	/*mfmendez - Datos SAP Card*/
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
	
	/*Datos del disco duro*/
	private String ddtvSerial;
	private String ddtvMarca;
	private String ddtvModelo;
	
	
	public String getCard() {
		return card;
	}

	public String getDeco() {
		return deco;
	}

	public void setCard(String string) {
		card = string;
	}

	public void setDeco(String string) {
		deco = string;
	}
	public int hashCode(){
		return deco.hashCode();
	}
	public String getDecoReference() {
		return decoReference;
	}
	public void setDecoReference(String string) {
		decoReference = string;
	}
	public boolean equals(Object arg0) {
		if (arg0 instanceof Equipment) {
			Equipment other = (Equipment) arg0;
			return EqualityUtilities.equals(deco, other.deco)
				&& EqualityUtilities.equals(card, other.card)
				&& EqualityUtilities.equals(decoReference,other.decoReference)
				&& EqualityUtilities.equals(decoMarca,other.decoMarca)
				&& EqualityUtilities.equals(decoSerial,other.decoSerial) &&
				EqualityUtilities.equals(decoPostingDateSAP, other.getDecoPostingDateSAP()) &&
				EqualityUtilities.equals(decoMoveTypeSAP, other.getDecoMoveTypeSAP()) &&
				EqualityUtilities.equals(decoMaterialCodeSAP, other.getDecoMaterialCodeSAP()) &&
				EqualityUtilities.equals(decoMaterialSAP, other.getDecoMaterialSAP()) &&
				EqualityUtilities.equals(decoPlantSAP, other.getDecoPlantSAP()) &&
				EqualityUtilities.equals(decoStorageSAP, other.getDecoStorageSAP()) &&
				EqualityUtilities.equals(decoBatchCodeSAP, other.getDecoBatchCodeSAP()) &&
				EqualityUtilities.equals(decoMeasurementUnitSAP, other.getDecoMeasurementUnitSAP()) &&
				EqualityUtilities.equals(decoCostCenterSAP, other.getDecoCostCenterSAP()) &&
				EqualityUtilities.equals(decoFuncAreaLongSAP, other.getDecoFuncAreaLongSAP()) &&
				EqualityUtilities.equals(decoPepElementSAP, other.getDecoPepElementSAP()) &&
				EqualityUtilities.equals(decoFlagMatSAP, other.getDecoFlagMatSAP()) &&
				EqualityUtilities.equals(cardPostingDateSAP, other.getCardPostingDateSAP()) &&
				EqualityUtilities.equals(cardMoveTypeSAP, other.getCardMoveTypeSAP()) &&
				EqualityUtilities.equals(cardMaterialCodeSAP, other.getCardMaterialCodeSAP()) &&
				EqualityUtilities.equals(cardMaterialSAP, other.getCardMaterialSAP()) &&
				EqualityUtilities.equals(cardPlantSAP, other.getCardPlantSAP()) &&
				EqualityUtilities.equals(cardStorageSAP, other.getCardStorageSAP()) &&
				EqualityUtilities.equals(cardBatchCodeSAP, other.getCardBatchCodeSAP()) &&
				EqualityUtilities.equals(cardMeasurementUnitSAP, other.getCardMeasurementUnitSAP()) &&
				EqualityUtilities.equals(cardCostCenterSAP, other.getCardCostCenterSAP()) &&
				EqualityUtilities.equals(cardFuncAreaLongSAP, other.getCardFuncAreaLongSAP()) &&
				EqualityUtilities.equals(cardPepElementSAP, other.getCardPepElementSAP()) &&
				EqualityUtilities.equals(cardFlagMatSAP, other.getCardFlagMatSAP())				
				;
		}
		return false;
	}

	

	public String getDecoMarca() {
		return decoMarca;
	}
	public void setDecoMarca(String decoMarca) {
		this.decoMarca = decoMarca;
	}
	public String getDecoSerial() {
		return decoSerial;
	}
	public void setDecoSerial(String decoSerial) {
		this.decoSerial = decoSerial;
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
	 * @return Returns the decoBatchCodeSAP.
	 */
	public String getDecoBatchCodeSAP() {
		return decoBatchCodeSAP;
	}
	/**
	 * @param decoBatchCodeSAP The decoBatchCodeSAP to set.
	 */
	public void setDecoBatchCodeSAP(String decoBatchCodeSAP) {
		this.decoBatchCodeSAP = decoBatchCodeSAP;
	}
	/**
	 * @return Returns the decoCostCenterSAP.
	 */
	public String getDecoCostCenterSAP() {
		return decoCostCenterSAP;
	}
	/**
	 * @param decoCostCenterSAP The decoCostCenterSAP to set.
	 */
	public void setDecoCostCenterSAP(String decoCostCenterSAP) {
		this.decoCostCenterSAP = decoCostCenterSAP;
	}
	/**
	 * @return Returns the decoFuncAreaLongSAP.
	 */
	public String getDecoFuncAreaLongSAP() {
		return decoFuncAreaLongSAP;
	}
	/**
	 * @param decoFuncAreaLongSAP The decoFuncAreaLongSAP to set.
	 */
	public void setDecoFuncAreaLongSAP(String decoFuncAreaLongSAP) {
		this.decoFuncAreaLongSAP = decoFuncAreaLongSAP;
	}
	/**
	 * @return Returns the decoMaterialCodeSAP.
	 */
	public String getDecoMaterialCodeSAP() {
		return decoMaterialCodeSAP;
	}
	/**
	 * @param decoMaterialCodeSAP The decoMaterialCodeSAP to set.
	 */
	public void setDecoMaterialCodeSAP(String decoMaterialCodeSAP) {
		this.decoMaterialCodeSAP = decoMaterialCodeSAP;
	}
	/**
	 * @return Returns the decoMaterialSAP.
	 */
	public String getDecoMaterialSAP() {
		return decoMaterialSAP;
	}
	/**
	 * @param decoMaterialSAP The decoMaterialSAP to set.
	 */
	public void setDecoMaterialSAP(String decoMaterialSAP) {
		this.decoMaterialSAP = decoMaterialSAP;
	}
	/**
	 * @return Returns the decoMeasurementUnitSAP.
	 */
	public String getDecoMeasurementUnitSAP() {
		return decoMeasurementUnitSAP;
	}
	/**
	 * @param decoMeasurementUnitSAP The decoMeasurementUnitSAP to set.
	 */
	public void setDecoMeasurementUnitSAP(String decoMeasurementUnitSAP) {
		this.decoMeasurementUnitSAP = decoMeasurementUnitSAP;
	}
	/**
	 * @return Returns the decoMoveTypeSAP.
	 */
	public String getDecoMoveTypeSAP() {
		return decoMoveTypeSAP;
	}
	/**
	 * @param decoMoveTypeSAP The decoMoveTypeSAP to set.
	 */
	public void setDecoMoveTypeSAP(String decoMoveTypeSAP) {
		this.decoMoveTypeSAP = decoMoveTypeSAP;
	}
	/**
	 * @return Returns the decoPepElementSAP.
	 */
	public String getDecoPepElementSAP() {
		return decoPepElementSAP;
	}
	/**
	 * @param decoPepElementSAP The decoPepElementSAP to set.
	 */
	public void setDecoPepElementSAP(String decoPepElementSAP) {
		this.decoPepElementSAP = decoPepElementSAP;
	}
	/**
	 * @return Returns the decoPlantSAP.
	 */
	public String getDecoPlantSAP() {
		return decoPlantSAP;
	}
	/**
	 * @param decoPlantSAP The decoPlantSAP to set.
	 */
	public void setDecoPlantSAP(String decoPlantSAP) {
		this.decoPlantSAP = decoPlantSAP;
	}
	/**
	 * @return Returns the decoPostingDateSAP.
	 */
	public String getDecoPostingDateSAP() {
		return decoPostingDateSAP;
	}
	/**
	 * @param decoPostingDateSAP The decoPostingDateSAP to set.
	 */
	public void setDecoPostingDateSAP(String decoPostingDateSAP) {
		this.decoPostingDateSAP = decoPostingDateSAP;
	}
	/**
	 * @return Returns the decoStorageSAP.
	 */
	public String getDecoStorageSAP() {
		return decoStorageSAP;
	}
	/**
	 * @param decoStorageSAP The decoStorageSAP to set.
	 */
	public void setDecoStorageSAP(String decoStorageSAP) {
		this.decoStorageSAP = decoStorageSAP;
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
	 * @return Returns the decoFlagMatSAP.
	 */
	public String getDecoFlagMatSAP() {
		return decoFlagMatSAP;
	}
	/**
	 * @param decoFlagMatSAP The decoFlagMatSAP to set.
	 */
	public void setDecoFlagMatSAP(String decoFlagMatSAP) {
		this.decoFlagMatSAP = decoFlagMatSAP;
	}
	/**
	 * @return Devuelve ddtvMarca.
	 */
	public String getDdtvMarca() {
		return ddtvMarca;
	}
	/**
	 * @param ddtvMarca El ddtvMarca a establecer.
	 */
	public void setDdtvMarca(String ddtvMarca) {
		this.ddtvMarca = ddtvMarca;
	}
	/**
	 * @return Devuelve ddtvModelo.
	 */
	public String getDdtvModelo() {
		return ddtvModelo;
	}
	/**
	 * @param ddtvModelo El ddtvModelo a establecer.
	 */
	public void setDdtvModelo(String ddtvModelo) {
		this.ddtvModelo = ddtvModelo;
	}
	/**
	 * @return Devuelve ddtvSerial.
	 */
	public String getDdtvSerial() {
		return ddtvSerial;
	}
	/**
	 * @param ddtvSerial El ddtvSerial a establecer.
	 */
	public void setDdtvSerial(String ddtvSerial) {
		this.ddtvSerial = ddtvSerial;
	}
}
