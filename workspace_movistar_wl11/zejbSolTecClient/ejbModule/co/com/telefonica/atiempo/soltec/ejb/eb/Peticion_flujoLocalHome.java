package co.com.telefonica.atiempo.soltec.ejb.eb;
/**
 * Local Home interface for Enterprise Bean: Peticion_flujo
 */
public interface Peticion_flujoLocalHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates an instance from a key for Entity Bean: Peticion_flujo
	 */
	static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/soltec/ejb/eb/Peticion_flujoLocalHome";
	
	/**
	 * Creates an instance from a key for Entity Bean: Peticion_flujo
	 */
	public co.com.telefonica.atiempo.soltec.ejb.eb.Peticion_flujoLocal create(
		java.lang.Integer pefl_id)
		throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Peticion_flujo
	 */
	public co
		.com
		.telefonica
		.atiempo
		.soltec
		.ejb
		.eb
		.Peticion_flujoLocal findByPrimaryKey(
			co
				.com
				.telefonica
				.atiempo
				.soltec
				.ejb
				.eb
				.Peticion_flujoKey primaryKey)
		throws javax.ejb.FinderException;
	public java.util.Collection findActividad(
		java.lang.Long cod_ave_cd,
		java.lang.Long idActividad)
		throws javax.ejb.FinderException;
	public java.util.Collection findActividadesByOrden(java.lang.Long cod_ave_cd, java.lang.Integer orden) throws javax.ejb.FinderException;
	public java.util.Collection findPendientes(java.lang.Long cod_ave_cd, java.lang.Integer nroOrden) throws javax.ejb.FinderException;
	public java.util.Collection findActividad(java.lang.Long cod_ave_cd, java.lang.Integer idActividad) throws javax.ejb.FinderException;
	public java.util.Collection findByIdPeticion(java.lang.Long cod_ave_cd) throws javax.ejb.FinderException;
	public java.util.Collection findByActividadEstado(
		java.lang.Long cod_ave_cd,
		java.lang.Integer idActividad)
		throws javax.ejb.FinderException;
	public java.util.Collection findByPeticionOrden(java.lang.Long cod_ave_cd)
		throws javax.ejb.FinderException;
		
	public Peticion_flujoLocal create(
		Integer pefl_id,
		java.lang.Long newIdPeticion,
		java.lang.Long newIdPRSE,
		java.lang.Long newIdOperacionComercial,
		java.lang.Integer newIdActividad,
		java.lang.Integer newOrden)
		throws javax.ejb.CreateException;		
}
