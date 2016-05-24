package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local Home interface for Enterprise Bean: Gaudi_traductor
 */
public interface Gaudi_traductorLocalHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates an instance from a key for Entity Bean: Gaudi_traductor
	 */
	 static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/ejb/eb/Gaudi_traductorLocalHome";
	 
	public co.com.telefonica.atiempo.ejb.eb.Gaudi_traductorLocal create(
		java.lang.Long id_correlativo)
		throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Gaudi_traductor
	 */
	public co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.Gaudi_traductorLocal findByPrimaryKey(
		co.com.telefonica.atiempo.ejb.eb.Gaudi_traductorKey primaryKey)
		throws javax.ejb.FinderException;
}
