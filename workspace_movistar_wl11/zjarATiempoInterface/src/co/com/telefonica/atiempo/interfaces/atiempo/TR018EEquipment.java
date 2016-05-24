//- Copyright Notice
//-----------------------------------------------------------------------
// (C) Copyright 2007 Telefonica Colombia, http://www.telefonica.com.co
// Bogota, Colombia All Rights Reserved
// THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF Telefonica Colombia
// The copyright notice above does not evidence any actual or intended
// publication of such source code.
//
// $Id: TR018EEquipment.java,v 1.1 2011/03/30 18:24:43 lfmartinez Exp $
//-----------------------------------------------------------------------

package co.com.telefonica.atiempo.interfaces.atiempo;

import java.io.Serializable;

import co.com.telefonica.atiempo.util.EqualityUtilities;

/**
 * @author ylapchik
 * @version $Revision: 1.1 $
 */
public class TR018EEquipment implements Serializable {

	private String casId;
	private int action;
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
	
	/*Datos del disco duro*/
	private String ddtvSerial;
	private String ddtvMarca;
	private String ddtvModelo;

	public int getAction() {
		return action;
	}

	public String getCasId() {
		return casId;
	}

	public void setAction(int i) {
		action = i;
	}

	public void setCasId(String string) {
		casId = string;
	}
	public boolean equals(Object arg0) {
		if (arg0 instanceof TR018EEquipment) {
			TR018EEquipment other = (TR018EEquipment) arg0;
			return EqualityUtilities.equals(casId, other.casId)
				&& action == other.action
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
				&& EqualityUtilities.equals(ddtvSerial, other.getDdtvSerial())
				&& EqualityUtilities.equals(ddtvMarca, other.getDdtvMarca())
				&& EqualityUtilities.equals(ddtvModelo, other.getDdtvModelo());
		}
		return false;
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
