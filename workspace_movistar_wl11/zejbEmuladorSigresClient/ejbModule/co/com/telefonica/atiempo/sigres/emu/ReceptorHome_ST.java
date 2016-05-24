package co.com.telefonica.atiempo.sigres.emu;
/**
 * Home interface for Enterprise Bean: Receptor
 */
public interface ReceptorHome_ST extends javax.ejb.EJBHome {
	/**
	 * Creates a default instance of Session Bean: Receptor
	 */
	public co.com.telefonica.atiempo.sigres.emu.Receptor_ST create()
		throws javax.ejb.CreateException, java.rmi.RemoteException;
}
