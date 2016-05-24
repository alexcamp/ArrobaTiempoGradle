/*
 * Created on Jul 06, 2011
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package co.com.telefonica.atiempo.interfaces.atiempo;

import co.com.telefonica.atiempo.util.EqualityUtilities;

/**
 * @author mfmendez
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TR020S extends ResponseHeaderAgendaSC{
	
	private String atiempoRequestNumber;
	private String documentoMaterial;
	private String fechaDocumento;
	private String fechaEjecucionSAP;
	private String horaEjecucionInterfaz;
	
	public int hashCode(){
		return super.hashCode();
	}
	
	public boolean equals(Object arg0){
		if(arg0 instanceof TR020S){
			TR020S other = (TR020S)arg0;
			return super.equals(arg0)
			&& EqualityUtilities.equals(atiempoRequestNumber,other.getAtiempoRequestNumber())
			&& EqualityUtilities.equals(documentoMaterial,other.getDocumentoMaterial())
			&& EqualityUtilities.equals(fechaDocumento,other.getFechaDocumento())	
			&& EqualityUtilities.equals(fechaEjecucionSAP,other.getFechaEjecucionSAP())
			&& EqualityUtilities.equals(horaEjecucionInterfaz,other.getHoraEjecucionInterfaz());
		}
		return false;
	}
	
	/**
	 * @return Returns the atiempoRequestNumber.
	 */
	public String getAtiempoRequestNumber() {
		return atiempoRequestNumber;
	}
	
	public String getDocumentoMaterial() {
		return documentoMaterial;
	}
	
	public String getFechaDocumento() {
		return fechaDocumento;
	}
	
	public String getFechaEjecucionSAP() {
		return fechaEjecucionSAP;
	}
	
	public String getHoraEjecucionInterfaz() {
		return horaEjecucionInterfaz;
	}
	/**
	 * @param atiempoRequestNumber The atiempoRequestNumber to set.
	 */
	
	public void setAtiempoRequestNumber(String atiempoRequestNumber) {
		this.atiempoRequestNumber = atiempoRequestNumber;
	}
	
	public void setDocumentoMaterial(String documentoMaterial) {
		this.documentoMaterial = documentoMaterial;
	}
	
	public void setFechaDocumento(String fechaDocumento) {
		this.fechaDocumento = fechaDocumento;
	}
	
	public void setFechaEjecucionSAP(String fechaEjecucionSAP) {
		this.fechaEjecucionSAP = fechaEjecucionSAP;
	}
	
	public void setHoraEjecucionInterfaz(String horaEjecucionInterfaz) {
		this.horaEjecucionInterfaz = horaEjecucionInterfaz;
	}
	
}
