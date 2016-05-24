package co.com.telefonica.atiempo.soltec.ejb.eb;
/**
 * Key class for Entity Bean: Regla
 */
public class ReglaKey implements java.io.Serializable {
	static final long serialVersionUID = 3206093459760846163L;
	/**
	 * Implementation field for persistent attribute: id_regla
	 */
	public java.lang.Long id_regla;
	/**
	 * Creates an empty key for Entity Bean: Regla
	 */
	public ReglaKey() {
	}
	/**
	 * Creates a key for Entity Bean: Regla
	 */
	public ReglaKey(java.lang.Long id_regla) {
		this.id_regla = id_regla;
	}
	/**
	 * Returns true if both keys are equal.
	 */
	public boolean equals(java.lang.Object otherKey) {
		if (otherKey
			instanceof co.com.telefonica.atiempo.soltec.ejb.eb.ReglaKey) {
			co.com.telefonica.atiempo.soltec.ejb.eb.ReglaKey o =
				(co.com.telefonica.atiempo.soltec.ejb.eb.ReglaKey) otherKey;
			return ((this.id_regla.equals(o.id_regla)));
		}
		return false;
	}
	/**
	 * Returns the hash code for the key.
	 */
	public int hashCode() {
		return (id_regla.hashCode());
	}
}
