package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local Home interface for Enterprise Bean: Perfil_usuario
 */
public interface Perfil_usuarioLocalHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates an instance from a key for Entity Bean: Perfil_usuario
	 */
	 static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/ejb/eb/Perfil_usuarioLocalHome";
	 
	public co.com.telefonica.atiempo.ejb.eb.Perfil_usuarioLocal create(
		co.com.telefonica.atiempo.ejb.eb.UsuarioLocal argFk_perfusu_usu,
		co.com.telefonica.atiempo.ejb.eb.PerfilLocal argFk_perfusu_perf)
		throws javax.ejb.CreateException;
	/**
	 * Creates an instance from a key for Entity Bean: Perfil_usuario
	 */
	public co.com.telefonica.atiempo.ejb.eb.Perfil_usuarioLocal create(
		java.lang.Long fk_perfusu_usu_usua_id,
		java.lang.Long fk_perfusu_perf_perf_id)
		throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Perfil_usuario
	 */
	public co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.Perfil_usuarioLocal findByPrimaryKey(
		co.com.telefonica.atiempo.ejb.eb.Perfil_usuarioKey primaryKey)
		throws javax.ejb.FinderException;
	public java.util.Collection findByUsuario(java.lang.Long usuaId) throws javax.ejb.FinderException;
}
