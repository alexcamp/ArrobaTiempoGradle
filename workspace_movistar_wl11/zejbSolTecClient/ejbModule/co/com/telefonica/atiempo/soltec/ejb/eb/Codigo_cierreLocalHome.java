package co.com.telefonica.atiempo.soltec.ejb.eb;
/**
 * Local Home interface for Enterprise Bean: Codigo_cierre
 */
public interface Codigo_cierreLocalHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates an instance from a key for Entity Bean: Codigo_cierre
	 */
	static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/soltec/ejb/eb/Codigo_cierreLocalHome";
	
	/**
	 * Creates an instance from a key for Entity Bean: Codigo_cierre
	 */
	public co.com.telefonica.atiempo.soltec.ejb.eb.Codigo_cierreLocal create(
		java.lang.String cod_cierre)
		throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Codigo_cierre
	 */
	public co
		.com
		.telefonica
		.atiempo
		.soltec
		.ejb
		.eb
		.Codigo_cierreLocal findByPrimaryKey(
			co
				.com
				.telefonica
				.atiempo
				.soltec
				.ejb
				.eb
				.Codigo_cierreKey primaryKey)
		throws javax.ejb.FinderException;
	public java.util.Collection findByLoc(java.lang.Integer cod_local)
		throws javax.ejb.FinderException;
	public co.com.telefonica.atiempo.soltec.ejb.eb.Codigo_cierreLocal findByDescripcion(
		java.lang.String descripcion_cierre) throws javax.ejb.FinderException;
	public co.com.telefonica.atiempo.soltec.ejb.eb.Codigo_cierreLocal findByDescripcion_cierre(
		java.lang.String descripcion_cierre) throws javax.ejb.FinderException;
}
