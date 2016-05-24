/*
 * Created on 24-05-2007
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package co.com.atiempo.dto;


public class PsPetCausalONovedad
{
	private Long idPs;
	private Long correlativoPs;
	private Long idCausa;
	private String descripcion;
	private Long codEstadoCierre;
	private Long codActividad;
	private String fechaInicio;
	private String fechaTermino;
	private Long usua_id;

	/**
	 * @return
	 */
	public Long getCodActividad() {
		return codActividad;
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
	public String getDescripcion() {
		return descripcion;
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
	public Long getIdCausa() {
		return idCausa;
	}

	/**
	 * @return
	 */
	public Long getIdPs() {
		return idPs;
	}

	/**
	 * @return
	 */
	public Long getUsua_id() {
		return usua_id;
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
	public void setCodEstadoCierre(Long long1) {
		codEstadoCierre = long1;
	}

	/**
	 * @param string
	 */
	public void setDescripcion(String string) {
		descripcion = string;
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
	public void setIdCausa(Long long1) {
		idCausa = long1;
	}

	/**
	 * @param long1
	 */
	public void setIdPs(Long long1) {
		idPs = long1;
	}

	/**
	 * @param long1
	 */
	public void setUsua_id(Long long1) {
		usua_id = long1;
	}

	/**
	 * @return
	 */
	public Long getCorrelativoPs() {
		return correlativoPs;
	}

	/**
	 * @param long1
	 */
	public void setCorrelativoPs(Long long1) {
		correlativoPs = long1;
	}

}
