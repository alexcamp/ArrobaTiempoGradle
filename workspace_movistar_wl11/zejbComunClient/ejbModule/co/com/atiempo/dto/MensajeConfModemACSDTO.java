/*
 * Created on Mar 16, 2011
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package co.com.atiempo.dto;

import java.sql.Timestamp;

/**
 * @author damartinezv
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class MensajeConfModemACSDTO {
	private Long id;
	private Long petiNumero;
	private Timestamp fechaEnvio;
	private String modemProcesado;
	private String estadoMensaje;
	private String descMensaje;
	private String operacionMensaje;
	
	public String getDescMensaje() {
		if (descMensaje != null)
			return descMensaje;
		else
			return "";
	}
	public void setDescMensaje(String descMensaje) {
		this.descMensaje = descMensaje;
	}
	public String getEstadoMensaje() {
		if (estadoMensaje != null)
			return estadoMensaje;
		else
			return "";
	}
	public void setEstadoMensaje(String estadoMensaje) {
		this.estadoMensaje = estadoMensaje;
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
	public String getModemProcesado() {
		return modemProcesado;
	}
	public void setModemProcesado(String modemProcesado) {
		this.modemProcesado = modemProcesado;
	}
	public String getOperacionMensaje() {
		return operacionMensaje;
	}
	public void setOperacionMensaje(String operacionMensaje) {
		this.operacionMensaje = operacionMensaje;
	}
	public Long getPetiNumero() {
		return petiNumero;
	}
	public void setPetiNumero(Long petiNumero) {
		this.petiNumero = petiNumero;
	}
}
