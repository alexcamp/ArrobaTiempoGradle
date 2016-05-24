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
 * TODO Para cambiar la plantilla de este comentario generado, vaya a Ventana -
 * Preferencias - Java - Estilo de código - Plantillas de código
 */
public class TR811SBreaks implements Serializable {
	private Collection breakPair;

	public int hashCode() {
		return super.hashCode();
	}

	public boolean equals(Object arg0) {
		if (arg0 instanceof TR811SNotes) {
			TR811SBreaks other = (TR811SBreaks) arg0;
			return super.equals(arg0)
					&& EqualityUtilities.equals(breakPair, other.breakPair);
		}
		return false;
	}




	/**
	 * @return Devuelve breakPair.
	 */
	public Collection getBreakPairs() {
		return breakPair;
	}
	/**
	 * @param breakPair El breakPair a establecer.
	 */
	public void setBreakPairs(Collection breakPair) {
		this.breakPair = breakPair;
	}
}