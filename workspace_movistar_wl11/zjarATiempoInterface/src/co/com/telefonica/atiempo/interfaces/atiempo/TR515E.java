/*
 * Created on Apr 21, 2008
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package co.com.telefonica.atiempo.interfaces.atiempo;

import java.util.Date;
import java.text.SimpleDateFormat;
import co.com.telefonica.atiempo.util.EqualityUtilities;

/**
 * @author 806713
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class TR515E extends RequestHeader{

	private Integer phoneNumber;
	private Long atiempoRequestNumber;	
	private Long atisRequestNumber;		
	private Integer actionType;
	private Integer city;	
	private Integer newPhoneNumber;
	private String subCity;
	private String requestDate;		
	private String psCode;
				
	public int hashCode(){
		return super.hashCode();
	}
	
	
	public boolean equals(Object arg0) {
			if (arg0 instanceof TR515E) {
				TR515E other = (TR515E) arg0;
				return super.equals(arg0)
					&& EqualityUtilities.equals(requestDate,other.requestDate)
					&& EqualityUtilities.equals(subCity,other.subCity)
					&& EqualityUtilities.equals(psCode,other.psCode)
					&& EqualityUtilities.equals(phoneNumber,other.phoneNumber)
					&& EqualityUtilities.equals(atiempoRequestNumber,other.atiempoRequestNumber)
					&& EqualityUtilities.equals(atisRequestNumber,other.atisRequestNumber)
					&& EqualityUtilities.equals(actionType,other.actionType)
					&& EqualityUtilities.equals(newPhoneNumber,other.newPhoneNumber)
					&& EqualityUtilities.equals(city,other.city)
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

	public void setRequestDate(Date date) {
		
		SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
		requestDate = formateador.format(date);
	}

	/**
	 * @param l
	 */
	public void setAtiempoRequestNumber(long l) {
		atiempoRequestNumber = new Long(l);
	}

	/**
	 * @param string
	 */
	public void setRequestDateString(String string) {
		requestDate = string;
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
	 * @return
	 */
	public Integer getCity() {
		return city;
	}

	/**
	 * @param i
	 */
	public void setCity(int i) {
		city = new Integer(i);
	}

	/**
	 * @param string
	 */
	public void setRequestDate(String string) {
		requestDate = string;
	}


	/**
	 * @return
	 */
	public Integer getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @return
	 */
	public String getSubCity() {
		return subCity;
	}

	/**
	 * @param i
	 */
	public void setPhoneNumber(int i) {
		phoneNumber = new Integer(i);
	}

	/**
	 * @param string
	 */
	public void setSubCity(String string) {
		subCity = string;
	}

	/**
	 * @return
	 */
	public Integer getActionType() {
		return actionType;
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
	public Integer getNewPhoneNumber() {
		return newPhoneNumber;
	}

	/**
	 * @return
	 */
	public String getPsCode() {
		return psCode;
	}

	/**
	 * @param i
	 */
	public void setActionType(int i) {
		actionType = new Integer(i);
	}

	/**
	 * @param l
	 */
	public void setAtisRequestNumber(long l) {
		atisRequestNumber = new Long(l);
	}

	/**
	 * @param i
	 */
	public void setNewPhoneNumber(int i) {
		newPhoneNumber = new Integer(i);
	}

	/**
	 * @param string
	 */
	public void setPsCode(String string) {
		psCode = string;
	}

	/**
	 * @param actionType The actionType to set.
	 */
	public void setActionType(Integer actionType) {
		this.actionType = actionType;
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
	 * @param city The city to set.
	 */
	public void setCity(Integer city) {
		this.city = city;
	}
	/**
	 * @param newPhoneNumber The newPhoneNumber to set.
	 */
	public void setNewPhoneNumber(Integer newPhoneNumber) {
		this.newPhoneNumber = newPhoneNumber;
	}
	/**
	 * @param phoneNumber The phoneNumber to set.
	 */
	public void setPhoneNumber(Integer phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
}
