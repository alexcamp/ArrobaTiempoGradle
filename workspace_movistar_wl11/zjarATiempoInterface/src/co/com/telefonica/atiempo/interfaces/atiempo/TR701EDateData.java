/*
 * Created on Aug 24, 2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package co.com.telefonica.atiempo.interfaces.atiempo;

import java.io.Serializable;
import java.util.Date;

import co.com.telefonica.atiempo.util.EqualityUtilities;

/**
 * @author damartinezv
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TR701EDateData implements Serializable {
	private String breakdownDate;
	private String breakdownCommitmentDate;
	
	public int hashCode() {
		return breakdownDate.hashCode();
	}
	
	public boolean equals(Object arg0) {
		if (arg0 instanceof TR701EDateData) {
			TR701EDateData other = (TR701EDateData) arg0;
			return super.equals(arg0)
				&& EqualityUtilities.equals(breakdownDate, other.breakdownDate)
				&& EqualityUtilities.equals(breakdownCommitmentDate, other.breakdownCommitmentDate);
			}
		return false;
	}
	
	public String getBreakdownCommitmentDate() {
		return breakdownCommitmentDate;
	}
	public void setBreakdownCommitmentDate(String breakdownCommitmentDate) {
		this.breakdownCommitmentDate = breakdownCommitmentDate;
	}
	public String getBreakdownDate() {
		return breakdownDate;
	}
	public void setBreakdownDate(String breakdownDate) {
		this.breakdownDate = breakdownDate;
	}
}
