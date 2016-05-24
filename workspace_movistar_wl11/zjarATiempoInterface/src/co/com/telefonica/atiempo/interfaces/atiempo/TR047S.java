/*
 * Created on Nov 18, 2010
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
public class TR047S extends ResponseHeaderAgendaSC {
	private String status;
	private String licenseCode;
	private String CancelationDate;
	
	public boolean equals(Object arg0) {
		if (arg0 instanceof TR047S) {
			TR047S other = (TR047S) arg0;
			return super.equals(arg0)
				&& EqualityUtilities.equals(status,other.status)
				&& EqualityUtilities.equals(licenseCode,other.licenseCode)
				&& EqualityUtilities.equals(CancelationDate,other.CancelationDate);
		}
		return false;
	}
	
	public int hashCode() {
		return status.hashCode();
	}
	
	public String getCancelationDate() {
		return CancelationDate;
	}
	public void setCancelationDate(String cancelationDate) {
		CancelationDate = cancelationDate;
	}
	public String getLicenseCode() {
		return licenseCode;
	}
	public void setLicenseCode(String licenseCode) {
		this.licenseCode = licenseCode;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
