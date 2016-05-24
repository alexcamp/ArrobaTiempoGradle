package co.com.atiempo.dto;

public class RangoDTO {

	private Integer idRango;
	private Short habilitado;
	private String nombreRango;
	private String codigoPcom;
	private String horaDesde;
	private String horaHasta;
	private String codigoRango;
	private Integer idPadre;

	/**
	 * @return
	 */
	public String getCodigoPcom() {
		return codigoPcom;
	}

	/**
	 * @return
	 */
	public String getCodigoRango() {
		return codigoRango;
	}

	/**
	 * @return
	 */
	public Short getHabilitado() {
		return habilitado;
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
	public Integer getIdPadre() {
		return idPadre;
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
	public String getNombreRango() {
		return nombreRango;
	}

	/**
	 * @param string
	 */
	public void setCodigoPcom(String string) {
		codigoPcom = string;
	}

	/**
	 * @param string
	 */
	public void setCodigoRango(String string) {
		codigoRango = string;
	}

	/**
	 * @param short1
	 */
	public void setHabilitado(Short short1) {
		habilitado = short1;
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
	public void setIdPadre(Integer integer) {
		idPadre = integer;
	}

	/**
	 * @param integer
	 */
	public void setIdRango(Integer integer) {
		idRango = integer;
	}

	/**
	 * @param string
	 */
	public void setNombreRango(String string) {
		nombreRango = string;
	}

}
