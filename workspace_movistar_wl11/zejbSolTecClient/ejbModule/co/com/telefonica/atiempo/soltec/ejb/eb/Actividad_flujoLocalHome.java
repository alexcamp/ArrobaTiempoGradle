package co.com.telefonica.atiempo.soltec.ejb.eb;
/**
 * Local Home interface for Enterprise Bean: Actividad_flujo
 */
public interface Actividad_flujoLocalHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates an instance from a key for Entity Bean: Actividad_flujo
	 */
	static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/soltec/ejb/eb/Actividad_flujoLocalHome";
	
	/**
	 * Creates an instance from a key for Entity Bean: Actividad_flujo
	 */
	public co.com.telefonica.atiempo.soltec.ejb.eb.Actividad_flujoLocal create(
		java.lang.Integer acti_id)
		throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Actividad_flujo
	 */
	public co
		.com
		.telefonica
		.atiempo
		.soltec
		.ejb
		.eb
		.Actividad_flujoLocal findByPrimaryKey(
			co
				.com
				.telefonica
				.atiempo
				.soltec
				.ejb
				.eb
				.Actividad_flujoKey primaryKey)
		throws javax.ejb.FinderException;
}
