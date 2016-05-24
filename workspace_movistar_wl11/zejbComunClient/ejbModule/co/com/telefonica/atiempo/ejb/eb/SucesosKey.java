package co.com.telefonica.atiempo.ejb.eb;
/**
 * Key class for Entity Bean: Sucesos
 */
public class SucesosKey implements java.io.Serializable {
	static final long serialVersionUID = 3206093459760846163L;
	/**
	 * Implementation field for persistent attribute: id
	 */
	public java.lang.Integer id;
	/**
	 * Creates an empty key for Entity Bean: Sucesos
	 */
	public SucesosKey() {
	}
	/**
	 * Creates a key for Entity Bean: Sucesos
	 */
	public SucesosKey(java.lang.Integer id) {
		this.id = id;
	}
	/**
	 * Returns true if both keys are equal.
	 */
	public boolean equals(java.lang.Object otherKey) {
		if (otherKey instanceof co.com.telefonica.atiempo.ejb.eb.SucesosKey) {
			co.com.telefonica.atiempo.ejb.eb.SucesosKey o = (co.com.telefonica.atiempo.ejb.eb.SucesosKey) otherKey;
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
}