package co.com.telefonica.atiempo.vpistbba.servicios;
/**
 * Local Home interface for Enterprise Bean: Activaciones_tv_TOA
 */
public interface Activaciones_tv_TOALocalHome extends javax.ejb.EJBLocalHome {
	
	static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/vpistbba/servicios/Activaciones_tv_TOALocalHome";
	/**
	 * Creates an instance from a key for Entity Bean: Activaciones_tv_TOA
	 */
	public co.com.telefonica.atiempo.vpistbba.servicios.Activaciones_tv_TOALocal create(
		java.lang.Long correlativo) throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Activaciones_tv_TOA
	 */
	public co.com.telefonica.atiempo.vpistbba.servicios.Activaciones_tv_TOALocal findByPrimaryKey(
		co.com.telefonica.atiempo.vpistbba.servicios.Activaciones_tv_TOAKey primaryKey)
		throws javax.ejb.FinderException;
}