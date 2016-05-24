package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local Home interface for Enterprise Bean: Usuario_token
 */
public interface Usuario_tokenLocalHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates an instance from a key for Entity Bean: Usuario_token
	 */
	static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/ejb/eb/Usuario_tokenLocalHome";
	
	/**
	 * Creates an instance from a key for Entity Bean: Usuario_token
	 */
	public co.com.telefonica.atiempo.ejb.eb.Usuario_tokenLocal create(
		java.lang.String usua_login)
		throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Usuario_token
	 */
	public co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.Usuario_tokenLocal findByPrimaryKey(
		co.com.telefonica.atiempo.ejb.eb.Usuario_tokenKey primaryKey)
		throws javax.ejb.FinderException;
	public java.util.Collection findBySubTok(java.lang.String subTok) throws javax.ejb.FinderException;
}
