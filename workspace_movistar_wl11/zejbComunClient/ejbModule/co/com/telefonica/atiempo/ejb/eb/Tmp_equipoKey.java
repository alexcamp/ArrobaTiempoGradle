package co.com.telefonica.atiempo.ejb.eb;
/**
 * Key class for Entity Bean: Tmp_equipo
 */
public class Tmp_equipoKey implements java.io.Serializable {
    static final long serialVersionUID = 3206093459760846163L;
    /**
     * Implementation field for persistent attribute: id
     */
    public java.lang.Long id;
    /**
     * Creates an empty key for Entity Bean: Tmp_equipo
     */
    public Tmp_equipoKey() {
    }
    /**
     * Creates a key for Entity Bean: Tmp_equipo
     */
    public Tmp_equipoKey(java.lang.Long id) {
        this.id = id;
    }
    /**
     * Returns true if both keys are equal.
     */
    public boolean equals(java.lang.Object otherKey) {
        if (otherKey instanceof co.com.telefonica.atiempo.ejb.eb.Tmp_equipoKey) {
            co.com.telefonica.atiempo.ejb.eb.Tmp_equipoKey o = (co.com.telefonica.atiempo.ejb.eb.Tmp_equipoKey) otherKey;
            return ((this.id.equals(o.id)));
        }
        return false;
    }
    /**
     * Returns the hash code for the key.
     */
    public int hashCode() {
        return (id.hashCode());
    }
    /**
     * Get accessor for persistent attribute: id
     */
    public java.lang.Long getId() {
        return id;
    }
    /**
     * Set accessor for persistent attribute: id
     */
    public void setId(java.lang.Long newId) {
        id = newId;
    }
}
