package co.com.telefonica.atiempo.ejb.eb;
/**
 * Key class for Entity Bean: Caracteristicas_linea
 */
public class Caracteristicas_lineaKey implements java.io.Serializable {
	static final long serialVersionUID = 3206093459760846163L;
	/**
	 * Implementation field for persistent attribute: ps_id
	 */
	public java.lang.Long ps_id;
	/**
	 * Creates an empty key for Entity Bean: Caracteristicas_linea
	 */
	public Caracteristicas_lineaKey() {
	}
	/**
	 * Creates a key for Entity Bean: Caracteristicas_linea
	 */
	public Caracteristicas_lineaKey(java.lang.Long ps_id) {
		this.ps_id = ps_id;
	}
	/**
	 * Returns true if both keys are equal.
	 */
	public boolean equals(java.lang.Object otherKey) {
		if (otherKey instanceof co.com.telefonica.atiempo.ejb.eb.Caracteristicas_lineaKey) {
			co.com.telefonica.atiempo.ejb.eb.Caracteristicas_lineaKey o = (co.com.telefonica.atiempo.ejb.eb.Caracteristicas_lineaKey) otherKey;
			return ((this.ps_id.equals(o.ps_id)));
		}
		return false;
	}
	/**
	 * Returns the hash code for the key.
	 */
	public int hashCode() {
		return (ps_id.hashCode());
	}
}