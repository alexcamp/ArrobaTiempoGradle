/*
 * Created on 08-03-2007
 */
package co.com.atiempo.dto;

import java.sql.Timestamp;

/**
 * @author Respinoza
 */
public class BintengradaDTO {

	/**
	 * 
	 */
	public BintengradaDTO() {
		super();
	}
	private Long biId;
	private Long apId;
	private Long usuaId;
	private Long actId;
	private Long agenId;
	private Long segmId;
	private Long biNroPeticion;
	private Timestamp biFechaCompromiso;
	private String biClienteNombre;
	private String biClienteApellidos;
	private String biClienteServicio;
	private String biClienteArea;
	private String biClienteRut;
	private String biClienteRutDv;
	private String biFamiliaPs;
	private String biUrlDetalle;
	private Integer biPuntaje;	
	private Timestamp biFechaInicio;
	private Timestamp biFechaApertura;
	private Timestamp biFechaAsignacion;
	private Integer biEstadoPeticion;
	private Integer biTipoTrabajo;
	private Integer biVisible;
	private Long biGrupoSegmento;
	private Long biIdTipoAgendamiento;
	private String biHoraDesde;
	private String biHoraHasta;
	private Integer biIdRango;
	private Long rolId;
	private String ticaId;
	private String biPcom;
	private String biCcn;
	private String biEmplazamiento;
	private String biNombreNodo;
	

	

	/**
	 * @return
	 */
	public Long getActId() {
		return actId;
	}

	/**
	 * @return
	 */
	public Long getAgenId() {
		return agenId;
	}

	/**
	 * @return
	 */
	public Long getApId() {
		return apId;
	}

	/**
	 * @return
	 */
	public String getBiCcn() {
		return biCcn;
	}

	/**
	 * @return
	 */
	public String getBiClienteApellidos() {
		return biClienteApellidos;
	}

	/**
	 * @return
	 */
	public String getBiClienteArea() {
		return biClienteArea;
	}

	/**
	 * @return
	 */
	public String getBiClienteNombre() {
		return biClienteNombre;
	}

	/**
	 * @return
	 */
	public String getBiClienteRut() {
		return biClienteRut;
	}

	/**
	 * @return
	 */
	public String getBiClienteRutDv() {
		return biClienteRutDv;
	}

	/**
	 * @return
	 */
	public String getBiClienteServicio() {
		return biClienteServicio;
	}

	/**
	 * @return
	 */
	public String getBiEmplazamiento() {
		return biEmplazamiento;
	}

	/**
	 * @return
	 */
	public Integer getBiEstadoPeticion() {
		return biEstadoPeticion;
	}

	/**
	 * @return
	 */
	public String getBiFamiliaPs() {
		return biFamiliaPs;
	}

	/**
	 * @return
	 */
	public Timestamp getBiFechaApertura() {
		return biFechaApertura;
	}

	/**
	 * @return
	 */
	public Timestamp getBiFechaAsignacion() {
		return biFechaAsignacion;
	}

	/**
	 * @return
	 */
	public Timestamp getBiFechaCompromiso() {
		return biFechaCompromiso;
	}

	/**
	 * @return
	 */
	public Timestamp getBiFechaInicio() {
		return biFechaInicio;
	}

	/**
	 * @return
	 */
	public Long getBiGrupoSegmento() {
		return biGrupoSegmento;
	}

	/**
	 * @return
	 */
	public String getBiHoraDesde() {
		return biHoraDesde;
	}

	/**
	 * @return
	 */
	public String getBiHoraHasta() {
		return biHoraHasta;
	}

	/**
	 * @return
	 */
	public Long getBiId() {
		return biId;
	}

	/**
	 * @return
	 */
	public Integer getBiIdRango() {
		return biIdRango;
	}

	/**
	 * @return
	 */
	public Long getBiIdTipoAgendamiento() {
		return biIdTipoAgendamiento;
	}

	/**
	 * @return
	 */
	public String getBiNombreNodo() {
		return biNombreNodo;
	}

	/**
	 * @return
	 */
	public Long getBiNroPeticion() {
		return biNroPeticion;
	}

	/**
	 * @return
	 */
	public String getBiPcom() {
		return biPcom;
	}

	/**
	 * @return
	 */
	public Integer getBiPuntaje() {
		return biPuntaje;
	}

	/**
	 * @return
	 */
	public Integer getBiTipoTrabajo() {
		return biTipoTrabajo;
	}

	/**
	 * @return
	 */
	public String getBiUrlDetalle() {
		return biUrlDetalle;
	}

	/**
	 * @return
	 */
	public Integer getBiVisible() {
		return biVisible;
	}

