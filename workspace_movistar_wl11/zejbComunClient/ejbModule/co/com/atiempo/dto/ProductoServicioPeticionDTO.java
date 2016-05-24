/*
 * Created on 09-03-2007
 */
package co.com.atiempo.dto;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;

/**
 * @author Respinoza
 */
public class ProductoServicioPeticionDTO {

	private Long petiNumero;
	private Long psId;
	private Long opcoId;
	private String numIdeNu;
	private Integer pspeCantidad;
	private Long pspeCargoInstalacion;
	private Long pspeRentaMensual;
	private Integer pspeTgenNodo;
	private Integer pspeTgenServMsu;
	private String pspeCodigoFamilia;
	private Timestamp pspeFechaInicio;
	private Timestamp pspeFechaFin;
	private String nomProSerNo;
	private String obsSubDs;
	private Long tipProSerCd;
	private Long correlativo;
	private Long codPetCd;
	private Integer codAgrSubNu;
	private Integer codSubCd;
	private Long codTipUso;
	private Long tipProCmrCd;
	private Long stbProCmrCd;
	private Long IdCausaAsociada;
	private ArrayList listaCausales;
	private String nombrePS;
	private String descOperComer;
	private boolean resolutor;
	private boolean llamaALaActividad;
	private boolean estaOK;
	private Long psIdFam;
	private String psDesFam;
	
	private String IdProductoAnt;
	private String nombreProductoAnt;
	private String tipoPeticion;
	
	//req 568, david m.
	private boolean quiebrePrevioAPGI;
	
	/**
	 * @return Devuelve quiebrePrevioAPGI.
	 */
	public boolean isQuiebrePrevioAPGI() {
		return quiebrePrevioAPGI;
	}
	/**
	 * @param quiebrePrevioAPGI El quiebrePrevioAPGI a establecer.
	 */
	public void setQuiebrePrevioAPGI(boolean quiebrePrevioAPGI) {
		this.quiebrePrevioAPGI = quiebrePrevioAPGI;
	}
	//	fin req 568, david m.
	public ProductoServicioPeticionDTO()
	{
		listaCausales=new ArrayList();
		resolutor=false;
		llamaALaActividad=false;
		estaOK=false;
	}

	/**
	 * @return
	 */
	public Integer getCodAgrSubNu() {
		return codAgrSubNu;
	}

	/**
	 * @return
	 */
	public Long getCodPetCd() {
		return codPetCd;
	}

	/**
	 * @return
	 */
	public Integer getCodSubCd() {
		return codSubCd;
	}

