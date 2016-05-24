package co.com.telefonica.atiempo.ejb.eb;
/**
 * Key class for Entity Bean: Flujo_definicion
 */
public class Flujo_definicionKey implements java.io.Serializable {
	static final long serialVersionUID = 3206093459760846163L;
	/**
	 * Implementation field for persistent attribute: flde_id
	 */
	public java.lang.Integer flde_id;
	/**
	 * Creates an empty key for Entity Bean: Flujo_definicion
	 */
	public Flujo_definicionKey() {
	}
	/**
	 * Creates a key for Entity Bean: Flujo_definicion
	 */
	public Flujo_definicionKey(java.lang.Integer flde_id) {
		this.flde_id = flde_id;
	}
	/**
	 * Returns true if both keys are equal.
	 */
	public boolean equals(java.lang.Object otherKey) {
		if (otherKey
			instanceof co.com.telefonica.atiempo.ejb.eb.Flujo_definicionKey) {
			co.com.telefonica.atiempo.ejb.eb.Flujo_definicionKey o =
				(co.com.telefonica.atiempo.ejb.eb.Flujo_definicionKey) otherKey;
			return ((this.flde_id.equals(o.flde_id)));
		}
		return false;
	}
	/**
	 * Returns the hash code for the key.
	 */
	public int hashCode() {
		return (flde_id.hashCode());
	}
}
