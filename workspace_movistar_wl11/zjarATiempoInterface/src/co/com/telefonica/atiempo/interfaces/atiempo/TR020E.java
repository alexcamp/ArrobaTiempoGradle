/*
 * Created on Dec 22, 2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package co.com.telefonica.atiempo.interfaces.atiempo;

import java.util.Collection;

import co.com.telefonica.atiempo.util.EqualityUtilities;

/**
 * @author mfmendez
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TR020E extends RequestHeaderAgendaSC{
	
	private String executionDate;
	private String executionTime;
	private String peticionAtis;
	private String flagMatGesrec;
	
	/*Cabeceras Peticion - Tr020ERequestHeader*/
	private Collection requestsHeader;
	
	/**
	 * 
	 */
	public int hashCode(){
		return super.hashCode();
	}
	
	/**
	 * 
	 */
	public boolean equals(Object arg0) {
		if (arg0 instanceof TR020E) {
			TR020E other = (TR020E) arg0;
			return super.equals(arg0)										
				&& EqualityUtilities.equals(executionDate,other.getExecutionDate())
				&& EqualityUtilities.equals(executionTime,other.getExecutionTime())
				&& EqualityUtilities.equals(requestsHeader,other.getRequestsHeader())
				;
			}
		return false;
	}
	/**
	 * @return Returns the executionDate.
	 */
	public String getExecutionDate() {
		return executionDate;
	}
	/**
	 * @param executionDate The executionDate to set.
	 */
	public void setExecutionDate(String executionDate) {
		this.executionDate = executionDate;
	}
	/**
	 * @return Returns the executionTime.
	 */
	public String getExecutionTime() {
		return executionTime;
	}
	/**
	 * @param executionTime The executionTime to set.
	 */
	public void setExecutionTime(String executionTime) {
		this.executionTime = executionTime;
	}
	/**
	 * @return Returns the requestHeader.
	 */
	public Collection getRequestsHeader() {
		return requestsHeader;
	}
	/**
	 * @param requestHeader The requestHeader to set.
	 */
	public void setRequestsHeader(Collection requestsHeader) {
		this.requestsHeader = requestsHeader;
	}
	/**
	 * @return Returns the flagMatGesrec.
	 */
	public String getFlagMatGesrec() {
		return flagMatGesrec;
	}
	/**
	 * @param flagMatGesrec The flagMatGesrec to set.
	 */
	public void setFlagMatGesrec(String flagMatGesrec) {
		this.flagMatGesrec = flagMatGesrec;
	}
	/**
	 * @return Returns the peticionAtis.
	 */
	public String getPeticionAtis() {
		return peticionAtis;
	}
	/**
	 * @param peticionAtis The peticionAtis to set.
	 */
	public void setPeticionAtis(String peticionAtis) {
		this.peticionAtis = peticionAtis;
	}
}
