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
public class TR811SBreakPair implements Serializable {
	private String family;

	private String breakCode;

	private String observations;

	public int hashCode() {
		return family.hashCode();
	}

	public boolean equals(Object arg0) {
		if (arg0 instanceof TR811SBreakPair) {
			TR811SBreakPair other = (TR811SBreakPair) arg0;
			return super.equals(arg0)
					&& EqualityUtilities.equals(family, other.family)
					&& EqualityUtilities.equals(observations,
							other.observations)
					&& EqualityUtilities.equals(breakCode, other.breakCode);

		}
		return false;
	}

	/**
	 * @return Devuelve breakCode.
	 */
	public String getBreakCode() {
		return breakCode;
	}

	/**
	 * @param breakCode
	 *            El breakCode a establecer.
	 */
	public void setBreakCode(String breakCode) {
		this.breakCode = breakCode;
	}

	/**
	 * @return Devuelve family.
	 */
	public String getFamily() {
		return family;
	}

	/**
	 * @param family
	 *            El family a establecer.
	 */
	public void setFamily(String family) {
		this.family = family;
	}

	/**
	 * @return Devuelve observations.
	 */
	public String getObservations() {
		return observations;
	}

	/**
	 * @param observations
	 *            El observations a establecer.
	 */
	public void setObservations(String observations) {
		this.observations = observations;
	}
}