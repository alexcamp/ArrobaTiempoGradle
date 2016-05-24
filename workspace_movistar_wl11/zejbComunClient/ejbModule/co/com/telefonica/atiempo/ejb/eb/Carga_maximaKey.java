package co.com.telefonica.atiempo.ejb.eb;
/**
 * Key class for Entity Bean: Carga_maxima
 */
public class Carga_maximaKey implements java.io.Serializable {
	static final long serialVersionUID = 3206093459760846163L;
	/**
	 * Implementation field for persistent attribute: cmax_id
	 */
	public java.lang.Long cmax_id;
	/**
	 * Creates an empty key for Entity Bean: Carga_maxima
	 */
	public Carga_maximaKey() {
	}
	/**
	 * Creates a key for Entity Bean: Carga_maxima
	 */
	public Carga_maximaKey(java.lang.Long cmax_id) {
		this.cmax_id = cmax_id;
	}
	/**
	 * Returns true if both keys are equal.
	 */
	public boolean equals(java.lang.Object otherKey) {
		if (otherKey
			instanceof co.com.telefonica.atiempo.ejb.eb.Carga_maximaKey) {
			co.com.telefonica.atiempo.ejb.eb.Carga_maximaKey o =
				(co.com.telefonica.atiempo.ejb.eb.Carga_maximaKey) otherKey;
			return ((this.cmax_id.equals(o.cmax_id)));
		}
		return false;
	}
	/**
	 * Returns the hash code for the key.
	 */
	public int hashCode() {
		return (cmax_id.hashCode());
	}
}
