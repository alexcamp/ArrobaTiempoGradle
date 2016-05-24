package com.telefonica_chile.comun.peticion.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * @author oaliaga (Brother Bear)
 */
public class PeticionDTO implements Serializable{
	private Long id;
	private Integer canalVenta;
	private Long idLinea;
	private Long idRure;
	private Integer idAmbito;
	private Long idTipoRiesgo;
	private Integer idPuntoVenta;
	private Integer idSistEntrada;
	private Integer idEstadoPeticion;
	private String idTica;
	private Date fechaIngreso;
	private Date fechaCompromiso;
	private Date fechaTermino;
	private String observaciones;
	private String usuarioEmisor;
	private Byte requiereMutacion;
	private Byte urgencia;
	private Long idAgencia;
	private String rutVendedor;
	private String rutRepresentante;
	private String causalBaja;
	private Long idLineaTraslado;
	private Long idCliente;
	private String tipoHora;
	private Date horaInicio;
	private Date horaFin;
	private Integer tipoTrabajo;
	private String numeroPostulacion;
	private Long ooss = null;
	private String nomBloqueSegmento="";
	private boolean mdfAbierto = false;
	private boolean gestionAdslAbierto = false;		//si está abierto, cuando SSVA envíe a PGI se le informará que
													//al terminar ADSL podrá ver la pantalla de PGI
	private boolean gestionRecursoAbierto = false;
	private boolean instalacionAbierto = false;
	private boolean ssvaAbierto = false;
	private boolean pcoAbierto = false;
	private boolean historico = false;
	
	//CONSULTA ESTADO
	private String codigoEstado;
	/**
	 * @return
	 */
	public Integer getCanalVenta() {
		return canalVenta;
	}

	/**
	 * @return
	 */
	public String getCausalBaja() {
		return causalBaja;
	}

	/**
	 * @return
	 */
	public Date getFechaCompromiso() {
		return fechaCompromiso;
	}

	/**
	 * @return
	 */
	public Date getFechaIngreso() {
		return fechaIngreso;
	}

	/**
	 * @return
	 */
	public Date getHoraFin() {
		return horaFin;
	}

