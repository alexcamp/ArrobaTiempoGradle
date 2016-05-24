/*
 * Created on Aug 3, 2010
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
public class RequestHeaderAgendaSC implements Serializable {
	private String id;
	private String destination;
	private String source;
	private String interfaz;
	private String version;
	
	public int hashCode() {
		return id != null ? id.hashCode() : 0;
	}
	
	public boolean equals(Object object) {
		if (object instanceof ResponseHeader) {
			RequestHeaderAgendaSC other = (RequestHeaderAgendaSC) object;
			
			return EqualityUtilities.equals(id, other.id) 
				&& EqualityUtilities.equals(destination, other.destination)
				&& EqualityUtilities.equals(source, other.source)
				&& EqualityUtilities.equals(interfaz, other.interfaz)
				&& EqualityUtilities.equals(version, other.version);
		}
		
		return false;
	}
	
	/**
	 * @return Returns the destination.
	 */
	public String getDestination() {
		return destination;
	}
	/**
	 * @param destination The destination to set.
	 */
	public void setDestination(String destination) {
		this.destination = destination;
	}
	/**
	 * @return Returns the id.
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id The id to set.
	 */
	public void setId(String id) {
		this.id = id;
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
	 * @return Returns the source.
	 */
	public String getSource() {
		return source;
	}
	/**
	 * @param source The source to set.
	 */
	public void setSource(String source) {
		this.source = source;
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
