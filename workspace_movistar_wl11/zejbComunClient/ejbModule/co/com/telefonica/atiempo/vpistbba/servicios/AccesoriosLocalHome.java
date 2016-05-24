package co.com.telefonica.atiempo.vpistbba.servicios;
/**
 * Local Home interface for Enterprise Bean: Accesorios
 */
public interface AccesoriosLocalHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates an instance from a key for Entity Bean: Accesorios
	 */
	static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/vpistbba/servicios/AccesoriosLocalHome";
	public co.com.telefonica.atiempo.vpistbba.servicios.AccesoriosLocal create(
		java.lang.Long id) throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Accesorios
	 */
	public co.com.telefonica.atiempo.vpistbba.servicios.AccesoriosLocal findByPrimaryKey(
		java.lang.Long primaryKey) throws javax.ejb.FinderException;
	public java.util.Collection findAll() throws javax.ejb.FinderException;
	public co.com.telefonica.atiempo.vpistbba.servicios.AccesoriosLocal findbyPK(java.lang.Long id) throws javax.ejb.FinderException;
}