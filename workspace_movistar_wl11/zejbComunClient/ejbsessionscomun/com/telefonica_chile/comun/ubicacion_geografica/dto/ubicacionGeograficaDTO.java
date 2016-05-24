/*
 * Created on Nov 22, 2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.telefonica_chile.comun.ubicacion_geografica.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author cegatic
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ubicacionGeograficaDTO implements Serializable{
	
	private Long idUbicacionGeografica = null;
	private String calle = null;
	private String dica = null;
	private String numero = null;
	private String latitud = null;
	private String longitud = null;
	private BigDecimal altura = null;
	private Integer idComuna = null;
	private String comunaDescrip = null;
	private String ciudadNombre = null;
	private Integer tipoViaId = null;
	private String tipoViaDescrip = null;
	private String comunaProvinciaDescrip = null;
	private String comunaProvinciaRegionDescrip = null;
	private Integer comunaIdProvincia = null;

	/**
	 * @return
	 */
	public String getCalle() {
		return calle;
	}

	/**
	 * @return
	 */
	public String getDica() {
		return dica;
	}

	/**
	 * @return
	 */
	public Long getIdUbicacionGeografica() {
		return idUbicacionGeografica;
	}

	/**
	 * @return
	 */
	public String getLatitud() {
		return latitud;
	}

	/**
	 * @return
	 */
	public String getLongitud() {
		return longitud;
	}

	/**
	 * @return
	 */
	public String getNumero() {
		return numero;
	}

	/**
	 * @param string
	 */
	public void setCalle(String string) {
		calle = string;
	}

	/**
	 * @param string
	 */
	public void setDica(String string) {
		dica = string;
	}

	/**
	 * @param long1
	 */
	public void setIdUbicacionGeografica(Long long1) {
		idUbicacionGeografica = long1;
	}

	/**
	 * @param string
	 */
	public void setLatitud(String string) {
		latitud = string;
	}

	/**
	 * @param string
	 */
	public void setLongitud(String string) {
		longitud = string;
	}

	/**
	 * @param string
	 */
	public void setNumero(String string) {
		numero = string;
	}

	/**
	 * @return
	 */
	public String getCiudadNombre() {
		return ciudadNombre;
	}

	/**
	 * @return
	 */
	public String getComunaDescrip() {
		return comunaDescrip;
	}

	/**
	 * @param string
	 */
	public void setCiudadNombre(String string) {
		ciudadNombre = string;
	}

	/**
	 * @param string
	 */
	public void setComunaDescrip(String string) {
		comunaDescrip = string;
	}

	/**
	 * @return
	 */
	public String getTipoViaDescrip() {
		return tipoViaDescrip;
	}

	/**
	 * @param string
	 */
	public void setTipoViaDescrip(String string) {
		tipoViaDescrip = string;
	}

	/**
	 * @return
	 */
	public String getComunaProvinciaDescrip() {
		return comunaProvinciaDescrip;
	}

	/**
	 * @param string
	 */
	public void setComunaProvinciaDescrip(String string) {
		comunaProvinciaDescrip = string;
	}

	/**
	 * @return
	 */
	public Integer getIdComuna() {
		return idComuna;
	}

	/**
	 * @param long1
	 */
	public void setIdComuna(Integer integer1) {
		idComuna = integer1;
	}

	/**
	 * @return
	 */
	public BigDecimal getAltura() {
		return altura;
	}

	/**
	 * @param string
	 */
	public void setAltura(BigDecimal bigDecimal) {
		altura = bigDecimal;
	}

	/**
	 * @return
	 */
	public Integer getComunaIdProvincia() {
		return comunaIdProvincia;
	}

	/**
	 * @param integer
	 */
	public void setComunaIdProvincia(Integer integer) {
		comunaIdProvincia = integer;
	}

	/**
	 * @return
	 */
	public String getComunaProvinciaRegionDescrip() {
		return comunaProvinciaRegionDescrip;
	}

	/**
	 * @param string
	 */
	public void setComunaProvinciaRegionDescrip(String string) {
		comunaProvinciaRegionDescrip = string;
	}

	/**
	 * @return
	 */
	public Integer getTipoViaId() {
		return tipoViaId;
	}

	/**
	 * @param long1
	 */
	public void setTipoViaId(Integer integer1) {
		tipoViaId = integer1;
	}

}
