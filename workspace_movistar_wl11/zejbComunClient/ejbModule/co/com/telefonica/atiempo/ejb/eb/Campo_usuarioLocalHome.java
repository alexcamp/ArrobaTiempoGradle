package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local Home interface for Enterprise Bean: Campo_usuario
 */
public interface Campo_usuarioLocalHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates an instance from a key for Entity Bean: Campo_usuario
	 */
	 static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/ejb/eb/Campo_usuarioLocalHome";
	public co.com.telefonica.atiempo.ejb.eb.Campo_usuarioLocal create(
		co
			.com
			.telefonica
			.atiempo
			.ejb
			.eb
			.Campo_variableLocal argFk_camusu_camvar,
		co.com.telefonica.atiempo.ejb.eb.UsuarioLocal argFk_campousua_usua)
		throws javax.ejb.CreateException;
	/**
	 * Creates an instance from a key for Entity Bean: Campo_usuario
	 */
	public co.com.telefonica.atiempo.ejb.eb.Campo_usuarioLocal create(
		java.lang.Short fk_camusu_camvar_cv_id,
		java.lang.Long fk_campousua_usua_usua_id)
		throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Campo_usuario
	 */
	public co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.Campo_usuarioLocal findByPrimaryKey(
		co.com.telefonica.atiempo.ejb.eb.Campo_usuarioKey primaryKey)
		throws javax.ejb.FinderException;
}
