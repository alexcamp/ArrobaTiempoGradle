/*
 * Creado el 28/02/2011
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package co.com.atiempo.dto;

import java.sql.Timestamp;

import co.com.telefonica.atiempo.interfaces.atiempo.TRSMSE;

/**
 * @author damartinezv
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class MensajeSMSACSDTO {
	private Long id;
	private Long petiNumero;
	private TRSMSE trsmse;
	private Timestamp fechaEnvio;
	private String usuario;
	
	
	
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public Timestamp getFechaEnvio() {
		return fechaEnvio;
	}
	public void setFechaEnvio(Timestamp fechaEnvio) {
		this.fechaEnvio = fechaEnvio;
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
	public TRSMSE getTrsmse() {
		return trsmse;
	}
	public void setTrsmse(TRSMSE trsmse) {
		this.trsmse = trsmse;
	}
}
