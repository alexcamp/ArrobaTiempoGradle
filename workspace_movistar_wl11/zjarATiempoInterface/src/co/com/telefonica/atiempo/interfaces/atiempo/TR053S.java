/*
 * Creado el 19/11/2010
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package co.com.telefonica.atiempo.interfaces.atiempo;

import co.com.telefonica.atiempo.util.EqualityUtilities;

/**
 * @author damartinezv
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class TR053S extends ResponseHeader{

	private String atiempoRequestNumber;
	private Long codigoStado;
	private String descripcion;
	private Long codigoError;
	private String descripcionError;

	
	public int hashCode(){
		return  super.hashCode();
	}
	
	public boolean equals(Object arg0) {
			if (arg0 instanceof TR053S) {
				TR053S other = (TR053S) arg0;
				return super.equals(arg0)
					&& EqualityUtilities.equals(atiempoRequestNumber, other.atiempoRequestNumber)
					&& EqualityUtilities.equals(codigoStado, other.codigoStado)
					&& EqualityUtilities.equals(descripcion, other.descripcion)
					&& EqualityUtilities.equals(codigoError, other.codigoError)
					&& EqualityUtilities.equals(descripcionError, other.descripcionError);
			}
			return false;
		}
	

	/**
	 * @return Devuelve atiempoRequestNumber.
	 */
	public String getAtiempoRequestNumber() {
		return atiempoRequestNumber;
	}
	/**
	 * @param atiempoRequestNumber El atiempoRequestNumber a establecer.
	 */
	public void setAtiempoRequestNumber(String atiempoRequestNumber) {
		this.atiempoRequestNumber = atiempoRequestNumber;
	}
	/**
	 * @return Devuelve codigoError.
	 */
	public Long getCodigoError() {
		return codigoError;
	}
	/**
	 * @param codigoError El codigoError a establecer.
	 */
	public void setCodigoError(Long codigoError) {
		this.codigoError = codigoError;
	}
	/**
	 * @return Devuelve codigoStado.
	 */
	public Long getCodigoStado() {
		return codigoStado;
	}
	/**
	 * @param codigoStado El codigoStado a establecer.
	 */
	public void setCodigoStado(Long codigoStado) {
		this.codigoStado = codigoStado;
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
	 * @return Devuelve descripcionError.
	 */
	public String getDescripcionError() {
		return descripcionError;
	}
	/**
	 * @param descripcionError El descripcionError a establecer.
	 */
	public void setDescripcionError(String descripcionError) {
		this.descripcionError = descripcionError;
	}
}
