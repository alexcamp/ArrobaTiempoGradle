/*
 * Creado el 29/07/2015
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package co.com.telefonica.atiempo.interfaces.atiempo;

import co.com.telefonica.atiempo.util.EqualityUtilities;

/**
 * @author Administrador
 * 
 * TODO Para cambiar la plantilla de este comentario generado, vaya a Ventana -
 * Preferencias - Java - Estilo de código - Plantillas de código
 */
public class TR811S extends RequestHeaderAgendaSC {
	private String idSourceSystem;

	private String idSchedule;

	private String apptNumber;

	private Integer codeStateSchedule;

	private String stateDescription;

	private String finalAutomaticDate;
	private String postageCode;

	private String initialManualDate;

	private String endAchievementScheduleDate;

	private TR811STechnician technician;

	private TR811SNotes notes;

	private TR811SCancelReason cancelReason;

	private TR811SInventory installedInventory;

	private TR811SInventory deinstalledInventory;

	private TR811SInventory customerInventory;
	
	private String material_flag;

	private TR811SBreaks breaks;

	private TR811SNoCompletInst noCompletInst;

	private TR811SCodCompletRep codCompletRep;

	private TR811SOtherData otherData;

	public int hashCode() {
		return idSchedule.hashCode();
	}

	public boolean equals(Object arg0) {
		if (arg0 instanceof TR811S) {
			TR811S other = (TR811S) arg0;
			return super.equals(arg0)
					&& EqualityUtilities.equals(idSourceSystem,
							other.idSourceSystem)
					&& EqualityUtilities.equals(idSchedule, other.idSchedule)
					&& EqualityUtilities.equals(apptNumber, other.apptNumber)
					&& EqualityUtilities.equals(codeStateSchedule,
							other.codeStateSchedule)
					&& EqualityUtilities.equals(stateDescription,
							other.stateDescription)
					&& EqualityUtilities.equals(finalAutomaticDate,
							other.finalAutomaticDate)
					&& EqualityUtilities.equals(postageCode, other.postageCode)
					&& EqualityUtilities.equals(initialManualDate,
							other.initialManualDate)
					&& EqualityUtilities.equals(endAchievementScheduleDate,
							other.endAchievementScheduleDate)
					&& EqualityUtilities.equals(technician, other.technician)
					&& EqualityUtilities.equals(notes, other.notes)
					&& EqualityUtilities.equals(cancelReason,
							other.cancelReason)
					&& EqualityUtilities.equals(installedInventory,
							other.installedInventory)
					&& EqualityUtilities.equals(deinstalledInventory,
							other.deinstalledInventory)
					&& EqualityUtilities.equals(customerInventory,
							other.customerInventory)
					&& EqualityUtilities.equals(breaks, other.breaks)
					&& EqualityUtilities.equals(noCompletInst,
							other.noCompletInst)
					&& EqualityUtilities.equals(codCompletRep,
							other.codCompletRep)
					&& EqualityUtilities.equals(otherData, other.otherData)
					&& EqualityUtilities.equals(material_flag, other.material_flag);

		}
		return false;
	}

	/**
	 * @return Devuelve apptNumber.
	 */
	public String getApptNumber() {
		return apptNumber;
	}

	/**
	 * @param apptNumber
	 *            El apptNumber a establecer.
	 */
	public void setApptNumber(String apptNumber) {
		this.apptNumber = apptNumber;
	}



	/**
	 * @return Devuelve codCompletRep.
	 */
	public TR811SCodCompletRep getCodCompletRep() {
		return codCompletRep;
	}

	/**
	 * @param codCompletRep
	 *            El codCompletRep a establecer.
	 */
	public void setCodCompletRep(TR811SCodCompletRep codCompletRep) {
		this.codCompletRep = codCompletRep;
	}

	/**
	 * @return Devuelve codeStateSchedule.
	 */
	public Integer getCodeStateSchedule() {
		return codeStateSchedule;
	}

	/**
	 * @param codeStateSchedule
	 *            El codeStateSchedule a establecer.
	 */
	public void setCodeStateSchedule(Integer codeStateSchedule) {
		this.codeStateSchedule = codeStateSchedule;
	}


	/**
	 * @return Devuelve endAchievementScheduleDate.
	 */
	public String getEndAchievementScheduleDate() {
		return endAchievementScheduleDate;
	}

	/**
	 * @param endAchievementScheduleDate
	 *            El endAchievementScheduleDate a establecer.
	 */
	public void setEndAchievementScheduleDate(String endAchievementScheduleDate) {
		this.endAchievementScheduleDate = endAchievementScheduleDate;
	}


	/**
	 * @return Devuelve finalAutomaticDate.
	 */
	public String getFinalAutomaticDate() {
		return finalAutomaticDate;
	}

	/**
	 * @param finalAutomaticDate
	 *            El finalAutomaticDate a establecer.
	 */
	public void setFinalAutomaticDate(String finalAutomaticDate) {
		this.finalAutomaticDate = finalAutomaticDate;
	}

