package co.com.telefonica.atiempo.ejb.eb;
/**
 * Key class for Entity Bean: Bintegrada
 */
public class BintegradaKey implements java.io.Serializable {
	static final long serialVersionUID = 3206093459760846163L;
	/**
	 * Implementation field for persistent attribute: bi_id
	 */
	public java.lang.Long bi_id;
	/**
	 * Creates an empty key for Entity Bean: Bintegrada
	 */
	public BintegradaKey() {
	}
	/**
	 * Creates a key for Entity Bean: Bintegrada
	 */
	public BintegradaKey(java.lang.Long bi_id) {
		this.bi_id = bi_id;
	}
	/**
	 * Returns true if both keys are equal.
	 */
	public boolean equals(java.lang.Object otherKey) {
		if (otherKey
			instanceof co.com.telefonica.atiempo.ejb.eb.BintegradaKey) {
			co.com.telefonica.atiempo.ejb.eb.BintegradaKey o =
				(co.com.telefonica.atiempo.ejb.eb.BintegradaKey) otherKey;
			return ((this.bi_id.equals(o.bi_id)));
		}
		return false;
	}
	/**
	 * Returns the hash code for the key.
	 */
	public int hashCode() {
		return (bi_id.hashCode());
	}
}
