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
public class TR601E extends RequestHeader{

	private Long atisRequestNumber;	
	private Long atiempoRequestNumber;	
	private Integer phoneNumber;	
	private Integer newPhoneNumber;
	private String psCode;
	private Integer department;
	private String atiempoServiceName;
	private Long useType;
	private String cycle;
	private String secretCode;
	private Long cityType;	

	
	public int hashCode(){
		return super.hashCode();
	}
	
	
	public boolean equals(Object arg0) {
			if (arg0 instanceof TR601E) {
				TR601E other = (TR601E) arg0;
				return super.equals(arg0)
					&& EqualityUtilities.equals(useType,other.useType)
					&& EqualityUtilities.equals(cycle,other.cycle)
					&& EqualityUtilities.equals(atisRequestNumber,other.atisRequestNumber)
					&& EqualityUtilities.equals(atiempoRequestNumber,other.atiempoRequestNumber)
					&& EqualityUtilities.equals(atiempoServiceName,other.atiempoServiceName)
					&& EqualityUtilities.equals(phoneNumber,other.phoneNumber)
					&& EqualityUtilities.equals(newPhoneNumber,other.newPhoneNumber)
					&& EqualityUtilities.equals(psCode,other.psCode)
					&& EqualityUtilities.equals(department,other.department)
					&& EqualityUtilities.equals(secretCode,other.secretCode)
					&& EqualityUtilities.equals(cityType,other.cityType)					
					;
			}
			return false;
		}


	public String getCycle() {
		return cycle;
	}
	public void setCycle(String cycle) {
		this.cycle = cycle;
	}
	public Long getUseType() {
		return useType;
	}
	public void setUseType(Long useType) {
		this.useType = useType;
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
	public String getAtiempoServiceName() {
		return atiempoServiceName;
	}
	public void setAtiempoServiceName(String atiempoServiceName) {
		this.atiempoServiceName = atiempoServiceName;
	}
	public Integer getDepartment() {
		return department;
	}
	public void setDepartment(Integer department) {
		this.department = department;
	}
	public Integer getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(Integer phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public Integer getNewPhoneNumber() {
		return newPhoneNumber;
	}
	public void setNewPhoneNumber(Integer newPhoneNumber) {
		this.newPhoneNumber = newPhoneNumber;
	}
	public String getPsCode() {
		return psCode;
	}
	public void setPsCode(String psCode) {
		this.psCode = psCode;
	}
	public String getSecretCode() {
		return secretCode;
	}
	public void setSecretCode(String secretCode) {
		this.secretCode = secretCode;
	}
	public Long getCityType() {
		return cityType;
	}
	public void setCityType(Long cityType) {
		this.cityType = cityType;
	}	
}