package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local Home interface for Enterprise Bean: Perfil
 */
public interface PerfilLocalHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates an instance from a key for Entity Bean: Perfil
	 */
	 static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/ejb/eb/PerfilLocalHome";
	 
	public co.com.telefonica.atiempo.ejb.eb.PerfilLocal create(
		java.lang.Long perf_id)
		throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Perfil
	 */
	public co.com.telefonica.atiempo.ejb.eb.PerfilLocal findByPrimaryKey(
		co.com.telefonica.atiempo.ejb.eb.PerfilKey primaryKey)
		throws javax.ejb.FinderException;
	public java.util.Collection findAll() throws javax.ejb.FinderException;
}
