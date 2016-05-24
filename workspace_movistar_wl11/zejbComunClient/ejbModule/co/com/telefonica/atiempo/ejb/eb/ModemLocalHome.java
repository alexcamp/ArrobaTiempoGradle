package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local Home interface for Enterprise Bean: Modem
 */
public interface ModemLocalHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates an instance from a key for Entity Bean: Modem
	 */
	static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/ejb/eb/ModemLocalHome";
	
	/**
	 * Finds an instance using a key for Entity Bean: Modem
	 */
	public co.com.telefonica.atiempo.ejb.eb.ModemLocal findByPrimaryKey(
		co.com.telefonica.atiempo.ejb.eb.ModemKey primaryKey)
		throws javax.ejb.FinderException;
	public java.util.Collection findPeticion(java.lang.Long peticion) throws javax.ejb.FinderException;
	/**
	 * ejbCreate
	 */
	public co.com.telefonica.atiempo.ejb.eb.ModemLocal create(
		String serial,
		PeticionLocal peticion,
		Long nroTel,
		Short accion)
		throws javax.ejb.CreateException;
	
	public co.com.telefonica.atiempo.ejb.eb.ModemLocal create(
			String serial,
			PeticionLocal peticion,
			Long nroTel,
			Short accion,
			String marca,
			String modelo,
			Integer tipo)
			throws javax.ejb.CreateException;
	/**
	 * Creates an instance from a key for Entity Bean: Modem
	 */
	public co.com.telefonica.atiempo.ejb.eb.ModemLocal create(
		java.lang.String serial,
		co.com.telefonica.atiempo.ejb.eb.PeticionLocal argPeticion)
		throws javax.ejb.CreateException;
	/**
	 * Creates an instance from a key for Entity Bean: Modem
	 */
	public co.com.telefonica.atiempo.ejb.eb.ModemLocal create(
		java.lang.String serial,
		java.lang.Long peticion_peti_numero) throws javax.ejb.CreateException;
}
