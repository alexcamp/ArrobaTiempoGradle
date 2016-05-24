package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local Home interface for Enterprise Bean: Mensaje_ACS
 */
public interface Mensaje_ACSLocalHome extends javax.ejb.EJBLocalHome {
	
	static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/ejb/eb/Mensaje_ACSLocalHome";
	/**
	 * Creates an instance from a key for Entity Bean: Mensaje_ACS
	 */
	public co.com.telefonica.atiempo.ejb.eb.Mensaje_ACSLocal create(
		java.lang.Long id) throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Mensaje_ACS
	 */
	public co.com.telefonica.atiempo.ejb.eb.Mensaje_ACSLocal findByPrimaryKey(
		java.lang.Long primaryKey) throws javax.ejb.FinderException;
	public java.util.Collection findByPetiNumero(java.lang.Long peti_numero) throws javax.ejb.FinderException;
	public co.com.telefonica.atiempo.ejb.eb.Mensaje_ACSLocal findByFechaMax(java.lang.Long peti_numero) throws javax.ejb.FinderException;
}