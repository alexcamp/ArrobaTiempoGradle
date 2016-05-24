/*
 * Created on May 02, 2011
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package co.com.telefonica.atiempo.interfaces.atiempo;

/**
 * @author mfmendez
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TR054S extends ResponseHeaderAgendaSC{
	
	private long atisRequestNumber;
	private long atiempoRequestNumber;
	
	/**
	 * 
	 */
	public int hashCode(){
		return new Long(atiempoRequestNumber).intValue();
	}
	
	public boolean equals(Object arg0){
		if(arg0 instanceof TR054S){
			TR054S other = (TR054S)arg0;
			return super.equals(arg0)
			&& atisRequestNumber == other.atisRequestNumber
			&& atiempoRequestNumber == other.atiempoRequestNumber;
		}
		return false;
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
	/**
	 * @return Returns the atisRequestNumber.
	 */
	public long getAtisRequestNumber() {
		return atisRequestNumber;
	}
	/**
	 * @param atisRequestNumber The atisRequestNumber to set.
	 */
	public void setAtisRequestNumber(long atisRequestNumber) {
		this.atisRequestNumber = atisRequestNumber;
	}
}
