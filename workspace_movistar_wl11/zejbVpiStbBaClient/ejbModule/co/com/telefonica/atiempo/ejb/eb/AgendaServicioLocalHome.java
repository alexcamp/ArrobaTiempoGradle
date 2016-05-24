package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local Home interface for Enterprise Bean: AgendaServicio
 */
public interface AgendaServicioLocalHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates a default instance of Session Bean: AgendaServicio
	 */
	public static final String JNDI_NAME ="ejb/co/com/telefonica/atiempo/ejb/eb/AgendaServicioLocalHome";
	public co.com.telefonica.atiempo.ejb.eb.AgendaServicioLocal create()
		throws javax.ejb.CreateException;
}