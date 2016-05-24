package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local Home interface for Enterprise Bean: Respuesta_conect4_apsc
 */
public interface Respuesta_conect4_apscLocalHome
	extends javax.ejb.EJBLocalHome {
	/**
	 * Creates an instance from a key for Entity Bean: Respuesta_conect4_apsc
	 */
	 static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/ejb/eb/Respuesta_conect4_apscLocalHome";
	 
	public co.com.telefonica.atiempo.ejb.eb.Respuesta_conect4_apscLocal create(
		java.lang.Long id_conector)
		throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Respuesta_conect4_apsc
	 */
	public co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.Respuesta_conect4_apscLocal findByPrimaryKey(
			co
				.com
				.telefonica
				.atiempo
				.ejb
				.eb
				.Respuesta_conect4_apscKey primaryKey)
		throws javax.ejb.FinderException;
}
