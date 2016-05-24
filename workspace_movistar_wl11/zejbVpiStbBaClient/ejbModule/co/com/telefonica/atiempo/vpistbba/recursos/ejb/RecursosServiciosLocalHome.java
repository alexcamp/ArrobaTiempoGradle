package co.com.telefonica.atiempo.vpistbba.recursos.ejb;
/**
 * Local Home interface for Enterprise Bean: RecursosServicios
 */
public interface RecursosServiciosLocalHome extends javax.ejb.EJBLocalHome {

	public static final String JNDI_NAME = "ejb/co/com/telefonica/atiempo/vpistbba/recursos/ejb/RecursosServiciosLocalHome";	
	
	/**
	 * Creates a default instance of Session Bean: RecursosServicios
	 */
	public co
		.com
		.telefonica
		.atiempo
		.vpistbba
		.recursos
		.ejb
		.RecursosServiciosLocal create()
		throws javax.ejb.CreateException;
}
