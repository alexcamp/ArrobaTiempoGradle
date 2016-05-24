package co.com.telefonica.atiempo.ejb.eb;
/**
 * Key class for Entity Bean: IncidenciasDesdePI
 */
public class IncidenciasDesdePIKey implements java.io.Serializable {
	static final long serialVersionUID = 3206093459760846163L;
	/**
	 * Implementation field for persistent attribute: nroIncidencia
	 */
	public java.lang.Long nroIncidencia;
	/**
	 * Creates an empty key for Entity Bean: IncidenciasDesdePI
	 */
	public IncidenciasDesdePIKey() {
	}
	/**
	 * Returns true if both keys are equal.
	 */
	public boolean equals(java.lang.Object otherKey) {
		if (otherKey
			instanceof co.com.telefonica.atiempo.ejb.eb.IncidenciasDesdePIKey) {
			co.com.telefonica.atiempo.ejb.eb.IncidenciasDesdePIKey o =
				(co
					.com
					.telefonica
					.atiempo
					.ejb
					.eb
					.IncidenciasDesdePIKey) otherKey;
			return ((this.nroIncidencia.equals(o.nroIncidencia)));
		}
		return false;
	}
	/**
	 * Returns the hash code for the key.
	 */
	public int hashCode() {
		return (nroIncidencia.hashCode());
	}
	/**
	 * Creates a key for Entity Bean: IncidenciasDesdePI
	 */
	public IncidenciasDesdePIKey(java.lang.Long nroIncidencia) {
		this.nroIncidencia = nroIncidencia;
	}
}
