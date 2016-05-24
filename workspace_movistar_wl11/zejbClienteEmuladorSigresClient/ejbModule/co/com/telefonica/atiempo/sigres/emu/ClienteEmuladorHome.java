package co.com.telefonica.atiempo.sigres.emu;
/**
 * Home interface for Enterprise Bean: ClienteEmulador
 */
public interface ClienteEmuladorHome extends javax.ejb.EJBHome {
	/**
	 * Creates a default instance of Session Bean: ClienteEmulador
	 */
	public co.com.telefonica.atiempo.sigres.emu.ClienteEmulador create()
		throws javax.ejb.CreateException, java.rmi.RemoteException;
}
