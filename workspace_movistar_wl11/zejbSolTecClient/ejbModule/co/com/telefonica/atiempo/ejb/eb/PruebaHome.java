package co.com.telefonica.atiempo.ejb.eb;
/**
 * Home interface for Enterprise Bean: Prueba
 */
public interface PruebaHome extends javax.ejb.EJBHome {
	/**
	 * Creates a default instance of Session Bean: Prueba
	 */
	public co.com.telefonica.atiempo.ejb.eb.Prueba create()
		throws javax.ejb.CreateException, java.rmi.RemoteException;
}
