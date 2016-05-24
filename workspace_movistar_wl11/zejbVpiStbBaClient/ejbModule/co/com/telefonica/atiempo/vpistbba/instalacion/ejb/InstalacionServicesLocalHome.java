package co.com.telefonica.atiempo.vpistbba.instalacion.ejb;
/**
 * Local Home interface for Enterprise Bean: InstalacionServices
 */
public interface InstalacionServicesLocalHome extends javax.ejb.EJBLocalHome {

	public final static String JNDI_NAME = "ejb/co/com/telefonica/atiempo/vpistbba/instalacion/ejb/InstalacionServicesLocalHome";
	
	/**
	 * Creates a default instance of Session Bean: InstalacionServices
	 */
	public co
		.com
		.telefonica
		.atiempo
		.vpistbba
		.instalacion
		.ejb
		.InstalacionServicesLocal create()
		throws javax.ejb.CreateException;
}
