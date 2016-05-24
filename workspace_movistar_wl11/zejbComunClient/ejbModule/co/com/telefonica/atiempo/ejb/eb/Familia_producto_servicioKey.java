package co.com.telefonica.atiempo.ejb.eb;
/**
 * Key class for Entity Bean: Familia_producto_servicio
 */
public class Familia_producto_servicioKey implements java.io.Serializable {
	static final long serialVersionUID = 3206093459760846163L;
	/**
	 * Implementation field for persistent attribute: faps_id
	 */
	public java.lang.Long faps_id;
	/**
	 * Creates an empty key for Entity Bean: Familia_producto_servicio
	 */
	public Familia_producto_servicioKey() {
	}
	/**
	 * Creates a key for Entity Bean: Familia_producto_servicio
	 */
	public Familia_producto_servicioKey(java.lang.Long faps_id) {
		this.faps_id = faps_id;
	}
	/**
	 * Returns true if both keys are equal.
	 */
	public boolean equals(java.lang.Object otherKey) {
		if (otherKey
			instanceof co.com.telefonica.atiempo.ejb.eb.Familia_producto_servicioKey) {
			co.com.telefonica.atiempo.ejb.eb.Familia_producto_servicioKey o =
				(co
					.com
					.telefonica
					.atiempo
					.ejb
					.eb
					.Familia_producto_servicioKey) otherKey;
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
}
