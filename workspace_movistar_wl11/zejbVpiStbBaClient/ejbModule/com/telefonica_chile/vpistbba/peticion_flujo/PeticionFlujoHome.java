package com.telefonica_chile.vpistbba.peticion_flujo;
/**
 * Home interface for Enterprise Bean: PeticionFlujo
 */
public interface PeticionFlujoHome extends javax.ejb.EJBHome {
	
	public static final String JNDI_NAME = "ejb/com/telefonica_chile/vpistbba/peticion_flujo/PeticionFlujoLocalHome";
	
	/**
	 * Creates an instance from a key for Entity Bean: PeticionFlujo
	 */
	public com.telefonica_chile.vpistbba.peticion_flujo.PeticionFlujo create(
		java.lang.Integer id)
		throws javax.ejb.CreateException, java.rmi.RemoteException;
	/**
	 * Finds an instance using a key for Entity Bean: PeticionFlujo
	 */
	public com
		.telefonica_chile
		.vpistbba
		.peticion_flujo
		.PeticionFlujo findByPrimaryKey(java.lang.Integer primaryKey)
		throws javax.ejb.FinderException, java.rmi.RemoteException;
	/**
	 * create
	 */
	public com.telefonica_chile.vpistbba.peticion_flujo.PeticionFlujo create(
		java.lang.Long newIdPeticion,
		java.lang.Long newIdPRSE,
		java.lang.Long newIdOperacionComercial,
		java.lang.Integer newIdActividad,
		java.lang.Integer newOrden)
		throws javax.ejb.CreateException, java.rmi.RemoteException;
	/**
	 * create
	 */
	public com.telefonica_chile.vpistbba.peticion_flujo.PeticionFlujo create(
		java.lang.Long newIdPeticion,
		java.lang.Integer newIdActividad,
		java.lang.Integer newOrden,
		java.lang.String newEstado)
		throws javax.ejb.CreateException, java.rmi.RemoteException;
	/**
	 * findActividadesByOrden
	 */
	public java.util.Collection findActividadesByOrden(
		java.lang.Long idPeticion,
		java.lang.Integer orden)
		throws javax.ejb.FinderException, java.rmi.RemoteException;
	/**
	 * findPendientes
	 */
	public java.util.Collection findPendientes(
		java.lang.Long idPeticion,
		java.lang.Integer numeroOrden)
		throws javax.ejb.FinderException, java.rmi.RemoteException;
	/**
	 * findMensajeEntrada
	 */
	public java.util.Collection findMensajeEntrada(java.lang.Long idPeticion)
		throws javax.ejb.FinderException, java.rmi.RemoteException;
	/**
	 * findActividad
	 */
	public java.util.Collection findActividad(
		java.lang.Long idPeticion,
		java.lang.Integer idActividad)
		throws javax.ejb.FinderException, java.rmi.RemoteException;
}
