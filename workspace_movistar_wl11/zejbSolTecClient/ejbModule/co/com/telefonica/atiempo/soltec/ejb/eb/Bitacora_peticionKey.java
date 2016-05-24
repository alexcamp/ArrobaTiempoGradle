package co.com.telefonica.atiempo.soltec.ejb.eb;
/**
 * Key class for Entity Bean: Bitacora_peticion
 */
public class Bitacora_peticionKey implements java.io.Serializable {
	static final long serialVersionUID = 3206093459760846163L;
	/**
	 * Implementation field for persistent attribute: bipe_id
	 */
	public java.lang.Long bipe_id;
	/**
	 * Creates an empty key for Entity Bean: Bitacora_peticion
	 */
	public Bitacora_peticionKey() {
	}
	/**
	 * Returns true if both keys are equal.
	 */
	public boolean equals(java.lang.Object otherKey) {
		if (otherKey
			instanceof co.com.telefonica.atiempo.soltec.ejb.eb.Bitacora_peticionKey) {
			co.com.telefonica.atiempo.soltec.ejb.eb.Bitacora_peticionKey o =
				(co
					.com
					.telefonica
					.atiempo
					.soltec
					.ejb
					.eb
					.Bitacora_peticionKey) otherKey;
			return ((this.bipe_id.equals(o.bipe_id)));
		}
		return false;
	}
	/**
	 * Returns the hash code for the key.
	 */
	public int hashCode() {
		return (bipe_id.hashCode());
	}
	/**
	 * Creates a key for Entity Bean: Bitacora_peticion
	 */
	public Bitacora_peticionKey(java.lang.Long bipe_id) {
		this.bipe_id = bipe_id;
	}
}
