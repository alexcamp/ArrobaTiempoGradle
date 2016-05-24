package co.com.telefonica.atiempo.ejb.eb;
/**
 * Key class for Entity Bean: Zonas_atendimiento
 */
public class Zonas_atendimientoKey implements java.io.Serializable {
	static final long serialVersionUID = 3206093459760846163L;
	/**
	 * Implementation field for persistent attribute: id_zona_atendimiento
	 */
	public java.lang.Long id_zona_atendimiento;
	/**
	 * Creates an empty key for Entity Bean: Zonas_atendimiento
	 */
	public Zonas_atendimientoKey() {
	}
	/**
	 * Returns true if both keys are equal.
	 */
	public boolean equals(java.lang.Object otherKey) {
		if (otherKey
			instanceof co.com.telefonica.atiempo.ejb.eb.Zonas_atendimientoKey) {
			co.com.telefonica.atiempo.ejb.eb.Zonas_atendimientoKey o =
				(co
					.com
					.telefonica
					.atiempo
					.ejb
					.eb
					.Zonas_atendimientoKey) otherKey;
			return ((this.id_zona_atendimiento.equals(o.id_zona_atendimiento)));
		}
		return false;
	}
	/**
	 * Returns the hash code for the key.
	 */
	public int hashCode() {
		return (id_zona_atendimiento.hashCode());
	}
	/**
	 * Creates a key for Entity Bean: Zonas_atendimiento
	 */
	public Zonas_atendimientoKey(java.lang.Long id_zona_atendimiento) {
		this.id_zona_atendimiento = id_zona_atendimiento;
	}
}
