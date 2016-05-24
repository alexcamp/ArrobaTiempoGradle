/*
 * Created on Jul 6, 2010
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
public class TR044EEquipment implements Serializable {
	private long codigoPS;
	private Long codigoCaracteristica;
	private Long codigoPaquete;
	private String serial;
		
	/**
	 * @return Returns the codigoCaracteristica.
	 */
	public Long getCodigoCaracteristica() {
		return codigoCaracteristica;
	}
	/**
	 * @param codigoCaracteristica The codigoCaracteristica to set.
	 */
	public void setCodigoCaracteristica(Long codigoCaracteristica) {
		this.codigoCaracteristica = codigoCaracteristica;
	}
	/**
	 * @return Returns the codigoPaquete.
	 */
	public Long getCodigoPaquete() {
		return codigoPaquete;
	}
	/**
	 * @param codigoPaquete The codigoPaquete to set.
	 */
	public void setCodigoPaquete(Long codigoPaquete) {
		this.codigoPaquete = codigoPaquete;
	}
	public String getSerial() {
		return serial;
	}
	public void setSerial(String serial) {
		this.serial = serial;
	}
	public long getCodigoPS() {
		return codigoPS;
	}
	public void setCodigoPS(long codigoPS) {
		this.codigoPS = codigoPS;
	}
	
	public int hashCode() {
		return (int)codigoPS;
	}
	
	public boolean equals(Object arg0) {
		if (arg0 instanceof TR044EEquipment) {
			TR044EEquipment other = (TR044EEquipment) arg0;
			return codigoPS == other.codigoPS
			&& EqualityUtilities.equals(serial, other.serial);
		}
		return false;
	}
}
