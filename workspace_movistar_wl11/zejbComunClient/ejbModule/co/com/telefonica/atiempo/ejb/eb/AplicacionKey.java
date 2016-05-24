package co.com.telefonica.atiempo.ejb.eb;
/**
 * Key class for Entity Bean: Aplicacion
 */
public class AplicacionKey implements java.io.Serializable {
	static final long serialVersionUID = 3206093459760846163L;
	/**
	 * Implementation field for persistent attribute: ap_id
	 */
	public java.lang.Long ap_id;
	/**
	 * Creates an empty key for Entity Bean: Aplicacion
	 */
	public AplicacionKey() {
	}
	/**
	 * Creates a key for Entity Bean: Aplicacion
	 */
	public AplicacionKey(java.lang.Long ap_id) {
		this.ap_id = ap_id;
	}
	/**
	 * Returns true if both keys are equal.
	 */
	public boolean equals(java.lang.Object otherKey) {
		if (otherKey
			instanceof co.com.telefonica.atiempo.ejb.eb.AplicacionKey) {
			co.com.telefonica.atiempo.ejb.eb.AplicacionKey o =
				(co.com.telefonica.atiempo.ejb.eb.AplicacionKey) otherKey;
			return ((this.ap_id.equals(o.ap_id)));
		}
		return false;
	}
	/**
	 * Returns the hash code for the key.
	 */
	public int hashCode() {
		return (ap_id.hashCode());
	}
}
