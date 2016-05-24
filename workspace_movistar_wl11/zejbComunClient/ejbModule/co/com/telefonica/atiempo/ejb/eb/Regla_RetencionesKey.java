package co.com.telefonica.atiempo.ejb.eb;
/**
 * Key class for Entity Bean: Regla_Retenciones
 */
public class Regla_RetencionesKey implements java.io.Serializable {
	static final long serialVersionUID = 3206093459760846163L;
	/**
	 * Implementation field for persistent attribute: regla_id
	 */
	public java.lang.Long regla_id;
	/**
	 * Creates an empty key for Entity Bean: Regla_Retenciones
	 */
	public Regla_RetencionesKey() {
	}
	
	/**
	 * Returns true if both keys are equal.
	 */
	public boolean equals(java.lang.Object otherKey) {
		if (otherKey instanceof co.com.telefonica.atiempo.ejb.eb.Regla_RetencionesKey) {
			co.com.telefonica.atiempo.ejb.eb.Regla_RetencionesKey o = (co.com.telefonica.atiempo.ejb.eb.Regla_RetencionesKey) otherKey;
			return ((this.regla_id.equals(o.regla_id)));
		}
		return false;
	}
	/**
	 * Returns the hash code for the key.
	 */
	public int hashCode() {
		return (regla_id.hashCode());
	}
	/**
	 * Creates a key for Entity Bean: Regla_Retenciones
	 */
	public Regla_RetencionesKey(java.lang.Long regla_id) {
		this.regla_id = regla_id;
	}
	/**
	 * Get accessor for persistent attribute: regla_id
	 */
	public java.lang.Long getRegla_id() {
		return regla_id;
	}
	/**
	 * Set accessor for persistent attribute: regla_id
	 */
	public void setRegla_id(java.lang.Long newRegla_id) {
		regla_id = newRegla_id;
	}
}
