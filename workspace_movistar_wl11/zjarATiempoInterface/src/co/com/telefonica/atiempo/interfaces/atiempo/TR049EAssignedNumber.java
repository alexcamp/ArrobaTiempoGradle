/*
 * Created on Dec 27, 2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package co.com.telefonica.atiempo.interfaces.atiempo;

import java.io.Serializable;

import co.com.telefonica.atiempo.util.EqualityUtilities;

/**
 * @author idrincon
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TR049EAssignedNumber implements Serializable {
	
	private String atiempoRequestNumber;
	private String newPhoneNumber;
	private String oldPhoneNumber;
	
	private TR049EBasicLine basicLine;
	private TR049EWideBand wideBand;
	
	
	public int hashCode(){
		return super.hashCode();
	}
	
	public boolean equals(Object arg0){
		if(arg0 instanceof TR049EAssignedNumber){
			TR049EAssignedNumber other = (TR049EAssignedNumber) arg0;
			return super.equals(arg0)
				&& EqualityUtilities.equals(atiempoRequestNumber,other.atiempoRequestNumber)
				&& EqualityUtilities.equals(newPhoneNumber,other.newPhoneNumber)
				&& EqualityUtilities.equals(oldPhoneNumber,other.oldPhoneNumber)
				&& EqualityUtilities.equals(basicLine,other.basicLine)
				&& EqualityUtilities.equals(wideBand,other.wideBand);
		}else{
			return false;
		}
	}
	
	/**
	 * @return Returns the atiempoNumber.
	 */
	public String getAtiempoNumber() {
		return atiempoRequestNumber;
	}
	/**
	 * @param atiempoNumber The atiempoNumber to set.
	 */
	public void setAtiempoNumber(String atiempoRequestNumber) {
		this.atiempoRequestNumber = atiempoRequestNumber;
	}
	/**
	 * @return Returns the newPhoneNumber.
	 */
	public String getNewPhoneNumber() {
		return newPhoneNumber;
	}
	/**
	 * @param newPhoneNumber The newPhoneNumber to set.
	 */
	public void setNewPhoneNumber(String newPhoneNumber) {
		this.newPhoneNumber = newPhoneNumber;
	}
	/**
	 * @return Returns the oldPhoneNumber.
	 */
	public String getOldPhoneNumber() {
		return oldPhoneNumber;
	}
	/**
	 * @param oldPhoneNumber The oldPhoneNumber to set.
	 */
	public void setOldPhoneNumber(String oldPhoneNumber) {
		this.oldPhoneNumber = oldPhoneNumber;
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
	 * @return Returns the basicLine.
	 */
	public TR049EBasicLine getBasicLine() {
		return basicLine;
	}
	/**
	 * @param basicLine The basicLine to set.
	 */
	public void setBasicLine(TR049EBasicLine basicLine) {
		this.basicLine = basicLine;
	}
	/**
	 * @return Returns the wideBand.
	 */
	public TR049EWideBand getWideBand() {
		return wideBand;
	}
	/**
	 * @param wideBand The wideBand to set.
	 */
	public void setWideBand(TR049EWideBand wideBand) {
		this.wideBand = wideBand;
	}
}
