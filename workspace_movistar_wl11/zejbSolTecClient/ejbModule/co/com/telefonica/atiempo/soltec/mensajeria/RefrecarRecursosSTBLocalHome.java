package co.com.telefonica.atiempo.soltec.mensajeria;
/**
 * Local Home interface for Enterprise Bean: RefrecarRecursosSTB
 */
public interface RefrecarRecursosSTBLocalHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates a default instance of Session Bean: RefrecarRecursosSTB
	 */
	public static final String JNDI_NAME = "ejb/co/com/telefonica/atiempo/soltec/mensajeria/RefrecarRecursosSTBLocalHome";
	public co.com.telefonica.atiempo.soltec.mensajeria.RefrecarRecursosSTBLocal create()
		throws javax.ejb.CreateException;
}