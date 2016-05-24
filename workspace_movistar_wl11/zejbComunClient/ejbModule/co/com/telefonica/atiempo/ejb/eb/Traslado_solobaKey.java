package co.com.telefonica.atiempo.ejb.eb;
/**
 * Key class for Entity Bean: Traslado_soloba
 */
public class Traslado_solobaKey implements java.io.Serializable {
	static final long serialVersionUID = 3206093459760846163L;
	/**
	 * Implementation field for persistent attribute: id_trasoba
	 */
	public java.lang.Long id_trasoba;
	/**
	 * Creates an empty key for Entity Bean: Traslado_soloba
	 */
	public Traslado_solobaKey() {
	}
	/**
	 * Returns true if both keys are equal.
	 */
	public boolean equals(java.lang.Object otherKey) {
		if (otherKey
			instanceof co.com.telefonica.atiempo.ejb.eb.Traslado_solobaKey) {
			co.com.telefonica.atiempo.ejb.eb.Traslado_solobaKey o =
				(co.com.telefonica.atiempo.ejb.eb.Traslado_solobaKey) otherKey;
			return ((this.id_trasoba.equals(o.id_trasoba)));
		}
		return false;
	}
	/**
	 * Returns the hash code for the key.
	 */
	public int hashCode() {
		return (id_trasoba.hashCode());
	}
	/**
	 * Creates a key for Entity Bean: Traslado_soloba
	 */
	public Traslado_solobaKey(java.lang.Long id_trasoba) {
		this.id_trasoba = id_trasoba;
	}
}
