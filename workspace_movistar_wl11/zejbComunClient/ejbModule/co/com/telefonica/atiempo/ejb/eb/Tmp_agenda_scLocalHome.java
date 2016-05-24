package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local Home interface for Enterprise Bean: Tmp_agenda_sc
 */
public interface Tmp_agenda_scLocalHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates an instance from a key for Entity Bean: Tmp_agenda_sc
	 */
	static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/ejb/eb/Tmp_agenda_scLocalHome";
	
	public co.com.telefonica.atiempo.ejb.eb.Tmp_agenda_scLocal create(
		java.lang.Long id) throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Tmp_agenda_sc
	 */
	public co.com.telefonica.atiempo.ejb.eb.Tmp_agenda_scLocal findByPrimaryKey(
		co.com.telefonica.atiempo.ejb.eb.Tmp_agenda_scKey primaryKey)
		throws javax.ejb.FinderException;
	public co.com.telefonica.atiempo.ejb.eb.Tmp_agenda_scLocal findbyPeti_numero(java.lang.Long peti_numero) throws javax.ejb.FinderException;
	public co.com.telefonica.atiempo.ejb.eb.Tmp_agenda_scLocal findID(java.lang.Long id) throws javax.ejb.FinderException;
}