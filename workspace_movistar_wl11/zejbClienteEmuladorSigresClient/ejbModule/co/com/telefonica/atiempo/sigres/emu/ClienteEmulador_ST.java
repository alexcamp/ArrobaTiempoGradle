package co.com.telefonica.atiempo.sigres.emu;
import java.rmi.RemoteException;

/**
 * Remote interface for Enterprise Bean: Receptor
 */
public interface ClienteEmulador_ST extends javax.ejb.EJBObject {
	public void recibirMensaje(String xml,String className)throws RemoteException;
}
