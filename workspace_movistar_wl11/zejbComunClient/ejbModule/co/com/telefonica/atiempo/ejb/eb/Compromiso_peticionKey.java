package co.com.telefonica.atiempo.ejb.eb;
/**
 * Key class for Entity Bean: Compromiso_peticion
 */
public class Compromiso_peticionKey implements java.io.Serializable {
	static final long serialVersionUID = 3206093459760846163L;
	/**
	 * Implementation field for persistent attribute: compr_id
	 */
	public java.lang.Long compr_id;
	/**
	 * Creates an empty key for Entity Bean: Compromiso_peticion
	 */
	public Compromiso_peticionKey() {
	}
	/**
	 * Creates a key for Entity Bean: Compromiso_peticion
	 */
	public Compromiso_peticionKey(java.lang.Long compr_id) {
		this.compr_id = compr_id;
	}
	/**
	 * Returns true if both keys are equal.
	 */
	public boolean equals(java.lang.Object otherKey) {
		if (otherKey
			instanceof co.com.telefonica.atiempo.ejb.eb.Compromiso_peticionKey) {
			co.com.telefonica.atiempo.ejb.eb.Compromiso_peticionKey o =
				(co
					.com
					.telefonica
					.atiempo
					.ejb
					.eb
					.Compromiso_peticionKey) otherKey;
			return ((this.compr_id.equals(o.compr_id)));
		}
		return false;
	}
	/**
	 * Returns the hash code for the key.
	 */
	public int hashCode() {
		return (compr_id.hashCode());
	}
}
