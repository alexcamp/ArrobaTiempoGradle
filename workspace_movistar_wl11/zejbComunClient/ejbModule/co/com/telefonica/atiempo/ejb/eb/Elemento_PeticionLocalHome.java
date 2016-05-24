package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local Home interface for Enterprise Bean: Elemento_Peticion
 */
public interface Elemento_PeticionLocalHome extends javax.ejb.EJBLocalHome {
    /**
     * Creates an instance from a key for Entity Bean: Elemento_Peticion
     */
    public static final String JNDI_NAME ="ejb/co/com/telefonica/atiempo/ejb/eb/Elemento_PeticionLocalHome";
	/**
	 * ejbCreate
	 */
	public co.com.telefonica.atiempo.ejb.eb.Elemento_PeticionLocal create(
			String serial,
			PeticionLocal peticion,
			Long nroTel,
			Short accion,
			String tipo_equipo,
			String tipo_inventario,
			Long tipo_elemento,
			Long ps_id)
			throws javax.ejb.CreateException;
    /**
     * Finds an instance using a key for Entity Bean: Elemento_Peticion
     */
    public co.com.telefonica.atiempo.ejb.eb.Elemento_PeticionLocal findByPrimaryKey(
        co.com.telefonica.atiempo.ejb.eb.Elemento_PeticionKey primaryKey)
        throws javax.ejb.FinderException;
    /**
     * Creates an instance from a key for Entity Bean: Elemento_Peticion
     */    
    public java.util.Collection findPeticion(java.lang.Long peticion)
        throws javax.ejb.FinderException;
	public java.util.Collection findbyPetiNumeroPsId(java.lang.Long peti_numero, java.lang.Integer ps_id) throws javax.ejb.FinderException;
	/**
	 * Creates an instance from a key for Entity Bean: Elemento_Peticion
	 */
	public co.com.telefonica.atiempo.ejb.eb.Elemento_PeticionLocal create(
		java.lang.String serial,
		java.lang.Long peticion_peti_numero) throws javax.ejb.CreateException;
}
