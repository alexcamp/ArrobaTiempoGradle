package co.com.telefonica.atiempo.interfaces.atiempo;

import co.com.telefonica.atiempo.util.EqualityUtilities;

/**
 * @author cacano
 * 
 * Representa la tr-612-s para Configuración Mediación Móvil
 */
public class TR614S extends ResponseHeaderAgendaSC {
	private Long atiempoRequestNumber;
	private String estado;

	/**
	 * @param response
	 *            El response a establecer.
	 */
	public boolean equals(Object arg0) {
		if (arg0 instanceof TR614S) {
			TR614S other = (TR614S) arg0;
			return super.equals(arg0)
				&& EqualityUtilities.equals(atiempoRequestNumber, other.atiempoRequestNumber)
				&& EqualityUtilities.equals(estado, other.estado);
			}
		return false;
	}
	
	public Long getAtiempoRequestNumber() {
		return atiempoRequestNumber;
	}
	public void setAtiempoRequestNumber(Long atiempoRequestNumber) {
		this.atiempoRequestNumber = atiempoRequestNumber;
	}
	/**
	 * @return Devuelve descripcion.
	 */
	
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/**
	 * @return Devuelve ocCode.
	 */
	public String getEstado() {
		return estado;
	}
}