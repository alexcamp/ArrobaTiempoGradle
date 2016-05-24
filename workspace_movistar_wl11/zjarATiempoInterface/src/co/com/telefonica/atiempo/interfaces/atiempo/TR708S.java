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
public class TR708S extends RequestHeaderAgendaSC {
	private String idSourceSystem;
	private String idSchedule;
	private Long taskTypeCode;
	private Long clientCode;
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
		if (arg0 instanceof TR708S) {
			TR708S other = (TR708S) arg0;
			return super.equals(arg0)
				&& EqualityUtilities.equals(idSourceSystem, other.idSourceSystem)
				&& EqualityUtilities.equals(idSchedule, other.idSchedule)
				&& EqualityUtilities.equals(clientCode, other.clientCode)
				&& EqualityUtilities.equals(clientNumber, other.clientNumber)
				&& EqualityUtilities.equals(city, other.city)
				&& EqualityUtilities.equals(locationCounty, other.locationCounty)
				&& EqualityUtilities.equals(equipments, other.equipments)
				&& EqualityUtilities.equals(atisRequestNumber, other.atisRequestNumber);
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
	public Long getClientCode() {
		return clientCode;
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
	public Long getTaskTypeCode() {
		return taskTypeCode;
	}
	public void setTaskTypeCode(Long taskTypeCode) {
		this.taskTypeCode = taskTypeCode;
	}
	public void setClientCode(Long clientCode) {
		this.clientCode = clientCode;
	}
	public String getAtisRequestNumber() {
		return atisRequestNumber;
	}
	public void setAtisRequestNumber(String atisRequestNumber) {
		this.atisRequestNumber = atisRequestNumber;
	}
}
