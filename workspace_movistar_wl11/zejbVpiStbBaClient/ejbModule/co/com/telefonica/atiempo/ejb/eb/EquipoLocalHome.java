package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local Home interface for Enterprise Bean: Equipo
 */
public interface EquipoLocalHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates a default instance of Session Bean: Equipo
	 */
	public static final String JNDI_NAME ="ejb/co/com/telefonica/atiempo/ejb/eb/EquipoLocalHome";
	
	public co.com.telefonica.atiempo.ejb.eb.EquipoLocal create()
		throws javax.ejb.CreateException;	
	
}
