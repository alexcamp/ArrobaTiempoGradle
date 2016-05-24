package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local Home interface for Enterprise Bean: Prueba
 */
public interface PruebaLocalHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates a default instance of Session Bean: Prueba
	 */
	public co.com.telefonica.atiempo.ejb.eb.PruebaLocal create()
		throws javax.ejb.CreateException;
}
