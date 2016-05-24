/*
 * Creado el 07/03/2007
 *
 * Para cambiar la plantilla para este archivo generado vaya a
 * Ventana&gt;Preferencias&gt;Java&gt;Generación de código&gt;Código y comentarios
 */
package co.com.telefonica.atiempo.vpistbba.dto;

import java.util.Date;

import co.com.telefonica.atiempo.utiles.DataTransferObject;

/**
 * @author gmassida
 *
 * Para cambiar la plantilla para este comentario de tipo generado vaya a
 * Ventana&gt;Preferencias&gt;Java&gt;Generación de código&gt;Código y comentarios
 */
public class InformacionPSDTO implements DataTransferObject {
	
	private Long codigoSubPeticion;
	private Long codigoPS;
	private String descripcionPS;
	private Long codigoOC;
	private String descripcionOC;
	// Inicio: no estoy seguro de este campo.
	// aca debería ir el resultado de la con OK o NOOK
	private String codigoEstadoSubpeticion;
	private String nombreMotivo; 
	// no se que pasa con la novedad 
	// no se de donde salen estas fechas.
	private Date fechaRegistro;
	private Date fechaAsignacion;
	private Date fechaTermino;
	
	/**
	 * @return
	 */
	public String getCodigoEstadoSubpeticion() {
		return codigoEstadoSubpeticion;
	}

	/**
	 * @return
	 */
	public Long getCodigoOC() {
		return codigoOC;
	}

	/**
	 * @return
	 */
	public Long getCodigoPS() {
		return codigoPS;
	}

	/**
	 * @return
	 */
	public Long getCodigoSubPeticion() {
		return codigoSubPeticion;
	}

	/**
	 * @return
	 */
	public String getDescripcionOC() {
		return descripcionOC;
	}

	/**
	 * @return
	 */
	public String getDescripcionPS() {
		return descripcionPS;
	}

	/**
	 * @return
	 */
	public Date getFechaAsignacion() {
		return fechaAsignacion;
	}

	/**
	 * @return
	 */
	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	/**
	 * @return
	 */
	public Date getFechaTermino() {
		return fechaTermino;
	}

	/**
	 * @return
	 */
	public String getNombreMotivo() {
		return nombreMotivo;
	}

	/**
	 * @param string
	 */
	public void setCodigoEstadoSubpeticion(String codigoEstadoSubpeticion) {
		this.codigoEstadoSubpeticion = codigoEstadoSubpeticion;
	}

	/**
	 * @param long1
	 */
	public void setCodigoOC(Long codigoOC) {
		this.codigoOC = codigoOC;
	}

	/**
	 * @param long1
	 */
	public void setCodigoPS(Long codigoPS) {
		this.codigoPS = codigoPS;
	}

	/**
	 * @param long1
	 */
	public void setCodigoSubPeticion(Long codigoSubPeticion) {
		this.codigoSubPeticion = codigoSubPeticion;
	}

	/**
	 * @param string
	 */
	public void setDescripcionOC(String descripcionOC) {
		this.descripcionOC = descripcionOC;
	}

	/**
	 * @param string
	 */
	public void setDescripcionPS(String descripcionPS) {
		this.descripcionPS = descripcionPS;
	}

	/**
	 * @param date
	 */
	public void setFechaAsignacion(Date fechaAsignacion) {
		this.fechaAsignacion = fechaAsignacion;
	}

	/**
	 * @param date
	 */
	public void setFechaRegistro(Date fechaRegistro ) {
		this.fechaRegistro = fechaRegistro;
	}

	/**
	 * @param date
	 */
	public void setFechaTermino(Date fechaTermino) {
		this.fechaTermino = fechaTermino;
	}

	/**
	 * @param string
	 */
	public void setNombreMotivo(String nombreMotivo) {
		this.nombreMotivo = nombreMotivo;
	}

}
