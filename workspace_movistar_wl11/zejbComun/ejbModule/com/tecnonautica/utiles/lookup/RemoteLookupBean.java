package com.tecnonautica.utiles.lookup;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.naming.NamingException;

import com.telefonica_chile.bandeja.ejbutiles.ContextFactory;

/**
 * Bean implementation class for Enterprise Bean: RemoteLookup
 */
//TCS - CR25138 - Eliminacion de Repetitive calls of same method for same information - dregueira - May 20, 2009
@Stateless
@Local
public class RemoteLookupBean implements javax.ejb.SessionBean, RemoteLookupLocal {
	
	/** constante para formar la enx-entry */
	public static final String _NAME_SPACE = "nameSpace";
	
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
	
	/**
	 * retorna el nombre jndi corba para el perfil
	 * indicado 
	 * @param perfil
	 * @return
	 * @throws NamingException
	 */
	public String resolveCorbaName(String perfil) throws NamingException {
		
		return (String) ContextFactory.lookup("java:comp/env/" + perfil + "." + _NAME_SPACE);
		
	}
	
}
