package co.com.telefonica.atiempo.soltec.ejb.eb;
/**
 * Local Home interface for Enterprise Bean: AgendaSCST
 */
public interface AgendaSCSTLocalHome extends javax.ejb.EJBLocalHome {
	public static final String JNDI_NAME = "ejb/co/com/telefonica/atiempo/soltec/ejb/eb/AgendaSCSTLocalHome";
	
	/**
	 * Creates an instance from a key for Entity Bean: AgendaSCST
	 */
	public co.com.telefonica.atiempo.soltec.ejb.eb.AgendaSCSTLocal create(
		java.lang.String id_actuacion) throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: AgendaSCST
	 */
	public co.com.telefonica.atiempo.soltec.ejb.eb.AgendaSCSTLocal findByPrimaryKey(
		java.lang.String primaryKey) throws javax.ejb.FinderException;
	public java.util.Collection findByCodAve(java.lang.Long id_peticion_st) throws javax.ejb.FinderException;
	public co.com.telefonica.atiempo.soltec.ejb.eb.AgendaSCSTLocal findByCodAveMaxFechaIngreso(
		java.lang.Long id_peticion_st) throws javax.ejb.FinderException;
}
