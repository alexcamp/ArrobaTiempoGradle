package co.com.telefonica.atiempo.vpistbba.inventario.ejb;
/**
 * Local Home interface for Enterprise Bean: InventarioServices
 */
public interface InventarioServicesLocalHome extends javax.ejb.EJBLocalHome {

	public static final String JNDI_NAME = "ejb/co/com/telefonica/atiempo/vpistbba/inventario/ejb/InventarioServicesLocalHome";
	
	/**
	 * Creates a default instance of Session Bean: InventarioServices
	 */
	public co
		.com
		.telefonica
		.atiempo
		.vpistbba
		.inventario
		.ejb
		.InventarioServicesLocal create()
		throws javax.ejb.CreateException;
}
