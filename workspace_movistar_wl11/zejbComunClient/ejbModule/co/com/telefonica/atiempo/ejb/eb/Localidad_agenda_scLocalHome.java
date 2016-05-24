package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local Home interface for Enterprise Bean: Localidad_agenda_sc
 */
public interface Localidad_agenda_scLocalHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates an instance from a key for Entity Bean: Localidad_agenda_sc
	 */
	
	static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/ejb/eb/Localidad_agenda_scLocalHome";
	
	public co.com.telefonica.atiempo.ejb.eb.Localidad_agenda_scLocal create(
		java.lang.String cod_loc) throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Localidad_agenda_sc
	 */
	public co.com.telefonica.atiempo.ejb.eb.Localidad_agenda_scLocal findByPrimaryKey(
		co.com.telefonica.atiempo.ejb.eb.Localidad_agenda_scKey primaryKey)
		throws javax.ejb.FinderException;
}