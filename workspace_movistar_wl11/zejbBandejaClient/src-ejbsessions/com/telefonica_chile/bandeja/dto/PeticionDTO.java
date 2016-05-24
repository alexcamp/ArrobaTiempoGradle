package com.telefonica_chile.bandeja.dto;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import com.telefonica_chile.atiempo.utiles.Fecha;
import com.telefonica_chile.comun.ComunInterfaces;

public class PeticionDTO implements Serializable
{	
	private Long cod_pet_cd;
	private String agrupaciones;
	private Long biId = new Long(0);
	private Long apId = new Long(0);
	private Long usuaId = new Long(0);
	private String usuaLogin = "";
	private String usuaNombre = "";
	private Long biNroPeticion = new Long(0);
	private String biCodAgencia = "";
	private Date biFechaCompromiso = new Date();	
	private String biClienteNombre = "";
	private String biClienteApellidos = "";
	private String biClienteServicio = "";
	private String biClienteArea = "";
	private String biClienteRut = "";
	private String biClienteRutDv = "";
	private String biSegmentoCliente = "";
	private String biFamiliaPs = "";
	private String biUrlDetalle = "";
	private String biUrlPuntaje = "";
	private Date biFechaInicio;
	private Date biFechaApertura;
	private Date biFechaAsignacion = new Date();
	private String biPrioridad = "";	
	private String biSemaforoActividad = "";
	private String biSemaforoCompromiso = "";		
	private String rolId = "";
	private String rolCodigo = "";
	private String rolNombre = "";
	private String actividadId = "";
	private String actividadCodigo = "";
	private String actividadDescripcion = "";
	private String aplicacionNombre = "";
	private String aplicacionCodigo = "";
	private Calendar fechaActual;
	private Calendar fechaFinal;
	private Integer estadoPeticion;
	private Integer tipoTrabajo;
	private String actividadNombreReversa="";
	private String ooss="";
	private Long biSegmentoId = new Long(0);
	private String estadoPeticionNombre = "";
	private String idTica = "";
	private String central = "";
	private String [][]valoresVariables;
	private String nombreBloqueSegmento = "";
	private Long idBloqueSegmento = new Long(0);
	//para GP
	private Long diasAtraso = new Long(0);

	// Para la visualizacion del Emplazamiento
	private String descripcionEmplazamiento = "";
	private String idEmplazamiento = "";

	// Para la visualizacion del Emplazamiento
	private String descripcionNodoAdsl = "";
	private String codigoNodoAdsl = "";

	// Para el Agendamiento.
	private Long idTipoAgendamiento;
	private String nombreTipoAgendamiento; 
	private Integer idRango;
	private String nombreRango;
	private String horaDesde;
	private String horaHasta;
	
	private Integer visible;
	private Integer biVisible;
	private String segmentoDescripcion;
	private String subSegmentoDescripcion;
	private Fecha fechaInicio;
	private Fecha fechaFin;
	
	private TimeZone tz = TimeZone.getTimeZone("America/Santiago");
	
	// Telecom.
	private String localidad;
	private String departamento;
    
	public String getActividadCodigo() {
		return actividadCodigo;
	}

	public String getActividadDescripcion()
	{
		if(estadoPeticion!=null && estadoPeticion.intValue()==ComunInterfaces.estadoPeticionEnCurso)
			return actividadDescripcion;
		if(estadoPeticion!=null && estadoPeticion.intValue()==ComunInterfaces.estadoPeticionCancelada)
			return " (REV)"+actividadDescripcion;
		return actividadDescripcion;
	}

	public String getActividadId() {
		return actividadId;
	}

	public Long getApId() {
		return apId;
	}

	public String getAplicacionCodigo() {
		return aplicacionCodigo;
	}

	public String getAplicacionNombre() {
		return aplicacionNombre;
	}

	public String getBiClienteApellidos() {
		return biClienteApellidos;
	}

	public String getBiClienteArea() {
		return biClienteArea;
	}

	public String getBiClienteNombre() {
		return biClienteNombre;
	}

	public String getBiClienteRut() {
		return biClienteRut;
	}

	public String getBiClienteRutDv() {
		return biClienteRutDv;
	}

	public String getBiClienteServicio() {
		return biClienteServicio;
	}

