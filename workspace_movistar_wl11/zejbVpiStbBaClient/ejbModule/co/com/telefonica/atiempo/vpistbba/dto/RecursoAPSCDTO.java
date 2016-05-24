/*
 * Created on 23-feb-07
 */
package co.com.telefonica.atiempo.vpistbba.dto;

import co.com.telefonica.atiempo.utiles.DataTransferObject;

/**
 * @author TCS
 */
public class RecursoAPSCDTO implements DataTransferObject  {

	private String codigoRespuesta;
	private long id;
	private int numeroOrdenServicio;
	private int codigoReserva;
	private String armarioId;
	private String cajaId;
	private int idParCobreEnCaja;
	private int idDistribuidorPrimario;
	private String listonId;
	private int idParCobreEnListon;
	private int idCentralTelefonica;
	private String descripcionCentralTelefonica;
	private String idCableEntradaPredio;
	private String idParDentroCable;
	private String lenAsociadoTelefono;
	private int codigoDistribuidorSecundarioId;
	private String descripcionDistribuidorSecundario;

	/**
	 * @return
	 */
	public String getArmarioId() {
		return armarioId;
	}

	/**
	 * @return
	 */
	public String getCajaId() {
		return cajaId;
	}

	/**
	 * @return
	 */
	public int getCodigoDistribuidorSecundarioId() {
		return codigoDistribuidorSecundarioId;
	}

	/**
	 * @return
	 */
	public int getCodigoReserva() {
		return codigoReserva;
	}

	/**
	 * @return
	 */
	public String getCodigoRespuesta() {
		return codigoRespuesta;
	}

	/**
	 * @return
	 */
	public String getDescripcionCentralTelefonica() {
		return descripcionCentralTelefonica;
	}

	/**
	 * @return
	 */
	public String getDescripcionDistribuidorSecundario() {
		return descripcionDistribuidorSecundario;
	}

	/**
	 * @return
	 */
	public long getId() {
		return id;
	}

	/**
	 * @return
	 */
	public String getIdCableEntradaPredio() {
		return idCableEntradaPredio;
	}

	/**
	 * @return
	 */
	public int getIdCentralTelefonica() {
		return idCentralTelefonica;
	}

	/**
	 * @return
	 */
	public int getIdDistribuidorPrimario() {
		return idDistribuidorPrimario;
	}

	/**
	 * @return
	 */
	public int getIdParCobreEnCaja() {
		return idParCobreEnCaja;
	}

	/**
	 * @return
	 */
	public int getIdParCobreEnListon() {
		return idParCobreEnListon;
	}

	/**
	 * @return
	 */
	public String getIdParDentroCable() {
		return idParDentroCable;
	}

	/**
	 * @return
	 */
	public String getLenAsociadoTelefono() {
		return lenAsociadoTelefono;
	}

	/**
	 * @return
	 */
	public String getListonId() {
		return listonId;
	}

	/**
	 * @return
	 */
	public int getNumeroOrdenServicio() {
		return numeroOrdenServicio;
	}

	/**
	 * @param string
	 */
	public void setArmarioId(String string) {
		armarioId = string;
	}

	/**
	 * @param string
	 */
	public void setCajaId(String string) {
		cajaId = string;
	}

	/**
	 * @param integer
	 */
	public void setCodigoDistribuidorSecundarioId(int integer) {
		codigoDistribuidorSecundarioId = integer;
	}

	/**
	 * @param integer
	 */
	public void setCodigoReserva(int integer) {
		codigoReserva = integer;
	}

	/**
	 * @param string
	 */
	public void setCodigoRespuesta(String string) {
		codigoRespuesta = string;
	}

	/**
	 * @param string
	 */
	public void setDescripcionCentralTelefonica(String string) {
		descripcionCentralTelefonica = string;
	}

	/**
	 * @param string
	 */
	public void setDescripcionDistribuidorSecundario(String string) {
		descripcionDistribuidorSecundario = string;
	}

	/**
	 * @param long1
	 */
	public void setId(long long1) {
		id = long1;
	}

	/**
	 * @param string
	 */
	public void setIdCableEntradaPredio(String string) {
		idCableEntradaPredio = string;
	}

	/**
	 * @param integer
	 */
	public void setIdCentralTelefonica(int integer) {
		idCentralTelefonica = integer;
	}

	/**
	 * @param integer
	 */
	public void setIdDistribuidorPrimario(int integer) {
		idDistribuidorPrimario = integer;
	}

	/**
	 * @param integer
	 */
	public void setIdParCobreEnCaja(int integer) {
		idParCobreEnCaja = integer;
	}

	/**
	 * @param integer
	 */
	public void setIdParCobreEnListon(int integer) {
		idParCobreEnListon = integer;
	}

	/**
	 * @param string
	 */
	public void setIdParDentroCable(String string) {
		idParDentroCable = string;
	}

	/**
	 * @param string
	 */
	public void setLenAsociadoTelefono(String string) {
		lenAsociadoTelefono = string;
	}

	/**
	 * @param string
	 */
	public void setListonId(String string) {
		listonId = string;
	}

	/**
	 * @param integer
	 */
	public void setNumeroOrdenServicio(int integer) {
		numeroOrdenServicio = integer;
	}

}
