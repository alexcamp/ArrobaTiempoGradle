package co.com.telefonica.atiempo.soltec.ejb.eb;
/**
 * Key class for Entity Bean: Codigo_cierre
 */
public class Codigo_cierreKey implements java.io.Serializable {
	static final long serialVersionUID = 3206093459760846163L;
	/**
	 * Implementation field for persistent attribute: cod_cierre
	 */
	public java.lang.String cod_cierre;
	/**
	 * Creates a key for Entity Bean: Codigo_cierre
	 */
	public Codigo_cierreKey() {
	}
	/**
	 * Returns true if both keys are equal.
	 */
	public boolean equals(java.lang.Object otherKey) {
		if (otherKey
			instanceof co.com.telefonica.atiempo.soltec.ejb.eb.Codigo_cierreKey) {
			co.com.telefonica.atiempo.soltec.ejb.eb.Codigo_cierreKey o =
				(co
					.com
					.telefonica
					.atiempo
					.soltec
					.ejb
					.eb
					.Codigo_cierreKey) otherKey;
			return ((this.cod_cierre.equals(o.cod_cierre)));
		}
		return false;
	}
	/**
	 * Returns the hash code for the key.
	 */
	public int hashCode() {
		return (cod_cierre.hashCode());
	}
	/**
	 * Creates a key for Entity Bean: Codigo_cierre
	 */
	public Codigo_cierreKey(java.lang.String cod_cierre) {
		this.cod_cierre = cod_cierre;
	}
}
