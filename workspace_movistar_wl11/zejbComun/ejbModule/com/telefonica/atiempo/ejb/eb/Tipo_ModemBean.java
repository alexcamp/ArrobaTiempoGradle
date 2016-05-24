package com.telefonica.atiempo.ejb.eb;
/**
 * Bean implementation class for Enterprise Bean: Tipo_Modem
 */
public abstract class Tipo_ModemBean implements javax.ejb.EntityBean {
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
    public java.lang.Integer ejbCreate(java.lang.Integer id_tipo_modem)
        throws javax.ejb.CreateException {
        setId_tipo_modem(id_tipo_modem);
        return null;
    }
    /**
     * ejbPostCreate
     */
    public void ejbPostCreate(java.lang.Integer id_tipo_modem)
        throws javax.ejb.CreateException {
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
     * Get accessor for persistent attribute: id_tipo_modem
     */
    public abstract java.lang.Integer getId_tipo_modem();
    /**
     * Set accessor for persistent attribute: id_tipo_modem
     */
    public abstract void setId_tipo_modem(java.lang.Integer newId_tipo_modem);
    /**
     * Get accessor for persistent attribute: desc_tipo
     */
    public abstract java.lang.String getDesc_tipo();
    /**
     * Set accessor for persistent attribute: desc_tipo
     */
    public abstract void setDesc_tipo(java.lang.String newDesc_tipo);
}
