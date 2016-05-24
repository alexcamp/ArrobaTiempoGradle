package co.com.telefonica.atiempo.ejb.eb;
/**
 * Key class for Entity Bean: Perfil
 */
public class PerfilKey implements java.io.Serializable {
	static final long serialVersionUID = 3206093459760846163L;
	/**
	 * Implementation field for persistent attribute: perf_id
	 */
	public java.lang.Long perf_id;
	/**
	 * Creates an empty key for Entity Bean: Perfil
	 */
	public PerfilKey() {
	}
	/**
	 * Creates a key for Entity Bean: Perfil
	 */
	public PerfilKey(java.lang.Long perf_id) {
		this.perf_id = perf_id;
	}
	/**
	 * Returns true if both keys are equal.
	 */
	public boolean equals(java.lang.Object otherKey) {
		if (otherKey instanceof co.com.telefonica.atiempo.ejb.eb.PerfilKey) {
			co.com.telefonica.atiempo.ejb.eb.PerfilKey o =
				(co.com.telefonica.atiempo.ejb.eb.PerfilKey) otherKey;
			return ((this.perf_id.equals(o.perf_id)));
		}
		return false;
	}
	/**
	 * Returns the hash code for the key.
	 */
	public int hashCode() {
		return (perf_id.hashCode());
	}
}
