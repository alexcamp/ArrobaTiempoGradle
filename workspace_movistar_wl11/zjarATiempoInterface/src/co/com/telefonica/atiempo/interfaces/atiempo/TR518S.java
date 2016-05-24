/*
 * Creado el 24/12/2013
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package co.com.telefonica.atiempo.interfaces.atiempo;

import java.util.Collection;

import co.com.telefonica.atiempo.util.EqualityUtilities;

/**
 * @author Administrador
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class TR518S extends ResponseHeader2 {
	
	private long atiempoRequestNumber;
	private long odsNumber;
	private Collection specialServicesType;	
	private String specialService;
	private long actionResultNumber;
	private String actionResultDescription;
	
	public int hashCode(){
		return super.hashCode();
	}
	public boolean equals(Object arg0) {
			if (arg0 instanceof TR518S) {
				TR518S other = (TR518S) arg0;
				return super.equals(arg0)
					&& (atiempoRequestNumber == other.atiempoRequestNumber)
					&& (actionResultNumber == other.actionResultNumber)
					&& (odsNumber == other.odsNumber)
						&& (specialServicesType == other.specialServicesType)
					&& EqualityUtilities.equals(specialService,other.specialService)
					&& EqualityUtilities.equals(actionResultDescription,other.actionResultDescription)

					;

			}
			return false;
		}
	
	/**
	 * @return Devuelve actionResultDescription.
	 */
	public String getActionResultDescription() {
		return actionResultDescription;
	}
	/**
	 * @param actionResultDescription El actionResultDescription a establecer.
	 */
	public void setActionResultDescription(String actionResultDescription) {
		this.actionResultDescription = actionResultDescription;
	}
	/**
	 * @return Devuelve actionResultNumber.
	 */
	public long getActionResultNumber() {
		return actionResultNumber;
	}
	/**
	 * @param actionResultNumber El actionResultNumber a establecer.
	 */
	public void setActionResultNumber(long actionResultNumber) {
		this.actionResultNumber = actionResultNumber;
	}
	/**
	 * @return Devuelve atiempoRequestNumber.
	 */
	public long getAtiempoRequestNumber() {
		return atiempoRequestNumber;
	}
	/**
	 * @param atiempoRequestNumber El atiempoRequestNumber a establecer.
	 */
	public void setAtiempoRequestNumber(long atiempoRequestNumber) {
		this.atiempoRequestNumber = atiempoRequestNumber;
	}
	/**
	 * @return Devuelve odsNumber.
	 */
	public long getOdsNumber() {
		return odsNumber;
	}
	/**
	 * @param odsNumber El odsNumber a establecer.
	 */
	public void setOdsNumber(long odsNumber) {
		this.odsNumber = odsNumber;
	}
	/**
	 * @return Devuelve specialService.
	 */
	public String getSpecialService() {
		return specialService;
	}
	/**
	 * @param specialService El specialService a establecer.
	 */
	public void setSpecialService(String specialService) {
		this.specialService = specialService;
	}
	/**
	 * @return Devuelve specialServicesType.
	 */
	public Collection getSpecialServicesType() {
		return specialServicesType;
	}
	/**
	 * @param specialServicesType El specialServicesType a establecer.
	 */
	public void setSpecialServicesType(Collection specialServicesType) {
		this.specialServicesType = specialServicesType;
	}
}
