/*
 * Created on Apr 21, 2008
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package co.com.telefonica.atiempo.interfaces.atiempo;

/**
 * @author 808026
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class TR512S extends ResponseHeader2{

	private long atiempoRequestNumber;
	private int phoneNumber; 


	public int hashCode(){
		return super.hashCode();
	}
	
	public boolean equals(Object arg0) {
			if (arg0 instanceof TR512S) {
				TR512S other = (TR512S) arg0;

				return super.equals(arg0)
					&& (atiempoRequestNumber == other.atiempoRequestNumber)
					&& (phoneNumber == other.phoneNumber)
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
	public int getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @param l
	 */
	public void setAtiempoRequestNumber(long l) {
		atiempoRequestNumber = l;
	}

	/**
	 * @param i
	 */
	public void setPhoneNumber(int i) {
		phoneNumber = i;
	}

}
