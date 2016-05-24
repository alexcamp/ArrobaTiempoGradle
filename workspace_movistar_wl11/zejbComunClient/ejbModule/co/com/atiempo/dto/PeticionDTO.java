/*
 * Created on 09-03-2007
 */
package co.com.atiempo.dto;

import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * @author Respinoza
 */
public class PeticionDTO {

	/**
	 * 
	 */
	public PeticionDTO()
	{
		listAgrupaciones=new ArrayList();
		tienePBX=false;
		piloto=false;
		troncal=false;
	}

	private String lineaAnteriorTrasSoloBA;
	private String idDecoCP;
	private String usuarioAcceso;
	private ArrayList listAgrupaciones;
	private Long petiNumero;
	private Integer ambiId;
	private Integer espeId;
	private String ticaId;
	private Long agenId;
	private Long lineTrasId;
	private Long codCliCd;
	private Timestamp petiFechaIngreso;
	private Timestamp petiFechaCompromiso;
	private String petiObservacion;
	private String petiUsuarioEmisor;
	private Timestamp petiFechaTermino;
	private String petiIdInstancia;
	private String petiRutVendedor;
	private String petiCausalBaja;
	private String petiTipoHora;
	private Timestamp petiHoraInicio;
	private Timestamp petiHoraFin;
	private Long petiNumeroNueva;
	private Long petiOss;
	private Integer petiRegistroAlta;
	private Integer petiAvisoAlta;
	private Timestamp petiFechaModificacion;
	private String petiTipo;
	private Long codPetCd;
	private Long codEmpCd;
	private Long codSgmCliCd;
	private Long codSbgCliCd;
	private String nomDs;
	private String priApeDs;
	private String segApeDs;
	private Long codCnlVenCd;
	private Long codFzaVenCd;
	private String nomIntDs;
	private String priApeIntDs;
	private String segApeIntDs;
	private Long codPetPdrCd;
	private Long codCtaCd;
	private Timestamp cmbEstPetFf;
	private String tipCliCd;
	private Long codSgmCtaCd;
	private Long codSbgCtaCd;
	private String telCotDs;
	// TODO -- atributos de los 2 nuevos tel -- Pablo L -- CR-10120
	private String segTelCotDs;
	private String terTelCotDs;
	// TODO -- Fin atributos -- Pablo L
	private String nomStePetSn;
	private String priApePetSn;
	private String segApePetSn;
	private String obsPetDs;
	private String obsSubDs;
	private Timestamp fecSctPetFf;
	private String tipDocCd;
	private String numDocCliCd;
	private String digCtlDocCd;
	private String codDpt;
	private String codLoc;
	private Long codCentral;
	private String descCentral;
	private String dirTipVia1;
	private String dirNroVia1;
	private String dirLt1Via1;
	private String dirLt2Via1;
	private String dirZonVia1;
	private String dirTipVia2;
	private String dirNroVia2;
	private String dirLt1Via2;
	private String dirLt2Via2;
	private String dirZonVia2;
	private String dirTipLg1;
	private String dirNroLg1;
	private String dirTipLg2;
	private String dirNroLg2;
	private String dirTipLg3;
	private String dirNroLg3;
	private String codExtLocCd;
	private String codTerCd;
	private String codAreTelCd;
	private String areSnTelCd;
	private String locExtTelCd;
	private String tipCalAtisCd;
	private String nomCalDs;
	private String numCalNu;
	private String dscCmpPriDs;
	private String dscCmpSegDs;
	private Long codLocCd;
	private String nomSloNo;
	private String numIdeNumSTB;
	private String numIdeNumTV;
	private String numIdeNumIC;
	private String numIdeNumMP;
	private String dscDepartamento;
	private String dscLocalidad;

	private String dscSegmento;
	private String dscSubSegmento;
	private String dscCanalVta;
	
	// CR14525 - ana santos - inicio
	private String descripcionCanalVta;
	// CR14525 - ana santos - fin
	
	private String tipoUso;
	private String dscEstado;

	//Datos de Agrupacion Tipo Linea
	private Long codTipUsoCdAgrupacionLinea;
	private String nomTipUsoNoAgrupacionLinea;
	private String agrupaciones;
	
	private String familiasEnPeticion;
	private String categoriaOpCo;
	
	private boolean tienePBX;
	private boolean piloto;
	private boolean troncal;
	private Integer cantidadTroncales;
	private String nroPiloto;
	
