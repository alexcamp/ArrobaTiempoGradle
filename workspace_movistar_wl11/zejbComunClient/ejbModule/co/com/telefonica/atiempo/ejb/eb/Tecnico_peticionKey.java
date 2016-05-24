package co.com.telefonica.atiempo.ejb.eb;
/**
 * Key class for Entity Bean: Tecnico_peticion
 */
public class Tecnico_peticionKey implements java.io.Serializable {
	static final long serialVersionUID = 3206093459760846163L;
	/**
	 * Implementation field for persistent attribute: tepe_id
	 */
	public java.lang.Long tepe_id;
	/**
	 * Creates an empty key for Entity Bean: Tecnico_peticion
	 */
	public Tecnico_peticionKey() {
	}
	/**
	 * Returns true if both keys are equal.
	 */
	public boolean equals(java.lang.Object otherKey) {
		if (otherKey
			instanceof co.com.telefonica.atiempo.ejb.eb.Tecnico_peticionKey) {
			co.com.telefonica.atiempo.ejb.eb.Tecnico_peticionKey o =
				(co.com.telefonica.atiempo.ejb.eb.Tecnico_peticionKey) otherKey;
			return ((this.tepe_id.equals(o.tepe_id)));
		}
		return false;
	}
	/**
	 * Returns the hash code for the key.
	 */
	public int hashCode() {
		return (tepe_id.hashCode());
	}
	/**
	 * Creates a key for Entity Bean: Tecnico_peticion
	 */
	public Tecnico_peticionKey(java.lang.Long tepe_id) {
		this.tepe_id = tepe_id;
	}
}
