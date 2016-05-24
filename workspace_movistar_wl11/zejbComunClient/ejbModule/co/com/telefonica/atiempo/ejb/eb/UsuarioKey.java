package co.com.telefonica.atiempo.ejb.eb;
/**
 * Key class for Entity Bean: Usuario
 */
public class UsuarioKey implements java.io.Serializable {
	static final long serialVersionUID = 3206093459760846163L;
	/**
	 * Implementation field for persistent attribute: usua_id
	 */
	public java.lang.Long usua_id;
	/**
	 * Creates an empty key for Entity Bean: Usuario
	 */
	public UsuarioKey() {
	}
	/**
	 * Creates a key for Entity Bean: Usuario
	 */
	public UsuarioKey(java.lang.Long usua_id) {
		this.usua_id = usua_id;
	}
	/**
	 * Returns true if both keys are equal.
	 */
	public boolean equals(java.lang.Object otherKey) {
		if (otherKey instanceof co.com.telefonica.atiempo.ejb.eb.UsuarioKey) {
			co.com.telefonica.atiempo.ejb.eb.UsuarioKey o =
				(co.com.telefonica.atiempo.ejb.eb.UsuarioKey) otherKey;
			return ((this.usua_id.equals(o.usua_id)));
		}
		return false;
	}
	/**
	 * Returns the hash code for the key.
	 */
	public int hashCode() {
		return (usua_id.hashCode());
	}
}
