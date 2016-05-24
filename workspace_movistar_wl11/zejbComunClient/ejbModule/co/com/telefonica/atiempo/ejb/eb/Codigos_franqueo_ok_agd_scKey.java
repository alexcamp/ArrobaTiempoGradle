package co.com.telefonica.atiempo.ejb.eb;
/**
 * Key class for Entity Bean: Codigos_franqueo_ok_agd_sc
 */
public class Codigos_franqueo_ok_agd_scKey implements java.io.Serializable {
	static final long serialVersionUID = 3206093459760846163L;
	/**
	 * Implementation field for persistent attribute: codigo_franqueo
	 */
	public java.lang.String codigo_franqueo;
	/**
	 * Creates an empty key for Entity Bean: Codigos_franqueo_ok_agd_sc
	 */
	public Codigos_franqueo_ok_agd_scKey() {
	}
	/**
	 * Creates a key for Entity Bean: Codigos_franqueo_ok_agd_sc
	 */
	public Codigos_franqueo_ok_agd_scKey(java.lang.String codigo_franqueo) {
		this.codigo_franqueo = codigo_franqueo;
	}
	/**
	 * Returns true if both keys are equal.
	 */
	public boolean equals(java.lang.Object otherKey) {
		if (otherKey instanceof co.com.telefonica.atiempo.ejb.eb.Codigos_franqueo_ok_agd_scKey) {
			co.com.telefonica.atiempo.ejb.eb.Codigos_franqueo_ok_agd_scKey o = (co.com.telefonica.atiempo.ejb.eb.Codigos_franqueo_ok_agd_scKey) otherKey;
			return ((this.codigo_franqueo.equals(o.codigo_franqueo)));
		}
		return false;
	}
	/**
	 * Returns the hash code for the key.
	 */
	public int hashCode() {
		return (codigo_franqueo.hashCode());
	}
}