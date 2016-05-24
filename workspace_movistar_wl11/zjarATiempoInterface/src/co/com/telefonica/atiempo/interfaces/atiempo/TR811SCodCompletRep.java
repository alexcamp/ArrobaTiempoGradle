/*
 * Creado el 29/07/2015
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package co.com.telefonica.atiempo.interfaces.atiempo;

import java.io.Serializable;

import co.com.telefonica.atiempo.util.EqualityUtilities;

/**
 * @author Administrador
 * 
 * TODO Para cambiar la plantilla de este comentario generado, vaya a Ventana -
 * Preferencias - Java - Estilo de código - Plantillas de código
 */
public class TR811SCodCompletRep implements Serializable {
	private String repairClosCode;

	private String locationDamage;

	private String causeDamage;

	private String correctiveAction;

	private String suspendedCode;

	private String unrealizedCode;

	public int hashCode() {
		return repairClosCode.hashCode();
	}

	public boolean equals(Object arg0) {
		if (arg0 instanceof TR811SCodCompletRep) {
			TR811SCodCompletRep other = (TR811SCodCompletRep) arg0;
			return super.equals(arg0)
					&& EqualityUtilities.equals(repairClosCode,
							other.repairClosCode)
					&& EqualityUtilities.equals(locationDamage,
							other.locationDamage)
					&& EqualityUtilities.equals(causeDamage, other.causeDamage)
					&& EqualityUtilities.equals(correctiveAction,
							other.correctiveAction)
					&& EqualityUtilities.equals(suspendedCode,
							other.suspendedCode)
					&& EqualityUtilities.equals(unrealizedCode,
							other.unrealizedCode);
		}
		return false;
	}
	
	/**
	 * @return Devuelve causeDamage.
	 */
	public String getCauseDamage() {
		return causeDamage;
	}
	/**
	 * @param causeDamage El causeDamage a establecer.
	 */
	public void setCauseDamage(String causeDamage) {
		this.causeDamage = causeDamage;
	}
	/**
	 * @return Devuelve correctiveAction.
	 */
	public String getCorrectiveAction() {
		return correctiveAction;
	}
	/**
	 * @param correctiveAction El correctiveAction a establecer.
	 */
	public void setCorrectiveAction(String correctiveAction) {
		this.correctiveAction = correctiveAction;
	}

	/**
	 * @return Devuelve locationDamage.
	 */
	public String getLocationDamage() {
		return locationDamage;
	}
	/**
	 * @param locationDamage El locationDamage a establecer.
	 */
	public void setLocationDamage(String locationDamage) {
		this.locationDamage = locationDamage;
	}
	/**
	 * @return Devuelve repairClosCode.
	 */
	public String getRepairClosCode() {
		return repairClosCode;
	}
	/**
	 * @param repairClosCode El repairClosCode a establecer.
	 */
	public void setRepairClosCode(String repairClosCode) {
		this.repairClosCode = repairClosCode;
	}
	/**
	 * @return Devuelve suspendedCode.
	 */
	public String getSuspendedCode() {
		return suspendedCode;
	}
	/**
	 * @param suspendedCode El suspendedCode a establecer.
	 */
	public void setSuspendedCode(String suspendedCode) {
		this.suspendedCode = suspendedCode;
	}
	/**
	 * @return Devuelve unrealizedCode.
	 */
	public String getUnrealizedCode() {
		return unrealizedCode;
	}
	/**
	 * @param unrealizedCode El unrealizedCode a establecer.
	 */
	public void setUnrealizedCode(String unrealizedCode) {
		this.unrealizedCode = unrealizedCode;
	}
}