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
public class TR601S extends ResponseHeader2{

	public int hashCode(){
		return super.hashCode();
	}
	
	private Long atiempoRequestNumber;	
	private String actionSecuence;
	private String altamiraServiceName;
	private String requestStage;
	private String secretCode;
	
	
	public boolean equals(Object arg0) {
			if (arg0 instanceof TR601S) {
				TR601S other = (TR601S) arg0;

				return super.equals(arg0)
				&& EqualityUtilities.equals(atiempoRequestNumber,other.atiempoRequestNumber)
				&& EqualityUtilities.equals(actionSecuence,other.actionSecuence)
				&& EqualityUtilities.equals(requestStage,other.requestStage)
				&& EqualityUtilities.equals(altamiraServiceName,other.altamiraServiceName)
				&& EqualityUtilities.equals(secretCode,other.secretCode)
				;
			}
			return false;
		}
	
	public String getActionSecuence() {
		return actionSecuence;
	}
	public void setActionSecuence(String actionSecuence) {
		this.actionSecuence = actionSecuence;
	}
	public Long getAtiempoRequestNumber() {
		return atiempoRequestNumber;
	}
	public void setAtiempoRequestNumber(Long atiempoRequestNumber) {
		this.atiempoRequestNumber = atiempoRequestNumber;
	}
	

	public String getRequestStage() {
		return requestStage;
	}
	public void setRequestStage(String requestStage) {
		this.requestStage = requestStage;
	}
	public String getAltamiraServiceName() {
		return altamiraServiceName;
	}
	public void setAltamiraServiceName(String altamiraServiceName) {
		this.altamiraServiceName = altamiraServiceName;
	}
	
	public String getSecretCode() {
		return secretCode;
	}

	public void setSecretCode(String secretCode) {
		this.secretCode = secretCode;
	}
}

