package co.com.telefonica.atiempo.vpistbba.servicios;
/**
 * Local Home interface for Enterprise Bean: Conexion_Interfaz
 */
public interface Conexion_InterfazLocalHome extends javax.ejb.EJBLocalHome {
	
	static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/vpistbba/servicios/Conexion_InterfazLocalHome";
	/**
	 * Creates an instance from a key for Entity Bean: Conexion_Interfaz
	 */
	public co.com.telefonica.atiempo.vpistbba.servicios.Conexion_InterfazLocal create(
		java.lang.String interfaz,
		java.lang.String propiedad) throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Conexion_Interfaz
	 */
	public co.com.telefonica.atiempo.vpistbba.servicios.Conexion_InterfazLocal findByPrimaryKey(
		co.com.telefonica.atiempo.vpistbba.servicios.Conexion_InterfazKey primaryKey)
		throws javax.ejb.FinderException;
}