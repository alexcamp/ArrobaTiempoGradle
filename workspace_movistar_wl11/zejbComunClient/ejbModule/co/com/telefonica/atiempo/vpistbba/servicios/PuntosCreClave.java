package co.com.telefonica.atiempo.vpistbba.servicios;
/**
 * Key class for Entity Bean: PuntosCre
 */
public class PuntosCreClave implements java.io.Serializable {
	static final long serialVersionUID = 3206093459760846163L;
	/**
	 * Implementation field for persistent attribute: id
	 */
	public java.lang.Long id;
	/**
	 * Creates an empty key for Entity Bean: PuntosCre
	 */
	public PuntosCreClave() {
	}
	/**
	 * Creates a key for Entity Bean: PuntosCre
	 */
	public PuntosCreClave(java.lang.Long id) {
		this.id = id;
	}
	/**
	 * Returns true if both keys are equal.
	 */
	public boolean equals(java.lang.Object otherKey) {
		if (otherKey instanceof co.com.telefonica.atiempo.vpistbba.servicios.PuntosCreClave) {
			co.com.telefonica.atiempo.vpistbba.servicios.PuntosCreClave o = (co.com.telefonica.atiempo.vpistbba.servicios.PuntosCreClave) otherKey;
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