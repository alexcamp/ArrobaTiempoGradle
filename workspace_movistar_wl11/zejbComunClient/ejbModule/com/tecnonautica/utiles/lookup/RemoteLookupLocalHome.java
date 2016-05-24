package com.tecnonautica.utiles.lookup;
/**
 * Local Home interface for Enterprise Bean: RemoteLookup
 */
public interface RemoteLookupLocalHome extends javax.ejb.EJBLocalHome {
	public static final String JNDI_NAME = "ejb/com/tecnonautica/utiles/lookup/RemoteLookupLocalHome";

	/**
	 * Creates a default instance of Session Bean: RemoteLookup
	 */
	public com.tecnonautica.utiles.lookup.RemoteLookupLocal create()
		throws javax.ejb.CreateException;
}
