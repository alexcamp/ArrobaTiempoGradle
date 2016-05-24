package co.com.telefonica.atiempo.ejb.eb;
/**
 * Key class for Entity Bean: Causal_peticion
 */
public class Causal_peticionKey implements java.io.Serializable {
	static final long serialVersionUID = 3206093459760846163L;
	/**
	 * Implementation field for persistent attribute: id_causal_peticion
	 */
	public java.lang.Long id_causal_peticion;
	/**
	 * Creates an empty key for Entity Bean: Causal_peticion
	 */
	public Causal_peticionKey() {
	}
	/**
	 * Returns true if both keys are equal.
	 */
	public boolean equals(java.lang.Object otherKey) {
		if (otherKey
			instanceof co.com.telefonica.atiempo.ejb.eb.Causal_peticionKey) {
			co.com.telefonica.atiempo.ejb.eb.Causal_peticionKey o =
				(co.com.telefonica.atiempo.ejb.eb.Causal_peticionKey) otherKey;
			return ((this.id_causal_peticion.equals(o.id_causal_peticion)));
		}
		return false;
	}
	/**
	 * Returns the hash code for the key.
	 */
	public int hashCode() {
		return (id_causal_peticion.hashCode());
	}
	/**
	 * Creates a key for Entity Bean: Causal_peticion
	 */
	public Causal_peticionKey(java.lang.Long id_causal_peticion) {
		this.id_causal_peticion = id_causal_peticion;
	}
}
