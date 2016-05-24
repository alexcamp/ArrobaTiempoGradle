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
public class TR041S extends ResponseHeader{

	private Long atiempoRequestNumber;
	private String code;
	private String description;
	private String action;
	
	public int hashCode(){
		return  super.hashCode();
	}
	
	public boolean equals(Object arg0) {
			if (arg0 instanceof TR041S) {
				TR041S other = (TR041S) arg0;
				return super.equals(arg0)
					&& EqualityUtilities.equals(code, other.code)
					&& EqualityUtilities.equals(atiempoRequestNumber, other.atiempoRequestNumber)
					&& EqualityUtilities.equals(action, other.action)
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
	 * @return Returns the atiempoRequestNumber.
	 */
	public Long getAtiempoRequestNumber() {
		return atiempoRequestNumber;
	}
	/**
	 * @param atiempoRequestNumber The atiempoRequestNumber to set.
	 */
	public void setAtiempoRequestNumber(Long atiempoRequestNumber) {
		this.atiempoRequestNumber = atiempoRequestNumber;
	}
	/**
	 * @param atiempoRequestNumber The atiempoRequestNumber to set.
	 */
	public void setAtiempoRequestNumber(long atiempoRequestNumber) {
		this.atiempoRequestNumber = new Long(atiempoRequestNumber);
	}
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
}
