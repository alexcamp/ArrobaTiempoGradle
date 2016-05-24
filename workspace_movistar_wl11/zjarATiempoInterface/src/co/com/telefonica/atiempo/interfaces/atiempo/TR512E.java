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
public class TR512E extends RequestHeaderAgendaSC{

	private Integer odsNumber;
	private Long atiempoRequestNumber;
	private Long atisRequestNumber;
	private Integer department;
	private Integer city;	
	private Character hbIndicator;
	private Long comercialOperation;
	private String requestDate;	
	private Collection specialServices;	
	private String password_fttc;	
	public int hashCode(){
		return super.hashCode();
	}
	
	
	public boolean equals(Object arg0) {
			if (arg0 instanceof TR512E) {
				TR512E other = (TR512E) arg0;
				return super.equals(arg0)
					&& EqualityUtilities.equals(specialServices,other.specialServices)
					&& EqualityUtilities.equals(requestDate,other.requestDate)
					&& EqualityUtilities.equals(odsNumber,other.odsNumber)
					&& EqualityUtilities.equals(atiempoRequestNumber,other.atiempoRequestNumber)
					&& EqualityUtilities.equals(atisRequestNumber,other.atisRequestNumber)
					&& EqualityUtilities.equals(department,other.department)
					&& EqualityUtilities.equals(city,other.city)
					&& EqualityUtilities.equals(hbIndicator,other.hbIndicator)
					&& EqualityUtilities.equals(password_fttc,other.password_fttc)
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
	 * @return
	 */
	public Character getHbIndicator() {
		return hbIndicator;
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
	 * @param c
	 */
	public void setHbIndicator(char c) {
		hbIndicator = new Character(c);
	}

	/**
	 * @param string
	 */
	public void setRequestDate(String string) {
		requestDate = string;
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
	 * @param department The department to set.
	 */
	public void setDepartment(Integer department) {
		this.department = department;
	}
	/**
	 * @param hbIndicator The hbIndicator to set.
	 */
	public void setHbIndicator(Character hbIndicator) {
		this.hbIndicator = hbIndicator;
	}
	
	/**
	 * @param odsNumber The odsNumber to set.
	 */
	public void setOdsNumber(Integer odsNumber) {
		this.odsNumber = odsNumber;
	}
	/**
	 * @return Returns the comercialOperation.
	 */
	public Long getComercialOperation() {
		return comercialOperation;
	}
	/**
	 * @param comercialOperation The comercialOperation to set.
	 */
	public void setComercialOperation(Long comercialOperation) {
		this.comercialOperation = comercialOperation;
	}
	public void setComercialOperation(long comercialOperation) {
		this.comercialOperation = new Long(comercialOperation);
	}
	/**
	 * @return Devuelve pasword_fttc.
	 */
	public String getPassword_fttc() {
		return password_fttc;
	}
	/**
	 * @param pasword_fttc El pasword_fttc a establecer.
	 */
	public void setPassword_fttc(String password_fttc) {
		this.password_fttc = password_fttc;
	}
}
