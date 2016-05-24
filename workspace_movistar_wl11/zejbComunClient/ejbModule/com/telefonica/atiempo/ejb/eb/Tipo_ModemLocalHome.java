package com.telefonica.atiempo.ejb.eb;
/**
 * Local Home interface for Enterprise Bean: Tipo_Modem
 */
public interface Tipo_ModemLocalHome extends javax.ejb.EJBLocalHome {
    
    static final String JNDI_NAME="ejb/com/telefonica/atiempo/ejb/eb/Tipo_ModemLocalHome";
    /**
     * Creates an instance from a key for Entity Bean: Tipo_Modem
     */
    public com.telefonica.atiempo.ejb.eb.Tipo_ModemLocal create(
        java.lang.Integer id_tipo_modem) throws javax.ejb.CreateException;
    /**
     * Finds an instance using a key for Entity Bean: Tipo_Modem
     */
    public com.telefonica.atiempo.ejb.eb.Tipo_ModemLocal findByPrimaryKey(
        java.lang.Integer primaryKey) throws javax.ejb.FinderException;
}
