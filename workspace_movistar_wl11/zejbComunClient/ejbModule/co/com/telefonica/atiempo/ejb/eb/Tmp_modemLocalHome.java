package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local Home interface for Enterprise Bean: Tmp_modem
 */
public interface Tmp_modemLocalHome extends javax.ejb.EJBLocalHome {
	/**
	 * Finds an instance using a key for Entity Bean: Tmp_modem
	 */
	static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/ejb/eb/Tmp_modemLocalHome";
	
	public co.com.telefonica.atiempo.ejb.eb.Tmp_modemLocal findByPrimaryKey(
		co.com.telefonica.atiempo.ejb.eb.Tmp_modemKey primaryKey)
		throws javax.ejb.FinderException;
	public co.com.telefonica.atiempo.ejb.eb.Tmp_modemLocal create(
		java.lang.Long id,
		PeticionLocal peticionLocal,
		String xml)
		throws javax.ejb.CreateException;
	public java.util.Collection findByNroPeticion(java.lang.Long nroPeticion) throws javax.ejb.FinderException;
}
