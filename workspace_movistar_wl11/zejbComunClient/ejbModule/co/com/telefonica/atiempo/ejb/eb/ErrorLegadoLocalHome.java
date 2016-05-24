package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local Home interface for Enterprise Bean: ErrorLegado
 */
public interface ErrorLegadoLocalHome extends javax.ejb.EJBLocalHome {

	static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/ejb/eb/ErrorLegadoLocalHome";

	/**
	 * Creates an instance from a key for Entity Bean: ErrorLegado
	 */
	public co.com.telefonica.atiempo.ejb.eb.ErrorLegadoLocal create(
		java.lang.Long id)
		throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: ErrorLegado
	 */
	public co.com.telefonica.atiempo.ejb.eb.ErrorLegadoLocal findByPrimaryKey(
		co.com.telefonica.atiempo.ejb.eb.ErrorLegadoKey primaryKey)
		throws javax.ejb.FinderException;
	
	public co.com.telefonica.atiempo.ejb.eb.ErrorLegadoLocal findByErrorTr(java.lang.String codError, java.lang.String codTr) throws javax.ejb.FinderException;
}
