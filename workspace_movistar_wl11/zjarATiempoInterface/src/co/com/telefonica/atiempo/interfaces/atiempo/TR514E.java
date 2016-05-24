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
public class TR514E extends RequestHeader{

	private Integer department;
	private Integer city;
	private Integer phoneNumber;
	private Long atiempoRequestNumber;	
	private String subCity;
	/**
	 * @param atiempoRequestNumber The atiempoRequestNumber to set.
	 */
	public void setAtiempoRequestNumber(Long atiempoRequestNumber) {
		this.atiempoRequestNumber = atiempoRequestNumber;
	}
	/**
	 * @param city The city to set.
	 */
	public void setCity(Integer city) {
		this.city = city;
	}
	/**
	 * @param department The department to set.
	 */
	public void setDepartment(Integer department) {
		this.department = department;
	}
	/**
	 * @param phoneNumber The phoneNumber to set.
	 */
	public void setPhoneNumber(Integer phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	private String requestDate;	
				
	public int hashCode(){
		return super.hashCode();
	}
	
	
	public boolean equals(Object arg0) {
			if (arg0 instanceof TR514E) {
				TR514E other = (TR514E) arg0;
				return super.equals(arg0)
					&& EqualityUtilities.equals(requestDate,other.requestDate)
					&& EqualityUtilities.equals(department,other.department)
					&& EqualityUtilities.equals(city,other.city)
					&& EqualityUtilities.equals(phoneNumber,other.phoneNumber)
					&& EqualityUtilities.equals(atiempoRequestNumber,other.atiempoRequestNumber)
					&& EqualityUtilities.equals(subCity,other.subCity)
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
	 * @return
	 */
	public Integer getDepartment() {
		return department;
	}

	/**
	 * @param i
	 */
	public void setCity(int i) {
		city = new Integer(i);
	}

	/**
	 * @param i
	 */
	public void setDepartment(int i) {
		department = new Integer(i);
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

}
