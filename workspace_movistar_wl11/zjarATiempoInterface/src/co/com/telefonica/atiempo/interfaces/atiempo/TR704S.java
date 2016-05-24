/*
 * Created on Aug 26, 2010
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
public class TR704S extends ResponseHeaderAgendaSC {
	private String idSystemOrigin;
	private String idRequestNumber;
	private Long idStateSchedule;
	private String descriptionStateSchedule;
	private String responseCancellationSchedule;
	
	public int hashCode() {
		return idRequestNumber.hashCode();
	}
	
	public boolean equals(Object arg0) {
		if (arg0 instanceof TR704S) {
			TR704S other = (TR704S) arg0;
			return super.equals(arg0)
				&& EqualityUtilities.equals(idSystemOrigin, other.idSystemOrigin)
				&& EqualityUtilities.equals(idRequestNumber, other.idRequestNumber)
				&& EqualityUtilities.equals(idStateSchedule, other.idStateSchedule)
				&& EqualityUtilities.equals(descriptionStateSchedule, other.descriptionStateSchedule)
				&& EqualityUtilities.equals(responseCancellationSchedule, other.responseCancellationSchedule);
			}
		return false;
	}
	
	public String getDescriptionStateSchedule() {
		return descriptionStateSchedule;
	}
	public void setDescriptionStateSchedule(String descriptionStateSchedule) {
		this.descriptionStateSchedule = descriptionStateSchedule;
	}
	public String getIdRequestNumber() {
		return idRequestNumber;
	}
	public void setIdRequestNumber(String idRequestNumber) {
		this.idRequestNumber = idRequestNumber;
	}
	public Long getIdStateSchedule() {
		return idStateSchedule;
	}
	public void setIdStateSchedule(Long idStateSchedule) {
		this.idStateSchedule = idStateSchedule;
	}
	public String getIdSystemOrigin() {
		return idSystemOrigin;
	}
	public void setIdSystemOrigin(String idSystemOrigin) {
		this.idSystemOrigin = idSystemOrigin;
	}
	public String getResponseCancellationSchedule() {
		return responseCancellationSchedule;
	}
	public void setResponseCancellationSchedule(
			String responseCancellationSchedule) {
		this.responseCancellationSchedule = responseCancellationSchedule;
	}
}
