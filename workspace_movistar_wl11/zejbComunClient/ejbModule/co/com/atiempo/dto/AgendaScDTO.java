/*
 * Created on Sep 17, 2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package co.com.atiempo.dto;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author idrincon
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class AgendaScDTO implements Serializable {

	private String idActucaion;
	private Long petiNumero;
	private Integer estado;
	private Timestamp fechaReagm;
	private Timestamp fechaMod;
	private String mensajeActuacionST;
	private String tieneActCerradaPorError;
	private String nombreContratista;
	private String quiebres;
	
	
	/**
	 * @return Devuelve tieneActCerradaPorError.
	 */
	public String getTieneActCerradaPorError() {
		return tieneActCerradaPorError;
	}
	/**
	 * @param tieneActCerradaPorError El tieneActCerradaPorError a establecer.
	 */
	public void setTieneActCerradaPorError(String tieneActCerradaPorError) {
		this.tieneActCerradaPorError = tieneActCerradaPorError;
	}
	/**
	 * @return Devuelve mensajeActuacionST.
	 */
	public String getMensajeActuacionST() {
		return mensajeActuacionST;
	}
	/**
	 * @param mensajeActuacionST El mensajeActuacionST a establecer.
	 */
	public void setMensajeActuacionST(String mensajeActuacionST) {
		this.mensajeActuacionST = mensajeActuacionST;
	}
	public AgendaScDTO(){
		super();
	}
	
	/**
	 * @return Returns the estado.
	 */
	public Integer getEstado() {
		return estado;
	}
	/**
	 * @param estado The estado to set.
	 */
	public void setEstado(Integer estado) {
		this.estado = estado;
	}
	/**
	 * @return Returns the fechaMod.
	 */
	public Timestamp getFechaMod() {
		return fechaMod;
	}
	/**
	 * @param fechaMod The fechaMod to set.
	 */
	public void setFechaMod(Timestamp fechaMod) {
		this.fechaMod = fechaMod;
	}
	/**
	 * @return Returns the fechaReagm.
	 */
	public Timestamp getFechaReagm() {
		return fechaReagm;
	}
	/**
	 * @param fechaReagm The fechaReagm to set.
	 */
	public void setFechaReagm(Timestamp fechaReagm) {
		this.fechaReagm = fechaReagm;
	}
	/**
	 * @return Returns the idActucaion.
	 */
	public String getIdActucaion() {
		return idActucaion;
	}
	/**
	 * @param idActucaion The idActucaion to set.
	 */
	public void setIdActucaion(String idActucaion) {
		this.idActucaion = idActucaion;
	}
	/**
	 * @return Returns the petiNumero.
	 */
	public Long getPetiNumero() {
		return petiNumero;
	}
	/**
	 * @param petiNumero The petiNumero to set.
	 */
	public void setPetiNumero(Long petiNumero) {
		this.petiNumero = petiNumero;
	}
	
	/**
	 * @return Returns the nombreContratista.
	 */
	public String getNombreContratista() {
		return nombreContratista;
	}
	/**
	 * @param nombreContratista The nombreContratista to set.
	 */
	public void setNombreContratista(String nombreContratista) {
		this.nombreContratista = nombreContratista;
	}
		
	/**
	 * @return Returns the quiebres.
	 */
	public String getQuiebres() {
		return quiebres;
	}
	/**
	 * @param quiebres The quiebres to set.
	 */
	public void setQuiebres(String quiebres) {
		this.quiebres = quiebres;
	}
}
