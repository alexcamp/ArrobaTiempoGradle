/*
 * Created on Aug 25, 2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package co.com.telefonica.atiempo.interfaces.atiempo;

import java.io.Serializable;
import java.util.Date;

import co.com.telefonica.atiempo.util.EqualityUtilities;

/**
 * @author damartinezv
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TR702SSchedule implements Serializable {
	private String idSchedule;
	private Long codeStateSchedule;
	private String descriptionStateSchedule;
	private String nameScheduleType;
	private String actionName;
	private String scheduleInitialDate;
	private String scheduleFinalDate;
	private String tecnicalName;
	private String address;
	private String city;
	
	public int hashCode() {
		return codeStateSchedule.intValue();
	}
	
	public boolean equals(Object arg0) {
		if (arg0 instanceof TR702SSchedule) {
			TR702SSchedule other = (TR702SSchedule) arg0;
			return super.equals(arg0)
				&& EqualityUtilities.equals(idSchedule, other.getIdSchedule())
				&& EqualityUtilities.equals(codeStateSchedule, other.getCodeStateSchedule())
				&& EqualityUtilities.equals(descriptionStateSchedule, other.getDescriptionStateSchedule())
				&& EqualityUtilities.equals(nameScheduleType, other.getNameScheduleType())
				&& EqualityUtilities.equals(actionName, other.getActionName())
				&& EqualityUtilities.equals(scheduleInitialDate, other.getScheduleInitialDate())
				&& EqualityUtilities.equals(scheduleFinalDate, other.getScheduleFinalDate())
				&& EqualityUtilities.equals(tecnicalName, other.getTecnicalName())
				&& EqualityUtilities.equals(address, other.getAddress())
				&& EqualityUtilities.equals(city, other.getCity());
			}
		return false;
	}
	
	public String getActionName() {
		return actionName;
	}
	public void setActionName(String actionName) {
		this.actionName = actionName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public Long getCodeStateSchedule() {
		return codeStateSchedule;
	}
	public void setCodeStateSchedule(Long codeStateSchedule) {
		this.codeStateSchedule = codeStateSchedule;
	}
	public String getDescriptionStateSchedule() {
		return descriptionStateSchedule;
	}
	public void setDescriptionStateSchedule(String descriptionStateSchedule) {
		this.descriptionStateSchedule = descriptionStateSchedule;
	}
	public String getNameScheduleType() {
		return nameScheduleType;
	}
	public void setNameScheduleType(String nameScheduleType) {
		this.nameScheduleType = nameScheduleType;
	}
	public String getIdSchedule() {
		return idSchedule;
	}
	public void setIdSchedule(String idSchedule) {
		this.idSchedule = idSchedule;
	}
	public String getScheduleFinalDate() {
		return scheduleFinalDate;
	}
	public void setScheduleFinalDate(String scheduleFinalDate) {
		this.scheduleFinalDate = scheduleFinalDate;
	}
	public String getScheduleInitialDate() {
		return scheduleInitialDate;
	}
	public void setScheduleInitialDate(String scheduleInitialDate) {
		this.scheduleInitialDate = scheduleInitialDate;
	}
	public String getTecnicalName() {
		return tecnicalName;
	}
	public void setTecnicalName(String tecnicalName) {
		this.tecnicalName = tecnicalName;
	}
}
