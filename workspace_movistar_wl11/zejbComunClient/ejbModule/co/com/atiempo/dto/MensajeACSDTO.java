/*
 * Creado el 25/02/2011
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package co.com.atiempo.dto;

import java.sql.Timestamp;

import co.com.telefonica.atiempo.interfaces.atiempo.TR137S;

/**
 * @author damartinezv
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class MensajeACSDTO {
	
	private Long id;
	private Long petiNumero;
	private TR137S tr137s;
	private Timestamp fechaIngreso;
	
	

	public Timestamp getFechaIngreso() {
		return fechaIngreso;
	}
	public void setFechaIngreso(Timestamp fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getPetiNumero() {
		return petiNumero;
	}
	public void setPetiNumero(Long petiNumero) {
		this.petiNumero = petiNumero;
	}
	public TR137S getTr137s() {
		return tr137s;
	}
	public void setTr137s(TR137S tr137s) {
		this.tr137s = tr137s;
	}
}
