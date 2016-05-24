package co.com.telefonica.atiempo.soltec.ejb.eb;
/**
 * Local Home interface for Enterprise Bean: Codigo_diagnostico
 */
public interface Codigo_diagnosticoLocalHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates an instance from a key for Entity Bean: Codigo_diagnostico
	 */
	static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/soltec/ejb/eb/Codigo_diagnosticoLocalHome";
	
	public co
		.com
		.telefonica
		.atiempo
		.soltec
		.ejb
		.eb
		.Codigo_diagnosticoLocal create(
		java.lang.String id_def_aper)
		throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Codigo_diagnostico
	 */
	public co
		.com
		.telefonica
		.atiempo
		.soltec
		.ejb
		.eb
		.Codigo_diagnosticoLocal findByPrimaryKey(
			co
				.com
				.telefonica
				.atiempo
				.soltec
				.ejb
				.eb
				.Codigo_diagnosticoKey primaryKey)
		throws javax.ejb.FinderException;
	public java.util.Collection findAll() throws javax.ejb.FinderException;
}
