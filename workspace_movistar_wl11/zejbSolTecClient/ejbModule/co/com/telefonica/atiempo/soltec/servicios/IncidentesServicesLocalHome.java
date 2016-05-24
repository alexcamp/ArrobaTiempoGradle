package co.com.telefonica.atiempo.soltec.servicios;
/**
 * Local Home interface for Enterprise Bean: IncidentesServices
 */
public interface IncidentesServicesLocalHome extends javax.ejb.EJBLocalHome {

	public static final String JNDI_NAME = "ejb/co/com/telefonica/atiempo/soltec/servicios/IncidentesServicesLocalHome";

	/**
	 * Creates a default instance of Session Bean: IncidentesServices
	 */
	public co
		.com
		.telefonica
		.atiempo
		.soltec
		.servicios
		.IncidentesServicesLocal create()
		throws javax.ejb.CreateException;
}
