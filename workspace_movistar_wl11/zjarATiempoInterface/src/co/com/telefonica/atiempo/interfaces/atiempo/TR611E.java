package co.com.telefonica.atiempo.interfaces.atiempo;

import co.com.telefonica.atiempo.util.EqualityUtilities;

/*
 * Created on Jun 1, 2011
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

/**
 * @author damartinezv
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TR611E extends ResponseHeaderAgendaSC {
	private Long atiempoRequestNumber;
	private Long atisRequestNumber;
	
	public int hashCode() {
		return atiempoRequestNumber.hashCode();
	}
	
	public boolean equals(Object arg0) {
		if (arg0 instanceof TR611E) {
			TR611E other = (TR611E) arg0;
			return super.equals(arg0)
				&& EqualityUtilities.equals(atiempoRequestNumber, other.atiempoRequestNumber)
				&& EqualityUtilities.equals(atisRequestNumber, other.atisRequestNumber);
			}
		return false;
	}
	
	public Long getAtiempoRequestNumber() {
		return atiempoRequestNumber;
	}
	public void setAtiempoRequestNumber(Long atiempoRequestNumber) {
		this.atiempoRequestNumber = atiempoRequestNumber;
	}
	public Long getAtisRequestNumber() {
		return atisRequestNumber;
	}
	public void setAtisRequestNumber(Long atisRequestNumber) {
		this.atisRequestNumber = atisRequestNumber;
	}
}
