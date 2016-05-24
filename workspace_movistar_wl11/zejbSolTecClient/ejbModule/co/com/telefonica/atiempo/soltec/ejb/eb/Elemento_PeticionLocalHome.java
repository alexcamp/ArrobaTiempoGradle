package co.com.telefonica.atiempo.soltec.ejb.eb;

import co.com.telefonica.atiempo.soltec.ejb.eb.Peticion_stLocal;

/**
 * Local Home interface for Enterprise Bean: Elemento_Peticion
 */
public interface Elemento_PeticionLocalHome extends javax.ejb.EJBLocalHome {
    /**
     * Creates an instance from a key for Entity Bean: Elemento_Peticion
     */
    public static final String JNDI_NAME ="ejb/co/com/telefonica/atiempo/soltec/ejb/eb/Elemento_PeticionLocalHome";
	/**
	 * ejbCreate
	 */
	public co.com.telefonica.atiempo.soltec.ejb.eb.Elemento_PeticionLocal create(
			String serial,
			Peticion_stLocal peticion,
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
    public co.com.telefonica.atiempo.soltec.ejb.eb.Elemento_PeticionLocal  findByPrimaryKey(  
        co.com.telefonica.atiempo.soltec.ejb.eb.Elemento_PeticionKey primaryKey)
        throws javax.ejb.FinderException;
    /**
     * Creates an instance from a key for Entity Bean: Elemento_Peticion
     */
    public co.com.telefonica.atiempo.soltec.ejb.eb.Elemento_PeticionLocal create(
        java.lang.String seriale,
        co.com.telefonica.atiempo.soltec.ejb.eb.Peticion_stLocal argPeticion_st)
        throws javax.ejb.CreateException;
    /**
     * Creates an instance from a key for Entity Bean: Elemento_Peticion
     */
    public co.com.telefonica.atiempo.soltec.ejb.eb.Elemento_PeticionLocal create(
        java.lang.String seriale,
        java.lang.Long peticion_st_cod_ave_cd) throws javax.ejb.CreateException;
}
