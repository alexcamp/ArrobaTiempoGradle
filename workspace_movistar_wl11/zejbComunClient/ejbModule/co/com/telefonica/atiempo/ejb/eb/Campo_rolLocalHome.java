package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local Home interface for Enterprise Bean: Campo_rol
 */
public interface Campo_rolLocalHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates an instance from a key for Entity Bean: Campo_rol
	 */
	 static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/ejb/eb/Campo_rolLocalHome";
	public co.com.telefonica.atiempo.ejb.eb.Campo_rolLocal create(
		co.com.telefonica.atiempo.ejb.eb.RolLocal argFk_camrol_2_rol,
		co
			.com
			.telefonica
			.atiempo
			.ejb
			.eb
			.Campo_variableLocal argFk_camrol_2_cam)
		throws javax.ejb.CreateException;
	/**
	 * Creates an instance from a key for Entity Bean: Campo_rol
	 */
	public co.com.telefonica.atiempo.ejb.eb.Campo_rolLocal create(
		java.lang.Long fk_camrol_2_rol_rol_id,
		java.lang.Short fk_camrol_2_cam_cv_id)
		throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Campo_rol
	 */
	public co.com.telefonica.atiempo.ejb.eb.Campo_rolLocal findByPrimaryKey(
		co.com.telefonica.atiempo.ejb.eb.Campo_rolKey primaryKey)
		throws javax.ejb.FinderException;
}
