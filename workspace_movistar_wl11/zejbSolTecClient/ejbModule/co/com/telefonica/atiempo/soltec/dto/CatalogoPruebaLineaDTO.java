/*
 * Created on 13-jul-07
 */
package co.com.telefonica.atiempo.soltec.dto;

/**
 * @author rodrigo
 */
public class CatalogoPruebaLineaDTO {
	
	private Long fapsId;
	private Integer codPrueba;
	private String descripcion;
	private String grupoTrabajo;
	private String tipoIncidencia;
	/**
	 * @return
	 */
	public Integer getCodPrueba() {
		return codPrueba;
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
	public Long getFapsId() {
		return fapsId;
	}

	/**
	 * @return
	 */
	public String getGrupoTrabajo() {
		return grupoTrabajo;
	}

	/**
	 * @param integer
	 */
	public void setCodPrueba(Integer integer) {
		codPrueba = integer;
	}

	/**
	 * @param string
	 */
	public void setDescripcion(String string) {
		descripcion = string;
	}

	/**
	 * @param long1
	 */
	public void setFapsId(Long long1) {
		fapsId = long1;
	}

	/**
	 * @param string
	 */
	public void setGrupoTrabajo(String string) {
		grupoTrabajo = string;
	}

	/**
	 * @return
	 */
	public String getTipoIncidencia() {
		return tipoIncidencia;
	}

	/**
	 * @param string
	 */
	public void setTipoIncidencia(String string) {
		tipoIncidencia = string;
	}

}
