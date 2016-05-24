package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local Home interface for Enterprise Bean: Agenda_sc
 */
public interface Agenda_scLocalHome extends javax.ejb.EJBLocalHome {
	static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/ejb/eb/Agenda_scLocalHome";
	/**
	 * Creates an instance from a key for Entity Bean: Agenda_sc
	 */
	public co.com.telefonica.atiempo.ejb.eb.Agenda_scLocal create(
		java.lang.String id_actuacion) throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Agenda_sc
	 */
	public co.com.telefonica.atiempo.ejb.eb.Agenda_scLocal findByPrimaryKey(
		co.com.telefonica.atiempo.ejb.eb.Agenda_scKey primaryKey)
		throws javax.ejb.FinderException;
	public java.util.Collection findByPetiNumero(java.lang.Long peti_numero)
		throws javax.ejb.FinderException;
	public co.com.telefonica.atiempo.ejb.eb.Agenda_scLocal findFechasByPetiNum(
		java.lang.Long petiNumero) throws javax.ejb.FinderException;
	public co.com.telefonica.atiempo.ejb.eb.Agenda_scLocal findFechasByPetiNumFecha(java.lang.Long petiNumero) throws javax.ejb.FinderException;
}