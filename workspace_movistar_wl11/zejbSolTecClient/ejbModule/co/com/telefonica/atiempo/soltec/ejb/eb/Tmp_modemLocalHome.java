package co.com.telefonica.atiempo.soltec.ejb.eb;
/**
 * Local Home interface for Enterprise Bean: Tmp_modem
 */
public interface Tmp_modemLocalHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates an instance from a key for Entity Bean: Tmp_modem
	 */
	static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/soltec/ejb/eb/Tmp_modemLocalHome";
	
	/**
	 * Finds an instance using a key for Entity Bean: Tmp_modem
	 */
	public co
		.com
		.telefonica
		.atiempo
		.soltec
		.ejb
		.eb
		.Tmp_modemLocal findByPrimaryKey(
		co.com.telefonica.atiempo.soltec.ejb.eb.Tmp_modemKey primaryKey)
		throws javax.ejb.FinderException;
	/**
	 * ejbCreate
	 */
	public co.com.telefonica.atiempo.soltec.ejb.eb.Tmp_modemLocal create(
		java.lang.Long id,
		Peticion_stLocal peticion_stLocal,
		String xml)
		throws javax.ejb.CreateException;
	public java.util.Collection findByCodAveCd(java.lang.Long codAveCd)
		throws javax.ejb.FinderException;
}
