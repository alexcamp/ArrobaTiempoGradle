package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local Home interface for Enterprise Bean: Ps_instalacion
 */
public interface Ps_instalacionLocalHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates an instance from a key for Entity Bean: Ps_instalacion
	 */
	static final String JNDI_NAME = "ejb/co/com/telefonica/atiempo/ejb/eb/Ps_instalacionLocalHome";

	public co.com.telefonica.atiempo.ejb.eb.Ps_instalacionLocal create(java.lang.Integer id) throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Ps_instalacion
	 */
	public co.com.telefonica.atiempo.ejb.eb.Ps_instalacionLocal findByPrimaryKey(co.com.telefonica.atiempo.ejb.eb.Ps_instalacionKey primaryKey)
		throws javax.ejb.FinderException;

	public java.util.Collection findAll() throws javax.ejb.FinderException;
	public java.util.Collection findByCodigoPS(java.lang.Long idPs) throws javax.ejb.FinderException;
}
