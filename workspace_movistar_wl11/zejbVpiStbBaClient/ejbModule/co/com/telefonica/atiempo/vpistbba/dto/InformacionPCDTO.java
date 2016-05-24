/*
 * Creado el 07/03/2007
 *
 * Para cambiar la plantilla para este archivo generado vaya a
 * Ventana&gt;Preferencias&gt;Java&gt;Generación de código&gt;Código y comentarios
 */
package co.com.telefonica.atiempo.vpistbba.dto;

import co.com.telefonica.atiempo.utiles.DataTransferObject;

/**
 * @author gmassida
 *
 * Para cambiar la plantilla para este comentario de tipo generado vaya a
 * Ventana&gt;Preferencias&gt;Java&gt;Generación de código&gt;Código y comentarios
 */
public class InformacionPCDTO implements DataTransferObject {
	
	private Long id;
	private String nombrePS;
	private String tipo;
	private String subTipo;
	private String direccionInstalacion;
	private String codDepartamento;
	private String codLocalidad;
	private String codSubLocalidad;
	private String departamento;
	private String localidad;
	private String subLocalidad;

	private String fechaHoraCompromiso;
	private String identificadorSTB;
	private String identificadorTV;


	/**
	 * @return
	 */
	public String getDepartamento() {
		return departamento;
	}

	/**
	 * @return
	 */
	public String getDireccionInstalacion() {
		return direccionInstalacion;
	}

	/**
	 * @return
	 */
	public String getFechaHoraCompromiso() {
		return fechaHoraCompromiso;
	}

	/**
	 * @return
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @return
	 */
	public String getIdentificadorSTB() {
		return identificadorSTB;
	}

	/**
	 * @return
	 */
	public String getIdentificadorTV() {
		return identificadorTV;
	}

	/**
	 * @return
	 */
	public String getLocalidad() {
		return localidad;
	}

	/**
	 * @return
	 */
	public String getNombrePS() {
		return nombrePS;
	}

	/**
	 * @return
	 */
	public String getSubLocalidad() {
		return subLocalidad;
	}

	/**
	 * @return
	 */
	public String getSubTipo() {
		return subTipo;
	}

	/**
	 * @return
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * @param string
	 */
	public void setDepartamento(String string) {
		departamento = string;
	}

	/**
	 * @param string
	 */
	public void setDireccionInstalacion(String string) {
		direccionInstalacion = string;
	}

	/**
	 * @param date
	 */
	public void setFechaHoraCompromiso(String date) {
		fechaHoraCompromiso = date;
	}

	/**
	 * @param long1
	 */
	public void setId(Long long1) {
		id = long1;
	}

	/**
	 * @param string
	 */
	public void setIdentificadorSTB(String string) {
		identificadorSTB = string;
	}

	/**
	 * @param string
	 */
	public void setIdentificadorTV(String string) {
		identificadorTV = string;
	}

	/**
	 * @param string
	 */
	public void setLocalidad(String string) {
		localidad = string;
	}

	/**
	 * @param string
	 */
	public void setNombrePS(String string) {
		nombrePS = string;
	}

	/**
	 * @param string
	 */
	public void setSubLocalidad(String string) {
		subLocalidad = string;
	}

	/**
	 * @param string
	 */
	public void setSubTipo(String string) {
		subTipo = string;
	}

	/**
	 * @param string
	 */
	public void setTipo(String string) {
		tipo = string;
	}

	/**
	 * @return
	 */
	public String getCodDepartamento() {
		return codDepartamento;
	}

	/**
	 * @return
	 */
	public String getCodLocalidad() {
		return codLocalidad;
	}

	/**
	 * @return
	 */
	public String getCodSubLocalidad() {
		return codSubLocalidad;
	}

	/**
	 * @param string
	 */
	public void setCodDepartamento(String string) {
		codDepartamento = string;
	}

	/**
	 * @param string
	 */
	public void setCodLocalidad(String string) {
		codLocalidad = string;
	}

	/**
	 * @param string
	 */
	public void setCodSubLocalidad(String string) {
		codSubLocalidad = string;
	}

}
