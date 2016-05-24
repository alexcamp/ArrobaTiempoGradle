package co.com.telefonica.atiempo.ejb.eb;
/**
 * Key class for Entity Bean: Parametro
 */
public class ParametroKey implements java.io.Serializable {
	static final long serialVersionUID = 3206093459760846163L;
	/**
	 * Implementation field for persistent attribute: para_id
	 */
	public java.lang.Long para_id;
	/**
	 * Creates an empty key for Entity Bean: Parametro
	 */
	public ParametroKey() {
	}
	/**
	 * Creates a key for Entity Bean: Parametro
	 */
	public ParametroKey(java.lang.Long para_id) {
		this.para_id = para_id;
	}
	/**
	 * Returns true if both keys are equal.
	 */
	public boolean equals(java.lang.Object otherKey) {
		if (otherKey
			instanceof co.com.telefonica.atiempo.ejb.eb.ParametroKey) {
			co.com.telefonica.atiempo.ejb.eb.ParametroKey o =
				(co.com.telefonica.atiempo.ejb.eb.ParametroKey) otherKey;
			return ((this.para_id.equals(o.para_id)));
		}
		return false;
	}
	/**
	 * Returns the hash code for the key.
	 */
	public int hashCode() {
		return (para_id.hashCode());
	}
}
