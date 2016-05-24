/*
 * Created on Aug 24, 2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package co.com.telefonica.atiempo.interfaces.atiempo;

import java.io.Serializable;
import java.util.Date;

/**
 * @author lmartinezm
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class RequestCanalOnline implements Serializable {
	
	private long atiempoRequestNumber;
	private String stateRequest;
	private String requestInitialDate;
	private String requestChangeDate;
	private String requestFinalDate;
	private String requestDescription;
	private String requestObservation;

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
	 * @return Returns the requestChangeDate.
	 */
	public String getRequestChangeDate() {
		return requestChangeDate;
	}
	/**
	 * @param requestChangeDate The requestChangeDate to set.
	 */
	public void setRequestChangeDate(String requestChangeDate) {
		this.requestChangeDate = requestChangeDate;
	}
	/**
	 * @return Returns the requestDescription.
	 */
	public String getRequestDescription() {
		return requestDescription;
	}
	/**
	 * @param requestDescription The requestDescription to set.
	 */
	public void setRequestDescription(String requestDescription) {
		this.requestDescription = requestDescription;
	}
	/**
	 * @return Returns the requestFinalDate.
	 */
	public String getRequestFinalDate() {
		return requestFinalDate;
	}
	/**
	 * @param requestFinalDate The requestFinalDate to set.
	 */
	public void setRequestFinalDate(String requestFinalDate) {
		this.requestFinalDate = requestFinalDate;
	}
	/**
	 * @return Returns the requestInitialDate.
	 */
	public String getRequestInitialDate() {
		return requestInitialDate;
	}
	/**
	 * @param requestInitialDate The requestInitialDate to set.
	 */
	public void setRequestInitialDate(String requestInitialDate) {
		this.requestInitialDate = requestInitialDate;
	}
	/**
	 * @return Returns the requestObservation.
	 */
	public String getRequestObservation() {
		return requestObservation;
	}
	/**
	 * @param requestObservation The requestObservation to set.
	 */
	public void setRequestObservation(String requestObservation) {
		this.requestObservation = requestObservation;
	}
	/**
	 * @return Returns the stateRequest.
	 */
	public String getStateRequest() {
		return stateRequest;
	}
	/**
	 * @param stateRequest The stateRequest to set.
	 */
	public void setStateRequest(String stateRequest) {
		this.stateRequest = stateRequest;
	}
}
