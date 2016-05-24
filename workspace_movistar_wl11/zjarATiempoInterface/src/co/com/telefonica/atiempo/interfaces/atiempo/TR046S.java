/*
 * Created on Jul 19, 2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package co.com.telefonica.atiempo.interfaces.atiempo;

import java.io.Serializable;

/**
 * @author lmartinezm
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TR046S extends RequestHeader{
	private long atisRequestNumber;
	
	public int hashCode(){
		return new Long(atisRequestNumber).intValue();
	}
	
	public boolean equals(Object arg0) {
		if (arg0 instanceof TR046S) {
			TR046S other = (TR046S) arg0;
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
