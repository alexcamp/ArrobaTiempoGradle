/*
 * Created on Aug 25, 2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package co.com.telefonica.atiempo.interfaces.atiempo;

import java.util.Collection;

import co.com.telefonica.atiempo.util.EqualityUtilities;

/**
 * @author damartinezv
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TR801S extends RequestHeaderAgendaSC {
	private String idSourceSystem;
	private String idSchedule;
	private String apptNumber;
	private String taskTypeCode;
	private String clientCode;
	private String clientNumber;
	private String city;
	private String locationCounty;
	private String atisRequestNumber;
	private Collection equipments;
	private String action;
	
	public int hashCode() {
		return idSchedule.hashCode();
	}
	
	public boolean equals(Object arg0) {
		if (arg0 instanceof TR801S) {
			TR801S other = (TR801S) arg0;
			return super.equals(arg0)
				&& EqualityUtilities.equals(idSourceSystem, other.idSourceSystem)
				&& EqualityUtilities.equals(idSchedule, other.idSchedule)
				&& EqualityUtilities.equals(clientCode, other.clientCode)
				&& EqualityUtilities.equals(clientNumber, other.clientNumber)
				&& EqualityUtilities.equals(city, other.city)
				&& EqualityUtilities.equals(locationCounty, other.locationCounty)
				&& EqualityUtilities.equals(equipments, other.equipments)
				&& EqualityUtilities.equals(atisRequestNumber, other.atisRequestNumber)
				&& EqualityUtilities.equals(apptNumber, other.apptNumber);
			}
		return false;
	}
	
	
	
	/**
	 * @return Devuelve action.
	 */
	public String getAction() {
		return action;
	}
	/**
	 * @param action El action a establecer.
	 */
	public void setAction(String action) {
		this.action = action;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getClientNumber() {
		return clientNumber;
	}
	public void setClientNumber(String clientNumber) {
		this.clientNumber = clientNumber;
	}
	public Collection getEquipments() {
		return equipments;
	}
	public void setEquipments(Collection equipments) {
		this.equipments = equipments;
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
	public String getLocationCounty() {
		return locationCounty;
	}
	public void setLocationCounty(String locationCounty) {
		this.locationCounty = locationCounty;
	}
	public String getAtisRequestNumber() {
		return atisRequestNumber;
	}
	public void setAtisRequestNumber(String atisRequestNumber) {
		this.atisRequestNumber = atisRequestNumber;
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
	 * @return Devuelve clientCode.
	 */
	public String getClientCode() {
		return clientCode;
	}
	/**
	 * @param clientCode El clientCode a establecer.
	 */
	public void setClientCode(String clientCode) {
		this.clientCode = clientCode;
	}
	/**
	 * @return Devuelve taskTypeCode.
	 */
	public String getTaskTypeCode() {
		return taskTypeCode;
	}
	/**
	 * @param taskTypeCode El taskTypeCode a establecer.
	 */
	public void setTaskTypeCode(String taskTypeCode) {
		this.taskTypeCode = taskTypeCode;
	}
}
