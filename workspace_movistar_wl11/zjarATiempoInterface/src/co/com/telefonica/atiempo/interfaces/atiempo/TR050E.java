/*
 * Created on Dec 27, 2010
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
public class TR050E extends ResponseHeaderAgendaSC {
	
	private String statusRequest;
	
	public int hashCode(){
		return super.hashCode();
	}
	
	public boolean equals(Object arg0){
		if(arg0 instanceof TR050E){
			TR050E other = (TR050E) arg0;
			return super.equals(arg0)
				&& EqualityUtilities.equals(statusRequest, other.statusRequest);
		}else{
			return false;
		}
	}
	
	/**
	 * @return Returns the statusRequest.
	 */
	public String getStatusRequest() {
		return statusRequest;
	}
	/**
	 * @param statusRequest The statusRequest to set.
	 */
	public void setStatusRequest(String statusRequest) {
		this.statusRequest = statusRequest;
	}
}
