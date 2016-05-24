/*
 * Created on Aug 24, 2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package co.com.telefonica.atiempo.interfaces.atiempo;

import java.io.Serializable;

import co.com.telefonica.atiempo.util.EqualityUtilities;

/**
 * @author damartinezv
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TR701EAccessTime implements Serializable {
	private String jorney;
	
	public int hashCode() {
		return jorney.hashCode();
	}
	
	public boolean equals(Object arg0) {
		if (arg0 instanceof TR701EAccessTime) {
			TR701EAccessTime other = (TR701EAccessTime) arg0;
			return super.equals(arg0)
				&& EqualityUtilities.equals(jorney, other.jorney);
			}
		return false;
	}
	
	public String getJorney() {
		return jorney;
	}
	public void setJorney(String jorney) {
		this.jorney = jorney;
	}
}
