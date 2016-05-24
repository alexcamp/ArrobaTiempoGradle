package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local Home interface for Enterprise Bean: Direccion_ps_peticion
 */
public interface Direccion_ps_peticionLocalHome
	extends javax.ejb.EJBLocalHome {
	/**
	 * Creates an instance from a key for Entity Bean: Direccion_ps_peticion
	 */
	 static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/ejb/eb/Direccion_ps_peticionLocalHome";
	 
	public co.com.telefonica.atiempo.ejb.eb.Direccion_ps_peticionLocal create(
		java.lang.Long dir_ps_peti_id)
		throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Direccion_ps_peticion
	 */
	public co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.Direccion_ps_peticionLocal findByPrimaryKey(
			co
				.com
				.telefonica
				.atiempo
				.ejb
				.eb
				.Direccion_ps_peticionKey primaryKey)
		throws javax.ejb.FinderException;
}
