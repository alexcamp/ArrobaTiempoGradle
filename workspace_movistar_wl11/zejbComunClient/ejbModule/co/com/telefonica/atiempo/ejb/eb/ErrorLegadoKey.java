package co.com.telefonica.atiempo.ejb.eb;
/**
 * Key class for Entity Bean: ErrorLegado
 */
public class ErrorLegadoKey implements java.io.Serializable {
	static final long serialVersionUID = 3206093459760846163L;
	/**
	 * Implementation field for persistent attribute: id
	 */
	public java.lang.Long id;
	/**
	 * Creates an empty key for Entity Bean: ErrorLegado
	 */
	public ErrorLegadoKey() {
	}
	/**
	 * Returns true if both keys are equal.
	 */
	public boolean equals(java.lang.Object otherKey) {
		if (otherKey
			instanceof co.com.telefonica.atiempo.ejb.eb.ErrorLegadoKey) {
			co.com.telefonica.atiempo.ejb.eb.ErrorLegadoKey o =
				(co.com.telefonica.atiempo.ejb.eb.ErrorLegadoKey) otherKey;
			return ((this.id.equals(o.id)));
		}
		return false;
	}
	/**
	 * Returns the hash code for the key.
	 */
	public int hashCode() {
		return (id.hashCode());
	}
	/**
	 * Creates a key for Entity Bean: ErrorLegado
	 */
	public ErrorLegadoKey(java.lang.Long id) {
		this.id = id;
	}
}
