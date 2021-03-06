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
public class TR043S extends ResponseHeader{

	private long atiempoRequestNumber;
	private String code;
	private String description;
	private String fatherEmail;
	private String action;
	
	/**
	 * @return Returns the action.
	 */
	public String getAction() {
		return action;
	}
	/**
	 * @param action The action to set.
	 */
	public void setAction(String action) {
		this.action = action;
	}
	public int hashCode(){
		return super.hashCode();
	}
	
	public boolean equals(Object arg0) {
			if (arg0 instanceof TR043S) {
				TR043S other = (TR043S) arg0;
				return super.equals(arg0)
				 	&& (atiempoRequestNumber == other.atiempoRequestNumber)
					&& EqualityUtilities.equals(code, other.code)
					&& EqualityUtilities.equals(action, other.action)
					&& EqualityUtilities.equals(fatherEmail, other.fatherEmail)
					&& EqualityUtilities.equals(description, other.description);
			}
			return false;
		}
	
	
	


	/**
	 * @return
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @return
	 */
	public String getDescription() {
		return description;
	}



	/**
	 * @param string
	 */
	public void setCode(String string) {
		code = string;
	}

	/**
	 * @param string
	 */
	public void setDescription(String string) {
		description = string;
	}

	/**
	 * @return
	 */
	public String getFatherEmail() {
		return fatherEmail;
	}

	/**
	 * @param string
	 */
	public void setFatherEmail(String string) {
		fatherEmail = string;
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
