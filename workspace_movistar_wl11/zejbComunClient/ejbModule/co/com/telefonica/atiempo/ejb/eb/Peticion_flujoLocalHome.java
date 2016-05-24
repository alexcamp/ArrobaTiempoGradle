package co.com.telefonica.atiempo.ejb.eb;

import java.util.Collection;
/**
 * Local Home interface for Enterprise Bean: Peticion_flujo
 */
public interface Peticion_flujoLocalHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates an instance from a key for Entity Bean: Peticion_flujo
	 */
	 static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/ejb/eb/Peticion_flujoLocalHome";
	 
	/**
		 * Creates an instance from a key for Entity Bean: PeticionFlujo
		 */
	public java.util.Collection findActividadesByOrden(java.lang.Long idPeticion, java.lang.Integer orden) throws javax.ejb.FinderException;
	public java.util.Collection findPendientes(java.lang.Long idPeticion, java.lang.Integer numeroOrden) throws javax.ejb.FinderException;
	public Collection findActividad(Long idPeticion, Integer idActividad) throws javax.ejb.FinderException;
	public java.util.Collection findByIdPeticion(java.lang.Long idPeticion)
		throws javax.ejb.FinderException;
	public java.util.Collection findByActividadEstado(java.lang.Long idPeticion, java.lang.Integer idActividad) throws javax.ejb.FinderException;
	public java.util.Collection findByPeticionOrden(java.lang.Long idPeticion)
		throws javax.ejb.FinderException;
	public java.util.Collection findByIdPeticionOrdenado(java.lang.Long idPeticion) throws javax.ejb.FinderException;
	public java.util.Collection findByIdActividadAndPeticion(
		java.lang.Integer idActividad,
		java.lang.Long numeroPeticion)
		throws javax.ejb.FinderException;
	public co.com.telefonica.atiempo.ejb.eb.Peticion_flujoLocal create(
		java.lang.Integer pefl_id)
		throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Peticion_flujo
	 */
	public co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.Peticion_flujoLocal findByPrimaryKey(
		co.com.telefonica.atiempo.ejb.eb.Peticion_flujoKey primaryKey)
		throws javax.ejb.FinderException;
	/**
	 * Creates an instance from a key for Entity Bean: Peticion_flujo
	 */
	public co.com.telefonica.atiempo.ejb.eb.Peticion_flujoLocal create(
		java.lang.Integer pefl_id,
		java.lang.Integer pefl_orden,
		Long prse_id,
		String pefl_estado,
		PeticionLocal argFk_peti_2_pefl,
		Actividad_flujoLocal argFk_acti_2_pefl,
		Operacion_comercialLocal fk_opco)
		throws javax.ejb.CreateException;
	/**
	 * ejbCreate
	 */
	public co.com.telefonica.atiempo.ejb.eb.Peticion_flujoLocal create(
		Integer pefl_id,
		java.lang.Long newIdPeticion,
		java.lang.Long newIdPRSE,
		java.lang.Long newIdOperacionComercial,
		java.lang.Integer newIdActividad,
		java.lang.Integer newOrden)
		throws javax.ejb.CreateException;
	public java.util.Collection findByIdActividadFPetPsOC(
		java.lang.Integer idActividad,
		java.lang.Long nroPet,
		java.lang.Long idPs,
		java.lang.Long opCo)
		throws javax.ejb.FinderException;
	public java.util.Collection findByActividad(java.lang.Long idPeticion, java.lang.Integer idActividad1, java.lang.Integer idActividad2) throws javax.ejb.FinderException;
}
