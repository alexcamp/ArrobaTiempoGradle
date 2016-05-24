package co.com.telefonica.atiempo.ejb.eb;
/**
 * Key class for Entity Bean: Rol_habilidad
 */
public class Rol_habilidadKey implements java.io.Serializable {
	static final long serialVersionUID = 3206093459760846163L;
	/**
	 * Implementation field for persistent attribute: roha_id
	 */
	public java.lang.Long roha_id;
	/**
	 * Creates an empty key for Entity Bean: Rol_habilidad
	 */
	public Rol_habilidadKey() {
	}
	/**
	 * Creates a key for Entity Bean: Rol_habilidad
	 */
	public Rol_habilidadKey(java.lang.Long roha_id) {
		this.roha_id = roha_id;
	}
	/**
	 * Returns true if both keys are equal.
	 */
	public boolean equals(java.lang.Object otherKey) {
		if (otherKey
			instanceof co.com.telefonica.atiempo.ejb.eb.Rol_habilidadKey) {
			co.com.telefonica.atiempo.ejb.eb.Rol_habilidadKey o =
				(co.com.telefonica.atiempo.ejb.eb.Rol_habilidadKey) otherKey;
			return ((this.roha_id.equals(o.roha_id)));
		}
		return false;
	}
	/**
	 * Returns the hash code for the key.
	 */
	public int hashCode() {
		return (roha_id.hashCode());
	}
}
