/*
 * Created on Jul 8, 2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package co.com.telefonica.atiempo.interfaces.atiempo;

import java.io.Serializable;

/**
 * @author lfmartinez
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TR045S extends RequestHeader{

	private long atisRequestNumber;
	
	public int hashCode(){
		return new Long(atisRequestNumber).intValue();
	}
	
	public boolean equals(Object arg0) {
		if (arg0 instanceof TR045S) {
			TR045S other = (TR045S) arg0;
			return (atisRequestNumber == other.atisRequestNumber);
		}
		return false;
	}
	
	public long getAtisRequestNumber() {
		return atisRequestNumber;
	}
	public void setAtisRequestNumber(long atiempoRequestNumber) {
		this.atisRequestNumber = atiempoRequestNumber;
	}
}