	/**
	 * @return
	 */
	public Long getCodTipUso() {
		return codTipUso;
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
	public String getNomProSerNo() {
		return nomProSerNo;
	}

	/**
	 * @return
	 */
	public String getNumIdeNu() {
		return numIdeNu;
	}

	/**
	 * @return
	 */
	public String getObsSubDs() {
		return obsSubDs;
	}

	/**
	 * @return
	 */
	public Long getOpcoId() {
		return opcoId;
	}

	/**
	 * @return
	 */
	public Long getPetiNumero() {
		return petiNumero;
	}

	/**
	 * @return
	 */
	public Long getPsId() {
		return psId;
	}

	/**
	 * @return
	 */
	public Integer getPspeCantidad() {
		return pspeCantidad;
	}

	/**
	 * @return
	 */
	public Long getPspeCargoInstalacion() {
		return pspeCargoInstalacion;
	}

	/**
	 * @return
	 */
	public String getPspeCodigoFamilia() {
		return pspeCodigoFamilia;
	}

	/**
	 * @return
	 */
	public Timestamp getPspeFechaFin() {
		return pspeFechaFin;
	}

	/**
	 * @return
	 */
	public Timestamp getPspeFechaInicio() {
		return pspeFechaInicio;
	}

	/**
	 * @return
	 */
	public Long getPspeRentaMensual() {
		return pspeRentaMensual;
	}

	/**
	 * @return
	 */
	public Integer getPspeTgenNodo() {
		return pspeTgenNodo;
	}

	/**
	 * @return
	 */
	public Integer getPspeTgenServMsu() {
		return pspeTgenServMsu;
	}

	/**
	 * @return
	 */
	public Long getStbProCmrCd() {
		return stbProCmrCd;
	}

	/**
	 * @return
	 */
	public Long getTipProCmrCd() {
		return tipProCmrCd;
	}

	/**
	 * @return
	 */
	public Long getTipProSerCd() {
		return tipProSerCd;
	}

	/**
	 * @param integer
	 */
	public void setCodAgrSubNu(Integer integer) {
		codAgrSubNu = integer;
	}

	/**
	 * @param long1
	 */
	public void setCodPetCd(Long long1) {
		codPetCd = long1;
	}

	/**
	 * @param integer
	 */
	public void setCodSubCd(Integer integer) {
		codSubCd = integer;
	}

	/**
	 * @param long1
	 */
	public void setCodTipUso(Long long1) {
		codTipUso = long1;
	}

	/**
	 * @param long1
	 */
	public void setCorrelativo(Long long1) {
		correlativo = long1;
	}

	/**
	 * @param string
	 */
	public void setNomProSerNo(String string) {
		nomProSerNo = string;
	}

	/**
	 * @param string
	 */
	public void setNumIdeNu(String string) {
		numIdeNu = string;
	}

	/**
	 * @param string
	 */
	public void setObsSubDs(String string) {
		obsSubDs = string;
	}

	/**
	 * @param long1
	 */
	public void setOpcoId(Long long1) {
		opcoId = long1;
	}

	/**
	 * @param long1
	 */
	public void setPetiNumero(Long long1) {
		petiNumero = long1;
	}

	/**
	 * @param long1
	 */
	public void setPsId(Long long1) {
		psId = long1;
	}

	/**
	 * @param integer
	 */
	public void setPspeCantidad(Integer integer) {
		pspeCantidad = integer;
	}

	/**
	 * @param long1
	 */
	public void setPspeCargoInstalacion(Long long1) {
		pspeCargoInstalacion = long1;
	}

	/**
	 * @param string
	 */
	public void setPspeCodigoFamilia(String string) {
		pspeCodigoFamilia = string;
	}

	/**
	 * @param timestamp
	 */
	public void setPspeFechaFin(Timestamp timestamp) {
		pspeFechaFin = timestamp;
	}

	/**
	 * @param timestamp
	 */
	public void setPspeFechaInicio(Timestamp timestamp) {
		pspeFechaInicio = timestamp;
	}

	/**
	 * @param long1
	 */
	public void setPspeRentaMensual(Long long1) {
		pspeRentaMensual = long1;
	}

	/**
	 * @param integer
	 */
	public void setPspeTgenNodo(Integer integer) {
		pspeTgenNodo = integer;
	}

	/**
	 * @param integer
	 */
	public void setPspeTgenServMsu(Integer integer) {
		pspeTgenServMsu = integer;
	}

	/**
	 * @param long1
	 */
	public void setStbProCmrCd(Long long1) {
		stbProCmrCd = long1;
	}

	/**
	 * @param long1
	 */
	public void setTipProCmrCd(Long long1) {
		tipProCmrCd = long1;
	}

	/**
	 * @param long1
	 */
	public void setTipProSerCd(Long long1) {
		tipProSerCd = long1;
	}

	/**
	 * @return
	 */
	public Long getIdCausaAsociada() {
		return IdCausaAsociada;
	}

	/**
	 * @param long1
	 */
	public void setIdCausaAsociada(Long long1) {
		IdCausaAsociada = long1;
	}

	/**
	 * @param causalPsOcActividadDTO
	 */
	public void addCausalPsOCActividad(CausalPsOcActividadDTO causalPsOcActividadDTO) {
		listaCausales.add(causalPsOcActividadDTO);
		
	}

	/**
	 * @return
	 */
	public String getDescOperComer() {
		return descOperComer;
	}

	/**
	 * @return
	 */
	public ArrayList getListaCausales() {
		return listaCausales;
	}

	/**
	 * @return
	 */
	public String getNombrePS() {
		return nombrePS;
	}

	/**
	 * @param string
	 */
	public void setDescOperComer(String string) {
		descOperComer = string;
	}

	/**
	 * @param list
	 */
	public void setListaCausales(ArrayList list) {
		listaCausales = list;
	}

	/**
	 * @param string
	 */
	public void setNombrePS(String string) {
		nombrePS = string;
	}

	/**
	 * @return
	 */
	public boolean isResolutor() {
		return resolutor;
	}

	/**
	 * @param b
	 */
	public void setResolutor(boolean b) {
		resolutor = b;
	}

	/**
	 * @return
	 */
	public boolean isLlamaALaActividad() {
		return llamaALaActividad;
	}

	/**
	 * @param b
	 */
	public void setLlamaALaActividad(boolean b) {
		llamaALaActividad = b;
	}

	/**
	 * 
	 */
	public void ordenarCausales()
	{
		if(listaCausales.size()>0)
			Collections.sort(listaCausales);
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
	public Long getPsIdFam() {
		return psIdFam;
	}

	/**
	 * @param long1
	 */
	public void setPsIdFam(Long long1) {
		psIdFam = long1;
	}

	/**
	 * @return
	 */
	public String getPsDesFam() {
		return psDesFam;
	}

	/**
	 * @param string
	 */
	public void setPsDesFam(String string) {
		psDesFam = string;
	}

	
	/**
	 * @return Returns the idProductoAnt.
	 */
	public String getIdProductoAnt() {
		return IdProductoAnt;
	}
	/**
	 * @param idProductoAnt The idProductoAnt to set.
	 */
	public void setIdProductoAnt(String idProductoAnt) {
		IdProductoAnt = idProductoAnt;
	}
	/**
	 * @return Returns the nombreProductoAnt.
	 */
	public String getNombreProductoAnt() {
		return nombreProductoAnt;
	}
	/**
	 * @param nombreProductoAnt The nombreProductoAnt to set.
	 */
	public void setNombreProductoAnt(String nombreProductoAnt) {
		this.nombreProductoAnt = nombreProductoAnt;
	}
	/**
	 * @return Returns the tipoPeticion.
	 */
	public String getTipoPeticion() {
		return tipoPeticion;
	}
	/**
	 * @param tipoPeticion The tipoPeticion to set.
	 */
	public void setTipoPeticion(String tipoPeticion) {
		this.tipoPeticion = tipoPeticion;
	}
}
