package co.com.telefonica.atiempo.ejb.eb;
/**
 * Key class for Entity Bean: Ps_rol
 */
public class Ps_rolKey implements java.io.Serializable {
	static final long serialVersionUID = 3206093459760846163L;
	/**
	 * Implementation field for persistent attribute: ps_id
	 */
	public java.lang.Long ps_id;
	/**
	 * Creates an empty key for Entity Bean: Ps_rol
	 */
	public Ps_rolKey() {
	}
	/**
	 * Creates a key for Entity Bean: Ps_rol
	 */
	public Ps_rolKey(java.lang.Long ps_id) {
		this.ps_id = ps_id;
	}
	/**
	 * Returns true if both keys are equal.
	 */
	public boolean equals(java.lang.Object otherKey) {
		if (otherKey instanceof co.com.telefonica.atiempo.ejb.eb.Ps_rolKey) {
			co.com.telefonica.atiempo.ejb.eb.Ps_rolKey o = (co.com.telefonica.atiempo.ejb.eb.Ps_rolKey) otherKey;
			return ((this.ps_id.equals(o.ps_id)));
		}
		return false;
	}
	/**
	 * Returns the hash code for the key.
	 */
	public int hashCode() {
		return (ps_id.hashCode());
	}
}