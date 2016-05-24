/*
 * Creado el 17/07/2015
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package co.com.telefonica.atiempo.interfaces.atiempo;

import co.com.telefonica.atiempo.util.EqualityUtilities;

/**
 * @author sfandino
 * 
 * TODO Para cambiar la plantilla de este comentario generado, vaya a Ventana -
 * Preferencias - Java - Estilo de código - Plantillas de código
 */
public class TR802S extends RequestHeaderAgendaSC {

	private String id_source_system;

	private String id_schedule;

	private String apptNumber;

	private String material_code;

	private String modem_brand;

	private String model_modem;

	private String modem_type;

	private String modem_serial;

	public int hashCode() {
		return super.hashCode();
	}

	public boolean equals(Object arg0) {
		if (arg0 instanceof TR802S) {
			TR802S other = (TR802S) arg0;
			return super.equals(arg0)
					&& EqualityUtilities.equals(id_source_system,
							other.id_source_system)
					&& EqualityUtilities.equals(id_schedule, other.id_schedule)
					&& EqualityUtilities.equals(apptNumber, other.apptNumber)
					&& EqualityUtilities.equals(model_modem, other.model_modem)
					&& EqualityUtilities.equals(modem_type, other.modem_type)
					&& EqualityUtilities.equals(modem_serial,
							other.modem_serial);
		} else {
			return false;
		}
	}

	/**
	 * @return Devuelve appt_number.
	 */
	public String getApptNumber() {
		return apptNumber;
	}

	/**
	 * @param appt_number
	 *            El appt_number a establecer.
	 */
	public void setApptNumber(String appt_number) {
		this.apptNumber = appt_number;
	}

	/**
	 * @return Devuelve id_schedule.
	 */
	public String getId_schedule() {
		return id_schedule;
	}

	/**
	 * @param id_schedule
	 *            El id_schedule a establecer.
	 */
	public void setId_schedule(String id_schedule) {
		this.id_schedule = id_schedule;
	}

	/**
	 * @return Devuelve id_source_system.
	 */
	public String getId_source_system() {
		return id_source_system;
	}

	/**
	 * @param id_source_system
	 *            El id_source_system a establecer.
	 */
	public void setId_source_system(String id_source_system) {
		this.id_source_system = id_source_system;
	}

	/**
	 * @return Devuelve material_code.
	 */
	public String getMaterial_code() {
		return material_code;
	}

	/**
	 * @param material_code
	 *            El material_code a establecer.
	 */
	public void setMaterial_code(String material_code) {
		this.material_code = material_code;
	}

	/**
	 * @return Devuelve model_modem.
	 */
	public String getModel_modem() {
		return model_modem;
	}

	/**
	 * @param model_modem
	 *            El model_modem a establecer.
	 */
	public void setModel_modem(String model_modem) {
		this.model_modem = model_modem;
	}

	/**
	 * @return Devuelve modem_brand.
	 */
	public String getModem_brand() {
		return modem_brand;
	}

	/**
	 * @param modem_brand
	 *            El modem_brand a establecer.
	 */
	public void setModem_brand(String modem_brand) {
		this.modem_brand = modem_brand;
	}

	/**
	 * @return Devuelve modem_serial.
	 */
	public String getModem_serial() {
		return modem_serial;
	}

	/**
	 * @param modem_serial
	 *            El modem_serial a establecer.
	 */
	public void setModem_serial(String modem_serial) {
		this.modem_serial = modem_serial;
	}

	/**
	 * @return Devuelve modem_type.
	 */
	public String getModem_type() {
		return modem_type;
	}

	/**
	 * @param modem_type
	 *            El modem_type a establecer.
	 */
	public void setModem_type(String modem_type) {
		this.modem_type = modem_type;
	}
}