package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local Home interface for Enterprise Bean: Cierre_actividad
 */
public interface Cierre_actividadLocalHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates an instance from a key for Entity Bean: Cierre_actividad
	 */
	 static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/ejb/eb/Cierre_actividadLocalHome";
	 
	public co.com.telefonica.atiempo.ejb.eb.Cierre_actividadLocal create(
		java.lang.Long ciac_id)
		throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Cierre_actividad
	 */
	public co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.Cierre_actividadLocal findByPrimaryKey(
		co.com.telefonica.atiempo.ejb.eb.Cierre_actividadKey primaryKey)
		throws javax.ejb.FinderException;
	/**
	 * Creates an instance from a key for Entity Bean: Cierre_actividad
	 */
	public co.com.telefonica.atiempo.ejb.eb.Cierre_actividadLocal create(
		java.lang.Long ciac_id,
		java.lang.String ciac_nombre,
		java.lang.String ciac_codigo,
		java.lang.Short ciac_reversa,
		co.com.telefonica.atiempo.ejb.eb.ActividadLocal argFk_cierre_act)
		throws javax.ejb.CreateException;
	public co.com.telefonica.atiempo.ejb.eb.Cierre_actividadLocal findCancelarAct(java.lang.Long idActividad) throws javax.ejb.FinderException;
	public co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.Cierre_actividadLocal findByIdActAndLike(
			java.lang.Long idActividad,
			java.lang.String nombre)
		throws javax.ejb.FinderException;
	public co.com.telefonica.atiempo.ejb.eb.Cierre_actividadLocal findCancelarActividadCiacNombre(java.lang.Long idActividad) throws javax.ejb.FinderException;
	public co.com.telefonica.atiempo.ejb.eb.Cierre_actividadLocal findByCierreActividadProblema(java.lang.Long idActividad) throws javax.ejb.FinderException;
	//public java.util.Collection findAllCierreActividad() throws javax.ejb.FinderException;
}
