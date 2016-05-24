package co.com.telefonica.atiempo.ejb.eb;
/**
 * Key class for Entity Bean: Estado_ps
 */
public class Estado_psKey implements java.io.Serializable {
	static final long serialVersionUID = 3206093459760846163L;
	/**
	 * Implementation field for persistent attribute: cod_estado_cierre
	 */
	public java.lang.Long cod_estado_cierre;
	/**
	 * Creates an empty key for Entity Bean: Estado_ps
	 */
	public Estado_psKey() {
	}
	/**
	 * Returns true if both keys are equal.
	 */
	public boolean equals(java.lang.Object otherKey) {
		if (otherKey
			instanceof co.com.telefonica.atiempo.ejb.eb.Estado_psKey) {
			co.com.telefonica.atiempo.ejb.eb.Estado_psKey o =
				(co.com.telefonica.atiempo.ejb.eb.Estado_psKey) otherKey;
			return ((this.cod_estado_cierre.equals(o.cod_estado_cierre)));
		}
		return false;
	}
	/**
	 * Returns the hash code for the key.
	 */
	public int hashCode() {
		return (cod_estado_cierre.hashCode());
	}
	/**
	 * Creates a key for Entity Bean: Estado_ps
	 */
	public Estado_psKey(java.lang.Long cod_estado_cierre) {
		this.cod_estado_cierre = cod_estado_cierre;
	}
}
