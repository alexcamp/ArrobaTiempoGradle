package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local Home interface for Enterprise Bean: Controlvisita
 */
public interface ControlvisitaLocalHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates an instance from a key for Entity Bean: Controlvisita
	 */
	static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/ejb/eb/ControlvisitaLocalHome";
	
	/**
	 * Creates an instance from a key for Entity Bean: Controlvisita
	 */
	public co.com.telefonica.atiempo.ejb.eb.ControlvisitaLocal create(
		java.sql.Timestamp fechahora_registro,
		co.com.telefonica.atiempo.ejb.eb.PeticionLocal argPeticion)
		throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Controlvisita
	 */
	public co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.ControlvisitaLocal findByPrimaryKey(
		co.com.telefonica.atiempo.ejb.eb.ControlvisitaKey primaryKey)
		throws javax.ejb.FinderException;
	public java.util.Collection findByPetiNumero(java.lang.Long nroPeticion) throws javax.ejb.FinderException;
	/**
	 * Creates an instance from a key for Entity Bean: Controlvisita
	 */
	public co.com.telefonica.atiempo.ejb.eb.ControlvisitaLocal create(
		java.sql.Timestamp fechahora_registro,
		java.lang.Long peticion_peti_numero) throws javax.ejb.CreateException;
}
