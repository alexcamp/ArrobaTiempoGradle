package co.com.telefonica.atiempo.vpistbba.peticiones.ejb;
/**
 * Local Home interface for Enterprise Bean: PeticionesServices
 */
public interface PeticionesServicesLocalHome extends javax.ejb.EJBLocalHome {
	
	public static final String JNDI_NAME = "ejb/co/com/telefonica/atiempo/vpistbba/peticiones/ejb/PeticionesServicesLocalHome";
	
	/**
	 * Creates a default instance of Session Bean: PeticionesServices
	 */
	public co.com.telefonica.atiempo.vpistbba.peticiones.ejb.PeticionesServicesLocal create()
		throws javax.ejb.CreateException;
}
