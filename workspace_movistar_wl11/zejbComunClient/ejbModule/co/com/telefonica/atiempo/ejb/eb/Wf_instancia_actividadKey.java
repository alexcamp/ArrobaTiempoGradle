package co.com.telefonica.atiempo.ejb.eb;
/**
 * Key class for Entity Bean: Wf_instancia_actividad
 */
public class Wf_instancia_actividadKey implements java.io.Serializable {
	static final long serialVersionUID = 3206093459760846163L;
	/**
	 * Implementation field for persistent attribute: id_instancia_actividad
	 */
	public java.lang.String id_instancia_actividad;
	/**
	 * Creates an empty key for Entity Bean: Wf_instancia_actividad
	 */
	public Wf_instancia_actividadKey() {
	}
	/**
	 * Creates a key for Entity Bean: Wf_instancia_actividad
	 */
	public Wf_instancia_actividadKey(java.lang.String id_instancia_actividad) {
		this.id_instancia_actividad = id_instancia_actividad;
	}
	/**
	 * Returns true if both keys are equal.
	 */
	public boolean equals(java.lang.Object otherKey) {
		if (otherKey
			instanceof co.com.telefonica.atiempo.ejb.eb.Wf_instancia_actividadKey) {
			co.com.telefonica.atiempo.ejb.eb.Wf_instancia_actividadKey o =
				(co
					.com
					.telefonica
					.atiempo
					.ejb
					.eb
					.Wf_instancia_actividadKey) otherKey;
			return (
				(this.id_instancia_actividad.equals(o.id_instancia_actividad)));
		}
		return false;
	}
	/**
	 * Returns the hash code for the key.
	 */
	public int hashCode() {
		return (id_instancia_actividad.hashCode());
	}
}
