package co.com.telefonica.atiempo.ejb.eb;

import java.util.Collection;
import java.util.Date;

/**
 * Local Home interface for Enterprise Bean: Excepcion_distribucion_carga_maxima
 */
public interface Excepcion_distribucion_carga_maximaLocalHome
	extends javax.ejb.EJBLocalHome {
	/**
	 * Creates an instance from a key for Entity Bean: Excepcion_distribucion_carga_maxima
	 */
	 static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/ejb/eb/Excepcion_distribucion_carga_maximaLocalHome";
	 
	public co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.Excepcion_distribucion_carga_maximaLocal create(
			java.lang.Long exdcm_id)
		throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Excepcion_distribucion_carga_maxima
	 */
	public co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.Excepcion_distribucion_carga_maximaLocal findByPrimaryKey(
			co
				.com
				.telefonica
				.atiempo
				.ejb
				.eb
				.Excepcion_distribucion_carga_maximaKey primaryKey)
		throws javax.ejb.FinderException;
	/**
	 * @param idFam
	 * @param string
	 * @param fecMin
	 * @param fecMax
	 * @return
	 */
	public Collection findByFamiliaPlantaFecha(Long idFam, String string, Date fecMin, Date fecMax) throws javax.ejb.FinderException;
}
