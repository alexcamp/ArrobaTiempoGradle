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
public class TR711S extends ResponseHeaderAgendaSC {
	private String idSourceSystem;
	private String idSchedule;
	private Integer codeStateSchedule;
	private String stateDescription;
	private String initialAutomaticDate;
	private String finalAutomaticDate;
	private String postageCode;
	private String initialManualDate;
	private String endAchievementScheduleDate;
	private String itAnswer;
	private String itComplement;
	private String itClosing;
	private TR711STechnician technician;
	private Collection notes;
	private Collection materials;
	private TR711SBreaks breaks;
	private String cambioPlan;

	
	public int hashCode() {
		return idSchedule.hashCode();
	}
	
	public boolean equals(Object arg0) {
		if (arg0 instanceof TR711S) {
			TR711S other = (TR711S) arg0;
			return super.equals(arg0)
				&& EqualityUtilities.equals(idSourceSystem, other.idSourceSystem)
				&& EqualityUtilities.equals(idSchedule, other.idSchedule)
				&& EqualityUtilities.equals(codeStateSchedule, other.codeStateSchedule)
				&& EqualityUtilities.equals(stateDescription, other.stateDescription)
				&& EqualityUtilities.equals(initialAutomaticDate, other.initialAutomaticDate)
				&& EqualityUtilities.equals(finalAutomaticDate, other.finalAutomaticDate)
				&& EqualityUtilities.equals(postageCode, other.postageCode)
				&& EqualityUtilities.equals(initialManualDate, other.initialManualDate)
				&& EqualityUtilities.equals(endAchievementScheduleDate, other.endAchievementScheduleDate)
				&& EqualityUtilities.equals(itAnswer, other.itAnswer)
				&& EqualityUtilities.equals(itComplement, other.itComplement)
				&& EqualityUtilities.equals(itClosing, other.itClosing)
				&& EqualityUtilities.equals(technician, other.technician)
				&& EqualityUtilities.equals(notes, other.notes)
				&& EqualityUtilities.equals(materials, other.materials)
				&& EqualityUtilities.equals(breaks, other.breaks);
			}
		return false;
	}
	
	public Integer getCodeStateSchedule() {
		return codeStateSchedule;
	}
	public void setCodeStateSchedule(Integer codeStateSchedule) {
		this.codeStateSchedule = codeStateSchedule;
	}
	public String getEndAchievementScheduleDate() {
		return endAchievementScheduleDate;
	}
	public void setEndAchievementScheduleDate(String endAchievementScheduleDate) {
		this.endAchievementScheduleDate = endAchievementScheduleDate;
	}
	public String getFinalAutomaticDate() {
		return finalAutomaticDate;
	}
	public void setFinalAutomaticDate(String finalAutomaticDate) {
		this.finalAutomaticDate = finalAutomaticDate;
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
	public String getInitialAutomaticDate() {
		return initialAutomaticDate;
	}
	public void setInitialAutomaticDate(String initialAutomaticDate) {
		this.initialAutomaticDate = initialAutomaticDate;
	}
	public String getInitialManualDate() {
		return initialManualDate;
	}
	public void setInitialManualDate(String initialManualDate) {
		this.initialManualDate = initialManualDate;
	}
	public String getPostageCode() {
		return postageCode;
	}
	public void setPostageCode(String postageCode) {
		this.postageCode = postageCode;
	}
	public String getStateDescription() {
		return stateDescription;
	}
	public void setStateDescription(String stateDescription) {
		this.stateDescription = stateDescription;
	}
	public TR711STechnician getTechnician() {
		return technician;
	}
	public void setTechnician(TR711STechnician technician) {
		this.technician = technician;
	}
	public Collection getMaterials() {
		return materials;
	}
	public void setMaterials(Collection materials) {
		this.materials = materials;
	}
	public Collection getNotes() {
		return notes;
	}
	public void setNotes(Collection notes) {
		this.notes = notes;
	}
	public TR711SBreaks getBreaks() {
		return breaks;
	}
	public void setBreaks(TR711SBreaks breaks) {
		this.breaks = breaks;
	}
	public String getItAnswer() {
		return itAnswer;
	}
	public void setItAnswer(String itAnswer) {
		this.itAnswer = itAnswer;
	}
	public String getItClosing() {
		return itClosing;
	}
	public void setItClosing(String itClosing) {
		this.itClosing = itClosing;
	}
	public String getItComplement() {
		return itComplement;
	}
	public void setItComplement(String itComplement) {
		this.itComplement = itComplement;
	}
	/**
	 * @return Devuelve cambioPlan.
	 */
	public String getCambioPlan() {
		return cambioPlan;
	}
	/**
	 * @param cambioPlan El cambioPlan a establecer.
	 */
	public void setCambioPlan(String cambioPlan) {
		this.cambioPlan = cambioPlan;
	}
}
