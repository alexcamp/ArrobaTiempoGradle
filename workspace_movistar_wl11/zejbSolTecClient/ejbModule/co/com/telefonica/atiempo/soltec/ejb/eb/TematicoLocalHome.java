package co.com.telefonica.atiempo.soltec.ejb.eb;
/**
 * Local Home interface for Enterprise Bean: Tematico
 */
public interface TematicoLocalHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates an instance from a key for Entity Bean: Tematico
	 */
	static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/soltec/ejb/eb/TematicoLocalHome";
	
	public co.com.telefonica.atiempo.soltec.ejb.eb.TematicoLocal create(
		java.lang.Long id_tematico)
		throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Tematico
	 */
	public co
		.com
		.telefonica
		.atiempo
		.soltec
		.ejb
		.eb
		.TematicoLocal findByPrimaryKey(
		co.com.telefonica.atiempo.soltec.ejb.eb.TematicoKey primaryKey)
		throws javax.ejb.FinderException;
		
	public java.util.Collection findByPeticion(java.lang.Long nroPeticion)
		throws javax.ejb.FinderException;
	public co.com.telefonica.atiempo.soltec.ejb.eb.TematicoLocal create(
		Long id_tematico,
		String origen,
		Peticion_stLocal pet,
		String codtematico,
		Long correlativo)
		throws javax.ejb.CreateException;
}
