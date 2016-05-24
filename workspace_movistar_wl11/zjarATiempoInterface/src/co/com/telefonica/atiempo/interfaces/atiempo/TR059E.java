/*
 * Created on Dec 22, 2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package co.com.telefonica.atiempo.interfaces.atiempo;

import java.util.Collection;

/**
 * @author mfmendez
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TR059E extends RequestHeaderAgendaSC{

	private String  nit;
	private String telefonoPadre;
	private String telefonoAdicional;
	private String extension;
	private Integer limiteUsuarios;
	private String nombreCliente;
	private String apellidoCliente;
	private Collection productosServicio;
	private Collection localidades;
	private long atisRequestNumber;
	private long atiempoRequestNumber;	
	/**
	 * @return Returns the apellidoCliente.
	 */
	public String getApellidoCliente() {
		return apellidoCliente;
	}
	/**
	 * @param apellidoCliente The apellidoCliente to set.
	 */
	public void setApellidoCliente(String apellidoCliente) {
		this.apellidoCliente = apellidoCliente;
	}
	/**
	 * @return Returns the extension.
	 */
	public String getExtension() {
		return extension;
	}
	/**
	 * @param extension The extension to set.
	 */
	public void setExtension(String extension) {
		this.extension = extension;
	}
	/**
	 * @return Returns the limiteUsuarios.
	 */
	public Integer getLimiteUsuarios() {
		return limiteUsuarios;
	}
	/**
	 * @param limiteUsuarios The limiteUsuarios to set.
	 */
	public void setLimiteUsuarios(Integer limiteUsuarios) {
		this.limiteUsuarios = limiteUsuarios;
	}
	/**
	 * @return Returns the localidades.
	 */
	public Collection getLocalidades() {
		return localidades;
	}
	/**
	 * @param localidades The localidades to set.
	 */
	public void setLocalidades(Collection localidades) {
		this.localidades = localidades;
	}
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
	 * @return Returns the nombreCliente.
	 */
	public String getNombreCliente() {
		return nombreCliente;
	}
	/**
	 * @param nombreCliente The nombreCliente to set.
	 */
	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}
	/**
	 * @return Returns the productosServicio.
	 */
	public Collection getProductosServicio() {
		return productosServicio;
	}
	/**
	 * @param productosServicio The productosServicio to set.
	 */
	public void setProductosServicio(Collection productosServicio) {
		this.productosServicio = productosServicio;
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

	/**
	 * @return Returns the atisRequestNumber.
	 */
	public long getAtisRequestNumber() {
		return atisRequestNumber;
	}
	/**
	 * @param atisRequestNumber The atisRequestNumber to set.
	 */
	public void setAtisRequestNumber(long atisRequestNumber) {
		this.atisRequestNumber = atisRequestNumber;
	}
	
	/**
	 * @return Returns the atiempoRequestNumber.
	 */
	public long getAtiempoRequestNumber() {
		return atiempoRequestNumber;
	}
	/**
	 * @param atiempoRequestNumber The atiempoRequestNumber to set.
	 */
	public void setAtiempoRequestNumber(long atiempoRequestNumber) {
		this.atiempoRequestNumber = atiempoRequestNumber;
	}
}
