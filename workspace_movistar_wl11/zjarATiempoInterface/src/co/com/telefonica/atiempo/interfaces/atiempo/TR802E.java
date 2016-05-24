/*
 * Creado el 17/07/2015
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package co.com.telefonica.atiempo.interfaces.atiempo;

import co.com.telefonica.atiempo.interfaces.atiempo.base.ITRxxxBase;
import co.com.telefonica.atiempo.util.EqualityUtilities;

/**
 * @author Administrador
 * 
 * TODO Para cambiar la plantilla de este comentario generado, vaya a Ventana -
 * Preferencias - Java - Estilo de código - Plantillas de código
 */
public class TR802E extends ResponseHeaderAgendaSC implements ITRxxxBase {
	private String idSourceSystem;

	private String idSchedule;

	private String apptNumber;

	private String response;

	private String DescripcionError;

	private String material_code;

	private String modem_serial;

	public int hashCode() {
		return super.hashCode();
	}

	public boolean equals(Object arg0) {
		if (arg0 instanceof TR802E) {
			TR802E other = (TR802E) arg0;
			return super.equals(arg0)
					&& EqualityUtilities.equals(idSourceSystem,
							other.idSourceSystem)
					&& EqualityUtilities.equals(idSchedule, other.idSchedule)
					&& EqualityUtilities.equals(apptNumber, other.apptNumber)
					&& EqualityUtilities.equals(response, other.response)
					&& EqualityUtilities.equals(DescripcionError,
							other.DescripcionError)
					&& EqualityUtilities.equals(material_code,
							other.material_code)
					&& EqualityUtilities.equals(modem_serial,
							other.modem_serial);
		} else {
			return false;
		}
	}

	/**
	 * @return Devuelve idSchedule.
	 */
	public String getIdSchedule() {
		return idSchedule;
	}
	/**
	 * @param idSchedule El idSchedule a establecer.
	 */
	public void setIdSchedule(String idSchedule) {
		this.idSchedule = idSchedule;
	}
	/**
	 * @return Devuelve idSourceSystem.
	 */
	public String getIdSourceSystem() {
		return idSourceSystem;
	}
	/**
	 * @param idSourceSystem El idSourceSystem a establecer.
	 */
	public void setIdSourceSystem(String idSourceSystem) {
		this.idSourceSystem = idSourceSystem;
	}
	/**
	 * @return Devuelve material_code.
	 */
	public String getMaterial_code() {
		return material_code;
	}
	/**
	 * @param material_code El material_code a establecer.
	 */
	public void setMaterial_code(String material_code) {
		this.material_code = material_code;
	}
	/**
	 * @return Devuelve modem_serial.
	 */
	public String getModem_serial() {
		return modem_serial;
	}
	/**
	 * @param modem_serial El modem_serial a establecer.
	 */
	public void setModem_serial(String modem_serial) {
		this.modem_serial = modem_serial;
	}
	/**
	 * @return Devuelve response.
	 */
	public String getResponse() {
		return response;
	}
	/**
	 * @param response El response a establecer.
	 */
	public void setResponse(String response) {
		this.response = response;
	}
	/**
	 * @return Devuelve apptNumber.
	 */
	public String getApptNumber() {
		return apptNumber;
	}
	/**
	 * @param apptNumber El apptNumber a establecer.
	 */
	public void setApptNumber(String apptNumber) {
		this.apptNumber = apptNumber;
	}
	/**
	 * @return Devuelve descripcionError.
	 */
	public String getDescripcionError() {
		return DescripcionError;
	}
	/**
	 * @param descripcionError El descripcionError a establecer.
	 */
	public void setDescripcionError(String descripcionError) {
		DescripcionError = descripcionError;
	}
}