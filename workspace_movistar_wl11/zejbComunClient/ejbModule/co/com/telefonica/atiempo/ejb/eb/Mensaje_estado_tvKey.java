package co.com.telefonica.atiempo.ejb.eb;
/**
 * Key class for Entity Bean: Mensaje_estado_tv
 */
public class Mensaje_estado_tvKey implements java.io.Serializable {
	static final long serialVersionUID = 3206093459760846163L;
	/**
	 * Implementation field for persistent attribute: correlativo
	 */
	public java.lang.Long correlativo;
	/**
	 * Creates an empty key for Entity Bean: Mensaje_estado_tv
	 */
	public Mensaje_estado_tvKey() {
	}
	/**
	 * Returns true if both keys are equal.
	 */
	public boolean equals(java.lang.Object otherKey) {
		if (otherKey
			instanceof co.com.telefonica.atiempo.ejb.eb.Mensaje_estado_tvKey) {
			co.com.telefonica.atiempo.ejb.eb.Mensaje_estado_tvKey o =
				(co
					.com
					.telefonica
					.atiempo
					.ejb
					.eb
					.Mensaje_estado_tvKey) otherKey;
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
	 * Creates a key for Entity Bean: Mensaje_estado_tv
	 */
	public Mensaje_estado_tvKey(java.lang.Long correlativo) {
		this.correlativo = correlativo;
	}
}
