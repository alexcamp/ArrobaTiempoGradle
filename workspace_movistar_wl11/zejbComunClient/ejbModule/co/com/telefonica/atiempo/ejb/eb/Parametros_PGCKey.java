package co.com.telefonica.atiempo.ejb.eb;
/**
 * Key class for Entity Bean: Parametros_PGC
 */
public class Parametros_PGCKey implements java.io.Serializable {
	static final long serialVersionUID = 3206093459760846163L;
	/**
	 * Implementation field for persistent attribute: correlativo
	 */
	public java.lang.Long correlativo;
	/**
	 * Returns true if both keys are equal.
	 */
	public boolean equals(java.lang.Object otherKey) {
		if (otherKey instanceof co.com.telefonica.atiempo.ejb.eb.Parametros_PGCKey) {
			co.com.telefonica.atiempo.ejb.eb.Parametros_PGCKey o = (co.com.telefonica.atiempo.ejb.eb.Parametros_PGCKey) otherKey;
			return ((this.correlativo.equals(o.correlativo)));
		}
		return false;
	}
	/**
	 * Returns the hash code for the key.
	 */
	public int hashCode() {
		return (correlativo.hashCode());
	}
	/**
	 * Creates an empty key for Entity Bean: Parametros_PGC
	 */
	public Parametros_PGCKey() {
	}
	/**
	 * Creates a key for Entity Bean: Parametros_PGC
	 */
	public Parametros_PGCKey(java.lang.Long correlativo) {
		this.correlativo = correlativo;
	}
}