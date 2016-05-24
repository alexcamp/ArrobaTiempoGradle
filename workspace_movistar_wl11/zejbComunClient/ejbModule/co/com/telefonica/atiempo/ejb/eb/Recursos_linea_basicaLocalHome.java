package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local Home interface for Enterprise Bean: Recursos_linea_basica
 */
public interface Recursos_linea_basicaLocalHome
	extends javax.ejb.EJBLocalHome {
	/**
	 * Creates an instance from a key for Entity Bean: Recursos_linea_basica
	 */
	static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/ejb/eb/Recursos_linea_basicaLocalHome";
	/**
	 * Creates an instance from a key for Entity Bean: Recursos_linea_basica
	 */
	public co.com.telefonica.atiempo.ejb.eb.Recursos_linea_basicaLocal create(
		java.lang.Long id_conector) throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Recursos_linea_basica
	 */
	public co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.Recursos_linea_basicaLocal findByPrimaryKey(
			co
				.com
				.telefonica
				.atiempo
				.ejb
				.eb
				.Recursos_linea_basicaKey primaryKey)
		throws javax.ejb.FinderException;
	public co.com.telefonica.atiempo.ejb.eb.Recursos_linea_basicaLocal findByPeticion(
		java.lang.Long idPeticion) 
		throws javax.ejb.FinderException;	
	public co.com.telefonica.atiempo.ejb.eb.Recursos_linea_basicaLocal findOds(
		java.lang.Long ods)
		throws javax.ejb.FinderException;
	
}
