/*
 * Created on 26-feb-07
 */
package co.com.telefonica.atiempo.vpistbba.dto;

import co.com.telefonica.atiempo.utiles.DataTransferObject;

/**
 * @author TCS
 */
public class ClienteATISDTO implements DataTransferObject {

	public Long clienteId;
	public String rut;
	public String digitoVerificador;
	public String nombre;
	public String primerApellido;
	public String segundoApellido;
	public Long codigoSegmento;
	public Long codigoSubSegmento;
	public String tipoDocumento;

	/**
	 * @return
	 */
	public Long getClienteId() {
		return clienteId;
	}

	/**
	 * @return
	 */
	public Long getCodigoSegmento() {
		return codigoSegmento;
	}

	/**
	 * @return
	 */
	public Long getCodigoSubSegmento() {
		return codigoSubSegmento;
	}

	/**
	 * @return
	 */
	public String getDigitoVerificador() {
		return digitoVerificador;
	}

	/**
	 * @return
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @return
	 */
	public String getPrimerApellido() {
		return primerApellido;
	}

	/**
	 * @return
	 */
	public String getRut() {
		return rut;
	}

	/**
	 * @return
	 */
	public String getSegundoApellido() {
		return segundoApellido;
	}

	/**
	 * @return
	 */
	public String getTipoDocumento() {
		return tipoDocumento;
	}

	/**
	 * @param long1
	 */
	public void setClienteId(Long long1) {
		clienteId = long1;
	}

	/**
	 * @param long1
	 */
	public void setCodigoSegmento(Long long1) {
		codigoSegmento = long1;
	}

	/**
	 * @param long1
	 */
	public void setCodigoSubSegmento(Long long1) {
		codigoSubSegmento = long1;
	}

	/**
	 * @param string
	 */
	public void setDigitoVerificador(String string) {
		digitoVerificador = string;
	}

	/**
	 * @param string
	 */
	public void setNombre(String string) {
		nombre = string;
	}

	/**
	 * @param string
	 */
	public void setPrimerApellido(String string) {
		primerApellido = string;
	}

	/**
	 * @param string
	 */
	public void setRut(String string) {
		rut = string;
	}

	/**
	 * @param string
	 */
	public void setSegundoApellido(String string) {
		segundoApellido = string;
	}

	/**
	 * @param string
	 */
	public void setTipoDocumento(String string) {
		tipoDocumento = string;
	}

}
