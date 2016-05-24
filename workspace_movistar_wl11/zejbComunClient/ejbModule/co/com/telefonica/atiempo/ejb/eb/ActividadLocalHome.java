package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local Home interface for Enterprise Bean: Actividad
 */
public interface ActividadLocalHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates an instance from a key for Entity Bean: Actividad
	 */
	static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/ejb/eb/ActividadLocalHome";
	
	public co.com.telefonica.atiempo.ejb.eb.ActividadLocal create(
		java.lang.Long act_id)
		throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Actividad
	 */
	public co.com.telefonica.atiempo.ejb.eb.ActividadLocal findByPrimaryKey(
		co.com.telefonica.atiempo.ejb.eb.ActividadKey primaryKey)
		throws javax.ejb.FinderException;
	/**
	 * Creates an instance from a key for Entity Bean: Actividad
	 */
	public co.com.telefonica.atiempo.ejb.eb.ActividadLocal create(
		java.lang.Long act_id,
		java.lang.String act_codigo,
		java.lang.String act_descripcion,
		co.com.telefonica.atiempo.ejb.eb.AplicacionLocal argFk_activ_aplic,
		co.com.telefonica.atiempo.ejb.eb.RolLocal argFk_act_rol)
		throws javax.ejb.CreateException;
	public java.util.Collection findActividades(
		java.lang.Long idRol,
		java.lang.Long idAplicacion)
		throws javax.ejb.FinderException;
	public java.util.Collection findByAplicacion(java.lang.Long idAplicacion) throws javax.ejb.FinderException;
	public co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.ActividadLocal findByCodigoActividadIdAplicacion(
			java.lang.String codAct,
			java.lang.Long idAplicacion)
		throws javax.ejb.FinderException;
	public java.util.Collection findAll() throws javax.ejb.FinderException;
	public co.com.telefonica.atiempo.ejb.eb.ActividadLocal findByPK(java.lang.Long act_id) throws javax.ejb.FinderException;
	public java.util.Collection findByActi_id(
		java.lang.Integer acti_id,
		java.lang.Long idAplicacion) throws javax.ejb.FinderException;
	public java.util.Collection findByIdActIdApliIdrol(
		java.lang.Long rol,
		java.lang.Integer idAplication,
		java.lang.Integer idActividad) throws javax.ejb.FinderException;
}
