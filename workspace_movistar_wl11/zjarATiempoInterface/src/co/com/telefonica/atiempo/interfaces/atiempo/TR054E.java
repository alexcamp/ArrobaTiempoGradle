/*
 * Created on Dec 22, 2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package co.com.telefonica.atiempo.interfaces.atiempo;

/**
 * @author mfmendez
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TR054E extends RequestHeaderAgendaSC{

	private long atisRequestNumber;
	private long atiempoRequestNumber;
	private int commercialOperation;
	private String idPC;
	private String codePlan;
	private String nit;
	private String url;
	private String messageOne;
	private String messageTwo;
	private String messageThree;
	private String region;
	private String promotionOne;
	private String promotionTwo;
	private String promotionThree;
	private String keyWordOne;
	private String keyWordTwo;
	private String keyWordThree;
	private String city;
	private String clientName;
	private String clientEmail;
	private String saleDate;
	private String address;
	private String phone;
	private String cellphone;
	private String contactName;
	private String contactPhone;
	private String contactEmail;
	private String indicative;
	private String openingCode;
	private String diagnosticCode;
	private String breakdownCode;
	private String observationsOne;
	private String observationsTwo;
	private String observationsThree;
	
	/**
	 * 
	 */
	public int hashCode(){
		return super.hashCode();
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
	 * @return Returns the commercialOperation.
	 */
	public int getCommercialOperation() {
		return commercialOperation;
	}
	/**
	 * @param commercialOperation The commercialOperation to set.
	 */
	public void setCommercialOperation(int commercialOperation) {
		this.commercialOperation = commercialOperation;
	}
	/**
	 * @return Returns the address.
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @param address The address to set.
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * @return Returns the breakdownCode.
	 */
	public String getBreakdownCode() {
		return breakdownCode;
	}
	/**
	 * @param breakdownCode The breakdownCode to set.
	 */
	public void setBreakdownCode(String breakdownCode) {
		this.breakdownCode = breakdownCode;
	}
	/**
	 * @return Returns the cellphone.
	 */
	public String getCellphone() {
		return cellphone;
	}
	/**
	 * @param cellphone The cellphone to set.
	 */
	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}
	/**
	 * @return Returns the city.
	 */
	public String getCity() {
		return city;
	}
	/**
	 * @param city The city to set.
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * @return Returns the clientEmail.
	 */
	public String getClientEmail() {
		return clientEmail;
	}
	/**
	 * @param clientEmail The clientEmail to set.
	 */
	public void setClientEmail(String clientEmail) {
		this.clientEmail = clientEmail;
	}
	/**
	 * @return Returns the clientName.
	 */
	public String getClientName() {
		return clientName;
	}
	/**
	 * @param clientName The clientName to set.
	 */
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	/**
	 * @return Returns the codePlan.
	 */
	public String getCodePlan() {
		return codePlan;
	}
	/**
	 * @param codePlan The codePlan to set.
	 */
	public void setCodePlan(String codePlan) {
		this.codePlan = codePlan;
	}
	/**
	 * @return Returns the contactEmail.
	 */
	public String getContactEmail() {
		return contactEmail;
	}
	/**
	 * @param contactEmail The contactEmail to set.
	 */
	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}
	/**
	 * @return Returns the contactName.
	 */
	public String getContactName() {
		return contactName;
	}
	/**
	 * @param contactName The contactName to set.
	 */
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	/**
	 * @return Returns the contactPhone.
	 */
	public String getContactPhone() {
		return contactPhone;
	}
	/**
	 * @param contactPhone The contactPhone to set.
	 */
	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}
	/**
	 * @return Returns the diagnosticCode.
	 */
	public String getDiagnosticCode() {
		return diagnosticCode;
	}
	/**
	 * @param diagnosticCode The diagnosticCode to set.
	 */
	public void setDiagnosticCode(String diagnosticCode) {
		this.diagnosticCode = diagnosticCode;
	}
	/**
	 * @return Returns the idPC.
	 */
	public String getIdPC() {
		return idPC;
	}
	/**
	 * @param idPC The idPC to set.
	 */
	public void setIdPC(String idPC) {
		this.idPC = idPC;
	}
	/**
	 * @return Returns the indicative.
	 */
	public String getIndicative() {
		return indicative;
	}
	/**
	 * @param indicative The indicative to set.
	 */
	public void setIndicative(String indicative) {
		this.indicative = indicative;
	}
	/**
	 * @return Returns the keyWordOne.
	 */
	public String getKeyWordOne() {
		return keyWordOne;
	}
	/**
	 * @param keyWordOne The keyWordOne to set.
	 */
	public void setKeyWordOne(String keyWordOne) {
		this.keyWordOne = keyWordOne;
	}
	/**
	 * @return Returns the keyWordThree.
	 */
	public String getKeyWordThree() {
		return keyWordThree;
	}
	/**
	 * @param keyWordThree The keyWordThree to set.
	 */
	public void setKeyWordThree(String keyWordThree) {
		this.keyWordThree = keyWordThree;
	}
	/**
	 * @return Returns the keyWordTwo.
	 */
	public String getKeyWordTwo() {
		return keyWordTwo;
	}
	/**
	 * @param keyWordTwo The keyWordTwo to set.
	 */
	public void setKeyWordTwo(String keyWordTwo) {
		this.keyWordTwo = keyWordTwo;
	}
	/**
	 * @return Returns the messageOne.
	 */
	public String getMessageOne() {
		return messageOne;
	}
	/**
	 * @param messageOne The messageOne to set.
	 */
	public void setMessageOne(String messageOne) {
		this.messageOne = messageOne;
	}
	/**
	 * @return Returns the messageThree.
	 */
	public String getMessageThree() {
		return messageThree;
	}
	/**
	 * @param messageThree The messageThree to set.
	 */
	public void setMessageThree(String messageThree) {
		this.messageThree = messageThree;
	}
	/**
	 * @return Returns the messageTwo.
	 */
	public String getMessageTwo() {
		return messageTwo;
	}
	/**
	 * @param messageTwo The messageTwo to set.
	 */
	public void setMessageTwo(String messageTwo) {
		this.messageTwo = messageTwo;
	}
	/**
	 * @return Returns the nit.
	 */
	public String getNit() {
		return nit;
	}
	/**
	 * @param nit The nit to set.
	 */
	public void setNit(String nit) {
		this.nit = nit;
	}
	/**
	 * @return Returns the observationsOne.
	 */
	public String getObservationsOne() {
		return observationsOne;
	}
	/**
	 * @param observationsOne The observationsOne to set.
	 */
	public void setObservationsOne(String observationsOne) {
		this.observationsOne = observationsOne;
	}
	/**
	 * @return Returns the observationsThree.
	 */
	public String getObservationsThree() {
		return observationsThree;
	}
	/**
	 * @param observationsThree The observationsThree to set.
	 */
	public void setObservationsThree(String observationsThree) {
		this.observationsThree = observationsThree;
	}
	/**
	 * @return Returns the observationsTwo.
	 */
	public String getObservationsTwo() {
		return observationsTwo;
	}
	/**
	 * @param observationsTwo The observationsTwo to set.
	 */
	public void setObservationsTwo(String observationsTwo) {
		this.observationsTwo = observationsTwo;
	}
	/**
	 * @return Returns the openingCode.
	 */
	public String getOpeningCode() {
		return openingCode;
	}
	/**
	 * @param openingCode The openingCode to set.
	 */
	public void setOpeningCode(String openingCode) {
		this.openingCode = openingCode;
	}
	/**
	 * @return Returns the phone.
	 */
	public String getPhone() {
		return phone;
	}
	/**
	 * @param phone The phone to set.
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**
	 * @return Returns the promotionOne.
	 */
	public String getPromotionOne() {
		return promotionOne;
	}
	/**
	 * @param promotionOne The promotionOne to set.
	 */
	public void setPromotionOne(String promotionOne) {
		this.promotionOne = promotionOne;
	}
	/**
	 * @return Returns the promotionThree.
	 */
	public String getPromotionThree() {
		return promotionThree;
	}
	/**
	 * @param promotionThree The promotionThree to set.
	 */
	public void setPromotionThree(String promotionThree) {
		this.promotionThree = promotionThree;
	}
	/**
	 * @return Returns the promotionTwo.
	 */
	public String getPromotionTwo() {
		return promotionTwo;
	}
	/**
	 * @param promotionTwo The promotionTwo to set.
	 */
	public void setPromotionTwo(String promotionTwo) {
		this.promotionTwo = promotionTwo;
	}
	/**
	 * @return Returns the region.
	 */
	public String getRegion() {
		return region;
	}
	/**
	 * @param region The region to set.
	 */
	public void setRegion(String region) {
		this.region = region;
	}
	/**
	 * @return Returns the saleDate.
	 */
	public String getSaleDate() {
		return saleDate;
	}
	/**
	 * @param saleDate The saleDate to set.
	 */
	public void setSaleDate(String saleDate) {
		this.saleDate = saleDate;
	}
	/**
	 * @return Returns the url.
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * @param url The url to set.
	 */
	public void setUrl(String url) {
		this.url = url;
	}
}
