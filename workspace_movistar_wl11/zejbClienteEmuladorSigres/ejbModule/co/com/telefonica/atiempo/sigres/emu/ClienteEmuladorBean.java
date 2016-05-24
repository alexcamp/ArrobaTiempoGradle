package co.com.telefonica.atiempo.sigres.emu;

import java.rmi.RemoteException;

import javax.ejb.EJBHome;
import javax.ejb.EJBObject;
import javax.ejb.Handle;
import javax.ejb.Local;
import javax.ejb.RemoveException;
import javax.ejb.Stateless;

import org.apache.log4j.Logger;

/**
 * Bean implementation class for Enterprise Bean: ClienteEmulador
 */
//TCS - CR25138 - Eliminacion de Repetitive calls of same method for same information - dregueira - May 20, 2009

@Stateless
@Local

public class ClienteEmuladorBean implements javax.ejb.SessionBean, ClienteEmulador {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1596990318984684932L;

	private static Logger logger = Logger.getLogger(ClienteEmuladorBean.class);

	private javax.ejb.SessionContext mySessionCtx;
	/**
	 * getSessionContext
	 */
	public javax.ejb.SessionContext getSessionContext() {
		return mySessionCtx;
	}
	/**
	 * setSessionContext
	 */
	public void setSessionContext(javax.ejb.SessionContext ctx) {
		mySessionCtx = ctx;
	}
	/**
	 * ejbCreate
	 */
	public void ejbCreate() throws javax.ejb.CreateException {
	}
	/**
	 * ejbActivate
	 */
	public void ejbActivate() {
	}
	/**
	 * ejbPassivate
	 */
	public void ejbPassivate() {
	}
	/**
	 * ejbRemove
	 */
	public void ejbRemove() {
	}

	public void recibirMensaje(String msg, String serviceClassName) {

		MessageReciever mr = new MessageReciever(msg,serviceClassName);
		Thread t = new Thread(mr);
		t.start();
		logger.info("La ejecucion continua..class Name.:" + serviceClassName);

	}
	@Override
	public EJBHome getEJBHome() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Handle getHandle() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Object getPrimaryKey() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean isIdentical(EJBObject obj) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void remove() throws RemoteException, RemoveException {
		// TODO Auto-generated method stub
		
	}
}