	/**
	 * @return
	 */
	public Long getRolId() {
		return rolId;
	}

	/**
	 * @return
	 */
	public Long getSegmId() {
		return segmId;
	}

	/**
	 * @return
	 */
	public String getTicaId() {
		return ticaId;
	}

	/**
	 * @return
	 */
	public Long getUsuaId() {
		return usuaId;
	}

	/**
	 * @param long1
	 */
	public void setActId(Long long1) {
		actId = long1;
	}

	/**
	 * @param long1
	 */
	public void setAgenId(Long long1) {
		agenId = long1;
	}

	/**
	 * @param long1
	 */
	public void setApId(Long long1) {
		apId = long1;
	}

	/**
	 * @param string
	 */
	public void setBiCcn(String string) {
		biCcn = string;
	}

	/**
	 * @param string
	 */
	public void setBiClienteApellidos(String string) {
		biClienteApellidos = string;
	}

	/**
	 * @param string
	 */
	public void setBiClienteArea(String string) {
		biClienteArea = string;
	}

	/**
	 * @param string
	 */
	public void setBiClienteNombre(String string) {
		biClienteNombre = string;
	}

	/**
	 * @param string
	 */
	public void setBiClienteRut(String string) {
		biClienteRut = string;
	}

	/**
	 * @param string
	 */
	public void setBiClienteRutDv(String string) {
		biClienteRutDv = string;
	}

	/**
	 * @param string
	 */
	public void setBiClienteServicio(String string) {
		biClienteServicio = string;
	}

	/**
	 * @param string
	 */
	public void setBiEmplazamiento(String string) {
		biEmplazamiento = string;
	}

	/**
	 * @param integer
	 */
	public void setBiEstadoPeticion(Integer integer) {
		biEstadoPeticion = integer;
	}

	/**
	 * @param string
	 */
	public void setBiFamiliaPs(String string) {
		biFamiliaPs = string;
	}

	/**
	 * @param timestamp
	 */
	public void setBiFechaApertura(Timestamp timestamp) {
		biFechaApertura = timestamp;
	}

	/**
	 * @param timestamp
	 */
	public void setBiFechaAsignacion(Timestamp timestamp) {
		biFechaAsignacion = timestamp;
	}

	/**
	 * @param timestamp
	 */
	public void setBiFechaCompromiso(Timestamp timestamp) {
		biFechaCompromiso = timestamp;
	}

	/**
	 * @param timestamp
	 */
	public void setBiFechaInicio(Timestamp timestamp) {
		biFechaInicio = timestamp;
	}

	/**
	 * @param long1
	 */
	public void setBiGrupoSegmento(Long long1) {
		biGrupoSegmento = long1;
	}

	/**
	 * @param string
	 */
	public void setBiHoraDesde(String string) {
		biHoraDesde = string;
	}

	/**
	 * @param string
	 */
	public void setBiHoraHasta(String string) {
		biHoraHasta = string;
	}

	/**
	 * @param long1
	 */
	public void setBiId(Long long1) {
		biId = long1;
	}

	/**
	 * @param integer
	 */
	public void setBiIdRango(Integer integer) {
		biIdRango = integer;
	}

	/**
	 * @param long1
	 */
	public void setBiIdTipoAgendamiento(Long long1) {
		biIdTipoAgendamiento = long1;
	}

	/**
	 * @param string
	 */
	public void setBiNombreNodo(String string) {
		biNombreNodo = string;
	}

	/**
	 * @param long1
	 */
	public void setBiNroPeticion(Long long1) {
		biNroPeticion = long1;
	}

	/**
	 * @param string
	 */
	public void setBiPcom(String string) {
		biPcom = string;
	}

	/**
	 * @param integer
	 */
	public void setBiPuntaje(Integer integer) {
		biPuntaje = integer;
	}

	/**
	 * @param integer
	 */
	public void setBiTipoTrabajo(Integer integer) {
		biTipoTrabajo = integer;
	}

	/**
	 * @param string
	 */
	public void setBiUrlDetalle(String string) {
		biUrlDetalle = string;
	}

	/**
	 * @param integer
	 */
	public void setBiVisible(Integer integer) {
		biVisible = integer;
	}

	/**
	 * @param long1
	 */
	public void setRolId(Long long1) {
		rolId = long1;
	}

	/**
	 * @param long1
	 */
	public void setSegmId(Long long1) {
		segmId = long1;
	}

	/**
	 * @param string
	 */
	public void setTicaId(String string) {
		ticaId = string;
	}

	/**
	 * @param long1
	 */
	public void setUsuaId(Long long1) {
		usuaId = long1;
	}

}
