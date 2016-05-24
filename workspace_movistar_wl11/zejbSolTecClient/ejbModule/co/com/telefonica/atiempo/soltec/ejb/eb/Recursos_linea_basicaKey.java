package co.com.telefonica.atiempo.soltec.ejb.eb;
/**
 * Key class for Entity Bean: Recursos_linea_basica
 */
public class Recursos_linea_basicaKey implements java.io.Serializable {
	static final long serialVersionUID = 3206093459760846163L;
	/**
	 * Implementation field for persistent attribute: id_conector
	 */
	public java.lang.Long id_conector;
	/**
	 * Creates an empty key for Entity Bean: Recursos_linea_basica
	 */
	public Recursos_linea_basicaKey() {
	}
	/**
	 * Returns true if both keys are equal.
	 */
	public boolean equals(java.lang.Object otherKey) {
		if (otherKey
			instanceof co.com.telefonica.atiempo.soltec.ejb.eb.Recursos_linea_basicaKey) {
			co.com.telefonica.atiempo.soltec.ejb.eb.Recursos_linea_basicaKey o =
				(co
					.com
					.telefonica
					.atiempo
					.soltec
					.ejb
					.eb
					.Recursos_linea_basicaKey) otherKey;
			return ((this.id_conector.equals(o.id_conector)));
		}
		return false;
	}
	/**
	 * Returns the hash code for the key.
	 */
	public int hashCode() {
		return (id_conector.hashCode());
	}
	/**
	 * Creates a key for Entity Bean: Recursos_linea_basica
	 */
	public Recursos_linea_basicaKey(java.lang.Long id_conector) {
		this.id_conector = id_conector;
	}
}
