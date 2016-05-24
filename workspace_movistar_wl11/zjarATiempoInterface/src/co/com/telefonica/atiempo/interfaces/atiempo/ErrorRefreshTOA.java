/*
 * Creado el 26/08/2015
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
public class ErrorRefreshTOA implements Serializable {

	private String errorcodemessage;
	
	public boolean equals(Object arg0) {
		if (arg0 instanceof Equipment) {
			ErrorRefreshTOA other = (ErrorRefreshTOA) arg0;
			return EqualityUtilities.equals(errorcodemessage, other.errorcodemessage);
		}
		return false;
	}
	
	/**
	 * @return Devuelve errorcodemessage.
	 */
	public String getErrorcodemessage() {
		return errorcodemessage;
	}
	/**
	 * @param errorcodemessage El errorcodemessage a establecer.
	 */
	public void setErrorcodemessage(String errorcodemessage) {
		this.errorcodemessage = errorcodemessage;
	}
}
