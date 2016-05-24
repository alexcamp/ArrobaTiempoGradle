/*
 * Created on 
 * Created by mfmendez
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package co.com.telefonica.atiempo.interfaces.atiempo;

import co.com.telefonica.atiempo.util.EqualityUtilities;

/**
 * @author mfmendez
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TR025E extends RequestHeaderAgendaSC{
	
	private long atisRequestNumber;
	private long atiempoRequestNumber;
	private String phoneNumber;
	private String idPC;
	
	public int hashCode(){
		return super.hashCode();
	}

	public boolean equals(Object arg0) {
		if (arg0 instanceof TR025E) {
			TR025E other = (TR025E) arg0;
			return super.equals(arg0)
				&& atisRequestNumber == other.getAtisRequestNumber()
				&& atiempoRequestNumber == other.getAtiempoRequestNumber()							
				&& EqualityUtilities.equals(phoneNumber,other.getPhoneNumber())
				&& EqualityUtilities.equals(idPC,other.getIdPC())				
				;
			}
		return false;
	}
	
	
	/**
	 * @return Returns the atiempoRequestNumber.
	 */
	public long getAtiempoRequestNumber() {
		return atiempoRequestNumber;
	}
	/**
	 * @param atiempoRequestNumber The atiempoRequestNumber to set.
	 */
	public void setAtiempoRequestNumber(long atiempoRequestNumber) {
		this.atiempoRequestNumber = atiempoRequestNumber;
	}
	/**
	 * @return Returns the atisRequestNumber.
	 */
	public long getAtisRequestNumber() {
		return atisRequestNumber;
	}
	/**
	 * @param atisRequestNumber The atisRequestNumber to set.
	 */
	public void setAtisRequestNumber(long atisRequestNumber) {
		this.atisRequestNumber = atisRequestNumber;
	}
	/**
	 * @return Returns the idPC.
	 */
	public String getIdPC() {
		return idPC;
	}
	/**
	 * @param idPC The idPC to set.
	 */
	public void setIdPC(String idPC) {
		this.idPC = idPC;
	}
	/**
	 * @return Returns the phoneNumber.
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}
	/**
	 * @param phoneNumber The phoneNumber to set.
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
}
