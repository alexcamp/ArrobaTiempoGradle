/*
 * Created on 09-03-2007
 */
package co.com.atiempo.dto;

import java.sql.Timestamp;

/**
 * @author Respinoza
 */
public class UsuarioDTO {

	/**
	 * 
	 */
	public UsuarioDTO() {
		super();
	}
	
	private Long usuaId;
	private String usuaLogin;
	private String usuaNombre;
	private String usuaDireccion;
	private String usuaEmail;
	private String usuaTelefono;
	private String usuaRut;
	private String usuaCargo;
	private String usuaHabilitado;
	private String emprId;
	private String usuaPassword;
	private String usuaIdca;
	private Timestamp usuaFechaAlta;
	private Long esusId;
	private Timestamp esusUltimaActualizacion;
	private String usuaNombres;
	private String usuaApePaterno;
	private String usuaApeMaterno;
	private String usuaNumRut;
	private String usuaDvRut;

	


	/**
	 * @return
	 */
	public String getEmprId() {
		return emprId;
	}

	/**
	 * @return
	 */
	public Long getEsusId() {
		return esusId;
	}

	/**
	 * @return
	 */
	public Timestamp getEsusUltimaActualizacion() {
		return esusUltimaActualizacion;
	}

	/**
	 * @return
	 */
	public String getUsuaApeMaterno() {
		return usuaApeMaterno;
	}

	/**
	 * @return
	 */
	public String getUsuaApePaterno() {
		return usuaApePaterno;
	}

	/**
	 * @return
	 */
	public String getUsuaCargo() {
		return usuaCargo;
	}

	/**
	 * @return
	 */
	public String getUsuaDireccion() {
		return usuaDireccion;
	}

	/**
	 * @return
	 */
	public String getUsuaDvRut() {
		return usuaDvRut;
	}

	/**
	 * @return
	 */
	public String getUsuaEmail() {
		return usuaEmail;
	}

	/**
	 * @return
	 */
	public Timestamp getUsuaFechaAlta() {
		return usuaFechaAlta;
	}

	/**
	 * @return
	 */
	public String getUsuaHabilitado() {
		return usuaHabilitado;
	}

	/**
	 * @return
	 */
	public Long getUsuaId() {
		return usuaId;
	}

	/**
	 * @return
	 */
	public String getUsuaIdca() {
		return usuaIdca;
	}

	/**
	 * @return
	 */
	public String getUsuaLogin() {
		return usuaLogin;
	}

	/**
	 * @return
	 */
	public String getUsuaNombre() {
		return usuaNombre;
	}

	/**
	 * @return
	 */
	public String getUsuaNombres() {
		return usuaNombres;
	}

	/**
	 * @return
	 */
	public String getUsuaNumRut() {
		return usuaNumRut;
	}

	/**
	 * @return
	 */
	public String getUsuaPassword() {
		return usuaPassword;
	}

	/**
	 * @return
	 */
	public String getUsuaRut() {
		return usuaRut;
	}

	/**
	 * @return
	 */
	public String getUsuaTelefono() {
		return usuaTelefono;
	}

	/**
	 * @param string
	 */
	public void setEmprId(String string) {
		emprId = string;
	}

	/**
	 * @param long1
	 */
	public void setEsusId(Long long1) {
		esusId = long1;
	}

	/**
	 * @param timestamp
	 */
	public void setEsusUltimaActualizacion(Timestamp timestamp) {
		esusUltimaActualizacion = timestamp;
	}

	/**
	 * @param string
	 */
	public void setUsuaApeMaterno(String string) {
		usuaApeMaterno = string;
	}

	/**
	 * @param string
	 */
	public void setUsuaApePaterno(String string) {
		usuaApePaterno = string;
	}

	/**
	 * @param string
	 */
	public void setUsuaCargo(String string) {
		usuaCargo = string;
	}

	/**
	 * @param string
	 */
	public void setUsuaDireccion(String string) {
		usuaDireccion = string;
	}

	/**
	 * @param string
	 */
	public void setUsuaDvRut(String string) {
		usuaDvRut = string;
	}

	/**
	 * @param string
	 */
	public void setUsuaEmail(String string) {
		usuaEmail = string;
	}

	/**
	 * @param timestamp
	 */
	public void setUsuaFechaAlta(Timestamp timestamp) {
		usuaFechaAlta = timestamp;
	}

	/**
	 * @param string
	 */
	public void setUsuaHabilitado(String string) {
		usuaHabilitado = string;
	}

	/**
	 * @param long1
	 */
	public void setUsuaId(Long long1) {
		usuaId = long1;
	}

	/**
	 * @param string
	 */
	public void setUsuaIdca(String string) {
		usuaIdca = string;
	}

	/**
	 * @param string
	 */
	public void setUsuaLogin(String string) {
		usuaLogin = string;
	}

	/**
	 * @param string
	 */
	public void setUsuaNombre(String string) {
		usuaNombre = string;
	}

	/**
	 * @param string
	 */
	public void setUsuaNombres(String string) {
		usuaNombres = string;
	}

	/**
	 * @param string
	 */
	public void setUsuaNumRut(String string) {
		usuaNumRut = string;
	}

	/**
	 * @param string
	 */
	public void setUsuaPassword(String string) {
		usuaPassword = string;
	}

	/**
	 * @param string
	 */
	public void setUsuaRut(String string) {
		usuaRut = string;
	}

	/**
	 * @param string
	 */
	public void setUsuaTelefono(String string) {
		usuaTelefono = string;
	}

}
