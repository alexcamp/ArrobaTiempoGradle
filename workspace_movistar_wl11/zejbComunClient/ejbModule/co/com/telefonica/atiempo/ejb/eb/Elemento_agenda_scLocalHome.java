package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local Home interface for Enterprise Bean: Elemento_agenda_sc
 */
public interface Elemento_agenda_scLocalHome extends javax.ejb.EJBLocalHome {

    public static final String JNDI_NAME = "ejb/co/com/telefonica/atiempo/ejb/eb/Elemento_agenda_scLocalHome";
	/**
	 * Creates an instance from a key for Entity Bean: Elemento_agenda_sc
	 */
	public co.com.telefonica.atiempo.ejb.eb.Elemento_agenda_scLocal create(
		java.lang.Integer id_correlativo) throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Elemento_agenda_sc
	 */
	public co.com.telefonica.atiempo.ejb.eb.Elemento_agenda_scLocal findByPrimaryKey(
		co.com.telefonica.atiempo.ejb.eb.Elemento_agenda_scKey primaryKey)
		throws javax.ejb.FinderException;
	public co.com.telefonica.atiempo.ejb.eb.Elemento_agenda_scLocal findByDescEquipo(java.lang.String desc_equipo) throws javax.ejb.FinderException;
}