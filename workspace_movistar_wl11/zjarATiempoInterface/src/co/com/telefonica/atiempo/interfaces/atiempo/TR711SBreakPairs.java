/*
 * Created on Jan 28, 2011
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package co.com.telefonica.atiempo.interfaces.atiempo;

import java.io.Serializable;

import co.com.telefonica.atiempo.util.EqualityUtilities;

/**
 * @author idrincon
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class TR711SBreakPairs implements Serializable {

	private String family;

	private String breakCode;

	private String observations;

	public int hashCode() {
		return super.hashCode();
	}

	public boolean equals(Object arg0) {
		if (arg0 instanceof TR711SBreakPairs) {
			TR711SBreakPairs other = (TR711SBreakPairs) arg0;
			return super.equals(arg0)
					&& EqualityUtilities.equals(family, other.family)
					&& EqualityUtilities.equals(breakCode, other.breakCode);
		}
		return false;
	}

	/**
	 * @return Returns the breakCode.
	 */
	public String getBreakCode() {
		return breakCode;
	}

	/**
	 * @param breakCode
	 *            The breakCode to set.
	 */
	public void setBreakCode(String breakCode) {
		this.breakCode = breakCode;
	}

	/**
	 * @return Returns the family.
	 */
	public String getFamily() {
		return family;
	}

	/**
	 * @param family
	 *            The family to set.
	 */
	public void setFamily(String family) {
		this.family = family;
	}

	/**
	 * @param observations
	 *            El observations a establecer.
	 */
	public void setObservations(String observations) {
		this.observations = observations;
	}

	/**
	 * @return Devuelve observations.
	 */
	public String getObservations() {
		return observations;
	}
}