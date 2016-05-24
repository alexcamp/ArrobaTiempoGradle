package com.telefonica.atiempo.ejb.eb;
/**
 * Key class for Entity Bean: Ps_Tipo_Modem
 */
public class Ps_Tipo_ModemKey implements java.io.Serializable {
    static final long serialVersionUID = 3206093459760846163L;
    /**
     * Implementation field for persistent attribute: pd_id
     */
    public java.lang.Integer pd_id;
    /**
     * Implementation field for persistent attribute: id_tipo_modem
     */
    public java.lang.Integer id_tipo_modem;
    /**
     * Creates an empty key for Entity Bean: Ps_Tipo_Modem
     */
    public Ps_Tipo_ModemKey() {
    }
    /**
     * Creates a key for Entity Bean: Ps_Tipo_Modem
     */
    public Ps_Tipo_ModemKey(
        java.lang.Integer pd_id,
        java.lang.Integer id_tipo_modem) {
        this.pd_id = pd_id;
        this.id_tipo_modem = id_tipo_modem;
    }
    /**
     * Returns true if both keys are equal.
     */
    public boolean equals(java.lang.Object otherKey) {
        if (otherKey instanceof com.telefonica.atiempo.ejb.eb.Ps_Tipo_ModemKey) {
            com.telefonica.atiempo.ejb.eb.Ps_Tipo_ModemKey o = (com.telefonica.atiempo.ejb.eb.Ps_Tipo_ModemKey) otherKey;
            return ((this.pd_id.equals(o.pd_id)) && (this.id_tipo_modem
                .equals(o.id_tipo_modem)));
        }
        return false;
    }
    /**
     * Returns the hash code for the key.
     */
    public int hashCode() {
        return (pd_id.hashCode() + id_tipo_modem.hashCode());
    }
    /**
     * Get accessor for persistent attribute: pd_id
     */
    public java.lang.Integer getPd_id() {
        return pd_id;
    }
    /**
     * Set accessor for persistent attribute: pd_id
     */
    public void setPd_id(java.lang.Integer newPd_id) {
        pd_id = newPd_id;
    }
    /**
     * Get accessor for persistent attribute: id_tipo_modem
     */
    public java.lang.Integer getId_tipo_modem() {
        return id_tipo_modem;
    }
    /**
     * Set accessor for persistent attribute: id_tipo_modem
     */
    public void setId_tipo_modem(java.lang.Integer newId_tipo_modem) {
        id_tipo_modem = newId_tipo_modem;
    }
}
