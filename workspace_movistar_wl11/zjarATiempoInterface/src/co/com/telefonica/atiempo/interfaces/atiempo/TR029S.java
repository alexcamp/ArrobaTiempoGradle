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
public class TR029S extends ResponseHeaderAgendaSC{
	
	private long atiempoRequestNumber;
	
	public int hashCode(){
		return new Long(atiempoRequestNumber).intValue();
	}
	
	public boolean equals(Object arg0){
		if(arg0 instanceof TR029S){
			TR029S other = (TR029S)arg0;
			return super.equals(arg0)
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
}
