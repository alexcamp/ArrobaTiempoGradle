package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local Home interface for Enterprise Bean: Mensaje_descarga_SAP
 */
public interface Mensaje_descarga_SAPLocalHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates an instance from a key for Entity Bean: Mensaje_descarga_SAP
	 */
	
	static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/ejb/eb/Mensaje_descarga_SAPLocalHome";
	
	/**
	 * Creates an instance from a key for Entity Bean: Mensaje_descarga_SAP
	 */
	public co.com.telefonica.atiempo.ejb.eb.Mensaje_descarga_SAPLocal create(
		java.lang.Long consecutivo) throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Mensaje_descarga_SAP
	 */
	public co.com.telefonica.atiempo.ejb.eb.Mensaje_descarga_SAPLocal findByPrimaryKey(
		co.com.telefonica.atiempo.ejb.eb.Mensaje_descarga_SAPKey primaryKey)
		throws javax.ejb.FinderException;
	public java.util.Collection findByIdPeti(java.lang.Long id_peti)
		throws javax.ejb.FinderException;
	public co.com.telefonica.atiempo.ejb.eb.Mensaje_descarga_SAPLocal find_max()
		throws javax.ejb.FinderException;
}