/*
 * Created on 09-02-2007
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package co.com.telefonica.atiempo.soltec.dto;

import java.util.ArrayList;
import java.util.Date;

import co.com.telefonica.atiempo.utiles.DataTransferObject;

/**
 * @author TCS
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class InformacionAgendamientoDTO implements DataTransferObject {
	
	private String cdpeticion;

	private String horaDesde;
	private String horaHasta;
	private Date fechaCompromiso;
	private Long idTipoAgenda;
	private Integer idRango;
	private String nombreTipoAgenda;
	private String nombreRango;
	
	// CR21938 - ana santos - inicio
	private String nomUsuario;
	private Date fechaAsignacion;
	// CR21938 - ana santos - fin

	private String descripcionEstado;
	private String descripcionCausa;
	private String codSegmento;
	private String codDepto;
	private String codLocalidad;
	private String codCentral;
	private String tipoTrabajo;
	
	private Long idContratista = null;
	private String idTecnicoInicial = null;
	private String idTecnicoFinal = null;
	private String nombreContratista = null;
	private String nombreTecnicoInicial = null;
	private String nombreTecnicoFinal= null;

	private ArrayList listaPS = new ArrayList();


	/**
	 * @return
	 */
	public void addPs(String codPs) {
		listaPS.add( codPs );
	}

	/**
	 * @return
	 */
	public String getCdpeticion() {
		return cdpeticion;
	}

	/**
	 * @return
	 */
	public String getCodCentral() {
		return codCentral;
	}

	/**
	 * @return
	 */
	public String getCodDepto() {
		return codDepto;
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
	public String getCodSegmento() {
		return codSegmento;
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
	public String getHoraDesde() {
		return horaDesde;
	}

	/**
	 * @return
	 */
	public String getHoraHasta() {
		return horaHasta;
	}

	/**
	 * @return
	 */
	public Long getIdTipoAgenda() {
		return idTipoAgenda;
	}

	/**
	 * @return
	 */
	public Integer getIdRango() {
		return idRango;
	}

	/**
	 * @return
	 */
	public ArrayList getListaPS() {
		return listaPS;
	}

	/**
	 * @return
	 */
	public String getNombreRango() {
		return nombreRango;
	}

	/**
	 * @return
	 */
	public String getNombreTipoAgenda() {
		return nombreTipoAgenda;
	}

	/**
	 * @return
	 */
	public String getTipoTrabajo() {
		return tipoTrabajo;
	}

	/**
	 * @param string
	 */
	public void setCdpeticion(String string) {
		cdpeticion = string;
	}

	/**
	 * @param string
	 */
	public void setCodCentral(String string) {
		codCentral = string;
	}

	/**
	 * @param string
	 */
	public void setCodDepto(String string) {
		codDepto = string;
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
	public void setCodSegmento(String string) {
		codSegmento = string;
	}

	/**
	 * @param date
	 */
	public void setFechaCompromiso(Date date) {
		fechaCompromiso = date;
	}

	/**
	 * @param string
	 */
	public void setHoraDesde(String string) {
		horaDesde = string;
	}

	/**
	 * @param string
	 */
	public void setHoraHasta(String string) {
		horaHasta = string;
	}

	/**
	 * @param long1
	 */
	public void setIdTipoAgenda(Long long1) {
		idTipoAgenda = long1;
	}

	/**
	 * @param integer
	 */
	public void setIdRango(Integer integer) {
		idRango = integer;
	}

	/**
	 * @param list
	 */
	public void setListaPS(ArrayList list) {
		listaPS = list;
	}

	/**
	 * @param string
	 */
	public void setNombreRango(String string) {
		nombreRango = string;
	}

	/**
	 * @param string
	 */
	public void setNombreTipoAgenda(String string) {
		nombreTipoAgenda = string;
	}

	/**
	 * @param string
	 */
	public void setTipoTrabajo(String string) {
		tipoTrabajo = string;
	}

	/**
	 * @return
	 */
	public Long getIdContratista() {
		return idContratista;
	}

	/**
	 * @return
	 */
	public String getIdTecnicoFinal() {
		return idTecnicoFinal;
	}

	/**
	 * @return
	 */
	public String getIdTecnicoInicial() {
		return idTecnicoInicial;
	}


	/**
	 * @return
	 */
	public String getNombreTecnicoFinal() {
		return nombreTecnicoFinal;
	}

	/**
	 * @return
	 */
	public String getNombreTecnicoInicial() {
		return nombreTecnicoInicial;
	}

	/**
	 * @param long1
	 */
	public void setIdContratista(Long long1) {
		idContratista = long1;
	}

	/**
	 * @param long1
	 */
	public void setIdTecnicoFinal(String str1) {
		idTecnicoFinal = str1;
	}

	/**
	 * @param long1
	 */
	public void setIdTecnicoInicial(String str1) {
		idTecnicoInicial = str1;
	}


	/**
	 * @param long1
	 */
	public void setNombreTecnicoFinal(String str1) {
		nombreTecnicoFinal = str1;
	}

	/**
	 * @param long1
	 */
	public void setNombreTecnicoInicial(String str1) {
		nombreTecnicoInicial = str1;
	}

	/**
	 * @return
	 */
	public String getNombreContratista() {
		return nombreContratista;
	}

	/**
	 * @param string
	 */
	public void setNombreContratista(String string) {
		nombreContratista = string;
	}

	/**
	 * @return
	 */
	public String getDescripcionCausa() {
		return descripcionCausa;
	}

	/**
	 * @return
	 */
	public String getDescripcionEstado() {
		return descripcionEstado;
	}

	/**
	 * @param string
	 */
	public void setDescripcionCausa(String string) {
		descripcionCausa = string;
	}

	/**
	 * @param string
	 */
	public void setDescripcionEstado(String string) {
		descripcionEstado = string;
	}

	// CR21938 - ana santos - inicio
	public Date getFechaAsignacion() {
		return fechaAsignacion;
	}
	/**
	 * @param fechaAsignacion The fechaAsignacion to set.
	 */
	public void setFechaAsignacion(Date fechaAsignacion) {
		this.fechaAsignacion = fechaAsignacion;
	}
	/**
	 * @return Returns the usuario.
	 */
	public String getNomUsuario() {
		return nomUsuario;
	}
	/**
	 * @param usuario The usuario to set.
	 */
	public void setUsuario(String nomUsuario) {
		this.nomUsuario = nomUsuario;
	}	
	// CR21938 - ana santos - fin

}
