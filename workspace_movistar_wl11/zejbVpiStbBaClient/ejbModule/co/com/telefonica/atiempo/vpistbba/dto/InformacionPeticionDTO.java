/*
 * Created on 09-02-2007
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package co.com.telefonica.atiempo.vpistbba.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;

import co.com.atiempo.dto.PeticionDTO;
import co.com.telefonica.atiempo.utiles.DataTransferObject;

import com.telefonica_chile.atiempo.utiles.Fecha;
import com.telefonica_chile.atiempo.utiles.FechaException;

/**
 * @author TCS
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class InformacionPeticionDTO implements DataTransferObject {
	
	private String cdpeticion;
	
	private String peticionAtis;
	private ArrayList codigosAgrupacion;
	private String familiaPs;
	private String tipoOp;
	
	private String identificadorOriLinea;
	private String nombreCliente;
	private String idCliente;
	private String cdDepartamento;
	private String dscDepartamento;
	private String cdLocalidad;
	private String dscLocalidad;
	private String cdCentral;
	private String dscCentral;
	private String direccion;
	private String barrio;
	private String cdSegmento;
	private String dscSegmento;
	private String cdSubSegmen;
	private String dscSubSegmen;
	private String cdTipoUso;
	private String telContacto;
	//TODO -- Dos nuevos campos para los tel de contacto -- Pablo L -- CR-10120
	private String segTelContacto;
	private String terTelContacto;
	//TODO -- Fin campos -- Pablo L
	private String fecRegistro;
	private String fecCompromiso;
	private String cdEstado;
	private String dscEstado;
	private String fecAsigRecursos;
	private String fecConfig;
	private String fecTermino;
	private Date fechaUltimaActividad;
	private String etapaProceso;
	
	private String identificadorTV;
	private String identificadorSTB;
	private String identificadorIC;
	private String identificadorMP;
	
	private String dscCanalVta;
	
	// CR14525 - ana santos - inicio
	private String descripcionCanalVta;
	// CR14525 - ana santos - fin	
	
	
	private String tipoDocumento;
	private String tipoUso;
	private String observacion;
	private String observacionSubpeticion;
	
//	Datos de Agrupacion Tipo Linea
	  private String codTipUsoCdAgrupacionLinea;
	  private String nomTipUsoNoAgrupacionLinea;
	  
//  Datos de Empresa del Gestor 
	private Long empresaId;
	private String empresaNombre;
	
// Datos para PBX
	private Integer cantidadTroncales;
	private String nroPiloto;	
	
	//Dato para IC
	private String usuarioAcceso;
	
	//Dato para reseteo CP
	private String idDecoCP;
	
	//Dato para Traslado Solo BA Alta
	private String lineaAnteriorTrasSoloBA;
	
// Datos del solicitante de la peticion
	private String nombreSolicitante;
	private String apellidoPatSolicitante;
	private String apellidoMatSolicitante;
//	CR-23444 - PCawen - Informacion del ciclo de facturacion
	private String infCicloFac;
	
	//CR-7390 - Yumbleiner - Linea Precableada
	private String codigoProyecto;
	//CR-7691 - Yumbleiner - Citofonia Virtual
	private String numPadre;
	private String numExtension;

	//CR- Yumbleiner - Bandera para Visualizar el IDPC Publicidad
	private boolean flagPublicidad;
	
	
	private String caracteristicaEquipo;
	/**
	 * 
	 * @return Returna la  caracteristicaEquipo ingresada al momento de ser creda la peticion.
	 */
	public String getCaracteristicaEquipo() {
		return caracteristicaEquipo;
	}
	/**
	 * @param caracteristicaEquipo The caracteristicaEquipo to set.
	 */
	public void setCaracteristicaEquipo(String caracteristicaEquipo) {
		this.caracteristicaEquipo = caracteristicaEquipo;
	}
	/**
	 * @return Returns the numCuotas.
	 */
	public String getNumCuotas() {
		return numCuotas;
	}
	/**
	 * @param numCuotas The numCuotas to set.
	 */
	public void setNumCuotas(String numCuotas) {
		this.numCuotas = numCuotas;
	}
	private String numCuotas;

	private String idPCPublicidad;
	
	
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
	public InformacionPeticionDTO()
	{
		codigosAgrupacion=new ArrayList();
		empresaId=new Long(0);
		empresaNombre="";
	}
	/**
	 * @return
	 */
	public String getBarrio() {
		return barrio;
	}

	/**
	 * @return
	 */
	public String getCdCentral() {
		return cdCentral;
	}

	/**
	 * @return
	 */
	public String getCdDepartamento() {
		return cdDepartamento;
	}

	/**
	 * @return
	 */
	public String getCdEstado() {
		return cdEstado;
	}

	/**
	 * @return
	 */
	public String getCdLocalidad() {
		return cdLocalidad;
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
	public String getCdSegmento() {
		return cdSegmento;
	}

	/**
	 * @return
	 */
	public String getCdSubSegmen() {
		return cdSubSegmen;
	}

	/**
	 * @return
	 */
	public String getCdTipoUso() {
		return cdTipoUso;
	}

	/**
	 * @return
	 */
	public String getDireccion() {
		return direccion;
	}

	/**
	 * @return
	 */
	public String getDscCentral() {
		return dscCentral;
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
	public String getDscEstado() {
		return dscEstado;
	}

	/**
	 * @return
	 */
	public String getDscLocalidad() {
		return dscLocalidad;
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
	public String getDscSubSegmen() {
		return dscSubSegmen;
	}

	/**
	 * @return
	 */
	public String getEtapaProceso() {
		return etapaProceso;
	}

	/**
	 * @return
	 */
	public String getFecAsigRecursos() {
		return fecAsigRecursos;
	}

	/**
	 * @return
	 */
	public String getFecCompromiso() {
		return fecCompromiso;
	}

	/**
	 * @return
	 */
	public String getFecConfig() {
		return fecConfig;
	}

	/**
	 * @return
	 */
	public String getFecRegistro() {
		return fecRegistro;
	}

	/**
	 * @return
	 */
	public String getFecTermino() {
		return fecTermino;
	}

	/**
	 * @return
	 */
	public String getIdCliente() {
		return idCliente;
	}

	/**
	 * @return
	 */
	public String getIdentificadorOriLinea() {
		return identificadorOriLinea;
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
	public String getNombreCliente() {
		return nombreCliente;
	}

	/**
	 * @return
	 */
	public String getTelContacto() {
		return telContacto;
	}
	/**
		 * @return
		 */
	// TODO Getters de los 2 nuevos teléfonos de contacto -- Pablo L -- CR-10120
	public String getSegTelContacto() {
			return segTelContacto;
		}
	/**
		 * @return
		 */
	public String getTerTelContacto() {
			return terTelContacto;
	}
	//TODO Fin getters -- Pablo L

	/**
	 * @param string
	 */
	public void setBarrio(String string) {
		barrio = string;
	}

	/**
	 * @param string
	 */
	public void setCdCentral(String string) {
		cdCentral = string;
	}

	/**
	 * @param string
	 */
	public void setCdDepartamento(String string) {
		cdDepartamento = string;
	}

	/**
	 * @param string
	 */
	public void setCdEstado(String string) {
		cdEstado = string;
	}

	/**
	 * @param string
	 */
	public void setCdLocalidad(String string) {
		cdLocalidad = string;
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
	public void setCdSegmento(String string) {
		cdSegmento = string;
	}

	/**
	 * @param string
	 */
	public void setCdSubSegmen(String string) {
		cdSubSegmen = string;
	}

	/**
	 * @param string
	 */
	public void setCdTipoUso(String string) {
		cdTipoUso = string;
	}

	/**
	 * @param string
	 */
	public void setDireccion(String string) {
		direccion = string;
	}

	/**
	 * @param string
	 */
	public void setDscCentral(String string) {
		dscCentral = string;
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
	public void setDscEstado(String string) {
		dscEstado = string;
	}

	/**
	 * @param string
	 */
	public void setDscLocalidad(String string) {
		dscLocalidad = string;
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
	public void setDscSubSegmen(String string) {
		dscSubSegmen = string;
	}

	/**
	 * @param string
	 */
	public void setEtapaProceso(String string) {
		etapaProceso = string;
	}

	/**
	 * @param string
	 */
	public void setFecAsigRecursos(String string) {
		fecAsigRecursos = string;
	}

	/**
	 * @param string
	 */
	public void setFecCompromiso(String string) {
		fecCompromiso = string;
	}

	/**
	 * @param string
	 */
	public void setFecConfig(String string) {
		fecConfig = string;
	}

	/**
	 * @param string
	 */
	public void setFecRegistro(String string) {
		fecRegistro = string;
	}

	/**
	 * @param string
	 */
	public void setFecTermino(String string) {
		fecTermino = string;
	}

	/**
	 * @param string
	 */
	public void setIdCliente(String string) {
		idCliente = string;
	}

	/**
	 * @param string
	 */
	public void setIdentificadorOriLinea(String string) {
		identificadorOriLinea = string;
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
	public void setNombreCliente(String string) {
		nombreCliente = string;
	}

	/**
	 * @param string
	 */
	public void setTelContacto(String string) {
		telContacto = string;
	}

	/**
	 * @return
	 */
//	TODO Setters de los 2 nuevos teléfonos de contacto -- Pablo L -- CR-10120
	/**
	 * @param string
	 */
	public void setSegTelContacto(String string) {
		segTelContacto = string;
	}

	/**
	 * @return
	 */
	/**
	 * @param string
	 */
	public void setTerTelContacto(String string) {
		terTelContacto = string;
	}

	/**
	 * @return
	 */
//	TODO Fin setters -- Pablo L 
	public String getTipoDocumento() {
		return tipoDocumento;
	}

	/**
	 * @param string
	 */
	public void setTipoDocumento(String string) {
		tipoDocumento = string;
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
	public Date getFechaUltimaActividad() {
		return fechaUltimaActividad;
	}

	/**
	 * @param date
	 */
	public void setFechaUltimaActividad(Date date) {
		fechaUltimaActividad = date;
	}
	
	public void addCodigoAgrupacion(Integer integer)
	{
		codigosAgrupacion.add(integer);
	}

	/**
	 * @return
	 */
	public ArrayList getCodigosAgrupacion() {
		return codigosAgrupacion;
	}
	
	public String getCodigosAgruConcat()
	{
		String retorno="";
		try
		{
			Collections.sort(codigosAgrupacion);
			for(Iterator iterator=codigosAgrupacion.iterator();iterator.hasNext();)
			{
				Integer val=(Integer) iterator.next();
				retorno+=val.toString();
				if(iterator.hasNext())
					retorno+=",";
			}
		}
		catch(Exception e)
		{
		}
		return retorno;
	}

	/**
	 * @return
	 */
	public String getPeticionAtis() {
		return peticionAtis;
	}

	/**
	 * @param list
	 */
	public void setCodigosAgrupacion(ArrayList list) {
		codigosAgrupacion = list;
	}

	/**
	 * @param string
	 */
	public void setPeticionAtis(String string) {
		peticionAtis = string;
	}

/**
 * @return
 */
public String getCodTipUsoCdAgrupacionLinea() {
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
public void setCodTipUsoCdAgrupacionLinea(String string) {
	codTipUsoCdAgrupacionLinea = string;
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
public Long getEmpresaId() {
	return empresaId;
}

	/**
	 * @return
	 */
	public String getEmpresaNombre() {
		return empresaNombre;
	}

/**
 * @param string
 */
public void setEmpresaId(Long string) {
	empresaId = string;
}

	/**
	 * @param string
	 */
	public void setEmpresaNombre(String string) {
		empresaNombre = string;
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
	 * @param string
	 */
	public void setNroPiloto(String string) {
		nroPiloto = string;
	}

	/**
	 * @return
	 */
	public String getUsuarioAcceso() {
		return usuarioAcceso;
	}

	/**
	 * @param string
	 */
	public void setUsuarioAcceso(String string) {
		usuarioAcceso = string;
	}

	/**
	 * @return
	 */
	public String getIdDecoCP() {
		return idDecoCP;
	}

	/**
	 * @param string
	 */
	public void setIdDecoCP(String string) {
		idDecoCP = string;
	}

	/**
	 * @return
	 */
	public String getLineaAnteriorTrasSoloBA() {
		return lineaAnteriorTrasSoloBA;
	}

	/**
	 * @param string
	 */
	public void setLineaAnteriorTrasSoloBA(String string) {
		lineaAnteriorTrasSoloBA = string;
	}
	
	public PeticionDTO toPeticionDto()
	{
		PeticionDTO pet=new PeticionDTO();
		if(getCdpeticion()!=null)
		pet.setPetiNumero(new Long(getCdpeticion()));
		if(getCdEstado()!=null)
			pet.setEspeId(new Integer(getCdEstado()));
		pet.setDscDepartamento(getDscDepartamento());
		pet.setDscLocalidad(getDscLocalidad());
		if(getCdCentral()!=null)
		pet.setCodCentral(new Long(getCdCentral()));
		pet.setDescCentral(""+getDscCentral());
		pet.setCodPetCd(new Long(getPeticionAtis()));
		try
		{
			pet.setPetiFechaIngreso(new Fecha(getFecRegistro(),"dd/MM/yyyy HH:mm:ss").getTimestamp());
		}
		catch (FechaException e)
		{
			e.printStackTrace();
		}
		pet.setNumIdeNumSTB(getIdentificadorSTB());
		return pet;
	}

	/**
	 * @return
	 */
	public String getFamiliaPs() {
		return familiaPs;
	}

	/**
	 * @param string
	 */
	public void setFamiliaPs(String string) {
		familiaPs = string;
	}

	/**
	 * @return
	 */
	public String getTipoOp() {
		return tipoOp;
	}

	/**
	 * @param string
	 */
	public void setTipoOp(String string) {
		tipoOp = string;
	}

	/**
	 * @return
	 */
	public String getObservacion() {
		return observacion;
	}

	/**
	 * @param string
	 */
	public void setObservacion(String string) {
		observacion = string;
	}

	/**
	 * @return
	 */
	public String getApellidoMatSolicitante() {
		return apellidoMatSolicitante;
	}

	/**
	 * @return
	 */
	public String getApellidoPatSolicitante() {
		return apellidoPatSolicitante;
	}

/**
 * @return
 */
public String getNombreSolicitante() {
	return nombreSolicitante;
}

	/**
	 * @param string
	 */
	public void setApellidoMatSolicitante(String string) {
		apellidoMatSolicitante = string;
	}

	/**
	 * @param string
	 */
	public void setApellidoPatSolicitante(String string) {
		apellidoPatSolicitante = string;
	}

/**
 * @param string
 */
	public void setNombreSolicitante(String string) {
	nombreSolicitante = string;
	}
	

	/**
	 * @return
	 */
	public String getIdentificadorIC() {
		return identificadorIC;
	}

	/**
	 * @param string
	 */
	public void setIdentificadorIC(String string) {
		identificadorIC = string;
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

	public String getInfCicloFac() {
		return infCicloFac;
	}
	public void setInfCicloFac(String infCicloFac) {
		this.infCicloFac = infCicloFac;
	}
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
	 * @param numIdeNumPdg
	 */
	public void setIdPCPublicidad(String numIdeNumPdg) {
		this.idPCPublicidad=numIdeNumPdg;
	}
	
	public String getIdPCPublicidad() {
		return this.idPCPublicidad;
	}
	/**
	 * @return Devuelve observacionSubpeticion.
	 */
	public String getObservacionSubpeticion() {
		return observacionSubpeticion;
	}
	/**
	 * @param observacionSubpeticion El observacionSubpeticion a establecer.
	 */
	public void setObservacionSubpeticion(String observacionSubpeticion) {
		this.observacionSubpeticion = observacionSubpeticion;
	}
	/**
	 * @return Devuelve identificadorMP.
	 */
	public String getIdentificadorMP() {
		return identificadorMP;
	}
	/**
	 * @param identificadorMP El identificadorMP a establecer.
	 */
	public void setIdentificadorMP(String identificadorMP) {
		this.identificadorMP = identificadorMP;
	}
}
