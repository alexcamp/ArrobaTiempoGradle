/*
 * Created on Apr 21, 2008
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package co.com.telefonica.atiempo.interfaces.atiempo;

import co.com.telefonica.atiempo.util.EqualityUtilities;

/**
 * @author 808026
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class TR517S extends ResponseHeader2{

	private Long atiempoRequestNumber;
	private Long atisRequestNumber;
	private Integer odsNumber; 
	private Integer phoneNumber;
	private Integer operationType;

	public int hashCode(){
		return super.hashCode();
	}
	
	public boolean equals(Object arg0) {
			if (arg0 instanceof TR517S) {
				TR517S other = (TR517S) arg0;

				return super.equals(arg0)
					&& EqualityUtilities.equals(atiempoRequestNumber,other.atiempoRequestNumber)
					&& EqualityUtilities.equals(atisRequestNumber,other.atisRequestNumber)
					&& EqualityUtilities.equals(odsNumber,other.odsNumber)
					&& EqualityUtilities.equals(operationType,other.operationType)
					&& EqualityUtilities.equals(phoneNumber,other.phoneNumber)
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
	public Integer getOdsNumber() {
		return odsNumber;
	}
	public void setOdsNumber(Integer odsNumber) {
		this.odsNumber = odsNumber;
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
}