	public String getBiCodAgencia() {
		return biCodAgencia;
	}

	public String getBiFamiliaPs() {
		return biFamiliaPs;
	}
	
	public String getBiFamiliaPsDes()
	{
		if(biFamiliaPs==null)
			return "";
		if(biFamiliaPs.equals("NO"))
			return "No Determinado";
		else if(biFamiliaPs.equals("IC"))
			return "IC";
		else if(biFamiliaPs.equals("TV"))
			return "TV";
		else if(biFamiliaPs.equals("TVIC"))
			return "TV+IC";
		else if(biFamiliaPs.equals("BA"))
			return "BA";
		else if(biFamiliaPs.equals("BAIC"))
			return "BA+IC";
		else if(biFamiliaPs.equals("BATV"))
			return "BA+TV";
		else if(biFamiliaPs.equals("BATVIC"))
			return "BA+TV+IC";
		else if(biFamiliaPs.equals("LB"))
			return "LB";
		else if(biFamiliaPs.equals("LBIC"))
			return "LB+IC";
		else if(biFamiliaPs.equals("LBTV"))
			return "LB+TV";
		else if(biFamiliaPs.equals("LBTVIC"))
			return "LB+TV+IC";
		else if(biFamiliaPs.equals("LBBA"))
			return "LB+BA";
		else if(biFamiliaPs.equals("LBBAIC"))
			return "LB+BA+IC";
		else if(biFamiliaPs.equals("LBBATV"))
			return "LB+BA+TV";
		else if(biFamiliaPs.equals("LBBATVIC"))
			return "LB+BA+TV+IC";
		else if(biFamiliaPs.equals("IP"))
			return "IP";
		else if(biFamiliaPs.equals("IPBA"))
			return "IP+BA";
		else if(biFamiliaPs.equals("IPIC"))
			return "IP+IC";
		else if(biFamiliaPs.equals("IPTV"))
			return "IP+TV";
		return biFamiliaPs;
	}

	public Long getBiId() {
		return biId;
	}

	public Long getBiNroPeticion() {
		return biNroPeticion;
	}

	public String getBiPrioridad() {
		return biPrioridad;
	}

	public String getBiSegmentoCliente() {
		return biSegmentoCliente;
	}

	public String getBiSemaforoActividad() {
		return biSemaforoActividad;
	}

	public String getBiSemaforoCompromiso() {
		return biSemaforoCompromiso;
	}

	public String getBiUrlDetalle() {
		return biUrlDetalle;
	}

	public String getBiUrlPuntaje() {
		return biUrlPuntaje;
	}

	public Calendar getFechaActual() {
		return fechaActual;
	}

	public Calendar getFechaFinal() {
		return fechaFinal;
	}

	public String getRolCodigo() {
		return rolCodigo;
	}

	public String getRolId() {
		return rolId;
	}

	public String getRolNombre() {
		return rolNombre;
	}

	public TimeZone getTz() {
		return tz;
	}

	public Long getUsuaId() {
		return usuaId;
	}

	public String getUsuaLogin() {
		return usuaLogin;
	}

	public String getUsuaNombre() {
		return usuaNombre;
	}

	public String getDescriocionEmplazamiento() {
		return descripcionEmplazamiento;
	}
	
	public String getDescripcionNodoAdsl() {
		return descripcionNodoAdsl;
	}

	public String getIdEmplazamiento() {
		return idEmplazamiento;
	}

	public String getCodigoNodoAdsl() {
		return codigoNodoAdsl;
	}

	public void setActividadCodigo(String string) {
		actividadCodigo = string;
	}

	public void setActividadDescripcion(String string) {
		actividadDescripcion = string;
	}

	public void setActividadId(String string) {
		actividadId = string;
	}

	public void setApId(Long long1) {
		apId = long1;
	}

	public void setAplicacionCodigo(String string) {
		aplicacionCodigo = string;
	}

	public void setAplicacionNombre(String string) {
		aplicacionNombre = string;
	}

	public void setBiClienteApellidos(String string) {
		biClienteApellidos = string;
	}

	public void setBiClienteArea(String string) {
		biClienteArea = string;
	}

	public void setBiClienteNombre(String string) {
		biClienteNombre = string;
	}

