package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local Home interface for Enterprise Bean: Respuesta_conec6_apsc_linea
 */
public interface Respuesta_conec6_apsc_lineaLocalHome
	extends javax.ejb.EJBLocalHome {
	/**
	 * Creates an instance from a key for Entity Bean: Respuesta_conec6_apsc_linea
	 */
	 static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/ejb/eb/Respuesta_conec6_apsc_lineaLocalHome";
	 
	public co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.Respuesta_conec6_apsc_lineaLocal create(
		java.lang.Long id_conector)
		throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Respuesta_conec6_apsc_linea
	 */
	public co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.Respuesta_conec6_apsc_lineaLocal findByPrimaryKey(
			co
				.com
				.telefonica
				.atiempo
				.ejb
				.eb
				.Respuesta_conec6_apsc_lineaKey primaryKey)
		throws javax.ejb.FinderException;
}
