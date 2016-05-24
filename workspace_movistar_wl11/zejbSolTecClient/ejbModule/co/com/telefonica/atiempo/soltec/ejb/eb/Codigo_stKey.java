package co.com.telefonica.atiempo.soltec.ejb.eb;
/**
 * Key class for Entity Bean: Codigo_st
 */
public class Codigo_stKey implements java.io.Serializable {
	static final long serialVersionUID = 3206093459760846163L;
	/**
	 * Implementation field for persistent attribute: correlativo
	 */
	public java.lang.Long correlativo;
	/**
	 * Creates an empty key for Entity Bean: Codigo_st
	 */
	public Codigo_stKey() {
	}
	/**
	 * Returns true if both keys are equal.
	 */
	public boolean equals(java.lang.Object otherKey) {
		if (otherKey
			instanceof co.com.telefonica.atiempo.soltec.ejb.eb.Codigo_stKey) {
			co.com.telefonica.atiempo.soltec.ejb.eb.Codigo_stKey o =
				(co.com.telefonica.atiempo.soltec.ejb.eb.Codigo_stKey) otherKey;
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
	 * Creates a key for Entity Bean: Codigo_st
	 */
	public Codigo_stKey(java.lang.Long correlativo) {
		this.correlativo = correlativo;
	}
}
