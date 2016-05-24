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
public class TR811SNoCompletInst implements Serializable {
	private String code;

	private String reason;

	private String observation;

	public int hashCode() {
		return code.hashCode();
	}

	public boolean equals(Object arg0) {
		if (arg0 instanceof TR811SNoCompletInst) {
			TR811SNoCompletInst other = (TR811SNoCompletInst) arg0;
			return super.equals(arg0)
					&& EqualityUtilities.equals(code, other.code)
					&& EqualityUtilities.equals(reason, other.reason)
					&& EqualityUtilities.equals(observation, other.observation);
		}
		return false;
	}

	/**
	 * @return Devuelve code.
	 */
	public String getCode() {
		return code;
	}
	/**
	 * @param code El code a establecer.
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * @return Devuelve observation.
	 */
	public String getObservation() {
		return observation;
	}
	/**
	 * @param observation El observation a establecer.
	 */
	public void setObservation(String observation) {
		this.observation = observation;
	}
	/**
	 * @return Devuelve reason.
	 */
	public String getReason() {
		return reason;
	}
	/**
	 * @param reason El reason a establecer.
	 */
	public void setReason(String reason) {
		this.reason = reason;
	}
}