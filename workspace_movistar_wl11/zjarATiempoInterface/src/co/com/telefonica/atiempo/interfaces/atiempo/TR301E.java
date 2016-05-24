/*
 * Created on Jul 06, 2011
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package co.com.telefonica.atiempo.interfaces.atiempo;

import co.com.telefonica.atiempo.util.EqualityUtilities;

/**
 * @author Jsilva
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TR301E extends ResponseHeaderAgendaSC{
	
	private String oimRequest;
	
	/**
	 * @return Devuelve oimRequest.
	 */
	public String getOimRequest() {
		return oimRequest;
	}
	/**
	 * @param oimRequest El oimRequest a establecer.
	 */
	public void setOimRequest(String oimRequest) {
		this.oimRequest = oimRequest;
	}
	public int hashCode(){
		return oimRequest.hashCode();
	}
	
	public boolean equals(Object arg0){
		if(arg0 instanceof TR301E){
			TR301E other = (TR301E)arg0;
			return super.equals(arg0)
			&& EqualityUtilities.equals(oimRequest,other.getOimRequest());
		}
		return false;
	}
	
}