	public void setBiClienteRut(String string) {
		biClienteRut = string;
	}

	public void setBiClienteRutDv(String string) {
		biClienteRutDv = string;
	}

	public void setBiClienteServicio(String string) {
		biClienteServicio = string;
	}

	public void setBiCodAgencia(String string) {
		biCodAgencia = string;
	}

	public void setBiFamiliaPs(String string) {
		biFamiliaPs = string;
	}

	public void setBiId(Long long1) {
		biId = long1;
	}

	public void setBiNroPeticion(Long long1) {
		biNroPeticion = long1;
	}

	public void setBiPrioridad(String string) {
		biPrioridad = string;
	}

	public void setBiSegmentoCliente(String string) {
		biSegmentoCliente = string;
	}

	public void setBiSemaforoActividad(String string) {
		biSemaforoActividad = string;
	}

	public void setBiSemaforoCompromiso(String string) {
		biSemaforoCompromiso = string;
	}

	public void setBiUrlDetalle(String string) {
		biUrlDetalle = string;
	}

	public void setBiUrlPuntaje(String string) {
		biUrlPuntaje = string;
	}

	public void setFechaActual(Calendar calendar) {
		fechaActual = calendar;
	}

	public void setFechaFinal(Calendar calendar) {
		fechaFinal = calendar;
	}

	public void setRolCodigo(String string) {
		rolCodigo = string;
	}

	public void setRolId(String string) {
		rolId = string;
	}

	public void setRolNombre(String string) {
		rolNombre = string;
	}

	public void setTz(TimeZone zone) {
		tz = zone;
	}

	public void setUsuaId(Long long1) {
		usuaId = long1;
	}

	public void setUsuaLogin(String string) {
		usuaLogin = string;
	}

	public void setUsuaNombre(String string) {
		usuaNombre = string;
	}

	public Date getBiFechaApertura() {
		return biFechaApertura;
	}

	public Date getBiFechaAsignacion() {
		return biFechaAsignacion;
	}

	public Date getBiFechaCompromiso() {
		return biFechaCompromiso;
	}

	public Date getBiFechaInicio() {
		return biFechaInicio;
	}

	public void setBiFechaApertura(Date date) {
		biFechaApertura = date;
	}

	public void setBiFechaAsignacion(Date date) {
		biFechaAsignacion = date;
	}

	public void setBiFechaCompromiso(Date date) {
		biFechaCompromiso = date;
	}

	public void setBiFechaInicio(Date date) {
		biFechaInicio = date;
	}
	
	public void setValoresVariables(String [][]valores) {
		valoresVariables = valores;
	}	
	
	public void setDescripcionEmplazamiento(String string) {
		descripcionEmplazamiento = string;
	}

	public void setIdEmplazamiento(String string) {
		idEmplazamiento = string;
	}

	public void setDescripcionNodoAdsl(String string) {
		descripcionNodoAdsl = string;
	}

	public void setCodigoNodoAdsl(String string) {
		codigoNodoAdsl = string;
	}

	/**
	 * @return
	 */
	public Integer getEstadoPeticion() {
		return estadoPeticion;
	}

	/**
	 * @return
	 */
	public Integer getTipoTrabajo() {
		return tipoTrabajo;
	}

	/**
	 * @param integer
	 */
	public void setEstadoPeticion(Integer integer) {
		estadoPeticion = integer;
	}

	/**
	 * @param integer
	 */
	public void setTipoTrabajo(Integer integer) {
		tipoTrabajo = integer;
	}

	/**
	 * @return
	 */
	public String getActividadNombreReversa() {
		return actividadNombreReversa;
	}

	/**
	 * @param string
	 */
	public void setActividadNombreReversa(String string) {
		actividadNombreReversa = string;
	}

	/**
	 * @return
	 */
	public String getOoss() {
		return ooss;
	}

	/**
	 * @param string
	 */
	public void setOoss(String string) {
		ooss = string;
	}

	/**
	 * @return
	 */
	public Long getBiSegmentoId() {
		return biSegmentoId;
	}

	/**
	 * @param long1
	 */
	public void setBiSegmentoId(Long long1) {
		biSegmentoId = long1;
	}

