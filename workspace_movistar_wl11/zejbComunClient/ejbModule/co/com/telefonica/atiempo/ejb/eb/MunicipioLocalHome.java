package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local Home interface for Enterprise Bean: Municipio
 */
public interface MunicipioLocalHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates an instance from a key for Entity Bean: Municipio
	 */
	static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/ejb/eb/MunicipioLocalHome";
	
	/**
	 * Creates an instance from a key for Entity Bean: Municipio
	 */
	public co.com.telefonica.atiempo.ejb.eb.MunicipioLocal create(
		java.lang.String cod_mun)
		throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Municipio
	 */
	public co.com.telefonica.atiempo.ejb.eb.MunicipioLocal findByPrimaryKey(
		co.com.telefonica.atiempo.ejb.eb.MunicipioKey primaryKey)
		throws javax.ejb.FinderException;
}
