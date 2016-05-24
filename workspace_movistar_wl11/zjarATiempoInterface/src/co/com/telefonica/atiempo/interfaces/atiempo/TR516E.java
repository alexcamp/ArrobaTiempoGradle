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
public class TR516E extends RequestHeader{

	private Long atisRequestNumber;	
	private Integer phoneNumber;
	private String requestDate;
	private Integer status;
	private Integer city;	
	
	public int hashCode(){
		return super.hashCode();
	}
	
	
	public boolean equals(Object arg0) {
			if (arg0 instanceof TR516E) {
				TR516E other = (TR516E) arg0;
				return super.equals(arg0)
					&& EqualityUtilities.equals(atisRequestNumber,other.atisRequestNumber)
					&& EqualityUtilities.equals(requestDate,other.requestDate)
					&& EqualityUtilities.equals(phoneNumber,other.phoneNumber)
					&& EqualityUtilities.equals(status,other.status)
					&& EqualityUtilities.equals(city,other.city)
					;
			}
			return false;
		}



	public void setRequestDate(Date date) {
		
		SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
		requestDate = formateador.format(date);
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

	/**
	 * @return Returns the atisRequestNumber.
	 */
	public Long getAtisRequestNumber() {
		return atisRequestNumber;
	}
	/**
	 * @param atisRequestNumber The atisRequestNumber to set.
	 */
	public void setAtisRequestNumber(Long atisRequestNumber) {
		this.atisRequestNumber = atisRequestNumber;
	}
	
	public void setAtisRequestNumber(long atisRequestNumber) {
		this.atisRequestNumber = new Long(atisRequestNumber);
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
	 * @param i
	 */
	public void setPhoneNumber(int i) {
		phoneNumber = new Integer(i);
	}



	/**
	 * @return
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * @param i
	 */
	public void setStatus(int i) {
		status = new Integer(i);
	}

	/**
	 * @param city The city to set.
	 */
	public void setCity(Integer city) {
		this.city = city;
	}
	/**
	 * @param phoneNumber The phoneNumber to set.
	 */
	public void setPhoneNumber(Integer phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	/**
	 * @param status The status to set.
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
}
