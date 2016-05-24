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
public class TR513S extends ResponseHeader2{

	private long atiempoRequestNumber;

	
	public int hashCode(){
		return super.hashCode();
	}
	
	public boolean equals(Object arg0) {
			if (arg0 instanceof TR513S) {
				TR513S other = (TR513S) arg0;

				return super.equals(arg0)
					&& (atiempoRequestNumber == other.atiempoRequestNumber)
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
	 * @param l
	 */
	public void setAtiempoRequestNumber(long l) {
		atiempoRequestNumber = l;
	}


}
