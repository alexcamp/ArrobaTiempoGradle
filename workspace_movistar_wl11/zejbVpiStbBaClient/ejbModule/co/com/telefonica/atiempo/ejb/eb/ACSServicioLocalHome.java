package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local Home interface for Enterprise Bean: ACSServicio
 */
public interface ACSServicioLocalHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates a default instance of Session Bean: ACSServicio
	 */
	public static final String JNDI_NAME ="ejb/co/com/telefonica/atiempo/ejb/eb/ACSServicioLocalHome";
	public co.com.telefonica.atiempo.ejb.eb.ACSServicioLocal create()
		throws javax.ejb.CreateException;
}