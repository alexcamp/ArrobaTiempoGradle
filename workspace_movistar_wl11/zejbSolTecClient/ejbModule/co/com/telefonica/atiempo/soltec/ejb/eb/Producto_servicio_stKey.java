package co.com.telefonica.atiempo.soltec.ejb.eb;
/**
 * Key class for Entity Bean: Producto_servicio_st
 */
public class Producto_servicio_stKey implements java.io.Serializable {
	static final long serialVersionUID = 3206093459760846163L;
	/**
	 * Implementation field for persistent attribute: ps_id
	 */
	public java.lang.Long ps_id;
	/**
	 * Creates an empty key for Entity Bean: Producto_servicio_st
	 */
	public Producto_servicio_stKey() {
	}
	/**
	 * Returns true if both keys are equal.
	 */
	public boolean equals(java.lang.Object otherKey) {
		if (otherKey
			instanceof co.com.telefonica.atiempo.soltec.ejb.eb.Producto_servicio_stKey) {
			co.com.telefonica.atiempo.soltec.ejb.eb.Producto_servicio_stKey o =
				(co
					.com
					.telefonica
					.atiempo
					.soltec
					.ejb
					.eb
					.Producto_servicio_stKey) otherKey;
			return ((this.ps_id.equals(o.ps_id)));
		}
		return false;
	}
	/**
	 * Returns the hash code for the key.
	 */
	public int hashCode() {
		return (ps_id.hashCode());
	}
	/**
	 * Creates a key for Entity Bean: Producto_servicio_st
	 */
	public Producto_servicio_stKey(java.lang.Long ps_id) {
		this.ps_id = ps_id;
	}
}
