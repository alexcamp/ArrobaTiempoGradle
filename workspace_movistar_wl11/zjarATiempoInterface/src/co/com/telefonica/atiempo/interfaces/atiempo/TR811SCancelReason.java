/*
 * Creado el 29/07/2015
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package co.com.telefonica.atiempo.interfaces.atiempo;

import java.io.Serializable;

import java.util.Collection;

import co.com.telefonica.atiempo.util.EqualityUtilities;

/**
 * @author Administrador
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class TR811SCancelReason implements Serializable {
	private Collection reason;
	
	public int hashCode() {
		return super.hashCode();
	}
	
	public boolean equals(Object arg0) {
		if (arg0 instanceof TR811SCancelReason) {
			TR811SCancelReason other = (TR811SCancelReason) arg0;
			return super.equals(arg0)
				&& EqualityUtilities.equals(reason, other.reason);
			}
		return false;
	}
	

	/**
	 * @return Devuelve reason.
	 */
	public Collection getReason() {
		return reason;
	}
	/**
	 * @param reason El reason a establecer.
	 */
	public void setReason(Collection reason) {
		this.reason = reason;
	}
}
