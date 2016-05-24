package co.com.telefonica.atiempo.soltec.ejb.eb;
/**
 * Key class for Entity Bean: Tipo_peticion_st
 */
public class Tipo_peticion_stKey implements java.io.Serializable {
	static final long serialVersionUID = 3206093459760846163L;
	/**
	 * Implementation field for persistent attribute: id
	 */
	public java.lang.String id;
	/**
	 * Creates an empty key for Entity Bean: Tipo_peticion_st
	 */
	public Tipo_peticion_stKey() {
	}
	/**
	 * Returns true if both keys are equal.
	 */
	public boolean equals(java.lang.Object otherKey) {
		if (otherKey
			instanceof co.com.telefonica.atiempo.soltec.ejb.eb.Tipo_peticion_stKey) {
			co.com.telefonica.atiempo.soltec.ejb.eb.Tipo_peticion_stKey o =
				(co
					.com
					.telefonica
					.atiempo
					.soltec
					.ejb
					.eb
					.Tipo_peticion_stKey) otherKey;
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
	 * Creates a key for Entity Bean: Tipo_peticion_st
	 */
	public Tipo_peticion_stKey(java.lang.String id) {
		this.id = id;
	}
}
