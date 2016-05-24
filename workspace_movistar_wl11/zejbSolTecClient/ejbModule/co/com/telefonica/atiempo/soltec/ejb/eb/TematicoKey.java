package co.com.telefonica.atiempo.soltec.ejb.eb;
/**
 * Key class for Entity Bean: Tematico
 */
public class TematicoKey implements java.io.Serializable {
	static final long serialVersionUID = 3206093459760846163L;
	/**
	 * Implementation field for persistent attribute: id_tematico
	 */
	public java.lang.Long id_tematico;
	/**
	 * Creates an empty key for Entity Bean: Tematico
	 */
	public TematicoKey() {
	}
	/**
	 * Returns true if both keys are equal.
	 */
	public boolean equals(java.lang.Object otherKey) {
		if (otherKey
			instanceof co.com.telefonica.atiempo.soltec.ejb.eb.TematicoKey) {
			co.com.telefonica.atiempo.soltec.ejb.eb.TematicoKey o =
				(co.com.telefonica.atiempo.soltec.ejb.eb.TematicoKey) otherKey;
			return ((this.id_tematico.equals(o.id_tematico)));
		}
		return false;
	}
	/**
	 * Returns the hash code for the key.
	 */
	public int hashCode() {
		return (id_tematico.hashCode());
	}
	/**
	 * Creates a key for Entity Bean: Tematico
	 */
	public TematicoKey(java.lang.Long id_tematico) {
		this.id_tematico = id_tematico;
	}
}
