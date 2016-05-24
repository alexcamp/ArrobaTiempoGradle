package co.com.telefonica.atiempo.ejb.eb;
/**
 * Key class for Entity Bean: Carga_maxima_usuario
 */
public class Carga_maxima_usuarioKey implements java.io.Serializable {
	static final long serialVersionUID = 3206093459760846163L;
	/**
	 * Implementation field for persistent attribute: cmu_id
	 */
	public java.lang.Long cmu_id;
	/**
	 * Creates an empty key for Entity Bean: Carga_maxima_usuario
	 */
	public Carga_maxima_usuarioKey() {
	}
	/**
	 * Creates a key for Entity Bean: Carga_maxima_usuario
	 */
	public Carga_maxima_usuarioKey(java.lang.Long cmu_id) {
		this.cmu_id = cmu_id;
	}
	/**
	 * Returns true if both keys are equal.
	 */
	public boolean equals(java.lang.Object otherKey) {
		if (otherKey
			instanceof co.com.telefonica.atiempo.ejb.eb.Carga_maxima_usuarioKey) {
			co.com.telefonica.atiempo.ejb.eb.Carga_maxima_usuarioKey o =
				(co
					.com
					.telefonica
					.atiempo
					.ejb
					.eb
					.Carga_maxima_usuarioKey) otherKey;
			return ((this.cmu_id.equals(o.cmu_id)));
		}
		return false;
	}
	/**
	 * Returns the hash code for the key.
	 */
	public int hashCode() {
		return (cmu_id.hashCode());
	}
}
