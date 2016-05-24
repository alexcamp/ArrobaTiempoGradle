/*
 * Created on Feb 22, 2011
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package co.com.telefonica.atiempo.interfaces.atiempo;

import co.com.telefonica.atiempo.util.EqualityUtilities;

/**
 * @author idrincon
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TR135E extends RequestHeaderAgendaSC {
	
	private String brandModem;
	private String modelModem;
	private String serialNumber;
	private String fatherEmail;
	private Long productServiceCode;
	private Long commercialOperationType;
	private String atiempoRequestNumber;
	private Integer phoneNumber;
	private Integer newPhoneNumber;
	
	private String OUI;
	private String serviceType;
	private String serviceValue; 
	private String ipService;
	private String maskService;
	//dcardena 29/03/2015 
	//Ba Naked 
	private String operationAcs;
	private Integer speed;
	private String passwordIMS;
	//fin Ba Naked
	
	public int hashCode(){
		return super.hashCode();
	}
	
	public boolean equals( Object arg0 ){
		if(arg0 instanceof TR135E){
			TR135E other = (TR135E) arg0;
			return super.equals(arg0)
				&& EqualityUtilities.equals(atiempoRequestNumber,other.atiempoRequestNumber)
				&& EqualityUtilities.equals(brandModem,other.brandModem)
				&& EqualityUtilities.equals(commercialOperationType,other.commercialOperationType)
				&& EqualityUtilities.equals(fatherEmail,other.fatherEmail)
				&& EqualityUtilities.equals(modelModem,other.modelModem)
				&& EqualityUtilities.equals(newPhoneNumber,other.newPhoneNumber)
				&& EqualityUtilities.equals(phoneNumber,other.phoneNumber)
				&& EqualityUtilities.equals(productServiceCode,other.productServiceCode)
				&& EqualityUtilities.equals(serialNumber,other.serialNumber)
				&& EqualityUtilities.equals(serviceType,other.serviceType)
				&& EqualityUtilities.equals(serviceValue,other.serviceValue)
				&& EqualityUtilities.equals(ipService,other.ipService)
				&& EqualityUtilities.equals(maskService,other.maskService)
				&& EqualityUtilities.equals(operationAcs,other.operationAcs)
				&& EqualityUtilities.equals(speed,other.speed)
				&& EqualityUtilities.equals(passwordIMS,other.passwordIMS);
		}else{
			return false;
		}
	}
	/**
	 * @return Returns the atiempoRequestNumber.
	 */
	public String getAtiempoRequestNumber() {
		return atiempoRequestNumber;
	}
	/**
	 * @param atiempoRequestNumber The atiempoRequestNumber to set.
	 */
	public void setAtiempoRequestNumber(String atiempoRequestNumber) {
		this.atiempoRequestNumber = atiempoRequestNumber;
	}
	/**
	 * @return Returns the brandModem.
	 */
	public String getBrandModem() {
		return brandModem;
	}
	/**
	 * @param brandModem The brandModem to set.
	 */
	public void setBrandModem(String brandModem) {
		this.brandModem = brandModem;
	}
	/**
	 * @return Returns the commercialOperationType.
	 */
	public Long getCommercialOperationType() {
		return commercialOperationType;
	}
	/**
	 * @param commercialOperationType The commercialOperationType to set.
	 */
	public void setCommercialOperationType(Long commercialOperationType) {
		this.commercialOperationType = commercialOperationType;
	}
	/**
	 * @return Returns the fatherEmail.
	 */
	public String getFatherEmail() {
		return fatherEmail;
	}
	/**
	 * @param fatherEmail The fatherEmail to set.
	 */
	public void setFatherEmail(String fatherEmail) {
		this.fatherEmail = fatherEmail;
	}
	/**
	 * @return Returns the modelModem.
	 */
	public String getModelModem() {
		return modelModem;
	}
	/**
	 * @param modelModem The modelModem to set.
	 */
	public void setModelModem(String modelModem) {
		this.modelModem = modelModem;
	}
	/**
	 * @return Returns the newPhoneNumber.
	 */
	public Integer getNewPhoneNumber() {
		return newPhoneNumber;
	}
	/**
	 * @param newPhoneNumber The newPhoneNumber to set.
	 */
	public void setNewPhoneNumber(Integer newPhoneNumber) {
		this.newPhoneNumber = newPhoneNumber;
	}
	/**
	 * @return Returns the phoneNumber.
	 */
	public Integer getPhoneNumber() {
		return phoneNumber;
	}
	/**
	 * @param phoneNumber The phoneNumber to set.
	 */
	public void setPhoneNumber(Integer phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	/**
	 * @return Returns the productServiceCode.
	 */
	public Long getProductServiceCode() {
		return productServiceCode;
	}
	/**
	 * @param productServiceCode The productServiceCode to set.
	 */
	public void setProductServiceCode(Long productServiceCode) {
		this.productServiceCode = productServiceCode;
	}
	/**
	 * @return Returns the serialNumber.
	 */
	public String getSerialNumber() {
		return serialNumber;
	}
	/**
	 * @param serialNumber The serialNumber to set.
	 */
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	/**
	 * @return Devuelve ipService.
	 */
	public String getIpService() {
		return ipService;
	}
	/**
	 * @param ipService El ipService a establecer.
	 */
	public void setIpService(String ipService) {
		this.ipService = ipService;
	}
	/**
	 * @return Devuelve mask.
	 */
	public String getMaskService() {
		return maskService;
	}
	/**
	 * @param mask El mask a establecer.
	 */
	public void setMaskService(String mask) {
		this.maskService = mask;
	}
	/**
	 * @return Devuelve serviceType.
	 */
	public String getServiceType() {
		return serviceType;
	}
	/**
	 * @param serviceType El serviceType a establecer.
	 */
	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}
	/**
	 * @return Devuelve serviceValue.
	 */
	public String getServiceValue() {
		return serviceValue;
	}
	/**
	 * @param serviceValue El serviceValue a establecer.
	 */
	public void setServiceValue(String serviceValue) {
		this.serviceValue = serviceValue;
	}
	/**
	 * @return Devuelve oUI.
	 */
	public String getOUI() {
		return OUI;
	}
	/**
	 * @param oui El oUI a establecer.
	 */
	public void setOUI(String oui) {
		OUI = oui;
	}
	//dcardena 29/03/2015
	//Ba Naked
	/**
	 * @return Devuelve operationAcs.
	 */
	public String getOperationAcs() {
		return operationAcs;
	}
	/**
	 * @param operationAcs El operationAcs a establecer.
	 */
	public void setOperationAcs(String operationAcs) {
		this.operationAcs = operationAcs;
	}
	/**
	 * @return Devuelve speed.
	 */
	public Integer getSpeed() {
		return speed;
	}
	/**
	 * @param speed El speed a establecer.
	 */
	public void setSpeed(Integer speed) {
		this.speed = speed;
	}
	//fin Ba Naked
	/**
	 * @return Devuelve passwordIMS.
	 */
	public String getPasswordIMS() {
		return passwordIMS;
	}
	/**
	 * @param passwordIMS El passwordIMS a establecer.
	 */
	public void setPasswordIMS(String passwordIMS) {
		this.passwordIMS = passwordIMS;
	}
}
