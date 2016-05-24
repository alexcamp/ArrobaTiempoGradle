package co.com.telefonica.atiempo.soltec.ejb.eb;
/**
 * Key class for Entity Bean: Flujo
 */
public class FlujoKey implements java.io.Serializable {
	static final long serialVersionUID = 3206093459760846163L;
	/**
	 * Implementation field for persistent attribute: fluj_id
	 */
	public java.lang.Integer fluj_id;
	/**
	 * Creates an empty key for Entity Bean: Flujo
	 */
	public FlujoKey() {
	}
	/**
	 * Creates a key for Entity Bean: Flujo
	 */
	public FlujoKey(java.lang.Integer fluj_id) {
		this.fluj_id = fluj_id;
	}
	/**
	 * Returns true if both keys are equal.
	 */
	public boolean equals(java.lang.Object otherKey) {
		if (otherKey
			instanceof co.com.telefonica.atiempo.soltec.ejb.eb.FlujoKey) {
			co.com.telefonica.atiempo.soltec.ejb.eb.FlujoKey o =
				(co.com.telefonica.atiempo.soltec.ejb.eb.FlujoKey) otherKey;
			return ((this.fluj_id.equals(o.fluj_id)));
		}
		return false;
	}
	/**
	 * Returns the hash code for the key.
	 */
	public int hashCode() {
		return (fluj_id.hashCode());
	}
}