	private String idOriginalPCLinea;
	private String idOriginalPCTV;
	private boolean estaEnBandeja=false;
	private boolean estaEnActividadPermitida=false;
	private String urlBandeja;
	private Long tipoErrorId;
	//CR-23444 - PCawen - Informacion del ciclo de facturacion
	private String infCicFac;

	//CR-7390 - Yumbleiner - Linea Precableada
	private String codigoProyecto;

	//CR-7691 - Yumbleiner - Citofonia Virtual
	private String numPadre;
	private String numExtension;
	//Yumbleiner - Publicidad
	private boolean flagPublicidad;
	private String numIdeNuPdg;
	/**
	 * @return Returns the flagPublicidad.
	 */
	public boolean isFlagPublicidad() {
		return flagPublicidad;
	}
	/**
	 * @param flagPublicidad The flagPublicidad to set.
	 */
	public void setFlagPublicidad(boolean flagPublicidad) {
		this.flagPublicidad = flagPublicidad;
	}
	
	/**
	 * @return Returns the codigoProyecto.
	 */
	public String getCodigoProyecto() {
		return codigoProyecto;
	}
	/**
	 * @param codigoProyecto The codigoProyecto to set.
	 */
	public void setCodigoProyecto(String codigoProyecto) {
		this.codigoProyecto = codigoProyecto;
	}
	/**
	 * @return Returns the numExtension.
	 */
	public String getNumExtension() {
		return numExtension;
	}
	/**
	 * @param numExtension The numExtension to set.
	 */
	public void setNumExtension(String numExtension) {
		this.numExtension = numExtension;
	}
	/**
	 * @return Returns the numPadre.
	 */
	public String getNumPadre() {
		return numPadre;
	}
	/**
	 * @param numPadre The numPadre to set.
	 */
	public void setNumPadre(String numPadre) {
		this.numPadre = numPadre;
	}
	public String getInfCicFac() {
		return infCicFac;
	}
	public void setInfCicFac(String infCicFac) {
		this.infCicFac = infCicFac;
	}
	private ArrayList listaPS = new ArrayList();
	
	/**
	 * @return
	 */
	public Long getAgenId() {
		return agenId;
	}

	/**
	 * @return
	 */
	public Integer getAmbiId() {
		return ambiId;
	}

	/**
	 * @return
	 */
	public String getAreSnTelCd() {
		return areSnTelCd;
	}

	/**
	 * @return
	 */
	public Timestamp getCmbEstPetFf() {
		return cmbEstPetFf;
	}

	/**
	 * @return
	 */
	public String getCodAreTelCd() {
		return codAreTelCd;
	}

	/**
	 * @return
	 */
	public Long getCodCentral() {
		return codCentral;
	}

	/**
	 * @return
	 */
	public Long getCodCliCd() {
		return codCliCd;
	}

	/**
	 * @return
	 */
	public Long getCodCnlVenCd() {
		return codCnlVenCd;
	}

	/**
	 * @return
	 */
	public Long getCodCtaCd() {
		return codCtaCd;
	}

	/**
	 * @return
	 */
	public String getCodDpt() {
		return codDpt;
	}

	/**
	 * @return
	 */
	public Long getCodEmpCd() {
		return codEmpCd;
	}

	/**
	 * @return
	 */
	public String getCodExtLocCd() {
		return codExtLocCd;
	}

	/**
	 * @return
	 */
	public Long getCodFzaVenCd() {
		return codFzaVenCd;
	}

	/**
	 * @return
	 */
	public String getCodLoc() {
		return codLoc;
	}

