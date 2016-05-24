package co.com.telefonica.atiempo.ejb.eb;
/**
 * Key class for Entity Bean: Causa
 */
public class CausaKey implements java.io.Serializable {
	static final long serialVersionUID = 3206093459760846163L;
	/**
	 * Implementation field for persistent attribute: caus_id
	 */
	public java.lang.Long caus_id;
	/**
	 * Creates an empty key for Entity Bean: Causa
	 */
	public CausaKey() {
	}
	/**
	 * Creates a key for Entity Bean: Causa
	 */
	public CausaKey(java.lang.Long caus_id) {
		this.caus_id = caus_id;
	}
	/**
	 * Returns true if both keys are equal.
	 */
	public boolean equals(java.lang.Object otherKey) {
		if (otherKey instanceof co.com.telefonica.atiempo.ejb.eb.CausaKey) {
			co.com.telefonica.atiempo.ejb.eb.CausaKey o =
				(co.com.telefonica.atiempo.ejb.eb.CausaKey) otherKey;
			return ((this.caus_id.equals(o.caus_id)));
		}
		return false;
	}
	/**
	 * Returns the hash code for the key.
	 */
	public int hashCode() {
		return (caus_id.hashCode());
	}
}
