package co.com.telefonica.atiempo.soltec.ejb.eb;
import javax.ejb.CreateException;
/**
 * Local Home interface for Enterprise Bean: Recursos_linea_basica
 */
public interface Recursos_linea_basicaLocalHome
	extends javax.ejb.EJBLocalHome {
	/**
	 * Creates an instance from a key for Entity Bean: Recursos_linea_basica
	 */
	static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/soltec/ejb/eb/Recursos_linea_basicaLocalHome";
	
	/**
	 * Finds an instance using a key for Entity Bean: Recursos_linea_basica
	 */
	public co
		.com
		.telefonica
		.atiempo
		.soltec
		.ejb
		.eb
		.Recursos_linea_basicaLocal findByPrimaryKey(
			co
				.com
				.telefonica
				.atiempo
				.soltec
				.ejb
				.eb
				.Recursos_linea_basicaKey primaryKey)
		throws javax.ejb.FinderException;
	public co
		.com
		.telefonica
		.atiempo
		.soltec
		.ejb
		.eb
		.Recursos_linea_basicaLocal create(
			java.lang.Long id_conector,
			Peticion_stLocal pet)
		throws CreateException;
	/**
	 * Creates an instance from a key for Entity Bean: Recursos_linea_basica
	 */
	public co
		.com
		.telefonica
		.atiempo
		.soltec
		.ejb
		.eb
		.Recursos_linea_basicaLocal create(java.lang.Long id_conector)
		throws javax.ejb.CreateException;
	public co.com.telefonica.atiempo.soltec.ejb.eb.Recursos_linea_basicaLocal findbyCod_ave_cd(java.lang.Long cod_ave_cd) throws javax.ejb.FinderException;
}
