package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local Home interface for Enterprise Bean: Usuario
 */
public interface UsuarioLocalHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates an instance from a key for Entity Bean: Usuario
	 */
	 static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/ejb/eb/UsuarioLocalHome";
	 
	public co.com.telefonica.atiempo.ejb.eb.UsuarioLocal create(
		java.lang.Long usua_id)
		throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Usuario
	 */
	public co.com.telefonica.atiempo.ejb.eb.UsuarioLocal findByPrimaryKey(
		co.com.telefonica.atiempo.ejb.eb.UsuarioKey primaryKey)
		throws javax.ejb.FinderException;
	/**
	 * Creates an instance from a key for Entity Bean: Usuario
	 */
	public co.com.telefonica.atiempo.ejb.eb.UsuarioLocal create(
		java.lang.Long usua_id,
		java.lang.String usua_login,
		java.lang.String usua_nombre)
		throws javax.ejb.CreateException;
	public co.com.telefonica.atiempo.ejb.eb.UsuarioLocal findByLogin(java.lang.String loginUsuario) throws javax.ejb.FinderException;
	public java.util.Collection findAll() throws javax.ejb.FinderException;
	public co.com.telefonica.atiempo.ejb.eb.UsuarioLocal findByLoginEmail(java.lang.String usuaLogin, java.lang.String usuaEmail) throws javax.ejb.FinderException;
	public co.com.telefonica.atiempo.ejb.eb.UsuarioLocal findByIdca(java.lang.String idca) throws javax.ejb.FinderException;
	/**
	 * ejbCreate method for a CMP entity bean.
	 */
	public co.com.telefonica.atiempo.ejb.eb.UsuarioLocal create(
		String usua_login,
		String usua_nombre)
		throws javax.ejb.CreateException;
}
