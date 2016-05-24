/*
 * Created on May 02, 2011
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package co.com.telefonica.atiempo.interfaces.atiempo;

/**
 * @author mfmendez
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TR055S extends ResponseHeaderAgendaSC{
	
	private String nit;
	private String telefonoPadre;
	private String telefonoAdicional;
	/**
	 * @return Returns the nit.
	 */
	public String getNit() {
		return nit;
	}
	/**
	 * @param nit The nit to set.
	 */
	public void setNit(String nit) {
		this.nit = nit;
	}
	/**
	 * @return Returns the telefonoAdicional.
	 */
	public String getTelefonoAdicional() {
		return telefonoAdicional;
	}
	/**
	 * @param telefonoAdicional The telefonoAdicional to set.
	 */
	public void setTelefonoAdicional(String telefonoAdicional) {
		this.telefonoAdicional = telefonoAdicional;
	}
	/**
	 * @return Returns the telefonoPadre.
	 */
	public String getTelefonoPadre() {
		return telefonoPadre;
	}
	/**
	 * @param telefonoPadre The telefonoPadre to set.
	 */
	public void setTelefonoPadre(String telefonoPadre) {
		this.telefonoPadre = telefonoPadre;
	}
}