	/**
	 * @return
	 */
	public Long getCodLocCd() {
		return codLocCd;
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
	public Long getCodPetPdrCd() {
		return codPetPdrCd;
	}

	/**
	 * @return
	 */
	public Long getCodSbgCtaCd() {
		return codSbgCtaCd;
	}

	/**
	 * @return
	 */
	public Long getCodSbgCliCd() {
		return codSbgCliCd;
	}

	/**
	 * @return
	 */
	public Long getCodSgmCliCd() {
		return codSgmCliCd;
	}

	/**
	 * @return
	 */
	public Long getCodSgmCtaCd() {
		return codSgmCtaCd;
	}

	/**
	 * @return
	 */
	public String getCodTerCd() {
		return codTerCd;
	}

	/**
	 * @return
	 */
	public String getDigCtlDocCd() {
		return digCtlDocCd;
	}

	/**
	 * @return
	 */
	public String getDirLt1Via1() {
		return dirLt1Via1;
	}

	/**
	 * @return
	 */
	public String getDirLt1Via2() {
		return dirLt1Via2;
	}

	/**
	 * @return
	 */
	public String getDirLt2Via1() {
		return dirLt2Via1;
	}

	/**
	 * @return
	 */
	public String getDirLt2Via2() {
		return dirLt2Via2;
	}

	/**
	 * @return
	 */
	public String getDirNroLg1() {
		return dirNroLg1;
	}

	/**
	 * @return
	 */
	public String getDirNroLg2() {
		return dirNroLg2;
	}

	/**
	 * @return
	 */
	public String getDirNroLg3() {
		return dirNroLg3;
	}

	/**
	 * @return
	 */
	public String getDirNroVia1() {
		return dirNroVia1;
	}

	/**
	 * @return
	 */
	public String getDirNroVia2() {
		return dirNroVia2;
	}

	/**
	 * @return
	 */
	public String getDirTipLg1() {
		return dirTipLg1;
	}

	/**
	 * @return
	 */
	public String getDirTipLg2() {
		return dirTipLg2;
	}

	/**
	 * @return
	 */
	public String getDirTipLg3() {
		return dirTipLg3;
	}

	/**
	 * @return
	 */
	public String getDirTipVia1() {
		return dirTipVia1;
	}

	/**
	 * @return
	 */
	public String getDirTipVia2() {
		return dirTipVia2;
	}

	/**
	 * @return
	 */
	public String getDirZonVia1() {
		return dirZonVia1;
	}

	/**
	 * @return
	 */
	public String getDirZonVia2() {
		return dirZonVia2;
	}

	/**
	 * @return
	 */
	public String getDscCmpPriDs() {
		return dscCmpPriDs;
	}

	/**
	 * @return
	 */
	public String getDscCmpSegDs() {
		return dscCmpSegDs;
	}

	/**
	 * @return
	 */
	public Integer getEspeId() {
		return espeId;
	}

	/**
	 * @return
	 */
	public Timestamp getPetiFechaTermino() {
		return petiFechaTermino;
	}

	/**
	 * @return
	 */
	public Timestamp getFecSctPetFf() {
		return fecSctPetFf;
	}

	/**
	 * @return
	 */
	public Long getLineTrasId() {
		return lineTrasId;
	}

	/**
	 * @return
	 */
	public String getLocExtTelCd() {
		return locExtTelCd;
	}

	/**
	 * @return
	 */
	public String getNomCalDs() {
		return nomCalDs;
	}

	/**
	 * @return
	 */
	public String getNomDs() {
		return nomDs;
	}

	/**
	 * @return
	 */
	public String getNomIntDs() {
		return nomIntDs;
	}

	/**
	 * @return
	 */
	public String getNomSloNo() {
		return nomSloNo;
	}

	/**
	 * @return
	 */
	public String getNomStePetSn() {
		return nomStePetSn;
	}

	/**
	 * @return
	 */
	public String getNumCalNu() {
		return numCalNu;
	}

	/**
	 * @return
	 */
	public String getNumDocCliCd() {
		return numDocCliCd;
	}

	/**
	 * @return
	 */
	public String getObsPetDs() {
		return obsPetDs;
	}

	/**
	 * @return
	 */
	public Timestamp getPetiHoraInicio() {
		return petiHoraInicio;
	}

	/**
	 * @return
	 */
	public Integer getPetiAvisoAlta() {
		return petiAvisoAlta;
	}

	/**
	 * @return
	 */
	public String getPetiCausalBaja() {
		return petiCausalBaja;
	}

	/**
	 * @return
	 */
	public Timestamp getPetiFechaCompromiso() {
		return petiFechaCompromiso;
	}

	/**
	 * @return
	 */
	public Timestamp getPetiFechaIngreso() {
		return petiFechaIngreso;
	}

	/**
	 * @return
	 */
	public Timestamp getPetiFechaModificacion() {
		return petiFechaModificacion;
	}

	/**
	 * @return
	 */
	public Timestamp getPetiHoraFin() {
		return petiHoraFin;
	}

	/**
	 * @return
	 */
	public String getPetiIdInstancia() {
		return petiIdInstancia;
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
	public Long getPetiNumeroNueva() {
		return petiNumeroNueva;
	}

	/**
	 * @return
	 */
	public String getPetiObservacion() {
		return petiObservacion;
	}

	/**
	 * @return
	 */
	public Long getPetiOss() {
		return petiOss;
	}

	/**
	 * @return
	 */
	public Integer getPetiRegistroAlta() {
		return petiRegistroAlta;
	}

	/**
	 * @return
	 */
	public String getPetiRutVendedor() {
		return petiRutVendedor;
	}

	/**
	 * @return
	 */
	public String getPetiTipo() {
		return petiTipo;
	}

	/**
	 * @return
	 */
	public String getPetiUsuarioEmisor() {
		return petiUsuarioEmisor;
	}

	/**
	 * @return
	 */
	public String getPriApeDs() {
		return priApeDs;
	}

	/**
	 * @return
	 */
	public String getPriApeIntDs() {
		return priApeIntDs;
	}

	/**
	 * @return
	 */
	public String getPriApePetSn() {
		return priApePetSn;
	}

	/**
	 * @return
	 */
	public String getSegApeDs() {
		return segApeDs;
	}

	/**
	 * @return
	 */
	public String getSegApeIntDs() {
		return segApeIntDs;
	}

	/**
	 * @return
	 */
	public String getSegApePetSn() {
		return segApePetSn;
	}

	/**
	 * @return
	 */
	public String getTelCotDs() {
		return telCotDs;
	}

	/**
	 * @return
	 */
	// TODO -- Getters nuevos tels de contacto -- Pablo L
	public String getSegTelCotDs() {
			return segTelCotDs;
		}

		/**
		 * @return
		 */
	public String getTerTelCotDs() {
			return terTelCotDs;
		}

		/**
		 * @return
		 */
	// TODO -- Fin getters -- Pablo L
	public String getTicaId() {
		return ticaId;
	}

	/**
	 * @return
	 */
	public String getTipCalAtisCd() {
		return tipCalAtisCd;
	}

	/**
	 * @return
	 */
	public String getTipCliCd() {
		return tipCliCd;
	}

	/**
	 * @return
	 */
	public String getTipDocCd() {
		return tipDocCd;
	}

	/**
	 * @param long1
	 */
	public void setAgenId(Long long1) {
		agenId = long1;
	}

	/**
	 * @param integer
	 */
	public void setAmbiId(Integer integer) {
		ambiId = integer;
	}

	/**
	 * @param string
	 */
	public void setAreSnTelCd(String string) {
		areSnTelCd = string;
	}

	/**
	 * @param timestamp
	 */
	public void setCmbEstPetFf(Timestamp timestamp) {
		cmbEstPetFf = timestamp;
	}

	/**
	 * @param string
	 */
	public void setCodAreTelCd(String string) {
		codAreTelCd = string;
	}

	/**
	 * @param long1
	 */
	public void setCodCentral(Long long1) {
		codCentral = long1;
	}

	/**
	 * @param long1
	 */
	public void setCodCliCd(Long long1) {
		codCliCd = long1;
	}

	/**
	 * @param long1
	 */
	public void setCodCnlVenCd(Long long1) {
		codCnlVenCd = long1;
	}

	/**
	 * @param long1
	 */
	public void setCodCtaCd(Long long1) {
		codCtaCd = long1;
	}

	/**
	 * @param string
	 */
	public void setCodDpt(String string) {
		codDpt = string;
	}

	/**
	 * @param long1
	 */
	public void setCodEmpCd(Long long1) {
		codEmpCd = long1;
	}

	/**
	 * @param string
	 */
	public void setCodExtLocCd(String string) {
		codExtLocCd = string;
	}

	/**
	 * @param long1
	 */
	public void setCodFzaVenCd(Long long1) {
		codFzaVenCd = long1;
	}

	/**
	 * @param string
	 */
	public void setCodLoc(String string) {
		codLoc = string;
	}

	/**
	 * @param long1
	 */
	public void setCodLocCd(Long long1) {
		codLocCd = long1;
	}

	/**
	 * @param long1
	 */
	public void setCodPetCd(Long long1) {
		codPetCd = long1;
	}

	/**
	 * @param long1
	 */
	public void setCodPetPdrCd(Long long1) {
		codPetPdrCd = long1;
	}

	/**
	 * @param long1
	 */
	public void setCodSbgCtaCd(Long long1) {
		codSbgCtaCd = long1;
	}

	/**
	 * @param long1
	 */
	public void setCodSbgCliCd(Long long1) {
		codSbgCliCd = long1;
	}

	/**
	 * @param long1
	 */
	public void setCodSgmCliCd(Long long1) {
		codSgmCliCd = long1;
	}

	/**
	 * @param long1
	 */
	public void setCodSgmCtaCd(Long long1) {
		codSgmCtaCd = long1;
	}

	/**
	 * @param string
	 */
	public void setCodTerCd(String string) {
		codTerCd = string;
	}

	/**
	 * @param string
	 */
	public void setDigCtlDocCd(String string) {
		digCtlDocCd = string;
	}

	/**
	 * @param string
	 */
	public void setDirLt1Via1(String string) {
		dirLt1Via1 = string;
	}

	/**
	 * @param string
	 */
	public void setDirLt1Via2(String string) {
		dirLt1Via2 = string;
	}

	/**
	 * @param string
	 */
	public void setDirLt2Via1(String string) {
		dirLt2Via1 = string;
	}

	/**
	 * @param string
	 */
	public void setDirLt2Via2(String string) {
		dirLt2Via2 = string;
	}

	/**
	 * @param string
	 */
	public void setDirNroLg1(String string) {
		dirNroLg1 = string;
	}

	/**
	 * @param string
	 */
	public void setDirNroLg2(String string) {
		dirNroLg2 = string;
	}

	/**
	 * @param string
	 */
	public void setDirNroLg3(String string) {
		dirNroLg3 = string;
	}

	/**
	 * @param string
	 */
	public void setDirNroVia1(String string) {
		dirNroVia1 = string;
	}

	/**
	 * @param string
	 */
	public void setDirNroVia2(String string) {
		dirNroVia2 = string;
	}

	/**
	 * @param string
	 */
	public void setDirTipLg1(String string) {
		dirTipLg1 = string;
	}

	/**
	 * @param string
	 */
	public void setDirTipLg2(String string) {
		dirTipLg2 = string;
	}

	/**
	 * @param string
	 */
	public void setDirTipLg3(String string) {
		dirTipLg3 = string;
	}

	/**
	 * @param string
	 */
	public void setDirTipVia1(String string) {
		dirTipVia1 = string;
	}

	/**
	 * @param string
	 */
	public void setDirTipVia2(String string) {
		dirTipVia2 = string;
	}

	/**
	 * @param string
	 */
	public void setDirZonVia1(String string) {
		dirZonVia1 = string;
	}

	/**
	 * @param string
	 */
	public void setDirZonVia2(String string) {
		dirZonVia2 = string;
	}

	/**
	 * @param string
	 */
	public void setDscCmpPriDs(String string) {
		dscCmpPriDs = string;
	}

	/**
	 * @param string
	 */
	public void setDscCmpSegDs(String string) {
		dscCmpSegDs = string;
	}

	/**
	 * @param integer
	 */
	public void setEspeId(Integer integer) {
		espeId = integer;
	}

	/**
	 * @param timestamp
	 */
	public void setPetiFechaTermino(Timestamp timestamp) {
		petiFechaTermino = timestamp;
	}

	/**
	 * @param timestamp
	 */
	public void setFecSctPetFf(Timestamp timestamp) {
		fecSctPetFf = timestamp;
	}

	/**
	 * @param long1
	 */
	public void setLineTrasId(Long long1) {
		lineTrasId = long1;
	}

	/**
	 * @param string
	 */
	public void setLocExtTelCd(String string) {
		locExtTelCd = string;
	}

	/**
	 * @param string
	 */
	public void setNomCalDs(String string) {
		nomCalDs = string;
	}

	/**
	 * @param string
	 */
	public void setNomDs(String string) {
		nomDs = string;
	}

	/**
	 * @param string
	 */
	public void setNomIntDs(String string) {
		nomIntDs = string;
	}

	/**
	 * @param string
	 */
	public void setNomSloNo(String string) {
		nomSloNo = string;
	}

	/**
	 * @param string
	 */
	public void setNomStePetSn(String string) {
		nomStePetSn = string;
	}

	/**
	 * @param string
	 */
	public void setNumCalNu(String string) {
		numCalNu = string;
	}

	/**
	 * @param string
	 */
	public void setNumDocCliCd(String string) {
		numDocCliCd = string;
	}

	/**
	 * @param string
	 */
	public void setObsPetDs(String string) {
		obsPetDs = string;
	}

	/**
	 * @param timestamp
	 */
	public void setPetiHoraInicio(Timestamp timestamp) {
		petiHoraInicio = timestamp;
	}

	/**
	 * @param integer
	 */
	public void setPetiAvisoAlta(Integer integer) {
		petiAvisoAlta = integer;
	}

	/**
	 * @param string
	 */
	public void setPetiCausalBaja(String string) {
		petiCausalBaja = string;
	}

	/**
	 * @param timestamp
	 */
	public void setPetiFechaCompromiso(Timestamp timestamp) {
		petiFechaCompromiso = timestamp;
	}

	/**
	 * @param timestamp
	 */
	public void setPetiFechaIngreso(Timestamp timestamp) {
		petiFechaIngreso = timestamp;
	}

	/**
	 * @param timestamp
	 */
	public void setPetiFechaModificacion(Timestamp timestamp) {
		petiFechaModificacion = timestamp;
	}

	/**
	 * @param timestamp
	 */
	public void setPetiHoraFin(Timestamp timestamp) {
		petiHoraFin = timestamp;
	}

	/**
	 * @param string
	 */
	public void setPetiIdInstancia(String string) {
		petiIdInstancia = string;
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
	public void setPetiNumeroNueva(Long long1) {
		petiNumeroNueva = long1;
	}

	/**
	 * @param string
	 */
	public void setPetiObservacion(String string) {
		petiObservacion = string;
	}

	/**
	 * @param long1
	 */
	public void setPetiOss(Long long1) {
		petiOss = long1;
	}

	/**
	 * @param integer
	 */
	public void setPetiRegistroAlta(Integer integer) {
		petiRegistroAlta = integer;
	}

	/**
	 * @param string
	 */
	public void setPetiRutVendedor(String string) {
		petiRutVendedor = string;
	}

	/**
	 * @param string
	 */
	public void setPetiTipo(String string) {
		petiTipo = string;
	}

	/**
	 * @param string
	 */
	public void setPetiUsuarioEmisor(String string) {
		petiUsuarioEmisor = string;
	}

	/**
	 * @param string
	 */
	public void setPriApeDs(String string) {
		priApeDs = string;
	}

	/**
	 * @param string
	 */
	public void setPriApeIntDs(String string) {
		priApeIntDs = string;
	}

	/**
	 * @param string
	 */
	public void setPriApePetSn(String string) {
		priApePetSn = string;
	}

	/**
	 * @param string
	 */
	public void setSegApeDs(String string) {
		segApeDs = string;
	}

	/**
	 * @param string
	 */
	public void setSegApeIntDs(String string) {
		segApeIntDs = string;
	}

	/**
	 * @param string
	 */
	public void setSegApePetSn(String string) {
		segApePetSn = string;
	}

	/**
	 * @param string
	 */
	public void setTelCotDs(String string) {
		telCotDs = string;
	}
	// TODO -- Setters nuevos tel de contacto -- Pablo L
	/**
	  * @param string
	  */
	public void setSegTelCotDs(String string) {
			segTelCotDs = string;
		}
	/**
	  * @param string
	  */
	public void setTerTelCotDs(String string) {
			terTelCotDs = string;
		}
	// TODO -- Fin setters -- Pablo L

	/**
	 * @param string
	 */
	public void setTicaId(String string) {
		ticaId = string;
	}

	/**
	 * @param string
	 */
	public void setTipCalAtisCd(String string) {
		tipCalAtisCd = string;
	}

	/**
	 * @param string
	 */
	public void setTipCliCd(String string) {
		tipCliCd = string;
	}

	/**
	 * @param string
	 */
	public void setTipDocCd(String string) {
		tipDocCd = string;
	}

	/**
	 * @return
	 */
	public String getPetiTipoHora() {
		return petiTipoHora;
	}

	/**
	 * @param string
	 */
	public void setPetiTipoHora(String string) {
		petiTipoHora = string;
	}

	/**
	 * @return
	 */
	public String getNumIdeNumSTB() {
		return numIdeNumSTB;
	}

	/**
	 * @return
	 */
	public String getNumIdeNumTV() {
		return numIdeNumTV;
	}

	/**
	 * @param string
	 */
	public void setNumIdeNumSTB(String string) {
		numIdeNumSTB = string;
	}

	/**
	 * @param string
	 */
	public void setNumIdeNumTV(String string) {
		numIdeNumTV = string;
	}

	/**
	 * @return
	 */
	public String getDscDepartamento() {
		return dscDepartamento;
	}

	/**
	 * @return
	 */
	public String getDscLocalidad() {
		return dscLocalidad;
	}

	/**
	 * @param string
	 */
	public void setDscDepartamento(String string) {
		dscDepartamento = string;
	}

	/**
	 * @param string
	 */
	public void setDscLocalidad(String string) {
		dscLocalidad = string;
	}

	/**
	 * @return
	 */
	public String getDscSegmento() {
		return dscSegmento;
	}

	/**
	 * @return
	 */
	public String getDscSubSegmento() {
		return dscSubSegmento;
	}

	/**
	 * @param string
	 */
	public void setDscSegmento(String string) {
		dscSegmento = string;
	}

	/**
	 * @param string
	 */
	public void setDscSubSegmento(String string) {
		dscSubSegmento = string;
	}

	/**
	 * @return
	 */
	public String getDscCanalVta() {
		return dscCanalVta;
	}

	/**
	 * @return
	 */
	public String getTipoUso() {
		return tipoUso;
	}

	/**
	 * @param string
	 */
	public void setDscCanalVta(String string) {
		dscCanalVta = string;
	}

	/**
	 * @param string
	 */
	public void setTipoUso(String string) {
		tipoUso = string;
	}

	/**
	 * @return
	 */
	public String getDscEstado() {
		return dscEstado;
	}

	/**
	 * @param string
	 */
	public void setDscEstado(String string) {
		dscEstado = string;
	}

	/**
	 * @return
	 */
	public ArrayList getListAgrupaciones() {
		return listAgrupaciones;
	}


	/**
	 * @param list
	 */
	public void setListAgrupaciones(ArrayList list) {
		listAgrupaciones = list;
	}

	
	public void agregarAgrupacion(Integer cod_sub_agru)
	{
		if(!listAgrupaciones.contains(cod_sub_agru))
			listAgrupaciones.add(cod_sub_agru);
	}

	/**
	 * @return
	 */
	public Long getCodTipUsoCdAgrupacionLinea() {
		return codTipUsoCdAgrupacionLinea;
	}

	/**
	 * @return
	 */
	public String getNomTipUsoNoAgrupacionLinea() {
		return nomTipUsoNoAgrupacionLinea;
	}

	/**
	 * @param long1
	 */
	public void setCodTipUsoCdAgrupacionLinea(Long long1) {
		codTipUsoCdAgrupacionLinea = long1;
	}

	/**
	 * @param string
	 */
	public void setNomTipUsoNoAgrupacionLinea(String string) {
		nomTipUsoNoAgrupacionLinea = string;
	}

	/**
	 * @return
	 */
	public String getAgrupaciones() {
		return agrupaciones;
	}

	/**
	 * @param string
	 */
	public void setAgrupaciones(String string) {
		agrupaciones = string;
	}

	/**
	 * @return
	 */
	public String getFamiliasEnPeticion() {
		return familiasEnPeticion;
	}

	/**
	 * @param string
	 */
	public void setFamiliasEnPeticion(String string) {
		familiasEnPeticion = string;
	}

	/**
	 * @return
	 */
	public boolean isTienePBX() {
		return tienePBX;
	}

	/**
	 * @param b
	 */
	public void setTienePBX(boolean b) {
		tienePBX = b;
	}

	/**
	 * @return
	 */
	public boolean isPiloto() {
		return piloto;
	}

	/**
	 * @return
	 */
	public boolean isTroncal() {
		return troncal;
	}

	/**
	 * @param b
	 */
	public void setPiloto(boolean b) {
		piloto = b;
	}

	/**
	 * @param b
	 */
	public void setTroncal(boolean b) {
		troncal = b;
	}

	/**
	 * @return
	 */
	public Integer getCantidadTroncales() {
		return cantidadTroncales;
	}

	/**
	 * @param integer
	 */
	public void setCantidadTroncales(Integer integer) {
		cantidadTroncales = integer;
	}

	/**
	 * @return
	 */
	public String getNroPiloto() {
		return nroPiloto;
	}

	/**
	 * @param integer
	 */
	public void setNroPiloto(String integer) {
		nroPiloto = integer;
	}

	/**
	 * @return
	 */
	public String getIdOriginalPCLinea() {
		return idOriginalPCLinea;
	}

	/**
	 * @param string
	 */
	public void setIdOriginalPCLinea(String string) {
		idOriginalPCLinea = string;
	}

	/**
	 * @return
	 */
	public ArrayList getListaPS() {
		return listaPS;
	}

	/**
	 * @param list
	 */
	public void setListaPS(ArrayList list) {
		listaPS = list;
	}

	/**
	 * @return
	 */
	public String getDescCentral() {
		return descCentral;
	}

	/**
	 * @param string
	 */
	public void setDescCentral(String string) {
		descCentral = string;
	}

	/**
	 * @param long1
	 */
	public void setUsuarioAcceso(String string)
	{
		usuarioAcceso=string;
		
	}

	/**
	 * @return
	 */
	public String getUsuarioAcceso() {
		return usuarioAcceso;
	}

	public void setIdDecoCP(String idDecoCP)
	{
		this.idDecoCP=idDecoCP;
	}

	/**
	 * @return
	 */
	public String getIdDecoCP() {
		return idDecoCP;
	}

	public void setLineaAnteriorTrasSoloBA(String lineaAnteriorTrasSoloBA)
	{
		this.lineaAnteriorTrasSoloBA=lineaAnteriorTrasSoloBA;
	}

	/**
	 * @return
	 */
	public String getLineaAnteriorTrasSoloBA() {
		return lineaAnteriorTrasSoloBA;
	}

	/**
	 * @return
	 */
	public String getIdOriginalPCTV() {
		return idOriginalPCTV;
	}

	/**
	 * @param string
	 */
	public void setIdOriginalPCTV(String string) {
		idOriginalPCTV = string;
	}

	/**
	 * @return
	 */
	public String getCategoriaOpCo() {
		return categoriaOpCo;
	}

	/**
	 * @param string
	 */
	public void setCategoriaOpCo(String string) {
		categoriaOpCo = string;
	}

	/**
	 * @return
	 */
	public boolean isEstaEnBandeja() {
		return estaEnBandeja;
	}

	/**
	 * @param b
	 */
	public void setEstaEnBandeja(boolean b) {
		estaEnBandeja = b;
	}

	/**
	 * @return
	 */
	public boolean isEstaEnActividadPermitida() {
		return estaEnActividadPermitida;
	}

	/**
	 * @param b
	 */
	public void setEstaEnActividadPermitida(boolean b) {
		estaEnActividadPermitida = b;
	}

	/**
	 * @return
	 */
	public String getUrlBandeja() {
		return urlBandeja;
	}

	/**
	 * @param string
	 */
	public void setUrlBandeja(String string) {
		urlBandeja = string;
	}

	/**
	 * @return
	 */
	public String getNumIdeNumIC() {
		return numIdeNumIC;
	}

	/**
	 * @param string
	 */
	public void setNumIdeNumIC(String string) {
		numIdeNumIC = string;
	}

	public Long getTipoErrorId() {
		return tipoErrorId;
	}
	public void setTipoErrorId(Long long1) {
		tipoErrorId = long1;
	}
	/**
	 * @return
	 */
	public String getDescripcionCanalVta() {
		return descripcionCanalVta;
	}

	/**
	 * @param string
	 */
	public void setDescripcionCanalVta(String string) {
		descripcionCanalVta = string;
	}
	/**
	 * IDPC de presencia digital
	 * @param num_ide_nu_pdg
	 */
	public void setNumIdeNumPdg(String num_ide_nu_pdg) {
		this.numIdeNuPdg=num_ide_nu_pdg;
		
	}
	
	/**
	 * @param num_ide_nu_pdg
	 */
	public String getNumIdeNumPdg() {
		return this.numIdeNuPdg;
		
	}
	/**
	 * @return Devuelve obsSubDs.
	 */
	public String getObsSubDs() {
		return obsSubDs;
	}
	/**
	 * @param obsSubDs El obsSubDs a establecer.
	 */
	public void setObsSubDs(String obsSubDs) {
		this.obsSubDs = obsSubDs;
	}
	/**
	 * @return Devuelve numIdeNumMP.
	 */
	public String getNumIdeNumMP() {
		return numIdeNumMP;
	}
	/**
	 * @param numIdeNumMP El numIdeNumMP a establecer.
	 */
	public void setNumIdeNumMP(String numIdeNumMP) {
		this.numIdeNumMP = numIdeNumMP;
	}
}
