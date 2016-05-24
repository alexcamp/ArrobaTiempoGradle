package co.com.telefonica.atiempo.ejb.eb;
/**
 * Key class for Entity Bean: Excepcion_tipo_agendamiento
 */
public class Excepcion_tipo_agendamientoKey implements java.io.Serializable {
	static final long serialVersionUID = 3206093459760846163L;
	/**
	 * Implementation field for persistent attribute: exta_id
	 */
	public java.lang.Long exta_id;
	/**
	 * Creates an empty key for Entity Bean: Excepcion_tipo_agendamiento
	 */
	public Excepcion_tipo_agendamientoKey() {
	}
	/**
	 * Creates a key for Entity Bean: Excepcion_tipo_agendamiento
	 */
	public Excepcion_tipo_agendamientoKey(java.lang.Long exta_id) {
		this.exta_id = exta_id;
	}
	/**
	 * Returns true if both keys are equal.
	 */
	public boolean equals(java.lang.Object otherKey) {
		if (otherKey
			instanceof co.com.telefonica.atiempo.ejb.eb.Excepcion_tipo_agendamientoKey) {
			co.com.telefonica.atiempo.ejb.eb.Excepcion_tipo_agendamientoKey o =
				(co
					.com
					.telefonica
					.atiempo
					.ejb
					.eb
					.Excepcion_tipo_agendamientoKey) otherKey;
			return ((this.exta_id.equals(o.exta_id)));
		}
		return false;
	}
	/**
	 * Returns the hash code for the key.
	 */
	public int hashCode() {
		return (exta_id.hashCode());
	}
}
