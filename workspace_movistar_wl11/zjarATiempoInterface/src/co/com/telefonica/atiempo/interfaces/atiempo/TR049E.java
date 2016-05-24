/*
 * Created on Dec 22, 2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package co.com.telefonica.atiempo.interfaces.atiempo;

import java.util.Collection;

/**
 * @author idrincon
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TR049E extends ResponseHeaderAgendaSC{
	
	private Collection assignedNumbers;
	
	/**
	 * @return Returns the assignedNumbers.
	 */
	public Collection getAssignedNumbers() {
		return assignedNumbers;
	}
	/**
	 * @param assignedNumbers The assignedNumbers to set.
	 */
	public void setAssignedNumbers(Collection assignedNumbers) {
		this.assignedNumbers = assignedNumbers;
	}
}
