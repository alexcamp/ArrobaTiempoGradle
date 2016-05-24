package co.com.telefonica.atiempo.ejb.eb;
/**
 * Key class for Entity Bean: Operacion_comercial
 */
public class Operacion_comercialKey implements java.io.Serializable {
	static final long serialVersionUID = 3206093459760846163L;
	/**
	 * Implementation field for persistent attribute: opco_id
	 */
	public java.lang.Long opco_id;
	/**
	 * Creates an empty key for Entity Bean: Operacion_comercial
	 */
	public Operacion_comercialKey() {
	}
	/**
	 * Creates a key for Entity Bean: Operacion_comercial
	 */
	public Operacion_comercialKey(java.lang.Long opco_id) {
		this.opco_id = opco_id;
	}
	/**
	 * Returns true if both keys are equal.
	 */
	public boolean equals(java.lang.Object otherKey) {
		if (otherKey
			instanceof co.com.telefonica.atiempo.ejb.eb.Operacion_comercialKey) {
			co.com.telefonica.atiempo.ejb.eb.Operacion_comercialKey o =
				(co
					.com
					.telefonica
					.atiempo
					.ejb
					.eb
					.Operacion_comercialKey) otherKey;
			return ((this.opco_id.equals(o.opco_id)));
		}
		return false;
	}
	/**
	 * Returns the hash code for the key.
	 */
	public int hashCode() {
		return (opco_id.hashCode());
	}
}
