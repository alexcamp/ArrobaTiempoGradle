package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local Home interface for Enterprise Bean: Habilidad_usuario
 */
public interface Habilidad_usuarioLocalHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates an instance from a key for Entity Bean: Habilidad_usuario
	 */
	 static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/ejb/eb/Habilidad_usuarioLocalHome";
	 
	public co.com.telefonica.atiempo.ejb.eb.Habilidad_usuarioLocal create(
		java.lang.Long husu_id)
		throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Habilidad_usuario
	 */
	public co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.Habilidad_usuarioLocal findByPrimaryKey(
		co.com.telefonica.atiempo.ejb.eb.Habilidad_usuarioKey primaryKey)
		throws javax.ejb.FinderException;
	/**
	 * Creates an instance from a key for Entity Bean: Habilidad_usuario
	 */
	public co.com.telefonica.atiempo.ejb.eb.Habilidad_usuarioLocal create(
		java.lang.Long husu_id,
		co.com.telefonica.atiempo.ejb.eb.UsuarioLocal argFk_usua_2_husa)
		throws javax.ejb.CreateException;
	public java.util.Collection findUsuariosByHabilidad(java.lang.Long idHabilidad) throws javax.ejb.FinderException;
	public java.util.Collection findByUsuario(java.lang.Long idUsuario) throws javax.ejb.FinderException;
	public java.util.Collection findUsuariosPorLaHabilidad(java.lang.Long habiId, java.lang.String valor) throws javax.ejb.FinderException;
	public java.util.Collection findUsuarios(
		java.lang.Long idUsuario,
		java.lang.Long idHabilidad)
		throws javax.ejb.FinderException;
	public java.util.Collection findUsuariosHabilidad() throws javax.ejb.FinderException;
	public java.util.Collection findPoseeHabilidadValor(
		java.lang.Long idUsuario,
		java.lang.Long idHabi,
		java.lang.String valor)
		throws javax.ejb.FinderException;
}
