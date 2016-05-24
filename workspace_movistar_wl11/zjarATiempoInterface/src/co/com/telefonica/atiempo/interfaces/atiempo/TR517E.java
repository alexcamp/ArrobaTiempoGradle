/*
 * Created on Apr 21, 2008
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package co.com.telefonica.atiempo.interfaces.atiempo;

import co.com.telefonica.atiempo.util.EqualityUtilities;

/**
 * @author 806713
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class TR517E extends RequestHeader{

	private Long atisRequestNumber;	 
	private Long atiempoRequestNumber;
	private Integer odsNumber; 
	private Integer phoneNumber;
	private Integer operationType;
	private Long newCategory;
	private Long newSubCategory;
	private Long requestType;
	
	public int hashCode(){
		return super.hashCode();
	}
	
	
	public boolean equals(Object arg0) {
			if (arg0 instanceof TR517E) {
				TR517E other = (TR517E) arg0;
				return super.equals(arg0)
					&& EqualityUtilities.equals(operationType,other.operationType)
					&& EqualityUtilities.equals(phoneNumber,other.phoneNumber)
					&& EqualityUtilities.equals(odsNumber,other.odsNumber)
					&& EqualityUtilities.equals(atiempoRequestNumber,other.atiempoRequestNumber)
					&& EqualityUtilities.equals(atisRequestNumber,other.atisRequestNumber)
					&& EqualityUtilities.equals(newCategory,other.newCategory)
					&& EqualityUtilities.equals(newSubCategory,other.newSubCategory)
					&& EqualityUtilities.equals(requestType,other.requestType)
					;
			}
			return false;
		}

	public Long getAtiempoRequestNumber() {
		return atiempoRequestNumber;
	}
	public void setAtiempoRequestNumber(Long atiempoRequestNumber) {
		this.atiempoRequestNumber = atiempoRequestNumber;
	}
	public Long getAtisRequestNumber() {
		return atisRequestNumber;
	}
	public void setAtisRequestNumber(Long atisRequestNumber) {
		this.atisRequestNumber = atisRequestNumber;
	}
	public Integer getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(Integer phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public Integer getOperationType() {
		return operationType;
	}
	public void setOperationType(Integer operationType) {
		this.operationType = operationType;
	}
	
	public Integer getOdsNumber() {
		return odsNumber;
	}
	public void setOdsNumber(Integer odsNumber) {
		this.odsNumber = odsNumber;
	}
	/**
	 * @return Devuelve newCategory.
	 */
	public Long getNewCategory() {
		return newCategory;
	}
	/**
	 * @param newCategory El newCategory a establecer.
	 */
	public void setNewCategory(Long newCategory) {
		this.newCategory = newCategory;
	}
	/**
	 * @return Devuelve newSubCategory.
	 */
	public Long getNewSubCategory() {
		return newSubCategory;
	}
	/**
	 * @param newSubCategory El newSubCategory a establecer.
	 */
	public void setNewSubCategory(Long newSubCategory) {
		this.newSubCategory = newSubCategory;
	}
	/**
	 * @return Devuelve requestType.
	 */
	public Long getRequestType() {
		return requestType;
	}
	/**
	 * @param requestType El requestType a establecer.
	 */
	public void setRequestType(Long requestType) {
		this.requestType = requestType;
	}
}
