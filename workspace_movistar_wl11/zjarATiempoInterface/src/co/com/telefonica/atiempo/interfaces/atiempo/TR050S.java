/*
 * Created on Dec 27, 2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package co.com.telefonica.atiempo.interfaces.atiempo;

import co.com.telefonica.atiempo.util.EqualityUtilities;

/**
 * @author idrincon
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TR050S extends RequestHeaderAgendaSC{
	
	private String atisRequestNumber;
	private String atiempoRequestNumber;
	private String action;
	private String activationDate;
	
	

	public int hashCode(){
		return new Long(atisRequestNumber).intValue();
	}
	
	public boolean equals(Object arg0){
		if(arg0 instanceof TR050S){
			TR050S other = (TR050S) arg0;
			return super.equals(arg0)
				&& EqualityUtilities.equals(atisRequestNumber,other.atisRequestNumber)
				&& EqualityUtilities.equals(atiempoRequestNumber, other.atiempoRequestNumber)
				&& EqualityUtilities.equals(action, other.action)
				&& EqualityUtilities.equals(activationDate, other.activationDate);
		}else{
			return false;
		}
	}
	/**
	 * @return Returns the activationDate.
	 */
	public String getActivationDate() {
		return activationDate;
	}
	/**
	 * @param activationDate The activationDate to set.
	 */
	public void setActivationDate(String activationDate) {
		this.activationDate = activationDate;
	}
	/**
	 * @return Returns the action.
	 */
	public String getAction() {
		return action;
	}
	/**
	 * @param action The action to set.
	 */
	public void setAction(String action) {
		this.action = action;
	}

	/**
	 * @return Returns the atiempoRequestNumber.
	 */
	public String getAtiempoRequestNumber() {
		return atiempoRequestNumber;
	}
	/**
	 * @param atiempoRequestNumber The atiempoRequestNumber to set.
	 */
	public void setAtiempoRequestNumber(String atiempoRequestNumber) {
		this.atiempoRequestNumber = atiempoRequestNumber;
	}
	/**
	 * @return Returns the atisRequestNumber.
	 */
	public String getAtisRequestNumber() {
		return atisRequestNumber;
	}
	/**
	 * @param atisRequestNumber The atisRequestNumber to set.
	 */
	public void setAtisRequestNumber(String atisRequestNumber) {
		this.atisRequestNumber = atisRequestNumber;
	}
}
