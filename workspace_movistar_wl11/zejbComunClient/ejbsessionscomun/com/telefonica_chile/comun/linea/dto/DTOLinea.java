/*
 * Created on Feb 24, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.telefonica_chile.comun.linea.dto;

import java.io.Serializable;

/**
 * @author randreu
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class DTOLinea implements Serializable{
	
	private Long idLinea;
	private Long idRure;
	private Integer codComuna;
	private Long codCliente;
	private Double codigoArea;
	private Double numero;
	private String tipoLinea;
    private String dirCalle;
    private String dirDpto;
    private String dirPiso;
	private String dirCodigoPostal;
    private String dirEntreCalles;
    private String dirTipoVivienda;
    private String dirNumero;
    private Long centralConmutacion;
    private String areaLogico1;
	private String fonoLogico1;
	private String areaLogico2;
	private String fonoLogico2;
	private Long idPlco;
	private Long idTerr;
	private String dirComuna;
	private String dirCiudad;
	private Long idCeco;
	private String central;
	private String codigoRural;
	private String noPublicar;
	private String distribucionCuenta;
	private Long idZona;
	private boolean mensajeCorto;    	

	private String areaPagador;
	private String fonoPagador;
	private String numeroCuenta;
	private Double numeroNuevo;
	
	/**
	 * @return
	 */
	public String getAreaLogico1() {
		return areaLogico1;
	}

	/**
	 * @return
	 */
	public String getAreaLogico2() {
		return areaLogico2;
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
	public Integer getCodComuna() {
		return codComuna;
	}

	

	/**
	 * @return
	 */
	public String getDirCalle() {
		return dirCalle;
	}

	/**
	 * @return
	 */
	public String getDirCodigoPostal() {
		return dirCodigoPostal;
	}

	/**
	 * @return
	 */
	public String getDirDpto() {
		return dirDpto;
	}

	/**
	 * @return
	 */
	public String getDirEntreCalles() {
		return dirEntreCalles;
	}

	/**
	 * @return
	 */
	public String getDirNumero() {
		return dirNumero;
	}

	/**
	 * @return
	 */
	public String getDirPiso() {
		return dirPiso;
	}

	/**
	 * @return
	 */
	public String getDirTipoVivienda() {
		return dirTipoVivienda;
	}

	/**
	 * @return
	 */
	public String getFonoLogico1() {
		return fonoLogico1;
	}

	/**
	 * @return
	 */
	public String getFonoLogico2() {
		return fonoLogico2;
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
	public Long getIdPlco() {
		return idPlco;
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
	public Long getIdTerr() {
		return idTerr;
	}

	/**
	 * @return
	 */
	public Double getNumero() {
		return numero;
	}

	/**
	 * @param string
	 */
	public void setAreaLogico1(String string) {
		areaLogico1 = string;
	}

	/**
	 * @param string
	 */
	public void setAreaLogico2(String string) {
		areaLogico2 = string;
	}

	/**
	 * @param long1
	 */
	public void setCodCliente(Long long1) {
		codCliente = long1;
	}

	/**
	 * @param integer
	 */
	public void setCodComuna(Integer integer) {
		codComuna = integer;
	}


	/**
	 * @param string
	 */
	public void setDirCalle(String string) {
		dirCalle = string;
	}

	/**
	 * @param string
	 */
	public void setDirCodigoPostal(String string) {
		dirCodigoPostal = string;
	}

	/**
	 * @param string
	 */
	public void setDirDpto(String string) {
		dirDpto = string;
	}

	/**
	 * @param string
	 */
	public void setDirEntreCalles(String string) {
		dirEntreCalles = string;
	}

	/**
	 * @param string
	 */
	public void setDirNumero(String string) {
		dirNumero = string;
	}

	/**
	 * @param string
	 */
	public void setDirPiso(String string) {
		dirPiso = string;
	}

	/**
	 * @param string
	 */
	public void setDirTipoVivienda(String string) {
		dirTipoVivienda = string;
	}

	/**
	 * @param string
	 */
	public void setFonoLogico1(String string) {
		fonoLogico1 = string;
	}

	/**
	 * @param string
	 */
	public void setFonoLogico2(String string) {
		fonoLogico2 = string;
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
	public void setIdPlco(Long long1) {
		idPlco = long1;
	}

	/**
	 * @param long1
	 */
	public void setIdRure(Long long1) {
		idRure = long1;
	}

	/**
	 * @param long1
	 */
	public void setIdTerr(Long long1) {
		idTerr = long1;
	}

	/**
	 * @param double1
	 */
	public void setNumero(Double double1) {
		numero = double1;
	}

	/**
	 * @return
	 */
	public String getDirCiudad() {
		return dirCiudad;
	}

	/**
	 * @return
	 */
	public String getDirComuna() {
		return dirComuna;
	}

	/**
	 * @param string
	 */
	public void setDirCiudad(String string) {
		dirCiudad = string;
	}

	/**
	 * @param string
	 */
	public void setDirComuna(String string) {
		dirComuna = string;
	}

	/**
	 * @return
	 */
	public Long getIdCeco() {
		return idCeco;
	}

	/**
	 * @param long1
	 */
	public void setIdCeco(Long long1) {
		idCeco = long1;
	}

	/**
	 * @return
	 */
	public String getCentral() {
		return central;
	}

	/**
	 * @param string
	 */
	public void setCentral(String string) {
		central = string;
	}

	/**
	 * @return
	 */
	public Long getCentralConmutacion() {
		return centralConmutacion;
	}

	/**
	 * @param long1
	 */
	public void setCentralConmutacion(Long long1) {
		centralConmutacion = long1;
	}

	/**
	 * @return
	 */
	public String getCodigoRural() {
		return codigoRural;
	}

	/**
	 * @return
	 */
	public String getDistribucionCuenta() {
		return distribucionCuenta;
	}

	/**
	 * @return
	 */
	public String getNoPublicar() {
		return noPublicar;
	}

	/**
	 * @param string
	 */
	public void setCodigoRural(String string) {
		codigoRural = string;
	}

	/**
	 * @param string
	 */
	public void setDistribucionCuenta(String string) {
		distribucionCuenta = string;
	}

	/**
	 * @param string
	 */
	public void setNoPublicar(String string) {
		noPublicar = string;
	}

	/**
	 * @return
	 */
	public Long getIdZona() {
		return idZona;
	}

	/**
	 * @param long1
	 */
	public void setIdZona(Long long1) {
		idZona = long1;
	}

	
	/**
	 * @return
	 */
	public Double getCodigoArea() {
		return codigoArea;
	}

	/**
	 * @param double1
	 */
	public void setCodigoArea(Double double1) {
		codigoArea = double1;
	}

	/**
	 * @return
	 */
	public boolean isMensajeCorto() {
		return mensajeCorto;
	}

	/**
	 * @param b
	 */
	public void setMensajeCorto(boolean b) {
		mensajeCorto = b;
	}

	/**
	 * @return
	 */
	public String getTipoLinea() {
		return tipoLinea;
	}

	/**
	 * @param string
	 */
	public void setTipoLinea(String string) {
		tipoLinea = string;
	}

	/**
	 * @return
	 */
	public String getAreaPagador() {
		return areaPagador;
	}

	/**
	 * @return
	 */
	public String getFonoPagador() {
		return fonoPagador;
	}

	/**
	 * @return
	 */
	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	/**
	 * @param string
	 */
	public void setAreaPagador(String string) {
		areaPagador = string;
	}

	/**
	 * @param string
	 */
	public void setFonoPagador(String string) {
		fonoPagador = string;
	}

	/**
	 * @param string
	 */
	public void setNumeroCuenta(String string) {
		numeroCuenta = string;
	}

	
	/**
	 * @return
	 */
	public Double getNumeroNuevo() {
		return numeroNuevo;
	}

	/**
	 * @param double1
	 */
	public void setNumeroNuevo(Double double1) {
		numeroNuevo = double1;
	}

}

