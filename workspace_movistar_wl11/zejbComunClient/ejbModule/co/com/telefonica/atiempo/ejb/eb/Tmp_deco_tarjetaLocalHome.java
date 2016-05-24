package co.com.telefonica.atiempo.ejb.eb;
import javax.ejb.CreateException;
/**
 * Local Home interface for Enterprise Bean: Tmp_deco_tarjeta
 */
public interface Tmp_deco_tarjetaLocalHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates an instance from a key for Entity Bean: Tmp_deco_tarjeta
	 */
	static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/ejb/eb/Tmp_deco_tarjetaLocalHome";
	
	/**
	 * Finds an instance using a key for Entity Bean: Tmp_deco_tarjeta
	 */
	public co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.Tmp_deco_tarjetaLocal findByPrimaryKey(
		co.com.telefonica.atiempo.ejb.eb.Tmp_deco_tarjetaKey primaryKey)
		throws javax.ejb.FinderException;
	public java.util.Collection findByNroPeticion(java.lang.Long nroPeticion) throws javax.ejb.FinderException;
	/**
	 * ejbCreate
	 */
	public co.com.telefonica.atiempo.ejb.eb.Tmp_deco_tarjetaLocal create(
		Long id,
		PeticionLocal pet,
		String xml)
		throws CreateException;
}
