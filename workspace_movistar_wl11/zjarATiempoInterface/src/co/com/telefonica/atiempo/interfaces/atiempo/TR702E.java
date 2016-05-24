/*
 * Created on Aug 25, 2010
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
public class TR702E extends RequestHeaderAgendaSC {
	private String idSystemOrigin;
	private String idSchedule;
	private String idType;
	private String customerId;
	private String atisRequestNumber;
	private String telephoneNumber;
	
	public int hashCode() {
		return atisRequestNumber.hashCode();
	}
	
	public boolean equals(Object arg0) {
		if (arg0 instanceof TR702E) {
			TR702E other = (TR702E) arg0;
			return super.equals(arg0)
				&& EqualityUtilities.equals(idSystemOrigin, other.getIdSystemOrigin())
				&& EqualityUtilities.equals(idSchedule, other.getIdSchedule())
				&& EqualityUtilities.equals(idType, other.getIdType())
				&& EqualityUtilities.equals(customerId, other.getCustomerId())
				&& EqualityUtilities.equals(atisRequestNumber, other.getAtisRequestNumber())
				&& EqualityUtilities.equals(telephoneNumber, other.getTelephoneNumber());
			}
		return false;
	}
	/**
	 * @return Returns the idSchedule.
	 */
	public String getIdSchedule() {
		return idSchedule;
	}
	/**
	 * @param idSchedule The idSchedule to set.
	 */
	public void setIdSchedule(String idSchedule) {
		this.idSchedule = idSchedule;
	}
	/**
	 * @return Returns the telephoneNumber.
	 */
	public String getTelephoneNumber() {
		return telephoneNumber;
	}
	/**
	 * @param telephoneNumber The telephoneNumber to set.
	 */
	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}		
	public String getAtisRequestNumber() {
		return atisRequestNumber;
	}
	public void setAtisRequestNumber(String atisRequestNumber) {
		this.atisRequestNumber = atisRequestNumber;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getIdSystemOrigin() {
		return idSystemOrigin;
	}
	public void setIdSystemOrigin(String idRequestNumber) {
		this.idSystemOrigin = idRequestNumber;
	}
	public String getIdType() {
		return idType;
	}
	public void setIdType(String idType) {
		this.idType = idType;
	}
}
