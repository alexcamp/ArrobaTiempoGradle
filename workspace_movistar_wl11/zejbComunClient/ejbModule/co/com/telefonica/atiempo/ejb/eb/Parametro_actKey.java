package co.com.telefonica.atiempo.ejb.eb;
/**
 * Key class for Entity Bean: Parametro_act
 */
public class Parametro_actKey implements java.io.Serializable {
	static final long serialVersionUID = 3206093459760846163L;
	/**
	 * Implementation field for persistent attribute: act_cod_cd
	 */
	public java.lang.Long act_cod_cd;
	/**
	 * Creates an empty key for Entity Bean: Parametro_act
	 */
	public Parametro_actKey() {
	}
	/**
	 * Creates a key for Entity Bean: Parametro_act
	 */
	public Parametro_actKey(java.lang.Long act_cod_cd) {
		this.act_cod_cd = act_cod_cd;
	}
	/**
	 * Returns true if both keys are equal.
	 */
	public boolean equals(java.lang.Object otherKey) {
		if (otherKey instanceof co.com.telefonica.atiempo.ejb.eb.Parametro_actKey) {
			co.com.telefonica.atiempo.ejb.eb.Parametro_actKey o = (co.com.telefonica.atiempo.ejb.eb.Parametro_actKey) otherKey;
			return ((this.act_cod_cd.equals(o.act_cod_cd)));
		}
		return false;
	}
	/**
	 * Returns the hash code for the key.
	 */
	public int hashCode() {
		return (act_cod_cd.hashCode());
	}
}