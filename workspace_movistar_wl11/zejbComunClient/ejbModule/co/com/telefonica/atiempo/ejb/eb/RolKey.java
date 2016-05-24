package co.com.telefonica.atiempo.ejb.eb;
/**
 * Key class for Entity Bean: Rol
 */
public class RolKey implements java.io.Serializable {
	static final long serialVersionUID = 3206093459760846163L;
	/**
	 * Implementation field for persistent attribute: rol_id
	 */
	public java.lang.Long rol_id;
	/**
	 * Creates an empty key for Entity Bean: Rol
	 */
	public RolKey() {
	}
	/**
	 * Creates a key for Entity Bean: Rol
	 */
	public RolKey(java.lang.Long rol_id) {
		this.rol_id = rol_id;
	}
	/**
	 * Returns true if both keys are equal.
	 */
	public boolean equals(java.lang.Object otherKey) {
		if (otherKey instanceof co.com.telefonica.atiempo.ejb.eb.RolKey) {
			co.com.telefonica.atiempo.ejb.eb.RolKey o =
				(co.com.telefonica.atiempo.ejb.eb.RolKey) otherKey;
			return ((this.rol_id.equals(o.rol_id)));
		}
		return false;
	}
	/**
	 * Returns the hash code for the key.
	 */
	public int hashCode() {
		return (rol_id.hashCode());
	}
}
