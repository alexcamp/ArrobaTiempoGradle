package co.com.telefonica.atiempo.soltec.ejb.eb;

import java.util.Date;

import javax.ejb.CreateException;

/**
 * Local Home interface for Enterprise Bean: Bitacora_peticion
 */
public interface Bitacora_peticionLocalHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates an instance from a key for Entity Bean: Bitacora_peticion
	 */
	static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/soltec/ejb/eb/Bitacora_peticionLocalHome";
	
	/**
	 * Creates an instance from a key for Entity Bean: Bitacora_peticion
	 */
	public co
		.com
		.telefonica
		.atiempo
		.soltec
		.ejb
		.eb
		.Bitacora_peticionLocal create(
		java.lang.Long bipe_id)
		throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Bitacora_peticion
	 */
	public co
		.com
		.telefonica
		.atiempo
		.soltec
		.ejb
		.eb
		.Bitacora_peticionLocal findByPrimaryKey(
			co
				.com
				.telefonica
				.atiempo
				.soltec
				.ejb
				.eb
				.Bitacora_peticionKey primaryKey)
		throws javax.ejb.FinderException;
		
	public co.com.telefonica.atiempo.soltec.ejb.eb.Bitacora_peticionLocal create(
		Long idActividad,
		Long idCausa,
		Long peticion,
		Long usuaId,
		Date bipeFechaInicio,
		Date bipeFechaFin,
		String bipeObservacion,
		Short esReversa)
		throws CreateException;
		
	public co
		.com
		.telefonica
		.atiempo
		.soltec
		.ejb
		.eb
		.Bitacora_peticionLocal findByPeticionActividad(
			java.lang.Long idPeticion,
			java.lang.Long IdActividad)
		throws javax.ejb.FinderException;
	public java.util.Collection findByPetiOrden(java.lang.Long cod_ave_cd) throws javax.ejb.FinderException;
	public co.com.telefonica.atiempo.soltec.ejb.eb.Bitacora_peticionLocal findByPeticionActividadUsuario(java.lang.Long cod_ave_cd, java.lang.Long idActividad, java.lang.Long usua_id) throws javax.ejb.FinderException;
	public java.util.Collection findbyNumeroPeticion(java.lang.Long cod_ave_cd) throws javax.ejb.FinderException;
	public java.util.Collection findbyPeticionUsuario(java.lang.Long cod_ave_cd, java.lang.Long usua_id) throws javax.ejb.FinderException;
}
