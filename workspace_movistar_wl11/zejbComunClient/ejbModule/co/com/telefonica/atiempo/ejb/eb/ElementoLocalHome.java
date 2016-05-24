package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local Home interface for Enterprise Bean: Elemento
 */
public interface ElementoLocalHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates an instance from a key for Entity Bean: Elemento
	 */
	public static final String JNDI_NAME = "ejb/co/com/telefonica/atiempo/ejb/eb/ElementoLocalHome";
	
	public co.com.telefonica.atiempo.ejb.eb.ElementoLocal create(
		java.lang.Integer id_elemento) throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Elemento
	 */
	public co.com.telefonica.atiempo.ejb.eb.ElementoLocal findByPrimaryKey(
		java.lang.Integer primaryKey) throws javax.ejb.FinderException;
	public co.com.telefonica.atiempo.ejb.eb.ElementoLocal findElemento(
		long id_elemento) throws javax.ejb.FinderException;
	public java.util.Collection findAll() throws javax.ejb.FinderException;
	public co.com.telefonica.atiempo.ejb.eb.ElementoLocal findByDescripcion(java.lang.String descripcion) throws javax.ejb.FinderException;
}