	/**
	 * @return
	 */
	public String getEstadoPeticionNombre() {
		return estadoPeticionNombre;
	}

	/**
	 * @param string
	 */
	public void setEstadoPeticionNombre(String string) {
		estadoPeticionNombre = string;
	}

	/**
	 * @return
	 */
	public String getIdTica() {
		return idTica;
	}

	/**
	 * @param string
	 */
	public void setIdTica(String string) {
		idTica = string;
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
	
	public String [][]getValoresVariables() {
		return valoresVariables;
	}	
	
	/*
	 *  (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("======== PeticionDTO =====\n");
		buffer.append("[biId=" + biId + "]\n");
		buffer.append("[apId=" + apId + "]\n");
		buffer.append("[usuaId=" + usuaId + "]\n");
		buffer.append("[usuaLogin=" + usuaLogin + "]\n");
		buffer.append("[usuaNombre=" + usuaNombre + "]\n");
		buffer.append("[biNroPeticion=" + biNroPeticion + "]\n");
		buffer.append("[biCodAgencia=" + biCodAgencia + "]\n");
		buffer.append("[biFechaCompromiso=" + biFechaCompromiso + "]\n");
		buffer.append("[biClienteNombre=" + biClienteNombre + "]\n");
		buffer.append("[biClienteApellidos=" + biClienteApellidos + "]\n");
		buffer.append("[biClienteServicio=" + biClienteServicio + "]\n");
		buffer.append("[biClienteArea=" + biClienteArea + "]\n");
		buffer.append("[biClienteRut=" + biClienteRut + "]\n");
		buffer.append("[biClienteRutDv=" + biClienteRutDv + "]\n");
		buffer.append("[biSegmentoCliente=" + biSegmentoCliente + "]\n");
		buffer.append("[biFamiliaPs=" + biFamiliaPs + "]\n");
		buffer.append("[biUrlDetalle=" + biUrlDetalle + "]\n");
		buffer.append("[biUrlPuntaje=" + biUrlPuntaje + "]\n");
		buffer.append("[biFechaInicio=" + biFechaInicio + "]\n");
		buffer.append("[biFechaAsignacion=" + biFechaAsignacion + "]\n");
		buffer.append("[biPrioridad=" + biPrioridad + "]\n");
		buffer.append("[biSemaforoActividad=" + biSemaforoActividad + "]\n");
		buffer.append("[biSemaforoCompromiso=" + biSemaforoCompromiso + "]\n");
		buffer.append("[rolId=" + rolId + "]\n");
		buffer.append("[rolCodigo=" + rolCodigo + "]\n");
		buffer.append("[rolNombre=" + rolNombre + "]\n");
		buffer.append("[actividadId=" + actividadId + "]\n");
		buffer.append("[actividadCodigo=" + actividadCodigo + "]\n");
		buffer.append("[actividadDescripcion=" + actividadDescripcion + "]\n");
		buffer.append("[aplicacionNombre=" + aplicacionNombre + "]\n");
		buffer.append("[aplicacionCodigo=" + aplicacionCodigo + "]\n");
		buffer.append("[fechaActual=" + fechaActual + "]\n");
		buffer.append("[fechaFinal=" + fechaFinal + "]\n");
		buffer.append("[estadoPeticion=" + estadoPeticion + "]\n");
		buffer.append("[tipoTrabajo=" + tipoTrabajo + "]\n");
		buffer.append("[actividadNombreReversa=" + actividadNombreReversa + "]\n");
		buffer.append("[ooss=" + ooss + "]\n");
		buffer.append("[biSegmentoId=" + biSegmentoId + "]\n");
		buffer.append("[estadoPeticionNombre=" + estadoPeticionNombre + "]\n");
		buffer.append("[idTica=" + idTica + "]\n");
		buffer.append("[central=" + central + "]\n");
		buffer.append("[tz=" + tz + "]\n");
		buffer.append("[idTipoAgenda=" + idTipoAgendamiento + "]\n");
		buffer.append("[idRango=" + idRango + "]\n");
		buffer.append("[horaDesde=" + horaDesde+ "]\n");
		buffer.append("[horaHasta=" + horaHasta + "]\n");
		return buffer.toString();
	}

	/**
	 * @return
	 */
	public Long getIdBloqueSegmento() {
		return idBloqueSegmento;
	}

	/**
	 * @return
	 */
	public String getNombreBloqueSegmento() {
		return nombreBloqueSegmento;
	}

	/**
	 * @param long1
	 */
	public void setIdBloqueSegmento(Long long1) {
		idBloqueSegmento = long1;
	}

	/**
	 * @param string
	 */
	public void setNombreBloqueSegmento(String string) {
		nombreBloqueSegmento = string;
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
	public Integer getIdRango() {
		return idRango;
	}

	/**
	 * @return
	 */
	public Long getIdTipoAgendamiento() {
		return idTipoAgendamiento;
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
	public void setIdRango(Integer int1) {
		idRango = int1;
	}

	/**
	 * @param long1
	 */
	public void setIdTipoAgendamiento(Long long1) {
		idTipoAgendamiento = long1;
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
	public String getNombreTipoAgendamiento() {
		return nombreTipoAgendamiento;
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
	public void setNombreTipoAgendamiento(String string) {
		nombreTipoAgendamiento = string;
	}
	/**
	 * @return
	 */
	public Integer getVisible() {
		return visible;
	}

	/**
	 * @param integer
	 */
	public void setVisible(Integer integer) {
		visible = integer;
	}

	/**
	 * @return
	 */
	public Integer getBiVisible() {
		return biVisible;
	}

	/**
	 * @param integer
	 */
	public void setBiVisible(Integer integer) {
		biVisible = integer;
	}

	/**
	 * @return
	 */
	public String getDescripcionEmplazamiento() {
		return descripcionEmplazamiento;
	}

	/**
	 * @return
	 */
	public Long getDiasAtraso() {
		return diasAtraso;
	}

	/**
	 * @param long1
	 */
	public void setDiasAtraso(Long long1) {
		diasAtraso = long1;
	}

	/**
	 * @return
	 */
	public String getLocalidad() {
		return localidad;
	}

	/**
	 * @param string
	 */
	public void setLocalidad(String string) {
		localidad = string;
	}

	/**
	 * @return
	 */
	public Long getCod_pet_cd() {
		return cod_pet_cd;
	}

	/**
	 * @param long1
	 */
	public void setCod_pet_cd(Long long1) {
		cod_pet_cd = long1;
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
	public String getSegmentoDescripcion() {
		return segmentoDescripcion;
	}

	/**
	 * @return
	 */
	public String getSubSegmentoDescripcion() {
		return subSegmentoDescripcion;
	}

	/**
	 * @param string
	 */
	public void setSegmentoDescripcion(String string) {
		segmentoDescripcion = string;
	}

	/**
	 * @param string
	 */
	public void setSubSegmentoDescripcion(String string) {
		subSegmentoDescripcion = string;
	}

	/**
	 * @return
	 */
	public String getDepartamento() {
		return departamento;
	}

	/**
	 * @param string
	 */
	public void setDepartamento(String string) {
		departamento = string;
	}

	/**
	 * @return
	 */
	public Fecha getFechaFin() {
		return fechaFin;
	}

	/**
	 * @return
	 */
	public Fecha getFechaInicio() {
		return fechaInicio;
	}

	/**
	 * @param fecha
	 */
	public void setFechaFin(Fecha fecha) {
		fechaFin = fecha;
	}

	/**
	 * @param fecha
	 */
	public void setFechaInicio(Fecha fecha) {
		fechaInicio = fecha;
	}
	
	public String getFechaInicioS()
	{
		if(fechaInicio!=null)
			return fechaInicio.getFechaconFormato();
		return "";
	}
	
	public String getFechaFinS()
	{
		if(fechaFin!=null)
			return fechaFin.getFechaconFormato();
		return "";
	}

	public String getRut()
	{
		if(this.biClienteRut!=null && this.biClienteRutDv!=null && !this.biClienteRut.equals("") && !this.biClienteRutDv.equals(""))
			return this.biClienteRut+"-"+biClienteRutDv;
		if(this.biClienteRut!=null && !this.biClienteRut.equals(""))
			return this.biClienteRut;
		if(this.biClienteRutDv!=null && !this.biClienteRutDv.equals(""))
			return this.biClienteRutDv;
		return this.biClienteRut;
	}
	
	

}

