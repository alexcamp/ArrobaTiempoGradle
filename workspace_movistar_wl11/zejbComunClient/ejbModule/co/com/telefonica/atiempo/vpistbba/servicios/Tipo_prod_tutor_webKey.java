package co.com.telefonica.atiempo.vpistbba.servicios;
/**
 * Key class for Entity Bean: Tipo_prod_tutor_web
 */
public class Tipo_prod_tutor_webKey implements java.io.Serializable {
	static final long serialVersionUID = 3206093459760846163L;
	/**
	 * Implementation field for persistent attribute: ps_id
	 */
	public java.lang.String ps_id;
	/**
	 * Creates an empty key for Entity Bean: Tipo_prod_tutor_web
	 */
	public Tipo_prod_tutor_webKey() {
	}
	/**
	 * Creates a key for Entity Bean: Tipo_prod_tutor_web
	 */
	public Tipo_prod_tutor_webKey(java.lang.String ps_id) {
		this.ps_id = ps_id;
	}
	/**
	 * Returns true if both keys are equal.
	 */
	public boolean equals(java.lang.Object otherKey) {
		if (otherKey instanceof co.com.telefonica.atiempo.vpistbba.servicios.Tipo_prod_tutor_webKey) {
			co.com.telefonica.atiempo.vpistbba.servicios.Tipo_prod_tutor_webKey o = (co.com.telefonica.atiempo.vpistbba.servicios.Tipo_prod_tutor_webKey) otherKey;
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