package co.com.telefonica.atiempo.soltec.dto;

import co.com.telefonica.atiempo.utiles.DataTransferObject;

/**
 * @author admin
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class InformacionBandejaDTO implements DataTransferObject {

	private String cdAveria;
	private String cdActividad;
	private String idReclamante;
	private String idProductoComercial;
	private String idProductoServicio;
	private String idTipo;
	private String idEstado;
	private String cdSegmento;
	private String idOrigen;
	private String idPropietario;
	private String fecDiagnostico;
	private String idDiagnostico;
	private String estActividad;
	private String estSemCompromiso;
	private String estSemActividad;
		
	public InformacionBandejaDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @return
	 */
	public String getCdAveria() {
		return cdAveria;
	}

	/**
	 * @return
	 */
	public String getCdActividad() {
		return cdActividad;
	}

	/**
	 * @return
	 */
	public String getIdReclamante() {
		return idReclamante;
	}

	/**
	 * @return
	 */
	public String getIdProductoComercial() {
		return idProductoComercial;
	}

	/**
	 * @return
	 */
	public String getIdProductoServicio() {
		return idProductoServicio;
	}

	/**
	 * @return
	 */
	public String getIdTipo() {
		return idTipo;
	}

	/**
	 * @return
	 */
	public String getIdEstado() {
		return idEstado;
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
	public String getFecDiagnostico() {
		return fecDiagnostico;
	}

	/**
	 * @return
	 */
	public String getIdDiagnostico() {
		return idDiagnostico;
	}

	/**
	 * @return
	 */
	public String getEstActividad() {
		return estActividad;
	}


	/**
	 * @param string
	 */
	public void setIdOrigen(String string) {
		idOrigen = string;
	}


	public String getIdOrigen() {
		return idOrigen;
	}

	public String getBiSemaforoCompromiso() {
		return estSemCompromiso;
	}
	
	public String getBiSemaforoActividad() {
		return estSemActividad;
	}
	/**
	 * @param string
	 */
	public void setIdPropietario(String string) {
		idPropietario = string;
	}


	public String getIdPropietario() {
		return idPropietario;
	}

	/**
	 * @param string
	 */
	public void setCdAveria(String string) {
		cdAveria = string;
	}
	/**
	 * @param string
	 */
	public void setCdActividad(String string) {
		cdActividad = string;
	}

	/**
	 * @param string
	 */
	public void setIdReclamante(String string) {
		idReclamante = string;
	}

	/**
	 * @param string
	 */
	public void setIdProductoComercial(String string) {
		idProductoComercial = string;
	}

	/**
	 * @param string
	 */
	public void setIdProductoServicio(String string) {
		idProductoServicio = string;
	}

	/**
	 * @param string
	 */
	public void setFecDiagnostico(String string) {
		fecDiagnostico = string;
	}

	/**
	 * @param string
	 */
	public void setIdTipo(String string) {
		idTipo = string;
	}

	/**
	 * @param string
	 */
	public void setIdEstado(String string) {
		idEstado = string;
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
	public void setIdDiagnostico(String string) {
		idDiagnostico = string;
	}

	/**
	 * @param string
	 */
	public void setEstActividad(String string) {
		estActividad = string;
	}

	public void setBiSemaforoCompromiso(String string){
		estSemCompromiso=string;
	}

	public void setBiSemaforoActividad(String string){
		estSemActividad = string;
	}
	
	public InformacionBandejaDTO obtenerInformacionSolucion(){
		
		InformacionBandejaDTO infoSolucion = new InformacionBandejaDTO();
		// esto es PROVISORIO, se crean datos directamente.
		infoSolucion.setCdAveria("10012976");
		infoSolucion.setCdActividad("Plataforma Diagnostico Planta Externa");
		infoSolucion.setIdReclamante("Maria Luisa Castro");
		infoSolucion.setIdProductoComercial("xxx");
		infoSolucion.setIdProductoServicio("xyz");
		infoSolucion.setIdPropietario("ciquira");
		infoSolucion.setIdTipo("Reclamo");
		infoSolucion.setIdEstado("Abierto");
		infoSolucion.setIdOrigen("Atención CLiente 104");
		infoSolucion.setCdSegmento("Cuenta controlada");
		infoSolucion.setFecDiagnostico("14/03/2007 11:35");
		infoSolucion.setIdDiagnostico("abc");
		infoSolucion.setEstActividad("1");
		infoSolucion.setBiSemaforoCompromiso("V");
		infoSolucion.setBiSemaforoActividad("A");
				
		// retorno los datos recuperados
		return infoSolucion;
	}
	
}