/*
 * Created on Apr 21, 2008
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package co.com.telefonica.atiempo.interfaces.atiempo;

import co.com.telefonica.atiempo.util.EqualityUtilities;

/**
 * @author 806713
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class IT010E extends ResponseHeader2{
	
	private Long breakdownNumber;	
	private String activityStartDate;
	private String activityEndDate;	
	private String activityName;	
	private String rol;	
	private String user;	
	private String activityObservation;
	
	/**
	 * @return Returns the activityEndDate.
	 */
	public String getActivityEndDate() {
		return activityEndDate;
	}
	/**
	 * @param activityEndDate The activityEndDate to set.
	 */
	public void setActivityEndDate(String activityEndDate) {
		this.activityEndDate = activityEndDate;
	}
	/**
	 * @return Returns the activityName.
	 */
	public String getActivityName() {
		return activityName;
	}
	/**
	 * @param activityName The activityName to set.
	 */
	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}
	/**
	 * @return Returns the activityObservation.
	 */
	public String getActivityObservation() {
		return activityObservation;
	}
	/**
	 * @param activityObservation The activityObservation to set.
	 */
	public void setActivityObservation(String activityObservation) {
		this.activityObservation = activityObservation;
	}
	/**
	 * @return Returns the activityStartDate.
	 */
	public String getActivityStartDate() {
		return activityStartDate;
	}
	/**
	 * @param activityStartDate The activityStartDate to set.
	 */
	public void setActivityStartDate(String activityStartDate) {
		this.activityStartDate = activityStartDate;
	}
	/**
	 * @return Returns the breakdownNumber.
	 */
	public Long getBreakdownNumber() {
		return breakdownNumber;
	}
	/**
	 * @param breakdownNumber The breakdownNumber to set.
	 */
	public void setBreakdownNumber(Long breakdownNumber) {
		this.breakdownNumber = breakdownNumber;
	}
	/**
	 * @return Returns the rol.
	 */
	public String getRol() {
		return rol;
	}
	/**
	 * @param rol The rol to set.
	 */
	public void setRol(String rol) {
		this.rol = rol;
	}
	/**
	 * @return Returns the user.
	 */
	public String getUser() {
		return user;
	}
	/**
	 * @param user The user to set.
	 */
	public void setUser(String user) {
		this.user = user;
	}
	public int hashCode(){
		return super.hashCode();
	}
	
	public boolean equals(Object arg0) {
			if (arg0 instanceof IT010E) {
				IT010E other = (IT010E) arg0;

				return super.equals(arg0)
				&& EqualityUtilities.equals(breakdownNumber,other.breakdownNumber)
				&& EqualityUtilities.equals(activityStartDate,other.activityStartDate)
				&& EqualityUtilities.equals(activityEndDate,other.activityEndDate)
				&& EqualityUtilities.equals(activityName,other.activityName)
				&& EqualityUtilities.equals(rol,other.rol)
				&& EqualityUtilities.equals(user,other.user)
				&& EqualityUtilities.equals(activityObservation,other.activityObservation)
				
				;
			}
			return false;
		}
}
