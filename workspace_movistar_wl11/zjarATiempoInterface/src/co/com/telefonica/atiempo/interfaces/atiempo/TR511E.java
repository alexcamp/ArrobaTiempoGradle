/*
 * Created on Apr 21, 2008
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package co.com.telefonica.atiempo.interfaces.atiempo;

import java.util.Collection;
import java.util.Date;
import java.text.SimpleDateFormat;
import co.com.telefonica.atiempo.util.EqualityUtilities;

/**
 * @author 806713
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class TR511E extends RequestHeaderAgendaSC{

	private Integer phoneNumber;
	private Integer previousNumber;
	private Integer operationType;
	private Long atiempoRequestNumber;
	private Long atisRequestNumber;
	private Integer odsNumber;
	private Long newCategory;
	private Long newSubCategory;
	private Integer requestPbx;
	private String requestDate;
	private Collection specialServices;		
			
	public int hashCode(){
		return super.hashCode();
	}
	
	
	public boolean equals(Object arg0) {
			if (arg0 instanceof TR511E) {
				TR511E other = (TR511E) arg0;
				return super.equals(arg0)
					&& EqualityUtilities.equals(specialServices,other.specialServices)
					&& EqualityUtilities.equals(requestDate,other.requestDate)
					&& EqualityUtilities.equals(phoneNumber,other.phoneNumber)
					&& EqualityUtilities.equals(previousNumber,other.previousNumber)
					&& EqualityUtilities.equals(operationType,other.operationType)
					&& EqualityUtilities.equals(atiempoRequestNumber,other.atiempoRequestNumber)
					&& EqualityUtilities.equals(atisRequestNumber,other.atisRequestNumber)
					&& EqualityUtilities.equals(newCategory,other.newCategory)
					&& EqualityUtilities.equals(newSubCategory,other.newSubCategory)
					&& EqualityUtilities.equals(odsNumber,other.odsNumber)
					&& EqualityUtilities.equals(requestPbx,other.requestPbx)
					
					;

			}
			return false;
		}


	/**
	 * @return
	 */
	public Long getAtiempoRequestNumber() {
		return atiempoRequestNumber;
	}

	/**
	 * @return
	 */
	public Long getAtisRequestNumber() {
		return atisRequestNumber;
	}

	/**
	 * @return
	 */
	public Long getNewCategory() {
		return newCategory;
	}

	/**
	 * @return
	 */
	public Long getNewSubCategory() {
		return newSubCategory;
	}

	/**
	 * @return
	 */
	public Integer getOdsNumber() {
		return odsNumber;
	}

	/**
	 * @return
	 */
	public Integer getOperationType() {
		return operationType;
	}

	/**
	 * @return
	 */
	public Integer getPhoneNumber() {
		return phoneNumber;
	}

	public void setRequestDate(Date date) {
		
		SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
		requestDate = formateador.format(date);
	}
	
	/**
	 * @return
	 */
	public Integer getPreviousNumber() {
		return previousNumber;
	}


	/**
	 * @return
	 */
	public Integer getRequestPbx() {
		return requestPbx;
	}

	/**
	 * @return
	 */
	public Collection getSpecialServices() {
		return specialServices;
	}

	/**
	 * @param l
	 */
	public void setAtiempoRequestNumber(long l) {
		atiempoRequestNumber = new Long(l);
	}

	/**
	 * @param l
	 */
	public void setAtisRequestNumber(long l) {
		atisRequestNumber = new Long(l);
	}

	/**
	 * @param l
	 */
	public void setNewCategory(long l) {
		newCategory = new Long(l);
	}

	/**
	 * @param l
	 */
	public void setNewSubCategory(long l) {
		newSubCategory = new Long(l);
	}

	/**
	 * @param i
	 */
	public void setOdsNumber(int i) {
		odsNumber = new Integer(i);
	}

	/**
	 * @param i
	 */
	public void setOperationType(int i) {
		operationType = new Integer(i);
	}

	/**
	 * @param i
	 */
	public void setPhoneNumber(int i) {
		phoneNumber = new Integer(i);
	}

	/**
	 * @param i
	 */
	public void setPreviousNumber(int i) {
		previousNumber = new Integer(i);
	}

	/**
	 * @param string
	 */
	public void setRequestDateString(String string) {
		requestDate = string;
	}

	/**
	 * @param i
	 */
	public void setRequestPbx(int i) {
		requestPbx = new Integer(i);
	}

	/**
	 * @param collection
	 */
	public void setSpecialServices(Collection collection) {
		specialServices = collection;
	}

	/**
	 * @return
	 */
	public Date getRequestDate() {
		
		SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
		try
		{
			return formateador.parse(requestDate);
		}
		catch (Exception e)
		{
			return new Date();
		}
	}

	public String getRequestDateString() {
		return requestDate;		
	}
	
	/**
	 * @param atiempoRequestNumber The atiempoRequestNumber to set.
	 */
	public void setAtiempoRequestNumber(Long atiempoRequestNumber) {
		this.atiempoRequestNumber = atiempoRequestNumber;
	}
	/**
	 * @param atisRequestNumber The atisRequestNumber to set.
	 */
	public void setAtisRequestNumber(Long atisRequestNumber) {
		this.atisRequestNumber = atisRequestNumber;
	}
	/**
	 * @param newCategory The newCategory to set.
	 */
	public void setNewCategory(Long newCategory) {
		this.newCategory = newCategory;
	}
	/**
	 * @param newSubCategory The newSubCategory to set.
	 */
	public void setNewSubCategory(Long newSubCategory) {
		this.newSubCategory = newSubCategory;
	}
	/**
	 * @param odsNumber The odsNumber to set.
	 */
	public void setOdsNumber(Integer odsNumber) {
		this.odsNumber = odsNumber;
	}
	/**
	 * @param operationType The operationType to set.
	 */
	public void setOperationType(Integer operationType) {
		this.operationType = operationType;
	}
	/**
	 * @param phoneNumber The phoneNumber to set.
	 */
	public void setPhoneNumber(Integer phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	/**
	 * @param previousNumber The previousNumber to set.
	 */
	public void setPreviousNumber(Integer previousNumber) {
		this.previousNumber = previousNumber;
	}
	/**
	 * @param requestDate The requestDate to set.
	 */
	public void setRequestDate(String requestDate) {
		this.requestDate = requestDate;
	}
	/**
	 * @param requestPbx The requestPbx to set.
	 */
	public void setRequestPbx(Integer requestPbx) {
		this.requestPbx = requestPbx;
	}
}
