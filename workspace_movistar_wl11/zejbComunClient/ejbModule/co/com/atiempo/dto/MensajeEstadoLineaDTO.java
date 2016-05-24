/*
 * Created on 09-03-2007
 */
package co.com.atiempo.dto;

/**
 * @author Respinoza
 */
public class MensajeEstadoLineaDTO {

	/**
	 * 
	 */
	public MensajeEstadoLineaDTO() {
		super();
	}

	private Long correlativo;
	private Long petiNumero;
	private Integer codFamiliaPs;
	private Integer codConector;
	private String fechaEnvio;
	private String fechaCierre;
	private Integer codEstado;
	private Integer codActividadGeneradora;
	private Long telefono;
	private Integer area;

	


	/**
	 * @return
	 */
	public Integer getArea() {
		return area;
	}

	/**
	 * @return
	 */
	public Integer getCodActividadGeneradora() {
		return codActividadGeneradora;
	}

	/**
	 * @return
	 */
	public Integer getCodConector() {
		return codConector;
	}

	/**
	 * @return
	 */
	public Integer getCodEstado() {
		return codEstado;
	}

	/**
	 * @return
	 */
	public Integer getCodFamiliaPs() {
		return codFamiliaPs;
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
	public String getFechaCierre() {
		return fechaCierre;
	}

	/**
	 * @return
	 */
	public String getFechaEnvio() {
		return fechaEnvio;
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
	public Long getTelefono() {
		return telefono;
	}

	/**
	 * @param integer
	 */
	public void setArea(Integer integer) {
		area = integer;
	}

	/**
	 * @param integer
	 */
	public void setCodActividadGeneradora(Integer integer) {
		codActividadGeneradora = integer;
	}

	/**
	 * @param integer
	 */
	public void setCodConector(Integer integer) {
		codConector = integer;
	}

	/**
	 * @param integer
	 */
	public void setCodEstado(Integer integer) {
		codEstado = integer;
	}

	/**
	 * @param integer
	 */
	public void setCodFamiliaPs(Integer integer) {
		codFamiliaPs = integer;
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
	public void setFechaCierre(String string) {
		fechaCierre = string;
	}

	/**
	 * @param string
	 */
	public void setFechaEnvio(String string) {
		fechaEnvio = string;
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
	public void setTelefono(Long long1) {
		telefono = long1;
	}

}
