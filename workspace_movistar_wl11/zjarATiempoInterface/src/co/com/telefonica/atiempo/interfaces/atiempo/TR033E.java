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
public class TR033E extends RequestHeader{

	private long atiempoRequestNumber;
	private String ticketNumber;
	private String installationEndDate;
	private String code;
	private String description;
	private TypeValueList others;
	private boolean reverse;
	
	public int hashCode(){
		return super.hashCode();
	}
	
	public boolean equals(Object arg0) {
		if (arg0 instanceof TR033E) {
			TR033E other = (TR033E) arg0;
			return super.equals(arg0)
				&& (atiempoRequestNumber == other.atiempoRequestNumber)
				&& (reverse == other.reverse)
				&& EqualityUtilities.equals(ticketNumber,other.ticketNumber)
				&& EqualityUtilities.equals(installationEndDate,other.installationEndDate)
				&& EqualityUtilities.equals(description,other.description)
				&& EqualityUtilities.equals(others,other.others)
				&& EqualityUtilities.equals(code,other.code);
			
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
	 * @return
	 */
	public String getInstallationEndDate() {
		return installationEndDate;
	}

	/**
	 * @return
	 */
	public TypeValueList getOthers() {
		return others;
	}

	/**
	 * @return
	 */
	public String getTicketNumber() {
		return ticketNumber;
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
	 * @param string
	 */
	public void setInstallationEndDate(String string) {
		installationEndDate = string;
	}

	/**
	 * @param collection
	 */
	public void setOthers(TypeValueList collection) {
		others = collection;
	}

	/**
	 * @param string
	 */
	public void setTicketNumber(String string) {
		ticketNumber = string;
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
	 * @return Returns the reverse.
	 */
	public boolean isReverse() {
		return reverse;
	}
	/**
	 * @param reverse The reverse to set.
	 */
	public void setReverse(boolean reverse) {
		this.reverse = reverse;
	}
}
