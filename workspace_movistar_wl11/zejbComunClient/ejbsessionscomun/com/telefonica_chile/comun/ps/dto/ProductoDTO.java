/*
 * Created on Nov 30, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.telefonica_chile.comun.ps.dto;

import java.io.Serializable;


/**
 * @author rbpizar
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ProductoDTO  implements Serializable{

	/**
	 * 
	 */
	public ProductoDTO()
	{
		llamaAlaActividad=false;
		estaOK=false;
	}
	
	private Long petiNumero;
	private Long correlativo;
	private Long idProducto;
	private Long idEmpresa;
	private Long idFaps;
	private String nombreProducto = "";
	private String observProducto = "";
	private String tipoTrabajo = "";
	private String operacionComercial = "";
	private int idHabilidad;
	private String descripcion = "";
	private String Observacion = "";
	private Byte obligatorio;
	private Byte variacion;
	
	private Integer cantidad;
	private Long id;
	private int estadoVariacion;
	private int realizado;
	private Integer idAmbito;

	private String nombreFamiliaPS;
	private Integer prioridadFamiliaPS;
	private String codigoFamiliaPS; 	
	private String  tipoOperacionComercial;
	private String trasladoOperCom;
	
	private Long idOpComercial;
	
	private Byte pcoObligatorio;
	private Long idGrupoProducto;
	
	//Campos para los ps tipo PC
	private String descTipo;
	private String descSubTipo;
	private String descLocalidad;
	private String descSubLocalidad;
	
	//Campos de Direcion, Departamento y Localidad
	private String codLocalidad;
	private String codDepartamento;
	private String descDepartamento;
	private String direccion;
	
	private String fechaCompromiso;
	private boolean tipoPC;
	private Integer valTipoPc;
	private String identificadorProducto;
	private boolean llamaAlaActividad;
	private boolean estaOK;
	
	private Long codCausal;
	private String descCausal;
	private String observacionCausal;

	/**
	 * @return
	 */
	public String getNombreProducto() {
		return nombreProducto;
	}



	/**
	 * @param string
	 */
	public void setNombreProducto(String string) {
		nombreProducto = string;
	}

	/**
	 * @return
	 */
	public String getObservProducto() {
		return observProducto;
	}

	/**
	 * @return
	 */
	public String getOperacionComercial() {
		return operacionComercial;
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
	public void setObservProducto(String string) {
		observProducto = string;
	}

	/**
	 * @param string
	 */
	public void setOperacionComercial(String string) {
		operacionComercial = string;
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
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param string
	 */
	public void setDescripcion(String string) {
		descripcion = string;
	}

	/**
	 * @return
	 */
	public Long getIdProducto() {
		return idProducto;
	}

	/**
	 * @param long1
	 */
	public void setIdProducto(Long long1) {
		idProducto = long1;
	}


	/**
	 * @return
	 */
	public int getIdHabilidad() {
		return idHabilidad;
	}

	/**
	 * @param i
	 */
	public void setIdHabilidad(int i) {
		idHabilidad = i;
	}

	/**
	 * @return
	 */
	public String getObservacion() {
		return Observacion;
	}

	/**
	 * @param string
	 */
	public void setObservacion(String string) {
		Observacion = string;
	}

	/**
	 * @return
	 */
	public Long getIdFaps() {
		return idFaps;
	}

	/**
	 * @param long1
	 */
	public void setIdFaps(Long long1) {
		idFaps = long1;
	}

	/**
	 * @return
	 */
	public Long getIdEmpresa() {
		return idEmpresa;
	}

	/**
	 * @param long1
	 */
	public void setIdEmpresa(Long long1) {
		idEmpresa = long1;
	}


	/**
	 * @return
	 */
	public Byte getVariacion() {
		return variacion;
	}

	/**
	 * @param byte1
	 */
	public void setVariacion(Byte byte1) {
		variacion = byte1;
	}

	/**
	 * @return
	 */
	public Byte getObligatorio() {
		return obligatorio;
	}

	/**
	 * @param byte1
	 */
	public void setObligatorio(Byte byte1) {
		obligatorio = byte1;
	}
	
	/**
	 * @return
	 */
	public Integer getCantidad() {
		return cantidad;
	}

	/**
	 * @param integer
	 */
	public void setCantidad(Integer integer) {
		cantidad = integer;
	}

	/**
	 * @return
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param long1
	 */
	public void setId(Long long1) {
		id = long1;
	}

	/**
	 * @return
	 */
	public int getEstadoVariacion() {
		return estadoVariacion;
	}

	/**
	 * @param i
	 */
	public void setEstadoVariacion(int i) {
		estadoVariacion = i;
	}

	/**
	 * @return
	 */
	public Integer getIdAmbito() {
		return idAmbito;
	}

	/**
	 * @param integer
	 */
	public void setIdAmbito(Integer integer) {
		idAmbito = integer;
	}

	/**
	 * @return
	 */
	public int getRealizado() {
		return realizado;
	}

	/**
	 * @param i
	 */
	public void setRealizado(int i) {
		realizado = i;
	}

	/**
	 * @return
	 */
	public String getCodigoFamiliaPS() {
		return codigoFamiliaPS;
	}

	/**
	 * @return
	 */
	public String getNombreFamiliaPS() {
		return nombreFamiliaPS;
	}

	/**
	 * @return
	 */
	public Integer getPrioridadFamiliaPS() {
		return prioridadFamiliaPS;
	}

	/**
	 * @param string
	 */
	public void setCodigoFamiliaPS(String string) {
		codigoFamiliaPS = string;
	}

	/**
	 * @param string
	 */
	public void setNombreFamiliaPS(String string) {
		nombreFamiliaPS = string;
	}

	/**
	 * @param integer
	 */
	public void setPrioridadFamiliaPS(Integer integer) {
		prioridadFamiliaPS = integer;
	}

	/**
	 * @return
	 */
	public String getTipoOperacionComercial() {
		return tipoOperacionComercial;
	}

	/**
	 * @param string
	 */
	public void setTipoOperacionComercial(String string) {
		tipoOperacionComercial = string;
	}

	/**
	 * @return
	 */
	public Long getIdOpComercial() {
		return idOpComercial;
	}

	/**
	 * @param long1
	 */
	public void setIdOpComercial(Long long1) {
		idOpComercial = long1;
	}

	/**
	 * @return
	 */
	public Byte getPcoObligatorio() {
		return pcoObligatorio;
	}

	/**
	 * @param byte1
	 */
	public void setPcoObligatorio(Byte byte1) {
		pcoObligatorio = byte1;
	}

	/**
	 * @return
	 */
	public Long getIdGrupoProducto() {
		return idGrupoProducto;
	}

	/**
	 * @param long1
	 */
	public void setIdGrupoProducto(Long long1) {
		idGrupoProducto = long1;
	}

	/**
	 * @return
	 */
	public String getDescLocalidad() {
		return descLocalidad;
	}

	/**
	 * @return
	 */
	public String getDescSubLocalidad() {
		return descSubLocalidad;
	}

	/**
	 * @return
	 */
	public String getDescSubTipo() {
		return descSubTipo;
	}

	/**
	 * @return
	 */
	public String getDescTipo() {
		return descTipo;
	}

	/**
	 * @param string
	 */
	public void setDescLocalidad(String string) {
		descLocalidad = string;
	}

	/**
	 * @param string
	 */
	public void setDescSubLocalidad(String string) {
		descSubLocalidad = string;
	}

	/**
	 * @param string
	 */
	public void setDescSubTipo(String string) {
		descSubTipo = string;
	}

	/**
	 * @param string
	 */
	public void setDescTipo(String string) {
		descTipo = string;
	}

	/**
	 * @return
	 */
	public boolean isTipoPC() {
		return tipoPC;
	}

	/**
	 * @param b
	 */
	public void setTipoPC(boolean b) {
		tipoPC = b;
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
	public String getDireccion() {
		return direccion;
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
	public void setDireccion(String string) {
		direccion = string;
	}

	/**
	 * @return
	 */
	public String getDescDepartamento() {
		return descDepartamento;
	}

	/**
	 * @param string
	 */
	public void setDescDepartamento(String string) {
		descDepartamento = string;
	}

	/**
	 * @return
	 */
	public String getFechaCompromiso() {
		return fechaCompromiso;
	}

	/**
	 * @param string
	 */
	public void setFechaCompromiso(String string) {
		fechaCompromiso = string;
	}

	/**
	 * @return
	 */
	public Integer getValTipoPc() {
		return valTipoPc;
	}

	/**
	 * @param integer
	 */
	public void setValTipoPc(Integer integer) {
		valTipoPc = integer;
	}

	/**
	 * @return
	 */
	public String getIdentificadorProducto() {
		return identificadorProducto;
	}

	/**
	 * @param string
	 */
	public void setIdentificadorProducto(String string) {
		identificadorProducto = string;
	}

	/**
	 * @return
	 */
	public Long getCorrelativo() {
		return correlativo;
	}

	/**
	 * @return
	 */
	public Long getPetiNumero() {
		return petiNumero;
	}

	/**
	 * @param long1
	 */
	public void setCorrelativo(Long long1) {
		correlativo = long1;
	}

	/**
	 * @param long1
	 */
	public void setPetiNumero(Long long1) {
		petiNumero = long1;
	}

	/**
	 * @return
	 */
	public boolean isLlamaAlaActividad() {
		return llamaAlaActividad;
	}

	/**
	 * @param b
	 */
	public void setLlamaAlaActividad(boolean b) {
		llamaAlaActividad = b;
	}

	/**
	 * @return
	 */
	public boolean isEstaOK() {
		return estaOK;
	}

	/**
	 * @param b
	 */
	public void setEstaOK(boolean b) {
		estaOK = b;
	}

	/**
	 * @return
	 */
	public Long getCodCausal() {
		return codCausal;
	}

	/**
	 * @return
	 */
	public String getDescCausal() {
		return descCausal;
	}

	/**
	 * @param long1
	 */
	public void setCodCausal(Long long1) {
		codCausal = long1;
	}

	/**
	 * @param string
	 */
	public void setDescCausal(String string) {
		descCausal = string;
	}

	/**
	 * @return
	 */
	public String getTrasladoOperCom() {
		return trasladoOperCom;
	}

	/**
	 * @param string
	 */
	public void setTrasladoOperCom(String string) {
		trasladoOperCom = string;
	}

	/**
	 * @return
	 */
	public String getObservacionCausal() {
		return observacionCausal;
	}

	/**
	 * @param string
	 */
	public void setObservacionCausal(String string) {
		observacionCausal = string;
	}
//	CR9664 - adocarmo - inicio
	public boolean equals(Object obj)
	{
		if ((! (obj instanceof ProductoDTO)) || (obj == null))
		   return (false) ;

		ProductoDTO otro = (ProductoDTO) obj ;
		if(idProducto==null || otro.getIdProducto()==null)
			return false;
		return (idProducto.equals(otro.getIdProducto()));
}

//	CR9664 - adocarmo - fin
}
