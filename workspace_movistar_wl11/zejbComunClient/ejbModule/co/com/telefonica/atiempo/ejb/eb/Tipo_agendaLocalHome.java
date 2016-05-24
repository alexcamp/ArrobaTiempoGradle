package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local Home interface for Enterprise Bean: Tipo_agenda
 */
public interface Tipo_agendaLocalHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates an instance from a key for Entity Bean: Tipo_agenda
	 */
	 static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/ejb/eb/Tipo_agendaLocalHome";
	 
	public co.com.telefonica.atiempo.ejb.eb.Tipo_agendaLocal create(
		java.lang.Long tiag_id)
		throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Tipo_agenda
	 */
	public co.com.telefonica.atiempo.ejb.eb.Tipo_agendaLocal findByPrimaryKey(
		co.com.telefonica.atiempo.ejb.eb.Tipo_agendaKey primaryKey)
		throws javax.ejb.FinderException;
}
