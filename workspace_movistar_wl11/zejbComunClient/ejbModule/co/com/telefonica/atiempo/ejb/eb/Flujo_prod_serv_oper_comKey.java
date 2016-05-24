package co.com.telefonica.atiempo.ejb.eb;
/**
 * Key class for Entity Bean: Flujo_prod_serv_oper_com
 */
public class Flujo_prod_serv_oper_comKey implements java.io.Serializable {
	static final long serialVersionUID = 3206093459760846163L;
	/**
	 * Implementation field for persistent attribute: flps_id
	 */
	public java.lang.Integer flps_id;
	/**
	 * Creates an empty key for Entity Bean: Flujo_prod_serv_oper_com
	 */
	public Flujo_prod_serv_oper_comKey() {
	}
	/**
	 * Creates a key for Entity Bean: Flujo_prod_serv_oper_com
	 */
	public Flujo_prod_serv_oper_comKey(java.lang.Integer flps_id) {
		this.flps_id = flps_id;
	}
	/**
	 * Returns true if both keys are equal.
	 */
	public boolean equals(java.lang.Object otherKey) {
		if (otherKey
			instanceof co.com.telefonica.atiempo.ejb.eb.Flujo_prod_serv_oper_comKey) {
			co.com.telefonica.atiempo.ejb.eb.Flujo_prod_serv_oper_comKey o =
				(co
					.com
					.telefonica
					.atiempo
					.ejb
					.eb
					.Flujo_prod_serv_oper_comKey) otherKey;
			return ((this.flps_id.equals(o.flps_id)));
		}
		return false;
	}
	/**
	 * Returns the hash code for the key.
	 */
	public int hashCode() {
		return (flps_id.hashCode());
	}
}
