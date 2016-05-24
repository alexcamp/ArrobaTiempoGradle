package co.com.telefonica.atiempo.ejb.eb;
/**
 * Key class for Entity Bean: Estado_ps_peticion
 */
public class Estado_ps_peticionKey implements java.io.Serializable {
	static final long serialVersionUID = 3206093459760846163L;
	/**
	 * Implementation field for persistent attribute: correlativo
	 */
	public java.lang.Long correlativo;
	/**
	 * Creates an empty key for Entity Bean: Estado_ps_peticion
	 */
	public Estado_ps_peticionKey() {
	}
	/**
	 * Returns true if both keys are equal.
	 */
	public boolean equals(java.lang.Object otherKey) {
		if (otherKey
			instanceof co.com.telefonica.atiempo.ejb.eb.Estado_ps_peticionKey) {
			co.com.telefonica.atiempo.ejb.eb.Estado_ps_peticionKey o =
				(co
					.com
					.telefonica
					.atiempo
					.ejb
					.eb
					.Estado_ps_peticionKey) otherKey;
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
	 * Creates a key for Entity Bean: Estado_ps_peticion
	 */
	public Estado_ps_peticionKey(java.lang.Long correlativo) {
		this.correlativo = correlativo;
	}
}
