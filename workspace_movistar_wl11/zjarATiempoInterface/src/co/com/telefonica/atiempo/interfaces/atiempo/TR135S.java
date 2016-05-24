/*
 * Created on Feb 22, 2011
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
public class TR135S extends ResponseHeaderAgendaSC {
	
	private String atiempoRequestNumber;
	private String errorCode;
	private String errorDescription;
	
	public int hashCode(){
		return super.hashCode();
	}
	
	public boolean equals( Object arg0 ){
		if( arg0 instanceof TR135S ){
			TR135S other = ( TR135S ) arg0;
			return super.equals(arg0)
				&& EqualityUtilities.equals( atiempoRequestNumber, other.atiempoRequestNumber)
				&& EqualityUtilities.equals( errorCode, other.errorCode)
				&& EqualityUtilities.equals( errorDescription, other.errorDescription);
		}else{
			return false;
		}
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
	/**
	 * @return Returns the errorDescription.
	 */
	public String getErrorDescription() {
		return errorDescription;
	}
	/**
	 * @param errorDescription The errorDescription to set.
	 */
	public void setErrorDescription(String errorDescription) {
		this.errorDescription = errorDescription;
	}
}
