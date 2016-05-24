/*
 * Created on Jul 30, 2010
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
public class TR701E extends RequestHeaderAgendaSC{
	private String idSystemOrigin;
	private String idSchedule;
	private String scheduleDate;
	private String codeScheduleType;
	private String actionName;
	private String codeAction;
	private String severity;
	private String urgency;
	private String summary;
	private String descriptionAction;
	private String affectedArea;
	private TR701ECustomer customer;
	private TR701EProduct product;
	private TR701ETechnicalData technicalData;
	private TR701EDateData dateData;
	private TR701EAdressData adressData;
	private TR701ECoordinatedActions coordinatedActions;
	private TR701ENotes notes;
	private TR701EContactData contactData;
	private TR701EAccessTime accessTime;
	private TR701EMassiveBreakdowns massiveBreakdowns;
	private Collection productsService;
	private Collection equipments;
	private String projectCode;
	private String transactionType;
	private String breakdowntype;
	
	/**
	 * @return Returns the transactionType.
	 */
	public String getTransactionType() {
		return transactionType;
	}
	/**
	 * @param transactionType The transactionType to set.
	 */
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	/**
	 * @return Returns the projectCode.
	 */
	public String getProjectCode() {
		return projectCode;
	}
	/**
	 * @param projectCode The projectCode to set.
	 */
	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}
	public int hashCode() {
		return idSchedule.hashCode();
	}
	public boolean equals(Object arg0) {
		if (arg0 instanceof TR701E) {
			TR701E other = (TR701E) arg0;
			return super.equals(arg0)
				&& EqualityUtilities.equals(idSystemOrigin, other.idSystemOrigin)
				&& EqualityUtilities.equals(idSchedule, other.idSchedule)
				&& EqualityUtilities.equals(scheduleDate, other.scheduleDate)
				&& EqualityUtilities.equals(codeScheduleType, other.codeScheduleType)
				&& EqualityUtilities.equals(actionName, other.actionName)
				&& EqualityUtilities.equals(codeAction, other.codeAction)
				&& EqualityUtilities.equals(severity, other.severity)
				&& EqualityUtilities.equals(urgency, other.urgency)
				&& EqualityUtilities.equals(summary, other.summary)
				&& EqualityUtilities.equals(descriptionAction, other.descriptionAction)
				&& EqualityUtilities.equals(affectedArea, other.affectedArea)
				&& EqualityUtilities.equals(customer, other.customer)
				&& EqualityUtilities.equals(product, other.product)
				&& EqualityUtilities.equals(technicalData, other.technicalData)
				&& EqualityUtilities.equals(dateData, other.dateData)
				&& EqualityUtilities.equals(adressData, other.adressData)
				&& EqualityUtilities.equals(coordinatedActions, other.coordinatedActions)
				&& EqualityUtilities.equals(notes, other.notes)
				&& EqualityUtilities.equals(contactData, other.contactData)
				&& EqualityUtilities.equals(accessTime, other.accessTime)
				&& EqualityUtilities.equals(massiveBreakdowns, other.massiveBreakdowns)
				&& EqualityUtilities.equals(productsService, other.productsService)
				&& EqualityUtilities.equals(equipments, other.equipments)
				&& EqualityUtilities.equals(breakdowntype, other.breakdowntype);
			}
		return false;
	}
	
	
	
	/**
	 * @return Devuelve equipments.
	 */
	public Collection getEquipments() {
		return equipments;
	}
	/**
	 * @param equipments El equipments a establecer.
	 */
	public void setEquipments(Collection equipments) {
		this.equipments = equipments;
	}
	public TR701EAccessTime getAccessTime() {
		return accessTime;
	}
	public void setAccessTime(TR701EAccessTime accessTime) {
		this.accessTime = accessTime;
	}
	public String getActionName() {
		return actionName;
	}
	public void setActionName(String actionName) {
		this.actionName = actionName;
	}
	public TR701EAdressData getAdressData() {
		return adressData;
	}
	public void setAdressData(TR701EAdressData adressData) {
		this.adressData = adressData;
	}
	public String getAffectedArea() {
		return affectedArea;
	}
	public void setAffectedArea(String affectedArea) {
		this.affectedArea = affectedArea;
	}
	public String getCodeAction() {
		return codeAction;
	}
	public void setCodeAction(String codeAction) {
		this.codeAction = codeAction;
	}
	public String getCodeScheduleType() {
		return codeScheduleType;
	}
	public void setCodeScheduleType(String codeScheduleType) {
		this.codeScheduleType = codeScheduleType;
	}
	public TR701EContactData getContactData() {
		return contactData;
	}
	public void setContactData(TR701EContactData contactData) {
		this.contactData = contactData;
	}
	public TR701ECoordinatedActions getCoordinatedActions() {
		return coordinatedActions;
	}
	public void setCoordinatedActions(
			TR701ECoordinatedActions coordinatedActions) {
		this.coordinatedActions = coordinatedActions;
	}
	public TR701ECustomer getCustomer() {
		return customer;
	}
	public void setCustomer(TR701ECustomer customer) {
		this.customer = customer;
	}
	public TR701EDateData getDateData() {
		return dateData;
	}
	public void setDateData(TR701EDateData dateData) {
		this.dateData = dateData;
	}
	public String getDescriptionAction() {
		return descriptionAction;
	}
	public void setDescriptionAction(String descriptionAction) {
		this.descriptionAction = descriptionAction;
	}
	public String getIdSchedule() {
		return idSchedule;
	}
	public void setIdSchedule(String idSchedule) {
		this.idSchedule = idSchedule;
	}
	public String getIdSystemOrigin() {
		return idSystemOrigin;
	}
	public void setIdSystemOrigin(String idSystemOrigin) {
		this.idSystemOrigin = idSystemOrigin;
	}
	public TR701EMassiveBreakdowns getMassiveBreakdowns() {
		return massiveBreakdowns;
	}
	public void setMassiveBreakdowns(TR701EMassiveBreakdowns massiveBreakdowns) {
		this.massiveBreakdowns = massiveBreakdowns;
	}
	public TR701ENotes getNotes() {
		return notes;
	}
	public void setNotes(TR701ENotes notes) {
		this.notes = notes;
	}
	public TR701EProduct getProduct() {
		return product;
	}
	public void setProduct(TR701EProduct product) {
		this.product = product;
	}
	public String getScheduleDate() {
		return scheduleDate;
	}
	public void setScheduleDate(String scheduleDate) {
		this.scheduleDate = scheduleDate;
	}
	public String getSeverity() {
		return severity;
	}
	public void setSeverity(String severity) {
		this.severity = severity;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public TR701ETechnicalData getTechnicalData() {
		return technicalData;
	}
	public void setTechnicalData(TR701ETechnicalData technicalData) {
		this.technicalData = technicalData;
	}
	public String getUrgency() {
		return urgency;
	}
	public void setUrgency(String urgency) {
		this.urgency = urgency;
	}
	public Collection getProductsService() {
		return productsService;
	}
	public void setProductsService(Collection productsService) {
		this.productsService = productsService;
	}
	/**
	 * @return Devuelve breakdowntype.
	 */
	public String getBreakdowntype() {
		return breakdowntype;
	}
	/**
	 * @param breakdowntype El breakdowntype a establecer.
	 */
	public void setBreakdowntype(String breakdowntype) {
		this.breakdowntype = breakdowntype;
	}
}
