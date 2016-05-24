//- Copyright Notice
//-----------------------------------------------------------------------
// (C) Copyright 2007 Telefonica Colombia, http://www.telefonica.com.co
// Bogota, Colombia All Rights Reserved
// THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF Telefonica Colombia
// The copyright notice above does not evidence any actual or intended
// publication of such source code.
//
// $Id: TR007E.java,v 1.1 2011/03/30 18:23:28 lfmartinez Exp $
//-----------------------------------------------------------------------

package co.com.telefonica.atiempo.interfaces.atiempo;

import co.com.telefonica.atiempo.util.EqualityUtilities;

/**
 * @author fmaceachen
 * @version $Revision: 1.1 $
 */
public class TR007E extends RequestHeader {
	private int phoneNumber;
	private String serialNumber;
	private int modemAction;
	private long atisRequestNumber;
	private long contractorId;
	private String brand;
	private String model;
	private String type;
	/*mfmendez - Datos SAP - 8595*/
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
	private String modemFlagMatSAP;
	

	/**
	 * @return Devuelve contractorId.
	 */
	public long getContractorId() {
		return contractorId;
	}
	/**
	 * @param contractorId El contractorId a establecer.
	 */
	public void setContractorId(long contractorId) {
		this.contractorId = contractorId;
	}
	public int getModemAction() {
		return modemAction;
	}

	public int getPhoneNumber() {
		return phoneNumber;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setModemAction(int i) {
		modemAction = i;
	}

	public void setPhoneNumber(int i) {
		phoneNumber = i;
	}

	public void setSerialNumber(String string) {
		serialNumber = string;
	}
	
	public int hashCode(){
		return this.modemAction;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public boolean equals(Object arg0){
		if (arg0 instanceof TR007E) {
			TR007E other = (TR007E) arg0;
			return super.equals(arg0)
				&& phoneNumber == other.phoneNumber
				&& EqualityUtilities.equals(serialNumber, other.serialNumber)
				&& modemAction == other.modemAction
				&& atisRequestNumber == other.atisRequestNumber
				&& contractorId == other.contractorId
				&& EqualityUtilities.equals(brand, other.brand)
				&& EqualityUtilities.equals(model, other.model)
				&& EqualityUtilities.equals(type, other.type)
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
				&& EqualityUtilities.equals(modemPepElementSAP,other.getModemPepElementSAP())
				&& EqualityUtilities.equals(modemFlagMatSAP,other.getModemFlagMatSAP())
				;
		}
		return false;
	}

	public long getAtisRequestNumber() {
		return atisRequestNumber;
	}

	public void setAtisRequestNumber(long l) {
		atisRequestNumber = l;
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
	 * @return Returns the modemFlagMatSAP.
	 */
	public String getModemFlagMatSAP() {
		return modemFlagMatSAP;
	}
	/**
	 * @param modemFlagMatSAP The modemFlagMatSAP to set.
	 */
	public void setModemFlagMatSAP(String modemFlagMatSAP) {
		this.modemFlagMatSAP = modemFlagMatSAP;
	}
}
