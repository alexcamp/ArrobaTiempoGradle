/*
 * Created on Aug 26, 2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package co.com.telefonica.atiempo.interfaces.atiempo;

import co.com.telefonica.atiempo.interfaces.atiempo.base.ITRxxxBase;
import co.com.telefonica.atiempo.util.EqualityUtilities;

/**
 * @author damartinezv
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TR801E extends ResponseHeaderAgendaSC implements ITRxxxBase{
	private String idSourceSystem;
	private String idSchedule;
	private String apptNumber;
	private String response;
	private String DescripcionError;

	
	public int hashCode() {
		return idSchedule.hashCode();
	}
	
	public boolean equals(Object arg0) {
		if (arg0 instanceof TR801E) {
			TR801E other = (TR801E) arg0;
			return super.equals(arg0)
				&& EqualityUtilities.equals(idSourceSystem, other.idSourceSystem)
				&& EqualityUtilities.equals(idSchedule, other.idSchedule)
				&& EqualityUtilities.equals(response, other.response)
				&& EqualityUtilities.equals(apptNumber, other.apptNumber);
			}
		return false;
	}
	
	public String getIdSchedule() {
		return idSchedule;
	}
	public void setIdSchedule(String idSchedule) {
		this.idSchedule = idSchedule;
	}
	public String getIdSourceSystem() {
		return idSourceSystem;
	}
	public void setIdSourceSystem(String idSourceSystem) {
		this.idSourceSystem = idSourceSystem;
	}
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}
	/**
	 * @return Devuelve apptNumber.
	 */
	public String getApptNumber() {
		return apptNumber;
	}
	/**
	 * @param apptNumber El apptNumber a establecer.
	 */
	public void setApptNumber(String apptNumber) {
		this.apptNumber = apptNumber;
	}
	/**
	 * @return Devuelve descripcionError.
	 */
	public String getDescripcionError() {
		return DescripcionError;
	}
	/**
	 * @param descripcionError El descripcionError a establecer.
	 */
	public void setDescripcionError(String descripcionError) {
		DescripcionError = descripcionError;
	}
}