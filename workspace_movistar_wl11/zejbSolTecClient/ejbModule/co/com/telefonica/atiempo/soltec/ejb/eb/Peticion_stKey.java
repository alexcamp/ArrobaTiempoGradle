package co.com.telefonica.atiempo.soltec.ejb.eb;
/**
 * Key class for Entity Bean: Peticion_st
 */
public class Peticion_stKey implements java.io.Serializable {
	static final long serialVersionUID = 3206093459760846163L;
	/**
	 * Implementation field for persistent attribute: cod_ave_cd
	 */
	public java.lang.Long cod_ave_cd;
	/**
	 * Creates an empty key for Entity Bean: Peticion_st
	 */
	public Peticion_stKey() {
	}
	/**
	 * Returns true if both keys are equal.
	 */
	public boolean equals(java.lang.Object otherKey) {
		if (otherKey
			instanceof co.com.telefonica.atiempo.soltec.ejb.eb.Peticion_stKey) {
			co.com.telefonica.atiempo.soltec.ejb.eb.Peticion_stKey o =
				(co
					.com
					.telefonica
					.atiempo
					.soltec
					.ejb
					.eb
					.Peticion_stKey) otherKey;
			return ((this.cod_ave_cd.equals(o.cod_ave_cd)));
		}
		return false;
	}
	/**
	 * Returns the hash code for the key.
	 */
	public int hashCode() {
		return (cod_ave_cd.hashCode());
	}
	/**
	 * Creates a key for Entity Bean: Peticion_st
	 */
	public Peticion_stKey(java.lang.Long cod_ave_cd) {
		this.cod_ave_cd = cod_ave_cd;
	}
}
