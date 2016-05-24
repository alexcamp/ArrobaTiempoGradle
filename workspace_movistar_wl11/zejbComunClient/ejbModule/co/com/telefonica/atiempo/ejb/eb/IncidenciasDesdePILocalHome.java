package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local Home interface for Enterprise Bean: IncidenciasDesdePI
 */
public interface IncidenciasDesdePILocalHome extends javax.ejb.EJBLocalHome {
	String JNDI_NAME = "ejb/co/com/telefonica/atiempo/ejb/eb/IncidenciasDesdePILocalHome";
	/**
	 * Creates an instance from a key for Entity Bean: IncidenciasDesdePI
	 */
	public co.com.telefonica.atiempo.ejb.eb.IncidenciasDesdePILocal create(
		java.lang.Long nroIncidencia)
		throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: IncidenciasDesdePI
	 */
	public co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.IncidenciasDesdePILocal findByPrimaryKey(
			co.com.telefonica.atiempo.ejb.eb.IncidenciasDesdePIKey primaryKey)
		throws javax.ejb.FinderException;
	public co.com.telefonica.atiempo.ejb.eb.IncidenciasDesdePILocal findByKey(java.lang.Long nroIncidencia) throws javax.ejb.FinderException;
}
