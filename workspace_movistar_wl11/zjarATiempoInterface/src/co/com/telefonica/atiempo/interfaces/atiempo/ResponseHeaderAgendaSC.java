/*
 * Created on Aug 25, 2010
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
public class ResponseHeaderAgendaSC implements Serializable {
	private String id;
	private String error;
	private String errorMessage;
	private String destination;
	private String source;
	private String interfaz;
	private String version;
	
	public int hashCode() {
		return id != null ? id.hashCode() : 0;
	}
	
	public boolean equals(Object object) {
		if (object instanceof ResponseHeaderAgendaSC) {
			ResponseHeaderAgendaSC other = (ResponseHeaderAgendaSC) object;
			
			return EqualityUtilities.equals(id, other.id) 
				&&	EqualityUtilities.equals(error, other.error)
				&&	EqualityUtilities.equals(errorMessage, other.errorMessage)
				&&	EqualityUtilities.equals(destination, other.destination)
				&&	EqualityUtilities.equals(source, other.source)
				&&	EqualityUtilities.equals(interfaz, other.interfaz)
				&&	EqualityUtilities.equals(version, other.version);
		}
		
		return false;
	}
	
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getInterfaz() {
		return interfaz;
	}
	public void setInterfaz(String interfaz) {
		this.interfaz = interfaz;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
}
