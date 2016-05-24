/*
 * Created on Jun 16, 2011
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package co.com.telefonica.atiempo.interfaces.atiempo;

import co.com.telefonica.atiempo.util.EqualityUtilities;

/**
 * @author damartinezv
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class TR805E extends RequestHeaderAgendaSC {
	private String idSistemaOrigen;

	private String idSchedule;

	private String apptNumber;

	private String idpcTv;

	private String numeroCliente;

	private String tipoDocumCliente;

	private String nombreCliente;

	private String ciudad;

	private String accion;

	private String idPedidoAtis;

	public int hashCode() {
		return idSchedule.hashCode();
	}

	public boolean equals(Object arg0) {
		if (arg0 instanceof TR805E) {
			TR805E other = (TR805E) arg0;
			return super.equals(arg0)
					&& EqualityUtilities.equals(idSistemaOrigen,
							other.idSistemaOrigen)
					&& EqualityUtilities.equals(idSchedule, other.idSchedule)
					&& EqualityUtilities.equals(apptNumber, other.apptNumber)
					&& EqualityUtilities.equals(idPedidoAtis,
							other.idPedidoAtis)
					&& EqualityUtilities.equals(idpcTv, other.idpcTv)
					&& EqualityUtilities.equals(numeroCliente,
							other.numeroCliente)
					&& EqualityUtilities.equals(tipoDocumCliente,
							other.tipoDocumCliente)
					&& EqualityUtilities.equals(nombreCliente,
							other.nombreCliente)
					&& EqualityUtilities.equals(ciudad, other.ciudad)
					&& EqualityUtilities.equals(accion, other.accion);
		}
		return false;
	}

	public String getIdSchedule() {
		return idSchedule;
	}

	public void setIdSchedule(String idSchedule) {
		this.idSchedule = idSchedule;
	}

	/**
	 * @return Devuelve apptNumber.
	 */
	public String getApptNumber() {
		return apptNumber;
	}

	/**
	 * @param apptNumber
	 *            El apptNumber a establecer.
	 */
	public void setApptNumber(String apptNumber) {
		this.apptNumber = apptNumber;
	}

	/**
	 * @return Devuelve idPedidoAtis.
	 */
	public String getIdPedidoAtis() {
		return idPedidoAtis;
	}

	/**
	 * @param idPedidoAtis
	 *            El idPedidoAtis a establecer.
	 */
	public void setIdPedidoAtis(String idPedidoAtis) {
		this.idPedidoAtis = idPedidoAtis;
	}

	/**
	 * @return Devuelve idSistemaOrigen.
	 */
	public String getIdSistemaOrigen() {
		return idSistemaOrigen;
	}

	/**
	 * @param idSistemaOrigen
	 *            El idSistemaOrigen a establecer.
	 */
	public void setIdSistemaOrigen(String idSistemaOrigen) {
		this.idSistemaOrigen = idSistemaOrigen;
	}

	/**
	 * @return Devuelve accion.
	 */
	public String getAccion() {
		return accion;
	}

	/**
	 * @param accion
	 *            El accion a establecer.
	 */
	public void setAccion(String accion) {
		this.accion = accion;
	}

	/**
	 * @return Devuelve ciudad.
	 */
	public String getCiudad() {
		return ciudad;
	}

	/**
	 * @param ciudad
	 *            El ciudad a establecer.
	 */
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	/**
	 * @return Devuelve idpcTv.
	 */
	public String getIdpcTv() {
		return idpcTv;
	}

	/**
	 * @param idpcTv
	 *            El idpcTv a establecer.
	 */
	public void setIdpcTv(String idpcTv) {
		this.idpcTv = idpcTv;
	}

	/**
	 * @return Devuelve nombreCliente.
	 */
	public String getNombreCliente() {
		return nombreCliente;
	}

	/**
	 * @param nombreCliente
	 *            El nombreCliente a establecer.
	 */
	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	/**
	 * @return Devuelve numeroCliente.
	 */
	public String getNumeroCliente() {
		return numeroCliente;
	}

	/**
	 * @param numeroCliente
	 *            El numeroCliente a establecer.
	 */
	public void setNumeroCliente(String numeroCliente) {
		this.numeroCliente = numeroCliente;
	}

	/**
	 * @return Devuelve tipoDocumCliente.
	 */
	public String getTipoDocumCliente() {
		return tipoDocumCliente;
	}

	/**
	 * @param tipoDocumCliente
	 *            El tipoDocumCliente a establecer.
	 */
	public void setTipoDocumCliente(String tipoDocumCliente) {
		this.tipoDocumCliente = tipoDocumCliente;
	}
}