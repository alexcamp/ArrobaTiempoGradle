/*
 * Created on Jul 8, 2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package co.com.telefonica.atiempo.interfaces.atiempo;

import java.io.Serializable;

import co.com.telefonica.atiempo.util.EqualityUtilities;

/**
 * @author damartinezv
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class RequestHeader2 implements Serializable {
	private int errorCode;
	private String errorMessage;
	
	public int hashCode() {
		return errorCode;
	}
	
	public boolean equals(Object object) {
		if (object instanceof RequestHeader2) {
			RequestHeader2 other = (RequestHeader2) object;
			
			return (errorCode == other.errorCode) &&
				EqualityUtilities.equals(errorMessage, other.errorMessage);
		}
		
		return false;
	}
	
	public int getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}
