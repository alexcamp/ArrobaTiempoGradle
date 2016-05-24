package co.com.telefonica.atiempo.soltec.ejb.eb;
import javax.ejb.CreateException;
/**
 * Local Home interface for Enterprise Bean: Recursos_ba
 */
public interface Recursos_baLocalHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates an instance from a key for Entity Bean: Recursos_ba
	 */
	static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/soltec/ejb/eb/Recursos_baLocalHome";
	
	/**
	 * Finds an instance using a key for Entity Bean: Recursos_ba
	 */
	public co
		.com
		.telefonica
		.atiempo
		.soltec
		.ejb
		.eb
		.Recursos_baLocal findByPrimaryKey(
			co.com.telefonica.atiempo.soltec.ejb.eb.Recursos_baKey primaryKey)
		throws javax.ejb.FinderException;
	public co.com.telefonica.atiempo.soltec.ejb.eb.Recursos_baLocal findByCorrelativo(java.lang.Long correlativo) throws javax.ejb.FinderException;
	public co.com.telefonica.atiempo.soltec.ejb.eb.Recursos_baLocal create(
		Long id_conector,
		Peticion_stLocal local)
		throws CreateException;
	public co.com.telefonica.atiempo.soltec.ejb.eb.Recursos_baLocal findbyPeti_numero(java.lang.Long peti_numero) throws javax.ejb.FinderException;
}
