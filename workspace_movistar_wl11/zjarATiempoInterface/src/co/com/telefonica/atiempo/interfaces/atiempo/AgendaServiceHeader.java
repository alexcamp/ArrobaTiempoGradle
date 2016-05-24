/*
 * Created on Feb 22, 2011
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package co.com.telefonica.atiempo.interfaces.atiempo;

import java.io.Serializable;

import co.com.telefonica.atiempo.util.EqualityUtilities;

/**
 * @author idrincon
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class AgendaServiceHeader implements Serializable {
	
	private String destino;
	private String origen;
	private String interfaz;
	private String version;
	
	public int hashCode(){
		return destino != null ? destino.hashCode() : 0;
	}
	
	public boolean equals(Object object){
		if(object instanceof AgendaServiceHeader){
			AgendaServiceHeader other = (AgendaServiceHeader) object;
			return EqualityUtilities.equals( destino, other.destino)
				&& EqualityUtilities.equals( interfaz, other.interfaz)
				&& EqualityUtilities.equals( origen, other.origen)
				&& EqualityUtilities.equals( version, other.version);
		}else{
			return false;
		}
	}
	
	/**
	 * @return Returns the destino.
	 */
	public String getDestino() {
		return destino;
	}
	/**
	 * @param destino The destino to set.
	 */
	public void setDestino(String destino) {
		this.destino = destino;
	}
	/**
	 * @return Returns the interfaz.
	 */
	public String getInterfaz() {
		return interfaz;
	}
	/**
	 * @param interfaz The interfaz to set.
	 */
	public void setInterfaz(String interfaz) {
		this.interfaz = interfaz;
	}
	/**
	 * @return Returns the origen.
	 */
	public String getOrigen() {
		return origen;
	}
	/**
	 * @param origen The origen to set.
	 */
	public void setOrigen(String origen) {
		this.origen = origen;
	}
	/**
	 * @return Returns the version.
	 */
	public String getVersion() {
		return version;
	}
	/**
	 * @param version The version to set.
	 */
	public void setVersion(String version) {
		this.version = version;
	}
}
