//- Copyright Notice
//-----------------------------------------------------------------------
//(C) Copyright 2007 Telefonica Colombia, http://www.telefonica.com.co
//Bogota, Colombia All Rights Reserved
//THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF Telefonica Colombia
//The copyright notice above does not evidence any actual or intended
//publication of such source code.
//
//$Id: TR014S.java,v 1.1 2011/03/30 18:24:04 lfmartinez Exp $
//-----------------------------------------------------------------------

package co.com.telefonica.atiempo.interfaces.atiempo;

import co.com.telefonica.atiempo.util.EqualityUtilities;

/**
* @author ylapchik
* @version $Revision: 1.1 $
*/
public class TR014S extends ResponseHeader{
	private long atisRequestNumber;
	private String errorCode;
	private String errorCodeMessage;
	private int phoneNumber;
	private String port;
	private String pots;
	private String adsl;
	private String lanIp;
	private String wanIp;
	private String mask;
	private String frame;
	private String dslamIp;
	private String vpiVciClient;
	private String vpiVciNetwork;
	private String slot;
	private String fatherEmail;	
	private String modemSerial;
	private String modemBrand;
	private String modemModel;
	private long modemType;
	private String coverageZone;
	/*mfmendez - Campos SAP 8595*/
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
	
	
	public String getCoverageZone() {
		return coverageZone;
	}
	public void setCoverageZone(String coverageZone) {
		this.coverageZone = coverageZone;
	}
	public String getModemBrand() {
		return modemBrand;
	}
	public void setModemBrand(String modemBrand) {
		this.modemBrand = modemBrand;
	}
	public String getModemModel() {
		return modemModel;
	}
	public void setModemModel(String modemModel) {
		this.modemModel = modemModel;
	}
	public long getModemType() {
		return modemType;
	}
	public void setModemType(long modemType) {
		this.modemType = modemType;
	}
	public String getModemSerial() {
		return modemSerial;
	}
	public void setModemSerial(String modemSerial) {
		this.modemSerial = modemSerial;
	}
	public int hashCode(){
		return this.phoneNumber;
	}

	public String getAdsl() {
		return adsl;
	}

	public long getAtisRequestNumber() {
		return atisRequestNumber;
	}

	public String getDslamIp() {
		return dslamIp;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public String getErrorCodeMessage() {
		return errorCodeMessage;
	}

	public String getFrame() {
		return frame;
	}

	public String getLanIp() {
		return lanIp;
	}

	public String getMask() {
		return mask;
	}

	public int getPhoneNumber() {
		return phoneNumber;
	}

	public String getPort() {
		return port;
	}

	public String getPots() {
		return pots;
	}

	public String getSlot() {
		return slot;
	}

	public String getVpiVciClient() {
		return vpiVciClient;
	}

	public String getVpiVciNetwork() {
		return vpiVciNetwork;
	}

	public String getWanIp() {
		return wanIp;
	}

	public String getFatherEmail() {
		return fatherEmail;
	}
	
	public void setAdsl(String string) {
		adsl = string;
	}

	public void setAtisRequestNumber(long l) {
		atisRequestNumber = l;
	}

	public void setDslamIp(String string) {
		dslamIp = string;
	}

	public void setErrorCode(String string) {
		errorCode = string;
	}

	public void setErrorCodeMessage(String string) {
		errorCodeMessage = string;
	}

	public void setFrame(String string) {
		frame = string;
	}

	public void setLanIp(String string) {
		lanIp = string;
	}

	public void setMask(String string) {
		mask = string;
	}

	public void setPhoneNumber(int i) {
		phoneNumber = i;
	}

	public void setPort(String string) {
		port = string;
	}

	public void setPots(String string) {
		pots = string;
	}

	public void setSlot(String string) {
		slot = string;
	}

	public void setVpiVciClient(String string) {
		vpiVciClient = string;
	}

	public void setVpiVciNetwork(String string) {
		vpiVciNetwork = string;
	}

	public void setWanIp(String string) {
		wanIp = string;
	}
	
	public void setFatherEmail(String string) {
		fatherEmail = string;
	}
	
	public boolean equals(Object arg0){
		if (arg0 instanceof TR014S) {
			TR014S other = (TR014S) arg0;
			return super.equals(arg0)
				&& atisRequestNumber == other.atisRequestNumber
				&& phoneNumber == other.phoneNumber
				&& EqualityUtilities.equals(errorCode, other.errorCode)
				&& EqualityUtilities.equals(errorCodeMessage, other.errorCodeMessage)
				&& EqualityUtilities.equals(port, other.port)
				&& EqualityUtilities.equals(pots, other.pots)
				&& EqualityUtilities.equals(adsl,other.adsl)
				&& EqualityUtilities.equals(lanIp, other.lanIp)
				&& EqualityUtilities.equals(wanIp, other.wanIp)
				&& EqualityUtilities.equals(mask, other.mask)
				&& EqualityUtilities.equals(frame, other.frame)
				&& EqualityUtilities.equals(dslamIp, other.dslamIp)
				&& EqualityUtilities.equals(vpiVciClient, other.vpiVciClient)
				&& EqualityUtilities.equals(vpiVciNetwork, other.vpiVciNetwork)
				&& EqualityUtilities.equals(slot, other.slot)
				&& EqualityUtilities.equals(coverageZone, other.coverageZone)
				&& EqualityUtilities.equals(fatherEmail, other.fatherEmail)
				&& EqualityUtilities.equals(modemSerial, other.modemSerial)
			
				&& EqualityUtilities.equals(modemBrand, other.modemBrand)
				&& EqualityUtilities.equals(modemModel, other.modemModel)
				&& (modemType == other.modemType)
				
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
