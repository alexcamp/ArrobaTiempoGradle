package co.com.telefonica.atiempo.soltec.ejb.eb;
/**
 * Local Home interface for Enterprise Bean: Flujo_definicion
 */
public interface Flujo_definicionLocalHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates an instance from a key for Entity Bean: Flujo_definicion
	 */
	static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/soltec/ejb/eb/Flujo_definicionLocalHome";
	
	/**
	 * Creates an instance from a key for Entity Bean: Flujo_definicion
	 */
	public co
		.com
		.telefonica
		.atiempo
		.soltec
		.ejb
		.eb
		.Flujo_definicionLocal create(
		java.lang.Integer flde_id)
		throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Flujo_definicion
	 */
	public co
		.com
		.telefonica
		.atiempo
		.soltec
		.ejb
		.eb
		.Flujo_definicionLocal findByPrimaryKey(
			co
				.com
				.telefonica
				.atiempo
				.soltec
				.ejb
				.eb
				.Flujo_definicionKey primaryKey)
		throws javax.ejb.FinderException;
	public java.util.Collection findByIdFlujo(java.lang.Integer idFlujo) throws javax.ejb.FinderException;
}
