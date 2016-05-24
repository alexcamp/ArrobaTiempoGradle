package co.com.telefonica.atiempo.soltec.ejb.eb;
/**
 * Key class for Entity Bean: Tipo_codigo
 */
public class Tipo_codigoKey implements java.io.Serializable {
	static final long serialVersionUID = 3206093459760846163L;
	/**
	 * Implementation field for persistent attribute: tipo
	 */
	public java.lang.Integer tipo;
	/**
	 * Creates an empty key for Entity Bean: Tipo_codigo
	 */
	public Tipo_codigoKey() {
	}
	/**
	 * Returns true if both keys are equal.
	 */
	public boolean equals(java.lang.Object otherKey) {
		if (otherKey
			instanceof co.com.telefonica.atiempo.soltec.ejb.eb.Tipo_codigoKey) {
			co.com.telefonica.atiempo.soltec.ejb.eb.Tipo_codigoKey o =
				(co
					.com
					.telefonica
					.atiempo
					.soltec
					.ejb
					.eb
					.Tipo_codigoKey) otherKey;
			return ((this.tipo.equals(o.tipo)));
		}
		return false;
	}
	/**
	 * Returns the hash code for the key.
	 */
	public int hashCode() {
		return (tipo.hashCode());
	}
	/**
	 * Creates a key for Entity Bean: Tipo_codigo
	 */
	public Tipo_codigoKey(java.lang.Integer tipo) {
		this.tipo = tipo;
	}
}
