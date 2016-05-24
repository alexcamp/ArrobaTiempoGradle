package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local Home interface for Enterprise Bean: Rol
 */
public interface RolLocalHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates an instance from a key for Entity Bean: Rol
	 */
	 static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/ejb/eb/RolLocalHome";
	public co.com.telefonica.atiempo.ejb.eb.RolLocal create(
		java.lang.Long rol_id)
		throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Rol
	 */
	public co.com.telefonica.atiempo.ejb.eb.RolLocal findByPrimaryKey(
		co.com.telefonica.atiempo.ejb.eb.RolKey primaryKey)
		throws javax.ejb.FinderException;
	public java.util.Collection findAll() throws javax.ejb.FinderException;
}
