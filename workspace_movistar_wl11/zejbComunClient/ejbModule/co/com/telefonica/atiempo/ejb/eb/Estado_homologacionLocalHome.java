package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local Home interface for Enterprise Bean: Estado_homologacion
 */
public interface Estado_homologacionLocalHome extends javax.ejb.EJBLocalHome {
	
	public static final String JNDI_NAME = "ejb/co/com/telefonica/atiempo/ejb/eb/Estado_homologacionLocalHome";
	
	/**
	 * Creates an instance from a key for Entity Bean: Estado_homologacion
	 */
	public co.com.telefonica.atiempo.ejb.eb.Estado_homologacionLocal create(
		java.lang.String cod_app,
		java.lang.String cod_estado) throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Estado_homologacion
	 */
	public co.com.telefonica.atiempo.ejb.eb.Estado_homologacionLocal findByPrimaryKey(
		co.com.telefonica.atiempo.ejb.eb.Estado_homologacionKey primaryKey)
		throws javax.ejb.FinderException;
	public java.util.Collection findByCodApp(java.lang.String cod_app) throws javax.ejb.FinderException;
}