package co.com.telefonica.atiempo.ejb.eb;
/**
 * Key class for Entity Bean: Habilidad
 */
public class HabilidadKey implements java.io.Serializable {
	static final long serialVersionUID = 3206093459760846163L;
	/**
	 * Implementation field for persistent attribute: habi_id
	 */
	public java.lang.Long habi_id;
	/**
	 * Creates an empty key for Entity Bean: Habilidad
	 */
	public HabilidadKey() {
	}
	/**
	 * Creates a key for Entity Bean: Habilidad
	 */
	public HabilidadKey(java.lang.Long habi_id) {
		this.habi_id = habi_id;
	}
	/**
	 * Returns true if both keys are equal.
	 */
	public boolean equals(java.lang.Object otherKey) {
		if (otherKey
			instanceof co.com.telefonica.atiempo.ejb.eb.HabilidadKey) {
			co.com.telefonica.atiempo.ejb.eb.HabilidadKey o =
				(co.com.telefonica.atiempo.ejb.eb.HabilidadKey) otherKey;
			return ((this.habi_id.equals(o.habi_id)));
		}
		return false;
	}
	/**
	 * Returns the hash code for the key.
	 */
	public int hashCode() {
		return (habi_id.hashCode());
	}
}
