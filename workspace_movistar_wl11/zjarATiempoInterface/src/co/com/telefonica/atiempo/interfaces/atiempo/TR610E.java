/*
 * Created on May 31, 2011
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package co.com.telefonica.atiempo.interfaces.atiempo;

import co.com.telefonica.atiempo.util.EqualityUtilities;

/**
 * @author damartinezv
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TR610E extends RequestHeaderAgendaSC{
	private long atiempoRequestNumber;
	private int productService;
	private String modemSerialNumber;
	private String simSerialNumber;
	private String clientIdType;
	private String clientIdNumber;
	private String clientLastName;
	private int salesNumber;
	private String cityCode;
	private String clientAddress;
	private String clientAddressCom;
	private String phoneNumber;
	private String modemBrand;
	private String modemModel;
	private String cellPhone;
	
	public int hashCode() {
		return new Long (atiempoRequestNumber).hashCode();
	}
	public boolean equals(Object arg0) {
		if (arg0 instanceof TR610E) {
			TR610E other = (TR610E) arg0;
			return super.equals(arg0)
				&& EqualityUtilities.equals(new Long(atiempoRequestNumber), new Long(other.atiempoRequestNumber))
				&& EqualityUtilities.equals(new Integer(productService), new Integer(other.productService))
				&& EqualityUtilities.equals(modemSerialNumber, other.modemSerialNumber)
				&& EqualityUtilities.equals(simSerialNumber, other.simSerialNumber)
				&& EqualityUtilities.equals(clientIdType, other.clientIdType)
				&& EqualityUtilities.equals(clientIdNumber, other.clientIdNumber)
				&& EqualityUtilities.equals(clientLastName, other.clientLastName)
				&& EqualityUtilities.equals(new Integer(salesNumber), new Integer(other.salesNumber))
				&& EqualityUtilities.equals(cityCode, other.cityCode)
				&& EqualityUtilities.equals(clientAddress, other.clientAddress)
				&& EqualityUtilities.equals(clientAddressCom, other.clientAddressCom)
				&& EqualityUtilities.equals(phoneNumber, other.phoneNumber)
				&& EqualityUtilities.equals(modemBrand, other.modemBrand)
				&& EqualityUtilities.equals(modemModel, other.modemModel)
				&& EqualityUtilities.equals(cellPhone,other.cellPhone);
			}
		return false;
	}
	
	

	public long getAtiempoRequestNumber() {
		return atiempoRequestNumber;
	}
	public void setAtiempoRequestNumber(long atiempoRequestNumber) {
		this.atiempoRequestNumber = atiempoRequestNumber;
	}
	public String getCityCode() {
		return cityCode;
	}
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	public String getClientAddress() {
		return clientAddress;
	}
	public void setClientAddress(String clientAddress) {
		this.clientAddress = clientAddress;
	}
	public String getClientAddressCom() {
		return clientAddressCom;
	}
	public void setClientAddressCom(String clientAddressCom) {
		this.clientAddressCom = clientAddressCom;
	}
	public String getClientIdNumber() {
		return clientIdNumber;
	}
	public void setClientIdNumber(String clientIdNumber) {
		this.clientIdNumber = clientIdNumber;
	}
	public String getClientIdType() {
		return clientIdType;
	}
	public void setClientIdType(String clientIdType) {
		this.clientIdType = clientIdType;
	}
	public String getClientLastName() {
		return clientLastName;
	}
	public void setClientLastName(String clientLastName) {
		this.clientLastName = clientLastName;
	}
	public String getModemSerialNumber() {
		return modemSerialNumber;
	}
	public void setModemSerialNumber(String modemSerialNumber) {
		this.modemSerialNumber = modemSerialNumber;
	}
	public int getProductService() {
		return productService;
	}
	public void setProductService(int productService) {
		this.productService = productService;
	}
	public int getSalesNumber() {
		return salesNumber;
	}
	public void setSalesNumber(int salesNumber) {
		this.salesNumber = salesNumber;
	}
	public String getSimSerialNumber() {
		return simSerialNumber;
	}
	public void setSimSerialNumber(String simSerialNumber) {
		this.simSerialNumber = simSerialNumber;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
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
	
	public String getCellPhone() {
		return cellPhone;
	}
	
	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}
}
