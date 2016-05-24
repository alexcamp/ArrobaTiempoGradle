package co.com.telefonica.atiempo.soltec.ejb.eb;
/**
 * Key class for Entity Bean: Peticion_flujo
 */
public class Peticion_flujoKey implements java.io.Serializable {
	static final long serialVersionUID = 3206093459760846163L;
	/**
	 * Implementation field for persistent attribute: pefl_id
	 */
	public java.lang.Integer pefl_id;
	/**
	 * Creates an empty key for Entity Bean: Peticion_flujo
	 */
	public Peticion_flujoKey() {
	}
	/**
	 * Returns true if both keys are equal.
	 */
	public boolean equals(java.lang.Object otherKey) {
		if (otherKey
			instanceof co.com.telefonica.atiempo.soltec.ejb.eb.Peticion_flujoKey) {
			co.com.telefonica.atiempo.soltec.ejb.eb.Peticion_flujoKey o =
				(co
					.com
					.telefonica
					.atiempo
					.soltec
					.ejb
					.eb
					.Peticion_flujoKey) otherKey;
			return ((this.pefl_id.equals(o.pefl_id)));
		}
		return false;
	}
	/**
	 * Returns the hash code for the key.
	 */
	public int hashCode() {
		return (pefl_id.hashCode());
	}
	/**
	 * Creates a key for Entity Bean: Peticion_flujo
	 */
	public Peticion_flujoKey(java.lang.Integer pefl_id) {
		this.pefl_id = pefl_id;
	}
}
