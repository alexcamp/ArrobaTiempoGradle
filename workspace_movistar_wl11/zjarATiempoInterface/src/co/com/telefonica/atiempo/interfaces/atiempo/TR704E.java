/*
 * Created on Aug 26, 2010
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
public class TR704E extends RequestHeaderAgendaSC {
	private String idSystemOrigin;
	private String idRequestNumber;
	private String idCancellationSchedule;
	private Date dateCancellationSchedule;
	
	public int hashCode() {
		return idRequestNumber.hashCode();
	}
	
	public boolean equals(Object arg0) {
		if (arg0 instanceof TR704E) {
			TR704E other = (TR704E) arg0;
			return super.equals(arg0)
				&& EqualityUtilities.equals(idSystemOrigin, other.idSystemOrigin)
				&& EqualityUtilities.equals(idRequestNumber, other.idRequestNumber)
				&& EqualityUtilities.equals(idCancellationSchedule, other.idCancellationSchedule)
				&& EqualityUtilities.equals(dateCancellationSchedule, other.dateCancellationSchedule);
			}
		return false;
	}
	
	public Date getDateCancellationSchedule() {
		return dateCancellationSchedule;
	}
	public void setDateCancellationSchedule(Date dateCancellationSchedule) {
		this.dateCancellationSchedule = dateCancellationSchedule;
	}
	public String getIdCancellationSchedule() {
		return idCancellationSchedule;
	}
	public void setIdCancellationSchedule(String idCancellationSchedule) {
		this.idCancellationSchedule = idCancellationSchedule;
	}
	public String getIdRequestNumber() {
		return idRequestNumber;
	}
	public void setIdRequestNumber(String idRequestNumber) {
		this.idRequestNumber = idRequestNumber;
	}
	public String getIdSystemOrigin() {
		return idSystemOrigin;
	}
	public void setIdSystemOrigin(String idSystemOrigin) {
		this.idSystemOrigin = idSystemOrigin;
	}
}
