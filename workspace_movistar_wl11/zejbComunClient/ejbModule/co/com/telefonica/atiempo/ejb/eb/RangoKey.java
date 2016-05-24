package co.com.telefonica.atiempo.ejb.eb;
/**
 * Key class for Entity Bean: Rango
 */
public class RangoKey implements java.io.Serializable {
	static final long serialVersionUID = 3206093459760846163L;
	/**
	 * Implementation field for persistent attribute: id_rango
	 */
	public java.lang.Integer id_rango;
	/**
	 * Creates an empty key for Entity Bean: Rango
	 */
	public RangoKey() {
	}
	/**
	 * Creates a key for Entity Bean: Rango
	 */
	public RangoKey(java.lang.Integer id_rango) {
		this.id_rango = id_rango;
	}
	/**
	 * Returns true if both keys are equal.
	 */
	public boolean equals(java.lang.Object otherKey) {
		if (otherKey instanceof co.com.telefonica.atiempo.ejb.eb.RangoKey) {
			co.com.telefonica.atiempo.ejb.eb.RangoKey o =
				(co.com.telefonica.atiempo.ejb.eb.RangoKey) otherKey;
			return ((this.id_rango.equals(o.id_rango)));
		}
		return false;
	}
	/**
	 * Returns the hash code for the key.
	 */
	public int hashCode() {
		return (id_rango.hashCode());
	}
}
