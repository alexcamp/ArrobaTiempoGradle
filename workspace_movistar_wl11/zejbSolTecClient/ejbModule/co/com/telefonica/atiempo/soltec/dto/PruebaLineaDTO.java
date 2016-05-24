/*
 * Created on 29-jun-07
 */
package co.com.telefonica.atiempo.soltec.dto;

import java.sql.Timestamp;

/**
 * @author rodrigo
 */
public class PruebaLineaDTO {
	
	private Long idPrueba;
	private String codPrueba;
	private Long codAveCd;
	private String observacion;
	private String usuario;
	private Timestamp fecha;
	private Long idFamilia;
	private Long idUsuario;
	
	private String descPrueba;
	
	public PruebaLineaDTO(){
		
	}
	
	

	/**
	 * @return
	 */
	public Long getCodAveCd() {
		return codAveCd;
	}

	/**
	 * @return
	 */
	public String getCodPrueba() {
		return codPrueba;
	}

	/**
	 * @return
	 */
	public Timestamp getFecha() {
		return fecha;
	}

	/**
	 * @return
	 */
	public Long getIdPrueba() {
		return idPrueba;
	}

	/**
	 * @return
	 */
	public String getObservacion() {
		return observacion;
	}

	/**
	 * @return
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * @param long1
	 */
	public void setCodAveCd(Long long1) {
		codAveCd = long1;
	}

	/**
	 * @param string
	 */
	public void setCodPrueba(String string) {
		codPrueba = string;
	}

	/**
	 * @param timestamp
	 */
	public void setFecha(Timestamp timestamp) {
		fecha = timestamp;
	}

	/**
	 * @param long1
	 */
	public void setIdPrueba(Long long1) {
		idPrueba = long1;
	}

	/**
	 * @param string
	 */
	public void setObservacion(String string) {
		observacion = string;
	}

	/**
	 * @param string
	 */
	public void setUsuario(String string) {
		usuario = string;
	}

	/**
	 * @return
	 */
	public String getDescPrueba() {
		return descPrueba;
	}

	/**
	 * @param string
	 */
	public void setDescPrueba(String string) {
		descPrueba = string;
	}

	/**
	 * @return
	 */
	public Long getIdFamilia() {
		return idFamilia;
	}

	/**
	 * @param long1
	 */
	public void setIdFamilia(Long long1) {
		idFamilia = long1;
	}

	/**
	 * @return
	 */
	public Long getIdUsuario() {
		return idUsuario;
	}

	/**
	 * @param long1
	 */
	public void setIdUsuario(Long long1) {
		idUsuario = long1;
	}

}
