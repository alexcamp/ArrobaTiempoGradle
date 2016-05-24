package co.com.telefonica.atiempo.ejb.eb;
/**
 * Key class for Entity Bean: Cambio_producto_peticion_vuelo
 */
public class Cambio_producto_peticion_vueloKey implements java.io.Serializable {
	static final long serialVersionUID = 3206093459760846163L;
	/**
	 * Implementation field for persistent attribute: id
	 */
	public java.lang.Double id;
	/**
	 * Creates an empty key for Entity Bean: Cambio_producto_peticion_vuelo
	 */
	public Cambio_producto_peticion_vueloKey() {
	}
	/**
	 * Creates a key for Entity Bean: Cambio_producto_peticion_vuelo
	 */
	public Cambio_producto_peticion_vueloKey(java.lang.Double id) {
		this.id = id;
	}
	/**
	 * Returns true if both keys are equal.
	 */
	public boolean equals(java.lang.Object otherKey) {
		if (otherKey instanceof co.com.telefonica.atiempo.ejb.eb.Cambio_producto_peticion_vueloKey) {
			co.com.telefonica.atiempo.ejb.eb.Cambio_producto_peticion_vueloKey o = (co.com.telefonica.atiempo.ejb.eb.Cambio_producto_peticion_vueloKey) otherKey;
			return ((this.id.equals(o.id)));
		}
		return false;
	}
	/**
	 * Returns the hash code for the key.
	 */
	public int hashCode() {
		return (id.hashCode());
	}
}