package co.com.telefonica.atiempo.ejb.eb;

import java.util.Date;

import javax.ejb.CreateException;
/**
 * Local Home interface for Enterprise Bean: Bitacora_peticion
 */
public interface Bitacora_peticionLocalHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates an instance from a key for Entity Bean: Bitacora_peticion
	 */
	 static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/ejb/eb/Bitacora_peticionLocalHome";
	public co.com.telefonica.atiempo.ejb.eb.Bitacora_peticionLocal create(
		java.lang.Long bipe_id)
		throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Bitacora_peticion
	 */
	public co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.Bitacora_peticionLocal findByPrimaryKey(
		co.com.telefonica.atiempo.ejb.eb.Bitacora_peticionKey primaryKey)
		throws javax.ejb.FinderException;
	/**
	 * Creates an instance from a key for Entity Bean: Bitacora_peticion
	 */
	public co.com.telefonica.atiempo.ejb.eb.Bitacora_peticionLocal create(
		java.lang.Long bipe_id,
		java.lang.Long usua_id,
		java.sql.Timestamp bipe_fecha_inicio,
		co.com.telefonica.atiempo.ejb.eb.ActividadLocal argFk_acti_2_bipe,
		co.com.telefonica.atiempo.ejb.eb.PeticionLocal argFk_peticion)
		throws javax.ejb.CreateException;

	/**
	 * @param idActividad
	 * @param object
	 * @param idPeticion
	 * @param idUsuario
	 * @param date
	 * @param object2
	 * @param object3
	 * @param esReversa
	 * @return
	 */
	public co.com.telefonica.atiempo.ejb.eb.Bitacora_peticionLocal create(
		Long idActividad,
		Long idCausa,
		Long peticion,
		Long usuaId,
		Date bipeFechaInicio,
		Date bipeFechaFin,
		String bipeObservacion,
		Short esReversa)
		throws CreateException;

	public java.util.Collection findbyNumeroPeticion(java.lang.Long idPeticion) throws javax.ejb.FinderException;
//	public java.util.Collection findFechaFin(java.lang.Long idPeticion) throws javax.ejb.FinderException;
//
	public java.util.Collection findByPetiOrden(java.lang.Long idPeticion) throws javax.ejb.FinderException;
	public Bitacora_peticionLocal findByPeticionActividad(java.lang.Long idPeticion, java.lang.Long idActividad) throws javax.ejb.FinderException;
	public Bitacora_peticionLocal findByPeticionActividadUsuario(java.lang.Long idPeticion, java.lang.Long idActividad, java.lang.Long idUsuario)
		throws javax.ejb.FinderException;
//	//public Bitacora_peticionLocal findNumPeticionActividad(java.lang.Long numPeticion) throws javax.ejb.FinderException;
//	public java.util.Collection findByPeticionFechaTerminoNula(java.lang.Long nroPeticion) throws javax.ejb.FinderException;
//	public Bitacora_peticionLocal findObsPeticionActividad(java.lang.Long numeroPeticion, java.lang.Long numeroActividad)
//		throws javax.ejb.FinderException;
//	public Bitacora_peticionLocal findByCodigoObservacionPeticion(java.lang.Long peticion) throws javax.ejb.FinderException;
//	public Bitacora_peticionLocal findByIdPeticionAndMdfTerminada(java.lang.Long idPeticion) throws javax.ejb.FinderException;
//	
	public java.util.Collection findbyPeticionRol(java.lang.Long idPeticion, java.lang.Long idRol) throws javax.ejb.FinderException;
//	public java.util.Collection findByPeticionActividadNoNula(java.lang.Long idPeticion, java.lang.Long idActividad) throws javax.ejb.FinderException;
	public java.util.Collection findByPeticionActividadCerrada(java.lang.Long petiNumero, java.lang.Long actId) throws javax.ejb.FinderException;
	public java.util.Collection findPreviuosActivity(java.lang.Long petiNumero)
		throws javax.ejb.FinderException;
	public java.util.Collection findbypeticionfechafin(java.lang.Long idPeticion)
		throws javax.ejb.FinderException;
	public java.util.Collection findByOpenAct(java.lang.Long idActividad)
		throws javax.ejb.FinderException;
	public co.com.telefonica.atiempo.ejb.eb.Bitacora_peticionLocal findbyMaxActivity(java.lang.Long idPeticion, java.lang.Long idActividad) throws javax.ejb.FinderException;
}
