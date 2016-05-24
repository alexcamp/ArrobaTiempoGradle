/*
 * Created on Apr 21, 2008
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package co.com.telefonica.atiempo.interfaces.atiempo;

import co.com.telefonica.atiempo.util.EqualityUtilities;

/**
 * @author 806713
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class IT010S extends RequestHeader{

	private Long breakdownNumber;	
	
	public int hashCode(){
		return super.hashCode();
	}
	
	
	public boolean equals(Object arg0) {
			if (arg0 instanceof IT010S) {
				IT010S other = (IT010S) arg0;
				return super.equals(arg0)
					&& EqualityUtilities.equals(breakdownNumber,other.breakdownNumber)
					;
			}
			return false;
		}


	/**
	 * @return Returns the breakdownNumber.
	 */
	public Long getBreakdownNumber() {
		return breakdownNumber;
	}
	/**
	 * @param breakdownNumber The breakdownNumber to set.
	 */
	public void setBreakdownNumber(Long breakdownNumber) {
		this.breakdownNumber = breakdownNumber;
	}
}
