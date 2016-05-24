package co.com.telefonica.atiempo.ejb.eb;
/**
 * Key class for Entity Bean: Habilidad_usuario
 */
public class Habilidad_usuarioKey implements java.io.Serializable {
	static final long serialVersionUID = 3206093459760846163L;
	/**
	 * Implementation field for persistent attribute: husu_id
	 */
	public java.lang.Long husu_id;
	/**
	 * Creates an empty key for Entity Bean: Habilidad_usuario
	 */
	public Habilidad_usuarioKey() {
	}
	/**
	 * Creates a key for Entity Bean: Habilidad_usuario
	 */
	public Habilidad_usuarioKey(java.lang.Long husu_id) {
		this.husu_id = husu_id;
	}
	/**
	 * Returns true if both keys are equal.
	 */
	public boolean equals(java.lang.Object otherKey) {
		if (otherKey
			instanceof co.com.telefonica.atiempo.ejb.eb.Habilidad_usuarioKey) {
			co.com.telefonica.atiempo.ejb.eb.Habilidad_usuarioKey o =
				(co
					.com
					.telefonica
					.atiempo
					.ejb
					.eb
					.Habilidad_usuarioKey) otherKey;
			return ((this.husu_id.equals(o.husu_id)));
		}
		return false;
	}
	/**
	 * Returns the hash code for the key.
	 */
	public int hashCode() {
		return (husu_id.hashCode());
	}
}
