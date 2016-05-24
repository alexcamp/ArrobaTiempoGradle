package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local Home interface for Enterprise Bean: Equipos_TOA
 */
public interface Equipos_TOALocalHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates an instance from a key for Entity Bean: Equipos_TOA
	 */
	static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/ejb/eb/Equipos_TOALocalHome";
	public co.com.telefonica.atiempo.ejb.eb.Equipos_TOALocal create(
		java.lang.Long codigo_ps,
		java.lang.Long operacion_comercial) throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Equipos_TOA
	 */
	public co.com.telefonica.atiempo.ejb.eb.Equipos_TOALocal findByPrimaryKey(
		co.com.telefonica.atiempo.ejb.eb.Equipos_TOAKey primaryKey)
		throws javax.ejb.FinderException;
}