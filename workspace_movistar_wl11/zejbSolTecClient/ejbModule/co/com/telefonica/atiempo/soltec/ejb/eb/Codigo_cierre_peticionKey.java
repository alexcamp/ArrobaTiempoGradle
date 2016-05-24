package co.com.telefonica.atiempo.soltec.ejb.eb;
/**
 * Key class for Entity Bean: Codigo_cierre_peticion
 */
public class Codigo_cierre_peticionKey implements java.io.Serializable {
	static final long serialVersionUID = 3206093459760846163L;
	/**
	 * Implementation field for persistent attribute: id_cierre
	 */
	public java.lang.Long id_cierre;
	/**
	 * Creates an empty key for Entity Bean: Codigo_cierre_peticion
	 */
	public Codigo_cierre_peticionKey() {
	}
	/**
	 * Creates a key for Entity Bean: Codigo_cierre_peticion
	 */
	public Codigo_cierre_peticionKey(java.lang.Long id_cierre) {
		this.id_cierre = id_cierre;
	}
	/**
	 * Returns true if both keys are equal.
	 */
	public boolean equals(java.lang.Object otherKey) {
		if (otherKey
			instanceof co
				.com
				.telefonica
				.atiempo
				.soltec
				.ejb
				.eb
				.Codigo_cierre_peticionKey) {
			co.com.telefonica.atiempo.soltec.ejb.eb.Codigo_cierre_peticionKey o =
				(co
					.com
					.telefonica
					.atiempo
					.soltec
					.ejb
					.eb
					.Codigo_cierre_peticionKey) otherKey;
			return ((this.id_cierre.equals(o.id_cierre)));
		}
		return false;
	}
	/**
	 * Returns the hash code for the key.
	 */
	public int hashCode() {
		return (id_cierre.hashCode());
	}
}
