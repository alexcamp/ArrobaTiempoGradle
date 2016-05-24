/*
 * Created on Jun 16, 2011
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package co.com.telefonica.atiempo.interfaces.atiempo;

import java.io.Serializable;

import co.com.telefonica.atiempo.util.EqualityUtilities;

/**
 * @author damartinezv
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TR703SOption implements Serializable {
	private String idOption;
	private String initialDate;
	private String finalDate;
	private String technicalName;
	private String cost;
	
	public int hashCode() {
		return idOption.hashCode();
	}
	
	public boolean equals(Object arg0) {
		if (arg0 instanceof TR703SOption) {
			TR703SOption other = (TR703SOption) arg0;
			return super.equals(arg0)
				&& EqualityUtilities.equals(idOption, other.getIdOption())
				&& EqualityUtilities.equals(initialDate, other.getInitialDate())
				&& EqualityUtilities.equals(finalDate, other.getFinalDate())
				&& EqualityUtilities.equals(technicalName, other.getTechnicalName())
				&& EqualityUtilities.equals(initialDate, other.getCost());
			}
		return false;
	}

	public String getIdOption() {
		return idOption;
	}
	public void setIdOption(String idOption) {
		this.idOption = idOption;
	}
	public String getInitialDate() {
		return initialDate;
	}
	public void setInitialDate(String initialDate) {
		this.initialDate = initialDate;
	}
	
	/**
	 * @return Returns the cost.
	 */
	public String getCost() {
		return cost;
	}
	/**
	 * @param cost The cost to set.
	 */
	public void setCost(String cost) {
		this.cost = cost;
	}
	/**
	 * @return Returns the finalDate.
	 */
	public String getFinalDate() {
		return finalDate;
	}
	/**
	 * @param finalDate The finalDate to set.
	 */
	public void setFinalDate(String finalDate) {
		this.finalDate = finalDate;
	}
	/**
	 * @return Returns the technicalName.
	 */
	public String getTechnicalName() {
		return technicalName;
	}
	/**
	 * @param technicalName The technicalName to set.
	 */
	public void setTechnicalName(String technicalName) {
		this.technicalName = technicalName;
	}
}
