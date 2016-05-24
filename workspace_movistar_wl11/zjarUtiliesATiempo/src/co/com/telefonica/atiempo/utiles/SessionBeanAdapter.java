/*
 * Created on Feb 12, 2007
 */
package co.com.telefonica.atiempo.utiles;

import java.rmi.RemoteException;

import javax.ejb.EJBException;
import javax.ejb.SessionContext;

/**
 * @author TCS
 */
//TCS - CR25138 - Eliminacion de Repetitive calls of same method for same information - dregueira - May 20, 2009
public class SessionBeanAdapter implements javax.ejb.SessionBean {

	private javax.ejb.SessionContext mySessionCtx;
	/* (non-Javadoc)
	 * @see javax.ejb.SessionBean#setSessionContext(javax.ejb.SessionContext)
	 */
	public void setSessionContext(SessionContext ctx)
		throws EJBException, RemoteException {
		mySessionCtx = ctx;
	}

	public javax.ejb.SessionContext getSessionContext() {
		return mySessionCtx;
	}

	public void ejbCreate() throws javax.ejb.CreateException {
	}	
	/* (non-Javadoc)
	 * @see javax.ejb.SessionBean#ejbRemove()
	 */
	public void ejbRemove() throws EJBException, RemoteException {

	}

	/* (non-Javadoc)
	 * @see javax.ejb.SessionBean#ejbActivate()
	 */
	public void ejbActivate() throws EJBException, RemoteException {

	}

	/* (non-Javadoc)
	 * @see javax.ejb.SessionBean#ejbPassivate()
	 */
	public void ejbPassivate() throws EJBException, RemoteException {

	}

}
