package co.com.telefonica.atiempo.sigres.emu;

import java.rmi.RemoteException;

/**
 * Remote interface for Enterprise Bean: Receptor
 */
public interface Receptor extends javax.ejb.EJBObject {
	public void recibirMensaje(String xml)throws RemoteException;
}
