/*
 * Created on Jul 8, 2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package co.com.telefonica.atiempo.interfaces.atiempo;

import java.util.Collection;
import java.util.Date;

import co.com.telefonica.atiempo.util.EqualityUtilities;

/**
 * @author lfmartinez
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TR045E extends ResponseHeader {
	
	Collection requests;
		
	public boolean equals(Object arg0) {
		if (arg0 instanceof TR045E) {
			TR045E other = (TR045E) arg0;
			return super.equals(arg0)
				&& getId() == other.getId()
				&& EqualityUtilities.equals(requests,other.requests);
			}
		return false;
	}
		
	/**
	 * @return Returns the requests.
	 */
	public Collection getRequests() {
		return requests;
	}
	/**
	 * @param requests The requests to set.
	 */
	public void setRequests(Collection requests) {
		this.requests = requests;
	}
}
