/*
 * Created on Jun 16, 2011
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package co.com.telefonica.atiempo.interfaces.atiempo;

import co.com.telefonica.atiempo.util.EqualityUtilities;

/**
 * @author damartinezv
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TR703E extends RequestHeaderAgendaSC {
	private String idSystemOrigin;
	private String idSchedule;
	private String initialDate;
	private String finalDate;
	
	public int hashCode() {
		return idSchedule.hashCode();
	}
	
	public boolean equals(Object arg0) {
		if (arg0 instanceof TR703E) {
			TR703E other = (TR703E) arg0;
			return super.equals(arg0)
				&& EqualityUtilities.equals(idSystemOrigin, other.idSystemOrigin)
				&& EqualityUtilities.equals(idSchedule, other.idSchedule)
				&& EqualityUtilities.equals(initialDate, other.initialDate)
				&& EqualityUtilities.equals(finalDate, other.finalDate);
			}
		return false;
	}
	
	public String getFinalDate() {
		return finalDate;
	}
	public void setFinalDate(String finalDate) {
		this.finalDate = finalDate;
	}
	public String getIdSchedule() {
		return idSchedule;
	}
	public void setIdSchedule(String idSchedule) {
		this.idSchedule = idSchedule;
	}
	public String getIdSystemOrigin() {
		return idSystemOrigin;
	}
	public void setIdSystemOrigin(String idSystemOrigin) {
		this.idSystemOrigin = idSystemOrigin;
	}
	public String getInitialDate() {
		return initialDate;
	}
	public void setInitialDate(String initialDate) {
		this.initialDate = initialDate;
	}
	
}
