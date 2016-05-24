package co.com.telefonica.atiempo.ejb.eb;
/**
 * Key class for Entity Bean: Mensaje_estado
 */
public class Mensaje_estadoKey implements java.io.Serializable {
	static final long serialVersionUID = 3206093459760846163L;
	/**
	 * Implementation field for persistent attribute: cod_estado
	 */
	public java.lang.Integer cod_estado;
	/**
	 * Creates an empty key for Entity Bean: Mensaje_estado
	 */
	public Mensaje_estadoKey() {
	}
	/**
	 * Creates a key for Entity Bean: Mensaje_estado
	 */
	public Mensaje_estadoKey(java.lang.Integer cod_estado) {
		this.cod_estado = cod_estado;
	}
	/**
	 * Returns true if both keys are equal.
	 */
	public boolean equals(java.lang.Object otherKey) {
		if (otherKey
			instanceof co.com.telefonica.atiempo.ejb.eb.Mensaje_estadoKey) {
			co.com.telefonica.atiempo.ejb.eb.Mensaje_estadoKey o =
				(co.com.telefonica.atiempo.ejb.eb.Mensaje_estadoKey) otherKey;
			return ((this.cod_estado.equals(o.cod_estado)));
		}
		return false;
	}
	/**
	 * Returns the hash code for the key.
	 */
	public int hashCode() {
		return (cod_estado.hashCode());
	}
}
