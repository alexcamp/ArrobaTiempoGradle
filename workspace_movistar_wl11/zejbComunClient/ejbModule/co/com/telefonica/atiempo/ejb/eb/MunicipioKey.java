package co.com.telefonica.atiempo.ejb.eb;
/**
 * Key class for Entity Bean: Municipio
 */
public class MunicipioKey implements java.io.Serializable {
	static final long serialVersionUID = 3206093459760846163L;
	/**
	 * Implementation field for persistent attribute: cod_mun
	 */
	public java.lang.String cod_mun;
	/**
	 * Creates an empty key for Entity Bean: Municipio
	 */
	public MunicipioKey() {
	}
	/**
	 * Returns true if both keys are equal.
	 */
	public boolean equals(java.lang.Object otherKey) {
		if (otherKey
			instanceof co.com.telefonica.atiempo.ejb.eb.MunicipioKey) {
			co.com.telefonica.atiempo.ejb.eb.MunicipioKey o =
				(co.com.telefonica.atiempo.ejb.eb.MunicipioKey) otherKey;
			return ((this.cod_mun.equals(o.cod_mun)));
		}
		return false;
	}
	/**
	 * Returns the hash code for the key.
	 */
	public int hashCode() {
		return (cod_mun.hashCode());
	}
	/**
	 * Creates a key for Entity Bean: Municipio
	 */
	public MunicipioKey(java.lang.String cod_mun) {
		this.cod_mun = cod_mun;
	}
}
