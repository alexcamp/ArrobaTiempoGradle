package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local Home interface for Enterprise Bean: Caracteristicas_linea
 */
public interface Caracteristicas_lineaLocalHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates an instance from a key for Entity Bean: Caracteristicas_linea
	 */
	static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/ejb/eb/Caracteristicas_lineaLocalHome";
	public co.com.telefonica.atiempo.ejb.eb.Caracteristicas_lineaLocal create(
		java.lang.Long ps_id) throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Caracteristicas_linea
	 */
	public co.com.telefonica.atiempo.ejb.eb.Caracteristicas_lineaLocal findByPrimaryKey(
		co.com.telefonica.atiempo.ejb.eb.Caracteristicas_lineaKey primaryKey)
		throws javax.ejb.FinderException;
}