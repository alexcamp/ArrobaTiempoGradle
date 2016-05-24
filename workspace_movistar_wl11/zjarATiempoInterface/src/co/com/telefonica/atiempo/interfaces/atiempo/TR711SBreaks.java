/*
 * Created on Jan 31, 2011
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package co.com.telefonica.atiempo.interfaces.atiempo;

import java.io.Serializable;
import java.util.Collection;

import co.com.telefonica.atiempo.util.EqualityUtilities;

/**
 * @author idrincon
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class TR711SBreaks implements Serializable {

	private Collection breakPairs;

	public int hasCode() {
		return super.hashCode();
	}

	public boolean equals(Object arg0) {
		if (arg0 instanceof TR711SBreaks) {
			TR711SBreaks other = (TR711SBreaks) arg0;
			return super.equals(arg0)
					&& EqualityUtilities.equals(breakPairs, other.breakPairs);
		}
		return false;
	}

	/**
	 * @return Returns the breakPairs.
	 */
	public Collection getBreakPairs() {
		return breakPairs;
	}

	/**
	 * @param breakPairs
	 *            The breakPairs to set.
	 */
	public void setBreakPairs(Collection breakPairs) {
		this.breakPairs = breakPairs;
	}
}