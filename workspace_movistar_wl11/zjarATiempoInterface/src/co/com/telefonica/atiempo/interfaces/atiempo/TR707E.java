/*
 * Creado el 16/12/2013
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package co.com.telefonica.atiempo.interfaces.atiempo;

import co.com.telefonica.atiempo.util.EqualityUtilities;

/**
 * @author Administrador
 * 
 * TODO Para cambiar la plantilla de este comentario generado, vaya a Ventana -
 * Preferencias - Java - Estilo de código - Plantillas de código
 */
public class TR707E extends ResponseHeaderAgendaSC {

	private String idSourceSystem;
	private String idSchedule;
	

	public boolean equals(Object arg0) {
		if (arg0 instanceof TR707E) {
			TR707E other = (TR707E) arg0;
			return super.equals(arg0)
					&& EqualityUtilities.equals(
							idSourceSystem,
							other.idSourceSystem)
					&& EqualityUtilities.equals(idSchedule, other.idSchedule);

		}
		return false;
	}


	/**
	 * @return Devuelve idSchedule.
	 */
	public String getIdSchedule() {
		return idSchedule;
	}

	/**
	 * @param idSchedule
	 *            El idSchedule a establecer.
	 */
	public void setIdSchedule(String idSchedule) {
		this.idSchedule = idSchedule;
	}


	public String getIdSourceSystem() {
		return idSourceSystem;
	}
	/**
	 * @param idSourceSystem El idSourceSystem a establecer.
	 */
	public void setIdSourceSystem(String idSourceSystem) {
		this.idSourceSystem = idSourceSystem;
	}
}