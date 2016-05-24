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
public class TR053E extends RequestHeader{

	private String pcid;
	private String atiempoRequestNumber;
	
	public int hashCode(){
		return  super.hashCode();
	}
	
	public boolean equals(Object arg0) {
			if (arg0 instanceof TR053E) {
				TR053E other = (TR053E) arg0;
				return super.equals(arg0)
					&& EqualityUtilities.equals(pcid, other.pcid)
					&& EqualityUtilities.equals(atiempoRequestNumber, other.atiempoRequestNumber);
				 	}
			return false;
		}
	

	/**
	 * @return Devuelve atisRequestNumber.
	 */
	public String getAtiempoRequestNumber() {
		return atiempoRequestNumber;
	}
	/**
	 * @param atisRequestNumber El atisRequestNumber a establecer.
	 */
	public void setAtiempoRequestNumber(String atisRequestNumber) {
		this.atiempoRequestNumber = atisRequestNumber;
		}
	/**
	 * @return Devuelve pcid.
	 */
	public String getPcid() {
		return pcid;
	}
	/**
	 * @param pcid El pcid a establecer.
	 */
	public void setPcid(String pcid) {
		this.pcid = pcid;
	}
}
