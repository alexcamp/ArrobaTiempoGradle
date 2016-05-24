/*
 * Created on Jun 1, 2011
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package co.com.telefonica.atiempo.interfaces.atiempo;

import co.com.telefonica.atiempo.util.EqualityUtilities;

/**
 * @author joeroa
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TR613S extends ResponseHeaderAgendaSC {
	private Long atiempoRequestNumber;
	private Long atisRequestNumber;
	private String codigo;
	private String mensaje;
	private String descripcion;
	
	public int hashCode() {
		return atiempoRequestNumber.hashCode();
	}
	
	public boolean equals(Object arg0) {
		if (arg0 instanceof TR613S) {
			TR613S other = (TR613S) arg0;
			return super.equals(arg0)
				&& EqualityUtilities.equals(atiempoRequestNumber, other.atiempoRequestNumber)
				&& EqualityUtilities.equals(atisRequestNumber, other.atisRequestNumber)
				&& EqualityUtilities.equals(codigo, other.codigo)
				&& EqualityUtilities.equals(mensaje, other.mensaje)
				&& EqualityUtilities.equals(descripcion, other.descripcion);
			}
		return false;
	}
	
	public Long getAtiempoRequestNumber() {
		return atiempoRequestNumber;
	}
	public void setAtiempoRequestNumber(Long atiempoRequestNumber) {
		this.atiempoRequestNumber = atiempoRequestNumber;
	}
	public Long getAtisRequestNumber() {
		return atisRequestNumber;
	}
	public void setAtisRequestNumber(Long atisRequestNumber) {
		this.atisRequestNumber = atisRequestNumber;
	}
	/**
	 * @return Devuelve codigo.
	 */
	public String getCodigo() {
		return codigo;
	}
	/**
	 * @param codigo El codigo a establecer.
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	/**
	 * @return Devuelve descripcion.
	 */
	public String getDescripcion() {
		return descripcion;
	}
	/**
	 * @param descripcion El descripcion a establecer.
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	/**
	 * @return Devuelve mensaje.
	 */
	public String getMensaje() {
		return mensaje;
	}
	/**
	 * @param mensaje El mensaje a establecer.
	 */
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
}
