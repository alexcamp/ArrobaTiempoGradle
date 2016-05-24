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
public class TR717S extends RequestHeaderAgendaSC {
	private String idSourceSystem;
	private String idSchedule;
	private String materialCode;
	private String modemBrand;
	private String modelModem;
	private String modemType;
	private String modemSerial;
	
	
	public int hashCode() {
		return idSchedule.hashCode();
	}
	
	public boolean equals(Object arg0) {
		if (arg0 instanceof TR717S) {
			TR717S other = (TR717S) arg0;
			return super.equals(arg0)
				&& EqualityUtilities.equals(idSourceSystem, other.idSourceSystem)
				&& EqualityUtilities.equals(idSchedule, other.idSchedule)
				&& EqualityUtilities.equals(materialCode, other.materialCode)
				&& EqualityUtilities.equals(modemBrand, other.modemBrand)
				&& EqualityUtilities.equals(modelModem, other.modelModem)
				&& EqualityUtilities.equals(modemType, other.modemType)
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
	public String getMaterialCode() {
		return materialCode;
	}
	public void setMaterialCode(String materialCode) {
		this.materialCode = materialCode;
	}
	public String getModemBrand() {
		return modemBrand;
	}
	public void setModemBrand(String modemBrand) {
		this.modemBrand = modemBrand;
	}
	public String getModemSerial() {
		return modemSerial;
	}
	public void setModemSerial(String modemSerial) {
		this.modemSerial = modemSerial;
	}
	public String getModemType() {
		return modemType;
	}
	public void setModemType(String modemType) {
		this.modemType = modemType;
	}
	public String getModelModem() {
		return modelModem;
	}
	public void setModelModem(String modelModem) {
		this.modelModem = modelModem;
	}
	public String getIdSourceSystem() {
		return idSourceSystem;
	}
	public void setIdSourceSystem(String idSourceSystem) {
		this.idSourceSystem = idSourceSystem;
	}
}