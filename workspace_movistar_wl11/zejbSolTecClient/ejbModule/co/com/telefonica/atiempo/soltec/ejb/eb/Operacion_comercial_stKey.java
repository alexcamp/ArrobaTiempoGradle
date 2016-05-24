package co.com.telefonica.atiempo.soltec.ejb.eb;
/**
 * Key class for Entity Bean: Operacion_comercial_st
 */
public class Operacion_comercial_stKey implements java.io.Serializable {
	static final long serialVersionUID = 3206093459760846163L;
	/**
	 * Implementation field for persistent attribute: opco_id
	 */
	public java.lang.Long opco_id;
	/**
	 * Creates an empty key for Entity Bean: Operacion_comercial_st
	 */
	public Operacion_comercial_stKey() {
	}
	/**
	 * Returns true if both keys are equal.
	 */
	public boolean equals(java.lang.Object otherKey) {
		if (otherKey
			instanceof co
				.com
				.telefonica
				.atiempo
				.soltec
				.ejb
				.eb
				.Operacion_comercial_stKey) {
			co.com.telefonica.atiempo.soltec.ejb.eb.Operacion_comercial_stKey o =
				(co
					.com
					.telefonica
					.atiempo
					.soltec
					.ejb
					.eb
					.Operacion_comercial_stKey) otherKey;
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
	/**
	 * Creates a key for Entity Bean: Operacion_comercial_st
	 */
	public Operacion_comercial_stKey(java.lang.Long opco_id) {
		this.opco_id = opco_id;
	}
}
