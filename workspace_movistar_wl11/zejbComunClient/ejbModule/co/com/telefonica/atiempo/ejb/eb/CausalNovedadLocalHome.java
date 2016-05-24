package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local Home interface for Enterprise Bean: CausalNovedad
 */
public interface CausalNovedadLocalHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates a default instance of Session Bean: CausalNovedad
	 */
	public static final String JNDI_NAME = "ejb/co/com/telefonica/atiempo/ejb/eb/CausalNovedadLocalHome";
	
	public co.com.telefonica.atiempo.ejb.eb.CausalNovedadLocal create()
		throws javax.ejb.CreateException;
		
}
