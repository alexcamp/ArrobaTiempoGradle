package com.telefonica.atiempo.ejb.eb;
/**
 * Local Home interface for Enterprise Bean: Ps_Tipo_Modem
 */
public interface Ps_Tipo_ModemLocalHome extends javax.ejb.EJBLocalHome {
    
    static final String JNDI_NAME="ejb/com/telefonica/atiempo/ejb/eb/Ps_Tipo_ModemLocalHome";
    /**
     * Creates an instance from a key for Entity Bean: Ps_Tipo_Modem
     */
    public com.telefonica.atiempo.ejb.eb.Ps_Tipo_ModemLocal create(
        java.lang.Integer pd_id,
        java.lang.Integer id_tipo_modem) throws javax.ejb.CreateException;
    /**
     * Finds an instance using a key for Entity Bean: Ps_Tipo_Modem
     */
    public com.telefonica.atiempo.ejb.eb.Ps_Tipo_ModemLocal findByPrimaryKey(
        com.telefonica.atiempo.ejb.eb.Ps_Tipo_ModemKey primaryKey)
        throws javax.ejb.FinderException;
    
	public com.telefonica.atiempo.ejb.eb.Ps_Tipo_ModemLocal findByNroPs(
		java.lang.Long nroPs) throws javax.ejb.FinderException;
	public java.util.Collection findAll() throws javax.ejb.FinderException;
}
