/*
 * Created on 09-03-2007
 */
package co.com.atiempo.dto;

import java.sql.Timestamp;

/**
 * @author Respinoza
 */
public class ConsultaEstadoDTO {

	/**
	 * 
	 */
	public ConsultaEstadoDTO() {
		super();
	}
	
	private Long coesNumeroPeticion;
	private String coesEstado;
	private String coesDesest;
	private String coesRespuestaAfac;
	private String coesRespuestaApel;
	private Timestamp coesFechaEmision;
	private Timestamp coesFechaCompromiso;
	private String coesCodPlan;
	private String coesPuntoVenta;
	private String ticaId;
	private Long codCliente;
	private Long LineId;
	private Long agenciaId;

	/**
	 * @return
	 */
	public Long getAgenciaId() {
		return agenciaId;
	}

	/**
	 * @return
	 */
	public Long getCodCliente() {
		return codCliente;
	}

	/**
	 * @return
	 */
	public String getCoesCodPlan() {
		return coesCodPlan;
	}

	/**
	 * @return
	 */
	public String getCoesDesest() {
		return coesDesest;
	}

	/**
	 * @return
	 */
	public String getCoesEstado() {
		return coesEstado;
	}

	/**
	 * @return
	 */
	public Timestamp getCoesFechaCompromiso() {
		return coesFechaCompromiso;
	}

	/**
	 * @return
	 */
	public Timestamp getCoesFechaEmision() {
		return coesFechaEmision;
	}

	/**
	 * @return
	 */
	public Long getCoesNumeroPeticion() {
		return coesNumeroPeticion;
	}

	/**
	 * @return
	 */
	public String getCoesPuntoVenta() {
		return coesPuntoVenta;
	}

	/**
	 * @return
	 */
	public String getCoesRespuestaAfac() {
		return coesRespuestaAfac;
	}

	/**
	 * @return
	 */
	public String getCoesRespuestaApel() {
		return coesRespuestaApel;
	}

	/**
	 * @return
	 */
	public Long getLineId() {
		return LineId;
	}

	/**
	 * @return
	 */
	public String getTicaId() {
		return ticaId;
	}

	/**
	 * @param long1
	 */
	public void setAgenciaId(Long long1) {
		agenciaId = long1;
	}

	/**
	 * @param long1
	 */
	public void setCodCliente(Long long1) {
		codCliente = long1;
	}

	/**
	 * @param string
	 */
	public void setCoesCodPlan(String string) {
		coesCodPlan = string;
	}

	/**
	 * @param string
	 */
	public void setCoesDesest(String string) {
		coesDesest = string;
	}

	/**
	 * @param string
	 */
	public void setCoesEstado(String string) {
		coesEstado = string;
	}

	/**
	 * @param timestamp
	 */
	public void setCoesFechaCompromiso(Timestamp timestamp) {
		coesFechaCompromiso = timestamp;
	}

	/**
	 * @param timestamp
	 */
	public void setCoesFechaEmision(Timestamp timestamp) {
		coesFechaEmision = timestamp;
	}

	/**
	 * @param long1
	 */
	public void setCoesNumeroPeticion(Long long1) {
		coesNumeroPeticion = long1;
	}

	/**
	 * @param string
	 */
	public void setCoesPuntoVenta(String string) {
		coesPuntoVenta = string;
	}

	/**
	 * @param string
	 */
	public void setCoesRespuestaAfac(String string) {
		coesRespuestaAfac = string;
	}

	/**
	 * @param string
	 */
	public void setCoesRespuestaApel(String string) {
		coesRespuestaApel = string;
	}

	/**
	 * @param long1
	 */
	public void setLineId(Long long1) {
		LineId = long1;
	}

	/**
	 * @param string
	 */
	public void setTicaId(String string) {
		ticaId = string;
	}

}