	/**
	 * @return Devuelve idSchedule.
	 */
	public String getIdSchedule() {
		return idSchedule;
	}

	/**
	 * @param idSchedule
	 *            El idSchedule a establecer.
	 */
	public void setIdSchedule(String idSchedule) {
		this.idSchedule = idSchedule;
	}

	/**
	 * @return Devuelve idSourceSystem.
	 */
	public String getIdSourceSystem() {
		return idSourceSystem;
	}

	/**
	 * @param idSourceSystem
	 *            El idSourceSystem a establecer.
	 */
	public void setIdSourceSystem(String idSourceSystem) {
		this.idSourceSystem = idSourceSystem;
	}

	/**
	 * @return Devuelve initialManualDate.
	 */
	public String getInitialManualDate() {
		return initialManualDate;
	}

	/**
	 * @param initialManualDate
	 *            El initialManualDate a establecer.
	 */
	public void setInitialManualDate(String initialManualDate) {
		this.initialManualDate = initialManualDate;
	}


	/**
	 * @return Devuelve customerInventory.
	 */


	/**
	 * @return Devuelve noCompletInst.
	 */
	public TR811SNoCompletInst getNoCompletInst() {
		return noCompletInst;
	}

	/**
	 * @param noCompletInst
	 *            El noCompletInst a establecer.
	 */
	public void setNoCompletInst(TR811SNoCompletInst noCompletInst) {
		this.noCompletInst = noCompletInst;
	}




	/**
	 * @return Devuelve cancelReason.
	 */
	public TR811SCancelReason getCancelReason() {
		return cancelReason;
	}
	/**
	 * @param cancelReason El cancelReason a establecer.
	 */
	public void setCancelReason(TR811SCancelReason cancelReason) {
		this.cancelReason = cancelReason;
	}
	/**
	 * @return Devuelve otherData.
	 */
	public TR811SOtherData getOtherData() {
		return otherData;
	}

	/**
	 * @param otherData
	 *            El otherData a establecer.
	 */
	public void setOtherData(TR811SOtherData otherData) {
		this.otherData = otherData;
	}

	/**
	 * @return Devuelve postageCode.
	 */
	public String getPostageCode() {
		return postageCode;
	}

	/**
	 * @param postageCode
	 *            El postageCode a establecer.
	 */
	public void setPostageCode(String postageCode) {
		this.postageCode = postageCode;
	}

	/**
	 * @return Devuelve stateDescription.
	 */
	public String getStateDescription() {
		return stateDescription;
	}

	/**
	 * @param stateDescription
	 *            El stateDescription a establecer.
	 */
	public void setStateDescription(String stateDescription) {
		this.stateDescription = stateDescription;
	}

	/**
	 * @return Devuelve technician.
	 */
	public TR811STechnician getTechnician() {
		return technician;
	}

	/**
	 * @param technician
	 *            El technician a establecer.
	 */
	public void setTechnician(TR811STechnician technician) {
		this.technician = technician;
	}
	/**
	 * @return Devuelve customerInventory.
	 */
	public TR811SInventory getCustomerInventory() {
		return customerInventory;
	}
	/**
	 * @param customerInventory El customerInventory a establecer.
	 */
	public void setCustomerInventory(TR811SInventory customerInventory) {
		this.customerInventory = customerInventory;
	}
	/**
	 * @return Devuelve deinstalledInventory.
	 */
	public TR811SInventory getDeinstalledInventory() {
		return deinstalledInventory;
	}
	/**
	 * @param deinstalledInventory El deinstalledInventory a establecer.
	 */
	public void setDeinstalledInventory(TR811SInventory deinstalledInventory) {
		this.deinstalledInventory = deinstalledInventory;
	}
	/**
	 * @return Devuelve families.
	 */
	public TR811SBreaks getBreaks() {
		return breaks;
	}
	/**
	 * @param families El families a establecer.
	 */
	public void setBreaks(TR811SBreaks breaks) {
		this.breaks = breaks;
	}
	/**
	 * @return Devuelve installedInventory.
	 */
	public TR811SInventory getInstalledInventory() {
		return installedInventory;
	}
	/**
	 * @param installedInventory El installedInventory a establecer.
	 */
	public void setInstalledInventory(TR811SInventory installedInventory) {
		this.installedInventory = installedInventory;
	}
	/**
	 * @return Devuelve notes.
	 */
	public TR811SNotes getNotes() {
		return notes;
	}
	/**
	 * @param notes El notes a establecer.
	 */
	public void setNotes(TR811SNotes notes) {
		this.notes = notes;
	}
	/**
	 * @return Devuelve material_flag.
	 */
	public String getMaterial_flag() {
		return material_flag;
	}
	/**
	 * @param material_flag El material_flag a establecer.
	 */
	public void setMaterial_flag(String material_flag) {
		this.material_flag = material_flag;
	}
}