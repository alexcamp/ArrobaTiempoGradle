package com.telefonica_chile.vpistbba.peticion_flujo;
/**
 * Remote interface for Enterprise Bean: PeticionFlujo
 */
public interface PeticionFlujo extends javax.ejb.EJBObject {
	/**
	 * getIdActividad
	 */
	public java.lang.Integer getIdActividad() throws java.rmi.RemoteException;
	/**
	 * setIdActividad
	 */
	public void setIdActividad(java.lang.Integer newIdActividad)
		throws java.rmi.RemoteException;
	/**
	 * getOrden
	 */
	public java.lang.Integer getOrden() throws java.rmi.RemoteException;
	/**
	 * setOrden
	 */
	public void setOrden(java.lang.Integer newOrden)
		throws java.rmi.RemoteException;
	/**
	 * getIdOperacionComercial
	 */
	public java.lang.Long getIdOperacionComercial()
		throws java.rmi.RemoteException;
	/**
	 * setIdOperacionComercial
	 */
	public void setIdOperacionComercial(java.lang.Long newIdOperacionComercial)
		throws java.rmi.RemoteException;
	/**
	 * getIdPRSE
	 */
	public java.lang.Long getIdPRSE() throws java.rmi.RemoteException;
	/**
	 * setIdPRSE
	 */
	public void setIdPRSE(java.lang.Long newIdPRSE)
		throws java.rmi.RemoteException;
	/**
	 * getIdPeticion
	 */
	public java.lang.Long getIdPeticion() throws java.rmi.RemoteException;
	/**
	 * setIdPeticion
	 */
	public void setIdPeticion(java.lang.Long newIdPeticion)
		throws java.rmi.RemoteException;
	/**
	 * getEstado
	 */
	public java.lang.String getEstado() throws java.rmi.RemoteException;
	/**
	 * setEstado
	 */
	public void setEstado(java.lang.String newEstado)
		throws java.rmi.RemoteException;
}
