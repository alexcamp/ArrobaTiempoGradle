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
public class TR513E extends RequestHeader{

	private Integer odsNumber;
	private Long atiempoRequestNumber;
	private Long atisRequestNumber;
	private Integer department;
	private Integer city;	
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
	 * @param cni The cni to set.
	 */
	public void setCni(Integer cni) {
		this.cni = cni;
	}
	/**
	 * @param department The department to set.
	 */
	public void setDepartment(Integer department) {
		this.department = department;
	}
	/**
	 * @param odsNumber The odsNumber to set.
	 */
	public void setOdsNumber(Integer odsNumber) {
		this.odsNumber = odsNumber;
	}
	private Integer cni;
	private String requestDate;	
	
			
	public int hashCode(){
		return super.hashCode();
	}
	
	
	public boolean equals(Object arg0) {
			if (arg0 instanceof TR513E) {
				TR513E other = (TR513E) arg0;
				return super.equals(arg0)
					&& EqualityUtilities.equals(requestDate,other.requestDate)
					&& EqualityUtilities.equals(odsNumber,other.odsNumber)
					&& EqualityUtilities.equals(atiempoRequestNumber,other.atiempoRequestNumber)
					&& EqualityUtilities.equals(atisRequestNumber,other.atisRequestNumber)
					&& EqualityUtilities.equals(department,other.department)
					&& EqualityUtilities.equals(city,other.city)
					&& EqualityUtilities.equals(cni,other.cni)
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
	public Integer getOdsNumber() {
		return odsNumber;
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
	 * @param l
	 */
	public void setAtisRequestNumber(long l) {
		atisRequestNumber = new Long(l);
	}

	/**
	 * @param i
	 */
	public void setOdsNumber(int i) {
		odsNumber = new Integer(i);
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
	public Integer getCni() {
		return cni;
	}

	/**
	 * @param i
	 */
	public void setCni(int i) {
		cni = new Integer(i);
	}

}
