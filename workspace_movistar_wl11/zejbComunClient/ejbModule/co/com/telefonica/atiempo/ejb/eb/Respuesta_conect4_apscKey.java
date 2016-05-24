package co.com.telefonica.atiempo.ejb.eb;
/**
 * Key class for Entity Bean: Respuesta_conect4_apsc
 */
public class Respuesta_conect4_apscKey implements java.io.Serializable {
	static final long serialVersionUID = 3206093459760846163L;
	/**
	 * Implementation field for persistent attribute: id_conector
	 */
	public java.lang.Long id_conector;
	/**
	 * Creates an empty key for Entity Bean: Respuesta_conect4_apsc
	 */
	public Respuesta_conect4_apscKey() {
	}
	/**
	 * Creates a key for Entity Bean: Respuesta_conect4_apsc
	 */
	public Respuesta_conect4_apscKey(java.lang.Long id_conector) {
		this.id_conector = id_conector;
	}
	/**
	 * Returns true if both keys are equal.
	 */
	public boolean equals(java.lang.Object otherKey) {
		if (otherKey
			instanceof co.com.telefonica.atiempo.ejb.eb.Respuesta_conect4_apscKey) {
			co.com.telefonica.atiempo.ejb.eb.Respuesta_conect4_apscKey o =
				(co
					.com
					.telefonica
					.atiempo
					.ejb
					.eb
					.Respuesta_conect4_apscKey) otherKey;
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
