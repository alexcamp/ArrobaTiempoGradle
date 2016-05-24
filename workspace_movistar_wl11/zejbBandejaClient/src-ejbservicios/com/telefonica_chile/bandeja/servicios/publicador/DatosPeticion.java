package com.telefonica_chile.bandeja.servicios.publicador;

import java.io.Serializable;
import java.util.Date;

import org.apache.log4j.Logger;

import com.telefonica_chile.atiempo.utiles.LoggerFactory;

public class DatosPeticion implements Serializable {
	private String nombreAplicacion;
	private String codigoActividad;
	private String codigoActividadNueva;
	private String actImplCorrelID;
	private Long numeroPeticion;
	private String usernameResponsable;
	private Long idResponsable;
	private String codigoAgencia;
	private String isp;
	private String nodo;
	private String idTica;
	
	private String nombreCliente;
	private String apellidosCliente;
	private String servicioCliente;
	private String areaCliente;
	private String rutCliente;
	private String rutdvCliente;
	private String segmentoCliente;
	private String subSegmentoCliente;
	
	private String familiaPS;
	private String categoriaOpCo;
	private String urlDetalle;
	private Integer puntaje;
	private Date fechaInicio;
	private Date fechaApertura;
	private Date fechaAsignacion;
	private Date fechaCompromiso;
	private Integer estadoPeticion;
	private Integer tipoTrabajo;
	
	// Datos de Agendamiento.
	private Long idTipoAgendamiento;
	private Integer idRango;
	private String horaDesde;
	private String horaHasta;
	
	//Datos para ADSL2
	private String emplazamiento;
	private String nombreNodo;
	
	private Integer idGrupoSegmento;
	private String[][] valoresVariables;
	
	// DAtos de TeleCom.
	private String codigoLocalidad;
	private String codigoDepartamento;
	private Long codigoCentral;
	private Long nroPeticionAtis;
	private String agrupaciones;
	private Integer tienePBX;
	
	//CR17031 pcawen
	private Date fechaCompromisoSec;
	private String Categoria;
	
	public Date getFechaCompromisoSec() {
		return fechaCompromisoSec;
	}
	public void setFechaCompromisoSec(Date fechaCompromisoSec) {
		this.fechaCompromisoSec = fechaCompromisoSec;
	}
	
	public String getCategoria() {
		return Categoria;
	}
	public void setCategoria(String categoria) {
		Categoria = categoria;
	}
	/**
	 * Obtencion de logger
	 */
	protected static Logger log = LoggerFactory.getLogger(DatosPeticion.class);

	public DatosPeticion()
	{
		
	}

	public DatosPeticion(String nombreAplicacion, Long numeroPeticion) {
		this.nombreAplicacion = nombreAplicacion;
		this.numeroPeticion = numeroPeticion;
	}

	public DatosPeticion(String nombreAplicacion, Long numeroPeticion, String codigoActividad) {
		this(nombreAplicacion, numeroPeticion);
		this.codigoActividad = codigoActividad;
	}
	
	public String[][] getValoresVariables() {
		return valoresVariables;
	}

	public String getCodigoActividad() {
		return codigoActividad;
	}

	public String getCodigoAgencia() {
		return codigoAgencia;
	}

	public String getFamiliaPS() {
		return familiaPS;
	}

	public Date getFechaApertura() {
		return fechaApertura;
	}

	public Date getFechaAsignacion() {
		return fechaAsignacion;
	}

