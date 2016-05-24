package co.com.telefonica.atiempo.ejb.eb;
/**
 * Key class for Entity Bean: Respuesta_conec6_apsc_linea
 */
public class Respuesta_conec6_apsc_lineaKey implements java.io.Serializable {
	static final long serialVersionUID = 3206093459760846163L;
	/**
	 * Implementation field for persistent attribute: id_conector
	 */
	public java.lang.Long id_conector;
	/**
	 * Creates an empty key for Entity Bean: Respuesta_conec6_apsc_linea
	 */
	public Respuesta_conec6_apsc_lineaKey() {
	}
	/**
	 * Creates a key for Entity Bean: Respuesta_conec6_apsc_linea
	 */
	public Respuesta_conec6_apsc_lineaKey(java.lang.Long id_conector) {
		this.id_conector = id_conector;
	}
	/**
	 * Returns true if both keys are equal.
	 */
	public boolean equals(java.lang.Object otherKey) {
		if (otherKey
			instanceof co.com.telefonica.atiempo.ejb.eb.Respuesta_conec6_apsc_lineaKey) {
			co.com.telefonica.atiempo.ejb.eb.Respuesta_conec6_apsc_lineaKey o =
				(co
					.com
					.telefonica
					.atiempo
					.ejb
					.eb
					.Respuesta_conec6_apsc_lineaKey) otherKey;
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
}
