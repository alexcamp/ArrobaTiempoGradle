package co.com.telefonica.atiempo.ejb.eb;

import java.util.Collection;

/**
 * Local Home interface for Enterprise Bean: Distribucion_carga_maxima
 */
public interface Distribucion_carga_maximaLocalHome
	extends javax.ejb.EJBLocalHome {
	/**
	 * Creates an instance from a key for Entity Bean: Distribucion_carga_maxima
	 */
	 static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/ejb/eb/Distribucion_carga_maximaLocalHome";
	 
	public co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.Distribucion_carga_maximaLocal create(
		java.lang.Long dicm_id)
		throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Distribucion_carga_maxima
	 */
	public co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.Distribucion_carga_maximaLocal findByPrimaryKey(
			co
				.com
				.telefonica
				.atiempo
				.ejb
				.eb
				.Distribucion_carga_maximaKey primaryKey)
		throws javax.ejb.FinderException;
	/**
	 * @param idFam
	 * @param string
	 * @return
	 */
	public Collection findByFamiliaPlanta(Long idFam, String string) throws javax.ejb.FinderException;
}
