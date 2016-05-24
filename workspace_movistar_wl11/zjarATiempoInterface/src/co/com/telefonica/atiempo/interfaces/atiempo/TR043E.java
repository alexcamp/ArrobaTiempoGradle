/*
 * Created on Apr 21, 2008
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package co.com.telefonica.atiempo.interfaces.atiempo;

import co.com.telefonica.atiempo.util.EqualityUtilities;

/**
 * @author 808026
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class TR043E extends RequestHeader{

	private long atiempoRequestNumber;
	private String serviceNumber;

	
	public int hashCode(){
		return super.hashCode();
	}
	
	public boolean equals(Object arg0) {
			if (arg0 instanceof TR043E) {
				TR043E other = (TR043E) arg0;
				return super.equals(arg0)
				 	&& (atiempoRequestNumber == other.atiempoRequestNumber)
					&& EqualityUtilities.equals(serviceNumber, other.serviceNumber);
			}
			return false;
		}
	
	
	


	/**
	 * @return
	 */
	public String getServiceNumber() {
		return serviceNumber;
	}



	/**
	 * @param string
	 */
	public void setServiceNumber(String string) {
		serviceNumber = string;
	}

	/**
	 * @return
	 */
	public long getAtiempoRequestNumber() {
		return atiempoRequestNumber;
	}

	/**
	 * @param l
	 */
	public void setAtiempoRequestNumber(long l) {
		atiempoRequestNumber = l;
	}

}
