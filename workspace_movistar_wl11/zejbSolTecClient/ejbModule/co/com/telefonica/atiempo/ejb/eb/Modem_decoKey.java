package co.com.telefonica.atiempo.ejb.eb;
/**
 * Key class for Entity Bean: Modem_deco
 */
public class Modem_decoKey implements java.io.Serializable {
	static final long serialVersionUID = 3206093459760846163L;
	/**
	 * Implementation field for persistent attribute: correlativo
	 */
	public java.lang.Long correlativo;
	/**
	 * Creates an empty key for Entity Bean: Modem_deco
	 */
	public Modem_decoKey() {
	}
	/**
	 * Returns true if both keys are equal.
	 */
	public boolean equals(java.lang.Object otherKey) {
		if (otherKey
			instanceof co.com.telefonica.atiempo.ejb.eb.Modem_decoKey) {
			co.com.telefonica.atiempo.ejb.eb.Modem_decoKey o =
				(co.com.telefonica.atiempo.ejb.eb.Modem_decoKey) otherKey;
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
	 * Creates a key for Entity Bean: Modem_deco
	 */
	public Modem_decoKey(java.lang.Long correlativo) {
		this.correlativo = correlativo;
	}
}
