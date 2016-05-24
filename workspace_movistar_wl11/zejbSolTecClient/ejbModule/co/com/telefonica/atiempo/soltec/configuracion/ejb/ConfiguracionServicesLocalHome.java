package co.com.telefonica.atiempo.soltec.configuracion.ejb;
/**
 * Local Home interface for Enterprise Bean: ConfiguracionServices
 */
public interface ConfiguracionServicesLocalHome
	extends javax.ejb.EJBLocalHome {
		
	public final static String JNDI_NAME = "ejb/co/com/telefonica/atiempo/soltec/configuracion/ejb/ConfiguracionServicesLocalHome";
				
	/**
	 * Creates a default instance of Session Bean: ConfiguracionServices
	 */
	public co
		.com
		.telefonica
		.atiempo
		.soltec
		.configuracion
		.ejb
		.ConfiguracionServicesLocal create()
		throws javax.ejb.CreateException;
}
