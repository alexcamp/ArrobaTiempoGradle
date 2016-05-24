/*
 * Creado el 16/12/2013 DLGA
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package co.com.telefonica.atiempo.interfaces.atiempo;



import co.com.telefonica.atiempo.util.EqualityUtilities;

/**
 * @author Administrador
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class TR707S extends RequestHeaderAgendaSC{
	
	private String comercialProductIdentificationNumber;
	
	public boolean equals(Object arg0) {
		if (arg0 instanceof TR707S) {
			TR707S other = (TR707S) arg0;
			boolean s= super.equals(arg0)
		&& EqualityUtilities.equals(comercialProductIdentificationNumber, other.comercialProductIdentificationNumber);
		return s;	
		}
		return false;
	}		
	
	/**
	 * @return Devuelve comercialProductIdentificationNumber.
	 */
	public String getComercialProductIdentificationNumber() {
		return comercialProductIdentificationNumber;
	}
	/**
	 * @param comercialProductIdentificationNumber El comercialProductIdentificationNumber a establecer.
	 */
	public void setComercialProductIdentificationNumber(
			String comercialProductIdentificationNumber) {
		this.comercialProductIdentificationNumber = comercialProductIdentificationNumber;
	}
	
}
