//- Copyright Notice
//-----------------------------------------------------------------------
// (C) Copyright 2007 Telefonica Colombia, http://www.telefonica.com.co
// Bogota, Colombia All Rights Reserved
// THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF Telefonica Colombia
// The copyright notice above does not evidence any actual or intended
// publication of such source code.
//
// $Id: TR025S.java,v 1.1 2011/06/19 mfmendez Exp $
//-----------------------------------------------------------------------

package co.com.telefonica.atiempo.interfaces.atiempo;

import java.util.Collection;
import co.com.telefonica.atiempo.util.EqualityUtilities;

/**
 * 
 * @author mfmendez
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TR025S extends ResponseHeaderAgendaSC{

	private long atisRequestNumber;
	private long atiempoRequestNumber;
	private String modemSerial;
	private long modemCode;
	private long modemWork;
	private String modemBrand;
	private String modemModel;	
	private long modemType;
	private String modemPostingDateSAP;
	private String modemMoveTypeSAP;
	private String modemMaterialCodeSAP;
	private String modemMaterialSAP;
	private String modemPlantSAP;
	private String modemStorageSAP;
	private String modemBatchCodeSAP;	
	private String modemMeasurementUnitSAP;
	private String modemCostCenterSAP;
	private String modemFuncAreaLongSAP;
	private String modemPepElementSAP;
	/*TR025SDecoCard*/
	private Collection decosCards;
	/*TR025SEquipment*/
	private Collection equipments;
	

	public int hashCode(){
		return (int)atisRequestNumber;
	}
	
	public boolean equals(Object arg0) {
		if (arg0 instanceof TR025S) {
			TR025S other = (TR025S) arg0;
			return super.equals(arg0)
				&& atisRequestNumber == other.atisRequestNumber
				&& atiempoRequestNumber == other.atiempoRequestNumber
				&& EqualityUtilities.equals(modemSerial,other.getModemSerial())
				&& modemCode == other.getModemCode()
				&& modemWork == other.getModemWork()
				&& EqualityUtilities.equals(modemBrand,other.getModemBrand())
				&& EqualityUtilities.equals(modemModel,other.getModemModel())
				&& modemType == other.getModemType()
				&& EqualityUtilities.equals(modemPostingDateSAP,other.getModemPostingDateSAP())
				&& EqualityUtilities.equals(modemMoveTypeSAP,other.getModemMoveTypeSAP())
				&& EqualityUtilities.equals(modemMaterialCodeSAP,other.getModemMaterialCodeSAP())
				&& EqualityUtilities.equals(modemMaterialSAP,other.getModemMaterialSAP())
				&& EqualityUtilities.equals(modemPlantSAP,other.getModemPlantSAP())
				&& EqualityUtilities.equals(modemStorageSAP,other.getModemStorageSAP())
				&& EqualityUtilities.equals(modemBatchCodeSAP,other.getModemBatchCodeSAP())
				&& EqualityUtilities.equals(modemMeasurementUnitSAP,other.getModemMeasurementUnitSAP())
				&& EqualityUtilities.equals(modemCostCenterSAP,other.getModemCostCenterSAP())
				&& EqualityUtilities.equals(modemFuncAreaLongSAP,other.getModemFuncAreaLongSAP())
				&& EqualityUtilities.equals(modemPepElementSAP,other.getModemPepElementSAP());				
		}
		return false;
	}
	
	/**
	 * @return Returns the atiempoRequestNumber.
	 */
	public long getAtiempoRequestNumber() {
		return atiempoRequestNumber;
	}
	/**
	 * @param atiempoRequestNumber The atiempoRequestNumber to set.
	 */
	public void setAtiempoRequestNumber(long atiempoRequestNumber) {
		this.atiempoRequestNumber = atiempoRequestNumber;
	}
	/**
	 * @return Returns the atisRequestNumber.
	 */
	public long getAtisRequestNumber() {
		return atisRequestNumber;
	}
	/**
	 * @param atisRequestNumber The atisRequestNumber to set.
	 */
	public void setAtisRequestNumber(long atisRequestNumber) {
		this.atisRequestNumber = atisRequestNumber;
	}
	/**
	 * @return Returns the decosCards.
	 */
	public Collection getDecosCards() {
		return decosCards;
	}
	/**
	 * @param decosCards The decosCards to set.
	 */
	public void setDecosCards(Collection decosCards) {
		this.decosCards = decosCards;
	}
	/**
	 * @return Returns the equipments.
	 */
	public Collection getEquipments() {
		return equipments;
	}
	/**
	 * @param equipments The equipments to set.
	 */
	public void setEquipments(Collection equipments) {
		this.equipments = equipments;
	}
	/**
	 * @return Returns the modemBatchCodeSAP.
	 */
	public String getModemBatchCodeSAP() {
		return modemBatchCodeSAP;
	}
	/**
	 * @param modemBatchCodeSAP The modemBatchCodeSAP to set.
	 */
	public void setModemBatchCodeSAP(String modemBatchCodeSAP) {
		this.modemBatchCodeSAP = modemBatchCodeSAP;
	}
	/**
	 * @return Returns the modemBrand.
	 */
	public String getModemBrand() {
		return modemBrand;
	}
	/**
	 * @param modemBrand The modemBrand to set.
	 */
	public void setModemBrand(String modemBrand) {
		this.modemBrand = modemBrand;
	}
	/**
	 * @return Returns the modemCode.
	 */
	public long getModemCode() {
		return modemCode;
	}
	/**
	 * @param modemCode The modemCode to set.
	 */
	public void setModemCode(long modemCode) {
		this.modemCode = modemCode;
	}
	/**
	 * @return Returns the modemCostCenterSAP.
	 */
	public String getModemCostCenterSAP() {
		return modemCostCenterSAP;
	}
	/**
	 * @param modemCostCenterSAP The modemCostCenterSAP to set.
	 */
	public void setModemCostCenterSAP(String modemCostCenterSAP) {
		this.modemCostCenterSAP = modemCostCenterSAP;
	}
	/**
	 * @return Returns the modemFuncAreaLongSAP.
	 */
	public String getModemFuncAreaLongSAP() {
		return modemFuncAreaLongSAP;
	}
	/**
	 * @param modemFuncAreaLongSAP The modemFuncAreaLongSAP to set.
	 */
	public void setModemFuncAreaLongSAP(String modemFuncAreaLongSAP) {
		this.modemFuncAreaLongSAP = modemFuncAreaLongSAP;
	}
	/**
	 * @return Returns the modemMaterialCodeSAP.
	 */
	public String getModemMaterialCodeSAP() {
		return modemMaterialCodeSAP;
	}
	/**
	 * @param modemMaterialCodeSAP The modemMaterialCodeSAP to set.
	 */
	public void setModemMaterialCodeSAP(String modemMaterialCodeSAP) {
		this.modemMaterialCodeSAP = modemMaterialCodeSAP;
	}
	/**
	 * @return Returns the modemMaterialSAP.
	 */
	public String getModemMaterialSAP() {
		return modemMaterialSAP;
	}
	/**
	 * @param modemMaterialSAP The modemMaterialSAP to set.
	 */
	public void setModemMaterialSAP(String modemMaterialSAP) {
		this.modemMaterialSAP = modemMaterialSAP;
	}
	/**
	 * @return Returns the modemMeasurementUnitSAP.
	 */
	public String getModemMeasurementUnitSAP() {
		return modemMeasurementUnitSAP;
	}
	/**
	 * @param modemMeasurementUnitSAP The modemMeasurementUnitSAP to set.
	 */
	public void setModemMeasurementUnitSAP(String modemMeasurementUnitSAP) {
		this.modemMeasurementUnitSAP = modemMeasurementUnitSAP;
	}
	/**
	 * @return Returns the modemModel.
	 */
	public String getModemModel() {
		return modemModel;
	}
	/**
	 * @param modemModel The modemModel to set.
	 */
	public void setModemModel(String modemModel) {
		this.modemModel = modemModel;
	}
	/**
	 * @return Returns the modemMoveTypeSAP.
	 */
	public String getModemMoveTypeSAP() {
		return modemMoveTypeSAP;
	}
	/**
	 * @param modemMoveTypeSAP The modemMoveTypeSAP to set.
	 */
	public void setModemMoveTypeSAP(String modemMoveTypeSAP) {
		this.modemMoveTypeSAP = modemMoveTypeSAP;
	}
	/**
	 * @return Returns the modemPepElementSAP.
	 */
	public String getModemPepElementSAP() {
		return modemPepElementSAP;
	}
	/**
	 * @param modemPepElementSAP The modemPepElementSAP to set.
	 */
	public void setModemPepElementSAP(String modemPepElementSAP) {
		this.modemPepElementSAP = modemPepElementSAP;
	}
	/**
	 * @return Returns the modemPlantSAP.
	 */
	public String getModemPlantSAP() {
		return modemPlantSAP;
	}
	/**
	 * @param modemPlantSAP The modemPlantSAP to set.
	 */
	public void setModemPlantSAP(String modemPlantSAP) {
		this.modemPlantSAP = modemPlantSAP;
	}
	/**
	 * @return Returns the modemSerial.
	 */
	public String getModemSerial() {
		return modemSerial;
	}
	/**
	 * @param modemSerial The modemSerial to set.
	 */
	public void setModemSerial(String modemSerial) {
		this.modemSerial = modemSerial;
	}
	/**
	 * @return Returns the modemStorageSAP.
	 */
	public String getModemStorageSAP() {
		return modemStorageSAP;
	}
	/**
	 * @param modemStorageSAP The modemStorageSAP to set.
	 */
	public void setModemStorageSAP(String modemStorageSAP) {
		this.modemStorageSAP = modemStorageSAP;
	}
	/**
	 * @return Returns the modemType.
	 */
	public long getModemType() {
		return modemType;
	}
	/**
	 * @param modemType The modemType to set.
	 */
	public void setModemType(long modemType) {
		this.modemType = modemType;
	}
	/**
	 * @return Returns the modemWork.
	 */
	public long getModemWork() {
		return modemWork;
	}
	/**
	 * @param modemWork The modemWork to set.
	 */
	public void setModemWork(long modemWork) {
		this.modemWork = modemWork;
	}	
	/**
	 * @return Returns the modemPostingDateSAP.
	 */
	public String getModemPostingDateSAP() {
		return modemPostingDateSAP;
	}
	/**
	 * @param modemPostingDateSAP The modemPostingDateSAP to set.
	 */
	public void setModemPostingDateSAP(String modemPostingDateSAP) {
		this.modemPostingDateSAP = modemPostingDateSAP;
	}
}
