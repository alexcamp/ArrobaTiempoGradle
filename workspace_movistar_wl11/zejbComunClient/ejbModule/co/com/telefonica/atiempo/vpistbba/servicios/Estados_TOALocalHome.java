package co.com.telefonica.atiempo.vpistbba.servicios;
/**
 * Local Home interface for Enterprise Bean: Estados_TOA
 */
public interface Estados_TOALocalHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates an instance from a key for Entity Bean: Estados_TOA
	 */
	static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/vpistbba/servicios/Estados_TOALocalHome";
	/**
	 * Creates an instance from a key for Entity Bean: Estados_TOA
	 */
	public co.com.telefonica.atiempo.vpistbba.servicios.Estados_TOALocal create(
		java.lang.Integer correlativo) throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Estados_TOA
	 */
	public co.com.telefonica.atiempo.vpistbba.servicios.Estados_TOALocal findByPrimaryKey(
		co.com.telefonica.atiempo.vpistbba.servicios.Estados_TOAKey primaryKey)
		throws javax.ejb.FinderException;
	public co.com.telefonica.atiempo.vpistbba.servicios.Estados_TOALocal findEstadoFam(
		java.lang.Integer estado,
		java.lang.String tipoFamilia) throws javax.ejb.FinderException;
}