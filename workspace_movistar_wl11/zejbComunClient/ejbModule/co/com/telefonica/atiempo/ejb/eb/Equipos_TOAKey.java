package co.com.telefonica.atiempo.ejb.eb;
/**
 * Key class for Entity Bean: Equipos_TOA
 */
public class Equipos_TOAKey implements java.io.Serializable {
	static final long serialVersionUID = 3206093459760846163L;
	/**
	 * Implementation field for persistent attribute: codigo_ps
	 */
	public java.lang.Long codigo_ps;
	/**
	 * Implementation field for persistent attribute: operacion_comercial
	 */
	public java.lang.Long operacion_comercial;
	/**
	 * Creates an empty key for Entity Bean: Equipos_TOA
	 */
	public Equipos_TOAKey() {
	}
	/**
	 * Creates a key for Entity Bean: Equipos_TOA
	 */
	public Equipos_TOAKey(
		java.lang.Long codigo_ps,
		java.lang.Long operacion_comercial) {
		this.codigo_ps = codigo_ps;
		this.operacion_comercial = operacion_comercial;
	}
	/**
	 * Returns true if both keys are equal.
	 */
	public boolean equals(java.lang.Object otherKey) {
		if (otherKey instanceof co.com.telefonica.atiempo.ejb.eb.Equipos_TOAKey) {
			co.com.telefonica.atiempo.ejb.eb.Equipos_TOAKey o = (co.com.telefonica.atiempo.ejb.eb.Equipos_TOAKey) otherKey;
			return ((this.codigo_ps.equals(o.codigo_ps)) && (this.operacion_comercial
				.equals(o.operacion_comercial)));
		}
		return false;
	}
	/**
	 * Returns the hash code for the key.
	 */
	public int hashCode() {
		return (codigo_ps.hashCode() + operacion_comercial.hashCode());
	}
}