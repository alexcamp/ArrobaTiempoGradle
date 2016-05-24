package co.com.telefonica.atiempo.vpistbba.servicios;
/**
 * Key class for Entity Bean: PuntosCre
 */
public class PuntosCreKey implements java.io.Serializable {
	static final long serialVersionUID = 3206093459760846163L;
	/**
	 * Returns true if both keys are equal.
	 */
	public boolean equals(java.lang.Object otherKey) {
		if (otherKey instanceof co.com.telefonica.atiempo.vpistbba.servicios.PuntosCreKey) {
			co.com.telefonica.atiempo.vpistbba.servicios.PuntosCreKey o = (co.com.telefonica.atiempo.vpistbba.servicios.PuntosCreKey) otherKey;
			return (super.equals(otherKey));
		}
		return false;
	}
	/**
	 * Returns the hash code for the key.
	 */
	public int hashCode() {
		return (super.hashCode());
	}
	/**
	 * Creates a key for Entity Bean: PuntosCre
	 */
	public PuntosCreKey() {
	}
}