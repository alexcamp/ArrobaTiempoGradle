package co.com.telefonica.atiempo.ejb.eb;
/**
 * Key class for Entity Bean: Peticion_atis
 */
public class Peticion_atisKey implements java.io.Serializable {
	static final long serialVersionUID = 3206093459760846163L;
	/**
	 * Implementation field for persistent attribute: cod_pet_cd
	 */
	public java.lang.Long cod_pet_cd;
	/**
	 * Creates an empty key for Entity Bean: Peticion_atis
	 */
	public Peticion_atisKey() {
	}
	/**
	 * Creates a key for Entity Bean: Peticion_atis
	 */
	public Peticion_atisKey(java.lang.Long cod_pet_cd) {
		this.cod_pet_cd = cod_pet_cd;
	}
	/**
	 * Returns true if both keys are equal.
	 */
	public boolean equals(java.lang.Object otherKey) {
		if (otherKey
			instanceof co.com.telefonica.atiempo.ejb.eb.Peticion_atisKey) {
			co.com.telefonica.atiempo.ejb.eb.Peticion_atisKey o =
				(co.com.telefonica.atiempo.ejb.eb.Peticion_atisKey) otherKey;
			return ((this.cod_pet_cd.equals(o.cod_pet_cd)));
		}
		return false;
	}
	/**
	 * Returns the hash code for the key.
	 */
	public int hashCode() {
		return (cod_pet_cd.hashCode());
	}
}
