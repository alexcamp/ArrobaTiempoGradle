/*
 * Creado el 12/11/2013
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
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class SpecialServicePS implements Serializable {
	 public long PS ;
	
		public boolean equals(Object arg0) {
			if (arg0 instanceof SpecialServicePS) {
				SpecialServicePS other = (SpecialServicePS) arg0;
				return super.equals(arg0)
					&& PS == other.PS
					;
			}
			return false;
		}
	 
	/**
 * @return Devuelve pS.
 */
public long getPS() {
	return PS;
}
/**
 * @param ps El pS a establecer.
 */
public void setPS(long ps) {
	PS = ps;
}

}
