package com.telefonica.atiempo.ejb.eb;
/**
 * Bean implementation class for Enterprise Bean: Ps_Tipo_Modem
 */
public abstract class Ps_Tipo_ModemBean implements javax.ejb.EntityBean {
    private javax.ejb.EntityContext myEntityCtx;
    /**
     * setEntityContext
     */
    public void setEntityContext(javax.ejb.EntityContext ctx) {
        myEntityCtx = ctx;
    }
    /**
     * getEntityContext
     */
    public javax.ejb.EntityContext getEntityContext() {
        return myEntityCtx;
    }
    /**
     * unsetEntityContext
     */
    public void unsetEntityContext() {
        myEntityCtx = null;
    }
    /**
     * ejbCreate
     */
    public com.telefonica.atiempo.ejb.eb.Ps_Tipo_ModemKey ejbCreate(
        java.lang.Integer pd_id,
        java.lang.Integer id_tipo_modem) throws javax.ejb.CreateException {
        setPd_id(pd_id);
        setId_tipo_modem(id_tipo_modem);
        return null;
    }
    /**
     * ejbPostCreate
     */
    public void ejbPostCreate(
        java.lang.Integer pd_id,
        java.lang.Integer id_tipo_modem) throws javax.ejb.CreateException {
    }
    /**
     * ejbActivate
     */
    public void ejbActivate() {
    }
    /**
     * ejbLoad
     */
    public void ejbLoad() {
    }
    /**
     * ejbPassivate
     */
    public void ejbPassivate() {
    }
    /**
     * ejbRemove
     */
    public void ejbRemove() throws javax.ejb.RemoveException {
    }
    /**
     * ejbStore
     */
    public void ejbStore() {
    }
    /**
     * Get accessor for persistent attribute: pd_id
     */
    public abstract java.lang.Integer getPd_id();
    /**
     * Set accessor for persistent attribute: pd_id
     */
    public abstract void setPd_id(java.lang.Integer newPd_id);
    /**
     * Get accessor for persistent attribute: id_tipo_modem
     */
    public abstract java.lang.Integer getId_tipo_modem();
    /**
     * Set accessor for persistent attribute: id_tipo_modem
     */
    public abstract void setId_tipo_modem(java.lang.Integer newId_tipo_modem);
}
