package co.com.telefonica.atiempo.vpistbba.inventario.ejb;
/**
 * Local Home interface for Enterprise Bean: AConsultarEstadoDTH
 */
public interface AConsultarEstadoDTHLocalHome extends javax.ejb.EJBLocalHome {
	
	public static final String JNDI_NAME = "ejb/co/com/telefonica/atiempo/vpistbba/inventario/ejb/AConsultarEstadoDTHLocalHome";
	
	/**
	 * Creates a default instance of Session Bean: AConsultarEstadoDTH
	 */
	public co.com.telefonica.atiempo.vpistbba.inventario.ejb.AConsultarEstadoDTHLocal create()
		throws javax.ejb.CreateException;
}