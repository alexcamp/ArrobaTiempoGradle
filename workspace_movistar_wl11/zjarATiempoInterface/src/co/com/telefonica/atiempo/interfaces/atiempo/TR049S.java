/*
 * Created on Dec 22, 2010
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
public class TR049S extends RequestHeaderAgendaSC{
	
	private String atisRequestNumber;
	private String errorCode;
	
	public int hashCode(){
		return new Long(atisRequestNumber).intValue();
	}
	
	public boolean equals(Object arg0){
		if(arg0 instanceof TR049S){
			TR049S other = (TR049S)arg0;
			return super.equals(arg0)
			&& EqualityUtilities.equals(atisRequestNumber, other.atisRequestNumber)
			&& EqualityUtilities.equals(errorCode, other.errorCode);
		}
		return false;
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
	/**
	 * @return Returns the errorCode.
	 */
	public String getErrorCode() {
		return errorCode;
	}
	/**
	 * @param errorCode The errorCode to set.
	 */
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
}
