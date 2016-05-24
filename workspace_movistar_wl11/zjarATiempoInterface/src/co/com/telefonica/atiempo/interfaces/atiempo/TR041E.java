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
public class TR041E extends RequestHeader{

	private Long atiempoRequestNumber;
	private String actualOperatorId;
	private String newOperatorId;
	private String actualTerminalNumber;
	private String newTerminalNumber;
	
	public int hashCode(){
		return  super.hashCode();
	}
	
	public boolean equals(Object arg0) {
			if (arg0 instanceof TR041E) {
				TR041E other = (TR041E) arg0;
				return super.equals(arg0)
					&& EqualityUtilities.equals(atiempoRequestNumber, other.atiempoRequestNumber)
				 	&& EqualityUtilities.equals(actualOperatorId, other.actualOperatorId)
					&& EqualityUtilities.equals(newOperatorId, other.newOperatorId)
					&& EqualityUtilities.equals(actualTerminalNumber, other.actualTerminalNumber)
					&& EqualityUtilities.equals(newTerminalNumber, other.newTerminalNumber);
			}
			return false;
		}
	
	
	
	
	/**
	 * @return
	 */
	public String getActualOperatorId() {
		return actualOperatorId;
	}

	/**
	 * @return
	 */
	public String getActualTerminalNumber() {
		return actualTerminalNumber;
	}

	/**
	 * @return
	 */
	public String getNewOperatorId() {
		return newOperatorId;
	}

	/**
	 * @return
	 */
	public String getNewTerminalNumber() {
		return newTerminalNumber;
	}

	/**
	 * @param string
	 */
	public void setActualOperatorId(String string) {
		actualOperatorId = string;
	}

	/**
	 * @param string
	 */
	public void setActualTerminalNumber(String string) {
		actualTerminalNumber = string;
	}

	/**
	 * @param string
	 */
	public void setNewOperatorId(String string) {
		newOperatorId = string;
	}

	/**
	 * @param string
	 */
	public void setNewTerminalNumber(String string) {
		newTerminalNumber = string;
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

}
