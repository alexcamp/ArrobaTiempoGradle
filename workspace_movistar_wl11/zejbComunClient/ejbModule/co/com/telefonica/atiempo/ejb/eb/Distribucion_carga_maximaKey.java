package co.com.telefonica.atiempo.ejb.eb;
/**
 * Key class for Entity Bean: Distribucion_carga_maxima
 */
public class Distribucion_carga_maximaKey implements java.io.Serializable {
	static final long serialVersionUID = 3206093459760846163L;
	/**
	 * Implementation field for persistent attribute: dicm_id
	 */
	public java.lang.Long dicm_id;
	/**
	 * Creates an empty key for Entity Bean: Distribucion_carga_maxima
	 */
	public Distribucion_carga_maximaKey() {
	}
	/**
	 * Creates a key for Entity Bean: Distribucion_carga_maxima
	 */
	public Distribucion_carga_maximaKey(java.lang.Long dicm_id) {
		this.dicm_id = dicm_id;
	}
	/**
	 * Returns true if both keys are equal.
	 */
	public boolean equals(java.lang.Object otherKey) {
		if (otherKey
			instanceof co.com.telefonica.atiempo.ejb.eb.Distribucion_carga_maximaKey) {
			co.com.telefonica.atiempo.ejb.eb.Distribucion_carga_maximaKey o =
				(co
					.com
					.telefonica
					.atiempo
					.ejb
					.eb
					.Distribucion_carga_maximaKey) otherKey;
			return ((this.dicm_id.equals(o.dicm_id)));
		}
		return false;
	}
	/**
	 * Returns the hash code for the key.
	 */
	public int hashCode() {
		return (dicm_id.hashCode());
	}
}
