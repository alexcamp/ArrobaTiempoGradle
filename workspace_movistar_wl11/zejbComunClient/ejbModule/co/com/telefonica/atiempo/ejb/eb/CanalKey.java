package co.com.telefonica.atiempo.ejb.eb;
/**
 * Key class for Entity Bean: Canal
 */
public class CanalKey implements java.io.Serializable {
	static final long serialVersionUID = 3206093459760846163L;
	/**
	 * Implementation field for persistent attribute: cod_cnl_ven_cd
	 */
	public java.lang.Long cod_cnl_ven_cd;
	/**
	 * Creates an empty key for Entity Bean: Canal
	 */
	public CanalKey() {
	}
	/**
	 * Returns true if both keys are equal.
	 */
	public boolean equals(java.lang.Object otherKey) {
		if (otherKey instanceof co.com.telefonica.atiempo.ejb.eb.CanalKey) {
			co.com.telefonica.atiempo.ejb.eb.CanalKey o =
				(co.com.telefonica.atiempo.ejb.eb.CanalKey) otherKey;
			return ((this.cod_cnl_ven_cd.equals(o.cod_cnl_ven_cd)));
		}
		return false;
	}
	/**
	 * Returns the hash code for the key.
	 */
	public int hashCode() {
		return (cod_cnl_ven_cd.hashCode());
	}
	/**
	 * Creates a key for Entity Bean: Canal
	 */
	public CanalKey(java.lang.Long cod_cnl_ven_cd) {
		this.cod_cnl_ven_cd = cod_cnl_ven_cd;
	}
}
