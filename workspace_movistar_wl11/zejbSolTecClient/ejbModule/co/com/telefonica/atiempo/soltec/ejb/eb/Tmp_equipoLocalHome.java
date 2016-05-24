package co.com.telefonica.atiempo.soltec.ejb.eb;



/**
 * Local Home interface for Enterprise Bean: Tmp_equipo
 */
public interface Tmp_equipoLocalHome extends javax.ejb.EJBLocalHome {
    /**
     * Creates an instance from a key for Entity Bean: Tmp_equipo
     */
	static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/soltec/ejb/eb/Tmp_equipoLocalHome";
    
    public co.com.telefonica.atiempo.soltec.ejb.eb.Tmp_equipoLocal create(
        java.lang.Long id) throws javax.ejb.CreateException;
    /**
     * Finds an instance using a key for Entity Bean: Tmp_equipo
     */
    public co.com.telefonica.atiempo.soltec.ejb.eb.Tmp_equipoLocal findByPrimaryKey(
        co.com.telefonica.atiempo.soltec.ejb.eb.Tmp_equipoKey primaryKey)
        throws javax.ejb.FinderException;
	public co.com.telefonica.atiempo.soltec.ejb.eb.Tmp_equipoLocal create(
			java.lang.Long id,
			Peticion_stLocal peticionLocal,
			String xml)
			throws javax.ejb.CreateException;
    public java.util.Collection findByCodAveCd(java.lang.Long codAveCd)
        throws javax.ejb.FinderException;
}
