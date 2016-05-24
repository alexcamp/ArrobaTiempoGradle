package co.com.telefonica.atiempo.vpistbba.servicios;
/**
 * Local Home interface for Enterprise Bean: Atiempo_Interfaz
 */
public interface Atiempo_InterfazLocalHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates an instance from a key for Entity Bean: Atiempo_Interfaz
	 */
	public co.com.telefonica.atiempo.vpistbba.servicios.Atiempo_InterfazLocal create(
		java.lang.String nombre) throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Atiempo_Interfaz
	 */
	public co.com.telefonica.atiempo.vpistbba.servicios.Atiempo_InterfazLocal findByPrimaryKey(
		co.com.telefonica.atiempo.vpistbba.servicios.Atiempo_InterfazKey primaryKey)
		throws javax.ejb.FinderException;
}