	/**
	 * @return
	 */
	public Date getHoraInicio() {
		return horaInicio;
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
	public Long getIdAgencia() {
		return idAgencia;
	}

	/**
	 * @return
	 */
	public Integer getIdAmbito() {
		return idAmbito;
	}

	/**
	 * @return
	 */
	public Long getIdCliente() {
		return idCliente;
	}

	/**
	 * @return
	 */
	public Integer getIdEstadoPeticion() {
		return idEstadoPeticion;
	}

	/**
	 * @return
	 */
	public Long getIdLinea() {
		return idLinea;
	}

	/**
	 * @return
	 */
	public Long getIdLineaTraslado() {
		return idLineaTraslado;
	}

	/**
	 * @return
	 */
	public Integer getIdPuntoVenta() {
		return idPuntoVenta;
	}

	/**
	 * @return
	 */
	public Long getIdRure() {
		return idRure;
	}

	/**
	 * @return
	 */
	public Integer getIdSistEntrada() {
		return idSistEntrada;
	}

	/**
	 * @return
	 */
	public String getIdTica() {
		return idTica;
	}

	/**
	 * @return
	 */
	public Long getIdTipoRiesgo() {
		return idTipoRiesgo;
	}

	/**
	 * @return
	 */
	public String getObservaciones() {
		return observaciones;
	}

	/**
	 * @return
	 */
	public Byte getRequiereMutacion() {
		return requiereMutacion;
	}

	/**
	 * @return
	 */
	public String getRutRepresentante() {
		return rutRepresentante;
	}

	/**
	 * @return
	 */
	public String getRutVendedor() {
		return rutVendedor;
	}

	/**
	 * @return
	 */
	public String getTipoHora() {
		return tipoHora;
	}

	/**
	 * @return
	 */
	public Integer getTipoTrabajo() {
		return tipoTrabajo;
	}

	/**
	 * @return
	 */
	public Byte getUrgencia() {
		return urgencia;
	}

	/**
	 * @return
	 */
	public String getUsuarioEmisor() {
		return usuarioEmisor;
	}

	/**
	 * @param integer
	 */
	public void setCanalVenta(Integer integer) {
		canalVenta = integer;
	}

	/**
	 * @param string
	 */
	public void setCausalBaja(String string) {
		causalBaja = string;
	}

	/**
	 * @param date
	 */
	public void setFechaCompromiso(Date date) {
		fechaCompromiso = date;
	}

	/**
	 * @param date
	 */
	public void setFechaIngreso(Date date) {
		fechaIngreso = date;
	}

	/**
	 * @param date
	 */
	public void setHoraFin(Date date) {
		horaFin = date;
	}

	/**
	 * @param date
	 */
	public void setHoraInicio(Date date) {
		horaInicio = date;
	}

	/**
	 * @param long1
	 */
	public void setId(Long long1) {
		id = long1;
	}

	/**
	 * @param long1
	 */
	public void setIdAgencia(Long long1) {
		idAgencia = long1;
	}

	/**
	 * @param integer
	 */
	public void setIdAmbito(Integer integer) {
		idAmbito = integer;
	}

	/**
	 * @param long1
	 */
	public void setIdCliente(Long long1) {
		idCliente = long1;
	}

	/**
	 * @param integer
	 */
	public void setIdEstadoPeticion(Integer integer) {
		idEstadoPeticion = integer;
	}

	/**
	 * @param long1
	 */
	public void setIdLinea(Long long1) {
		idLinea = long1;
	}

	/**
	 * @param long1
	 */
	public void setIdLineaTraslado(Long long1) {
		idLineaTraslado = long1;
	}

	/**
	 * @param integer
	 */
	public void setIdPuntoVenta(Integer integer) {
		idPuntoVenta = integer;
	}

	/**
	 * @param long1
	 */
	public void setIdRure(Long long1) {
		idRure = long1;
	}

	/**
	 * @param integer
	 */
	public void setIdSistEntrada(Integer integer) {
		idSistEntrada = integer;
	}

	/**
	 * @param string
	 */
	public void setIdTica(String string) {
		idTica = string;
	}

	/**
	 * @param long1
	 */
	public void setIdTipoRiesgo(Long long1) {
		idTipoRiesgo = long1;
	}

	/**
	 * @param string
	 */
	public void setObservaciones(String string) {
		observaciones = string;
	}

	/**
	 * @param byte1
	 */
	public void setRequiereMutacion(Byte byte1) {
		requiereMutacion = byte1;
	}

	/**
	 * @param string
	 */
	public void setRutRepresentante(String string) {
		rutRepresentante = string;
	}

	/**
	 * @param string
	 */
	public void setRutVendedor(String string) {
		rutVendedor = string;
	}

	/**
	 * @param string
	 */
	public void setTipoHora(String string) {
		tipoHora = string;
	}

	/**
	 * @param integer
	 */
	public void setTipoTrabajo(Integer integer) {
		tipoTrabajo = integer;
	}

	/**
	 * @param byte1
	 */
	public void setUrgencia(Byte byte1) {
		urgencia = byte1;
	}

	/**
	 * @param string
	 */
	public void setUsuarioEmisor(String string) {
		usuarioEmisor = string;
	}

	/**
	 * @return
	 */
	public Date getFechaTermino() {
		return fechaTermino;
	}

	/**
	 * @param date
	 */
	public void setFechaTermino(Date date) {
		fechaTermino = date;
	}

	/**
	 * @return
	 */
	public String getNumeroPostulacion() {
		return numeroPostulacion;
	}

	/**
	 * @param string
	 */
	public void setNumeroPostulacion(String string) {
		numeroPostulacion = string;
	}

	/**
	 * @return
	 */
	public Long getOoss() {
		return ooss;
	}

	/**
	 * @param long1
	 */
	public void setOoss(Long long1) {
		ooss = long1;
	}

	/**
	 * @return
	 */
	public String getNomBloqueSegmento() {
		return nomBloqueSegmento;
	}

	/**
	 * @param string
	 */
	public void setNomBloqueSegmento(String string) {
		nomBloqueSegmento = string;
	}

	/**
	 * @return
	 */
	public boolean isMdfAbierto() {
		return mdfAbierto;
	}

	/**
	 * @param b
	 */
	public void setMdfAbierto(boolean b) {
		mdfAbierto = b;
	}

	/**
	 * @return
	 */
	public boolean isGestionAdslAbierto() {
		return gestionAdslAbierto;
	}

	/**
	 * @return
	 */
	public boolean isGestionRecursoAbierto() {
		return gestionRecursoAbierto;
	}

	/**
	 * @return
	 */
	public boolean isInstalacionAbierto() {
		return instalacionAbierto;
	}

	/**
	 * @return
	 */
	public boolean isSsvaAbierto() {
		return ssvaAbierto;
	}

	/**
	 * @param b
	 */
	public void setGestionAdslAbierto(boolean b) {
		gestionAdslAbierto = b;
	}

	/**
	 * @param b
	 */
	public void setGestionRecursoAbierto(boolean b) {
		gestionRecursoAbierto =
			b;
	}

	/**
	 * @param b
	 */
	public void setInstalacionAbierto(boolean b) {
		instalacionAbierto = b;
	}

	/**
	 * @param b
	 */
	public void setSsvaAbierto(boolean b) {
		ssvaAbierto = b;
	}

	

	/**
	 * @return
	 */
	public boolean isPcoAbierto() {
		return pcoAbierto;
	}

	/**
	 * @param b
	 */
	public void setPcoAbierto(boolean b) {
		pcoAbierto = b;
	}

	/**
	 * @return
	 */
	public String getCodigoEstado() {
		return codigoEstado;
	}

	/**
	 * @param string
	 */
	public void setCodigoEstado(String string) {
		codigoEstado = string;
	}

	/**
	 * @return
	 */
	public boolean isHistorico() {
		return historico;
	}

	/**
	 * @param b
	 */
	public void setHistorico(boolean b) {
		historico = b;
	}

}
