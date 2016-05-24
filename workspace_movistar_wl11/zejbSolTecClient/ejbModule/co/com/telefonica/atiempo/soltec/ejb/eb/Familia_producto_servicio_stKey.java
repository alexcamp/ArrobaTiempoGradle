package co.com.telefonica.atiempo.soltec.ejb.eb;
/**
 * Key class for Entity Bean: Familia_producto_servicio_st
 */
public class Familia_producto_servicio_stKey implements java.io.Serializable {
	static final long serialVersionUID = 3206093459760846163L;
	/**
	 * Implementation field for persistent attribute: faps_id
	 */
	public java.lang.Long faps_id;
	/**
	 * Creates an empty key for Entity Bean: Familia_producto_servicio_st
	 */
	public Familia_producto_servicio_stKey() {
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
				.Familia_producto_servicio_stKey) {
			co
				.com
				.telefonica
				.atiempo
				.soltec
				.ejb
				.eb
				.Familia_producto_servicio_stKey o =
				(co
					.com
					.telefonica
					.atiempo
					.soltec
					.ejb
					.eb
					.Familia_producto_servicio_stKey) otherKey;
			return ((this.faps_id.equals(o.faps_id)));
		}
		return false;
	}
	/**
	 * Returns the hash code for the key.
	 */
	public int hashCode() {
		return (faps_id.hashCode());
	}
	/**
	 * Creates a key for Entity Bean: Familia_producto_servicio_st
	 */
	public Familia_producto_servicio_stKey(java.lang.Long faps_id) {
		this.faps_id = faps_id;
	}
}
