/*
 * Created on Dec 22, 2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package co.com.telefonica.atiempo.interfaces.atiempo;

import java.util.Collection;

/**
 * @author mfmendez
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TR029E extends RequestHeaderAgendaSC{
	
	private String executionDate;
	private String executionTime;
	private long atiempoRequestNumber;
	private String postingDate;
	private String atisRequestNumber;
	
	/*Posciones - Tr029EPosition*/
	private Collection positions;
	
	
	public int hashCode(){
		return super.hashCode();
	}
	
	/**
	 * @return Returns the atisRequestNumber.
	 */
	public String getAtisRequestNumber() {
		return atisRequestNumber;
	}
	/**
	 * @param atisRequestNumber The atisRequestNumber to set.
	 */
	public void setAtisRequestNumber(String atisRequestNumber) {
		this.atisRequestNumber = atisRequestNumber;
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
	 * @return Returns the positions.
	 */
	public Collection getPositions() {
		return positions;
	}
	/**
	 * @param positions The positions to set.
	 */
	public void setPositions(Collection positions) {
		this.positions = positions;
	}
	/**
	 * @return Returns the postingDate.
	 */
	public String getPostingDate() {
		return postingDate;
	}
	/**
	 * @param postingDate The postingDate to set.
	 */
	public void setPostingDate(String postingDate) {
		this.postingDate = postingDate;
	}
	
	/**
	 * @return Returns the atiempoRequestNumber.
	 */
	public long getAtiempoRequestNumber() {
		return atiempoRequestNumber;
	}
	/**
	 * @param atiempoRequestNumber The atiempoRequestNumber to set.
	 */
	public void setAtiempoRequestNumber(long atiempoRequestNumber) {
		this.atiempoRequestNumber = atiempoRequestNumber;
	}
}
