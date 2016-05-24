package co.com.telefonica.atiempo.soltec.ejb.eb;
/**
 * Key class for Entity Bean: Localizacion
 */
public class LocalizacionKey implements java.io.Serializable {
	static final long serialVersionUID = 3206093459760846163L;
	/**
	 * Implementation field for persistent attribute: cod_loc
	 */
	public java.lang.Integer cod_loc;
	/**
	 * Creates an empty key for Entity Bean: Localizacion
	 */
	public LocalizacionKey() {
	}
	/**
	 * Creates a key for Entity Bean: Localizacion
	 */
	public LocalizacionKey(java.lang.Integer cod_loc) {
		this.cod_loc = cod_loc;
	}
	/**
	 * Returns true if both keys are equal.
	 */
	public boolean equals(java.lang.Object otherKey) {
		if (otherKey
			instanceof co.com.telefonica.atiempo.soltec.ejb.eb.LocalizacionKey) {
			co.com.telefonica.atiempo.soltec.ejb.eb.LocalizacionKey o =
				(co
					.com
					.telefonica
					.atiempo
					.soltec
					.ejb
					.eb
					.LocalizacionKey) otherKey;
			return ((this.cod_loc.equals(o.cod_loc)));
		}
		return false;
	}
	/**
	 * Returns the hash code for the key.
	 */
	public int hashCode() {
		return (cod_loc.hashCode());
	}
}
