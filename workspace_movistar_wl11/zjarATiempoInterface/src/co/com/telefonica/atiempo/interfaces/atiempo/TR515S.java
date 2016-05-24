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
public class TR515S extends ResponseHeader2{

	private long atiempoRequestNumber;
	private long atisRequestNumber;

	public int hashCode(){
		return super.hashCode();
	}
	
	public boolean equals(Object arg0) {
			if (arg0 instanceof TR515S) {
				TR515S other = (TR515S) arg0;

				return super.equals(arg0)
					&& (atiempoRequestNumber == other.atiempoRequestNumber)
					&& (atisRequestNumber == other.atisRequestNumber)
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


	/**
	 * @return
	 */
	public long getAtisRequestNumber() {
		return atisRequestNumber;
	}

	/**
	 * @param l
	 */
	public void setAtisRequestNumber(long l) {
		atisRequestNumber = l;
	}

}
