package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local Home interface for Enterprise Bean: Jer_usuario_rol
 */
public interface Jer_usuario_rolLocalHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates an instance from a key for Entity Bean: Jer_usuario_rol
	 */
	 static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/ejb/eb/Jer_usuario_rolLocalHome";
	 
	public co.com.telefonica.atiempo.ejb.eb.Jer_usuario_rolLocal create(
		co.com.telefonica.atiempo.ejb.eb.RolLocal argFk_jerrol_rol,
		co.com.telefonica.atiempo.ejb.eb.UsuarioLocal argFk_jerusuarol_usu2,
		co.com.telefonica.atiempo.ejb.eb.UsuarioLocal argFk_jerusuarol_usua)
		throws javax.ejb.CreateException;
	/**
	 * Creates an instance from a key for Entity Bean: Jer_usuario_rol
	 */
	public co.com.telefonica.atiempo.ejb.eb.Jer_usuario_rolLocal create(
		java.lang.Long fk_jerrol_rol_rol_id,
		java.lang.Long fk_jerusuarol_usu2_usua_id,
		java.lang.Long fk_jerusuarol_usua_usua_id)
		throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Jer_usuario_rol
	 */
	public co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.Jer_usuario_rolLocal findByPrimaryKey(
		co.com.telefonica.atiempo.ejb.eb.Jer_usuario_rolKey primaryKey)
		throws javax.ejb.FinderException;
	public java.util.Collection findSupervisoresByRol(java.lang.Long idRol) throws javax.ejb.FinderException;
	public java.util.Collection findUsuariosSupervisores(java.lang.Long rol, java.lang.Long usuario) throws javax.ejb.FinderException;
	public java.util.Collection findSupervisados(java.lang.Long idUserSupervisor) throws javax.ejb.FinderException;
}
