/*
 * Created on Mar 10, 2011
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
public class TR717E extends ResponseHeaderAgendaSC {
	private String idSourceSystem;
	private String idSchedule;
	private String response;
	private String responseDescription;
	private String materialCode;
	private String modemSerial;
	
	public int hashCode() {
		return idSchedule.hashCode();
	}
	
	public boolean equals(Object arg0) {
		if (arg0 instanceof TR717E) {
			TR717E other = (TR717E) arg0;
			return super.equals(arg0)
				&& EqualityUtilities.equals(idSourceSystem, other.idSourceSystem)
				&& EqualityUtilities.equals(idSchedule, other.idSchedule)
				&& EqualityUtilities.equals(response, other.response)
				&& EqualityUtilities.equals(responseDescription, other.responseDescription)
				&& EqualityUtilities.equals(materialCode, other.materialCode)
				&& EqualityUtilities.equals(modemSerial, other.modemSerial);
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
	public void setIdSourceSystem(String idSystemSource) {
		this.idSourceSystem = idSystemSource;
	}
	public String getMaterialCode() {
		return materialCode;
	}
	public void setMaterialCode(String materialCode) {
		this.materialCode = materialCode;
	}
	public String getModemSerial() {
		return modemSerial;
	}
	public void setModemSerial(String modemSerial) {
		this.modemSerial = modemSerial;
	}
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}
	public String getResponseDescription() {
		return responseDescription;
	}
	public void setResponseDescription(String responseDescription) {
		this.responseDescription = responseDescription;
	}
}