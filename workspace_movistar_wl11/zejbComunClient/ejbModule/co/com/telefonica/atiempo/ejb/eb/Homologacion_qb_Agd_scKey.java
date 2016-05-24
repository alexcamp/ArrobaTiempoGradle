package co.com.telefonica.atiempo.ejb.eb;
/**
 * Key class for Entity Bean: Homologacion_qb_Agd_sc
 */
public class Homologacion_qb_Agd_scKey implements java.io.Serializable {
	static final long serialVersionUID = 3206093459760846163L;
	/**
	 * Implementation field for persistent attribute: hq_agd_cod_familia
	 */
	public java.lang.String hq_agd_cod_familia;
	/**
	 * Returns true if both keys are equal.
	 */
	public boolean equals(java.lang.Object otherKey) {
		if (otherKey instanceof co.com.telefonica.atiempo.ejb.eb.Homologacion_qb_Agd_scKey) {
			co.com.telefonica.atiempo.ejb.eb.Homologacion_qb_Agd_scKey o = (co.com.telefonica.atiempo.ejb.eb.Homologacion_qb_Agd_scKey) otherKey;
			return ((this.hq_agd_cod_familia.equals(o.hq_agd_cod_familia)));
		}
		return false;
	}
	/**
	 * Returns the hash code for the key.
	 */
	public int hashCode() {
		return (hq_agd_cod_familia.hashCode());
	}
	/**
	 * Creates a key for Entity Bean: Homologacion_qb_Agd_sc
	 */
	public Homologacion_qb_Agd_scKey() {
	}
	/**
	 * Creates a key for Entity Bean: Homologacion_qb_Agd_sc
	 */
	public Homologacion_qb_Agd_scKey(java.lang.String hq_agd_cod_familia) {
		this.hq_agd_cod_familia = hq_agd_cod_familia;
	}
}
