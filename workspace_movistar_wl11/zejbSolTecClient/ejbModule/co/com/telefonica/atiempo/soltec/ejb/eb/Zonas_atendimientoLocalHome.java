package co.com.telefonica.atiempo.soltec.ejb.eb;
/**
 * Local Home interface for Enterprise Bean: Zonas_atendimiento
 */
public interface Zonas_atendimientoLocalHome extends javax.ejb.EJBLocalHome {
	
	
	static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/soltec/ejb/eb/Zonas_atendimientoLocalHome";
	
	/**
	 * Creates an instance from a key for Entity Bean: Zonas_atendimiento
	 */
	public co
		.com
		.telefonica
		.atiempo
		.soltec
		.ejb
		.eb
		.Zonas_atendimientoLocal create(
		java.lang.Long id)
		throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Zonas_atendimiento
	 */
	public co
		.com
		.telefonica
		.atiempo
		.soltec
		.ejb
		.eb
		.Zonas_atendimientoLocal findByPrimaryKey(
			co
				.com
				.telefonica
				.atiempo
				.soltec
				.ejb
				.eb
				.Zonas_atendimientoKey primaryKey)
		throws javax.ejb.FinderException;
	public java.util.Collection findByConector(java.lang.Long Conector) throws javax.ejb.FinderException;
}
