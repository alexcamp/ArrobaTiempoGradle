package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local Home interface for Enterprise Bean: Usuario_rol
 */
public interface Usuario_rolLocalHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates an instance from a key for Entity Bean: Usuario_rol
	 */
	 static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/ejb/eb/Usuario_rolLocalHome";
	 
	public co.com.telefonica.atiempo.ejb.eb.Usuario_rolLocal create(
		co.com.telefonica.atiempo.ejb.eb.RolLocal argFk_usuarol_rol,
		co.com.telefonica.atiempo.ejb.eb.UsuarioLocal argFk_usuarol_usua)
		throws javax.ejb.CreateException;
	/**
	 * Creates an instance from a key for Entity Bean: Usuario_rol
	 */
	public co.com.telefonica.atiempo.ejb.eb.Usuario_rolLocal create(
		java.lang.Long fk_usuarol_rol_rol_id,
		java.lang.Long fk_usuarol_usua_usua_id)
		throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Usuario_rol
	 */
	public co.com.telefonica.atiempo.ejb.eb.Usuario_rolLocal findByPrimaryKey(
		co.com.telefonica.atiempo.ejb.eb.Usuario_rolKey primaryKey)
		throws javax.ejb.FinderException;
	public java.util.Collection findHabilitadosByRol(java.lang.Long rolId) throws javax.ejb.FinderException;
	public java.util.Collection findByRol(java.lang.Long rolId) throws javax.ejb.FinderException;
	public java.util.Collection findPoseeUsuarioRol(
		java.lang.Long idUsuario,
		java.lang.Long idRol)
		throws javax.ejb.FinderException;
	public co.com.telefonica.atiempo.ejb.eb.Usuario_rolLocal findSupervisorUsuarioRol(java.lang.Long idUsuario, java.lang.Long idRol) throws javax.ejb.FinderException;
	public java.util.Collection findSupervisados(
		java.lang.Long usuarioSupervisor)
		throws javax.ejb.FinderException;
}
