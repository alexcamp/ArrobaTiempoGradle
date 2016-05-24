package co.com.telefonica.atiempo.ejb.eb;
/**
 * Key class for Entity Bean: Causal_ps_oc_actividad
 */
public class Causal_ps_oc_actividadKey implements java.io.Serializable {
	static final long serialVersionUID = 3206093459760846163L;
	/**
	 * Implementation field for persistent attribute: correlativo
	 */
	public java.lang.Long correlativo;
	/**
	 * Creates an empty key for Entity Bean: Causal_ps_oc_actividad
	 */
	public Causal_ps_oc_actividadKey() {
	}
	/**
	 * Returns true if both keys are equal.
	 */
	public boolean equals(java.lang.Object otherKey) {
		if (otherKey
			instanceof co.com.telefonica.atiempo.ejb.eb.Causal_ps_oc_actividadKey) {
			co.com.telefonica.atiempo.ejb.eb.Causal_ps_oc_actividadKey o =
				(co
					.com
					.telefonica
					.atiempo
					.ejb
					.eb
					.Causal_ps_oc_actividadKey) otherKey;
			return ((this.correlativo.equals(o.correlativo)));
		}
		return false;
	}
	/**
	 * Returns the hash code for the key.
	 */
	public int hashCode() {
		return (correlativo.hashCode());
	}
	/**
	 * Creates a key for Entity Bean: Causal_ps_oc_actividad
	 */
	public Causal_ps_oc_actividadKey(java.lang.Long correlativo) {
		this.correlativo = correlativo;
	}
}
