/*
 * Created on Aug 25, 2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package co.com.telefonica.atiempo.interfaces.atiempo;

import java.util.Date;

import co.com.telefonica.atiempo.util.EqualityUtilities;

/**
 * @author damartinezv
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TR705S extends ResponseHeaderAgendaSC {
	private String idSystemOrigin;
	private String idSchedule;
	private String responseReschedule;
	private String rescheduleDate;
	private String codeErrorReschedule;
	private String descriptionErrorReschedule;
	
	public int hashCode() {
		return idSchedule.hashCode();
	}
	
	public boolean equals(Object arg0) {
		if (arg0 instanceof TR705S) {
			TR705S other = (TR705S) arg0;
			return super.equals(arg0)
				&& EqualityUtilities.equals(idSystemOrigin, other.idSystemOrigin)
				&& EqualityUtilities.equals(idSchedule, other.idSchedule)
				&& EqualityUtilities.equals(responseReschedule, other.responseReschedule)
				&& EqualityUtilities.equals(codeErrorReschedule, other.codeErrorReschedule)
				&& EqualityUtilities.equals(descriptionErrorReschedule, other.descriptionErrorReschedule)
				&& EqualityUtilities.equals(rescheduleDate, other.rescheduleDate);
			}
		return false;
	}
	
	public String getCodeErrorReschedule() {
		return codeErrorReschedule;
	}
	public void setCodeErrorReschedule(String codeErrorReschedule) {
		this.codeErrorReschedule = codeErrorReschedule;
	}
	public String getDescriptionErrorReschedule() {
		return descriptionErrorReschedule;
	}
	public void setDescriptionErrorReschedule(String descriptionErrorReschedule) {
		this.descriptionErrorReschedule = descriptionErrorReschedule;
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
	public String getResponseReschedule() {
		return responseReschedule;
	}
	public void setResponseReschedule(String responseReschedule) {
		this.responseReschedule = responseReschedule;
	}
	public String getRescheduleDate() {
		return rescheduleDate;
	}
	public void setRescheduleDate(String rescheduleDate) {
		this.rescheduleDate = rescheduleDate;
	}
}
