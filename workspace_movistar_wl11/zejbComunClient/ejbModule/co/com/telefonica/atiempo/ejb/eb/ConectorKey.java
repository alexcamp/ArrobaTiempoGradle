package co.com.telefonica.atiempo.ejb.eb;
/**
 * Key class for Entity Bean: Conector
 */
public class ConectorKey implements java.io.Serializable {
	static final long serialVersionUID = 3206093459760846163L;
	/**
	 * Implementation field for persistent attribute: cod_conector
	 */
	public java.lang.Integer cod_conector;
	/**
	 * Creates an empty key for Entity Bean: Conector
	 */
	public ConectorKey() {
	}
	/**
	 * Creates a key for Entity Bean: Conector
	 */
	public ConectorKey(java.lang.Integer cod_conector) {
		this.cod_conector = cod_conector;
	}
	/**
	 * Returns true if both keys are equal.
	 */
	public boolean equals(java.lang.Object otherKey) {
		if (otherKey instanceof co.com.telefonica.atiempo.ejb.eb.ConectorKey) {
			co.com.telefonica.atiempo.ejb.eb.ConectorKey o =
				(co.com.telefonica.atiempo.ejb.eb.ConectorKey) otherKey;
			return ((this.cod_conector.equals(o.cod_conector)));
		}
		return false;
	}
	/**
	 * Returns the hash code for the key.
	 */
	public int hashCode() {
		return (cod_conector.hashCode());
	}
}
