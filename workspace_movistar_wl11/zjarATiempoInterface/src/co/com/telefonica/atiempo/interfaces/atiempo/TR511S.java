/*
 * Created on Apr 21, 2008
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package co.com.telefonica.atiempo.interfaces.atiempo;

import java.util.Collection;
import co.com.telefonica.atiempo.util.EqualityUtilities;

/**
 * @author 808026
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class TR511S extends ResponseHeader2{

	private long atiempoRequestNumber;
	private Collection specialServices; 
	private long atisRequestNumber;
	private int odsNumber;
	
	public int hashCode(){
		return super.hashCode();
	}
	
	public boolean equals(Object arg0) {
			if (arg0 instanceof TR511S) {
				TR511S other = (TR511S) arg0;

				return super.equals(arg0)
					&& (atiempoRequestNumber == other.atiempoRequestNumber)
					&& (atisRequestNumber == other.atisRequestNumber)
					&& (odsNumber == other.odsNumber)
					&& EqualityUtilities.equals(specialServices,other.specialServices)
					;					
			}
			return false;
		}


	/**
	 * @return
	 */
	public long getAtiempoRequestNumber() {
		return atiempoRequestNumber;
	}

	/**
	 * @return
	 */
	public Collection getSpecialServices() {
		return specialServices;
	}

	/**
	 * @param l
	 */
	public void setAtiempoRequestNumber(long l) {
		atiempoRequestNumber = l;
	}

	/**
	 * @param collection
	 */
	public void setSpecialServices(Collection collection) {
		specialServices = collection;
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
	public int getOdsNumber() {
		return odsNumber;
	}
	public void setOdsNumber(int odsNumber) {
		this.odsNumber = odsNumber;
	}
}
