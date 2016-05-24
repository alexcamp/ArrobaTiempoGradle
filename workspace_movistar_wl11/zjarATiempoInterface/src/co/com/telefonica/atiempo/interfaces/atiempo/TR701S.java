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
public class TR701S extends ResponseHeaderAgendaSC {
	private String idSystemOrigin;
	private String idSchedule;
	private String errorCode;
	private String errorMessage;
	private String atisRequestNumber;
	
	public int hashCode() {
		return idSchedule.hashCode();
	}
	
	public boolean equals(Object arg0) {
		if (arg0 instanceof TR701S) {
			TR701S other = (TR701S) arg0;
			return super.equals(arg0)
				&& EqualityUtilities.equals(idSystemOrigin, other.idSystemOrigin)
				&& EqualityUtilities.equals(idSchedule, other.idSchedule)
				&& EqualityUtilities.equals(errorCode, other.errorCode)
				&& EqualityUtilities.equals(errorMessage, other.errorMessage)
				&& EqualityUtilities.equals(atisRequestNumber, other.atisRequestNumber);
			}
		return false;
	}
	
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public String getIdSchedule() {
		return idSchedule;
	}
	public void setIdSchedule(String idSchedule) {
		this.idSchedule = idSchedule;
	}
	public String getIdSystemOrigin() {
		return idSystemOrigin;
	}
	public void setIdSystemOrigin(String idSystemOrigin) {
		this.idSystemOrigin = idSystemOrigin;
	}
	public String getAtisRequestNumber() {
		return atisRequestNumber;
	}
	public void setAtisRequestNumber(String atisRequestNumber) {
		this.atisRequestNumber = atisRequestNumber;
	}
}