	public Date getFechaCompromiso() {
		return fechaCompromiso;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public String getNombreAplicacion() {
		return nombreAplicacion;
	}

	public Long getNumeroPeticion() {
		return numeroPeticion;
	}

	public Integer getPuntaje() {
		return puntaje;
	}

	public String getUrlDetalle() {
		return urlDetalle;
	}

	public String getUsernameResponsable() {
		return usernameResponsable;
	}

	public void setValoresVariables(String[][] strings) {
		valoresVariables = strings;
	}

	public void setCodigoActividad(String string) {
		codigoActividad = string;
	}

	public void setCodigoAgencia(String string) {
		codigoAgencia = string;
	}

	public void setFamiliaPS(String string) {
		familiaPS = string;
	}

	public void setFechaApertura(Date date) {
		fechaApertura = date;
	}

	public void setFechaAsignacion(Date date) {
		fechaAsignacion = date;
	}

	public void setFechaCompromiso(Date date) {
		fechaCompromiso = date;
	}

	public void setFechaInicio(Date date) {
		fechaInicio = date;
	}

	public void setPuntaje(Integer p) {
		puntaje = p;
	}

	public void setUrlDetalle(String string) {
		urlDetalle = string;
	}

	public void setUsernameResponsable(String string) {
		usernameResponsable = string;
	}

	public String getApellidosCliente() {
		return apellidosCliente;
	}

	public String getAreaCliente() {
		return areaCliente;
	}

	public String getNombreCliente() {
		return nombreCliente;
	}

	public String getRutCliente() {
		return rutCliente;
	}

	public String getRutdvCliente() {
		return rutdvCliente;
	}

//	public String getCodigoSegmentoCliente() {
//		return segmentoCliente;
//	}

	public String getServicioCliente() {
		return servicioCliente;
	}

	public void setApellidosCliente(String string) {
		apellidosCliente = string;
	}

	public void setAreaCliente(String string) {
		areaCliente = string;
	}

	public void setNombreAplicacion(String string) {
		nombreAplicacion = string;
	}

	public void setNombreCliente(String string) {
		nombreCliente = string;
	}

	public void setNumeroPeticion(Long numeroPeticion) {
		this.numeroPeticion = numeroPeticion;
	}

	public void setRutCliente(String string) {
		rutCliente = string;
	}

	public void setRutdvCliente(String string) {
		rutdvCliente = string;
	}

	public void setSegmentoCliente(String string) {
		segmentoCliente = string;
	}

	/**
	 * @param string
	 */
	public void setServicioCliente(String string) {
		servicioCliente = string;
	}

	/**
	 * @return
	 */
	public String getCodigoActividadNueva() {
		return codigoActividadNueva;
	}

	/**
	 * @param string
	 */
	public void setCodigoActividadNueva(String string) {
		codigoActividadNueva = string;
	}

	/**
	 * @return
	 */
	public String getSegmentoCliente() {
		return segmentoCliente;
	}

	/**
	 * @return
	 */
	public String getIsp() {
		return isp;
	}

	/**
	 * @param string
	 */
	public void setIsp(String string) {
		isp = string;
	}

	/**
	 * @return
	 */
	public String getNodo() {
		return nodo;
	}

	/**
	 * @param string
	 */
	public void setNodo(String string) {
		nodo = string;
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
	public String getIdTica() {
		return idTica;
	}

	/**
	 * @param string
	 */
	public void setIdTica(String string) {
		idTica = string;
	}
	
	public String toString(){
		StringBuffer sb = new StringBuffer();
		
		sb.append("nombreAplicacion = '" + nombreAplicacion + "'\n");
		sb.append("codigoActividad = '" + codigoActividad + "'\n");
		sb.append("codigoActividadNueva = '" + codigoActividadNueva + "'\n");
		sb.append("numeroPeticion = '" + numeroPeticion + "'\n");
		sb.append("usernameResponsable = '" + usernameResponsable + "'\n");
		sb.append("idResponsable = '" + idResponsable + "'\n");
		sb.append("codigoAgencia = '" + codigoAgencia + "'\n");
		sb.append("isp = '" + isp + "'\n");
		sb.append("nodo = '" + nodo + "'\n");
		sb.append("idTica = '" + idTica + "'\n");
		sb.append("nombreCliente = '" + nombreCliente + "'\n");
		sb.append("apellidosCliente = '" + apellidosCliente + "'\n");
		sb.append("servicioCliente = '" + servicioCliente + "'\n");
		sb.append("areaCliente = '" + areaCliente + "'\n");
		sb.append("rutCliente = '" + rutCliente + "'\n");
		sb.append("rutdvCliente = '" + rutdvCliente + "'\n");
		sb.append("segmentoCliente = '" + segmentoCliente + "'\n");
		sb.append("familiaPS = '" + familiaPS + "'\n");
		sb.append("urlDetalle = '" + urlDetalle + "'\n");
		sb.append("puntaje = '" + puntaje + "'\n");
		sb.append("fechaInicio = '" + fechaInicio + "'\n");
		sb.append("fechaApertura = '" + fechaApertura + "'\n");
		sb.append("fechaAsignacion = '" + fechaAsignacion + "'\n");
		sb.append("fechaCompromiso = '" + fechaCompromiso + "'\n");
		sb.append("estadoPeticion = '" + estadoPeticion + "'\n");
		sb.append("tipoTrabajo = '" + tipoTrabajo + "'\n");;
		sb.append("idTipoAgendamiento = '" + idTipoAgendamiento + "'\n");
		sb.append("idRango = '" + idRango + "'\n");
		sb.append("horaDesde = '" + horaDesde + "'\n");
		sb.append("horaHasta = '" + horaHasta + "'\n");
		sb.append("idGrupoSegmento = '" + idGrupoSegmento + "'\n");
		
//		if (valoresVariables != null) {
//			salida += "VALORES VARIABLES = '" + valoresVariables[0][0] + "' - '"+valoresVariables[0][1]+"'\n"
//				+ "                  - '" + valoresVariables[1][0] + "' - '"+valoresVariables[1][1]+"'\n"
//				+ "                  - '" + valoresVariables[2][0] + "' - '"+valoresVariables[2][1]+"'\n"
//				+ "                  - '" + valoresVariables[3][0] + "' - '"+valoresVariables[3][1]+"'\n";
//		}

		if (valoresVariables != null) {
			try {
					sb.append("VALORES VARIABLES = [");
					for (int i = 0; i < valoresVariables.length; i++) {
						if (i != 0)
							sb.append("                     ");
						for (int j = 0; j < valoresVariables[i].length; j++) {
							sb.append("'" + valoresVariables[i][j] + "' ");
						}
						sb.append("\n");
					}
					sb.append("]\n");;
				} catch (RuntimeException e) {
				log.debug("Problemas para imprimir valores variables de Datospeticion en toString");
			}
		}
		
		return sb.toString();
	}


	/**
	 * @return
	 */
	public Long getIdResponsable() {
		return idResponsable;
	}

	/**
	 * @param long1
	 */
	public void setIdResponsable(Long long1) {
		idResponsable = long1;
	}

	/**
	 * @return
	 */
	public Integer getIdGrupoSegmento() {
		return idGrupoSegmento;
	}

	/**
	 * @param integer
	 */
	public void setIdGrupoSegmento(Integer integer) {
		idGrupoSegmento = integer;
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
	 * @param integer
	 */
	public void setIdRango(Integer integer) {
		idRango = integer;
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
	public String getEmplazamiento() {
		return emplazamiento;
	}

	/**
	 * @return
	 */
	public String getNombreNodo() {
		return nombreNodo;
	}

	/**
	 * @param string
	 */
	public void setEmplazamiento(String string) {
		emplazamiento = string;
	}

	/**
	 * @param string
	 */
	public void setNombreNodo(String string) {
		nombreNodo = string;
	}

	/**
	 * @return
	 */
	public String getCodigoDepartamento() {
		return codigoDepartamento;
	}

	/**
	 * @return
	 */
	public String getCodigoLocalidad() {
		return codigoLocalidad;
	}

	/**
	 * @param string
	 */
	public void setCodigoDepartamento(String string) {
		codigoDepartamento = string;
	}

	/**
	 * @param string
	 */
	public void setCodigoLocalidad(String string) {
		codigoLocalidad = string;
	}

	/**
	 * @return
	 */
	public Long getCodigoCentral() {
		return codigoCentral;
	}

	/**
	 * @param string
	 */
	public void setCodigoCentral(Long long1) {
		codigoCentral = long1;
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
	public Long getNroPeticionAtis() {
		return nroPeticionAtis;
	}

	/**
	 * @param long1
	 */
	public void setNroPeticionAtis(Long long1) {
		nroPeticionAtis = long1;
	}

	/**
	 * @return
	 */
	public String getSubSegmentoCliente() {
		return subSegmentoCliente;
	}

	/**
	 * @param string
	 */
	public void setSubSegmentoCliente(String string) {
		subSegmentoCliente = string;
	}

	/**
	 * @return
	 */
	public static Logger getLog() {
		return log;
	}

	/**
	 * @return
	 */
	public Integer getTienePBX() {
		return tienePBX;
	}

	/**
	 * @param logger
	 */
	public static void setLog(Logger logger) {
		log = logger;
	}

	/**
	 * @param integer
	 */
	public void setTienePBX(Integer integer) {
		tienePBX = integer;
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
	public String getActImplCorrelID() {
		return actImplCorrelID;
	}

	/**
	 * @param string
	 */
	public void setActImplCorrelID(String string) {
		actImplCorrelID = string;
	}

}
