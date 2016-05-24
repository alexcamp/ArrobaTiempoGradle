/*
 * Created on Aug 26, 2010
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
public class TR708E extends ResponseHeaderAgendaSC {
	private String idSourceSystem;
	private String idSchedule;
	private String response;
	private String action;
	private Collection equipments;

	
	public int hashCode() {
		return idSchedule.hashCode();
	}
	
	public boolean equals(Object arg0) {
		if (arg0 instanceof TR708E) {
			TR708E other = (TR708E) arg0;
			return super.equals(arg0)
				&& EqualityUtilities.equals(idSourceSystem, other.idSourceSystem)
				&& EqualityUtilities.equals(idSchedule, other.idSchedule)
				&& EqualityUtilities.equals(response, other.response)
				&& EqualityUtilities.equals(equipments, other.equipments);
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
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}
}