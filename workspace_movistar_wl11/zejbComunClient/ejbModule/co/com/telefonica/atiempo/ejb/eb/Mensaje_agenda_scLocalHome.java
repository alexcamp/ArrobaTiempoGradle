package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local Home interface for Enterprise Bean: Mensaje_agenda_sc
 */
public interface Mensaje_agenda_scLocalHome extends javax.ejb.EJBLocalHome {
	
	static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/ejb/eb/Mensaje_agenda_scLocalHome";
	
	/**
	 * Creates an instance from a key for Entity Bean: Mensaje_agenda_sc
	 */
	public co.com.telefonica.atiempo.ejb.eb.Mensaje_agenda_scLocal create(
		java.lang.Long correlativo) throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Mensaje_agenda_sc
	 */
	public co.com.telefonica.atiempo.ejb.eb.Mensaje_agenda_scLocal findByPrimaryKey(
		co.com.telefonica.atiempo.ejb.eb.Mensaje_agenda_scKey primaryKey)
		throws javax.ejb.FinderException;
}