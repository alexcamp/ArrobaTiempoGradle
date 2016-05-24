package co.com.telefonica.atiempo.soltec.ejb.eb;
/**
 * Key class for Entity Bean: Tmp_deco_tarjeta
 */
public class Tmp_deco_tarjetaKey implements java.io.Serializable {
	static final long serialVersionUID = 3206093459760846163L;
	/**
	 * Implementation field for persistent attribute: id
	 */
	public java.lang.Long id;
	/**
	 * Creates an empty key for Entity Bean: Tmp_deco_tarjeta
	 */
	public Tmp_deco_tarjetaKey() {
	}
	/**
	 * Returns true if both keys are equal.
	 */
	public boolean equals(java.lang.Object otherKey) {
		if (otherKey
			instanceof co.com.telefonica.atiempo.soltec.ejb.eb.Tmp_deco_tarjetaKey) {
			co.com.telefonica.atiempo.soltec.ejb.eb.Tmp_deco_tarjetaKey o =
				(co
					.com
					.telefonica
					.atiempo
					.soltec
					.ejb
					.eb
					.Tmp_deco_tarjetaKey) otherKey;
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
	 * Creates a key for Entity Bean: Tmp_deco_tarjeta
	 */
	public Tmp_deco_tarjetaKey(java.lang.Long id) {
		this.id = id;
	}
}
