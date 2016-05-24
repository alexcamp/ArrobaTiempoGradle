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
public class TR701EMassiveBreakdowns implements Serializable {
	private String breakdownMassiveType;
	private String initialRange;
	private String finalRange;
	
	public int hashCode() {
		return breakdownMassiveType.hashCode();
	}
	
	public boolean equals(Object arg0) {
		if (arg0 instanceof TR701EMassiveBreakdowns) {
			TR701EMassiveBreakdowns other = (TR701EMassiveBreakdowns) arg0;
			return super.equals(arg0)
				&& EqualityUtilities.equals(breakdownMassiveType, other.breakdownMassiveType)
				&& EqualityUtilities.equals(initialRange, other.initialRange)
				&& EqualityUtilities.equals(finalRange, other.finalRange);
			}
		return false;
	}
	
	public String getBreakdownMassiveType() {
		return breakdownMassiveType;
	}
	public void setBreakdownMassiveType(String breakdownMassiveType) {
		this.breakdownMassiveType = breakdownMassiveType;
	}
	public String getFinalRange() {
		return finalRange;
	}
	public void setFinalRange(String finalRange) {
		this.finalRange = finalRange;
	}
	public String getInitialRange() {
		return initialRange;
	}
	public void setInitialRange(String initialRange) {
		this.initialRange = initialRange;
	}
}
