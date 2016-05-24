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
public class TR811SOtherData implements Serializable {
	private String closingRemarks;

	private String namePersonReceiving;

	private String certificatePersonReceiving;

	public int hashCode() {
		return closingRemarks.hashCode();
	}

	public boolean equals(Object arg0) {
		if (arg0 instanceof TR811SOtherData) {
			TR811SOtherData other = (TR811SOtherData) arg0;
			return super.equals(arg0)
					&& EqualityUtilities.equals(closingRemarks, other.closingRemarks)
&& EqualityUtilities.equals(namePersonReceiving, other.namePersonReceiving)
&& EqualityUtilities.equals(certificatePersonReceiving, other.certificatePersonReceiving);
		}
		return false;
	}
	
	/**
	 * @return Devuelve certificatePersonReceiving.
	 */
	public String getCertificatePersonReceiving() {
		return certificatePersonReceiving;
	}
	/**
	 * @param certificatePersonReceiving El certificatePersonReceiving a establecer.
	 */
	public void setCertificatePersonReceiving(String certificatePersonReceiving) {
		this.certificatePersonReceiving = certificatePersonReceiving;
	}
	/**
	 * @return Devuelve closingRemarks.
	 */
	public String getClosingRemarks() {
		return closingRemarks;
	}
	/**
	 * @param closingRemarks El closingRemarks a establecer.
	 */
	public void setClosingRemarks(String closingRemarks) {
		this.closingRemarks = closingRemarks;
	}
	/**
	 * @return Devuelve namePersonReceiving.
	 */
	public String getNamePersonReceiving() {
		return namePersonReceiving;
	}
	/**
	 * @param namePersonReceiving El namePersonReceiving a establecer.
	 */
	public void setNamePersonReceiving(String namePersonReceiving) {
		this.namePersonReceiving = namePersonReceiving;
	}
}