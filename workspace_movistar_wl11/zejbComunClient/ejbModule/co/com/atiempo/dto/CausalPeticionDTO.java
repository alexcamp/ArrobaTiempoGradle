/*
 * Created on 15-03-2007
 */
package co.com.atiempo.dto;

/**
 * @author Respinoza
 */
public class CausalPeticionDTO {
	

	public CausalPeticionDTO() {
		super();
	}
	
	private Long correlativo;
	private Long codActividad;
	private String novedad;
	private Long codEstadoCierre;
	private Long codCausal;
	private String fechaInicio;
	private String fechaTermino;
	private Long idCausalPeticion;
	private Long usuaId;
	private String usuaNombre;

	/**
	 * @return
	 */
	public Long getCodActividad() {
		return codActividad;
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
	public Long getCodEstadoCierre() {
		return codEstadoCierre;
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
	public String getFechaInicio() {
		return fechaInicio;
	}

	/**
	 * @return
	 */
	public String getFechaTermino() {
		return fechaTermino;
	}

	/**
	 * @return
	 */
	public Long getIdCausalPeticion() {
		return idCausalPeticion;
	}

	/**
	 * @return
	 */
	public String getNovedad() {
		return novedad;
	}

	/**
	 * @param long1
	 */
	public void setCodActividad(Long long1) {
		codActividad = long1;
	}

	/**
	 * @param long1
	 */
	public void setCodCausal(Long long1) {
		codCausal = long1;
	}

	/**
	 * @param long1
	 */
	public void setCodEstadoCierre(Long long1) {
		codEstadoCierre = long1;
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
	public void setFechaInicio(String string) {
		fechaInicio = string;
	}

	/**
	 * @param string
	 */
	public void setFechaTermino(String string) {
		fechaTermino = string;
	}

	/**
	 * @param long1
	 */
	public void setIdCausalPeticion(Long long1) {
		idCausalPeticion = long1;
	}

	/**
	 * @param string
	 */
	public void setNovedad(String string) {
		novedad = string;
	}
	/**
	 * @return
	 */
	public Long getUsuaId() {
		return usuaId;
	}

	/**
	 * @return
	 */
	public String getUsuaNombre() {
		return usuaNombre;
	}

	/**
	 * @param long1
	 */
	public void setUsuaId(Long long1) {
		usuaId = long1;
	}

	/**
	 * @param string
	 */
	public void setUsuaNombre(String string) {
		usuaNombre = string;
	}

}
