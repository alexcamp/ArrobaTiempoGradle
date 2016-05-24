/*
 * Created on 26-feb-07
 */
package co.com.telefonica.atiempo.vpistbba.dto;

import co.com.telefonica.atiempo.utiles.DataTransferObject;

/**
 * @author TCS
 */
public class RangoDTO implements DataTransferObject {

	private String id = "";
	private String codigo = "";
	private String horaDesde = "";
	private String horaHasta = "";

	public RangoDTO() {
	}
	/**
	 * @return
	 */
	public String getCodigo() {
		return codigo;
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
	public String getId() {
		return id;
	}

	/**
	 * @param string
	 */
	public void setCodigo(String string) {
		codigo = string;
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
	 * @param string
	 */
	public void setId(String string) {
		id = string;
	}

}
