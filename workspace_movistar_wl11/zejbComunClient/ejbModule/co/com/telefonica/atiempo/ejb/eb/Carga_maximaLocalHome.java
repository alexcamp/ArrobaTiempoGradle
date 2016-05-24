package co.com.telefonica.atiempo.ejb.eb;

import java.util.Collection;

/**
 * Local Home interface for Enterprise Bean: Carga_maxima
 */
public interface Carga_maximaLocalHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates an instance from a key for Entity Bean: Carga_maxima
	 */
	 static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/ejb/eb/Carga_maximaLocalHome";
	public co.com.telefonica.atiempo.ejb.eb.Carga_maximaLocal create(
		java.lang.Long cmax_id)
		throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Carga_maxima
	 */
	public co.com.telefonica.atiempo.ejb.eb.Carga_maximaLocal findByPrimaryKey(
		co.com.telefonica.atiempo.ejb.eb.Carga_maximaKey primaryKey)
		throws javax.ejb.FinderException;
	/**
	 * Creates an instance from a key for Entity Bean: Carga_maxima
	 */
	public co.com.telefonica.atiempo.ejb.eb.Carga_maximaLocal create(
		java.lang.Long cmax_id,
		java.lang.Long codigo_familia_ps,
		java.lang.String codigo_pcom,
		java.lang.String dia_semana)
		throws javax.ejb.CreateException;
	/**
	 * @param idFam
	 * @param string
	 * @return
	 */
	public Collection findByFamiliaAgencia(Long idFam, String codAgencia) throws javax.ejb.FinderException;;

}
