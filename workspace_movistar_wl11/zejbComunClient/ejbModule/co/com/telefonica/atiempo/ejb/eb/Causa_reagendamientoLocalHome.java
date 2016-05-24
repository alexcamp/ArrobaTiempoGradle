package co.com.telefonica.atiempo.ejb.eb;

import java.util.Collection;

/**
 * Local Home interface for Enterprise Bean: Causa_reagendamiento
 */
public interface Causa_reagendamientoLocalHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates an instance from a key for Entity Bean: Causa_reagendamiento
	 */
	 static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/ejb/eb/Causa_reagendamientoLocalHome";
	 
	public co.com.telefonica.atiempo.ejb.eb.Causa_reagendamientoLocal create(
		java.lang.Integer care_id)
		throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Causa_reagendamiento
	 */
	public co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.Causa_reagendamientoLocal findByPrimaryKey(
			co
				.com
				.telefonica
				.atiempo
				.ejb
				.eb
				.Causa_reagendamientoKey primaryKey)
		throws javax.ejb.FinderException;
	/**
	 * @return
	 */
	public Collection findAll() throws javax.ejb.FinderException;
}
